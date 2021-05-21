package com.grap.game.util;

import com.grap.category.domain.Category;
import com.grap.category.dto.CategoryResponseDto;
import com.grap.category.repository.CategoryRepository;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.game.service.GameService;
import com.grap.gameandcategory.service.GameAndCategoryService;
import com.grap.relatedgame.domain.RelatedGame;
import com.grap.relatedgame.repository.RelatedGameRepository;
import com.grap.relatedgame.service.RelatedGameService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JsonToDB {
    /* 메인에서 사용 시
    @Autowired
    public static JsonToDB jsonToDB;

    @Autowired
    private JsonToDB db;

    @PostConstruct
    private void initStaticDao () {
        jsonToDB = this.db;
    }
    필요.
    메인 메소드에서
    jsonToDB.메소드();
    호출 필요
     */

    private Game game = new Game();

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RelatedGameService relatedGameService;

    @Autowired
    private GameAndCategoryService gameAndCategoryService;

    @Autowired
    private GameService gameService;


    public void jsonToGameDB(){
        //saveCategories(); // 카테고리 없을 때

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(
                    "src/main/resources/json/game_detail.json"
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
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void jsonToRelatedGameDB(){
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(
                    "src/main/resources/json/related_game_list.json"
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

    private void saveCategories(){
        String[][] categoryNames = { {"Arcade", "Horror", "Action","Adventure", "Casual", "Strategy", "FPS", "RPG"
                , "Simulation", "Puzzle", "2D", "Atmospheric", "Story Rich", "Sci-fi", "Fantasy", "Colorful"},
                {"아케이드", "무서운", "액션", "모험적인", "캐주얼한", "전략적인", "FPS", "롤플레잉", "시뮬레이션", "퍼즐", "2D", "분위기 있는"
                , "풍부한 스토리의", "상상력을 자극하는", "판타지", "색감이 다채로운"}};

        for (String[] categoryName : categoryNames){
            categoryRepository.save(Category.builder()
                    .name(categoryName[0])
                    .name(categoryName[1])
                    .build());
        }
    }

    private String removeSepecialSymbol(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s\\p{Punct}]";
        str =str.replaceAll(match, "");
        return str;
    }
}
