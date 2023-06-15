#include <bits/stdc++.h>
using namespace std;
const int v = 104;
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, 1, 0, -1};
int a[v][v], visited[v][v], hour, cnt, ret, mcnt, m, n;

void dfs(int y, int x)
{

    for (int i = 0; i < 4; i++)
    {
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (ny <= 0 || nx <= 0 || ny >= m || nx >= n || visited[ny][nx])
            continue;
        if (a[ny][nx])
        {
            cout << "Enter\n";
            visited[ny][nx] = 1;
            mcnt++;
            a[ny][nx] = 0;
            continue;
        }

        dfs(ny, nx);
    }
}

int main()
{
    cin >> m >> n;
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> a[i][j];
            if (a[i][j] == 1)
                cnt++;
        }
    }

    ret = cnt;
    while (cnt != 0)
    {
        memset(visited, 0, sizeof(visited));
        mcnt = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (a[i][j] && !visited[i][j])
                {
                    dfs(i, j);
                }
            }
        }

        hour++;
        cnt -= mcnt;
        if (cnt != 0)
            ret = min(ret, cnt);
        cout << "mcnt: " << mcnt << '\n';
    }

    cout << hour << '\n';
    cout << ret << '\n';
    return 0;
}
