# 0. Introduction

> 1. [Method 활용하여 Getter, Setter 작성](#1-method-활용하여-getter-setter-작성)
> 2. [Property란?](#2-property란)

- 지난 Chapter [Python basic 43: Underscore](https://jeha00.github.io/post/python_basic/python_basic_43_underscore/) 의 access modifier에 대한 개념을 먼저 이해해야 한다.

<br>

- class 내의 attribute를 관리하기 위해서 2가지 방법을 가진다.

  - 첫 번째: 이 attribute data에 직접 접근하여 변경하기 => [Python basic 43: Underscore](https://jeha00.github.io/post/python_basic/python_basic_43_underscore/)
  - 두 번째: method를 통해 접근하여 변경하기 => [Method 활용하여 Getter, Setter 작성](#1-method-활용하여-getter-setter-작성)

- 이번 Chapter에서는 두 번째 방법에 대해 알아본 후, 보다 파이썬스러운 방법으로 사용해보자.

<br>

---

<br>

# 1. Method 활용하여 Getter, Setter 작성

- **Method를 활용하여 data에 접근하는 이유**

  - access modifier로 구분한 data에 직접 접근하기보다는 class 내의 method를 통해서 접근하는 것이 side effect를 고려했을 때 현명한 방법이다.

- 그래서 data에 접근하기 위한 method인 `Getter (=accessor)` 와 `Setter(= mutator)`에 대해 작성해보겠다.

```yml
> class Sample:
>   def __init__(self):
>       self.x = 0
>       self.__y = 0

# Getter
>   def get_y(self):
>       return self.__y

# Setter
>   def set_y(self, value):
>       self.__y = value

> A = Sample()
> A.set_y(2)

> print('Ex > x: {}'.format(b.x))
Ex > x : 0

> print('Ex > y: {}'.format(b.get_y()))
Ex > y : 2

> print(dir(A))
['_SampleB__y', '__class__', ..., 'get_y', 'set_y', 'x']
```

- `dir()`를 통해서 private variable은 naming mangling이 일어난 걸 알 수 있다.
- method `get_y` 와 `set_y`를 확인할 수 있다.

<br>

---

# 2. Property

<br>

## 2.1 Property란??

> 공개적으로 노출된 API를 변경하지 않고도 class 내부의 attribute의 기본 구현을 변경할 수 있는 도구

- 위 방식의 문제점
  - 위 방식은 접근하려는 변수의 수가 만약 10개라면 각 변수에 대해 method를 작성해야한다.
  - 객체 지향 설계의 캡슐화를 깨뜨린다.
- 그래서 Python에서는 위 방식보다 간편하고 안전한 도구를 제공하는데, 바로 `property` 다.
- `property`는
  - 속성 같이 행동하는 method를 만든다.
  - getter와 setter method를 피하는 pythonic way로, 내부 method를 외부에 노출시키는 것 없이 처리할 수 있다.

<br>

## 2.2 Property의 구현 방식 2가지

> function으로서 Property() 와 decorator로서 @property

- function으로서 property를 사용하는 방법은 [Real Python - Python's property(): Add Managed Attributes to Your Classes - Getting Started With Python’s property()](https://realpython.com/python-property/#getting-started-with-pythons-property)를 참고한다.

- 전자보다 후자인 **_decorator 로서의 property_** 를 사용하는 방식이 보다 Pythonic way 이며, **_Python open source community에서 가장 유명한 방법_** 이다.

- `@property` 는 `Python 2.4` 부터 가능해진 방식이다.

<br>

## 2.3 @property

```yml
# circle.py

> class Circle:
>     def __init__(self, radius):
>         self.__radius = radius

>     @property
>     def radius(self):
>         """The radius property."""
>         print("Get radius")
>         return self.__radius

>     @radius.setter
>     def radius(self, value):
>         print("Set radius")
>         self.__radius = value

>     @radius.deleter
>     def radius(self):
>         print("Delete radius")
>         del self.__radius
```

- 그러면 위 파일을 import하여 실행해보자.

```yml
> from circle import Circle

> circle = Circle(100)

> circle.radius
Get radius

> print(circle.radius)
Get radius
100

> circle.radius = 50
Set radius

> print(circle.radius)
Get radius
50

> print(dir(circle))
['_Circle__radius', '__class__', ... 'radius']

> del circle.radius
Delete radius

# `del`을 실행한 후, `dir`로 확인하면 `_Circle_radius`가 사라진 걸 확인할 수 있다.
> print(dir(circle))
[ '__class__', ... 'radius']

> circle.radius
AttributeError: 'Circle' object has no attribute '_radius'
```

- 또한 property 내의 docstring을 출력할 수 있다.
- 출력되는 내용은 `@property` 가 붙은 method 안에 docstring이다.

```yml
> help(circle)
Help on Circle in module circle object:

class Circle(builtins.object)
 |  Circle(radius)
 |
 |  Methods defined here:
 |
 |  __init__(self, radius)
 |      Initialize self.  See help(type(self)) for accurate |
---------------
 |  Data descriptors defined here:
 |  ......
 |  radius
 |      The radius property
```

- 그러면 마지막으로 제약조건을 추가하여 사용해보자.
  - @radius.setter 에 제약조건을 추가한다.

```yml
> @radius.setter
> def radius(value):
>   print('Set radius')
>   if value <= 0:
>     raise ValueError('0보다 큰 값을 입력하세요')
>   self.__radius = value
```

- 똑같이 import하여 실행해보자.

```yml
> circle = Circle(100)

> circle.radius = -20
ValueError: 0보다 큰 값을 입력하세요.
```

- 의도한대로 ValueError가 뜨는 걸 확인할 수 있다.

<br>

## 2.4 Summary

- `@property`는 `getter` method를 decorate 한다.
- docstring의 위치는 `@property` 안에 입력해야 한다.
- `setter` 와 `deleter` method는 `getter` method name에 `.setter` 그리고, `.deleter` 로 추가하여 decorate 된다.

<br>

---

# Reference

- [모두를 위한 파이썬 : 필수 문법 배우기 Feat. 오픈소스 패키지 배포 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B3%A0%EA%B8%89/dashboard)
- [Real Python - Python's property(): Add Managed Attributes to Your Classes](https://realpython.com/python-property/)
