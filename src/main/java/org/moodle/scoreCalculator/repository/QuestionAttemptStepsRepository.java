package org.moodle.scoreCalculator.repository;

import java.util.List;

import org.moodle.scoreCalculator.model.QuestionAttemptSteps;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAttemptStepsRepository extends CrudRepository<QuestionAttemptSteps, Long> {
	List<QuestionAttemptSteps> findByReadflag(Integer readflag);
}
