#pragma once
#include "Fraction.h"
class FractionContainer
{
	public:
		FractionContainer();
		void addFraction(Fraction* fraction);
		void printFractions();
	private :
		Fraction* fractions[100];
		int count;
};

