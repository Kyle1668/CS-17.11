package edu.srjc.A6.calculator;


public class Fraction
{
    private int numerator = 0;
    private int denominator = 1;

    public Fraction(int numerator, int denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    void add(Fraction otherFraction)
    {
        if (this.denominator == otherFraction.denominator)
        {
            this.numerator += otherFraction.numerator;
        }
        else
        {
            int commonMultiple = this.denominator * otherFraction.denominator;
            this.numerator += (this.numerator * commonMultiple) + (otherFraction.numerator * commonMultiple);
        }
    }

    void subtract(Fraction otherFraction)
    {
        if (this.denominator == otherFraction.denominator)
        {
            this.numerator -= otherFraction.numerator;
        }
        else
        {
            int commonMultiple = this.denominator * otherFraction.denominator;
            this.numerator -= (this.numerator * commonMultiple) + (otherFraction.numerator * commonMultiple);
        }
    }

    void multiply(Fraction otherFraction)
    {
        this.numerator *= otherFraction.numerator;
    }

    void divide(Fraction otherFraction)
    {
        int newNumerator = this.numerator * otherFraction.denominator;
        int newDenominator = this.denominator * otherFraction.numerator;
    }

    private String value()
    {
       return numerator + "/" + denominator;
    }

}
