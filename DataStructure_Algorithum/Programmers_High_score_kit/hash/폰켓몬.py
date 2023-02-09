"""
[폰켓몬]
주어진 폰켓몬 N마리에서 N/2마리를 가져가는 총 경우의 수 구하기

입력값
- N마리의 폰켓몬 배열: nums

출력값
- N/2 마리의 폰켓몬을 선택하는 방법 중 가장 많은 종류의 폰켓몬을 선택하는 방법을 찾아
- 그 때의 폰켓몬 종류 번호의 최대 개수를 return 하기

제약사항
- nums는 폰켓몬의 종류 번호가 담긴 1차원 배열
- N은 1이상 10,000이하 자연수이며 항상 짝수다.
- 폰켓몬의 종류 번호는 1 이상 200,000 이하의 자연수 
"""
from typing import List

def solution(nums: List) -> int:
    """주어진 폰켓몬 목록을 받으면 최대로 가질 수 있는 경우의 수를 반환하는 함수

    Args:
        nums (List): 주어진 폰켓몬 목록

    Returns:
        int: 최대 경우의 수
    """

    max_count = len(nums) // 2
    phonketmon = dict()

    for num in nums:
        if phonketmon.get(num) == None:
            phonketmon[num] = 0
        else:
            phonketmon[num] += 1

    if len(phonketmon) >= max_count:
        return max_count
    else:
        return len(phonketmon)

if __name__ == "__main__":
    nums_list = [[3,1,2,3], [3,3,3,2,2,4], [3,3,3,2,2,2]]
    for nums in nums_list:
       print(solution(nums))
