# deque: double ended queue의 약자로 데이터의 양 끝 모두 삽입 및 삭제가 가능하다.  

# 스택, 큐 모두 활용 가능하다.

# append(x), appendleft(x),pop(), popleft() 등 메소드 활용이 편리하다. 

# 파이썬에서는 덱 모듈을 활용하여 덱을 구현할 수 있다. -> 값의 삽입, 삭제 속도가 바르다. 


"""

- append(x): 덱의 가장 오른족에 x 추가

- appendleft(x): 덱의 가장 왼쪽에 x 추가

- pop(): 덱의 가장 오른쪽 값 삭제

- popleft(): 덱의 가장 왼쪽 값 삭제

- extend(iterable): 덱의 오른쪽에 배열 추가

- extendleft(iterable): 덱의 왼쪽에 배열이 뒤집혀져서 추가

- insert(i, x): i위치에 x 추가

- remove(x): x 삭제

- coumt(x): x의 개수 반환

- index(x): x의 위치 반환

- clear(): 전체 삭제

- reverse(): 전체 역순

- len(iterable): 배열의 개수

- sum(iterable): 배열의 합

- max(iterable): 전체 요소 중 최대값

- min(iterable): 전체 요소 중 최소값  

"""


from collections import deque 

deq = deque([4, 5, 6])

deq.append(7)
deq.appendleft(3)
print(deq)

deq.pop()
deq.popleft()
print(deq)

li = [1, 2, 3]
deq.extend(li)
print(deq)
deq.extendleft(li) # 순서가 하나씩 뒤집혀져서 왼쪽에 추가된다. 
print(deq)


print(f"insert, remove")
deq = deque([4, 5, 6, 4])
deq.insert(0, 0)
print(f"insert: {deq}")
deq.remove(0)
print(f"remove: {deq}")

print(f"count: {deq.count(4)}, index: {deq.index(5)}")
deq.reverse()
print(f"reverse: {deq}")


deq = deque([4, 5, 6])

print(f"len: {len(deq)} / sum: {sum(deq)} / max: {max(deq)} / min: {min(deq)}")

deq.clear()

print(deq)


