#include <bits/stdc++.h>
using namespace std;
int n, a[68][68];
string s;

string divide(int y, int x, int size)
{
    if (size == 1)
        return to_string(a[y][x]); 
    // string(size_t, char c) 이므로 int가 아닌 char가 들어가야 한다.
    // 그래서 int를 사용하고 싶다면 string이 아닌 to_string()을 사용해야 한다.
    string ans = "";
    for (int i = y; i < y + size; i++)
    {
        for (int j = x; j < x + size; j++)
        {
            if (a[y][x] != a[i][j])
            {
                ans += "(";
                ans += divide(y, x, size / 2);
                ans += divide(y, x + size / 2, size / 2);
                ans += divide(y + size / 2, x, size / 2);
                ans += divide(y + size / 2, x + size / 2, size / 2);
                ans += ")";
                return ans;
            }
        }
    }
    return to_string(a[y][x]);
    // return string(1, a[y][x]); 
}

int main()
{
    cin >> n;
    for (int y = 0; y < n; y++)
    {
        for (int x = 0; x < n; x++)
        {
            scanf("%1d", &a[y][x]);
        }
    }
    // string() 을 사용하고 싶으면 다음과 같이 작성한다. 
    // for (int y= 0 ; y<n; y++) {
    //     cin >> s;
    //     for (int x = 0; x < n; x++) {
    //         a[y][x] = s[x];
    //     }
    // }

    cout << divide(0, 0, n);
    return 0;
}