#pragma once

#include <iostream>

using namespace std;

class Fraction {
	public :
		int getNumerator();
		int getDenominator();
		Fraction add(Fraction fraction);
		Fraction(int numerator, int denominator);
		Fraction();
		void print();
	private :
		void simplify();
		int gcd(int a, int b);
		int numerator;
		int denominator;
};

