"""
[11729(Hanoi)]

N개의 서로 다른 원판이 첫 번째 장대에 피라미드처럼 쌓여있다.
두 번째 장대를 사용해서, 세 번째 장대에 똑같은 순서로 쌓이도록 옮겨라.
- 한 번에 한 개의 원판만을 다른 탑으로 옮길 수 있다.
- 쌓아 놓은 원판은 항상 위의 것이 아래의 것보다 작아야 한다.

입력값: N개 

출력값
- 수행과정을 출력하기: A B
- A번째 탑의 가장 위에 있는 원판을 B번째 탑의 가장위로 옮긴다.
"""
import sys

def hanoi_recursive(number_of_plates: int, start_pole: int, end_pole: int) -> str:

    # n개 -> (n-1)개를 A -> B로 옮김 
    if number_of_plates > 1:
        hanoi_recursive(number_of_plates-1, start_pole, 6-start_pole-end_pole)
    # hanoi_recursive(2, 1, 2)
    """
    1 / 1 -> 3 / 시작지점 -> 중간지점
    2 / 1 -> 2 / 시작지점 -> 종료 지점
    1 / 3 -> 2 / 중간지점 -> 종료지점
    """

    # 마지막 1개를 A->C로 옮김
    print(f"{start_pole} {end_pole}")

    # (n-1)개를 B->C로 옮김
    if number_of_plates > 1:
        hanoi_recursive(number_of_plates-1, 6 -start_pole-end_pole, end_pole)
    # hanoi_recursive(2, 2, 3)
    """
    1 / 2 -> 1 # 시작지점 -> 중간지점
    2 / 2 -> 3 # 시작지점 -> 종료지점
    1 / 1 -> 3 # 중간지점 -> 종료지점
    """

if __name__ == "__main__":
    N = int(sys.stdin.readline())
    """
    2 -> 3 = 2^(2) - 1
    3 -> 7 = 2^(3) - 1
    4 -> 15  = 2^(4) - 1
    5 -> 31 = 2^(5) - 1
    """
    move_counts = 2**(N) - 1 
    print(move_counts)
    hanoi_recursive(N, 1, 3)

