package com.grap.util.controller;

import com.grap.util.JsonToDB;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class utilController {

    private final JsonToDB jsonToDB;

    @GetMapping("/api/util/saveJson")
    public void jsonInS3ToDB() {
        jsonToDB.jsonToRelatedGameDB("related_game_list.json");
        jsonToDB.jsonToCategoryTabDB("category_tab_list.json");

        return;
    }
}
