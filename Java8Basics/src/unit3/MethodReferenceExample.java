package unit3;

public class MethodReferenceExample {

	private static void someMethod() {
		System.out.println("Say Hello " + Thread.currentThread().getId());
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getId());
		someMethod();
		/* Earlier way of doing this. Just to recall once. */
		// Thread thread = new Thread(new Runnable() {
		// @Override
		// public void run() {
		// someMethod();
		// }
		// });
		// thread.start();

		/*
		 * Then comes the lambda where we eliminate the unnecessary class
		 * creation and override statements since Runnable is a Functional
		 * Interface. The run method takes no arguments and we simply override
		 * the body. Since the body is just 1 line, we take out all the curly
		 * braces around the body and hence only provide what is to be in the
		 * body.
		 */
		// Thread thread2 = new Thread(() -> someMethod());
		// thread2.start();

		Thread thread3 = new Thread(MethodReferenceExample::someMethod);
		thread3.start();

	}

}
