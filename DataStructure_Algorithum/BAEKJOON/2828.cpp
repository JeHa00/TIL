#include <bits/stdc++.h>
using namespace std;
int n, m, j, ret, l, r, p;
int main()
{
    cin >> n >> m >> j;
    l = 1;
    for (int i = 0; i < j; i++)
    {
        r = l + m - 1;
        cin >> p;

        if (l <= p && p <= r)
            continue;

        else
        {
            if (p < l)
            {
                ret += (l - p);
                l = p;
            }
            else
            {
                ret += (p - r);
                l += (p - r);
            }
        }
    }
    cout << ret;
    return 0;
}
