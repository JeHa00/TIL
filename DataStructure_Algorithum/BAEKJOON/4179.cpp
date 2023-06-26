#include <bits/stdc++.h>
using namespace std;
const int INF = 987654321, v = 1004;
char a[v][v];
int n, m, ret, sy, sx, y, x;
int dx[4] = {0, 1, 0, -1}, dy[4] = {-1, 0, 1, 0};
int fire_check[v][v], person_check[v][v];

bool in(int a, int b)
{
    return 0 <= a && a < n && 0 <= b && b < m;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    queue<pair<int, int>> q;
    cin >> n >> m;
    fill(&fire_check[0][0], &fire_check[0][0] + v * v, INF);
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> a[i][j];
            if (a[i][j] == 'F')
            {
                fire_check[i][j] = 1;
                q.push({i, j});
            }
            else if (a[i][j] == 'J')
            {
                sy = i;
                sx = j;
            }
        }
    }

    // fire가 퍼질 때 이동 거리 계산
    while (q.size())
    {
        tie(y, x) = q.front();
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!in(ny, nx))
                continue;
            if (fire_check[ny][nx] != INF || a[ny][nx] == '#')
                continue;
            fire_check[ny][nx] = fire_check[y][x] + 1;
            q.push({ny, nx});
        }
    }

    // 지훈이의 이동거리 계산
    person_check[sy][sx] = 1;
    q.push({sy, sx});
    while (q.size())
    {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();
        if (x == m - 1 || y == n - 1 || x == 0 || y == 0)
        {
            ret = person_check[y][x];
            break;
        }
        for (int i = 0; i < 4; i++)
        {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!in(ny, nx))
                continue;
            if (person_check[ny][nx] || a[ny][nx] == '#')
                continue;
            if (fire_check[ny][nx] <= person_check[y][x] + 1)
                continue;
            person_check[ny][nx] = person_check[y][x] + 1;
            q.push({ny, nx});
        }
    }
    if (ret)
        cout << ret;
    else
        cout << "IMPOSSIBLE\n";
    return 0;
}