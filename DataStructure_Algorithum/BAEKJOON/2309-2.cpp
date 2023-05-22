#include <bits/stdc++.h>
using namespace std;
const int total_count = 9;
int sum;
int a[total_count];
vector<int> v;
pair<int, int> p;

void solve()
{
    for (int i = 0; i < total_count; i++)
    {
        for (int j = 0; j < i; j++)
        {
            if (sum - a[i] - a[j] == 100)
                p = {i, j};
        }
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    for (int i = 0; i < total_count; i++)
    {
        cin >> a[i];
        sum += a[i];
    }

    solve();

    for (int i = 0; i < total_count; i++)
    {
        if (p.first == i || p.second == i)
            continue;
        v.push_back(a[i]);
    }

    sort(v.begin(), v.end());

    for (auto i : v)
        cout << i << "\n";

    return 0;
}