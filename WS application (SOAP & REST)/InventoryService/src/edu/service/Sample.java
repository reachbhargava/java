package edu.service;

public class Sample {
	public static void main(String[] args) {
		int val = sol(110);
		System.out.println("---- " + val);
	}

	private static int sol(int N) {
		int ret = 0;
		for (int i = 1; i <= N; i++) {
			String stringRep = String.valueOf(i);
			for (char ch : stringRep.toCharArray()) {
				if (ch == '1') {
					ret = ret + 1;
				}
			}
		}
		return ret;
	}
}
