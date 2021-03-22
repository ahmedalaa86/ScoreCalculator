package org.moodle.scoreCalculator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_question_attempt_steps")
public class QuestionAttemptSteps implements java.io.Serializable{
	@Id
	Long id;
	Long questionattemptid;
	String state;
	Integer readflag;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQuestionattemptid() {
		return questionattemptid;
	}
	public void setQuestionattemptid(Long questionattemptid) {
		this.questionattemptid = questionattemptid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getReadflag() {
		return readflag;
	}
	public void setReadflag(Integer readflag) {
		this.readflag = readflag;
	}
	public boolean isCorrectanswer() {
		if("gradedright".equals(state))
			return true;
		return false;
	}
	
}