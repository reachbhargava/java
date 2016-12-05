package unit2;

import java.util.function.BiConsumer;

public class ExceptionExplained {

	public static void main(String[] args) {
		int[] someNumbers = { 1, 2, 3, 4 };
		int key = 0;

		/*
		 * Rather than ruining the single line lambda here. Wrap it and handle
		 * exception there. Also, the exception could be handled in the actual
		 * place where the consumer.accept happens originally which is the
		 * performOperation method, but the perform
		 */
		performOperation(someNumbers, key, wrapperLambda((i, k) -> System.out.println(i / k)));
	}

	private static void performOperation(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer) {
		for (int i : someNumbers) {
			consumer.accept(i, key);
		}
	}

	private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {
		return (i, k) -> {
			try {
				consumer.accept(i, k);
			} catch (ArithmeticException e) {
				System.out.println("Exception caught");
			}
		};
	}

}
