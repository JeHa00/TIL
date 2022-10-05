# 이진 트리 탐색 클래스
# 노드 삽입 [5, 3, 8, 2, 4, 6, 9, 1, 7]
# 노드 검색 6, 10
# 노드 값 접근


# 노드 클래스
class Node:
    def init(self, value):
        self.value = value
        self.left = None
        self.right = None 
    
# 이진 탐색 트리 클래스 
class BST:
    def __init__(self):
        self.root = None
    
    def insert(self, value):
        if self.root == None:
            self.root = Node(value)