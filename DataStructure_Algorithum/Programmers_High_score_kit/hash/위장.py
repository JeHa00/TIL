"""
[위장]
주어진 clothes 로 스파이가 위장할 수 있는 총 경우의 수를 구하기

clothes의 각 행은 [의상의 이름, 의상의 종류] 를 의미한다.  

https://school.programmers.co.kr/learn/courses/30/lessons/42578
"""
from typing import List


def solution(clothes: List) -> List:

    answer = 1
    hash_map = dict()
    for _, cloth_type in clothes:
        """
        if cloth_type not in hash_map:
            hash_map[cloth_type] = 1
        else:
            hash_map[cloth_type] += 1

        위 코드보다 아래 코드 한 줄로 작성할 수 있다. 
        """
        hash_map[cloth_type] = hash_map.get(cloth_type, 0) + 1
    
    # 입지 않는 경우를 추가하여 모든 조합 계산하기  
    for type_count in hash_map.values():
        answer *= (type_count + 1)       

    # 아무 종류의 옷도 입지 않는 경우는 제외하기  
    return answer - 1

if __name__ == "__main__":
    clothes_list = [[["yellow_hat", "headgear"], ["blue_sunglasses", "eyewear"], ["green_turban", "headgear"]], 
        [["crow_mask", "face"], ["blue_sunglasses", "face"], ["smoky_makeup", "face"]]]
    for clothes in clothes_list:
        print(solution(clothes))