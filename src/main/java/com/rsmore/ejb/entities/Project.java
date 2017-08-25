package com.rsmore.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="project")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Project() {
		super();
	}
	
	@Id
	private int pnumber;
	
	private String pname;
	
	private String plocation;
	
	@Column(name = "dept_no")
	private int deptNo;

	public int getPnumber() {
		return pnumber;
	}

	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPlocation() {
		return plocation;
	}

	public void setPlocation(String plocation) {
		this.plocation = plocation;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	
	@Override
	public String toString() {
		return "Project [pnumber=" + pnumber + ", pname=" + pname
                + ", plocation=" + plocation + ", deptNo=" + deptNo + "]";
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
