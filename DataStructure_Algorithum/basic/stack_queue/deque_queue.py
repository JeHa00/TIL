# deque으로 queue 만들기
# size(), isEmpty(), enqueue(), dequeue(), front()
# enqueue(7), enqueue(5), enqueue(3), enqueue(2), dequeue(), dequeue(), enqueue(4)


from collections import deque 


def size():
    print(len(que))

def isEmpty():
    if len(que) == 0: 
        return True 
    return False

def enqueue(n):
    que.append(n)

def dequeue():
    if isEmpty():
        return 
    n = que[0]
    que.popleft()
    return n  

def front():
    if isEmpty():
        return 
    return que[0]

que = deque() 

enqueue(7)
enqueue(5)
enqueue(3)
enqueue(2)
dequeue()
dequeue()
enqueue(4)


print(que)