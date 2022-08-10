from typing import MutableSequence


def binary_insertion(a: MutableSequence) -> None:
    """이진 삽입 정렬"""
   
    n = len(a)


    for i in range(1, n):
        
        key = a[i]
        pi = 0 
        pe = i - 1 
        print(f'패스 { i + 1}')

        while True:
            pc = (pi + pe) // 2 
            
            if a[pc] == key or pi > pe:
                break 

            elif a[pc] < key: 
                pi = pc + 1

            else:  
                pe = pc - 1

        pd = pc + 1 if pi <= pe else pe + 1

        for j in range(i, pd, -1):
            a[j] = a[j - 1]
        a[pd] = key

if __name__ == '__main__':

    num = int(input('배열의 원소 갯수: '))
    a = [None] * num 
    for i in range(num): 
        a[i] = int(input(f'a[{i}] = '))
    
    print('Before sorting: ', a)
    
    binary_insertion(a)
    
    print('After sorting: ', a)

    for i in range(num):
        print(f'a[{i}] = {a[i]}')

