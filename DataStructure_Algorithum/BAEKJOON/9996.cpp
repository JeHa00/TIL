#include <bits/stdc++.h>
using namespace std;
int n, pos;
string name, pattern, prefix, suffix;

int main()
{
    cin >> n;
    cin >> pattern;
    pos = pattern.find('*');
    prefix = pattern.substr(0, pos);
    suffix = pattern.substr(pos + 1);

    for (int i = 0; i < n; i++)
    {
        cin >> name;
        if (prefix.size() + suffix.size() > name.size())
        {
            cout << "NE\n";
        }
        else
        {
            if (name.substr(0, prefix.size()) == prefix && name.substr(name.size() - suffix.size()) == suffix)
                cout << "DA\n";
            else
                cout << "NE\n";
        }
    }

    return 0;
}