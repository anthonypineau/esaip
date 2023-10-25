#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include "read_csv.h"

using namespace std;

const int ROWS = 7;

enum ExpenseType {
    GROCERIES,
    CLOTHES,
    SHOES,
    MISCELLANEOUS
};

typedef struct T_Expense {
    int year;
    int month;
    int day;
    enum ExpenseType type;
    double amount;
} Expense;

void printExpenseType(enum ExpenseType type) {
    switch (type) {
        case GROCERIES:
            cout << "Groceries" << "\t";
            break;
        case SHOES:
            cout << "Shoes" << "\t\t";
            break;
        case CLOTHES:
            cout << "Clothes" << "\t\t";
            break;
        default:
            cout << "Miscellaneous" << "\t\t";
    }
}

void printExpenses(Expense array[], int entries) {
    cout << "Expenses List" << endl;
    cout << "========================================" << endl;
    for (int i = 0; i < entries; i++) {
        cout << array[i].day << "." << array[i].month << "." << array[i].year << "\t";
        printExpenseType(array[i].type);
        cout << array[i].amount << endl;
    }
}

double meanAmount(Expense array[], int entries) {
    double sum = 0;
    for (int i = 0; i < entries; i++) {
        sum += array[i].amount;
    }
    return sum / entries;
}

double amountForType(Expense array[], int entries, enum ExpenseType type) {
    double sum = 0;
    for (int i = 0; i < entries; i++) {
        if (array[i].type == type)
            sum += array[i].amount;
    }
    return sum;
}

double maximumExpense(Expense array[], int entries) {
    double max = 0;
    for (int i = 0; i < entries; i++) {
        if (array[i].amount > max)
            max = array[i].amount;
    }
    return max;
}

double minimumExpense(Expense array[], int entries) {
    double min = array[0].amount;
    for (int i = 0; i < entries; i++) {
        if (array[i].amount < min)
            min = array[i].amount;
    }
    return min;
}

int main() {
    //char* data[5][100]; // adapt the number of columns
    //Expense* expenses = (Expense*)malloc(sizeof(Expense) * ROWS);
    string data[100];
    Expense expenses[ROWS];
    
    read_csv("expenses.csv", data);

    
    for (int i = 0; i < ROWS; i++) {
        string row = data[i];

        //cout << row << endl;

        string rowValue[5];

        stringstream X(row); // X is an object of stringstream that references the S string  

        string T;

        int j = 0;

        // use while loop to check the getline() function condition  
        while (getline(X, T, ',')) {
            //X represents to read the string from stringstream, T use for store the token string and,
            // ' ' whitespace represents to split the string where whitespace is found.

            //cout << T << endl; // print split string  
            rowValue[j] = T;
            j += 1;
        }
        /*cout << rowValue[0] << endl;
        cout << rowValue[1] << endl;
        cout << rowValue[2] << endl;
        cout << rowValue[3] << endl;
        cout << rowValue[4] << endl;*/
        
        expenses[i].year = stoi(rowValue[0]);
        expenses[i].month = stoi(rowValue[1]);
        expenses[i].day = stoi(rowValue[2]);
        expenses[i].amount = stof(rowValue[4]);
        if (rowValue[3] == "groceries") {
            expenses[i].type = GROCERIES;
        }
        else if (rowValue[3] == "shoes") {
            expenses[i].type = SHOES;
        }
        else if (rowValue[3] == "clothes") {
            expenses[i].type = CLOTHES;
        }
        else {
            expenses[i].type = MISCELLANEOUS;
        }
    }

    printExpenses(expenses, ROWS);
    cout << "========================================" << endl;
    cout << "Mean amount spent: " << meanAmount(expenses, ROWS) << endl;
    cout << "Amount spent on groceries: " << amountForType(expenses, ROWS, GROCERIES) << endl;
    cout << "Amount spent on shoes: " << amountForType(expenses, ROWS, SHOES) << endl;
    cout << "Amount spent on clothes: " << amountForType(expenses, ROWS, CLOTHES) << endl;
    cout << "Maximum amount spent: " << maximumExpense(expenses, ROWS) << endl;
    cout << "Minimum amount spent: " << minimumExpense(expenses, ROWS) << endl;

    return 0;
}

