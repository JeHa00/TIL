from typing import MutableSequence


def bubble_sort(a: MutableSequence) -> None: 
    """bubble 정렬"""
    n = len(a) 
    for j in range(n):
        for i in range(n - 1, j + 1, -1):
            if a[i - 1] > a[i]:
                a[i], a[i - 1] = a[i - 1], a[i]  
            print(a[i], a[i - 1])




if __name__ == '__main__': 
    num = int(input('원하는 성분 갯수를 입력하세요. : '))
    a = [None] * num 
    for i in range(num):
        value = int(input(f'a[{i}] = '))
        a[i] = value 
    
    print('Before sorting: ', a)

    bubble_sort(a)
    print('After sorting: ' , a)