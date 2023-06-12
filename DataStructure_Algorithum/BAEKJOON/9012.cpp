#include <bits/stdc++.h>
using namespace std;
int t;
string text;

int main()
{
    cin >> t;
    while (t--)
    {
        cin >> text;
        stack<char> s;
        for (char i : text)
        {
            if (!s.empty()) s.push(i);
            else {
                if (s.top() == '(' && i == ')') s.pop();
                else s.push(i);
            }
        }
        if (s.size()) cout << "NO\n";
        else cout << "YES\n";
    }

    return 0;
}