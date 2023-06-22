#include <bits/stdc++.h>
using namespace std;
int m, n, k, visited[104][104], a[104][104];
int ly, lx, ry, rx;
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, 1, 0, -1};
vector<int> s;

int dfs(int y, int x)
{
    visited[y][x] = 1;
    int su = 1;
    for (int i = 0; i < 4; i++)
    {
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (ny < 0 || nx < 0 || ny >= m || nx >= n || visited[ny][nx] || !a[ny][nx])
            continue;
        visited[ny][nx] = 1;
        su += dfs(ny, nx);
    }

    return su;
}

int main()
{
    memset(a, 1, sizeof(a));
    cin >> m >> n >> k;

    while (k--)
    {
        cin >> lx >> ly >> rx >> ry;
        for (int y = ly; y < ry; y++)
        {
            for (int x = lx; x < rx; x++)
            {
                a[y][x] = 0;
            }
        }
    }

    for (int y = 0; y < m; y++)
    {
        for (int x = 0; x < n; x++)
        {
            if (a[y][x] && !visited[y][x])
            {
                s.push_back(dfs(y, x));
            }
        }
    }

    sort(s.begin(), s.end());
    
    cout << s.size() << "\n";
    for (int i : s)
        cout << i << " ";

    return 0;
}