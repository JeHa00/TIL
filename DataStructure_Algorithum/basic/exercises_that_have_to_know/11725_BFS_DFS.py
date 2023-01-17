"""
[트리의 부모찾기 - 11725]
트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.

입력창
- 노드의 개수 N
- (N-1)개의 줄에 트리 상에서 연결된 두 정점이 주어진다
7
1 6
6 3
3 5
4 1
2 4
4 7

출력창
- 첫째 줄부터 (N-1)개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력
4
6
1
3
1
4
"""

import sys
from collections import deque


def findParentNode(counts_of_nodes: int):
    """
    bfs를 사용하여 푸는 문제
    - 시간 초과 발생
    """


    tree = [[] for _ in range(counts_of_nodes+1)]
    parent = [0 for _ in range(counts_of_nodes+1)]
    parent[1] = 1
    visited = []
    queue = deque([1])

    for _ in range(counts_of_nodes-1):
        node_A, node_B = map(int, sys.stdin.readline().split())
        tree[node_A].append(node_B)
        tree[node_B].append(node_A)


    # bfs
    while queue:
        visited_node = queue.popleft()
        if visited_node not in visited:
            visited.append(visited_node)
            queue.extend(tree[visited_node])
            for child_node in tree[visited_node]:
                if not parent[child_node]:
                    parent[child_node] = visited_node
                parent[child_node] = visited_node

    return parent



def findParentNode(counts_of_nodes: int):
    """
    bfs를 사용하여 푸는 문제
    - 불필요한 코드 실행을 삭제
    - bfs 구현 핵심은 queue임을 기억하자.
    """


    tree = [[] for _ in range(counts_of_nodes+1)]
    parent = [0 for _ in range(counts_of_nodes+1)]
    parent[1] = 1
    queue = deque([1])

    for _ in range(counts_of_nodes-1):
        node_A, node_B = map(int, sys.stdin.readline().split())
        tree[node_A].append(node_B)
        tree[node_B].append(node_A)


    # bfs
    while queue:
        visited_node = queue.popleft()
        # if visited_node not in visited:
            # visited.append(visited_node)
            # queue.extend(tree[visited_node])
        for child_node in tree[visited_node]:
            if not parent[child_node]:
                parent[child_node] = visited_node
                queue.append(child_node)

    return parent


if __name__ == "__main__":
    N = int(sys.stdin.readline())
    parent = findParentNode(N)
    for parent_node in parent[2:]: 
        print(parent_node)