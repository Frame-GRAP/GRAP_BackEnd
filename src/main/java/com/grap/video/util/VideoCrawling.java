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
import org.springframework.beans.factory.annotation.Autowired;
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
    메인 메소드에서 videocrawling.startCrawl(게임이름); 호출 필요
     */

    @Autowired
    public VideoRepository videoRepository;

    @Autowired
    GameRepository gameRepository;

    int time_limit = 5;
    String chromeDriverPath = "C:/저장할 것들/기타자료/chromedriver/chromedriver.exe";

    private Game findGameByName(String gameName){
        System.out.println(gameName + " 크롤링 시작");
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

    public void startCrawl(Long id){
//        gameRepository.save(Game.builder()
//                .name(gameName)
//                .description("description")
//                .developer("developer")
//                .publisher("publisher")
//                .releaseDate(LocalDate.now())
//                .headerImg("headerImg")
//                .downloadUrl("downloadUrl")
//                .build());

        Game gameEntity = findGameById(id);
        googleCrawl(gameEntity.getName(), gameEntity);
        twitchCrawl(gameEntity.getName(), gameEntity);
    }

    private void googleCrawl(String gameName, Game gameEntity){
        System.setProperty("webdriver.chrome.driver", "C:/저장할 것들/기타자료/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.youtube.com/results?search_query=" + gameName + ", video");

        scrollDown(15, driver);

        String req = driver.getPageSource();
        Document doc = Jsoup.parse(req);

        Elements videos = doc.select("#contents > ytd-video-renderer");
        for (Element video : videos){
            try {
                String time = video.selectFirst("#overlays > ytd-thumbnail-overlay-time-status-renderer > span").text();
                System.out.println(time);

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
                driver.close();
                continue;
            }
        }
        driver.close();
    }

    private void twitchCrawl(String gameName, Game gameEntity){
        System.setProperty("webdriver.chrome.driver", "C:/저장할 것들/기타자료/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.twitch.tv/directory/game/" + gameName + "/clips");

        try {
            prepareTwitchScrollDown(driver);
            scrollDown(3, driver);

            String req = driver.getPageSource();
            Document doc = Jsoup.parse(req);

            Elements videos = doc.select(".ScTower-sc-1dei8tr-0.hRbnOC.tw-tower > .tw-mg-b-2");
            for (Element video : videos) {
                String time = video.selectFirst("article > div.tw-item-order-1 > div > div.ScTransformWrapper-uo2e2v-1.coZOAa > a > div > div > div.tw-absolute.tw-full-height.tw-full-width.tw-left-0.tw-media-card-image__corners.tw-top-0 > div.tw-absolute.tw-left-0.tw-mg-1.tw-top-0 > div > p").text();
                String title = video.selectFirst("article > div.tw-item-order-2.tw-mg-t-1 > div > div.tw-flex-grow-1.tw-flex-shrink-1.tw-full-width.tw-item-order-2.tw-media-card-meta__text-container > div.tw-media-card-meta__title > div > a > div > h3").text();
                String uploader = video.selectFirst("article > div.tw-item-order-2.tw-mg-t-1 > div > div.tw-flex-grow-1.tw-flex-shrink-1.tw-full-width.tw-item-order-2.tw-media-card-meta__text-container > div.tw-media-card-meta__links > div:nth-child(1) > p > a").text();
                String urlKey = video.selectFirst("article > div.tw-item-order-1 > div > div.ScTransformWrapper-uo2e2v-1.coZOAa > a").attr("href").split("/")[3];
                String image = video.selectFirst("div.tw-item-order-1 > div > div.ScTransformWrapper-uo2e2v-1.coZOAa > a > div > div > div.ScAspectRatio-sc-1sw3lwy-1.dNNaBC.tw-aspect > img").attr("src");
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
        WebElement forScroll = driver.findElement(By.cssSelector("#root > div > div.tw-flex.tw-flex-column.tw-flex-nowrap.tw-full-height > div > main > div.root-scrollable.scrollable-area.scrollable-area--suppress-scroll-x > div.simplebar-scroll-content > div > div > div > div > div > div.directory-header-new__banner-cover.tw-overflow-hidden.tw-relative > div.directory-header-new__info.tw-bottom-0.tw-left-0.tw-pd-b-2.tw-pd-t-3.tw-right-0 > div > div.tw-flex.tw-flex-column.tw-justify-content-center > div.tw-flex.tw-justify-content-between.tw-relative > h1"));
        forScroll.click();
    }

}
