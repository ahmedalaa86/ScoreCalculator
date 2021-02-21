package org.moodle.scoreCalculator.service;

import java.util.List;

import org.moodle.scoreCalculator.model.QuestionaireChoice;
import org.moodle.scoreCalculator.model.QuestionaireResponseDetails;
import org.moodle.scoreCalculator.repository.QuestionaireChoiceRepository;
import org.moodle.scoreCalculator.repository.QuestionaireResponseDetailsRepository;
import org.moodle.scoreCalculator.repository.QuestionaireResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionaireResponseService {
	@Autowired
	QuestionaireResponseRepository questionaireResponseRepository;
	
	@Autowired
	QuestionaireResponseDetailsRepository questionaireResponseDetailsRepository;
	
	@Autowired
	QuestionaireChoiceRepository questionaireChoiceRepository;
	
	public void getQuistionaire(){
		//Iterable<QuistionaireResponse> list = repo.findAll();
		questionaireResponseRepository.findAll().forEach(questionaire -> {
			if(questionaire.getScore1() == null) {
				List<QuestionaireResponseDetails> questionaireDetails = questionaireResponseDetailsRepository.findByResponseId(questionaire.getId());
				int score1=0,score2=0,score3=0,score4=0;
			    for(int i=0;i<questionaireDetails.size();i++) {
			    	if(i >= 0 && i < 11) {
			    		QuestionaireChoice choice = questionaireChoiceRepository.findById(questionaireDetails.get(i).getChoiceId()).get();
			    		score1+=choice.getWeight();
			    	}
			    	
			    	if(i >= 11 && i < 22) {
			    		QuestionaireChoice choice = questionaireChoiceRepository.findById(questionaireDetails.get(i).getChoiceId()).get();
			    		score2+=choice.getWeight();
			    	}
			    	
			    	if(i >= 22 && i < 33) {
			    		QuestionaireChoice choice = questionaireChoiceRepository.findById(questionaireDetails.get(i).getChoiceId()).get();
			    		score3+=choice.getWeight();
			    	}
			    	
			    	if(i >= 33 && i < 44) {
			    		QuestionaireChoice choice = questionaireChoiceRepository.findById(questionaireDetails.get(i).getChoiceId()).get();
			    		score4+=choice.getWeight();
			    	}
			    }

			    questionaire.setScore1(score1);
			    questionaire.setScore2(score2);
			    questionaire.setScore3(score3);
			    questionaire.setScore4(score4);
			    
			    questionaireResponseRepository.save(questionaire);
			}
			
			
		});
	}
}
