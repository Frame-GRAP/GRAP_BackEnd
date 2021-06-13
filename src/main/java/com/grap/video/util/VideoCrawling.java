package com.grap.video.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.video.domain.Video;
import com.grap.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class VideoCrawling {
    /* 메인에서 사용 시
    @Autowired
    public static VideoCrawling videocrawling;

    @Autowired
    private VideoCrawling crawling;

    @PostConstruct
    private void initStaticDao () {
        videocrawling = this.crawling;
    }
    필요.
    메인 메소드에서 videocrawling.startCrawl(게임 아이디); 호출 필요
     */

    @Autowired
    public VideoRepository videoRepository;

    @Autowired
    GameRepository gameRepository;

    int time_limit = 5;

    private Game findGameByName(String gameName){
        Optional<Game> gameOp = gameRepository.findByName(gameName);
        if(!gameOp.isPresent()) {
            throw new IllegalArgumentException("일치하는 게임이 없습니다. name =" + gameName);
        }
        return gameOp.get();
    }

    private Game findGameById(Long Id){
        Optional<Game> gameOp = gameRepository.findById(Id);
        if(!gameOp.isPresent()) {
            throw new IllegalArgumentException("일치하는 게임이 없습니다. Id =" + Id);
        }
        return gameOp.get();
    }

    @Async
    public void startCrawl(Long id){
        Game gameEntity = findGameById(id);
        googleCrawl(gameEntity.getName(), gameEntity);
        twitchCrawl(gameEntity.getName(), gameEntity);
    }

    private void googleCrawl(String gameName, Game gameEntity){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--single-process");
        options.addArguments("--disable-dev-shm-usage");

//        System.setProperty("webdriver.chrome.driver", "C:/저장할 것들/기타자료/chromedriver/chromedriver.exe"); // 로컬용
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); // ec2 서버용
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.youtube.com/results?search_query=" + gameName + " game play, video");

        scrollDown(15, driver);

        String req = driver.getPageSource();
        Document doc = Jsoup.parse(req);

        Elements videos = doc.select("#contents > ytd-video-renderer");
        for (Element video : videos){
            try {
                String time = video.selectFirst("#overlays > ytd-thumbnail-overlay-time-status-renderer > span").text();

                String[] timeSplit = time.split(":");
                if (timeSplit.length > 2)
                    continue;

                int minute = Integer.parseInt(timeSplit[0]);
                if (minute < time_limit) {
                    String title = video.selectFirst("#video-title").text();
                    String uploader = video.selectFirst("#text > a").text();
                    String urlKey = video.selectFirst("#thumbnail").attr("href").toString().split("=")[1];

                    Element imageTag = video.selectFirst("#img");
                    if (!imageTag.hasAttr("src"))
                        continue;
                    String image = imageTag.attr("src");


                    videoRepository.save(Video.builder()
                            .title(title)
                            .uploader(uploader)
                            .platform("youtube")
                            .urlKey(urlKey)
                            .length(time)
                            .gameName(gameName)
                            .game(gameEntity)
                            .image(image)
                            .build());
                }
            }catch(NullPointerException e){
                System.out.println("유튜브 : " + gameName);
                continue;
            }
        }
        driver.close();
    }

    private void twitchCrawl(String gameName, Game gameEntity){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--single-process");
        options.addArguments("--disable-dev-shm-usage");

//        System.setProperty("webdriver.chrome.driver", "C:/저장할 것들/기타자료/chromedriver/chromedriver.exe"); // 로컬용
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); // ec2 서버용
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.twitch.tv/directory/game/" + gameName + "/clips");

        prepareTwitchScrollDown(driver);
        scrollDown(3, driver);

        String req = driver.getPageSource();
        Document doc = Jsoup.parse(req);

        try{
            Elements videos = doc.select("#root > div > div.sc-AxjAm.tlQbp > div > main > div.root-scrollable.scrollable-area.scrollable-area--suppress-scroll-x > div.simplebar-scroll-content > div > div > div > div > div > div.sc-AxjAm.gBRyDo > div.sc-AxjAm.fzeiWJ > div > div > div > div");
            for (Element video : videos) {
                String time = video.selectFirst("article > div.sc-AxjAm.kJBVIw > div > div.ScTransformWrapper-uo2e2v-1.eiQqOY > a > div > div > div.ScPositionOver-sc-1iiybo2-0.hahEKi.tw-media-card-image__corners > div.sc-AxjAm.eFyVLi > div > p").text();
                String title = video.selectFirst("article > div.sc-AxjAm.czqVsG > div > div.ScTextWrapper-sc-14f6evl-1.gboCPP > div:nth-child(1) > div > a > div > h3").text();
                String uploader = video.selectFirst("article > div.sc-AxjAm.czqVsG > div > div.ScTextWrapper-sc-14f6evl-1.gboCPP > div:nth-child(2) > p:nth-child(1) > a").text();
                String urlKey = video.selectFirst("article > div.sc-AxjAm.kJBVIw > div > div.ScTransformWrapper-uo2e2v-1.eiQqOY > a").attr("href").split("/")[3];
                String image = video.selectFirst("article > div.sc-AxjAm.kJBVIw > div > div.ScTransformWrapper-uo2e2v-1.eiQqOY > a > div > div > div.ScAspectRatio-sc-1sw3lwy-1.dNNaBC.tw-aspect > img").attr("src");
                videoRepository.save(Video.builder()
                        .title(title)
                        .uploader(uploader)
                        .platform("twitch")
                        .urlKey(urlKey)
                        .length(time)
                        .gameName(gameName)
                        .game(gameEntity)
                        .image(image)
                        .build());
            }
        }catch(NoSuchElementException e){
            System.out.println("트위치(NoSuch) : " + gameName);
            driver.close();
            return;
        }catch(Exception e){
            System.out.println("트위치 : " + e + " / 게임 이름 : " + gameName);
            driver.close();
            return;
        }

        driver.close();
    }

    private void timeSleep(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scrollDown(int count, WebDriver driver){
        timeSleep(2);
        WebElement html = driver.findElement(By.cssSelector("html"));
        for (int i = 0; i < count; i++){
            html.sendKeys(Keys.PAGE_DOWN);
            timeSleep(1);
        }
        //timeSleep(2);
    }

    private void prepareTwitchScrollDown(WebDriver driver){
        timeSleep(3);
        WebElement forScroll = driver.findElement(By.cssSelector("#root > div > div.sc-AxjAm.tlQbp > div > main > div.root-scrollable.scrollable-area.scrollable-area--suppress-scroll-x"));
        forScroll.click();
    }

}
