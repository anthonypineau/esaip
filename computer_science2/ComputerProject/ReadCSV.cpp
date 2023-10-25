#include "ReadCSV.h"

list<Expense> ReadCSV::readCSV(string filename) {
    ifstream data(filename);
    string line;
    list<Expense> parsedCsv;
    std::getline(data, line);

    while (std::getline(data, line))
    {
        stringstream lineStream(line);
        string cell;

        list<string> parsedRow;
        
        while (std::getline(lineStream, cell, ',')){
            parsedRow.push_back(cell);
        }

        string year = parsedRow.front();
        parsedRow.pop_front();

        string month = parsedRow.front();
        parsedRow.pop_front();

        string day = parsedRow.front();

        parsedRow.pop_front();

        Date date(year, month, day);
    
        string type = parsedRow.front();
        parsedRow.pop_front();

        double amount = stod(parsedRow.front());

        Expense expense(date, amount, type);

        parsedCsv.push_back(expense);
    }

    return parsedCsv;
}