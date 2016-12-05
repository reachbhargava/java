package unit1;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import commonPackage.Person;


public class Unit1Exercise2SolJava7 {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("bhargava", "narasipura", 10), new Person("siri", "rao", 10),
				new Person("bharath", "simha", 10), new Person("bali", "nagaraja", 10));

		// Step1. Sort list by last name.
		Collections.sort(people, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getLastName().compareTo(o2.getLastName());
			}

		});

		// Step2. Create a method to print all elements in the list.
		printConditionally(people, new Condition() {

			@Override
			public boolean testCondition(Person p) {
				return true;
			}

		});

		// Step3. Print name with last name in n.
		printConditionally(people, new Condition() {
			@Override
			public boolean testCondition(Person p) {
				return p.getLastName().startsWith("n");
			}
		});
	}

	private static void printConditionally(List<Person> people, Condition condition) {
		for (Person p : people) {
			if (condition.testCondition(p))
				System.out.println(p);
		}
	}

	private static void filterWithLastNameBeginWithN(List<Person> people) {
		for (Person p : people) {
			if (p.getLastName().startsWith("n")) {
				System.out.println(p);
			}
		}
	}

	private static void printAll(List<Person> people) {
		for (Person p : people) {
			System.out.println(p);
		}
	}
}

interface Condition {
	boolean testCondition(Person p);
}
