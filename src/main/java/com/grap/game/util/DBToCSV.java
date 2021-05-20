package com.grap.game.util;

import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
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
    dbToCSV.makeCSV();
    호출 필요
     */
    private final GameRepository gameRepository;

    public void makeCSV() {
        //List<Game> games = gameRepository.findAll();
        String filePath = "src/main/resources/csv/game.csv";
        try{
            BufferedWriter fw
                    = new BufferedWriter(new FileWriter(filePath, true));

            fw.write("악"+","+"오");
            fw.newLine();
            fw.write("악1"+","+"오1");
            fw.newLine();
            fw.write("악2"+","+"오2");

            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일 경로가 잘못되었습니다. filePath = " + filePath);
        }
    }

}
