#include <bits/stdc++.h>
using namespace std;
int v_count, c_count, w_count;
bool flag, v_must;
string word;

bool isVowel(char w)
{
    return (w == 'a' || w == 'e' || w == 'i' || w == 'o' || w == 'u');
}

int main()
{
    while (cin >> word)
    {
        if (word == "end") break;
        v_must = false;
        v_count = 0;
        c_count = 0;
        flag = false;
        char v = ' ';

        for (char w : word)
        {
            if (isVowel(w))
            {
                v_must = true;
                v_count++;
                c_count = 0;
                flag = 0;
            }
            else
            {
                c_count++;
                v_count = 0;
            }

            if (w == v && (w != 'e' && w != 'o')) flag = true;
            if (v_count == 3 || c_count == 3) flag = true;
            if (flag) break;
            v = w;
        }
        if (!v_must) flag = true;
        if (flag) cout << "<" << word << "> is not acceptable.\n";
        else cout << "<" << word << "> is acceptable.\n";
    };
    return 0;
}