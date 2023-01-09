from typing import MutableSequence

def shaker_sort(a:MutableSequence): 
    """세이커 정렬"""
    ccnt = 0 # 비교 횟수
    scnt = 0 # 교환 횟수
    left = 0
    right = len(a) - 1
    n = len(a)
    last = right
    j = 0
    while left < right:
        print(f'패스 {j + 1}')
        j += 1 
        for i in range(right, left, -1):
            """홀수 패스: 작은 걸 맨 앞으로 보내는 패스"""
            for m in range(n - 1):
                print(f'{a[m]:2}' + (' ' if m != (i - 1) else '+' if a[i - 1] > a[i] else '-') , 
                        end = ' ')
            
            print(f'{a[n - 1]:2}')

            ccnt += 1
            if a[i - 1] > a[i]:
                scnt += 1
                a[i], a[i - 1] = a[i - 1], a[i]
                last = i 
        left = last 
        
        for m in range(0, n - 1): 
            print(f'{a[m]:2}', end = ' ')
        print(f'{a[n - 1]:2}')

        if left == right:
            break 

        print(f'패스 {j + 1}')
        j += 1 
        for i in range(left, right):
            """짝수 패스: 큰 걸 맨 뒤로 보내는 패스"""
            for m in range(n - 1):
                print(f'{a[m]:2}' + (' ' if m != (i - 1) else '+' if a[i - 1] > a[i] else '-') , 
                        end = ' ')
            
            print(f'{a[n - 1]:2}')


            ccnt += 1
            if a[i] > a[i + 1]:
                scnt += 1
                a[i], a[i + 1] = a[i + 1], a[i]
                last = i 
        right = last
        
        for m in range(0, n - 1): 
            print(f'{a[m]:2}', end = ' ')
        print(f'{a[n - 1]:2}')
    print(f'비교를 {ccnt}번 했습니다.')
    print(f'조회를 {scnt}번 했습니다.')



if __name__ == '__main__':
    num = int(input('원하는 성분 갯수: '))
    a = [None] * num 
    for i in range(num):
        x = int(input(f'a[{i}] = '))
        a[i] = x
    
    print('Before Sorting > ', a)
    shaker_sort(a)
    print('After Sorting > ', a)