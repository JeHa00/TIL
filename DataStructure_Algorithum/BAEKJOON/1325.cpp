#include <bits/stdc++.h>
using namespace std;
int n, m, a, b, ret, cnt, visited[10004];
vector<int> ans;
vector<int> v[10004];

int dfs(int node)
{
    visited[node] = 1;
    int ret = 1;
    for (int there : v[node])
    {
        if (!visited[there])
        {
            ret += dfs(there);
        }
    }
    return ret;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> n >> m;

    while (m--)
    {
        cin >> a >> b;
        v[b].push_back(a);
    }

    for (int i = 1; i <= n; i++)
    {
        cnt = 0;
        memset(visited, 0, sizeof(visited));
        if (v[i].size() != 0 && !visited[i])
            cnt = dfs(i);

        if (cnt > ret)
        {
            ans.clear();
            ret = cnt;
            ans.push_back(i);
        }
        else if (cnt == ret) ans.push_back(i);
    }

    sort(ans.begin(), ans.end());

    for (int j : ans) cout << j << ' ';
    return 0;
}