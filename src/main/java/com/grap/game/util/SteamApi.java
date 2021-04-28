package com.grap.game.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Iterator;

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
    필요
     */
    private Game game = new Game();

    @Autowired
    private GameRepository gameRepository;

    public JsonNode getJSonFromUrl(String url) {
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
        int id = 100;
        String SteamTop100ApiUrl = "https://steamspy.com/api.php?request=top100forever";
        String SteamAppDetailUrl = "https://store.steampowered.com/api/appdetails?appids=";

        JsonNode top100Node = getJSonFromUrl(SteamTop100ApiUrl);
        Iterator itr  = top100Node.elements();

        while( itr.hasNext()) {
            JsonNode obj = (JsonNode)itr.next();
            String appId = obj.get("appid").toString();

            JsonNode detailNode = getJSonFromUrl(SteamAppDetailUrl + appId).get(appId);
            if(detailNode.get("success").toString().equals("false")){
                continue;
            }

            detailNode = detailNode.get("data");
            LocalDate date = LocalDate.parse("1996-05-17");
            Game savedGame = gameRepository.save(Game.builder()
                    .name(detailNode.get("name").toString())
                    .description(detailNode.get("short_description").toString())
                    .developer(detailNode.get("developers").toString())
                    .publisher(detailNode.get("publishers").toString())
                    .releaseDate(date)
                    .headerImg("https://steamcdn-a.akamaihd.net/steam/apps/" + appId + "/header.jpg")
                    .downloadUrl("https://store.steampowered.com/app/" + appId)
                    .rating(0.0)
                    .build());
        }
    }


}
