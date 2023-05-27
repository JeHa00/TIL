#include <bits/stdc++.h>
using namespace std;
int n, m, cnt, a[15001];
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;
    cin >> m;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }

    if (m > 200000)
        cout << 0 << "\n";
    else
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                if (a[i] + a[j] == m)
                    cnt++;
            }
        }
        cout << cnt << '\n';
    }

    return 0;
}

// 투 포인터 방식
int n, m, a[15001], cnt, l, r, sum;
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;
    cin >> m;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }

    sort(a, a + n);

    r = n - 1;

    while (l < r)
    {
        sum = a[l] + a[r];

        if (sum < m)
        {
            l++;
        }
        else if (sum > m)
        {
            r--;
        }
        else
        {
            cnt++;
            l++;
            r--;
        }
    }

    cout << cnt;
    return 0;
}
