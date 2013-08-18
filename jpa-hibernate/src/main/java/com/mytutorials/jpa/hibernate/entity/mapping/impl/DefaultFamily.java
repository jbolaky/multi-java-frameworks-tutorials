package com.mytutorials.jpa.hibernate.entity.mapping.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mytutorials.jpa.hibernate.entity.mapping.api.Family;
import com.mytutorials.jpa.hibernate.entity.mapping.api.Person;

@Entity
@Table(name = "FAMILY")
public class DefaultFamily implements Family {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "family", targetEntity = DefaultPerson.class)
	private Set<Person> members = new HashSet<Person>();

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Set<Person> getMembers() {
		return members;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean add(Person member) {

		if (member != null) {

			((DefaultPerson) member).setFamily(this);
		}

		return members.add(member);
	}

	public boolean remove(Person member) {

		return members.remove(member);
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
		DefaultFamily other = (DefaultFamily) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
