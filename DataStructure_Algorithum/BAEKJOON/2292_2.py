"""
[problem: 2292]

- 문제
    - 계차수열을 이용하는 문제로서, 계차수열을 코드에 녹여내야 한다. 

- 그래서 입력한 숫자 벌집까지 가는 최소 루트를 계산해야 한다. 
    - 최소 루트이기 때문에, 입력한 숫자 벌집이 몇 번째 둘레 (또는 layer)에 있는지만 확인하면 된다. 그 과정 루트는 고려하지 않기 때문이다. 

- 2292_1.py 에서 해결했지만, 128MB 메모리 제한을 넘어버려서 다른 방안을 찾아본다. 

- 굳이 list를 만들 필요는 없다. 범위 안에 속하는지만 판단하면 되고, 시작값과 끝값을 계차수열로 표현하면 된다. 

- 또한 if 문 판별도 start <= N <= end 로 할 필요가 없다. i가 작은 값보다 점차 커지기 때문이다. 
"""


def findRoute(N: int) -> int:
    i = 1
    while True:
        if N == 1:
            print(1)
            break

        start = 2 + sum(range(6, 6 * (i - 1) + 1, 6))
        end = start + 6 * i - 1

        # print(f"start: {start}, end: {end}")

        if N <= end:
            print(i + 1)
            break
        else:
            i += 1
            continue


if __name__ == "__main__":
    beehive_room_number = int(input())
    findRoute(beehive_room_number)
