package org.moodle.scoreCalculator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_question_categories")
public class QuestionCategory implements java.io.Serializable{
	@Id
	Long id;
	String name;
	Long contextid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getContextid() {
		return contextid;
	}
	public void setContextid(Long contextid) {
		this.contextid = contextid;
	}
	
	
}