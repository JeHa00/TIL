"""
[위장]
주어진 clothes 로 스파이가 위장할 수 있는 총 경우의 수를 구하기

clothes의 각 행은 [의상의 이름, 의상의 종류] 를 의미한다.  

https://school.programmers.co.kr/learn/courses/30/lessons/42578
"""
from typing import List


def solution(clothes: List) -> List:

    answer = 1

    cloth_dict = dict()
    for cloth in clothes:
        if cloth_dict.get(cloth[1]) == None:
            cloth_dict[cloth[1]] = []
            cloth_dict[cloth[1]].append(cloth[0])
        else:
            cloth_dict[cloth[1]].append(cloth[0])

    if len(cloth_dict) == 1:
        answer = len(clothes)
    else:
        for key in cloth_dict:
            answer *= len(cloth_dict[key])        
        answer += len(clothes)

    return answer

if __name__ == "__main__":
    clothes_list = [
        [["yellow_hat", "headgear"], ["blue_sunglasses", "eyewear"], ["green_turban", "headgear"]], 
        [["crow_mask", "face"], ["blue_sunglasses", "face"], ["smoky_makeup", "face"]],
        [["yellow_hat", "headgear"], ["crow_mask", "face"], ["blue_sunglasses", "eyewear"], ["crow_mask", "face"], ["green_turban", "headgear"]]
        ]
    for clothes in clothes_list:
        print(solution(clothes)) # 5, 3, 9