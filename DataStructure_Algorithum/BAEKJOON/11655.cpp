#include <bits/stdc++.h>
using namespace std;
string word;
int a;
int main()
{
    getline(cin, word);
    for (int i = 0; i < word.size(); i++)
    {
        if (65 <= word[i] && word[i] <= 90)
        {
            if (90 < word[i] + 13)
            {
                word[i] = char(word[i] - 13);
            }
            else
            {
                word[i] = char(word[i] + 13);
            }
        }

        else if (97 <= word[i] && word[i] <= 122)
        {
            if (122 < (word[i] + 13))
            {
                word[i] = char(word[i] - 13);
            }
            else
            {
                word[i] = char(word[i] + 13);
            }
        }
    }

    cout << word << "\n";

    return 0;
}