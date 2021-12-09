//Cannon Wu
//Andrew ID: clwu

package lab3;

public class MixedFraction extends Fraction {

	int naturalNumber;
	
	public MixedFraction(int naturalNumber, int numerator, int denominator) {
		super(numerator, denominator);
		this.naturalNumber = naturalNumber;
	}
	
	public String toString() {
		return naturalNumber + " " + numerator + "/" + denominator;	
	}
	
	public double toDecimal() {
		double doubleNaturalNum = naturalNumber;
		double doubleNumerator = numerator;
		double doubleDenominator = denominator;
		
		return (((doubleNaturalNum * doubleDenominator) + doubleNumerator) / doubleDenominator);	//calculates improper fraction first
	}
	
	public Fraction toFraction() {
		Fraction bigFraction = new Fraction((naturalNumber * denominator) + numerator, denominator);	//convert to improper fraction
		return bigFraction;
	}
	
	public Fraction add(MixedFraction mf) {
		//add behaves like this.add(mf) where 'this' is being added to mf
		//convert this and mf to fraction
		//then feed into this.add(mf)
		
		
		//Alt solution:
		Fraction mf1 = this.toFraction();
		Fraction mf2 = mf.toFraction();
		Fraction solution = mf1.add(mf2);
		
		
		//Fraction solution = this.toFraction().add(mf.toFraction());
		return solution;
	}

}
