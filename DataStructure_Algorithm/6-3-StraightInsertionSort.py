"""
단순 삽입 정렬
"""

from typing import MutableSequence

def insertion_sort(a: MutableSequence) -> None:
    """
    단순 삽입 정렬: 주목한 원소보다 더 앞쪽에서 보다 적절한 위치로 삽입하는 알고리즘
    오름차순으로 정렬
    """
    n = len(a)

    for i in range(1, n + 1):
        j = i
        tmp = a[i]
        while j > 0 and a[j - 1] > tmp:
            a[j] = a[j - 1]
            j -= 1 
        a[j] = tmp

    

if __name__ == '__main__':

    num = int(input('배열의 원소 갯수: '))
    a = [None] * num 
    for i in range(num): 
        a[i] = int(input(f'a[{i}] = '))
    
    print('Before sorting: ', a)
    
    insertion_sort(a)
    
    print('After sorting: ', a)

