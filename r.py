from collections import deque, OrderedDict
import sys

s = sys.stdin.readline().strip()
t = sys.stdin.readline().strip()

answer = OrderedDict()

for char in t:
    answer.update({char: 0})

orderedDict, queue = OrderedDict(), deque()
anagram = ""
count, lp = 0, 0

for char in s:
    orderedDict.update({char : 0})
    queue.append(char)

    while len(queue) > len(answer):
        queue.popleft()
        lp += 1 

    if len(orderedDict) == len(answer):
        result = True
        for key in answer:
            if key not in orderedDict:
                result = False
        
        orderedDict.pop(s[lp])

        if result:
            count += 1
    

print(count)

