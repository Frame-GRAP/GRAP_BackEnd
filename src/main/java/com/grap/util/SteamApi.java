package com.grap.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SteamApi {

    /* 메인에서 사용 시
    @Autowired
    public static SteamApi steamapi;

    @Autowired
    private SteamApi api;

    @PostConstruct
    private void initStaticDao () {
        steamapi = this.api;
    }
    필요.
    메인 메소드에서
    steamapi.saveSteamData();
    호출 필요
     */
    private Game game = new Game();

    @Autowired
    private GameRepository gameRepository;

    private JsonNode getJSonFromUrl(String url) {
        InputStream is = null;
        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

            String jsonText = sb.toString();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonText);
            return node;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveSteamData(){
        String SteamTop100ApiUrl = "https://steamspy.com/api.php?request=top100forever";
        String SteamAppDetailUrl = "https://store.steampowered.com/api/appdetails?appids=";

        JsonNode top100Node = getJSonFromUrl(SteamTop100ApiUrl);
        Iterator itr  = top100Node.elements();

        while( itr.hasNext()) {
            JsonNode obj = (JsonNode)itr.next();
            String appId = obj.get("appid").toString();

            JsonNode infoNode = getJSonFromUrl(SteamAppDetailUrl + appId).get(appId);
            if(infoNode.get("success").toString().equals("false")){
                continue;
            }

            infoNode = infoNode.get("data");

            gameRepository.save(Game.builder()
                    .name(removeSepecialSymbol(infoNode.get("name").asText()))
                    .description(infoNode.get("short_description").asText())
                    .developer(infoNode.get("developers").toString())
                    .publisher(infoNode.get("publishers").toString())
                    .releaseDate(LocalDate.parse("1996-05-17"))
                    .headerImg("https://steamcdn-a.akamaihd.net/steam/apps/" + appId + "/header.jpg")
                    .downloadUrl("https://store.steampowered.com/app/" + appId)
                    .build());
        }
    }

    private String removeSepecialSymbol(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s\\p{Punct}]";
        str =str.replaceAll(match, "");
        return str;
    }

    public void createCSV(){
//        File file = null;
//        BufferedWriter bw = null;
//        String newLine = System.lineSeparator();

        List<Game> g = gameRepository.findAll();
        g.forEach(a -> System.out.println(a.getName()));
    }

}
