#include <bits/stdc++.h>
using namespace std;
int n, a[1000004];
stack<int> stk;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> n;
    int ans[n];
    memset(ans, -1, sizeof(ans));
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }

    for (int i = 0; i < n; i++)
    {
        while (!stk.empty() && (a[stk.top()] < a[i]))
        {
            ans[stk.top()] = a[i];
            stk.pop();
        }
        stk.push(i);
    }
    for (int a : ans)
        cout << a << " ";

    return 0;
}
