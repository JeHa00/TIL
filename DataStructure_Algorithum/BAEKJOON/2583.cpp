#include <bits/stdc++.h>
using namespace std;
const int s = 101;
int m, n, k, a[s][s], visited[s][s], xs, xe, ys, ye, sum;
int dy[4] = {1, 0, -1, 0};
int dx[4] = {0, 1, 0, -1};
vector<int> v;

void dfs(int y, int x)
{
    sum++;
    visited[y][x] = 1;
    for (int i = 0; i < 4; i++)
    {
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (ny < 0 || nx < 0 || ny >= m || nx >= n)
            continue;
        if (a[ny][nx] == 0 && !visited[ny][nx])
            dfs(ny, nx);
    }
};

int main()
{
    cin >> m >> n >> k;

    for (int i = 0; i < k; i++)
    {
        cin >> xs >> ys >> xe >> ye;
        for (int y = ys; y < ye; y++)
        {
            for (int x = xs; x < xe; x++)
                a[y][x] = 1;
        }
    }

    for (int y = 0; y < m; y++)
    {
        for (int x = 0; x < n; x++)
        {
            if (a[y][x] == 0 && !visited[y][x])
            {
                dfs(y, x);
                v.push_back(sum);
                sum = 0;
            }
        }
    }

    sort(v.begin(), v.end());
    cout << v.size() << '\n';
    for (auto i : v)
        cout << i << ' ';
    return 0;
}