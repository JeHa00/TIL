# Intro

> 1. [Decorator가 중요한 이유](#1-decorator가-중요한-이유)
> 2. [Closure의 기본 패턴](#2-closure의-기본-패턴)
> 3. [Decorator 실습 예제](#3-decorator-실습-예제)

<br>

- Closure에 이어서 decorator(데코레이터)에 대해 알아보자.

<br>

---

# 1. Decorator가 중요한 이유

- 데코레이터를 사용하기는 쉽지만, 작성하기는 어렵다고 한다.
- 왜냐하면 여러가지 개념들이 합쳐져 있기 때문이다.
- 파이썬의 전반적인 과정을 이해해야 가능하다.

  1. Closure(클로저) -> [[TIL] Python basic 33: Closure](https://jeha00.github.io/post/python_basic/python_basic_33_closure/)
  2. firt-class(일급 함수) -> [[TIL] Python basic 31: First-class](https://jeha00.github.io/post/python_basic/python_basi)
  3. 가변 인자(\*args, \*\*args) ->[[TIL] Python basic 12: Method](https://jeha00.github.io/post/python_basic/python_basic_12/#3-packing-unpacking)
  4. 인자 풀기(unpacking) ->[[TIL] Python basic 12: Method](https://jeha00.github.io/post/python_basic/python_basic_12/#3-packing-unpacking)
  5. 파이썬이 소스 코드를 불러오는 자세한 과정

  - from [파이썬 데코레이터를 작성하는 법을 배워야 하는 5가지 이유](https://www.hanbit.co.kr/media/channel/view.html?cms_code=CMS5689111564)

<br>

- 그렇다면 왜 데코레이터를 배워야하는가???

  - 장점

  1. 중복 제거, 코드 간결, 공통 함수 작성

     - 프로그래밍 언어의 패러다임이 이런 방식으로 진행된다.

  2. 로깅, 프레임워크, 유효성 체크

     - 이러한 기능들을 가지는 함수를 만들어서 공통 기능으로 사용 가능하다.
     - 파이썬 기반 프레임 워크의 많은 비중이 데코레이터로 설계되어 있다.

  3. 조합해서 사용 용이

- 단점

  1. 가독성 감소
  2. 특정 기능에 한정된 함수는 단일 함수로 작성하는 것이 유리
  3. 디버깅 불편

  - from [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)

<br>

---

# 2. Closure의 기본 패턴

- 데코레이터는 클로저와 형태가 유사하다.
- 그래서, 특히 클로저를 이해하지 못하면 데코레이터를 만들 수 없다.
- 자바에서는 어노테이션이라 한다.

```yml
## closure의 기본 패턴

# outer function
> def perf_clock(func):

# inner function(nested function)
# closure
>   def perf_clocked(*args):
>       # inner function의 결과 반환
>       return result
>   # inner function 반환
>   return perf_clocked
```

<br>

---

# 3. Decorator 실습 예제

- decorator를 직접 구현해보자.
- 모든 함수가 실행될 때마다 performance를 체크하는 함수를 만들 것이다.
- `perf_counter`: time module에 있는 method로, 코드 실행 시간을 측정한다.

## 3.1 Decorator로 사용할 function 만들기

```yml
> import time

> def perf_clock(func):
>   def perf_clocked(*args):
>       # st = start time
>       st = time.perf_counter()
>       # outer function의 argument인 func이 inner function 안에서 실행된다.
>       # 그래서 자유 변수가 된다.
>       result = func(*args)
>       # et = end time
>       et = time.perf_counter() - st
>       # func의 이름
>       name = func.__name__
>       # arg로 구성된 문자열이 arg_str에 할당된다.
>       arg_str = ', '.join(repr(arg) for arg in args)
>       print('[%0.5fs] %s(%s) -> %r' % (et, name, arg_str, result))
>       return result
>   return perf_clocked
```

<br>

## 3.2 Decorator 없이 사용하기

- 그러면 데코레이터를 사용하지 않고, 위 function을 사용해보자.

```yml
> def time_func(seconds):
>     time.sleep(seconds)

> def sum_func(*numbers):
>     return sum(numbers)

> none_deco1 = perf_clock(time_func)
> none_deco2 = perf_clock(sum_func)

> print(none_deco1, none_deco1.__code__co_freevars)
<function perf_clock.<locals>.perf_clocked at 0x0000021629F6FCA0> ('func',)

> print(none_decow, none_deco2.__code__co_freevars)
<function perf_clock.<locals>.perf_clocked at 0x0000021629F6FD30> ('func',)

> print('-' * 40, 'Called None Decorator -> time_func')
---------------------------------------- Called None Decorator -> time_func

> none_deco1(1.5)
[1.51397s] time_func(1.5) -> None

> print('-' * 40, 'Called None Decorator -> sum_func')
---------------------------------------- Called None Decorator -> sum_func

> none_deco2(100, 150, 250, 300, 350)
[0.00001s] sum_func(100, 150, 250, 300, 350) -> 1150
```

- 위 코드의 매커니즘에 대해 알아보자.

  - 첫 번째, perf_clock(func) 의 func 인자에 `time_func`을 할당했다.
  - 두 번째, perf_clock(time_func)의 return 값인 `perf_clocked` fuction을 none_deco1에 할당했다.
  - 세 번째,

    - inner fuction인 perf_clocked(\*\*args) function의 `자유변수인 func`에 time_func가 할당된 상태로, none_deco1에 할당된다.

    - 그러면 인자 `func` 그리고 `**args` 중에서 할당되지 않은 인자는 `**args`다.

  - 다섯 번째,

    - none_deco1(1.5)를 선언하면서 `**args`에 1.5가 할당된다.

    - none_deco2(100, 150, 250, 300, 350)은 `**args`에 100, 150, 200, 300, 350이 할당된다.

- 이처럼 지난 firt-class에서 알아본 partial처럼 하나씩 고정인수를 만들어간다.
- 이 방식이 가능한 이유는 closure의 개념을 이용했기 때문이다.

<br>

## 3.3 Decorator로 사용하기

- 이번에는 데코레이터를 사용해본다.
- decorator를 사용하니 코드가 훨씬 간결해진 걸 확인해보자.

```yml
> @perf_clock
> def time_func(seconds):
>     time.sleep(seconds)

>@perf_clock
> def sum_func(*numbers):
>     return sum(numbers)

> print('*' * 40, 'Called Decorator -> time_func')
 **************************************** Called Decorator -> time_func

> time_func(1.5)
[1.49993s] time_func(1.5) -> None

> print('*' * 40, 'Called Decorator -> sum_func')
 **************************************** Called Decorator -> sum_func

> sum_func(100, 150, 250, 300, 350)
[0.00001s] sum_func(100, 150, 250, 300, 350) -> 1150
```

- Decorator를 사용하면 별도의 변수에 함수를 할당할 필요가 없다.

- `@perf_clock`을 time_func와 sum_func 위에 각각 입력하여 time_func와 sum_func의 decorator로 사용한다.

- 위에 각각 입력하는 의미는 `perf_clock`의 `func` 인자에 time_func와 sum_func을 할당하여 고정시킨다.

- 이 다음으로 time_func(1.5) 와 time_func(100, 150, 250, 300, 350)처럼 `**args` 가변인자를 사용하여 할당한다.

<br>

- 이처럼 decorator는 closure, firt-class, 가변인자, packing & unpacking 개념을 사용한다.

<br>

---

# Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [파이썬 데코레이터를 작성하는 법을 배워야 하는 5가지 이유](https://www.hanbit.co.kr/media/channel/view.html?cms_code=CMS5689111564)
