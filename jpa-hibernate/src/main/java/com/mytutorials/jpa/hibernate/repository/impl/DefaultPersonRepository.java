package com.mytutorials.jpa.hibernate.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mytutorials.jpa.hibernate.entity.mapping.api.Person;
import com.mytutorials.jpa.hibernate.entity.mapping.impl.DefaultPerson;
import com.mytutorials.jpa.hibernate.repository.api.PersonRepository;

public class DefaultPersonRepository implements PersonRepository {

	private static final String FIRST_NAME = "firstname";

	private static final String PERSISTENCE_UNIT_NAME = "person_persistence_unit_name";

	private static final String FIND_PERSONS_BY_FIRSTNAME_JPQL = "SELECT p FROM DefaultPerson p WHERE p.firstName = :"
			+ FIRST_NAME;

	private EntityManagerFactory entityManagerFactory;

	public DefaultPersonRepository() {
		super();
		entityManagerFactory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	public Person persist(Person person) {

		EntityManager entityManager = getEntityManagerAndBeginTransaction();

		entityManager.persist(person);

		commitTransactionAndCloseEntityManager(entityManager);

		return person;

	}

	public Person find(Long id) {

		EntityManager entityManager = getEntityManager();

		Person person = entityManager.find(DefaultPerson.class, id);

		closeEntityManager(entityManager);

		return person;

	}

	@SuppressWarnings("unchecked")
	public List<Person> find(String firstName) {

		EntityManager entityManager = getEntityManagerAndBeginTransaction();

		Query query = entityManager.createQuery(FIND_PERSONS_BY_FIRSTNAME_JPQL);
		query.setParameter(FIRST_NAME, firstName);

		return (List<Person>) query.getResultList();
	}

	public Person merge(Long id, String newFirstName) {

		EntityManager entityManager = getEntityManagerAndBeginTransaction();

		DefaultPerson person = entityManager.find(DefaultPerson.class, id);

		person.setFirstName(newFirstName);

		person = entityManager.merge(person);

		commitTransactionAndCloseEntityManager(entityManager);

		return person;

	}

	private EntityManager getEntityManager() {

		return entityManagerFactory.createEntityManager();
	}

	private EntityManager getEntityManagerAndBeginTransaction() {

		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		return entityManager;
	}

	private void closeEntityManager(EntityManager entityManager) {

		entityManager.close();
	}

	private void commitTransactionAndCloseEntityManager(
			EntityManager entityManager) {

		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
