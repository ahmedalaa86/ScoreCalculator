package org.moodle.scoreCalculator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "mdl_questionnaire_resp_single")
public class QuestionaireResponseDetails implements java.io.Serializable {
	@Id
	Long id;
	
	@Column(name = "response_id")
	Long responseId;
	
	@Column(name = "question_id")
	Long questionId;
	
	@Column(name = "choice_id")
	Long choiceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getResponseId() {
		return responseId;
	}

	public void setResponseId(Long responseId) {
		this.responseId = responseId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(Long choiceId) {
		this.choiceId = choiceId;
	}
	
	
	
}
