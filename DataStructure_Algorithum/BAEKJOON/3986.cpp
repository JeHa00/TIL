#include <bits/stdc++.h>
using namespace std;
int n, cnt;
string word;
int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> word;
        stack<int> s;
        for (auto w : word)
        {
            if (s.size() && s.top() == w)
                s.pop();
            else
                s.push(w);
        }
        if (s.size() == 0)
        {
            cnt++;
        }
    }
    cout << cnt;
    return 0;
}