#pragma once
class Point
{
	public :
		Point(double x, double y);
		void swapXYRef(int& x, int& y);
		void swapXYPtr(int* x, int* y);
	private :
		double x, y;
};

