# 주사위
"""
두 주사위의 각 최대치를 입력하여, 나올 수 있는 모든 경우의 수를 출력하는 것
"""

a = int(input('a: '))
b = int(input('b: '))

for i in range(1, a + 1):
    for j in range(1, b + 1):
        print(i, j)


# 구구단
"""
1 ~ 9단 모두 출력
"""
for i in range(1, 10):
    for j in range(1, 10):
        print(f"{i} x {j} = {i * j}")



# 행렬 만들기

m, n = map(int, input("M,N: ").split(','))

for i in range(n): 
    for j in range(1, m + 1): 
        if j == m: 
            print(f'{i * n + j}')
        else:
            print(f'{i * n + j}', end = ' ')
