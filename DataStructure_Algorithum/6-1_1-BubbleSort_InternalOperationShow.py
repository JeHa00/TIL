from typing import MutableSequence

"""
Bubble sort의 과정이 보이도록 구현
"""


def bubble_sort(a: MutableSequence) -> None: 
    """bubble 정렬"""
    n = len(a)  
    ccnt = 0 # 비교 횟수
    scnt = 0 # 교환 횟수 
    for i in range(n - 1):
        exchaging = 0 
        print(f'패스 {i + 1}')
        for j in range(n - 1, i, -1): 
            for m in range(n - 1): # range(n - 1)인 이유: 마지막 a[n - 1]은 다르게 출력해서 end로 이어지는 걸 방지한다.  
                print(f'{a[m]:2}' + (' ' if m != j - 1 else '+' if a[j - 1] > a[j] else '-') ,end = ' ')
            
            print(f'{a[n - 1]:2}')
            ccnt += 1 
            if a[j - 1] > a[j]:
                scnt += 1
                a[j], a[j - 1] = a[j - 1], a[j]  
                exchaging += 1 
        for m in range(n - 1):
            print(f'{a[m]:2}', end = ' ')
        print(f'{a[n - 1]:2}')
        if exchaging == 0:
            break

    print(f'비교를 {ccnt}번 했습니다.')
    print(f'교환을 {scnt}번 했습니다.')

if __name__ == '__main__': 
    num = int(input('원하는 성분 갯수를 입력하세요. : '))
    a = [None] * num 
    for i in range(num):
        value = int(input(f'a[{i}] = '))
        a[i] = value 
    
    # a = [6, 4, 3, 7, 1, 9, 8]

    print('Before sorting: ', a)

    bubble_sort(a)
    print('After sorting: ' , a)