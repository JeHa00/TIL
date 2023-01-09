"""
단순 선택 정렬 알고리즘
"""

from typing import MutableSequence

def Straight_Selection(a) -> None:
    n = len(a)
    for i in range(n):
        min = i # 가장 작은 원소의 인덱스를 i로 가정하여 진행
        for j in range(i + 1, n):
            if a[j] < a[min]:
                min = j # 더 작은 원소가 나타나면 이를 min 변수에 저장
        a[min], a[i] = a[i], a[min]
    

if __name__ == '__main__':
    num = int(input('원하는 성분 갯수를 입력하세요. : '))
    a = [None] * num 
    for i in range(num):
        value = int(input(f'a[{i}] = '))
        a[i] = value 
    
    print('Before sorting: ', a)

    Straight_Selection(a)
    print('After sorting: ' , a)