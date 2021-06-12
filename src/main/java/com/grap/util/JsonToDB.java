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
import com.grap.recommendgameforuser.domain.RecommendGameForUser;
import com.grap.recommendgameforuser.repository.RecommendGameForUserRepository;
import com.grap.recommendgameforuser.service.RecommendGameForUserService;
import com.grap.relatedgame.domain.RelatedGame;
import com.grap.relatedgame.repository.RelatedGameRepository;
import com.grap.relatedgame.service.RelatedGameService;
import com.grap.review.dto.ReviewSaveRequestDto;
import com.grap.review.service.ReviewService;
import com.grap.starter.service.StarterService;
import com.grap.user.domain.Role;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import com.grap.user.service.UserService;
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
import java.util.Random;

@RequiredArgsConstructor
@Service
public class JsonToDB {
    /* 메인에서 사용 시
    @Autowired
    public static JsonToDB jsonToDB;

    @Autowired
    private JsonToDB jtb;

    @PostConstruct
    private void initStaticDao () {
        jsonToDB = this.jtb;
    }
    필요.
    메인 메소드에서
    jsonToDB.메소드();
    호출 필요
     */


    private final GameRepository gameRepository;

    private final CategoryRepository categoryRepository;

    private final RelatedGameService relatedGameService;
    private final RelatedGameRepository relatedGameRepository;

    private final CategoryTabService categoryTabService;
    private final CategoryTabRepository categoryTabRepository;

    private final CustomTabRepository customTabRepository;

    private final GameAndCustomTabService gameAndCustomTabService;
    private final GameAndCategoryService gameAndCategoryService;

    private final UserRepository userRepository;

    private final ReviewService reviewService;

    private final RecommendGameForUserService recommendGameForUserService;
    private final RecommendGameForUserRepository recommendGameForUserRepository;

    private final AWSS3 awsS3;

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

    @Transactional
    public void jsonToRecommnedGameForUserDB(String fileName){
        awsS3.downloadFromS3(fileName);
        recommendGameForUserRepository.customDeleteAll();

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

                Long userId = Long.parseLong(currentJSONObject.get("user_id").toString());

                String[] gameIdList = currentJSONObject.get("game_id").toString().split(" ");
                for (String gameId : gameIdList){
                    recommendGameForUserService.save(userId,Long.parseLong(gameId));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void jsonToGameDB(String fileName){
        // 본 서비스의 지속적인 운영에 필요한 기능은 아님
        // 개발 단계에서 테스트를 위해 준비된 게임 json 목록을 DB에 반영

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
    public void jsonToReviewDB(String fileName){
        // 본 서비스의 지속적인 운영에 필요한 기능은 아님
        // 추천 서비스 테스트를 위해 유저 리뷰 임의 생성을 위함

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

                ReviewSaveRequestDto requestDto = ReviewSaveRequestDto.builder()
                        .content("정말 좋은 게임입니다!")
                        .rating((int) Double.parseDouble(currentJSONObject.get("rating").toString()))
                        .build();
                reviewService.saveReview(Long.parseLong(currentJSONObject.get("userId").toString()), Long.parseLong(currentJSONObject.get("movieId").toString()),
                        requestDto);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void createRandomUser(int userNum){
        // 본 서비스의 지속적인 운영에 필요한 기능은 아님
        // 추천 서비스 테스트를 위해 유저 임의 생성을 위함

        JSONParser parser = new JSONParser();

        for(int i = 0; i < userNum; i++) {
            User user = User.builder()
                    .picture("https://yt3.ggpht.com/ytc/AAUvwngm3mI8Oq_xqMF-DyMjcIpyfjfoMWscBTRh_g=s48-c-k-c0x00ffffff-no-rj")
                    .email(createRandomString(10) + "@test.com")
                    .nickname("닉네임 " + createRandomString(6))
                    .name("테스트 계정")
                    .role(Role.USER)
                    .build();

            userRepository.save(user);
        }
    }

    public String createRandomString(int codeLength){
        final char[] possibleCharacters =
                {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F',
                        'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V',
                        'W','X','Y','Z'};

        final int possibleCharacterCount = possibleCharacters.length;
        Random rnd = new Random();
        StringBuffer buf = new StringBuffer(16);

        for (int i= 0; i < codeLength; i++) {
            buf.append(possibleCharacters[rnd.nextInt(possibleCharacterCount)]);
        }

        return buf.toString();
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
