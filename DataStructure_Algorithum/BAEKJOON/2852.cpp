#include <bits/stdc++.h>
#define prev ee
using namespace std;
int t, d, A, B, asum, bsum;
string e, prev, minute, second;

void print(int ret)
{

    minute = "00" + to_string(ret / 60);
    second = "00" + to_string(ret % 60);

    cout << minute.substr(minute.size() - 2, 2) << ':' << second.substr(second.size() - 2, 2) << '\n';
}

int changeToTime(string score_time)
{
    return (atoi(score_time.substr(0, 2).c_str()) * 60 + atoi(score_time.substr(3, 2).c_str()));
}

void getWinningTime(int &sum, string score_time)
{
    sum += (changeToTime(score_time) - changeToTime(prev));
}

int main()
{
    cin >> t;
    while (t--)
    {
        cin >> d >> e;
        if (A > B)
            getWinningTime(asum, e);
        else if (A < B)
            getWinningTime(bsum, e);
        d == 1 ? A++ : B++;
        prev = e;
    }

    if (A > B)
        getWinningTime(asum, "48:00");
    else if (A < B)
        getWinningTime(bsum, "48:00");

    print(asum);
    print(bsum);

    return 0;
}