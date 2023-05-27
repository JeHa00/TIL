#include <bits/stdc++.h>
using namespace std;
int sum, a[9];
pair<int, int> ret;
void solve()
{
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < i; j++)
        {
            if (sum - a[i] - a[j] == 100)
            {
                ret = {i, j};
                return;
            }
        }
    }
}

int main()
{
    for (int i = 0; i < 9; i++)
    {
        cin >> a[i];
        sum += a[i];
    }

    sort(a, a + 9);

    solve();

    for (int i = 0; i < 7; i++)
    {
        if (ret.first == i || ret.second == i)
        {
            continue;
        }
        cout << a[i] << "\n";
    }

    return 0;
}