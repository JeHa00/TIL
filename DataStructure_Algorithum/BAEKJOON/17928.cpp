#include <bits/stdc++.h>
using namespace std;
int n, a[1000004], ret[1000004];
stack <int> s;

int main() {
    cin >> n;  //  4
    memset(ret, -1, sizeof(ret));
    for (int i = 0; i < n; i++) {
        cin >> a[i]; // 3 5
        while (s.size() && a[s.top()] < a[i]) {

            ret[s.top()] = a[i];
            s.pop();
        }
        s.push(i);
    }
    return 0;
}