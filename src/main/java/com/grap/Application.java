package com.grap;

import com.grap.util.DBToCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@EnableJpaAuditing
@SpringBootApplication
    public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}