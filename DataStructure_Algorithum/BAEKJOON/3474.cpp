#include <bits/stdc++.h>
using namespace std;
int t, n;

int main()
{
    cin >> t;
    while (t--)
    {
        cin >> n;
        int five_cnt = 0;
        for (int i = 5; i <= n; i *= 5)
        {
            five_cnt += n / i;
        }
        cout << five_cnt << "\n";
    }

    return 0;
}