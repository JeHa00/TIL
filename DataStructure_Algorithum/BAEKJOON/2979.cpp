#include <bits/stdc++.h>
using namespace std;
int sum = 0;
int A, B, C, a, b;
int time_table[104];

int main()
{
    cin >> A >> B >> C;
    for (int i = 0; i < 3; i++)
    {
        cin >> a >> b;
        for (int i = a; i < b; i++)
        {
            time_table[i]++;
        }
    }
    for (auto count : time_table)
    {
        if (count == 1)
        {
            sum += A;
        }
        else if (count == 2)
        {
            sum += B * 2;
        }
        else if (count == 3)
        {
            sum += C * 3;
        }
    }
    cout << sum << "\n";
}