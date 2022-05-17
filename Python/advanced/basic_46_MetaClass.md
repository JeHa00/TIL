# 0. Introduction

> 1. [Type: function and metaclass](#1-type-function-and-metaclass)
> 2. [Metaclass의 이점](#2-metaclass의-이점)
> 3. [Make a class statically and dynamically](#3-make-a-class-statically-and-dynamically)
> 4. [Type inheritance로 Custom metaclass 만들기](#4-type-inheritance로-custom-metaclass-만들기)

<br>

- **Meta class에 대한 의견**

> “Metaclasses are deeper magic than 99% of users should ever worry about. If you wonder whether you need them, you don’t (the people who actually need them know with certainty that they need them, and don’t need an explanation about why).”
>
> [Tim Peters]

- **Meta class를 배우는 이유**

  - Tim Peters가 언급한 것처럼 아직은 필요성을 못 느끼지만, 여러 오픈 소스들에서는 이 meteaclass를 활용하여 작성된 경우가 매우 많으므로,  class의 형성 원리를 이해한 것을 바탕으로 오픈 소스를 보다 잘 이해하기 위해 학습한다.

- **파이썬에서는 클래스도 포함하여 모든 것들이 객체다.**

  - 공식 레퍼런스에서도 클래스와 객체를 혼용해서 사용한다.

- **이번 chapter에서의 목표는 최종적으로 Custom Meta class를 만들기 위함이다.**

<br>

---

# 1. Type: function and metaclass

> - 첫 번째: type() 함수를 통해서 해당 인스턴스 및 클래스의 상위 클래스가 무엇인지 확인할 수 있다.
> - 두 번째: type class는 class of class로, 클래스를 만들어내는 meta class다.

```yml
## Foo class 만들기
# class Foo(object): 로 해도 동일
> class Foo():
>   pass

## instance 만들기
> x = Foo()

## 해당 객체의 상위 클래스가 무엇인지 알려준다.
> print(x.__class__)
> print(type(x))
<class '__main__.Foo'>

## 그렇다면 Foo class의 상위 class는 무엇일까??
> print(x.__class__.__class__)
> print(type(Foo))
> print(type(x).__class__)
<class 'type'>

> print(x.__class__ is type(x))
> print(x.__class__.__class__ is type(x).__class__)
True

## type class의 상위 class는 무엇일까???
> print(Foo.__class__.__class__)
<class 'type'>
```

- `type`의 상위 클래스도 `type`이 출력되었다.
- 그러면 다른 자료형들의 상위 클래스도 알아보자.

```yml
# integer
> n = 10

# dictionary
> d = {'a' : 10, 'b': 20}

> for o in (n, d):
>   print('{} {} {}'.format(type(o), type(o) is o.__class__, o.__class__.__class__))
<class 'int'> True <class 'type'>
<class 'dict'> True <class 'type'>

> for t in int, float, list, tuple:
>   print(type(t))
<class 'type'>
<class 'type'>
<class 'type'>
<class 'type'>
```

- 여러 자료형들의 상위 클래스가 `type class`임을 확인했다.

- **그러면 다음과 같은 사실들을 알 수 있다.**

  1. 여러 자료형들은 class를 기반으로 만들어졌다.
  2. 각 class들의 상위 클래스는(원형은) `type class`다.
  3. `type class`의 상위 클래스는(원형은) `type class`다.

- **_이 `type class`를 `metaclass`라 한다._**
  - **모든 클래스들은 이 type metaclass의 인스턴스들이다.**
    - 즉, 모든 클래스들은 type metaclass로부터 만들어졌다.
    - 모든 클래스의 원형은 type metaclass다.

![image](https://files.realpython.com/media/class-chain.5cb031a299fe.png)

<br>

---

# 2. Metaclass의 이점

**1. type function을 통해서 동적으로 생성한 metaclass를 통해서 custom metaclass를 생성할 수 있다.**

    - meta class를 통해 클래스 구현 레벨에 직접 관여할 수 있기 때문에, 의도하는 방향으로 클래스를 커스텀할 수 있다.
    - 단 `type class` 이상으로는 관여할 수 없다.

**2. framework 작성 시 필수다.**

    - 1번의 이유처럼 의도하는 방향대로 직접 클래스 생성에 관여할 수 있기 때문에, 
    - 범용적인 프레임워크 개발, 패키지 개발에 사용된다.
    - Django의 내부를 보면 이 metaclass를 사용하여 구현할 것을 알 수 있다.
    - 많은 세계적인 파이썬 user들은 meta class를 바탕으로 선언한 후, 원하는 대로 구현한다.

**3. type 함수를 통해서 클래스 동적으로 생성 가능하기 때문에, custom metaclass 생성이 가능하다.**

**4. meta class를 바탕으로 엄격한 class 사용 그리고, method override를 요구한다.**

    - Django는 metaclass를 기반으로 만든 ORM framework로, DB와 클래스를 일대일로 맵핑하기 때문에 엄격하다.

<br>

---

# 3. Make a class statically and dynamically

## 3.1 정적으로 클래스 만들기

- 정적으로 클래스를 만든다는 건 여태까지 클래스를 만드는 보다 일반적인 방식을 말한다.

```yml
> class Ex:
>   pass

> class foo(Ex):
>   attr1 = 100
>   attr2 = 'hi'

>   def add(self, m, n):
>       return m + n

>   def mul(self, m, n):
>       return m * n

> ex = foo()
```

<br>

## 3.2 동적으로 클래스 만들기

> 동적으로 클래스를 만든다는 건 **_type() 에 3가지 인자를 입력_** 하여 만드는 방식으로, 필요할 때마다 바로 바로 클래스를 만들어낼 수 있다는 걸 의미한다. 

- **type([name], [bases], [dct])**

  - [name] : 만들려는 **_클래스의 이름_** 을 명시한다. 그리고, 만들어진 클래스의 `__name__` 속성이 된다.

  - [bases] : 만들려는 클래스에게 **_상속할 클래스 이름_** 을 명시한다. 이 때, tuple type으로 입력한다. 이는 클래스의 `__base__` 속성이 된다.

  - [dct] : class body에 있는 **_속성, method_** 들을 포함하는 namespace dictionary를 명시한다. 이는 클래스의 `__dict__` 속성이 된다.

```yml
> Ex = type('eX', (), {})

## 위에 처럼 Ex로 인스턴스를 만들거나, Ex라는 클래스를 정적으로 만들어야 상속할 수 있다.
> class Ex:
>   pass

## 동적 정의 type 외부에 method를 정의할 수도 있으나, 한 번만 사용할거면 람다 함수를 사용하는 게 낫다.
> ex = type('foo',
>           (Ex, ),
>           dict(attr1 = 100, attr2 = 'hi', add = lambda x,y : x + y, mul = lambda x, y: x * y)
>       )

## 위에 동적으로 정의한 class를 정적으로 정의하면 다음과 같은 형식이다.
> class foo(Ex):
>   attr1 = 100
>   attr2 = 'hi'

>    def add(x,y):
>        return x + y

>     def mul(x, y):
>         return x * y

## 그러면 동적으로 정의한 ex를 출력해보자.
> print(ex)
<__main__.foo object at 0x000002A86E3A7D90>

> print(type(ex))
<class '__main__.foo'>

> print(ex.__name__)
foo

> print(ex.__base__)
<class '__main__.Ex'>

> print(ex.__dict__)
{'attr1': 100, 'attr2': 'hi', '__module__': '__main__', '__doc__': None}

> print(ex.attr1, ex.attr2)
100 hi

> print(ex.add(100,200))
300

> print(ex.mul(100,200))
2000
```

<br>

---

# 4. Type inheritance로 Custom metaclass 만들기

- **Metaclass를 상속한다는 것의 의미**

  - type class를 상속받는다.
  - metaclass 속성을 사용한다.
  - custom metaclass 생성하면 다음 일들이 쉬워진다.
    - **_클래서 생성 가로채기(intercept)_**: 후킹(hooking)이라 하며, 잡아채서 원하는 기능을 보충하고 수정하는 것을 말한다.
    - 클래스 수정하기 (modifiy)
    - 클래스 개선(기능 추가)
    - 수정된 클래스 반환

<br>

## 4.1 Custom metaclass란?

> 사용자가 의도한 대로 작동하도록 만든 metaclass다. 하지만, 이 또한 type meta class에서 파생된다.

- **Custom Metaclass가 필요한 이유** 를 다음 코드를 통해서 알아보자.

```yml
# cls란 class에 사용되는 변수를 의미한다.
# self는 instance에 사용되는 변수를 의미한다.
> def new(cls):
>   x = object.__new__(cls)
>   x.attr = 100
>   return x

> class Foo:
>   pass

> Foo.__new__ = new

> f = Foo()
> print(f.attr)
100

# Foo의 __new__를 customizing 했다.
# Foo는 type metaclass의 instance이므로, type의 __new__ method도 커스터마이징을 할려고 한다.

# 하지만 다음과 같이 error가 떴다.
> type.__new__ = new
TypeError: can't set attributes of built-in/extension type 'type'
```

- **위와 같은 Error가 발생된 이유는 파이썬이 이를 허용하지 않기 때문이다.**

  - Why?? type은 모든 클래스들이 만들어지는 기본 토대인 meta class이기 때문에, 사용자가 만질 수 없다.

- **그래서 type으로부터 만들어지는 custom metaclass를 만드는 것이다.**

- **Custom Metaclass 만드는 단계: type metaclass로부터 파생된 metaclass를 정의하기**
  - definition head를 `class <custom metaclass name>(type):` 로 작성하자.
  - 새롭게 정의한 `__new__` method 역할
    - 상위 metaclass의 `__new__` method를 `super()` function을 통해서 새로 만들어지는 클래스에게 할당한다.

```yml
> class MetaClass(type):
>   def __new__(cls, name, bases, dct or namespace):
>       x = super().__new__(cls, name, bases, dct or namespace)
>       x.attr = 100
>       return x

> class Foo(MetaClass)
>   pass

> print(Foo.attr)
> 100
```

<br>

## 4.2 Ex1 with Type 상속 X

- **그러면 custom metacalss를 생성해보자.**
- 다음 것들에 유의해서 보자.
  - class가 아니어도 self를 사용하는 것
  - class 내부가 아닌 외부에 function을 정의하여, component 식으로 필요할 때마다 꺼내서 custom metaclass에 사용할 수 있다.
    - open source에서 이런 방식을 많이 사용한다.
  - 동적으로 class 만들 때, 상속할 class로 list를 입력함에 따라 어떻게 흘러가는지.

```yml
## class 외부에 function 만들기
> def cus_mul(self, d):
>   for i in range(len(self)):
>       self[i] = self[i] * d

> def cus_replace(self, old, new):
>   while old in self:
>       self[self.index(old)] = new

## 동적으로 class 만들기
> CustomList = type(
>                   'CustomList',
>                    (list, ),
>                    {
>                       'desc' : '커스텀 리스트 1',
>                      # 위에서 만든 함수를 넣는다.
>                       'cus_mul' : cus_mul,
>                       'cus_replace' : cus_replace
>                    }
>                )

## 인스턴스 만들기
# 위 method의 self가 list가 자체가 된다.
> c = CustomList([x for x in range(10)])

> c.cus_mul(1000)
> print(c)
[1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000]

> c.cus_replace(1000,1)
> print(c)
[1, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000]

> c.cus_replace(1,'hahaha')
> print(c)
['hahaha', 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000]

> print(c.desc)
커스텀 리스트1

> print(dir(c))
 ['__add__', '__class__', '__class_getitem__', '__contains__', '__delattr__', '__delitem__', '__dict__', '__dir__', '__doc__', .... 'append', 'clear', 'copy', 'count', 'cus_mul', 'cus_replace', 'desc', 'extend', 'index', 'insert', 'pop', 'remove', 'reverse', 'sort']
```

- base에 list를 입력하여, list class를 상속받았기 때문에, list object를 class롤 받을 수 있다.
  - list만의 모든 method를 사용할 수 있으면서, 추가한 method까지 사용할 수 있다.
  - `dir()`로 확인한 속성들을 보면 list에 사용되는 method들이 있는 것을 확인할 수 있다.

<br>

## 4.3 Ex2 with Type 상속 O

> **Ex1 에 대한 내부 작동 원리를 보여주는 예제**

- `__new__`: class의 new instance를 만들기 위해 호출되는 static method

  - 사용자 정의 메타 클래스에서 클래스 생성을 customizing 하기 위해 사용된다.

- **실행 순서: _`__new__` -> `__init__` -> `__call__`_**

  - `__new__` 가 class의 instance를 return하지 않으면, 새 instance의 `__init__`은 호출되지 않는다.

```yml
> class CustomListMeta(type):


## __new__에 의해 생성된 인스턴스 초기화
# type function의 3가지 인수가 포함된 걸 확인할 수 있다.
>   def __init__(self, object_or_name, bases, dict):
# method overriding
>       print('__init__ ->', self, object_or_name, bases, dict)
>       super().__init__(object_or_name, bases, dict)

## instance 실행을 위한 호출
>   def __call__(self, *args, **kwargs)
# method overriding
>       print('__call__ ->', self, args, kwargs)
>       return super().__call__(*args, **kwargs)

## class instance 생성하기: 이 때 메모리 초기화
>   def __new__(metacls, name, bases, namespace):

# method overriding
>       print('__new__ ->', metacls, name, bases, namespace)
>       namespace['desc'] = '커스텀 리스트 2'
>       namespace['cus_mul'] = cus_mul
>       namespace['cus_replace'] = cus_replace
>       return type.__new__(metacls, name, bases, namespace)

## 클래스 동적 생성
> CustomList2 = CustomListMeta(
>                'CustomList2',
>                (list, ),
>                {}
>            )

> c = CustomList2([x for x in range(10)])
__new__ ->  <class '__main__.CustomListMeta'> CustomList2 (<class 'list'>,) {}
__init__ ->  <class '__main__.CustomList2'> CustomList2 (<class 'list'>,) {'desc': '커스텀 리스트2', 'cus_mul': <function cus_mul at 0x0000017A20387F70>, 'cus_replace': <function cus_replace at 0x0000017A2051FB80>}
__call__ ->  <class '__main__.CustomList2'> ([1, 2, 3, 4, 5, 6, 7, 8, 9],) {}


> c.cus_mul(100)
> c.cus_replace(100, 777)

> print(c)
[777, 200, 300, 400, 500, 600, 700, 800, 900]
```

<br>

---

# Reference

- [모두를 위한 파이썬 : 필수 문법 배우기 Feat. 오픈소스 패키지 배포 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B3%A0%EA%B8%89/dashboard)
- [Read Python - Python Metaclasses](https://realpython.com/python-metaclasses/)
