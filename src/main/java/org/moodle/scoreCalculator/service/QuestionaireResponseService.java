package org.moodle.scoreCalculator.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.moodle.scoreCalculator.model.CourseModule;
import org.moodle.scoreCalculator.model.QuestionaireChoice;
import org.moodle.scoreCalculator.model.QuestionaireResponseDetails;
import org.moodle.scoreCalculator.model.UserLog;
import org.moodle.scoreCalculator.repository.CourseModuleRepository;
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

	public void getQuistionaire() {
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
}
