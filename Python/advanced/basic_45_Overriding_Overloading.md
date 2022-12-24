# 0. Introduction

> 1. [Method overriding](#1-method-overriding)
> 2. [Method overloading](#2-method-overloading)
> 3. [Overloading: multipledispatch](#3-overloading-multipledispatch)

<br>

---

# 1. Method overriding

> 상위 클래스에서 정의한 method의 기능을 하위 클래스에서 가져와 자신에게(하위 클래스에) 맞게 customizing 하는 것

- **Method overriding 장점**

  1. sub class에서 super class를 호출 후 사용한다.
  2. method 재정의 후, 사용 가능하다.
  3. Super class의 method 추상화 후 사용 가능
     - 구조적 접근이 가능하다.
  4. 확장 가능, 다형성

     - 다형성: 상위 클래스에서 하나로 만들었지만, 하위 클래스의 성질에 따라 다양하게 적용되기 때문에 다형성을 띈다.

  5. 가독성 증가, 오류 가능성 감소, 유지 보수성 증가

     - Method 이름이 절약되기 때문이다.

- **아래 코드들을 보면서 위 장점들에 대해 느껴보자**

## 1.1 Ex1

```yml

## Super class
> class ParentEx1():
>       def __init__(self):
>           self.value = 5

# 지난 번에 배운 property를 사용할 수도 있다.
>       def get_vaue(self):
>           return self.value

## Sub class
> class ChildEx1(ParentEx1):
>   pass

## Instance
> c1 = ChildEx1()
> p1 = ParentEx1()

## 상위 클래스의 method를 호출한다.
> print(c1.get_value())
5

# c1의 모든 속성 출력
> print(dir(c1))
['__class__', '__dict__', '__dir__', '__doc__',... , 'get_value', 'value']

## 상위, 하위 클래스의 모든 속성 비교하기
# 동일하다.
> print(dir(ParentEx1))
> print(dir(ChildEx1))
['__class__', ..., 'get_value']
```

- `dir`과 `__dict__`를 각각 호출해보자.

```yml
> print(ParentEx1.__dict__)
Ex1 >  {'__module__': '__main__', '__init__': <function ParentEx1.__init__ at 0x0000025A8A4AFA60>, 'get_value': <function ParentEx1.get_value at 0x0000025A8A4AFB80>, '__dict__': <attribute '__dict__' of 'ParentEx1' objects>, '__weakref__': <attribute '__weakref__' of 'ParentEx1' objects>, '__doc__': None}

> print(ChildEx1.__dict__)
{'__module__': '__main__', '__doc__': None}
```

- 그 결과, 차이가 있다는 걸 알 수 있다.

<br>

## Ex 1.2

```yml
> class ParentEx2():
>   def __init__(self):
>       self.value = 5

>   def get_value(self):
>       return self.value

> class ChildEx2(ParentEx2):

# 상위 클래스의 method를 가져와서 하위 클래스에서 customizing 한다.
>   def get_value(self):
>       return self.value * 10

> p2 = ParentEx2()
> c2 = ChildEx2()


> print(p2.get_value())
5

> print(c2.get_value())
10

# 출력값이 동일하다.
> print(dir(ParentEx2))
> print(dir(ChiledEx2))
['__class__', '__delattr__', '__dict__', '__dir__', '__doc__', '__eq__', '__format__', '__ge__', '__getattribute__', '__gt__', '__hash__', '__init__', '__init_subclass__', '__le__', '__lt__', '__module__',
'__ne__', '__new__', '__reduce__', '__reduce_ex__', '__repr__', '__setattr__', '__sizeof__', '__str__', '__subclasshook__', '__weakref__', 'get_value']

> print(ParentEx2.__dict__)
{'__module__': '__main__', '__init__': <function ParentEx2.__init__ at 0x000002919386FC10>, 'get_value': <function ParentEx2.get_value at 0x000002919386FCA0>, '__dict__': <attribute '__dict__' of 'ParentEx2' objects>, '__weakref__': <attribute '__weakref__' of 'ParentEx2' objects>, '__doc__': None}

> print(ChildEx2.__dict__)
{'__module__': '__main__', 'get_value': <function ChildEx2.get_value at 0x000002919386FD30>, '__doc__': None}
```

<br>

## Ex 1.3

> Method overriding을 통해서 log class를 구현하기

- 이번 예제를 통해서 구조적 설계에서의 장점을 보인다.
- 운영에 있어서 필요한 log class를 구현해보자.
- 실무적으로 좋은 예제다.

```yml
> import datetime

> class Logger(object):
>   def log(self, msg):
>       print(msg)

## 다형성으로 하위 클래스 2개를 만든다.

# 1. 시분초까지 표시되는 하위 클래스
> class TimestampLogger(Logger):
>   def log(self, msg):
>       message = '{ts} {msg}'.format(ts = datetime.datetime.now(), msg = msg)
>       super(TimestampLogger, self).log(message)


# 2. 날짜만 출력되는 하위 클래스
# strftime은 현장에서 많이 사용된다.
> class DateLogger(Logger):
>   def log(self, msg):
>       message = '{ts} {msg}'.format(ts = datetime.datetime.now().strtime('%y-%m-%d'), msg = msg)
>       super(DateLogger, self).log(message)

> l = Logger()
> t = TimestampLogger()
> d = DateLogger()

> l.log('test1')
test1

> t.log('test2')
2022-05-15 20:23:45.956916 test2

> d.log('test3')
22-05-15 test3
```

- `super(TimestampLogger, self)`는 `super(파생클래스, self)`로 기반 클래스의 method 호출에 사용한다.

<br>

---

# 2. Method overloading

> 동일한 class 내에서 method 이름은 동일하지만, parameter의 number와 type이 다른 여러 method를 정의하는 것

- **Method overloading 장점**

  1. 동일 method 재정의
  2. Naming으로 기능 예측
  3. 코드 절약, 가독성 향상
  4. Method parameter 기반 호출 방식

- **아래 코드들을 보면서 위 장점들에 대해 느껴보자**

```yml
> class SampleA():

>   def add(self, x, y):
>       return x + y

>   def add(self, x, y, z):
>       return x+ y + z

> a = SampleA()

> print(a.add(4,5))
TypeError: add() missing 1 required positional argument: 'z'
```

- 분명 인자가 2개인 method를 만들었지만, `z`가 부족하다는 Error가 떴다.
- 동일한 method name으로 작성했을 때, 맨 마지막 method로 인식한다는 것이다.
- 이 문제에 대해 해결책은 2가지 방법이 있다.

  - unpacking 사용하기
  - 외부 모듈인 multipledispatch 사용하기

```yml
# unpacking 사용하기
> class SampleA():

>   def add(self, *args):
>       return sum(args)

> a = SampleA()

> print(a.add(2,3))
5

> print(a.add(2,3,6))
11
```

<br>

- 다음으로 unpacking에 **_자료형에 따른 분기 처리_**를 추가해보자.
  - single method로 여러 기능들을 구현할 수 있다.

```yml
> class SampleB():

>   def add(self, datatype, *args):

>       if datatype == 'int':
>           return sum(args)

>       if datatype == 'str':
>           return ' '.join([x for x in args])

> b = SampleB()

# 정수형 연산
> print(b.add('int', 5, 6))
11

# 문자열 연산
> print(b.add('str', 'Hi', 'Guys'))
Hi Guys
```

- 이렇게 단일 method 내에서 조건 분기화를 통해서 구현했지만, 이는 method overriding이 아니다.

- **파이썬은 클래스 내에서 method overloading을 지원하지 않는다.**
  - 그래서 multipledispatch를 사용하여 method overloading을 구현한다.

<br>

---

# 3. Overloading: multipledispatch

- 외부 module인 multipledispatch를 사용하여 overriding을 구현해보자.

<br>

## 3.1 To install Multipledispatch

- 이를 위해서 먼저 외부 module을 설치해보자.
- 외부 모듈이기 때문에, 가상환경에 입력한다.
- `pip install multipledispatch` 로는 설치되지 않는다.

```yml
# 다음 명령어를 입력하여 설치한다.
> py -m pip install multipledispath

# 설치가 잘 되었는지 확인한다.
# 설치된 module을 확인할 수 있다.
> pip list

# 설치된 multipledispatch의 version을 탐색한다.
> pip search multipledispatch
```

<br>

## 3.2 Multipledispatch로 구현하기

- 설치한 package를 통해서 method overloading을 보다 편하게 구현할 수 있다.
- 또한, 최근 overloading을 구현하는 방식이다.

```yml
> from multipledispatch import dispatch

> class SampleC():

>   @dispatch(int, int)
>   def product(x, y):
>       return x * y

>   @dispatch(int, int, int)
>   def prodcut(x, y, z):
>       return x * y * z

>   @dispatch(float, float, float)
>   def product(x, y, z):
>       return x * y * z
```

- 동일한 이름을 사용해서 훨씬 깔끔하고, 이름을 아낄 수 있다.
- 그럼 인스턴스를 만들어서 각 method를 실행해보자.

```yml
> c = SampleC()

> print(c.product(5, 6))
30

> print(c.product(5, 6, 7))
210

> print(c.product(10.0, 15.0, 25.0))
3750.0
```

- 동일한 method 명이지만, 변수의 타입과 갯수에 맞게 적절한 method가 적용된다는 걸 확인했다.
- 동일한 name을 사용하여 훨씬 깔끔하고, name을 아낄 수 있다.

<br>

---

# Reference

- [모두를 위한 파이썬 : 필수 문법 배우기 Feat. 오픈소스 패키지 배포 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B3%A0%EA%B8%89/dashboard)
- [외부 모듈 설치하기](https://pip.pypa.io/en/stable/cli/pip_install/)
