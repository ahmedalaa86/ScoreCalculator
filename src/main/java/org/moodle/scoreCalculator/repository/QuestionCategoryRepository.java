package org.moodle.scoreCalculator.repository;

import java.util.List;

import org.moodle.scoreCalculator.model.CourseModule;
import org.moodle.scoreCalculator.model.Question;
import org.moodle.scoreCalculator.model.QuestionCategory;
import org.moodle.scoreCalculator.model.QuestionaireChoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionCategoryRepository extends CrudRepository<QuestionCategory, Long> {
	List<QuestionCategory> findByContextid(Long contextid);
}
