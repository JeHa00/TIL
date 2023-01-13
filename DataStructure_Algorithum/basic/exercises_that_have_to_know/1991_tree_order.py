"""
트리 순횐 (1991)
- 이진 트리를 입력받아 전위 순회(preorder traversal), 중위 순회(inorder traversal), 후위 순회(postorder traversal)한 결과를 출력하세요.

입력값
- 노드의 갯수
- 갯수만큼 순서대로 A부터 알파벳 대문자로 입력 (A는 항상 루트 노드)

출력값
- 전위 순회, 중위 순회, 후위 순회
"""

import sys

def preorder(node):
    """
    전위 순회
    """
    if node != '.':
        print(node, end = '')
        preorder(tree[node][0])
        preorder(tree[node][1])

def inorder(node):
    """
    중위 순회
    """
    if node != '.':
        inorder(tree[node][0])
        print(node, end='')
        inorder(tree[node][1])

def postorder(node):
    """
    후위 순회
    """
    if node != '.':
        postorder(tree[node][0])
        postorder(tree[node][1])
        print(node, end='')

if __name__ == "__main__":
    counts_of_node = int(sys.stdin.readline())
    tree = {}
    for _ in range(counts_of_node):
        node, left, right = sys.stdin.readline().split()
        tree[node] = [left, right]
    
    preorder('A')
    print()
    inorder('A')
    print()
    postorder('A')