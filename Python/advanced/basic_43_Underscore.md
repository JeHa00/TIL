# 0. Introduction

> 1. [Python underscore 활용](#1-python-underscore-활용)
> 2. [Access modifier](#2-naming-mangling-access-modifier)

<br>

---

# 1. Python underscore 활용

- Interpreter 내에서의 마지막 값

```yml
> 2 + 3
5

> _ + 5
10
```

- 특정값을 무시하는 용도

```yml
> x, _, y = (1, 2, 3)


# Unpacking에 사용
> a, *_, b = (1, 2, 3, 4, 5)

> print(x, y, a, b)
1 3 1 5

# Error가 뜨지 않는다.
> for _ in range(10):
>   pass

# 각 index에 대응하는 value만 출력
> for _, val in enumerate(range(10)):
>   print(val, end = '')
0123456789
```

- magic method 사용 시, double underscore를 사용한다.

  - magic method는 special method 또는 Dunder(Double underscore) method와 동의어다.
  - [[TIL] Python basic 27: Special Method](https://jeha00.github.io/post/python_basic/python_basic_27_specialmethod/)

- Access modifier 접근 지정자로 사용된다.
  - 이 접근 지정자 private에서 **_Naming Mangling_** 이 발생된다.

<br>

---

# 2. Access modifier

> 접근 지정자라 하며, 정보 은익(Information Hiding)을 위해 사용된다. 즉, class의 attribute, method에 대해 접근을 제어할 수 있는 기능이다.

- Public -> Protected -> Private 단계로 접근할 수 있는 대상이 좁아진다.
- underscore를 통해 각 대상의 범위를 지정한다.

  - `name`: public
  - `_name`: protected
  - `__name`: private

- Python에서는 약속된 규약에 따라 자유도와 책임감을 가지고 코딩하는 것을 장려한다.

- 그러면 Public, Protected, Private에 대해 각각 알아보자.

<br>

## 2.1 Public

> - public으로 선언된 attribute, method는 어떤 클래스라도 접근 가능하다.
> - Python에서 모든 attribute, method는 기본적으로 **public** 이다.

- 클래스 외부에서 attribute, method가 접근 가능하기 때문에 사용 가능하다.
- underscore는 없다.

```yml
> class kim:
# constructor
>     def __init__(self, name, age):
>         self.kimname = name
>         self.kimage = age

>     # public member function
>     def display_age(self):

>         # accessing public data member
>         print("Age: ", self.kimage)

# creating instance of the class
> inst = kim('king', 30)

# accessing public data member
> print("Name: ", inst.kimname)
> inst.display_age()

Name:  king
Age:  30
```

- public이어도 다음과 같이 접근하여 직접 수정하는 건 권장하지 않는다.
- 수정하고 출력하는 별도의 method를 사용하는 걸 권장한다.

```yml
# 권장하지 않는 방법
> inst.kimname = 'Wang'
> print("Name: ", inst.kimname)
Name:  Wang
```

<br>

## 2.2 Protected

> - 정의한 해당 class 또는 해당 class를 **상속** 받은 클래스에서만 접근이 가능하다는 의미지만, 실제로는 public 처럼 접근 가능하다.

- 상속받은 자식 class에서 사용하자는 의미다.
- 하나의 underscore를 name 앞에 붙여서 **_표시만_** 한다.
  - 즉, 실제로 제약되지는 않고, 일종의 **_경고 표시로 사용_** 된다.
  - 단지 경고 표시일 지라도 접근하여 수정하지 않는 걸 권고한다.

```yml
> class Sample:
>   def __init__(self):
>       self.x = 0
>       self._y = 0

> a = Sample()

# 출력 가능하다.
# 이렇게 직접 출력하는 걸 권장하지 않는다.
> print('y = ', a._y)
y = 0
```

- 상속받은 클래스에서 사용하기
- 아래 code처럼 출력 method를 통해서 접근하는 걸 권장한다.

```yml
## super class
> class Student:

    # protected data members
>     _name = None
>     _roll = None
>     _branch = None

>     # constructor
>     def __init__(self, name, roll, branch):
>         self._name = name
>         self._roll = roll
>         self._branch = branch

>     # protected member function
>     def _displayRollAndBranch(self):

>         # accessing protected data memebers
>         print("Roll: ", self._roll)
>         print("branch: ", self._branch)


## Subclass
> class Geek(Student):

>     # constructor
>     def __init__(self, name, roll, branch):
>         Student.__init__(self, name, roll, branch)

>     # public function
>     def displayDetails(self):

>         # accessing protected data of super class
>         print("Name: ", self._name)

>         # accessing protected function of super class
>         self._displayRollAndBranch()

> inst = Geek("Wang",130205, "Information Technology")

> inst.displayDetails()

Name:  Wang
Roll:  130205
branch:  Information Technology
```

<br>

## 2.3 Private: Naming Mangling

> - 정의한 해당 class에서만 직접 접근이 가능하다.   
> - 만약 상속받은 클래스에서 접근하고자 한다면 상위 클래스에서의 private 변수에 접근할 수 있는 method를 만들어야 가능하다.    
> - 두 개의 underscore를 name 앞에 붙여서 사용하여 지정한다.    

- OOP의 캡슐화를 의미한다.
- python에서는 double underscore를 name 앞에 붙이면 해당 이름으로 접근이 허용되지 않는다.
- 왜냐하면 `Naming Mangling`이 일어나기 때문이다.
- double underscore를 붙이면 해당 이름이 `_<해당 class name>_name` 으로 mangling이 일어난다.

  - `__name` -> `_<해당 class name>_name`

```yml
## 정의한 class에서만 접근이 가능하다.

> class Student:

    # private members
>     __name = None
>     __roll = None
>     __branch = None

>     # constructor
>     def __init__(self, name, roll, branch):
>         self.__name = name
>         self.__roll = roll
>         self.__branch = branch

>     # private function
>     def __displayRollAndBranch(self):

>         # accessing private memebers
>         print("Roll: ", self.__roll)
>         print("branch: ", self.__branch)

>     # public function
>     def accessPrivateFunction(self):
>       # accessing private function
>       self.__displayRollAndBranch()

> inst = Student("Wang",130205, "Information Technology")

> inst.acessPrivateFunction()

Name:  Wang
Roll:  130205
branch:  Information Technology
```

- 만약 Protected chapter에서 다뤘던 code에서 protected data로 지정한 `_name = None` 을 private로 바꿔서 상속된 class를 통해서 실행한다면 어떻게 될까???
  - `_name = None` -> `__name = None`

```yml
## Super class
> class Student:

>     # private data
>     __name = None

>    # protected data members
>     _roll = None
>     _branch = None

>     # constructor
>     def __init__(self, name, roll, branch):

>         # private data
>         self.__name = name

>         # protected data
>         self._roll = roll
>         self._branch = branch

>     def _displayRollAndBranch(self):

>         # accessing protected data memebers
>         print("Roll: ", self._roll)
>         print("branch: ", self._branch)


## Subclass
> class Geek(Student):

>     # constructor
>     def __init__(self, name, roll, branch):
>         Student.__init__(self, name, roll, branch)

>     # public function
>     def displayDetails(self):

        # accessing protected data of super class
>         self._displayRollAndBranch()

>         # accessing private data of super class
>         print("Name: ", self.__name)


> inst = Geek("Wang",130205, "Information Technology")

# super class의 private 변수에 직접 접근을 불허한다. 
> inst.displayDetails()

AttributeError: 'Geek' object has no attribute '_Geek__name'
```

- 위의 경우처럼 Error가 뜬다.
- private data는 상속된 class에서 출력할 수 없다는 걸 확인했다.


<br>


### Naming Mangling 확인하기

- 그러면 Naming Mangling을 확인해보자.
- 위의 `AttributeError` 에서도 확인할 수 있듯이 `_Geek__name`으로 private data인 `__name`이 변경된 걸 확인했다.

```yml
# 위 코드에서 실행했다.
> print(dir(inst))

# 결과는 다음과 같다.
['_Student__name', '__class__', ... '_branch', '_displayRollAndBranch', '_roll', 'displayDetails']
```

- 맨 처음 private data를 선언된 class의 이름이 붙여져서 `_Student__name`으로 naming Mangling 된 걸 확인했다.
- mangling의 의미처럼 기존의 설정한 name은 훼손되어 사용할 수 없다.

<br>

### 다른 언어와의 차이점

- 타 클래스의 private attribute, method에 접근하지 않는 것이 원칙이지만, 파이썬은 사실 접근이 가능하다.

- 하지만, 원칙에 맞게 Python 오픈 소스 프로젝트들은 이를 준수하고 있다.

<br>

## 2.4 Summary

- 그러면 마지막으로 3가지 access modifier에 대해 코드로 정리한 후, 마무리하겠다.

```yml
## Super class
> class Super:

>     # public
>     var1 = None

>     # protected data
>     _var2 = None

>     # private data
>     __var3 = None

>     # Constructor
>     def __init__(self, var1, var2, var3):
>         self.var1 = var1
>         self._var2 = var2
>         self.__var3 = var3

>     # public function
>     def displayPublicMembers(self):

>         print("Public data: ", self.var1)

>     # protected function
>     def _displayProtectedMembers(self):

>         print("Protected data: ", self._var2)

>     # private function
>     def __displayPrivateMembers(self):

>         print("private data: ", self.__var3)

>     # public function
>     def accessPrivateMembers(self):
>         # accessing private member function
>         self.__displayPrivateMembers()

## derived class
> class Sub(Super):

    # constructor
>     def __init__(self, var1, var2, var3):
>             Super.__init__(self, var1, var2, var3)

>     # public fucntion
>     def accessProtectedMembers(self):
>         self._displayProtectedMembers()

> inst = Sub("Wang",4, "!")
> inst.displayPublicMembers()
> inst.accessProtectedMembers()
> inst.accessPrivateMembers()


Public data:  Wang
Protected data:  4
private data:  !
```

<br>

---

# Reference

- [모두를 위한 파이썬 : 필수 문법 배우기 Feat. 오픈소스 패키지 배포 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B3%A0%EA%B8%89/dashboard)
- [The Python Standard library - Python 3.10.2 document](https://docs.python.org/3/tutorial/classes.html?highlight=name%20mangling#private-variables)
- [파이썬과 객체지향 (public, private, protected) 프로그래밍](https://www.fun-coding.org/PL&OOP1-5.html)
- [Access Modifiers in Python: Public, Private and Protected](https://www.geeksforgeeks.org/access-modifiers-in-python-public-private-and-protected/)
