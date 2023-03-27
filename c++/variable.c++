#include <iostream>

using namespace std;

int main() {
    /* 변수는 변하는 값 */
    /*변수의 자료형, 이름, 어디에 저장되는가*/
    /* 변수명의 시작으로 정수 사용 불가능 / 변수명으로 예약어 사용 불가능 */

    int a;
    a = 7; 

    int b = 3; 

    cout << a << b << endl;

    a = 5;
    b = 10;

    /* 변수라서 값 변경 가능 */
    cout << a << b << endl;
    
    /* 어디에 저장되는가는 컴파일러가 결정한다.  &{변수명} 으로 확인가능 */
    cout << &a << endl, cout << &b << endl;

    return 0;
}
