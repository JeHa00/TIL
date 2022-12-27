# 0. Introduction

> 1. [Special Method란??](#special-method란-)
> 2. [Special method 연산 예제 1](#special-method-연산-예제-1)
> 3. [Special method 연산 예제 2](#special-method-연산-예제-2)

- 예시코드는 [Python basic 24](https://jeha00.github.io/post/python_basic/python_basic_24_oop/)에서 작성한 코드를 이어서 사용한다.

```yml
> class Airline():


>     """
>     Airline class
>     Author: Kim
>     Data: 2022.03.16
>     Description: Class, Static, Instance Method
>     """


>     price_per_raise = 1.0



>     def __init__(self, company, details):
>         self._company = company
>         self._details = details

>     def __str__(self):
>       return 'str : {} - {}'.format(self._company, self._details)

>     def __repr__(self):
>       return 'repr : {} - {}'.format(self._company, self._details)


### 코드 추가

>     def __add__(self, x):
>        print('Called >> __add__')
>        return (self._details.get('price') + x._details.get('price'))

>     def __sub__(self, x):
>        print('Called >> __sub__')
>        return (self._details.get('price') - x._details.get('price'))

>     def __le__(self, x):
>        print('Called >> __le__')
>        if self._details.get('price') <= x._details.get('price'):
>            return True
>        else:
>            return False

>     def __ge__(self, x):
>        print('Called >> __ge__')
>        if self._details.get('price') >= x._details.get('price'):
>            return True
>        else:
>            return False

### 코드 추가 끝


## Instance
> Airline1 = Airline('Koreanair', {'uniform_color': 'skyblue', 'kind':'FSC', 'price': 8000})
> Airline2 = Airline('Asiana', {'uniform_color': 'gray', 'kind':'FSC', 'price': 6000})
> Airline3 = Airline('t-way', {'uniform_color': 'red', 'kind':'LCC', 'price': 3000})

```

# 1. Python의 핵심: 4가지

- 파이썬의 핵심은 4 가지다.

  - **_시퀀스(Sequence), 반복(Iterator), 함수(Functions), 클래스(Class)_**

    - Sequence를 알아야 Iterator를 할 수 있다.
    - 일급함수 개념을 알아야 Iterator와 함께 Closure와 coroutine을 할 수 있다.
    - special method를 알아야 클래스를 풍부하게 사용할 수 있다.

  - 이 4가지는 서로 유기적으로 연결되어 있다.

- 이번 시간에는 이 4가지 중 클래스 안에 정의되는 `special method`에 대해 알아보자.

- 파이썬 공식문서 [Data Model](https://docs.python.org/3/reference/datamodel.html#special-method-names) 을 꼭 참고하자.

<br>

---

# 2. Special method란 ??

> **_double under-bar로 시작하는 이 method는 class 안에서 정의할 수 있는 특별한 method로, 많은 fuction들이 내부적으로 이 special method에 의해 동작된다._**

- 그렇다면 왜 특별하여, `Magic method`라 불릴까?

  - 내장(Built-in)되어 있는 method를 사용자가 오버라이딩하여 사용할 수 있다.
  - Special method를 통해서 클래스끼리의 연산도 가능해진다.
  - low level에서 효율적인 코딩을 작성할 수 있다.

- low level에서 효율적인 코딩이 가능한 이유는 function의 내부를 보면 이 special method에 의해서 구동되기 때문이다. 왜냐하면 우리가 사용한 모든 데이터 타입을 넘어서 객체는 다 `클래스` 라서, 많은 연산의 백그라운드에는 special method가 사용되고 있다.

- `dir()`은 인자로 들어간 클래스 객체에서 사용할 수 있는 `Special method` 속성을 보여주는데, 이를 통해서 많은 magic method가 class 하에 존재한다는 걸 알 수 있다.

- [[TIL] Python basic 46: Metaclass](https://jeha00.github.io/post/python_basic/python_basic_46_metaclass/)를 참고하여 모든 객체가 클래스임을 확인하자.

## 2.1 Special method 예시 1

```yml
> n = 10
> print(type(n))
<class 'int>

# `__add__` 는 `dir()`로 확인할 수 있듯이 <class 'int'> 안에 정의된 method다.
> print(dir(n))
['__abs__', '__add__', '__and__', '__bool__', '__ceil__', '__class__', ...]

> print(n + 100)
110

# 이는 `__add__` magic method를 사용하는 결과와 동일하다.

> print(n.__add__(100))
110
```

- '+' 연산자도 `__add__` magic method를 호출하기 때문에, 위와 같이 결과가 동일하다.

<br>

## 2.2 Special method 예시 2

- 또한, 지난 번에 알아봤던 것처럼 repr() 를 사용하는 건 `__repr__`를 호출하여 이 method에 의한 결과값을 반환하는 것이라 했다.

<br>

## 2.3 Special method 예시 3

- boolean 함수도 그렇다.

```yml
> print(n.__bool__(), bool(n))
True True
```

- 파이썬에서는 많은 함수가 magic method를 호출하여 사용된다.

<br>

---

# 3. Special method 연산 예제 1

- magic method를 재정의하지 않고, 클래스끼리 덧셈을 해보자.

```yml
> print( Airline1 + Airline2 )
> print(Airline1.__add__(Airline2))
TypeError: unsupported operand type(s) for +: 'Airline' and 'Airline'
```

- 즉, 피연산자의 type이 지원되지 않는 type이라는 의미다. 이처럼 클래스끼리의 연산은 가능하지 않다.
- 그러면 이를 magic method를 customizing하여 사용해보자.

```yml
# 각 Airline의 `price` key에 대한 value끼리 덧셈 연산하기
>     def __add__(self, x):
        # 호출되었는지 확인하기
>        print('Called >> __add__')
>        return (self._details.get('price') + x._details.get('price'))
```

- instance 끼리 연산을 한다면 instance의 어느 부분들이 더하는 건지 **_재정의_** 한다.
- 그러면 다시 덧셈을 해보자.

```yml
> print( Airline1 + Airline2 )
> print(Airline1.__add__(Airline2))
Called >> __add__
14000
```

- 위 코드를 보고 이런 생각이 들 수도 있다.
- 아래와 같이 코드를 짤 수도 있지 않은가??

```yml
> print(Airline1._details.get('price') + Airline2._details.get('price'))
14000
```

- 동일한 결과다.
- 하지만 이렇게 직접 값에 접근하는 건
  - 위험하다.
  - 코드 양이 많아지고, 가독성이 좋지않다.
- 그래서 `magic method`를 잘 활용해야 한다.

- 다른 magic method도 만들어보자.

```yml

# 각 Airline의 `price` key에 대한 value끼리 뺄셈 연산하기
>     def __sub__(self, x):
        # 호출되었는지 확인하기
>        print('Called >> __sub__')
>        return (self._details.get('price') - x._details.get('price'))


# 각 Airline의 `price` key에 대한 value끼리 비교 연산하기
>     def __le__(self, x):
        # 호출되었는지 확인하기
>        print('Called >> __le__')
>        if self._details.get('price') <= x._details.get('price'):
>            return True
>        else:
>            return False


# 각 Airline의 `price` key에 대한 value끼리 비교 연산하기
>     def __ge__(self, x):
        # 호출되었는지 확인하기
>        print('Called >> __ge__')
>        if self._details.get('price') >= x._details.get('price'):
>            return True
>        else:
>            return False


> print(Airline1 >= Airline2)
Called >> __ge__
True

> print(Airline1 <= Airline2)
Called >> __le__
False

> print(Airline1 - Airline2)
Called >> __sub__
2000

```

> - 연산자 약어  
>   ge: greater or equal  
>   le: less or equal  
>   lt: little  
>   gt: greater

<br>

---

# 4. Special method 연산 예제 2

- 이번 예제에서는 `packin` 과 `unpacking`을 사용한다.
- `packin` 과 `unpacking`에 대한 기본 내용은 다음을 참고한다.

  - [[TIL] Python basic 12: Method](https://jeha00.github.io/post/python_basic/python_basic_12/#3-packing-unpacking)

- 일반적인 덧셈 연산으로는 벡터 연산을 하지 못한다.
- 그래서 벡터 연산에 맞게 magic method를 구현한다.

> **_`other`이란?? 정의한 class로 만든 또 다른 instance를 의미한다._**

<br>

````yml
> class Vector(Object):

#   def __init__(self,x,y) 로 써도 되지만, 팩킹을 사용해보자.
# 잘 만든 오픈 소스들을 보면 아래처럼 작성된다.
>   def __init__(self, *args):
>       ''' Create a vector, example: v = Vector(5, 10) '''
#    실전에서는, 그리고 잘 만든 소스들에는 주석이 잘 달려있다.

>    if len(args) == 0:
>         self._x, self._y = 0, 0
>   else:
#   unpacking
>        self._x, self._y = args

>   def __repr__(self):
>       '''Returns the vector method informations'''
#       raw data로 출력하기
>       return 'Vector(%r, %r)' % (self._x, self._y)

>   def __add__(self, other):
>       '''Returns the vector addtion of self and other'''
>       return Vector(self._x+ other._x)

>   def __mul__(self, y):
>   ```Returns the vector multiplication of self and other```
>       return Vector(self._x * y, self._y * y )

````

- Vector에 대한 comment를 보고 싶다면??

  - `print(Vector.__doc__)` 를 사용한다.

- 그러나 위에 코드에는 Vector에 대한 comment는 없다.
- Vector에 대한 comment 대신 Vector.\_\_init\_\_에 대한 comment가 있다.
- 이런 경우에는 어떻게 볼 수 있을까???

  - `print(Vector.__init__.__doc__)`를 사용한다.

- 위 코드들로 실습을 해보자.

```yml
> v1 = Vector(20, 45)
> v2 = Vector(2.5, 5)
> v3 = Vector()

> print(Vector.__init__.__doc__)
Create a vector, example : v = Vector(5, 10)

> print(Vector.__repr__.__doc__)
Returns the vector method infomations

> print(Vector.__add__.__doc__)
Returns the vector addition of self and other

> print(v1, v2, v3)
Vector(20, 45) Vector(2.5, 5) Vector(0, 0)

> print(v1 + v2)
Vector(22.5, 50)


> print(v1 * 3)
Vector(60, 135)


> print(v2 * 10)
Vector(25.0, 50)

```

- 위 magic method 외에도 매우 많이 있다.
- 이 문서 [A Guide to Python's Magic Methods](https://rszalski.github.io/magicmethods/)를 참고하자.

<br>

---

# Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [Data Model](https://docs.python.org/3/reference/datamodel.html#special-method-names)
- [stackoverflow : 'What does "other" mean in python?'](https://stackoverflow.com/questions/51010228/what-does-other-mean-in-python)
