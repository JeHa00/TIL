#include <bits/stdc++.h>
using namespace std;
string word;
int a[26];
int main()
{
    cin >> word;

    for (char s : word)
    {
        a[(int)s - 97]++;
    }

    for (int i : a)
    {
        cout << i << " ";
    }

    return 0;
}
