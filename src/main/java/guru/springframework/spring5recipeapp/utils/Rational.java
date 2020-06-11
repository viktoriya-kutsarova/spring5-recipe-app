package guru.springframework.spring5recipeapp.utils;

/**
 * Created by Viktoriya on 07-Jun-20
 */
public class Rational {

	private int num, denom;

	public Rational(double d) {
		String s = String.valueOf(d);
		int digitsDec = s.length() - 1 - s.indexOf('.');
		int potentialDenom = 1;
		for (int i = 0; i < digitsDec; i++) {
			d *= 10;
			potentialDenom *= 10;
		}

		int potentialNum = (int) Math.round(d);
		int g = gcd(potentialNum, potentialDenom);
		this.num = num / g;
		this.denom = denom / g;
	}

	public Rational(int num, int denom) {
		this.num = num;
		this.denom = denom;
	}

	public String toString() {
		return String.valueOf(num) + "/" + denom;
	}

	public int gcd(int num, int denom) {
		int smaller = Math.min(num, denom);
		int gcd = 1;
		for (int i = 2; i <= smaller; i++) {
			if (num % i == 0 && denom % i == 0) {
				gcd = i;
			}
		}
		return gcd;
	}
}
