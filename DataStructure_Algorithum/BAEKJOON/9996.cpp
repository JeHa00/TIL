#include <bits/stdc++.h>
using namespace std;
int N;
long long pos;
string name, pattern, prefix, suffix, name_prefix, name_suffix;
vector<string> names;
int main()
{
    cin >> N;
    cin >> pattern;
    pos = pattern.find('*');
    prefix = pattern.substr(0, pos);
    suffix = pattern.substr(pos + 1);

    for (int i = 0; i < N; i++)
    {
        cin >> name;
        if (name.size() >= prefix.size() + suffix.size())
        {
            name_prefix = name.substr(0, prefix.size());
            name_suffix = name.substr(name.size() - suffix.size());

            if (name_prefix == prefix && name_suffix == suffix)
                cout << "DA\n";
            else
                cout << "NE\n";
        }
        else
            cout << "NE\n";
    }

    return 0;
}