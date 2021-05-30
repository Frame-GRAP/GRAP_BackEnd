package com.grap.util;

import com.grap.category.domain.Category;
import com.grap.category.dto.CategoryResponseDto;
import com.grap.category.repository.CategoryRepository;
import com.grap.categorytab.repository.CategoryTabRepository;
import com.grap.categorytab.service.CategoryTabService;
import com.grap.customtab.domain.CustomTab;
import com.grap.customtab.repository.CustomTabRepository;
import com.grap.customtab.service.CustomTabService;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.game.service.GameService;
import com.grap.gameandcategory.service.GameAndCategoryService;
import com.grap.gameandcustomtab.service.GameAndCustomTabService;
import com.grap.relatedgame.domain.RelatedGame;
import com.grap.relatedgame.repository.RelatedGameRepository;
import com.grap.relatedgame.service.RelatedGameService;
import com.grap.starter.service.StarterService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JsonToDB {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final RelatedGameService relatedGameService;
    private final RelatedGameRepository relatedGameRepository;
    private final CategoryTabService categoryTabService;
    private final CategoryTabRepository categoryTabRepository;
    private final CustomTabRepository customTabRepository;
    private final GameAndCustomTabService gameAndCustomTabService;
    private final GameAndCategoryService gameAndCategoryService;
    private final AWSS3 awsS3;

    @Transactional
    public void jsonToGameDB(String fileName){
//        saveCategories(); // 카테고리 없을 때
        //saveCustomTab("최신 인기 게임"); // 커스텀 탭 넣을 시

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(
                    "src/main/resources/json/" + fileName
            ));
            JSONObject jsonObject = (JSONObject) obj;
            Iterator<String> keys = jsonObject.keySet().iterator();
            while(keys.hasNext()){
                String key = keys.next();
                JSONObject currentJSONObject = (JSONObject)jsonObject.get(key);

                Game game = Game.builder()
                        .name(removeSepecialSymbol(currentJSONObject.get("name").toString()))
                        .description(currentJSONObject.get("short_description").toString())
                        .developer(currentJSONObject.get("developers").toString())
                        .publisher(currentJSONObject.get("publishers").toString())
                        .releaseDate(LocalDate.parse("1996-05-17"))
                        .headerImg(currentJSONObject.get("header_image").toString())
                        .downloadUrl(currentJSONObject.get("download_url").toString())
                        .rating(Double.parseDouble(currentJSONObject.get("vote_average").toString()))
                        .voteCount(Integer.parseInt(currentJSONObject.get("vote_count").toString()))
                        .build();

                gameRepository.save(game);

                JSONObject tags =  (JSONObject) currentJSONObject.get("tags");
                Iterator<String> tagsKeys = tags.keySet().iterator();
                while(tagsKeys.hasNext()) {
                    String tagKey = tagsKeys.next();
                    gameAndCategoryService.save(game.getId(), categoryRepository.findByName(tagKey).getId());
                }

                // saveGameAndCustomTab(game.getId(), 1L); // 커스텀 탭에 게임을 넣을 시
                // starterService.saveStarter(game.getId()); // 스타터에 게임을 넣을 시
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void jsonToRelatedGameDB(String fileName){
        awsS3.downloadFromS3(fileName);
        relatedGameRepository.customDeleteAll();

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(
                    "src/main/resources/json/" + fileName
            ));
            JSONObject jsonObject = (JSONObject) obj;
            Iterator<String> keys = jsonObject.keySet().iterator();
            while(keys.hasNext()){
                String key = keys.next();
                JSONObject currentJSONObject = (JSONObject)jsonObject.get(key);

                relatedGameService.save(Long.parseLong(currentJSONObject.get("game_id").toString()), currentJSONObject.get("related_game_id").toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void jsonToCategoryTabDB(String fileName){
        awsS3.downloadFromS3(fileName);
        categoryTabRepository.customDeleteAll();

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(
                    "src/main/resources/json/" + fileName
            ));
            JSONObject jsonObject = (JSONObject) obj;
            Iterator<String> keys = jsonObject.keySet().iterator();
            while(keys.hasNext()){
                String key = keys.next();
                JSONObject currentJSONObject = (JSONObject)jsonObject.get(key);

                Category category = categoryRepository.findByName(currentJSONObject.get("category_name").toString());

                String[] gameIdList = currentJSONObject.get("game_id").toString().split(" ");
                for (String gameId : gameIdList){
                    categoryTabService.saveCategoryTab(Long.parseLong(gameId), category.getId());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveGameAndCustomTab(Long gameId, Long customTabId){
        gameAndCustomTabService.saveGameAndCustomTab(gameId, customTabId);
        return;
    }

    public void saveCustomTab(String name){
        customTabRepository.save(CustomTab.builder()
                .name(name)
                .build());
    }

    private void saveCategories(){
        String[][] categoryNames = { {"Arcade", "Horror", "Action","Adventure", "Casual", "Strategy", "FPS", "RPG"
                , "Simulation", "Puzzle", "2D", "Atmospheric", "Story Rich", "Sci-fi", "Fantasy", "Colorful"},
                {"아케이드", "무서운", "액션", "모험적인", "캐주얼한", "전략적인", "FPS", "롤플레잉", "시뮬레이션", "퍼즐", "2D", "분위기 있는"
                , "풍부한 스토리의", "상상력을 자극하는", "판타지", "색감이 다채로운"}};

        for (int i = 0; i < categoryNames[0].length ; i++){
            categoryRepository.save(Category.builder()
                    .name(categoryNames[0][i])
                    .uiName(categoryNames[1][i])
                    .build());
        }
    }

    private String removeSepecialSymbol(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s\\p{Punct}]";
        str =str.replaceAll(match, "");
        return str;
    }
}
