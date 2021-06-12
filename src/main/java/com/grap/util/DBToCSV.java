package com.grap.util;

import com.grap.category.dto.CategoryResponseDto;
import com.grap.category.service.CategoryService;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.review.domain.Review;
import com.grap.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DBToCSV {
    /* 메인에서 사용 시
    @Autowired
    public static DBToCSV dbToCSV;

    @Autowired
    private DBToCSV csv;

    @PostConstruct
    private void initStaticDao () {
        dbToCSV = this.csv;
    }
    필요.
    메인 메소드에서
    dbToCSV.메소드();
    호출 필요
     */
    private final GameRepository gameRepository;
    private final ReviewRepository reviewRepository;
    private final CategoryService categoryService;
    private final AWSS3 awsS3;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Scheduled(cron = "0 0 4 * * *")
    public void startDBToCSV(){
        gameDBToCSV();
        reviewDBToCSV();
        requestFlaskAPI();
    }

    public void gameDBToCSV() {
        String filePath = "src/main/resources/csv/game.csv";

        File file = new File(filePath);
        if (file.exists()){
            file.delete();
        }

        try{
            BufferedWriter fw
                    = new BufferedWriter(new FileWriter(filePath, true));

            fw.write("id,genres,vote_average,vote_count,name,developer,publisher");
            fw.newLine();

            List<Game> games = gameRepository.findAll();

            for (Game game : games){
                fw.write(game.getId().toString()+",");

                List<CategoryResponseDto> categories = categoryService.findByGame(game.getId());
                String temp = "";
                for (CategoryResponseDto category : categories){
                    fw.write(category.getName()+"|");
                }
                fw.write(",");

                fw.write(Double.toString(game.getRating())+",");
                fw.write(Integer.toString(game.getVoteCount())+",");

                String gameName = game.getName().replace(",", " ");
                fw.write(gameName+",");

                String developer = game.getDeveloper().replace(",","|").replace("[", "").replace("\"", "").replace("]", "");
                fw.write(developer+",");

                String publisher = game.getDeveloper().replace(",","|").replace("[", "").replace("\"", "").replace("]", "");
                fw.write(publisher);
                fw.newLine();
            }

            fw.flush();
            fw.close();

            File csvFile = new File(filePath);
            awsS3.UploadToS3(csvFile, "game.csv");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일 경로가 잘못되었습니다. filePath = " + filePath);
        }
    }

    public void reviewDBToCSV() {
        String filePath = "src/main/resources/csv/ratings.csv";

        File file = new File(filePath);
        if (file.exists()){
            file.delete();
        }

        try{
            BufferedWriter fw
                    = new BufferedWriter(new FileWriter(filePath, true));

            fw.write(",userId,movieId,rating,timestamp");
            fw.newLine();

            List<Review> reviews = reviewRepository.findAll();
            int i = 1;

            for (Review review : reviews){
                fw.write(Integer.toString(i)+",");
                i++;
                fw.write(review.getUser().getId().toString()+",");
                fw.write(review.getGame().getId().toString()+",");
                fw.write(review.getRating()+",");
                fw.write("0");


                fw.newLine();
            }

            fw.flush();
            fw.close();

            File csvFile = new File(filePath);
            awsS3.UploadToS3(csvFile, "ratings.csv");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일 경로가 잘못되었습니다. filePath = " + filePath);
        }
    }

    public void requestFlaskAPI(){
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = restTemplate();
        restTemplate.exchange("http://127.0.0.1:5000/", HttpMethod.GET, entity, String.class).getBody();
    }

}
