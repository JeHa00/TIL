#include <bits/stdc++.h>
using namespace std;
int t, n;
string a, b;
int main()
{
    cin >> t;
    while (t--)
    {
        cin >> n;
        long long sum = 1;
        map<string, long long> mp;
        for (int i = 0; i < n; i++)
        {
            cin >> a >> b;
            mp[b]++;
        }

        for (auto m : mp)
        {
            sum *= (m.second + 1);
        }

        sum--;
        cout << sum << "\n";
    }

    return 0;
}