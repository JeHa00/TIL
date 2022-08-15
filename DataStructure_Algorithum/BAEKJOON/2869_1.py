"""
[problem: 2869]

- 문제
    - 달팽이가 나무 막대를 다 올라가는데 걸리는 날 수
    - 달팽이는 V meter의 나무 막대를 올라간다.  
    - 달팽이는 낮에 A meter 올라가고, 밤에 자는 동안 B meter 만큼 떨어진다. 
    - V meter에 도착하면 밤이어도 내려가지 않는다. 

- 입력값
    - A, B, V

- 출력값
    - 요일  
"""


def getDays(up: int, down: int, total_distance: int) -> int:

    day = total_distance // up
    present_distance = day * (up - down)

    while True:
        day += 1
        present_distance += up
        # print(f"day: {day}")
        # print(f"present_distance at afternoon: {present_distance}")
        if present_distance >= total_distance:
            print(day)
            break
        else:
            pass
        present_distance -= down
        # print(f"present_distance at night: {present_distance}")


if __name__ == "__main__":
    A, B, V = list(map(int, input().split()))
    getDays(A, B, V)
