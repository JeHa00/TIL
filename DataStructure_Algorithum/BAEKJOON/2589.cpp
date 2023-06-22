#include <bits/stdc++.h>
using namespace std;
const int v = 54;
int n, m, cnt = 1, visited[v][v], a[v][v];
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, 1, 0, -1};
char c;

void bfs(int y, int x)
{
    memset(visited, 0, sizeof(visited));
    visited[y][x] = 1;
    queue<pair<int, int>> q;
    q.push({y, x});
    while (q.size())
    {
        tie(y, x) = q.front();
        q.pop();

        for (int i = 0; i < 4; i++)
        {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx] || !a[ny][nx])
                continue;
            visited[ny][nx] = visited[y][x] + 1;
            q.push({ny, nx});
            cnt = max(cnt, visited[ny][nx]);
        }
    }
    return;
}

int main()
{
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> c;
            if (c == 'L')
                a[i][j] = 1;
            else
                a[i][j] = 0;
        }
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (a[i][j])
                bfs(i, j);
        }
    }

    cout << cnt - 1;
    return 0;
}