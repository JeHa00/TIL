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

    for i in range(1, n):
        j = i
        tmp = a[i]
        print('j: {}, tmp = {}'.format(j, tmp))
        while j > 0 and a[j - 1] > tmp:
            print('교환 전, j : {}, a[j - 1] : {}'.format(j, a[j - 1]))
            a[j] = a[j - 1]
            print('교환 후, a[j] = {}, a= {}'.format(a[j], a))
            j -= 1 
        a[j] = tmp
        print(a)
    

if __name__ == '__main__':

    num = int(input('배열의 원소 갯수: '))
    a = [None] * num 
    for i in range(num): 
        a[i] = int(input(f'a[{i}] = '))
    
    print('Before sorting: ', a)
    
    insertion_sort(a)
    
    print('After sorting: ', a)

