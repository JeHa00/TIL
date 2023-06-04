#include <bits/stdc++.h>
using namespace std;
const int v = 50;
int t, n, m, k, y, x, adj[v][v], visited[v][v], ret;
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, 1, 0, -1};

void dfs(int y, int x)
{
    visited[y][x] = 1;
    for (int i = 0; i < 4; i++)
    {
        int ny = y + dy[i];
        int nx = x + dx[i];

        if (ny < 0 || nx < 0 || ny >= n || nx >= m)
            continue;
        if (adj[ny][nx] == 1 && !visited[ny][nx])
            dfs(ny, nx);
    }

    return;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> t;
    while (t--)
    {
        int ret = 0;
        memset(adj, 0, sizeof(adj));
        memset(visited, 0, sizeof(visited));

        cin >> m >> n >> k;
        for (int i = 0; i < k; i++)
        {
            cin >> x >> y;
            adj[y][x] = 1;
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {

                if (adj[i][j] && visited[i][j] == 0)
                {
                    ret++;
                    dfs(i, j);
                }
            }
        }
        cout << ret << '\n';
    }
}