#include <bits/stdc++.h>
using namespace std;
string s, ret;
int cnt[100], flag;
char mid;
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> s;
    for (char a : s)
        cnt[int(a)]++;
    for (int i = 'Z'; i >= 'A'; i--)
    {
        if (cnt[i])
        {
            if (cnt[i] & 1)
            {
                mid = char(i);
                flag++;
                cnt[i]--;
            }
            if (flag == 2)
            {
                cout << "break";
                return 0;
            }
            for (int j = 0; j < cnt[i]; j += 2)
            {
                ret = char(i) + ret;
                ret += char(i);
            }
        }
    }
    if (mid)
        ret.insert(ret.begin() + ret.size() / 2, mid);
    // if (flag == 2)
    //     cout << "I'm Sorry Hansoo\n";
    else
        cout << ret << "\n";
}