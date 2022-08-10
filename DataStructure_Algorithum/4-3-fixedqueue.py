"""
고정 길이 큐 클래스를 ring buffer 개념을 사용하여 fixedQueue 구현하기
"""

from typing import Any

class FixedQueue():

    class Empty(Exception):
        """비어있는 FixedQueue에서 디큐 또는 피크할 때 나타내는 예외 처리"""
        pass

    class Full(Exception):
        """가득 차 있는 FixedQueue에서 인큐할 때 나타내는 예외 처리"""
        pass 

    def __init__(self, capacity: int) -> None: 
        self.no = 0 # 현재 데이터 갯수
        self.front = 0 # 맨 앞 원소 커서 
        self.rear = 0 # 맨 뒤 원소 커서 
        self.capacity = capacity
        self.queue = [None] * capacity

    def is_empty(self) -> bool:
        """queue가 비어 있는지 판단"""
        return self.no <= 0 

    def is_full(self) -> bool: 
        """queue가 가득 차 있는지 판단"""
        return self.no >= self.capacity

    def __len__(self) -> int:
        """queue의 데이터 갯수를 나타냄"""
        return self.no 


    def enque(self, x: Any) -> None:
        """데이터 x를 인큐"""
        if self.is_full():
            raise FixedQueue.Full  # 큐가 가득 찬 경우 예외처리를 발생
        self.queue[self.rear] = x
        self.rear += 1
        self.no += 1
        if self.rear == self.capacity:
            self.rear = 0

    def deque(self) -> int: 
        """dequeue 작업"""
        if self.is_empty():
            raise FixedQueue.Empty
        else:
            x = self.queue[self.front]
            self.front += 1 
            self.no -= 1 
            if self.front == self.capacity: 
                self.front = 0 
            return x 
    
    def peek(self) -> Any: 
        """q큐에서 데이터를 피크(맨 앞 데이터를 들여다 봄)"""
        if self.is_empty():
            raise FixedQueue.Empty
        return self.queue[self.front]

    def search(self, value: Any) -> Any:
        """queue 안에서 원하는 값의 유무를 나타냄"""
        """front에서 rear 방향으로 search"""
        for i in range(self.no):
            idx = (i + self.front)  
            if self.queue[idx] == value:
                return idx
        return -1 

    def count(self, value: int) -> int: 
        """queue 안에 원하는 value의 갯수를 나타냄"""
        if self.is_empty():
            raise FixedQueue.Empty
        else: 
            n = 0
            for i in range(self.no):
                idx = i + self.front
                if self.queue[idx] == value: 
                    n += 1
            return n 

    def __contains__(self, value) -> bool: 
        """queue 안에 원하는 값의 유무를 나타냄"""
        return self.count(value)

    def clear(self) -> None: 
        """큐의 모든 데이터를 비움"""
        self.no = self.front = self.rear = 0  

    def dump(self) -> Any: 
        """모든 데이터를 맨 앞부터 맨 뒤까지 출력""" 
        if self.is_empty():
            raise FixedQueue.Empty
        else:
            for i in range(self.no):
                print(self.queue[i + self.front], end = ' ')
            print()

from enum  import Enum

Menu = Enum('Menu', ['인큐', '디큐', '피크', '검색', '덤프', '종료'])

def select_menu():
    """메뉴 선택"""
    s = [f'({m.value}){m.name}' for m in Menu]
    while True:
        print(*s, sep = '    ')
        t = int(input('원하는 메뉴 선택: '))
        if 1 <= t <= len(Menu):
            return Menu(t)

qu = FixedQueue(5)

while True: 
    print(f'현재 데이터 개수: {qu.no}/{qu.capacity}')
    menu = select_menu()

    if menu == Menu.인큐:
        x = int(input('추가하고 싶은 정수 데이터 값을 입력하세요. : '))
        try: 
            qu.enque(x)
        except FixedQueue.Full:
            print('큐가 가득 찼습니다.') 


    elif menu == Menu.디큐:
        try:
            print(f'{qu.deque()} 값이 꺼내졌습니다.')
        except FixedQueue.Empty:
            print('큐가 비어있습니다.') 


    elif menu == Menu.피크:
        try:
            print(f'맨 앞 데이터는 {qu.peek()}입니다.')
        except FixedQueue.Empty:
            print('큐가 비어있습니다.') 
    
    elif menu == Menu.검색:
        x = int(input('찾고 싶은 정수 데이터 값을 입력하세요. : '))
        if x in qu: 
            print(f'찾으시는 값은 맨 앞에서 {qu.search(x)}번째에 첫 번째로 있고, 총 {qu.count(x)}개 있습니다.')
        else: 
            print('찾으시는 값이 존재하지 않습니다.')
    
    elif menu == Menu.덤프:
        try:
            qu.dump()
        except FixedQueue.Empty:
            print('큐가 비어있습니다.') 
        
    else:
        pass