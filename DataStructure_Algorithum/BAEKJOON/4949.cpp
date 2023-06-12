#include <bits/stdc++.h>
using namespace std;
string s;
bool check;

int main()
{
    while (getline(cin, s))
    {
        if (s == ".")
            break;
        stack<char> stk;
        check = true;
        for (char i : s)
        {
            if (i == ')') {
                if (stk.empty() || stk.top() == '[') {
                    check = false;
                    break;
                }
                else stk.pop();
            }
            else if (i == ']') {
                if (stk.empty() || stk.top() == '(') {
                    check = false;
                    break;
                }
                else stk.pop();
            }
            
            if (i == '(' || i == '[') stk.push(i);
        }

        if (check && stk.empty())
            cout << "yes\n";
        else
            cout << "no\n";
    }

    return 0;
}