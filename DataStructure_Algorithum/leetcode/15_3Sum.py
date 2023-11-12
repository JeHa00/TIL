from typing import List

nums_list = [
    [-1, 0, 1, 2, -1, -4],
    [0, 0, 0, 0],
    [1, -1, -1, 0],
    [-2, 0, 1, 1, 2],
]


def solution01(nums: List[int]) -> List[List[int]]:
    answers = []
    nums.sort()

    for i, v in enumerate(nums):
        if i > 0 and nums[i] == nums[i - 1]:
            continue

        left, right = i + 1, len(nums) - 1

        while left < right:
            sum = v + nums[left] + nums[right]

            if sum > 0:
                right -= 1
            elif sum < 0:
                left += 1
            else:
                answers.append([v, nums[left], nums[right]])

                while left < right and nums[left] == nums[left + 1]:
                    left += 1
                while left < right and nums[right] == nums[right - 1]:
                    right -= 1

                left += 1
                right -= 1
    return answers


if __name__ == "__main__":
    for i, nums in enumerate(nums_list):
        if i == 0:
            assert solution01(nums) == [[-1, -1, 2], [-1, 0, 1]]
        elif i == 1:
            assert solution01(nums) == [[0, 0, 0]]
        elif i == 2:
            assert solution01(nums) == [[-1, 0, 1]]
        else:
            assert solution01(nums) == [[[-2, 0, 2], [-2, 1, 1]]]
