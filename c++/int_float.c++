#include <iostream>
// #include <climits>

using namespace std; 

int main() {
    /* 정수형: short < int < long < long long */
    short n_short_max = SHRT_MAX;
    int n_int_max = INT_MAX;
    long n_long_max = LONG_MAX;
    long long n_double_long_max = LLONG_MAX;
    
    short n_short_min = SHRT_MIN;
    int n_int_min = INT_MIN;
    long n_long_min = LONG_MIN;
    long long n_double_long_min = LLONG_MIN;

    cout << "##### short #####" << endl;
    cout << "short는" << sizeof n_short_max << "바이트이다." << endl;
    cout << "short의 최댓값은" << n_short_max << "이다." << endl;
    cout << "short의 최솟값은" << n_short_min << "이다." << endl;
    
    cout << "##### int #####" << endl;
    cout << "int는" << sizeof n_int_max << "바이트이다." << endl;
    cout << "int 최댓값은" << n_int_max << "이다." << endl;
    cout << "int 최솟값은" << n_int_min << "이다." << endl;
    
    cout << "##### long #####" << endl;
    cout << "long은" << sizeof n_long_max  << "바이트이다." << endl;
    cout << "long의 최댓값은" << n_long_max << "이다." << endl;
    cout << "long의 최솟값은" << n_long_min << "이다." << endl;
    
    cout << "##### long long #####" << endl;
    cout << "long long은" << sizeof n_double_long_max << "바이트이다." << endl;
    cout << "long long의 최댓값은" << n_double_long_max << "이다." << endl;
    cout << "long long의 최솟값은" << n_double_long_min << "이다." << endl;

    cout << "##### 음수 x #####" << endl;
    unsigned short a = -1;    
    unsigned int b = -1;
    unsigned long c = -1;
    unsigned long long d = -1;

    cout << "short의 최댓값은" << a << "이다." << endl;
    cout << "int의 최댓값은" << b << "이다." << endl;
    cout << "long의 최댓값은" << c << "이다." << endl;
    cout << "long long의 최댓값은" << d << "이다." << endl;


    /* 실수형: float */
    float a_float = 3.14;
    int b_float = 3.14; 
    cout << a_float << "  " << b_float << endl;

    return 0;
}