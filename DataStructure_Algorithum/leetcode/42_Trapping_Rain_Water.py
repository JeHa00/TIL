from typing import List


def solution01(height: List[int]) -> int:
    """
    시간 초과 발생한 솔루션
    """

    max_height = max(height)
    current_height = 1

    answer = 0

    while current_height <= max_height:  # 100000
        start_bar_location, end_bar_location = -1, -1

        for location, bar_height in enumerate(height):
            if bar_height >= current_height:
                if start_bar_location == -1 and end_bar_location == -1:
                    start_bar_location = location
                elif start_bar_location != -1 and end_bar_location == -1:
                    end_bar_location = location
                else:
                    start_bar_location = location
                    end_bar_location = -1

                if end_bar_location != -1:
                    answer += end_bar_location - start_bar_location - 1
                    start_bar_location, end_bar_location = end_bar_location, -1

        current_height += 1
    return answer


def solution02(height: List[int]) -> int:
    if not height:
        return 0

    volume = 0

    left, right = 0, len(height) - 1
    left_max, right_max = height[left], height[right]

    while left < right:
        left_max, right_max = max(height[left], left_max), max(height[right], right_max)

        if left_max <= right_max:
            volume += left_max - height[left]
            left += 1
        else:
            volume += right_max - height[right]
            right -= 1

    return volume


height = [1, 2, 0, 3, 5, 3]


def solution03(height: List[int]) -> int:
    stack = []
    volume = 0

    for i in range(len(height)):
        while stack and height[i] > height[stack[-1]]:
            top = stack.pop()  # 0

            if not stack:
                break

            distance = i - stack[-1] - 1
            waters = min(height[i], height[stack[-1]]) - height[top]

            volume += distance * waters

        stack.append(i)
    return volume


heights = [
    [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1],
    [4, 2, 0, 3, 2, 5],
]

for height in heights:
    print(solution01(height))
    print(solution02(height))
