"""stack을 사용하여 재귀 함수 없이 구현하기"""


class stack():

    class Empty(Exception):
        '''stack이 비어있는데 pop 시, 사용하는 예외 처리'''
        pass

    class Full(Exception):
        '''stack이 가득 차 있는데 push 시, 사용하는 예외 처리'''
        pass

    def __init__(self, capacity: int) -> None:
        self.capacity = capacity # stack의 크기 
        self.stk = [None] * capacity # stack list 배열 형성
        self.ptr = 0  # stack에 쌓여 있는 데이터 수 
    
    def is_empty(self) -> bool: 
        '''stack이 비어있는지 판단'''
        return self.ptr <= 0 
    
    def is_full(self) -> bool: 
        '''stack이 가득 차 있는지 판단'''
        return self.ptr >= self.capacity 
    
    def push(self, value: int) -> None: 
        '''stack에 데이터 추가하기'''
        if self.is_full():
            raise stack.Full 
        else: 
            self.stk[self.ptr] = value 
            self.ptr += 1 

    def pop(self) -> int: 
        '''stack에 데이터 빼기'''
        if self.is_empty():
            raise stack.Empty
        else: 
            self.ptr -= 1 
            return self.stk[self.ptr]


def recur(x: int) -> int: 
    stk = stack(5)
    while True: 
        if x > 0: 
            stk.push(x)
            x = x - 1
            continue 
        if not stk.is_empty():
            x = stk.pop()
            print(x)
            x = x - 2
            continue 
        break 

recur(5)