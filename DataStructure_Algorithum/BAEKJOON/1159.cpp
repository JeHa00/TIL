#include <bits/stdc++.h>
using namespace std;
int n, a[26];
string name, ans;
int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> name;
        a[name[0] - 'a']++;
    }

    for (int i = 0; i < 26; i++)
    {
        if (a[i] >= 5)
        {
            ans += i + 'a';
        }
    }
    if (ans.size())
        cout << ans;
    else
        cout << "PREDAJA";
    return 0;
}