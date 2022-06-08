"""
while 문으로 작성한 선형 검색 알고리즘  

원소 수와 각 원소 값 그리고, 찾고자하는 값을 input으로 입력하여 배열을 만든다. 

seq_search function을 통해서 선형 검색을 구현한다.  
"""

from typing import Sequence, Any 

def seq_search(a: Sequence, key: Any) -> int:
    """
    Sequence a에서 key와 값이 같은 원소를 선형 검색하여, 값을 찾을 경우 그 값의 인덱스를 반환하는 함수
    """
    
    """
    # 이를 for문으로 입력하면 다음과 같다. 
    for i in range(len(a)):
            if a[i] == key:
                return i
    return -1 
    """
    

    i = 0

    while True: 
    
        if i == len(a):
            return -1

        if a[i] == key:
            return i

        i += 1 

if __name__ == '__main__': 

    num = int(input('원하는 원소 갯수를 입력하세요. : '))


    """
    x = []  
    
    for _ in range(num):
        k = int(input('원하는 값을 입력하세요. : '))
        x.append(k)
    
    이 방법보다 아래 방법이 보다 직관적이다.  
    """
        
    # 원하는 갯수 만큼의 인덱스가 만들어진다.
    x = [None] * num   
    
    # range는 integer가 있는 것을 인자로 받는다.  
    for i in range(num):
        x[i] = int(input('원하는 값을 입력하세요. : '))

    ky = int(input('찾고자 하는 값을 입력하세요. : '))


    idx = seq_search(x, ky)  

    if idx == -1:
        print('Don\' find it')
    
    else:
        print('The value is in x[{}]'.format(idx))
