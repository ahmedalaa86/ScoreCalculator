package org.moodle.scoreCalculator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_question_attempts")
public class QuestionAttempt implements java.io.Serializable{
	@Id
	Long id;
	Long questionusageid;
	Long questionid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQuestionusageid() {
		return questionusageid;
	}
	public void setQuestionusageid(Long questionusageid) {
		this.questionusageid = questionusageid;
	}
	public Long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(Long questionid) {
		this.questionid = questionid;
	}
}