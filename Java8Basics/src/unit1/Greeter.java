package unit1;

public class Greeter {

	public static void main(String[] args) {
		Greeter greeter = new Greeter();

		/* Conventional Way. */
		Greeting helloWorldGreeting = new HelloWorldGreeting();

		/*
		 * Above is essentially not needed. I can create an Anonymous Instance
		 * here and use it.
		 */
		Greeting throughAnonInstance = new Greeting() {
			public void perform() {
				System.out.println("Hello world from Anonymous Instance.");
			}
		};

		/* Through Lambda. */
		Greeting myLambdaEx = () -> System.out.println("Hello world From Lambda");

		greeter.callPerform(throughAnonInstance);
		greeter.callPerform(myLambdaEx);
	}

	private void callPerform(Greeting greeting) {
		greeting.perform();
	}

}
