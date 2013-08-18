package com.mytutorials.jpa.hibernate.entity.mapping.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mytutorials.jpa.hibernate.entity.mapping.api.Job;

@Entity
@Table(name = "JOB")
public class DefaultJob implements Job {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "SALARY")
	private Double salary;

	@Column(name = "DESCRIPTION")
	private String description;

	public Long getId() {
		return id;
	}

	public Double getSalary() {
		return salary;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultJob other = (DefaultJob) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
