#include <bits/stdc++.h>
using namespace std;
int n, m, y, x, adj[100][100], visited[100][100];
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, 1, 0, -1};

int main()
{
    cin >> n >> m;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            scanf("%1d", &adj[i][j]);
        }
    }
    int sy = 0;
    int sx = 0;

    queue<pair<int, int>> q;
    q.push({sy, sx});
    while (q.size())
    {
        tie(y, x) = q.front();
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= n || nx >= m)
                continue;
            if (visited[ny][nx])
                continue;
            if (adj[ny][nx] == 1 && !visited[ny][nx])
            {
                visited[ny][nx] = visited[y][x] + 1;
                q.push({ny, nx});
            }
        }
    }
    cout << visited[n - 1][m - 1] + 1;
    return 0;
}