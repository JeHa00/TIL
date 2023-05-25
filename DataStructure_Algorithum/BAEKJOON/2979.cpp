#include <bits/stdc++.h>
using namespace std;
int a, b, c, s, e, sum, t[100];
int main()
{
    cin >> a >> b >> c;
    for (int i = 0; i < 3; i++)
    {
        cin >> s >> e;
        for (int i = s; i < e; i++)
        {
            t[i]++;
        }
    }

    for (int i : t)
    {
        if (i == 1)
            sum += i * a;
        else if (i == 2)
            sum += i * b;
        else
            sum += i * c;
    }

    cout << sum;

    return 0;
}