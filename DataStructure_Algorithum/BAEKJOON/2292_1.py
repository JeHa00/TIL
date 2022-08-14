"""
[problem: 2292]

- 문제
    - 계차수열을 이용하는 문제로서, 계차수열을 코드에 녹여내야 한다. 

- 그래서 입력한 숫자 벌집까지 가는 최소 루트를 계산해야 한다. 
    - 최소 루트이기 때문에, 입력한 숫자 벌집이 몇 번째 둘레 (또는 layer)에 있는지만 확인하면 된다. 그 과정 루트는 고려하지 않기 때문이다. 

- 처음에는 while True 대신에 for i in range(1, N) 으로 시작했다. 그런데, range 마지막 값을 어떻게 계산해야할지 막막했다. 확실한 건 N이 발견되면 끝나는 것이다. 그래서 while True를 사용하고, 그 대신 발견되면 N까지의 최소 경로를 출력하고 break로 중단한다. 

- 최소 경로는 layer 층을 의미한다. 그리고, list의 index로 이를 구현했다.  

"""


def findRoute(N: int) -> int:
    beehive = [[1], ]
    i = 1
    while True:
        beehive.append([None] * i * 6)

        # start number
        if i == 1:
            six_sum = 0

        else:
            six_sum = sum(range(6, 6 * (i - 1) + 1, 6))

        start = 2 + six_sum

        # end number
        end = start + 6 * i

        # 둘러 쌓여진 beehive number
        for j, k in enumerate(range(start, end)):
            beehive[i][j] = k

        if N in beehive[i]:
            print(i + 1)
            break
        else:
            i += 1
            continue


if __name__ == "__main__":
    total_through_rooms = int(input())
    findRoute(total_through_rooms)
