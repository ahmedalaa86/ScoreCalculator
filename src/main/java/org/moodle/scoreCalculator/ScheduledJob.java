package org.moodle.scoreCalculator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.moodle.scoreCalculator.service.QuestionaireResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJob {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	QuestionaireResponseService service;
	
    @Scheduled(fixedDelay = 20000)
    public void reportCurrentTime() {
        System.out.println("The time is -: "+ dateFormat.format(new Date()));
        //service.handleUserPreferences();
        service.handleQuestionsDifficulty();
    }
}
