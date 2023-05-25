#include <bits/stdc++.h>
using namespace std;
string s;
int main()
{
    getline(cin, s);

    for (int i = 0; i < s.size(); i++)
    {
        if (65 <= int(s[i]) && int(s[i]) <= 90)
        {
            if (90 < s[i] + 13)
            {
                s[i] -= 26;
            }
            s[i] += 13;
        }

        else if (97 <= int(s[i]) && int(s[i]) <= 122)
        {

            if (122 < s[i] + 13)
            {
                s[i] -= 26;
            }
            s[i] += 13;
        }
    }

    cout << s << "\n";
}