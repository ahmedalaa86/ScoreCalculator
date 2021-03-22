package org.moodle.scoreCalculator.repository;

import org.moodle.scoreCalculator.model.QuestionAttempt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAttemptRepository extends CrudRepository<QuestionAttempt, Long> {
	
}
