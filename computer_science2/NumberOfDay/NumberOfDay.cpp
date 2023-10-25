#include <iostream>

using namespace std;

int main()
{
    int d; int m; int y;
    cout << "Enter d, m and y" << endl;
    cout << "Enter d : " << endl;
    cin >> d;
    cout << "Enter m : " << endl;
    cin >> m;
    cout << "Enter y : " << endl;
    cin >> y;

    if (m <= 0 || m >= 13 || y < 1583) {
        cout << "Error";
    } else {
        int dim;
        if (m == 2) {
            if (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)) {
                dim = 29;
            } else {
                dim = 28;
            }
        }else{
            if (m == 4 || m == 6 || m == 9 || m == 11) {
                dim = 30;
            }
            else {
                dim = 31;
            }
        }
        if (d > 0 && d <= dim) {
            int dnr = 0;
            int i = 1;
            while (i < m) {
                if (i == 2) {
                    if (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)) {
                        dnr += 29;
                    }
                    else {
                        dnr += 28;
                    }
                }
                else {
                    if (i == 4 || i == 6 || i == 9 || i == 11) {
                        dnr += 30;
                    }
                    else {
                        dnr += 31;
                    }
                }
                i += 1;
            }
            dnr += d;
            cout << "Number of the day : " << dnr;
        } else {
            cout << "Error";
        }
    }

    //cout << "D : " << d << ", M : " << m << ", Y : " << y;
}