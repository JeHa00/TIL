#include <bits/stdc++.h>
using namespace std;
int n, m, a[50][50], ans = 987654321;
vector<pair<int, int>> homeList, chickenList;
vector<vector<int>> selectedChicken;

void combi(int start, vector<int> v)
{
    if (v.size() == m)
    {
        selectedChicken.push_back(v);
        return;
    }

    for (int i = start + 1; i < chickenList.size(); i++)
    {
        v.push_back(i);
        combi(i, v);
        v.pop_back();
    }
    return;
}

int main()
{
    cin >> n >> m;
    for (int y = 0; y < n; y++)
    {
        for (int x = 0; x < n; x++)
        {
            cin >> a[y][x];
            if (a[y][x] == 1)
                homeList.push_back({y, x});
            else if (a[y][x] == 2)
                chickenList.push_back({y, x});
        }
    }
    vector<int> b;
    combi(-1, b);

    for (vector<int> cList : selectedChicken)
    {
        int ret = 0;
        for (pair<int, int> h : homeList)
        {
            int cnt = 987654321;
            for (int c : cList)
            {
                int dist = abs(h.first - chickenList[c].first) + abs(h.second - chickenList[c].second);
                cnt = min(cnt, dist);
            }
            ret += cnt;
        }
        ans = min(ans, ret);
    }
    cout << ans << "\n";
    return 0;
}