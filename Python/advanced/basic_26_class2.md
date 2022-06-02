# Python basic 26 : Class advanced

# 0. Introduction

> 1. [Self의 의미](#1-self의-의미)
> 2. [dir & \_\_dict\_\_ 그리고 \_\_doc\_\_](#2-dir-__dict__-그리고-__doc__)
> 3. [Method의 3 종류](#3-method의-3종류)

<br>

- 이번 내용은 [Python basic 14: 파이썬 클래스(class)](https://jeha00.github.io/post/python_basic/python_basic_14/)에 이어 진행한다.

- 예시코드는 [Python basic 24](https://jeha00.github.io/post/python_basic/python_basic_24_oop/)에서 작성한 코드를 이어서 사용한다.

```yml
> class Airline():

### 코드 추가
>     """
>     Airline class
>     Author: Kim
>     Data: 2022.03.16
>     Description: Class, Static, Instance Method
>     """


>     price_per_raise = 1.0

### 코드 추가 끝

>     def __init__(self, company, details):
>         self._company = company
>         self._details = details

>     def __str__(self):
>       return 'str : {} - {}'.format(self._company, self._details)

>     def __repr__(self):
>       return 'repr : {} - {}'.format(self._company, self._details)

### 코드 추가
## Instance Method:
# Self로 객체의 고유한 속성 값을 사용
>   def detail_info(self):
>        print('Current ID: {}'.format(id(self)))
>        print('Airline Detail Info: {} {}'.format(self._company, self._details.get('price')))

>   def get_price(self):
>        return 'Before Airline ticket price -> Company: {}, price: {}'.format(self._company, self._details.get('price'))


>   def get_price_culc(self):
>        return 'After Airline ticket price -> Company: {}, price: {}'.format(self._company, self._details.get('price') * Airline.price_per_raise = 1.0)

## Class Method:
# 클래스 변수를 다룰 때는 직접 사용하기보다는 class method로 사용하기.
>   @classmethod
>    def raise_price(cls,per)
>         if per <= 1:
>           print('Please Enter 1 or More')
>           return
>         cls.price_per_raise = per
>         print('Succeed! price increased')

## Static Method
>   @staticmethod
>   def is_Koreanair(inst):
>     if inst._company == 'Koreanair':
>       return 'OK! This car is {}'.format(inst._company)
>     return 'Sorry! This is not Koreanair'

### 코드 추가 끝

## Instance
> Airline1 = Airline('Koreanair', {'uniform_color': 'skyblue', 'kind':'FSC', 'price': 8000})
> Airline2 = Airline('Asiana', {'uniform_color': 'gray', 'kind':'FSC', 'price': 6000})
> Airline3 = Airline('t-way', {'uniform_color': 'red', 'kind':'LCC', 'price': 3000})


```

  <br>

---

# 1. Self의 의미

> **_각 인스턴스의 고유값을 인자로 받는 매개변수로, 고유값은 인스턴스뿐만 아니라 클래스도 가진다._**

- `self._속성`으로 입력하기 때문에, 각 인스턴스마다 자신의 영역에 저장할 수 있다.

<br>

- `self._속성` 입력 시, 언더바(\_)를 사용하는 이유??
  - PEP에서 권장하기 때문에 인스턴스 변수를 만들 때 underbar를 사용한다.
  - 언더바의 의미는 [[TIL] Python basic 43: Underscore](https://jeha00.github.io/post/python_basic/python_basic_43_underscore/)을 참고하라.

```yml
## Instance의 ID 확인
> print(id(Airline1))
> print(id(Airline2))
> print(id(Airline3))
2866211294752
2866211294368
2866211294176
```

<br>

- Instance를 만들기 위한 class도 고유 id 값을 가지고 있다.

```yml

## Instance가 무슨 class로 만들어졌는지 알 수 있다.
> print(Airline1.__class__, Airline2.__class__)
<class '__main__.Airline'>


## class의 고유 id값을 알 수 있다.
> print(id(Airline))
> print(id(Airline1.__class__), id(Airline2.__class__))
3104262219664
3104262219664 3104262219664
```

<br>

---

# 2. dir, \_\_dict\_\_ 그리고 \_\_doc\_\_

<br>

## 2.1 dir 과 \_\_dict\_\_

> **_dir 과 \_\_dict\_\_의 차이는 [[TIL] Python basic 14: class](https://jeha00.github.io/post/python_basic/python_basic_14_class/#23-namespace-%ED%99%95%EC%9D%B8%ED%95%98%EA%B8%B0)을 참고하라_**

```yml
# `dir`을 통해서 namespace 안에 class Airline class에 작성된 속성들과 method들을 확인할 수 있다.
> print(dir(Airline1))
> print(dir(Airline2))
['__class__', '__dict__', '__dir__', '__doc__', '__init__', ..... ,'__repr__','__str__',
'_company', '_details', 'detail_info', 'get_price', 'get_price_culc', 'price_per_raise',
 'raise_price']

# __init__ 생성자로 만들어진 instance variable 의 구체적인 값을 확인할 수 있다.
> print(Airline1.__dict__)
{'_company': 'Koreanair', '_details': {'uniform_color': 'skyblue', 'kind': 'FSC', 'price': 8000}}

> print(Airline2.__dict__)
{'_company': 'Asiana', '_details': {'uniform_color': 'gray', 'kind': 'FSC', 'price': 6000}}
```

<br>

## 2.2 \_\_doc\_\_

> **_multi-line으로 입력된 comment가 출력되는 magic method_**

- 코멘트를 입력할 때는 # 도 되지만, `""" """` 을 통해서 multi-line으로 입력할 수 있다.
- 상세한 설명을 적으면 doc 예약어를 호출하여 다른 사람들이 확인할 수 있다.
- 필수적인 건 아니지만, 이러한 원칙을 정해서 개발하는게 실력 향상에 좋다.
- 그리고, 이런 게 하나 하나 모여서 실력 있는 개발자가 된다.

```yml
# 클래서로 접근한다.
# Intro에 작성한 코드에서 """  """ 사이에 적은 comment가 출력된다.
> print(Airline.__doc__)

    Airline class
    Author: Kim
    Data: 2022.03.16
    Description: Class, Static, Instance Method
```

---

<br>

# 3. Method의 3종류

> **_Method의 세 종류: class method, Instance method, Static Method_**

- **Method를 만들어서 사용하는 이유**

  - _class에 속한 모든 변수들(class variables, instance variables)에 직접 접근해서 사용하는 것보다, method를 통해 사용하는 방법이 '캡슐화' 성질을 고려했을 때, 좋은 방법이기 때문이다._

- **Static method는 그럼 무엇인가??**

  - python 전문 서적을 보면 static method가 굳이 필요한지 의문성을 보이는 만큼 반드시 필요하진 않지만 개념적으로 알고 있자.
  - 클래스 변수, 인스턴스 변수를 받기에 조금 적절하지 않을 때 클래스 변수와 인스턴스 변수를 사용하지 않는 대책으로 사용한다. 그래서 위 method들과는 달리, 아무것도 받지 않는다.
  - 또한, `@staticmethod`라는 decorator를 입력한다.

  | Method    | Class method                | Instance method               | Static method                     |
  | --------- | --------------------------- | ----------------------------- | --------------------------------- |
  | Parameter | cls                         | self                          | X                                 |
  | Purpose   | 클래스 변수를 사용하기 위해 | 인스턴스 변수를 사용하기 위해 | 옆 두 method에 대한 대책으로 사용 |
  | Decorator | @classmethod                | x                             | @staticmethod                     |

- [[TIL] Python basic 14: class - class method, instance method](https://jeha00.github.io/post/python_basic/python_basic_14_class/#3-self-%EC%9D%98-%EC%9D%B4%ED%95%B4-class-method-instance-method/) 와 [[TIL] Python basic 14: class - class, instance variable](https://jeha00.github.io/post/python_basic/python_basic_14_class/#4-class-instance-variable) 를 참고하자

```yml
### Intro code에서 각 method는 다음과 같다.

## Instance Method(self):
# 첫 번째 인자로, self 즉 instance를 받는다는 걸 알 수 있다.
>   def detail_info(self):
>        print('Current ID: {}'.format(id(self)))
>        print('Airline Detail Info: {} {}'.format(self._company, self._details.get('price')))

>   def get_price(self):
>        return 'Before Airline ticket price -> Company: {}, price: {}'.format(self._company, self._details.get('price'))


>   def get_price_culc(self):
>        return 'After Airline ticket price -> Company: {}, price: {}'.format(self._company, self._details.get('price') * Airline.price_per_raise = 1.0)

## Class Method(cls):
# @classmethod라는 데코레이터를 입력한 것과
# 첫 번째 인자로, cls == class 를 받는다는 걸 알 수 있다.
>   @classmethod
>    def raise_price(cls,per)
>         if per <= 1:
>           print('Please Enter 1 or More')
>           return
>         cls.price_per_raise = per
>         print('Succeed! price increased')

## Static Method
# @staticmehod라는 데코레이터를 입력한 걸 알 수 있다.
>   @staticmethod
>   def is_Koreaair(inst):
>     if inst._company == 'Koreanair':
>       return 'OK! This car is {}'.format(inst._company)
>     return 'Sorry! This is not Koreanair'

```

- 각 method를 사용하여 variable에 접근하자.

```yml

## instance의 _details에 있는 price 정보에 접근하자.
# 직접 접근하기
> print(Airline1._details.get('price'))
8000

# method를 통해 접근하기
> print(Airline1.get_price())
Before Airline ticket price -> Company: Koreanair, price: 8000


## class variable인 price_per_raise에 접근하자.
# 직접 접근하기
> Airline.price_per_raise = 1.4

# method를 통해 접근하기
> Airline.raise_price(1.4)
Succeed! price increased

> print(Airline1.get_price_culc())
After Airline ticket price -> Company: Koreanair, price: 12000.0


## static method
# airline이 Koreanair인지 아닌지 확인해보자.
# instance로 접근하든, class로 접근하든 유연하다.

# instance로 접근하기: 어느 instance로 접근하든 넘겨주는 값에 따라 결과가 나온다.
> print(Airline1.is_koreanair(Airline1))
OK! This car is Koreanair

> print(Airline1.is_koreanair(Airline2))
Sorry! This is not Koreanair

# class로 접근하기
> print(Airline.is_koreanair(Airline1))
OK! This car is Koreanair
```

---

## Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [[TIL] Python basic 14: 파이썬 클래스(class)](https://jeha00.github.io/post/python_basic/python_basic_14/)
- [[TIL] Python basic 43: Underscore](https://jeha00.github.io/post/python_basic/python_basic_43_underscore/)
