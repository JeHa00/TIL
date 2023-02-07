# 0. Introduction

> 1. [일급 함수(first-class)란?](#1-일급-함수first-class란)
> 2. [익명 함수(lambda)](#2-익명-함수lambda)
> 3. [Callable 설명](#3-callable-설명)
> 4. [Partial 사용법](#3-callable-설명)
> 5. [Signature](#5-signature)

<br>

- 이번 시간에 배울 내용은 특히 더 중요하다.
- 이번 시간에는 일급 객체라고도 불리는 `일급 함수`에 대해 알아보겠다.
- 그 후, `일급 함수`의 예인 lambda, callable, partial에 대해 알아본다.

---

# 1. 일급 함수(first-class)란??

> - **_일급 함수(일급 객체, first-class)란??_**
>   - **_객체 취급되는 함수_**
> - **_다음 4가지 특징을 가지는 함수를 말한다._**
>   - **_1. 런타임 초기화: 실행 시점에서 초기화한다._**
>   - **_2. 함수를 변수에 할당 가능하다._**
>   - **_3. 함수를 인수로 전달 가능하다. (Higher - order function의 첫 번째 특징)_**
>   - **_4. 함수를 결과값으로서 반환 가능하다. (Higher - order function의 첫 번째 특징)_**

- 위 일급 함수의 특징들은 `파이썬 함수의 특징`이라고 할 수 있다.
- 그러면 일급함수는 왜 중요한가??

  - 일급함수를 알아야 **_'함수형 프로그래밍'_** 을 할 수 있기 때문이다.
  - **'함수형 프로그래밍'** 이란??
    - side effect를 허용하지 않는 순수 함수(pure function)를 지향하여 동시에 여러 thread에서 문제 없이 동작하는 프로그램을 쉽게 작성하는 방식

- 이러한 이유로 `일급 함수`에 대해 알아보자.
- 일급 함수가 가지는 특징을 모두 예제로 구현해볼 것이다.

<br>

## 1.1 객체 취급되는 함수

- **객체**란 무엇인가???

  - [[TIL] Python basic 14: class](https://jeha00.github.io/post/python_basic/python_basic_14_class/)에 따르면 소프트웨어로 구현할 대상이라 했다.

- 하지만 파이썬 내부에서의 객체의 정의와 특징은 무엇일까???

  - 파이썬이 data를 추상화(abstraction)한 것을 말하며,
  - id(identity), type(형) 그리고, value(값)를 가지는 걸 말한다.
  - 파이썬의 모든 데이터는 객체나 객체 간의 관계로 표현된다.
  - 객체 id는 메모리 상에서 객체의 주소이며, id는 만들어진 후에는 변경되지 않는다.

    - from [데이터 모델: 객체](https://docs.python.org/ko/3/reference/datamodel.html#objects-values-and-types)

  - attribute란 점표현식을 사용하는 이름으로 참조되는 객체와 결합한 값(value)
    - [용어집 - python 3.10.4](https://docs.python.org/ko/3/glossary.html?highlight=%EC%86%8D%EC%84%B1)

- **함수 객체** : 함수처럼 행동하는 객체 from [함수 객체의 장점](https://namoeye.tistory.com/entry/%ED%95%A8%EC%88%98%EA%B0%9D%EC%B2%B4%EB%9E%80)

- 그럼 코드 상에서 확인해보자.

````yml
> def factorial(n):
>     ```Factorial Function -> n : int ```
>     if n == 1:
>         return 1
>     # 이렇게 함수 내에서 함수를 호출하는 걸 재귀함수라 한다.
>     return n * factorial(n-1)

> class A:
>     pass

> print(factorial(6))
720

# 함수 comment 출력
> print(factorial.__doc__)
Factorial Function -> n : int

# 함수를 인자로서 넘겼다.
> print(type(factorial), type(A))
<class 'fuction'>, <class 'type'>

# 함수 또한 객체임을 확인했다.
> print(id(factorial))
id -  2416266045904

# dir은 객체가 가지고 있는 속성(attribute)를 출력하는 함수다.
> print(dir(factorial))
['__annotations__', '__call__', '__class__', '__closure__', '__code__', '__defaults__', '__delattr__', '__dict__', '__dir__', '__doc__', '__eq__', '__format__', '__ge__', '__get__', '__getattribute__', '__globals__', '__gt__', '__hash__', '__init__', '__init_subclass__', '__kwdefaults__', '__le__', '__lt__', '__module__', '__name__', '__ne__', '__new__', '__qualname__', '__reduce__', '__reduce_ex__', '__repr__', '__setattr__', '__sizeof__', '__str__', '__subclasshook__']


# class와 동일하게 가지는 속성들을 빼서 함수만 가진 속성들을 확인할 수도 있다.
> print(set(sorted(dir(factorial))) - set(sorted(dir(A)))
{'__call__', '__defaults__', '__closure__', '__kwdefaults__', '__code__', '__globals__', '__name__', '__get__', '__annotations__', '__qualname__'}

# 함수의 이름을 알려준다.
> print(factorial.__name__)
factorial

# type과 위치, 코드의 몇 번째 줄인지를 알려준다.
> print(factorial.__code__)
<code object factorial at 0x00000201685D8D40, file "c:\Users\rudtl\Desktop\Dev\Python_lecture\InflearnOriginal\Level_2_중급\p_chapter05_01.py", line 35>

````

<br>

## 1.2 변수로 할당되는 함수

- 함수 또한 객체로 취급되는 걸 확인했다.
- 다음으로 이 함수가 변수에 할당되는지 확인해보자.

```yml
# 1.1 코드와 이어진다.

# 변수에 할당
> var_func = factorial

> print(var_func)
<function factorial at 0x0000023294ADE9D0>

> print(var_func(10))
3628800
```

- 변수에 할당되어 여러 함수에 사용될 수 있다는 걸 확인했다.

<br>

## 1.3 고위 함수의 두 가지 특징

> **_Higher - order function (고위함수)의 특징_**  
> **_- 1. 함수를 인수로 전달 가능하다._**  
> **_- 2. 함수를 결과값으로서 반환 가능하다._**

- 고위 함수의 대표적인 예로는 `map`, `filter`, `reduce`, `lambda` 등이 있다.

- 그러면 코드로 확인해보자.

```yml
## range 함수로 iterator를 만들고, iteraotr의 각 성분들이 vap_func의 인자로 넘어간다.
# 다음처럼 object로 출력된다면 type conversion을 해야 한다.

# map 함수의 인자로 전달 가능하다.
> print(map(vap_func, range(1,6)))
<map object at 0x000001C1FC0A0DF0>

> print(list(map(vap_func, range(1,6))))
[1, 2, 6, 24, 120]

# var_func 을 결과값으로 반환 가능하다.
> print(list(map(var_func, filter(lambda x: x % 2, range(1,6)))))
[1, 6, 120]

## list comprehension을 사용하기
# 위와 동일한 출력값을 갖는다. 하지만, 가독성이 더 좋다.
> print([var_func(i) for i in range(1, 6) if i % 2])
[1, 6, 120]
```

- `reduce` : 여러 원소를 하나의 원소로 줄이기 위해, 왼쪽에서부터 오른쪽 방향으로 축적하며 함수를 적용해간다.

```yml
## reduce

> from functools import reduce
> from operator import add

> print(list(range(1,11)))
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# 인수 하나 하나를 줄여나가면서 더해가는 함수
> print(reduce(add, range(1, 11)))

# 하지만 reduce는 잘 사용하지 않고, sum을 사용한다.
> print(sum(range(1, 11)))
```

<br>

---

# 2. High - order functions

## 2.1 익명 함수(lambda)

> **_이름 없는 함수로, 익명 함수다. 그래서 익명 함수가 복잡할 때, 주석을 사용해야 한다._**  
> **_하지만, 되도록 함수를 만들어서 사용하자. 일반 함수 형태로 `refactoring`을 권장한다._**

```yml
> print(reduce(lambda x, t : x + t, range(1,11)))
55
```

<br>

## 2.2 Callable

> **_호출 연산자로 함수, 클래스 인스턴스, 메서드 등이 호출 가능한지 확인하는 함수다. 이를 구체적으로 확인하는 방법은 specail method인 `__call__` method의 존재 유무를 확인하는데, 이 method가 있으면 True다._**  
> from [What is a 'callable'](https://stackoverflow.com/questions/111234/what-is-a-callable)

- 호출한다는 건 무슨 의미일까???

```yml
# 아래 예시처럼 함수를 불러와서 사용할 수 있는 걸 의미한다.
> str(3)

> var_func(5)

# 하지만 다음 같은 경우는 호출할 수 없는 함수다.
> 3.14(334)
```

- 이를 `callable`로 확인해보자.

```yml
> print(callable(str), callable(list), callable(var_func), callable(3.14))
True True True False
```

- `3.14`는 호출할 수 없다는 걸 `callable`을 통해 간단히 확인했다.

<br>

---

## 2.3 Partial

> **_인수를 고정할 때 사용하는 함수로, 콜백 함수에 사용하기 때문에 매우 중요하다._**

- 코드로 알아보자.

```yml
> from operator import mul
> from functools import partial

# mul은 multiply의 약어다.
> print(mul(10, 10))
100

# 그러면 인수 하나를 고정하여, 함수를 변수에 할당하자.
> five = partial(mul, 5)

> print(five(10))
50

# 한 번도 고정한다면??
> six = partial(five, 6)
> print(six())
60
```

<br>

## 2.4 Signature

> **_signature(callable, \*, follow_wrapped=True) 형식으로 인자로 callable을 취하고, annotation을 반환한다._**

- `signature` 함수는 `inspect` module에서 import한다.
- `inspect` module은
  - 모듈은 모듈, 클래스, 메서드, 함수, 트레이스백, 프레임 객체 및 코드 객체와 같은 라이브 객체에 대한 정보를 얻는 데 도움이 되는 몇 가지 유용한 함수를 제공한다.
- 예를 들어
  - 클래스의 내용을 검사하거나,
  - 메서드의 소스 코드를 꺼내오거나,
  - 함수의 인자 리스트를 추출하고 포맷하거나,
  - 자세한 트레이스백을 표시하는 데 필요한 모든 정보를 얻는 데 도움이 될 수 있다.
  - from [Inspect: 라이브 객체 검사](https://docs.python.org/ko/3/library/inspect.html)

```yml
> from inspect import signature

> sg = signature(var_func)

> print(sg)
(n)

> print(sg.parameters)
OrderedDict([('n', <Parameter "n">)])

> def foo(a, *, b:int, **kwargs):
>    pass

> sig = signature(foo)

> print(str(sig))
(a, *, b:int, **kwargs)

> print(str(sig.parameters['b']))
'b:int'

> print(sig.parameters['b'].annotation)
<class 'int'>
```

---

# Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [데이터 모델: 객체](https://docs.python.org/ko/3/reference/datamodel.html#objects-values-and-types)
- [Inspect: 라이브 객체 검사](https://docs.python.org/ko/3/library/inspect.html)
- [용어집 - python 3.10.4](https://docs.python.org/ko/3/glossary.html?highlight=%EC%86%8D%EC%84%B1)
- [What is a 'callable'](https://stackoverflow.com/questions/111234/what-is-a-callable)
