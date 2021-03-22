package org.moodle.scoreCalculator.repository;

import java.util.List;

import org.moodle.scoreCalculator.model.CourseModule;
import org.moodle.scoreCalculator.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
	
}
