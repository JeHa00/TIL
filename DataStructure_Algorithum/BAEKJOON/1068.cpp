#include <bits/stdc++.h>
using namespace std;
vector<int> a[50];
int n, m, d, root;

int dfs(int idx)
{
    int ret = 0;
    int child = 0;
    for (int there : a[idx])
    {
        if (there == d) continue;
        ret += dfs(there);
        child++;
    }
    if (child == 0) return 1;
    return ret;
}

int main()
{
    cin >> n;
    for(int i = 0; i < n; i++){
        cin >> m;
        if (m == -1) root = i;
        else a[m].push_back(i);
    }

    cin >> d;

    if (d == root)
    {
        cout << "0\n";
        return 0;
    }

    cout << dfs(root) << '\n';
}
