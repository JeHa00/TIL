from collections import deque

graph = [
    [], [2, 4, 7], [1, 3], [2, 6, 8], [1], 
    [7, 9], [3], [1, 5, 9], [3], [5, 7]
]

"""
[DFS]
- Depth-First Search
- stack 기반
"""

def dfs(graph, start):
    visited = [] 
    stack = [start]

    while stack:
        v = stack.pop() # stack이라서 pop

        if v not in visited: 
            visited.append(v)
            stack.extend(graph[v])
    
    return visited

print(dfs(graph, 1))


"""
[BFS]
- Breath-First Search
- queue 기반
"""

def bfs(graph, start): 
    visited = []
    queue = deque([start])

    while queue: 
        v = queue.popleft() # queue라서 popleft

        if v not in visited: 
            visited.append(v)
            queue.extend(graph[v])
    
    return visited

print(bfs(graph, 1))
