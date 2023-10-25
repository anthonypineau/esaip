#include "Fraction.h"

Fraction::Fraction() {

}

Fraction::Fraction(int numerator, int denominator) {
	this->numerator = numerator;
	this->denominator = denominator;
}

int Fraction::getNumerator() {
	return this->numerator;
}

int Fraction::getDenominator() {
	return this->denominator;
}

Fraction Fraction::add(Fraction fraction) {
	int numerator = this->numerator * fraction.denominator + fraction.numerator * this->denominator;
	int denominator = this->denominator * fraction.denominator;
	Fraction res = Fraction(numerator, denominator);
	res.simplify();
	return res;
}

void Fraction::simplify() {
	int gcd = this->gcd(this->numerator, this->denominator);
	this->numerator = this->numerator / gcd;
	this->denominator = this->denominator / gcd;
}

int Fraction::gcd(int a, int b) {
	if (b == 0)
		return a;
	return gcd(b, a % b);
}

void Fraction::print() {
	cout << numerator << "/" << denominator << endl;
}