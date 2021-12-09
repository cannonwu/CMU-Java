//Cannon Wu
//Andrew ID: clwu

package lab3;

public class Fraction {

	int numerator;
	int denominator;
	
	public Fraction() {			//default constructor
		numerator = 1;
		denominator = 1;
	}
	
	public Fraction(int numerator, int denominator) {		//non-default constructor
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public String toString() {
		return numerator + "/" + denominator;
	}
	
	public double toDecimal() {
		double doubleNumerator = numerator;
		double doubleDenominator = denominator;
		return doubleNumerator / doubleDenominator;
	}
	
	public Fraction add(Fraction f) {
		//already has n1, d1
		//add with n2, d2
		int totalNumerator = (this.numerator * f.denominator) + (f.numerator * this.denominator);	//criss-cross
		int totalDenominator = this.denominator * f.denominator;	//across
		
		int gcd = findGCD(totalNumerator, totalDenominator);
		totalNumerator /= gcd;
		totalDenominator /= gcd;
		
		Fraction solution = new Fraction(totalNumerator, totalDenominator);
		
		return solution;
	}
	
	public int findGCD(int n, int d) {
		if (n == 0) {
			return 1;
		} else if (d == 0){
			return n;
		} else {
			return findGCD(d, n % d);
		}
		
	}

}
