class Stack():

    def __init__(self):
        self.stack = []
    

    def isEmpty(self):
        is_empty = False
        if len(self.stack) == 0:
            is_empty = True 
        return is_empty

    def push(self, data):
        self.stack.append(data)
    
    def pop(self):
        pop_object = None
        if self.isEmpty():
            print("Stack is Empty")
        else:
            pop_object = self.stack.pop()
        return pop_object
    
    def top(self):
        top_object = None
        if self.isEmpty():
            print('Stack is Empty')
        else:
            top_object = self.stack[-1]
        return top_object


mystack = Stack()

mystack.stack 
mystack.isEmpty()
mystack.push(10)
mystack.push(22)
mystack.push(333)
mystack.pop()
mystack.top()
print(mystack.stack)


class Node():
    def __init__(self, data):
        self.data = data
        self.next = None 

class SignlyLinkedStack():
    def __init__(self):
        self.head = None

# list와 달리 head가 None인 것으로 판단
    def isEmpty(self):
        is_empty = False 
        if self.head is None: 
            is_empty = True 
        return is_empty

    def push(self, data):
        new_node = Node(data)
        new_node.next = self.head
        self.head = new_node 


mystack01 = SignlyLinkedStack()
mystack01.push(1)
mystack01.push(2)
mystack01.push(3)

mystack01.head.data 

mystack01.head.next.data 

mystack01.head.next.next.data 