# 이진 노드 클래스
# 전위, 중위, 후위

class Node:
    def __init__(self, value):
        self.value = value 
        self.left = None 
        self.right = None

def preorder(node):
    if node == None: 
        return 
    print(node.value, end ='  ')
    preorder(node.left)
    preorder(node.right)

def inorder(node):
    if node == None: 
        return 
    inorder(node.left)
    print(node.value, end ='  ')
    inorder(node.right)

def postorder(node):
    if node == None: 
        return 
    postorder(node.left)
    postorder(node.right)
    print(node.value, end ='  ')


node1 = Node("A")
node2 = Node("B")
node3 = Node("C")
node4 = Node("D")
node5 = Node("E")
node6 = Node("F")
node7 = Node("G")

node1.left = node2
node1.right = node3


print(node1.__dict__)

node2.left = node4
node2.right =  node5

print(node2.__dict__)

node3.left = node6
node3.right =  node7

print(node3.__dict__)

preorder(node1)
print()
inorder(node1)
print()
postorder(node1)