package org.moodle.scoreCalculator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_logstore_standard_log")
public class UserLog implements java.io.Serializable{
	@Id
	Long id;
	String action;
	String target;
	String objecttable;
	Long contextinstanceid;
	Long userid;
	Long realuserid;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getObjecttable() {
		return objecttable;
	}
	public void setObjecttable(String objecttable) {
		this.objecttable = objecttable;
	}
	public Long getContextinstanceid() {
		return contextinstanceid;
	}
	public void setContextinstanceid(Long contextinstanceid) {
		this.contextinstanceid = contextinstanceid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getRealuserid() {
		return realuserid;
	}
	public void setRealuserid(Long realuserid) {
		this.realuserid = realuserid;
	}
	
	
}
