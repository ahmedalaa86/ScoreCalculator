package org.moodle.scoreCalculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScoreCalculatorApplication{

    private static final Logger log = LoggerFactory.getLogger(ScoreCalculatorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ScoreCalculatorApplication.class, args);
    }

}