package org.moodle.scoreCalculator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "mdl_questionnaire_response")
public class QuestionaireResponse implements java.io.Serializable {
	@Id
	Long id;
	
	Long submitted;
	String complete;
	Long grade;
	Long questionnaireid;
	
	Long userid;
	
	Integer score1;
	
	Integer score2;
	
	Integer score3;
	
	Integer score4;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Long submitted) {
		this.submitted = submitted;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public Long getGrade() {
		return grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	public Long getQuestionnaireid() {
		return questionnaireid;
	}

	public void setQuestionnaireid(Long questionnaireid) {
		this.questionnaireid = questionnaireid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Integer getScore1() {
		return score1;
	}

	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	public Integer getScore2() {
		return score2;
	}

	public void setScore2(Integer score2) {
		this.score2 = score2;
	}

	public Integer getScore3() {
		return score3;
	}

	public void setScore3(Integer score3) {
		this.score3 = score3;
	}

	public Integer getScore4() {
		return score4;
	}

	public void setScore4(Integer score4) {
		this.score4 = score4;
	}
	
	
}
