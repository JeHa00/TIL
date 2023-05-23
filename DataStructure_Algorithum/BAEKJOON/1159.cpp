#include <bits/stdc++.h>
using namespace std;
int a[26];
int N;
string word, s;
int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    for (int i = 0; i < N; i++)
    {
        cin >> word;
        a[word[0] - 97]++;
    }

    for (int i = 0; i < 26; i++)
    {
        if (a[i] >= 5)
            s += char(i + 97);
    }

    if (s.size())
        cout << s << "\n";
    else
        cout << "PREDAJA"
             << "\n";

    return 0;
}