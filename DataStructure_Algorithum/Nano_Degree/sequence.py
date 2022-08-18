# 등차수열
def commonDifference():
    a1, d, n = map(int, input('첫 항/공차/몇 번째 수: ').split('/'))
    result = a1 + d * (n - 1)

    print(result)


# 등비 수열
def commonRatio():
    a1, r, n = map(int, input('첫 항/공비/몇 번째 수: ').split('/'))
    result = a1 * r**(n - 1)

    print(result)


# 피보나치 수열
def Fibonacci():
    a1, a2, n = map(int, input('첫 번째항/ 두 번째항/몇 번째 수 : ').split('/'))
    result = [None] * n
    result[0], result[1] = a1, a2
    for i in range(n - 2):
        result[i + 2] = result[i] + result[i + 1]

    print(result[n - 1])


if __name__ == '__main__':
    commonDifference()
    commonRatio()
    Fibonacci()
