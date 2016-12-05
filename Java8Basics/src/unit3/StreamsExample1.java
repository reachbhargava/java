package unit3;

import java.util.Arrays;
import java.util.List;

import commonPackage.Person;

public class StreamsExample1 {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("bhargava", "narasipura", 10), new Person("siri", "rao", 10),
				new Person("bharath", "simha", 10), new Person("bali", "nagaraja", 10));

		people.stream().filter(p -> p.getLastName().startsWith("n")).forEach(p -> System.out.println(p.getLastName()));

		people.forEach(p -> System.out.println(p));

		/*
		 * people.parallelStream() can be executed which potentially gives way
		 * for multi-processors to take centerstage
		 */
	}

}
