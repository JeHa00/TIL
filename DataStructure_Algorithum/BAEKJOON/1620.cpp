#include <bits/stdc++.h>
using namespace std;
int n, m;
string name;
map<string, int> mp;
map<int, string> mp2;
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        cin >> name;
        mp[name] = i + 1;
        mp2[i + 1] = name;
    }

    for (int i = 0; i < m; i++)
    {
        cin >> name;
        int k = atoi(name.c_str());
        if (k == 0)
        {
            cout << mp[name] << "\n";
        }
        else
        {
            cout << mp2[k] << "\n";
        }
    }
    return 0;
}