package org.moodle.scoreCalculator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "mdl_questionnaire_quest_choice")
public class QuestionaireChoice implements java.io.Serializable {
	@Id
	Long id;
	
	@Column(name = "question_id")
	Long questionId;
	
	@Column(name = "weight")
	Integer weight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
		
}
