#include <bits/stdc++.h>
using namespace std;
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, 1, 0, -1};
const int v = 8;
int n, m, a[v][v], ret, cnt, visited[v][v];
vector<pair<int, int>> wallList, virusList;

void dfs(int y, int x)
{
    visited[y][x] = 1;
    for (int i = 0; i < 4; i++)
    {
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx] || a[ny][nx] != 0)
            continue;
        dfs(ny, nx);
    }
    return;
}

int solve()
{
    memset(visited, 0, sizeof(visited));
    for (pair<int, int> v : virusList)
    {
        dfs(v.first, v.second);
    }

    int cnt = 0;
    for (int y = 0; y < n; y++)
    {
        for (int x = 0; x < m; x++)
        {
            if (a[y][x] == 0 && !visited[y][x])
                cnt++;
        }
    }

    return cnt;
}

int main()
{
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> a[i][j];
            if (a[i][j] == 0)
                wallList.push_back({i, j});
            if (a[i][j] == 2)
                virusList.push_back({i, j});
        }
    }

    for (int i = 0; i < wallList.size(); i++)
    {
        for (int j = i + 1; j < wallList.size(); j++)
        {
            for (int k = j + 1; k < wallList.size(); k++)
            {
                a[wallList[i].first][wallList[i].second] = 1;
                a[wallList[j].first][wallList[j].second] = 1;
                a[wallList[k].first][wallList[k].second] = 1;
                ret = max(ret, solve());
                a[wallList[i].first][wallList[i].second] = 0;
                a[wallList[j].first][wallList[j].second] = 0;
                a[wallList[k].first][wallList[k].second] = 0;
            }
        }
    }
    cout << ret << '\n';
    return 0;
}
