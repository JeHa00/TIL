from functools import reduce
from typing import List

nums_list = [
    [1, 2, 3, 4],
    [-1, 1, 0, -3, 3],
    [0, 0],
]


def solution(nums: List[int]) -> List[int]:
    p = 1
    out = []

    for i in range(len(nums)):
        out.append(p)
        p = p * nums[i]

    p = 1

    for i in range(len(nums) - 1, -1, -1):
        out[i] = out[i] * p
        p = p * nums[i]

    return out


for nums in nums_list:
    print(solution(nums))
