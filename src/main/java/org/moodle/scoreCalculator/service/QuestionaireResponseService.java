package org.moodle.scoreCalculator.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.moodle.scoreCalculator.model.CourseModule;
import org.moodle.scoreCalculator.model.Question;
import org.moodle.scoreCalculator.model.QuestionAttempt;
import org.moodle.scoreCalculator.model.QuestionCategory;
import org.moodle.scoreCalculator.model.QuestionaireChoice;
import org.moodle.scoreCalculator.model.QuestionaireResponseDetails;
import org.moodle.scoreCalculator.model.UserLog;
import org.moodle.scoreCalculator.repository.CourseModuleRepository;
import org.moodle.scoreCalculator.repository.QuestionAttemptRepository;
import org.moodle.scoreCalculator.repository.QuestionAttemptStepsRepository;
import org.moodle.scoreCalculator.repository.QuestionCategoryRepository;
import org.moodle.scoreCalculator.repository.QuestionRepository;
import org.moodle.scoreCalculator.repository.QuestionaireChoiceRepository;
import org.moodle.scoreCalculator.repository.QuestionaireResponseDetailsRepository;
import org.moodle.scoreCalculator.repository.QuestionaireResponseRepository;
import org.moodle.scoreCalculator.repository.UserLogRepository;
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

	@Autowired
	UserLogRepository userLogRepository;

	@Autowired
	CourseModuleRepository courseModuleRepository;

	@Autowired
	QuestionAttemptRepository questionAttemptRepository;
	
	@Autowired
	QuestionAttemptStepsRepository questionAttemptStepsRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionCategoryRepository questionCategoryRepository;
	
	public void handleUserPreferences() {
		// Iterable<QuistionaireResponse> list = repo.findAll();
		questionaireResponseRepository.findAll().forEach(questionaire -> {
			if (questionaire.getScore1() == null) {
				List<QuestionaireResponseDetails> questionaireDetails = questionaireResponseDetailsRepository
						.findByResponseId(questionaire.getId());
				int score1 = 0, score2 = 0, score3 = 0, score4 = 0;
				for (int i = 0; i < questionaireDetails.size(); i++) {
					if (i >= 0 && i < 11) {
						QuestionaireChoice choice = questionaireChoiceRepository
								.findById(questionaireDetails.get(i).getChoiceId()).get();
						score1 += choice.getWeight();
					}

					if (i >= 11 && i < 22) {
						QuestionaireChoice choice = questionaireChoiceRepository
								.findById(questionaireDetails.get(i).getChoiceId()).get();
						score2 += choice.getWeight();
					}

					if (i >= 22 && i < 33) {
						QuestionaireChoice choice = questionaireChoiceRepository
								.findById(questionaireDetails.get(i).getChoiceId()).get();
						score3 += choice.getWeight();
					}

					if (i >= 33 && i < 44) {
						QuestionaireChoice choice = questionaireChoiceRepository
								.findById(questionaireDetails.get(i).getChoiceId()).get();
						score4 += choice.getWeight();
					}
				}

				questionaire.setScore1(score1);
				questionaire.setScore2(score2);
				questionaire.setScore3(score3);
				questionaire.setScore4(score4);

				questionaireResponseRepository.save(questionaire);
			} else {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				long startTime = cal.getTimeInMillis();
				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				long endTime = cal.getTimeInMillis();

				List<UserLog> logs = userLogRepository.findByUseridAndActionAndTargetAndObjecttableAndRealuserid(
						questionaire.getUserid(), "viewed", "course_module", "resource",null);
				int videoCount = 0, pdfCount = 0;
				for (UserLog log : logs) {
					Optional<CourseModule> courseModule = courseModuleRepository.findById(log.getContextinstanceid());
					if (courseModule.isPresent()) {
						String idNumber = courseModule.get().getIdnumber();
						if (idNumber.contains("PDF"))
							pdfCount++;
						else if (idNumber.contains("VID"))
							videoCount++;
					}
					
					log.setRealuserid(0l);
					userLogRepository.save(log);
				}

				if (videoCount > pdfCount) {
					questionaire.setScore3(questionaire.getScore3() + 1);
				} else if (videoCount < pdfCount) {
					questionaire.setScore3(questionaire.getScore3() - 1);
				}

				questionaireResponseRepository.save(questionaire);
				System.out.println(pdfCount);
				System.out.println(videoCount);
			}

		});
	}
	
	public void handleQuestionsDifficulty() {
		Map<Long,String> questionRank = new HashMap<>();
		questionAttemptStepsRepository.findByReadflag(0).forEach(questionAttemptStep -> {
			
			QuestionAttempt questionAttempt = questionAttemptRepository.findById(questionAttemptStep.getQuestionattemptid()).get();
			
			if(!questionRank.containsKey(questionAttempt.getQuestionid()))
				questionRank.put(questionAttempt.getQuestionid(), "0_0");
			
			String currQuestionRank = questionRank.get(questionAttempt.getQuestionid());
			
			if(questionAttemptStep.isCorrectanswer())
				currQuestionRank = (Integer.parseInt(currQuestionRank.split("_")[0])+1) + "_" + currQuestionRank.split("_")[1];
			else
				currQuestionRank = currQuestionRank.split("_")[0] + "_" + (Integer.parseInt(currQuestionRank.split("_")[1])+1);
			questionRank.replace(questionAttempt.getQuestionid(), currQuestionRank);
			
			questionAttemptStep.setReadflag(1);
			questionAttemptStepsRepository.save(questionAttemptStep);
				
		});
		
		
		for(Long questionId:questionRank.keySet()) {
			Question currQuestion = questionRepository.findById(questionId).get();
			QuestionCategory currQuestionCategory = questionCategoryRepository.findById(currQuestion.getCategory()).get();
			List<QuestionCategory> questionCategories = questionCategoryRepository.findByContextid(currQuestionCategory.getContextid());
			Long easyId=-1l,mediumId=-1l,hardId=-1l;
			for(QuestionCategory questionCategory:questionCategories) {
				if("Easy".equals(questionCategory.getName()))
					easyId=questionCategory.getId();
				else if("Medium".equals(questionCategory.getName()))
					mediumId=questionCategory.getId();
				else if("Hard".equals(questionCategory.getName()))
					hardId=questionCategory.getId();
			}
			String currQuestionRank = questionRank.get(questionId);
			int correctCount = Integer.parseInt(currQuestionRank.split("_")[0]);
			int wrongCount = Integer.parseInt(currQuestionRank.split("_")[1]);
			if(((wrongCount/(correctCount+wrongCount))*100) >= 70) {
				if("Easy".equals(currQuestionRank)) {
					currQuestion.setCategory(mediumId);
				} else if("Medium".equals(currQuestionRank)) {
					currQuestion.setCategory(hardId);
				}
			}
			
			if(((correctCount/(correctCount+wrongCount))*100) >= 70) {
				if("Medium".equals(currQuestionRank)) {
					currQuestion.setCategory(easyId);
				} else if("Hard".equals(currQuestionRank)) {
					currQuestion.setCategory(mediumId);
				}
			}
			
			questionRepository.save(currQuestion);
		}
		
	}
}
