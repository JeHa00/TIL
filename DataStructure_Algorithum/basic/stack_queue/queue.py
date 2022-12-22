# 선입선출 구조 

# 데이터 삽입: 인큐 / 데이터 제거: 디큐

# 큐 모듈을 활용하여 큐 구현

# 스택과 달리 밑에가 뚤려 있다. 


import queue 

def size():
    print(que.qsize())

def isEmpty():
    return que.empty()

def enqueue(n):
    que.put(n)

def dequeue():
    return queue.get() 

def front():
    return que.queue[0]

que = queue.Queue()

enqueue(7)
enqueue(5)
enqueue(3)
enqueue(2)
dequeue()
dequeue()
enqueue(4)