package org.moodle.scoreCalculator.repository;

import org.moodle.scoreCalculator.model.QuestionaireResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionaireResponseRepository extends CrudRepository<QuestionaireResponse, Long> {

}
