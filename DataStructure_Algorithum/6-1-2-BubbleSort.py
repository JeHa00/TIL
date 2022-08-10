from typing import MutableSequence

"""
이미 정렬된 부분은 비교하지 않고, 스캔 범위를 제한하여 버블 정렬 과정을 보여주는 알고리즘
"""

def bubble_sort(a: MutableSequence) -> None: 

    ccnt = 0 # 비교 횟수 
    scnt = 0 # 교환 횟수
    n = len(a) # n = 7
    k = 0
    i = 0 
    while k < n - 1: # k < 6
        last = n - 1  # last = 6
        print(f'패스 {i + 1}')
        for j in range(n - 1, k, -1): # range(6, 0, -1)
            for m in range(0, n - 1): 
                print(f'{a[m]}' + ('  ' if m != j - 1 else '+' if a[j - 1] > a[j] else '-'), end = ' ')
            print(f'{a[n - 1]:2}')
            ccnt += 1 
            if a[j - 1] > a[j]:
                a[j - 1], a[j] = a[j], a[j - 1]
                scnt += 1 
                last = j 
        k = last 
        for m in range(0, n - 1):
            print(f'{a[m]:2}', end = ' ')
        print(f'{a[n - 1]:2}')
        i += 1 
    print(f'비교를 {ccnt}번 했습니다.')
    print(f'교환을 {scnt}번 했습니다.')




if __name__ == '__main__':
    print('Bubble sort')
    num = int(input('원하는 성분 갯수: '))
    a = [None] * num
    
    for i in range(len(a)):
        a[i] = int(input(f'a[{i}] = '))
    
    print('Before sorting >', a)
    bubble_sort(a)
    print('After sorting >', a)
