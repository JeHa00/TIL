"""
[같은 숫자는 싫어]
https://school.programmers.co.kr/learn/courses/30/lessons/12906

주어지는 값인 arr 배열에서 중복값들만 제거하고, 순서는 동일한 배열을 반환하기
"""
from typing import List

def main01(arr:List) -> List:

    answer = [] 
    for element in arr: 
        if element not in answer:
            answer.append(element)
        else:
            if element != answer[-1]:
                answer.append(element)

    return answer


def main02(arr:List) -> List: 
    """
    main01보다 더 빠른 방식  
    """


    answer = []
    save_element = -1
    for element in arr:
        if save_element != element:
            save_element = element
            answer.append(save_element)
    return answer


if __name__=="__main__":
    arr_list = [[1,1,3,3,0,1,1], [4,4,4,3,3]] 
    for arr in arr_list:
        print(main01(arr))
        print(main02(arr))
