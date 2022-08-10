def factorial(x):
    """양의 정수 n의 팩토리얼 값을 재귀적으로 구함"""
    if x > 0:
        return x * factorial(x-1)
    else:
        return 1 

print(factorial(5))


def gcd(x: int, y: int) -> int: 
    """유클리드 호제법를 재귀적으로 구현"""
    if y == 0:
        return x 
    else:
        return gcd(y, x % y)


print(gcd(22, 8))

print()

def recur(n: int) -> int:
    """순수한 재귀 함수 구현하기"""
    if n > 0:
        recur(n-1)
        print(n)
        recur(n-2)

recur(5)


print()


# 꼬리 재귀를 제거한 알고리즘: while 문 사용
def recur(n: int) -> int: 
    """순수한 재귀 함수에서 꼬리 재귀를 제거한 경우"""
    while n > 0:
        recur(n-1) 
        print(n)
        n = n - 2 

recur(5)

print()


# 꼬리 재귀를 제거한 알고리즘: if 문 사용 
def recur(n: int) -> int: 
    """순수한 재귀 함수에서 꼬리 재귀를 제거한 경우"""
    if n > 0:
        recur(n-1) 
        print(n)
        n = n - 2 

recur(5)

print()


#스택으로 재귀 함수 구현하기 

class Stack():

    class Empty(Exception):
        """pop 시, 스택이 비어있는 상황을 나타냄"""
        pass

    class Full(Exception):
        """push 시, 스택이 가득 찬 상황을 나타냄"""
        pass

    def __init__(self, capacity: int) -> None:
        """초기화"""
        self.capacity = capacity
        self.stk = [None] * capacity
        self.ptr = 0 
    
    def is_empty(self):
        """stack이 비어있는 상황인지를 판단"""
        return self.ptr <= 0

    def is_full(self):
        """stack이 가득 차 있는 상황인지를 판단"""
        return self. ptr >= self.capacity 
    
    def push(self, value: int) -> None: 
        """stack에 추가하기"""
        if self.is_full():
            raise Stack.Full
        else:
            self.stk[self.ptr] = value
            self.ptr += 1 
    
    def pop(self) -> int: 
        """stack에서 pop 제거"""
        if self.is_empty():
            raise Stack.Empty 
        else:
            self.ptr -= 1 
            return self.stk[self.ptr]

def recur(n: int) -> int:
    stk = Stack(n)
    while True: 
        if n > 0: 
            stk.push(n)
            n = n - 1
            continue
        elif not stk.is_empty():
            n = stk.pop()
            print(n)
            n = n - 2 
            continue 
        break 

x = int(input('정숫값을 입력하세요.: ')) 

recur(x)


    
    
