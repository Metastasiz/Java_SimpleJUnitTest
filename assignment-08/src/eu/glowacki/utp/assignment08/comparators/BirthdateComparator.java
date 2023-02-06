package eu.glowacki.utp.assignment08.comparators;

import java.util.Comparator;

import eu.glowacki.utp.assignment08.Person;

public final class BirthdateComparator implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {
		return person1.getDOB().compareTo(person2.getDOB());
		// TODO Auto-generated method stub
	}
}