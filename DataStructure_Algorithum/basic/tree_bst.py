"""
[tree]

- 그래프 중 노드가 단 하나의 부모에 의해서 참조되는 형태의 그래프
- 부모 노드: 노드에서 자신을 참조할 수 있는 바로 위 상위 노드
- 자식 노드: 부모 노드에 의해서 참조되는 노드
- 루트 노드: 부모가 없는 최상위 노드로 트리 전체에서 단 하나만 존재  

- 노드가 순환이 되거나, 상위 노드가 2개이면 tree가 아니다.
- 노드가 끊어져 있으면 tree가 아니다.

[순회]
- 전위 순회: 노드 - 왼쪽 - 오른쪽
- 중위 순회: 왼쪽 - 노드 - 오른쪽  
- 후위 순회: 왼쪽 - 오른쪽 - 노드
"""



class Node:
    def __init__(self, value):
        self.value = value 
        self.left =  None 
        self.right = None

# 전위 순회
def preorder(node):
    if node == None: 
        return 

    print(node.value, end ='  ') # 노드
    preorder(node.left) # 왼쪽
    preorder(node.right) # 오른쪽

# 중위 순회
def inorder(node):
    if node == None: 
        return 

    inorder(node.left) # 왼쪽
    print(node.value, end ='  ') # 노드
    inorder(node.right) # 오른쪽


# 후위 순회
def postorder(node):
    if node == None: 
        return 

    postorder(node.left) # 왼쪽
    postorder(node.right) # 오른쪽
    print(node.value, end ='  ') # 노드


root_node = Node("A")
node2 = Node("B")
node3 = Node("C")
node4 = Node("D")
node5 = Node("E")
node6 = Node("F")
node7 = Node("G")

root_node.left = node2
root_node.right = node3


print(root_node.__dict__)

node2.left = node4
node2.right =  node5

print(node2.__dict__)

node3.left = node6
node3.right =  node7

print(node3.__dict__)

preorder(root_node)
print()
inorder(root_node)
print()
postorder(root_node)
print()


"""
[BST: 이진 트리] 
- 노드가 정렬된 순서를 유지하는 이진 트리

- 각 노드의 왼쪽 하위 값은 부모 노드 값보다 작고, 
- 각 노드의 오른쪽 하위 값은 부모 노드 값보다 크다.  

- 데이터의 삽입, 검색에 효율적인 구조다.  

[BST - 생성]
- 첫 번째 값: 루트 노드 
- 비교 작업을 하다가 비교할 노드가 없으면 그 자리에 생성

[BST - 검색]
- 차례대로 삽입된 상태에서 value 값 검색  
- 루트부터 현재 노드의 값과 value를 비교하여 같으면 True
- 비교하여 다르면 left, right node로 이동  
- 더 이상 비교할 노드가 없으면 False 리턴
"""
print('##### BST #####')

class BST:
    def __init__(self):
        self.root = None

    def insert(self, value):
        if self.root == None: 
            self.root = Node(value)
        else:
            self.current_node = self.root
            while True:
                if self.current_node.value > value:
                    if self.current_node.left == None: 
                        self.current_node.left = Node(value)
                        print(self.current_node.left.value, end = ' ')
                        break
                    else:
                        self.current_node = self.current_node.left

                if self.current_node.value < value:
                    if self.current_node.right == None: 
                        self.current_node.right = Node(value)
                        print(self.current_node.right.value, end = ' ')
                        break
                    else:
                        self.current_node = self.current_node.right

    def search(self, value):
        if self.root == None:
            return False
        else: 
            self.current_node = self.root
            while True: 
                if value == self.current_node.value:
                    return f"True {id(self.current_node.value)}"
                else: 
                    if value < self.current_node.value:
                        if self.current_node.left == None: 
                            return False
                        elif value == self.current_node.left.value:
                            return True 
                        else:
                            self.current_node = self.current_node.left

                    else: 
                        if self.current_node.right == None: 
                            return False
                        elif value == self.current_node.right.value:
                            return True 
                        else:
                            self.current_node = self.current_node.right



bst = BST() 

input_value_list = [5, 3, 8, 2, 4, 6, 9, 1, 7]
for value in input_value_list:
    bst.insert(value)

print()
print(bst.search(6))
print(bst.search(12))