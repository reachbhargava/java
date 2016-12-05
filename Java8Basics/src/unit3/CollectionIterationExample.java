package unit3;

import java.util.Arrays;
import java.util.List;

import commonPackage.Person;

public class CollectionIterationExample {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("bhargava", "narasipura", 10), new Person("siri", "rao", 10),
				new Person("bharath", "simha", 10), new Person("bali", "nagaraja", 10));

		/*
		 * The below 2 ways are called External Iterators. Imperative
		 * programming. I control the iteration, I say how the iteration
		 * happens.
		 */
		// for (int i = 0; i < people.size(); i++) {
		// System.out.println(people.get(i));
		// }
		//
		// This is referred to as For-In
		// for (Person p : people) {
		// System.out.println(p);
		// }
		/* End */

		/*
		 * This is For-Each. the forEach is implemented as the runtime wants it.
		 * I have no control of the order in which these are executed. The
		 * processor can take advantage of multi-threading and parallelism
		 */
		// people.forEach(p -> System.out.println(p)); // Using lambda
		people.forEach(System.out::println); // Using Method References.

	}

}
