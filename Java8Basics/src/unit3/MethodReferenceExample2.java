package unit3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import commonPackage.Person;

public class MethodReferenceExample2 {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("bhargava", "narasipura", 10), new Person("siri", "rao", 10),
				new Person("bharath", "simha", 10), new Person("bali", "nagaraja", 10));

		performConditionally(people, p -> true, System.out::println);

	}

	private static void performConditionally(List<Person> people, Predicate<Person> predicate,
			Consumer<Person> consumer) {
		for (Person p : people) {
			if (predicate.test(p))
				consumer.accept(p);
		}
	}

}
