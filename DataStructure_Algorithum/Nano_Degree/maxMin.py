from typing import List

# 최소값 구하기


def findMinValue(value: List) -> int:

    min_value = value[0]
    for i in value:
        if i < min_value:
            min_value = i
        else:
            continue

    print(f"최솟값: {min_value}")

# 최대값 구하기


def findMaxValue(value: List) -> int:
    max_value = value[0]
    for i in value:
        if i > max_value:
            max_value = i
        else:
            continue

    print(f"최댓값: {max_value}")


if __name__ == "__main__":
    numbers = list(map(int, input().split()))
    findMinValue(numbers)
    findMaxValue(numbers)
