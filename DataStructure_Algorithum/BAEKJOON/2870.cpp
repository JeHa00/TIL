#include <bits/stdc++.h>
using namespace std;
int n;
vector<string> v;
string s, a;

void go()
{
    if (a.size())
    {
        while (true)
        {
            if (a.size() && a.front() == '0')
                a.erase(a.begin());
            else
                break;
        }

        if (a.size() == 0)
            a = "0";
        v.push_back(a);
        a = "";
    }
}

bool cmp(string a, string b)
{

    if (a.size() == b.size())
        return a < b;

    return a.size() < b.size();
}

int main()
{
    cin >> n;
    while (n--)
    {
        cin >> s;
        a = "";
        for (char i : s)
        {
            if (i < 65)
                a += i;
            else
                go();
        }
        go();
    }
    sort(v.begin(), v.end(), cmp);

    for (string i : v)
        cout << i << '\n';
    return 0;
}