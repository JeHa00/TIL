#include <bits/stdc++.h>
using namespace std;
vector<int> a[50];
int n, m, visited[50], d, cnt;

void go(int idx)
{
    visited[idx] = 1;
    if (a[idx].size() == 0 && idx != d)
    {
        cnt++;
    }
    for (int there : a[idx])
    {
        if (visited[there] || there == d)
            continue;
        go(there);
    }
    return;
}

int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> m;
        if (m == -1)
            continue;
        a[m].push_back(i);
    }

    cin >> d;
    for (int i = 0; i < n; i++)
    {
        if (a[i].size() && visited[i] == 0 && i != d)
            go(i);
    }
    cout << cnt << '\n';
}