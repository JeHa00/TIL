# 0. Introduction

> 1. [Descriptor란?](#1-descriptor란)
> 2. [Descriptor가 필요한 이유](#2-descriptor가-필요한-이유)
> 3. [Property()과 descriptor 함께 사용하기](#3-property과-descriptor-함께-사용하기)
> 4. [Descriptor를 사용하여 File Count하기](#4-descriptor를-사용하여-file-count하기)
> 5. [Descriptor가 자주 사용되는 용도: logging](#5-descriptor가-자주-사용되는-용도-logging)
> 6. [Lookup chain](#6-lookup-chain)

<br>

- Descriptor에 대한 개념만을 학습한다는 수준으로 정리했다.
- 더 깊이 하고 싶었지만, 아직 경험이 부족한 것 같아 이 정도로 끝내며, Desciptor의 필요성을 느낄 때 다시 한 번 학습한다. 그 때는 `lookup chain` 개념에 대해서도 학습하며, 이를 위해서 밑에 reference들을 다시 한 번 보도록 하자.

---

# 1. Descriptor란?

> class의 속성을 다른 객체와(= 다른 클래스와) mapping하여, 이 객체에서 지정된 Descriptor protocol의 method로 동작하게 하는 것이다. 즉, 클래스에서 다른 클래스를 속성값으로 가지는 것이다.

<br>

## 1.1 Descriptor protocol의 정의

- descriptor protocol의 정확한 정의는 다음과 같다.

```yml
> __get__(self, obj, objtype = None) -> object
> __set__(self, obj, value) -> None
> __delete__(self, obj) -> None
```

- 이는 property의 getter, setter 확장판이라 생각하면 된다.
- property는 function `proeprty()` 과 decorator `@property` 로서, 크게 2가지로 구현할 수 있는데,
- 후자는 [[TIL] Python basic 44: Property](https://jeha00.github.io/post/python_basic/python_basic_44_property/) 를 참고한다.
- 전자의 경우, [Real Python - Python's property(): Add Mangaged Attributes to Your Classes](https://realpython.com/python-property/#getting-started-with-pythons-property)을 보면 알 수 있다.
- 위 문서에 따르면 `property()` 의 signature는 다음과 같다.
  - signature: name, scope, parameters 같은 함수에 대한 일반적인 정보를 명시한 것

```yml
> property(fget = None, fset = None, fdel = None, doc = None)
```

| 인자 | 설명                                   | Descriptor Protocol |
| ---- | -------------------------------------- | ------------------- |
| fget | 속성의 값을 반환하는 함수 (read)       | \_\_get\_\_         |
| fset | 속성의 값을 수정하는 함수 (write)      | \_\_set\_\_         |
| fdel | 속성의 값을 삭제하는 함수              | \_\_delete\_\_      |
| doc  | property에 대해 설명하는 문자열 string |                     |



<br>

## 1.2 Descriptor protocol의 분류

- descriptor protocol의 다음과 같이 분류할 수 있다.
- 이 분류는 큰 의미를 가지지 않는다. 단지 공식 문서에 나와있기 때문에 언급한다.

- **_data descriptor_** : `__set__`, `__del__` 을 구현한 descriptor
- **_non-data descriptor_**: `__get__` 을 구현한 descriptor

<br>

## 1.3 Descriptor protocol signature 의미

> - `self`: descriptor의 instance를 의미
> - `obj`: descriptor의 instance가 속한 'class'의 instance를 의미
> - `objtype`: descriptor의 instance가 속한 class를 의미

- Descriptor를 구현해보면서 descriptor protocol signature의 의미를 확인해보자.
- 여기서 special method는 이미 python 내부적으로 되어 있는 것을 사용한다.

```yml
## Descriptor
> class Ex(object):

>  def __init__(self, name):
>       self.name  = name

>   def __get__(self, obj, objtype):
>       return "Get method called. -> self: {},\n obj: {},\n objtype: {},\n name: {}".format(self, obj, objtype, self.name)

>   def __set__(self, obj, name):
>       print('Set method called.')

# 내부적으로 조건을 만들어 string만 받도록 하여 attribute 값을 유지한다.
>       if isinstance(name, str):
>           self.name = name
>       else:
>           raise TypeError("Name should be string")

>   def __delete__(self, obj):
>       print('Delete method called.')
>       self.name = None


##  Ex1 클래스의 속성인 name과 위의 Ex() class를 mapping 한다.
# 그러면 이 name에 접근할 때, 위 Ex class의 method들을 통해서 접근된다.
# 그리고, 클래스의 속성과 mapping 될 때, descriptor로 여겨진다.
> class Ex1(object):
>   name = Ex()  # desciptor의 instance

## __set__ 호출
# dot notation을 통해서 s1의 name에 접근했지만, 내부적으로는 Ex() class의 __set__ method에서 처리된다.
> s1 = Ex1()
> s1.name = "Descriptor Test"
Set method called

## Ex() class의 __set__ method에 작성한 대로 error가 발생된다.
> s1.name = 7
TypeError: Name should be string

## __get__ 호출
> print(s1.name)
Ex1 >  Get method called. -> self : <__main__.Ex object at 0x000001EC36F30FD0>,
obj : <__main__.Ex1 object at 0x000001EC36F30E80>,
objtype : <class '__main__.Ex1'>,
name : Descriptor Test1

## __delete__ 호출
> del s1.name
Delete method called.

## 맨 마지막 name의 value가 None으로 바뀐 걸 확인할 수 있다.
> print(s1.name)
Get method called. -> self : <__main__.Ex object at 0x0000026EF9AB0FD0>,
obj : <__main__.Ex1 object at 0x0000026EF9AB0E80>,
objtype : <class '__main__.Ex1'>,
name : None
```

- 위 code 상에서 아래 argument들은 다음을 의미한다.

| self | obj | objtype |
| ---- | --- | ------- |
| name | s1  | Ex1     |

<br>

---

# 2. Descriptor가 필요한 이유

- 첫 번째, `__get__`, `__set__`, `__del__` 등을 미리 정의 가능하다.

  - 위 method를 통해서 읽기 전용 객체를 생성할 수 있다.

- 두 번째, property 보다 low level에 간섭할 수 있어서 클래스를 의도하는 방향으로 생성 가능하기 때문에, 상황에 맞는 method를 구현하여 객체 지향 프로그래밍을 구현할 수 있다.

- 세 번째, property와 달리 재사용이 가능하다.
- 네 번째, ORM framework에 사용된다.
  - [Real Python - descriptor의 ORM 예제](https://docs.python.org/ko/3/howto/descriptor.html#orm-example)

<br>

- 두 번째 low level에 관여할 수 있다는 건 다음과 같은 경우를 말한다.
  - 밑에 코드는 [Real Python - descriptor의 dry code](https://realpython.com/python-descriptors/#dry-code) 부분을 가져왔다.

```yml
# Properties로 구현한 경우
> class Values:
>     def __init__(self):
>         self._value1 = 0
>         self._value2 = 0
>         self._value3 = 0


>     @property
>     def value1(self):
>         return self._value1

>     @value1.setter
>     def value1(self, value):
>         self._value1 = value if value % 2 == 0 else 0

>     @property
>     def value2(self):
>         return self._value2

>     @value2.setter
>     def value2(self, value):
>         self._value2 = value if value % 2 == 0 else 0

>     @property
>     def value3(self):
>         return self._value3

>     @value3.setter
>     def value3(self, value):
>         self._value3 = value if value % 2 == 0 else 0

> my_values = Values()
> my_values.value1 = 1
> my_values.value2 = 4
> print(my_values.value1)
> print(my_values.value2)


## 위 code를 desciptor로 구현해보자.
> class EvenNumber:
>     def __set_name__(self, owner, name):
>         self.name = name

>     def __get__(self, obj, type=None) -> object:
>         return obj.__dict__.get(self.name) or 0

>     def __set__(self, obj, value) -> None:
>         obj.__dict__[self.name] = (value if value % 2 == 0 else 0)

> class Values:
>     value1 = EvenNumber()
>     value2 = EvenNumber()
>     value3 = EvenNumber()


> my_values = Values()
> my_values.value1 = 1
> my_values.value2 = 4

> print(my_values.value1)
> print(my_values.value2)
```

- descriptor로 구현하여 훨씬 짧은 code로 구현했다.
- 더 low level에서 구현할 수 있기 때문에, 코드 복제를 줄이고, 보다 심플하게 구현할 수 있다.
- 그렇기 때문에 low level 까지 관여할 수 있는 실력을 갖춰야 한다.

<br>

---

# 3. Property()과 descriptor 함께 사용하기

- 위에 예제들과 달리 이번 단원에서 구현할 예제는 3가지 차이점이 있다.
  - 한 class에서 descriptor를 구현한다.
  - property(fget = None, fset = None, fdel = None, doc = None) 를 사용한다.
  - 정해진 magic method name을 사용하지 않는다.

```yml
> class Descriptor(object):

>    def __init__(self, value):
>        self._name = value

>    def getVal(self):
>        return 'Get method called -> \nself : {},\nname : {}'.format(self, self._name)

>    def setVal(self, value):
>        print('Set method called')
>        if isinstance(value, str):
>            # Return whether an object is an instance of a class or of a subclass thereof.
>            self._name = value
>        else:
>            raise TypeError('Name should be string')

>    def delVal(self):
>        print('Delete method called')
>        self._name = None

>    name = property(getVal, setVal, delVal, 'Property Method Example')

> s2 = Descriptor('Descriptor Test1')

> print('s2.name > ', s2.name)
s2.name >  Get method called ->
self : <__main__.Descriptor object at 0x00000269295F7BE0>,
name : Descriptor Test1

> s2.name = 'Descriptor Test2'
Set method called

> print('s2.name > ', s2.name)
s2.name >  Get method called ->
self : <__main__.Descriptor object at 0x00000269295F7BE0>,
name : Descriptor Test2

> del s2.name

> print('s2.name > ', s2.name)
s2.name >  Get method called ->
self : <__main__.Descriptor object at 0x00000269295F7BE0>,
name : Nones

# property() function의 docs 부분이 출력된다.
> print(Descriptor.name.__doc__)
Property Method Example
```

- descriptor의 instance를 만드는 것 대신에, 한 클래스 안에 name 객체에다가 property() 를 할당하여, property 안에 method들을 통해 내부적으로 작동할 수 있도록 한다.

<br>

---

# 4. Descriptor를 사용하여 File Count하기

```yml
## file 수 세는 descriptor

> import os

> # descriptor
> class DirectoryfileCount():
>   def __get__(self, obj, objtype):
>       print(os.listdir(obj.dirname))
>       return len(os.listdir(obj.dirname))

> # objtype
> class DirectoryPath:
>   # self: descriptor instance
>   size = DirectoryFileCount()
>   def __init__(self, dirname):
>       self.dirname = dirname

## obj: objtype의 instnace
# 현재 경로
> s = DirectoryPath('./')

# 이전 경로
> g = DirectoryPath('../')
```

- 그러면 `dir()`과 `__dict__`를 통해서 알아보자.

```yml
# class로 접근
> print(dir(DirectoryPath))
['__class__', '__delattr__', '__dict__', '__dir__', '__doc__',..., 'size']

> print(DirectoryPath.__dict__)
{'__module__': '__main__',
'size': <__main__.DirectoryFileCount object at 0x00000167DA7679D0>,
'__init__': <function DirectoryPath.__init__ at 0x00000167DA770280>,
'__dict__': <attribute '__dict__' of 'DirectoryPath' objects>,
'__weakref__': <attribute '__weakref__' of 'DirectoryPath' objects>,
'__doc__': None}

# instance로 접근
> print(dir(s))
['__class__', '__delattr__', '__dict__', '__dir__', '__doc__', ... , 'dirname', 'size']

> print(s.__dict__)
{'dirname': '../'}

# 현재 파일이 있는 경로에 있는 파일들 목록들이 출력된 후, 총 파일 수가 출력됩니다.
> print(s.size)
....
16
> print(g.size)
....
9
```

- `listdir`: 주어진 directory에 있는 항목들의 이름을 담고 있는 list를 반환한다.

<br>

---

# 5. Descriptor가 자주 사용되는 용도: logging

- logging class는 기본 템플릿을 만들고 나서 사용하는 게 편한다.
- 비행기 티켓 클래스에 가격과 장소만 있다고 고려한다.

- descriptor를 사용할 때, 중요한 것들을 discriptor field로 정한다.
  - 여기 클래스에서는 가격으로 정한다.
  - 그러면 이후에 가격과 관련된 class에는 이 descriptor를 재사용할 수 있는 것이다.

```yml
> import logging

> logging.basicConfig(
>   format = '%(asctime)s %(message)s',
>   level = logging.INFO,
>   datafmt = '%Y-%m-%d %H:%M:%S'
> )

# 공용 module로도 사용할 수 있다.
> class LoggedPriceAccess
>   def __init__(self, value = 150000):
>       self.value = value

>   def __get__(self, obj, objtype):
>       logging.info('Accessing %r giving %r', 'price', self.value)
>       return self.value

>   def __set__(self, obj, value):
>       logging.info('Updating %r to %r', 'score', self.value)
>       self.value = value

> class Ticket:
>   price = LoggedPriceAccess()
>   def __init__(self, destination):
>       self.destination = destination

> Ticket1 = Ticket('Jeju')


> print('Jeju Price > ', Ticket1.price)
2022-05-19 18:10:32 Accessing 'price' giving 150000
Jeju Price >  150000

> Ticket1.price -= 50000

> print('Jeju Price > ', Ticket1.price)
2022-05-19 18:12:07 Accessing 'price' giving 100000
Jeju Price >  100000
```

<br>

---

# Reference

- [모두를 위한 파이썬 : 필수 문법 배우기 Feat. 오픈소스 패키지 배포 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B3%A0%EA%B8%89/dashboard)
- [Python docs - How to use a Descriptor guide](https://docs.python.org/ko/3/howto/descriptor.html#managed-attributes)
- [Python docs - Functions and methods](https://docs.python.org/3/howto/descriptor.html#functions-and-methods)
- [Real Python - Python Descriptors: An Introduction](https://realpython.com/python-descriptors/)
- [Real Python - Python's property(): Add Mangaged Attributes to Your Classes](https://realpython.com/python-property/#getting-started-with-pythons-property)
- [Python Descriptor - A Thorough Guide](https://elfi-y.medium.com/python-descriptor-a-thorough-guide-60a915f67aa9)
