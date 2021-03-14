package org.moodle.scoreCalculator.repository;

import java.util.List;

import org.moodle.scoreCalculator.model.CourseModule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseModuleRepository extends CrudRepository<CourseModule, Long> {
	
}
