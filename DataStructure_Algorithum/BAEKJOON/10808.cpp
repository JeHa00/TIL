#include <bits/stdc++.h>
using namespace std;
string word;
int a[26];
int main()
{
    cin >> word;
    for (auto w : word)
    {
        a[w - 'a']++;
    }

    for (auto i : a)
    {
        cout << i << " ";
    }
    return 0;
}