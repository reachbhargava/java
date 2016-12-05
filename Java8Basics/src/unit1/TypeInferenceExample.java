package unit1;

public class TypeInferenceExample {

	public static void main(String[] args) {
		StringLengthInterface myImpl = s -> s.length();
		System.out.println(myImpl.returnLength("Bhargava"));
	}

	interface StringLengthInterface {
		int returnLength(String s);
	}
}
