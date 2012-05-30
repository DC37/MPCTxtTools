package fileSplitter;

import static java.lang.Math.*;

/**
 * 
 * @author RehdBlob
 * @version 1.06
 * @since 1.03
 * @since 2011.0808
 */
public class Fraction {

	private Integer denominator;
	private Integer numerator;

	/**
	 * Default constructor, when the Fraction is created
	 * without initial values.
	 * @deprecated
	 */
	public Fraction() {
		numerator = 0;
		denominator = 1;
	}
	
	/**
	 * Initializes the Fraction, giving it a <b>numerator</b>
	 * and <b>denominator</b> value.
	 * @param n The numerator of the Fraction to be created.
	 * @param d The denominator of the Fraction to be created.
	 */
	public Fraction(int n, int d) {
		setNumerator(n);
		setDenominator(d);
		simplify();
	}

	/**
	 * Reduces the Fraction to lowest terms.
	 */
	private void simplify() {
		int divisor = 1;
		for(int i = 1; i<=min(numerator,denominator); i++)
			if(numerator % i == 0 & denominator % i == 0)
				divisor = i;
		numerator /= divisor;
		denominator /= divisor;
	}

	/**
	 * @return The <b>denominator</b> of the Fraction.
	 */
	public int getDenominator() 
	{
		return denominator;
	}

	/**
	 * Sets the <b>denominator</b> of the Fraction.
	 * @param d The number that the denominator is to be set to.
	 */
	public void setDenominator(int d) {
		denominator = d;
	}

	/**
	 * @return The <b>numerator</b> of the Fraction.
	 */
	public int getNumerator() {
		return numerator;
	}

	/**
	 * Sets the <b>numerator</b> of the Fraction.
	 * @param n The number that the numerator is to be set to.
	 */
	public void setNumerator(int n) 
	{
		numerator = n;
	}

	/**
	 * @return A traditional a/b notation of the Fraction.
	 */
	@Override
	public String toString()
	{
		return ""+numerator+"/"+denominator;
	}

}
