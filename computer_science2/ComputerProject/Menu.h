#pragma once

#include "ExpenseContainer.h"
#include "ReadCSV.h"
#include<iostream>

using namespace std;

class Menu {
	public:
		Menu();
		
		void initialiseData();
		void displayMenu();

		void start();
		bool isRunning();
	private:
		bool running;
		ExpenseContainer* expenseContainer;

		void displayData();
		void displayStatistics();
		void addExpense();
};

