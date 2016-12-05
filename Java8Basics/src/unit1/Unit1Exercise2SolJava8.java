package unit1;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import commonPackage.Person;

public class Unit1Exercise2SolJava8 {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("bhargava", "narasipura", 10), new Person("siri", "rao", 10),
				new Person("bharath", "simha", 10), new Person("bali", "nagaraja", 10));

		// Step1. Sort list by last name.
		/*
		 * There is a lot of inference that is happening here. The o1 and o2 are
		 * of types Person is also removed becuase the compiler is intelligent
		 * enough to identify that it is comparing Persons (through the first
		 * param people --> List<Person>.
		 */
		Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));

		// Step2. Create a method to print all elements in the list.
		System.out.println("printing all names");
		Condition myConditionToPrintAllNames = p -> true;
		printConditionally(people, myConditionToPrintAllNames);

		// Step3. Print name with last name in n.
		System.out.println("printing all names with last name starting with n");
		Condition myConditionToPrintNamesWithLastNameStartingWithN = p -> p.getLastName().startsWith("n");
		printConditionally(people, myConditionToPrintNamesWithLastNameStartingWithN);
	}

	private static void printConditionally(List<Person> people, Condition condition) {
		for (Person p : people) {
			if (condition.testCondition(p))
				System.out.println(p);
		}
	}

}
