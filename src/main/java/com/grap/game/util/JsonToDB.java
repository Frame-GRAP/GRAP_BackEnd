package com.grap.game.util;

import com.grap.category.domain.Category;
import com.grap.category.dto.CategoryResponseDto;
import com.grap.category.repository.CategoryRepository;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.gameandcategory.service.GameAndCategoryService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

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
    jsonToDB.jsonToDB();
    호출 필요
     */

    private Game game = new Game();

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GameAndCategoryService gameAndCategoryService;


    public void jsonToDB(){
        saveCategories(); // 테스트용

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

    private void saveCategories(){
        String[] categoryNames = { "Arcade", "Horror", "Action","Adventure", "Casual", "Strategy", "FPS", "RPG"
                , "Simulation", "Puzzle", "2D", "Atmospheric", "Story Rich", "Sci-fi", "Fantasy", "Colorful" };

        for (String categoryName : categoryNames){
            categoryRepository.save(Category.builder()
                    .name(categoryName)
                    .build());
        }
    }

    private String removeSepecialSymbol(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s\\p{Punct}]";
        str =str.replaceAll(match, "");
        return str;
    }
}
