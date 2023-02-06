"""
[카카오 코딩테스트 2021 - 메뉴 리뉴얼]

<문제 상황>
단품으로 제공되는 걸 코스요리로 제공할려고 한다. 그래서 이전에 손님들이 함께 주문한 단품 내역들을 확인해본다.
- 코스요리이기 때문에 단품 메뉴를 최소 2가지로 구성하기로 한다.  
- 그리고 최소 2명 이상의 손님으로부터 주문된 조합에 대해 코스 요리에 포함시키려고 한다.

<입력값>
각 손님들이 주문한 단품 메뉴들이 문자열 형식으로 담긴 배열 orders, 
"스카피"가 추가하고 싶어하는 코스요리를 구성하는 단품메뉴들의 갯수가 담긴 배열 course,
위 두 개의 배열이 주어질 때, "스카피"가 새로 추가하게 될 코스요리의 메뉴 구성을 문자열 형태로 배열에 담아 return 하도록 solution 함수를 완성하세요.
"""
from itertools import combinations
from collections import Counter

def solution(orders: list, course: list) -> list:
    """order와 course를 추천할 코스요리를 반환하는 함수

    Args:
        order (list): 각 손님들이 주문한 단품 메뉴들이 문자열 형식으로 담긴 배열
        course (list): 추가하고 싶은 코스 요리를 구성하는 단품 메뉴들의 갯수가 담긴 배열

    Returns:
        list: 새로 추가하게 될 코스 요리의 메뉴 구성을 문자열 형태로 배열에 담아 반환
    """
    
    # 1) for 문을 사용하여 all_menu 리스트에 모든 orders의 조합 구하기

    answer = []
    for food_count in course:
        all_menus = []
        for order in orders:
            # all_menus.append(combinations(sorted(order), food_count))
            all_menus += combinations(sorted(order), food_count)
            """
            += 연산자와 append로 list에 추가하는 건 다른 결과를 가진다. 
            예를 들어보자.
            += 연산자로 추가할 경우, [('XYZ', 'XWY'), ('XYZ', 'WXA'), ('XWY', 'WXA')] 
            append로 추가할 경우, [<itertools.combinations object at 0x7fb61814d310>]
            combinations(sorted(order), food_count)를 list()에 집어넣은 결과가 += 연산자를 사용한 것과 동일하다. 또한, 그 이후에 추가되는 것들도 list가 아닌 tuple 성분들로 추가된다.
            +=는 기존에 두 리스트를 합쳐서 새로운 리스트를 만드는 것을 알고 있었으나, 이처럼 다른 데이터 타입인 경우에도 자동적으로 list로 형 변환하여 추가되는 것은 이번 문제를 통해 알게 되었다. 

            만약 append로 이를 구현할려면 코드를 어떻게 작성해야할까? 아래와 같이 작성하면 동일한 결과가 나온다.
            for combination in list(combinations(sorted(order), food_count)):
                all_menus.append(combination)
            """


    # 2) set_menu = Counter(all_menu)로 all_menu의 조합 중복 횟수 구하기
        set_menus = Counter(all_menus)

    # key = 세트 메뉴, value = 중복 수
    # 3) for 문을 사용하여 enumerate(set_menu) 반복하여 max(set_menu.value()) 값을 찾아 해당 key를 answer에 추가
        if set_menus:
            if max(set_menus.values()) >= 2: 
                for _, key in enumerate(set_menus):
                    if set_menus[key] == max(set_menus.values()):
                        answer.append(''.join(key))
    result = sorted(answer)
    
    return result


if __name__ == "__main__":
    total_orders = [["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"], ["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"], ["XYZ", "XWY", "WXA"]]
    total_course = [[2,3,4], [2,3,5], [2,3,4]]
    for orders, course in zip(total_orders, total_course):
        print(solution(orders, course))