// Template.cpp : Ce fichier contient la fonction 'main'. L'exécution du programme commence et se termine à cet endroit.
//

#include <iostream>

using namespace std;

template<class T>
class Stack {
    private :
        T* st;
        int p;
    public:
        Stack(int m = 100) { st = new T[m]; p = 0; }
        ~Stack() { delete[] st; }
        void push(T v) { st[p++] = v; }
        T pop() { return st[--p]; }
        bool empty() { return p==0; }
        int size() { return p; }
        T top() { return st[p - 1]; }
};

template <typename T>
void sum(T a, T b) {
    std::cout << a + b << std::endl;
}

int main()
{
    sum(2, 4);
    sum(2.5, 4.1);

    Stack<int> s(10);

    for (int i = 1; i < 11; i++) {
        s.push(i);
    }

    cout << "int stack" << endl;

    cout << "stack size " << s.size() << endl;

    cout << "TOP element : " << s.top() << endl;

    int size = s.size();

    for (int i = 0; i < size; i++) {
        cout << "popping elements : " << s.pop() << endl;
    }

    cout << "stack is empty : " << s.empty() << endl;

    Stack<double> t(5);

    for (int i = 1; i < 6; i++) {
        t.push(i + 0.1);
    }

    cout << "-------------" << endl;

    cout << "double stack" << endl;

    cout << "stack size " << t.size() << endl;

    cout << "TOP element : " << t.top() << endl;

    int sizeT = t.size();

    for (int i = 0; i < sizeT; i++) {
        cout << "popping elements : " << t.pop() << endl;
    }

    cout << "stack is empty : " << t.empty() << endl;

    Stack<char> c(5);

    for (int i = 1; i < 6; i++) {
        c.push(i*'a');
    }

    cout << "-------------" << endl;

    cout << "char stack" << endl;

    cout << "stack size " << c.size() << endl;

    cout << "TOP element : " << c.top() << endl;

    int sizeC = c.size();

    for (int i = 0; i < sizeC; i++) {
        cout << "popping elements : " << c.pop() << endl;
    }

    cout << "stack is empty : " << c.empty() << endl;
}