#include "Menu.h"
Menu::Menu() {
	this->expenseContainer = new ExpenseContainer();
	this->running = false;
}

void Menu::initialiseData() {
	expenseContainer->setExpenses(ReadCSV::readCSV("data.csv"));
}

void Menu::displayMenu() {
	cout << "Menu" << endl;
	cout << "1 - Display data" << endl;
	cout << "2 - Display statistics" << endl;
	cout << "3 - New expense" << endl;
	cout << "Q - Quit" << endl;

	char choice;

	cout << endl;

	cout << "Choose an option : ";
	cin >> choice;

	cout << endl;

	switch (choice) {
		case '1':
			this->displayData();
			break;
		case '2':
			this->displayStatistics();
			break;
		case '3':
			this->addExpense();
			break;
		case 'Q':
		case 'q':
			cout << "Quit" << endl;
			running = false;
			break;
		default:
			cout << "This option doesn't exist" << endl;
			break;
	}

	cout << endl;
	cout << endl;
	cout << endl;
}


void Menu::start() {
	running = true;
}

bool Menu::isRunning() {
	return this->running;
}


void Menu::displayData() {
	cout << "List of expenses" << endl;
	cout << endl;
	this->expenseContainer->printExpenses();
}

void Menu::displayStatistics() {
	cout << "Statistics" << endl;
	cout << endl;

	cout << "Mean amount" << endl;
	cout << this->expenseContainer->meanAmount() << endl;
	cout << endl;

	cout << "Mean amount for GROCERIES" << endl;
	cout << this->expenseContainer->amountForType(Expense::GROCERIES) << endl;
	cout << endl;

	cout << "Minimum expense" << endl;
	this->expenseContainer->miniumExpense().print();
	cout << endl;

	cout << "Maximum expense" << endl;
	this->expenseContainer->maximumExpense().print();
	cout << endl;
}

void Menu::addExpense() {
	string year;
	string month;
	string day;
	double amount;
	string type;

	cout << "YEAR : ";
	cin >> year;

	cout << "MONTH : ";
	cin >> month;

	cout << "DAY : ";
	cin >> day;

	cout << "AMOUNT : ";
	cin >> amount;

	cout << "TYPE : ";
	cin >> type;

	this->expenseContainer->addExpense(Expense(Date(year,month,day), amount, type));

	ofstream o1("data.csv", ios::app);
	o1 << year << "," << month << "," << day << "," << type << "," << amount << endl;
	o1.close();

	cout << "New data" << endl;

	this->displayData();
}


