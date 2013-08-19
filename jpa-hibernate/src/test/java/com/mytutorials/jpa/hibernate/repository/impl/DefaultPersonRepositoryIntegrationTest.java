package com.mytutorials.jpa.hibernate.repository.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.mytutorials.jpa.hibernate.entity.mapping.api.Family;
import com.mytutorials.jpa.hibernate.entity.mapping.api.Job;
import com.mytutorials.jpa.hibernate.entity.mapping.api.Person;
import com.mytutorials.jpa.hibernate.entity.mapping.impl.DefaultFamily;
import com.mytutorials.jpa.hibernate.entity.mapping.impl.DefaultJob;
import com.mytutorials.jpa.hibernate.entity.mapping.impl.DefaultPerson;
import com.mytutorials.jpa.hibernate.repository.api.PersonRepository;

@Ignore
public class DefaultPersonRepositoryIntegrationTest {

	private PersonRepository personRepository = new DefaultPersonRepository();

	@Test
	public void testPersistAndFind() {

		String firstName = "Jack";
		String lastName = "Sparrow";

		Person jackSparrow = createPerson(firstName, lastName);

		Job job = createJob("Movie Industry", 9999999D);

		jackSparrow.add(job);

		Family family = createFamily("Pirate");
		family.add(jackSparrow);

		jackSparrow = personRepository.persist(jackSparrow);
		assertNotNull(jackSparrow);
		assertNotNull(jackSparrow.getId());

		Person persistedJackSparrow = personRepository
				.find(jackSparrow.getId());

		// test person entity has been successfully saved
		assertNotNull(persistedJackSparrow);
		assertThat(persistedJackSparrow.getFirstName(), is(firstName));
		assertThat(persistedJackSparrow.getLastName(), is(lastName));

		// test job entity has been successfully saved and linked to jackSparrow
		assertNotNull(persistedJackSparrow.getJobs());
		assertThat(persistedJackSparrow.getJobs().size(), is(1));
		assertThat(persistedJackSparrow.getJobs().get(0).getSalary(),
				is(9999999D));

		// test family has been successfully saved and linked to jackSparrow
		assertNotNull(persistedJackSparrow.getFamily());
		assertThat(persistedJackSparrow.getFamily().getDescription(),
				is("Pirate"));
	}

	@Test
	public void testMerge() {

		String firstName = "Jack";
		String lastName = "Sparrow";
		String newFirstName = "Captain Jack";

		Person jackSparrow = createPerson(firstName, lastName);

		Job job = createJob("Movie Industry", 9999999D);

		jackSparrow.add(job);

		Family family = createFamily("Pirate");
		family.add(jackSparrow);

		jackSparrow = personRepository.persist(jackSparrow);
		assertNotNull(jackSparrow);
		assertNotNull(jackSparrow.getId());

		Person persistedJackSparrow = personRepository
				.find(jackSparrow.getId());

		// test person entity has been successfully saved
		assertNotNull(persistedJackSparrow);
		assertThat(persistedJackSparrow.getFirstName(), is(firstName));
		assertThat(persistedJackSparrow.getLastName(), is(lastName));

		Person mergedJackSparrow = personRepository.merge(
				persistedJackSparrow.getId(), newFirstName);

		assertNotNull(mergedJackSparrow);
		assertNotNull(mergedJackSparrow.getId());
		assertThat(mergedJackSparrow.getId(), is(persistedJackSparrow.getId()));

		Person newJackSparrow = personRepository.find(jackSparrow.getId());
		assertNotNull(newJackSparrow);
		assertThat(newJackSparrow.getFirstName(), is(newFirstName));
	}

	@Test
	public void testFindByFirstName() {

		Person johnnyEnglish = createPerson("Jhonny", "English");
		Person johnnyLover = createPerson("Jhonny", "Lover");

		johnnyEnglish = personRepository.persist(johnnyEnglish);
		assertNotNull(johnnyEnglish);
		assertNotNull(johnnyEnglish.getId());

		johnnyLover = personRepository.persist(johnnyLover);
		assertNotNull(johnnyLover);
		assertNotNull(johnnyLover.getId());

		List<Person> persons = personRepository.find("Jhonny");
		assertNotNull(persons);
		assertThat(persons.size(), is(2));
		assertThat(persons.get(0).getLastName(), is("English"));
		assertThat(persons.get(1).getLastName(), is("Lover"));
	}

	private Person createPerson(String firstName, String lastName) {

		DefaultPerson defaultPerson = new DefaultPerson();
		defaultPerson.setFirstName(firstName);
		defaultPerson.setLastName(lastName);

		return defaultPerson;
	}

	private Family createFamily(String description) {

		DefaultFamily defaultFamily = new DefaultFamily();
		defaultFamily.setDescription(description);

		return defaultFamily;
	}

	private Job createJob(String description, Double salary) {

		DefaultJob defaultJob = new DefaultJob();
		defaultJob.setDescription(description);
		defaultJob.setSalary(salary);

		return defaultJob;
	}
}
