package org.moodle.scoreCalculator.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_questions_log")
public class QuestionsLog implements java.io.Serializable{
	@Id
	Long id;
	Long questionId;
	Integer noOfWrong;
	Integer noOfCorrect;
	Long oldCategory;
	Long newCategory;
	Integer threshold;
	Date actionDate;
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
	public Integer getNoOfWrong() {
		return noOfWrong;
	}
	public void setNoOfWrong(Integer noOfWrong) {
		this.noOfWrong = noOfWrong;
	}
	public Integer getNoOfCorrect() {
		return noOfCorrect;
	}
	public void setNoOfCorrect(Integer noOfCorrect) {
		this.noOfCorrect = noOfCorrect;
	}
	public Long getOldCategory() {
		return oldCategory;
	}
	public void setOldCategory(Long oldCategory) {
		this.oldCategory = oldCategory;
	}
	public Long getNewCategory() {
		return newCategory;
	}
	public void setNewCategory(Long newCategory) {
		this.newCategory = newCategory;
	}
	public Integer getThreshold() {
		return threshold;
	}
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
}