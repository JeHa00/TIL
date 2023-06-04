#include <bits/stdc++.h>
using namespace std;
const int v = 101;
int dy[4] = {-1, 0, 1, 0};
int dx[4] = {0, 1, 0, -1};
int n, a[v][v], visited[v][v], ret = 1;
// ret = 1로 정의하지 않으면 정답이 되지 않는다.
// 비가 안올 때 잠기지 않는 영역은 1이므로 최소값은 1이어야 한다.

void dfs(int y, int x, int d)
{
    visited[y][x] = 1;
    for (int i = 0; i < 4; i++)
    {
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (nx < 0 || ny < 0 || nx >= n || ny >= n)
            continue;
        if (a[ny][nx] > d && !visited[ny][nx])
            dfs(ny, nx, d);
    }
    return;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
            cin >> a[i][j];
    }

    for (int d = 1; d < 101; d++)
    {
        int cnt = 0;
        memset(visited, 0, sizeof(visited));

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (a[i][j] > d && !visited[i][j])
                {
                    dfs(i, j, d);
                    cnt++;
                }
            }
        }
        ret = max(ret, cnt);
    }

    cout << ret << '\n';

    return 0;
}