#include <bits/stdc++.h>
using namespace std;
int t, n, two_n, ret5, ret, j;
int main()
{
    cin >> t;
    while (t--)
    {
        cin >> n;

        for (int i = 5; i < n; i *= 5)
        {
            ret5 += n / i;
        }

        cout << ret5 << '\n';
    }

    return 0;
}