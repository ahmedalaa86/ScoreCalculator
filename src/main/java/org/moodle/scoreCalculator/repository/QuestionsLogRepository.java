package org.moodle.scoreCalculator.repository;

import org.moodle.scoreCalculator.model.QuestionsLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsLogRepository extends CrudRepository<QuestionsLog, Long> {
	
}
