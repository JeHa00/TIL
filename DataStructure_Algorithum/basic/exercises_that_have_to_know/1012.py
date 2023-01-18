"""
[유기농 배추: 2012]
인접해 있는 배추들이 총 몇 군데에 퍼져 있는지, 이에 따라 지렁이는 총 몇 마리 필요한지 구하기
한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우가 서로 인접해있는 것

입력값
- 테스트 케이스의 갯수: T 
- 배추밭의 가로길이 (M) 와 세로 길이 (N) 그리고, 배추가 심어져 있는 위치의 개수 (K)
- 배추의 위치: X, Y (두 배추의 위치가 같은 경우는 없다)
2
10 8 17
0 0
1 0
1 1
4 2
4 3
4 5
2 4
3 4
7 4
8 4
9 4
7 5
8 5
9 5
7 6
8 6
9 6
10 10 1
5 5 

출력값
- 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수 출력 
5
1
"""

import sys
import copy


def firstSolution(length_of_horizontal: int, length_of_vertical: int, number_of_position: int):
    """최소의 배추흰지렁이 마리 수

    Args:
        length_of_horizontal (int): 배추밭의 가로 길이 (1 <= M <= 50)
        length_of_vertical (int): 배추밭의 세로 길이 (1 <= N <= 50)
        number_of_position (int): 배추가 심어져 있는 위치의 갯수 (1<= K <= 2500)

    Returns:
        _type_: _description_

    """

    answer = 0 # 흰지렁이의 수
    farm = [[0 for _ in range(length_of_horizontal)] for _ in range(length_of_vertical)] # 배추밭의 크기
    visited = copy.deepcopy(farm)
    
    for _ in range(number_of_position):
        x, y = list(map(int, sys.stdin.readline().split()))
        farm[y][x] = 1

    print("--- farm ---")
    print(f'{farm}')

    """
    [1, 1, 0, 0, 0, 0, 0, 0, 0, 0], 
    [0, 1, 0, 0, 0, 0, 0, 0, 0, 0], 
    [0, 0, 0, 0, 1, 0, 0, 0, 0, 0], 
    [0, 0, 0, 0, 1, 0, 0, 0, 0, 0], 
    [0, 0, 1, 1, 0, 0, 0, 1, 1, 1], 
    [0, 0, 0, 0, 1, 0, 0, 1, 1, 1], 
    [0, 0, 0, 0, 0, 0, 0, 1, 1, 1], 
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
    """

    for y in range(length_of_vertical):
        for x in range(length_of_horizontal):
            try:
                if farm[y][x] == 1 and 1 not in [visited[y-1][x], visited[y+1][x], visited[y][x-1], visited[y][x+1]]:
                    answer += 1 
                    total_direction = 5
                    visited[y][x], visited[y-1][x], visited[y+1][x], visited[y][x-1], visited[y][x+1] = [1 for _ in range(total_direction)]
                    print(f"----- answer: {answer} / x: {x} / y: {y} / visited ---------- ")
                    for i in visited:
                        print(i)
                    print('-------------------------------------------')
                elif farm[y][x] == 1 and 1 in [visited[y-1][x], visited[y+1][x], visited[y][x-1], visited[y][x+1]]:
                    """
                    한 칸 띄어서 존재하는 밭의 경우, visited 처리가 되면서 배추가 붙어있는 걸로 판단되므로, 예제 1에서 1개가 덜 추가된다.
                    farm[4][3]일 때 visited 처리가 되면서 farm[5][4] 에서 주변에 visited가 있으므로 존재한다고 판단되어 1개가 덜 추가된다.
                    """
                    total_direction = 5
                    visited[y][x], visited[y-1][x], visited[y+1][x], visited[y][x-1], visited[y][x+1] = [1 for _ in range(total_direction)]
                    
            except IndexError: 
                continue
    return answer


def secondSolution(length_of_horizontal: int, length_of_vertical: int, number_of_position: int):
    """최소의 배추흰지렁이 마리 수

    Args:
        length_of_horizontal (int): 배추밭의 가로 길이 (1 <= M <= 50)
        length_of_vertical (int): 배추밭의 세로 길이 (1 <= N <= 50)
        number_of_position (int): 배추가 심어져 있는 위치의 갯수 (1<= K <= 2500)

    Returns:
        _type_: _description_

    첫 번째 방식의 문제점을 해결해보자.
    """

    answer = 0 # 흰지렁이의 수
    farm = [[0 for _ in range(length_of_vertical)] for _ in range(length_of_horizontal)] # 배추밭의 크기
    # 첫 번째 솔루션과 length_of_vertical과 length_of_horizontal을 바꾼 이유는 이렇게 해야 입력되는 좌표가 보다 직관적이고, [x][y]로 생각할 수 있다.
    visited = copy.deepcopy(farm)

    for _ in range(number_of_position):
        x, y = list(map(int, sys.stdin.readline().split()))
        farm[y][x] = 1




if __name__ == "__main__":
    T = int(sys.stdin.readline()) # 테스트 케이스의 수
    while T > 0 :
        M, N, K = list(map(int, sys.stdin.readline().split())) # 가로 길이, 세로길이, 배추가 심어진 갯수
        print(f"배추흰지렁이 마리 수: {firstSolution(M, N, K)}")
        # print(f"배추흰지렁이 마리 수: {secondSolution(M, N, K)}")
        T -= 1




"""
[firstSolution의 출력 결과]

농장
[1, 1, 0, 0, 0, 0, 0, 0, 0, 0], 
[0, 1, 0, 0, 0, 0, 0, 0, 0, 0], 
[0, 0, 0, 0, 1, 0, 0, 0, 0, 0], 
[0, 0, 0, 0, 1, 0, 0, 0, 0, 0], 
[0, 0, 1, 1, 0, 0, 0, 1, 1, 1], 
[0, 0, 0, 0, 1, 0, 0, 1, 1, 1], 
[0, 0, 0, 0, 0, 0, 0, 1, 1, 1], 
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]

----- answer: 1 / x: 0 / y: 0 / visited ---------- 
[1, 1, 0, 0, 0, 0, 0, 0, 0, 1]
[1, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[1, 0, 0, 0, 0, 0, 0, 0, 0, 0]
-------------------------------------------
----- answer: 2 / x: 4 / y: 2 / visited ---------- 
[1, 1, 1, 0, 0, 0, 0, 0, 0, 1]
[1, 1, 1, 0, 1, 0, 0, 0, 0, 0]
[0, 1, 0, 1, 1, 1, 0, 0, 0, 0]
[0, 0, 0, 0, 1, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[1, 1, 0, 0, 0, 0, 0, 0, 0, 0]
-------------------------------------------
----- answer: 3 / x: 2 / y: 4 / visited ---------- 
[1, 1, 1, 0, 0, 0, 0, 0, 0, 1]
[1, 1, 1, 0, 1, 0, 0, 0, 0, 0]
[0, 1, 0, 1, 1, 1, 0, 0, 0, 0]
[0, 0, 1, 1, 1, 1, 0, 0, 0, 0]
[0, 1, 1, 1, 1, 0, 0, 0, 0, 0]
[0, 0, 1, 0, 0, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[1, 1, 0, 0, 0, 0, 0, 0, 0, 0]
-------------------------------------------
----- answer: 4 / x: 7 / y: 4 / visited ---------- 
[1, 1, 1, 0, 0, 0, 0, 0, 0, 1]
[1, 1, 1, 0, 1, 0, 0, 0, 0, 0]
[0, 1, 0, 1, 1, 1, 0, 0, 0, 0]
[0, 0, 1, 1, 1, 1, 0, 1, 0, 0]
[0, 1, 1, 1, 1, 0, 1, 1, 1, 0]
[0, 0, 1, 1, 0, 0, 0, 1, 0, 0]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[1, 1, 0, 0, 0, 0, 0, 0, 0, 0]
-------------------------------------------

"""