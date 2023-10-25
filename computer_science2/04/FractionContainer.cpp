#include "FractionContainer.h"
FractionContainer::FractionContainer() {
	count = 0;
}

void FractionContainer::addFraction(Fraction* fraction) {
	fractions[count++] = fraction;
}

void FractionContainer::printFractions() {
	for (int i = 0; i < count; i++) {
		fractions[i]->print();
	}
}