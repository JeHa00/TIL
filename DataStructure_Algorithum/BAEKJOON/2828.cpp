#include <bits/stdc++.h>
using namespace std;
int n, m, j, a, l, r, cnt, d;

int main()
{
    cin >> n >> m;
    cin >> j;
    int t[n];
    memset(t, 0, sizeof(t));
    l = 0;
    r = l + m - 1;
    while (j--)
    {
        cin >> a;
        if (a - 1 > r)
        {
            d = (a - 1) - r;
            cnt += d;
            r += d;
        }
        else if (a - 1 < l)
        {
            d = l - (a - 1);
            cnt += d;
            r -= d;
        }

        l = r - m + 1;
    }
    cout << cnt;
    return 0;
}