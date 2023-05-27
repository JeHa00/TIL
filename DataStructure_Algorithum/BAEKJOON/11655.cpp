#include <bits/stdc++.h>
using namespace std;
string n;
int main()
{
    getline(cin, n);
    for (int i = 0; i < n.size(); i++)
    {
        if (65 <= int(n[i]) && int(n[i]) <= 90)
        {
            if (90 < n[i] + 13)
                n[i] -= 13;
            else
                n[i] += 13;
        }
        else if (97 <= int(n[i]) && int(n[i]) <= 122)
        {
            if (122 < n[i] + 13)
                n[i] -= 13;
            else
                n[i] += 13;
        }
    }

    cout << n;
    return 0;
}