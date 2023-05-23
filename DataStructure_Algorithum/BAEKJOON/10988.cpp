#include <bits/stdc++.h>
using namespace std;
string word;
string r;
int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> r;
    word = r;
    reverse(r.begin(), r.end());
    if (word == r)
    {
        cout << 1 << "\n";
    }
    else
        cout << 0 << "\n";
    return 0;
}