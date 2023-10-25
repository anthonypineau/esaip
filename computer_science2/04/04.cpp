#include <iostream>
#include "Fraction.h"
#include "FractionContainer.h"

using namespace std;

int main()
{
    Fraction* f1 = new Fraction(21, 3);
    Fraction* f2 = new Fraction(1, 2);
    Fraction f3Temp = f1->add(*f2);
    Fraction* f3 = new Fraction(f3Temp.getNumerator(), f3Temp.getDenominator());

    FractionContainer container;

    container.addFraction(f1);
    container.addFraction(f2);
    container.addFraction(f3);

    container.printFractions();

    //std::cout << f3.getNumerator() << "/" << f3.getDenominator() << std::endl;

    int a = 5;

    int& aRef = a;

    int* aPtr = &a;

    cout << "a : " << a << endl;
    cout << "aRef : " << aRef << endl;
    cout << "aPtr : " << *aPtr << endl;

    cout << "address a : " << &a << endl;
    cout << "address aRef : " << &aRef << endl;
    cout << "address  aPtr : " << aPtr << endl;
}
