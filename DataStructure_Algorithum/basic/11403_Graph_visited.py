"""
[11403 - 경로찾기]
가중치 없는 방향 그래프 G가 주어졌을 때, (i, j)에 대하여 
i에서 j로 가는 경로가 있는지 없는지 구하는 프로그램을 작성하시오.

입력값
- 정점의 갯수: N
- 갯수만큼 정점의 연결 유무 입력

출력값
- 행렬로 연결 유무 출력

0 0 0 1 0 0 0
0 0 0 0 0 0 1
0 0 0 0 0 0 0
0 0 0 0 1 1 0
1 0 0 0 0 0 0
0 0 0 0 0 0 1
0 0 1 0 0 0 0
"""

import sys

def findPathFail(number_of_nodes: int):
    """
    이 방식은 직접적으로 바로 연결된 경로만 확인할 수 있다. 즉, 다른 노드를 걸쳐서 가는 경우는 확인할 수 없다.
    """

    nodes = []
    paths = [ [0 for _ in range(number_of_nodes)] for _ in range(number_of_nodes)]
    
    # 각 node 와 연결된 node 연결 유무 입력
    for _ in range(number_of_nodes):
        node = list(map(int, sys.stdin.readline().split()))
        nodes.append(node)

    # 위 입력값을 바탕으로 걸쳐서 가지 않고, 바로 가는 것만 입력한 인접 행렬 생성 
    for start_node in range(number_of_nodes):
        for end_node in range(number_of_nodes):
            if nodes[start_node][end_node] == 1:
                paths[start_node][end_node] = 1
                # 방향 그래프이므로 paths[y_ord][x_ord] = paths[x_ord][y_ord]은 하지 않는다.

    # 인접 행렬 출력하기
    for path in paths:
        for i, edge in enumerate(path):
            if i == len(path) - 1:
                print(str(edge))
            else: 
                print(str(edge), end = ' ')


def visited(edges, start, check):
    for end in range(1, number_of_nodes+1): 
        if check[end] == 0 and edges[start][end] == 1:
            check[end] = 1 
            visited(edges, end, check)


def thirdFindPath(number_of_nodes: int): 
    """
    visited 를 사용해서 돌아서 갈 수 있는 Node도 체크한다.
    """
    edges = [] 
    edges.append([]) # node를 index 1부터 시작하기 위해서

    for _ in range(number_of_nodes):
        edge = list(map(int, sys.stdin.readline().split()))
        # edges.extend([0] + edge)
        edges.append([0] + edge)

    print("answer")

    for start_node in range(1, number_of_nodes+1):
        check = [0 for _ in range(number_of_nodes+1)]
        visited(edges, start_node, check)
        check.remove(0)
        print(*check)


if __name__ == "__main__":
    number_of_nodes = int(sys.stdin.readline())
    # print('findPathFail')
    # findPathFail(number_of_nodes)
    print('thirdFindPath')
    thirdFindPath(number_of_nodes)



