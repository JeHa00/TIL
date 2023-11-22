from typing import List

nums = [2, 7, 11, 15]

target = 9


def solution01(nums: List[int], target: int) -> List[int]:
    answers = {}

    for i, num in enumerate(nums):
        difference = target - num
        if difference in answers:
            return [answers[difference], i]

        answers[num] = i


def solution02(nums: List[int], target: int) -> List[int]:
    nums_with_index = []

    for i, num in enumerate(nums):
        nums_with_index.append((i, num))

    nums_with_index.sort(key=lambda x: x[1])

    left, right = 0, len(nums_with_index) - 1

    while left < right:
        if nums_with_index[left][1] + nums_with_index[right][1] > target:
            right -= 1

        elif nums_with_index[left][1] + nums_with_index[right][1] < target:
            left += 1

        else:
            return [left, right]


print(solution01(nums, target))
print(solution02(nums, target))
print(globals())

from timeit import timeit

t1 = timeit(
    "solution01(nums, target)",
    setup="from __main__ import solution01",
    number=1,
    globals=globals(),
)
t2 = timeit(
    "solution02(nums, target)",
    setup="from __main__ import solution01",
    number=1,
    globals=globals(),
)

print(t1)
print(t2)
 