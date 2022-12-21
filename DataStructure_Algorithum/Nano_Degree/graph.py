"""
[graph]
- 정점(node)와 간선(edge)으로 구성된 비선형 자료구조다.  
    - 비선형의 의미: 1차원이 아닌 2차원으로 된 형태
- 방향 그래프: 간선이 방향을 가지는 그래프  
- 가중 그래프: 간선에 가중치를 부여한 그래프  
- 완전 그래프: 모든 정점이 연결된 그래프  
- 부분 그래프: 원본 그래프에서 일부 정점과 간선으로 이뤄진 그래프  
- 차수: 한 노드에 이어져 있는 간선의 수  
- 입력 차수, 출력 차수: 유향 그래프에서의 차수  
"""


## graph를 코드로 작성하는 방법 2가지
"""
node: A, B, C, D
"""

A, B, C, D = range(1, 5)

# 중첩 딕셔너리
node = {A, B, C, D}

edge = [{A, B}, {A, C}, {A, D}, {B, D}]


# 중첩 리스트
edge = [[A, B], [A, C], [A, D], [B, D]] # 존재하는 edge 갯수만큼 리스트가 생성

print(A in edge[0])
print(A in edge[2])
print(len(edge))


## 가중치가 있는 경우 
edge = [[B, C, D], [A, D], [A], [A, B]]

# 가중치가 있는 경우, node를 숫자로 표시하는 건 가독성 측면에서 좋지 않다.
edge_weight = [{B:3, C:1, D:7}, {A:3, D:2}, {A:1}, {A:7, B:2}]


## 인접 리스트: 연결된 부분만을 중첩 리스트 또는 딕셔너리를 사용하여 표현하는 방식

## 인접 행렬: 그래프의 연결 관계를 이차원 배열(중첩 리스트)로 표현하는 방식 (행렬)
# 인접 리스트와의 차이점: 연결되지 않은 부분도 인접 행렬에서는 전부 표현한다.
# 주의할 점: node의 값이 1번부터 시작하면 index는 0부터 시작한다. 그래서 이를 맞추기 위해서 한 칸씩 늘리는 방법도 있다.
edge = [[B, C, D], [A, D], [A], [A, B]]

edge = [[0, 1, 1, 1], [1, 0, 0, 1], [1, 0, 0, 0], [1, 1, 0, 0]]

edge_start_index_1 = [[], [0, 0, 1, 1, 1], [0, 1, 0, 0, 1], [0, 1, 0, 0, 0], [0, 1, 1, 0, 0]]

edge_weight = [{}, {B:3, C:1, D:7}, {A:3, D:2}, {A:3}, {A:7, B:2}]

print(B in edge_weight[A])
print(B in edge_weight[C])
print(len(edge_weight[A]))
print(len(edge_start_index_1[A]) - edge_start_index_1[A].count(0)) # 노드의 차수


edge_weight = [[0, 3, 1, 7], [3, 0, 0, 2], [1, 0, 0, 0], [7, 2, 0, 0]]


## 값을 사용자에게 입력받을 때
# 가중치 x
node = int(input('total amount of node: '))
edge = int(input('total amount of edge: '))

graph = [[] for _ in range(edge)]

for i in range(edge): 
    node1, node2 = map(int, input(f'link{i+1}: ').split())
    graph[i].append(node1)
    graph[i].append(node2)

print(graph)

# 가중치 o 

print('########## 가중치 o ##########')

graph = [{} for _ in range(node + 1)]

print(graph)

for i in range(edge):
    node1, node2, weight = map(int, input(f'link{i+1}: ').split())
    if node1 == node2 or node2 in graph[node1]: 
        continue
    else:
        graph[node1][node2] = weight
        graph[node2][node1] = weight

print(graph)


# 인접행렬 + 가중치 x

print('########## 인접 행렬 + 가중치 x ##########')

graph = [[0] * (node+1) for _ in range(node+1)]

for i in range(edge):
    node1, node2= map(int, input(f'link{i+1}: ').split())
    if node1 == node2 or node2 in graph[node1]: 
        continue
    graph[node1][node2] = 1
    graph[node2][node1] = 1

print(graph)


# 인접행렬 + 가중치 o (1 대신에 가중치를 입력)

print('########## 인접 행렬 + 가중치 o ##########')

graph = [[0] * (node+1) for _ in range(node+1)]

for i in range(edge):
    node1, node2, weight= map(int, input(f'link{i+1}: ').split())
    if node1 == node2 or node2 in graph[node1]:  
        continue
    graph[node1][node2] = weight
    graph[node2][node1] = weight

print(graph)