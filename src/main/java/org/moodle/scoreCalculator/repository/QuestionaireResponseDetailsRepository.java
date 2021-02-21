package org.moodle.scoreCalculator.repository;

import java.util.List;

import org.moodle.scoreCalculator.model.QuestionaireResponseDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionaireResponseDetailsRepository extends CrudRepository<QuestionaireResponseDetails, Long> {
	List<QuestionaireResponseDetails> findByResponseId(Long responseId);
}
