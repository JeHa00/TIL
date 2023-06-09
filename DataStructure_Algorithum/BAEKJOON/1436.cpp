#include <bits/stdc++.h>
using namespace std;
int n, i;

int main()
{
    cin >> n;
    i = 666;
    for (;; i++)
    {
        if (to_string(i).find("666") != string::npos)
            n--;
        if (n == 0)
            break;
    }

    cout << i;

    return 0;
}