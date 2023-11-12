from typing import List


def solution01(nums: List[int]) -> int:
    nums.sort()
    answer = 0
    for i in range(0, len(nums), 2):
        answer += min(nums[i], nums[i + 1])

    return answer


def solution02(nums: List[int]) -> int:
    return sum(sorted(nums)[::2])
