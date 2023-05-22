#include <bits/stdc++.h>
using namespace std;

int n[9];
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    for (int i = 0; i < 9; i++)
        cin >> n[i];

    sort(n, n + 9);

    do
    {
        int sum = 0;
        for (int i = 0; i < 7; i++)
        {
            sum += n[i];
        }
        if (sum == 100)
            break;
    } while (next_permutation(n, n + 9));

    for (int i = 0; i < 7; i++)
        cout << n[i] << "\n";

    return 0;
}
