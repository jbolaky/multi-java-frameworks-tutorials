package com.mytutorials.jpa.hibernate.entity.mapping.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mytutorials.jpa.hibernate.entity.mapping.api.Family;
import com.mytutorials.jpa.hibernate.entity.mapping.api.Job;
import com.mytutorials.jpa.hibernate.entity.mapping.api.Person;

@Entity
@Table(name = "PERSON")
public class DefaultPerson implements Person {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = DefaultFamily.class)
	@JoinColumn(name = "FAMILY_ID")
	private Family family;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = DefaultJob.class)
	private List<Job> jobs = new ArrayList<Job>();

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Family getFamily() {
		return family;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public boolean add(Job job) {

		return jobs.add(job);
	}

	public boolean remove(Job job) {

		return jobs.remove(job);
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
		DefaultPerson other = (DefaultPerson) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
