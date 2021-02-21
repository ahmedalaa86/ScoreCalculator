package org.moodle.scoreCalculator.repository;

import java.util.List;

import org.moodle.scoreCalculator.model.QuestionaireChoice;
import org.moodle.scoreCalculator.model.QuestionaireResponseDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionaireChoiceRepository extends CrudRepository<QuestionaireChoice, Long> {
	List<QuestionaireChoice> findByQuestionId(Long questionId);
}
