# Intro

> 1. [UnboundLocalError](#1-unboundlocalerror)
> 2. [What is closure??](#2-what-is-closure)
> 3. [Exercises for closure](#3-exercises-for-closure)

<br>

- Closure가 필요한 이유 그리고, 잘못된 사용 사례에 대해 알아보자.

<br>

---

# 1. UnboundLocalError

- 전역 변수와 지역 변수에 대해 간단히 복습해보자.

```yml
# global (전역) 변수
> c = 30

> def func_v3(a):
>    print(a)
>    # local (지역) 변수
>    print(c)
>    c = 40

> func_v3(10)
UnboundLocalError: local variable 'c' referenced before assignmnet
```

- func_v3를 정의하기 전에 c에 값을 할당했다.
- 그래서 func_v3 안에 값은 이름의 c 와 이어질거라 생각했지만, Error가 떴다.
- Error의 의미:

  - `c` 라는 변수에 값이 할당되지 않았는데, print(c)로 참조되었다.
  - 함수 블럭에서 할당하는 건 `지역 변수`이기 때문에, name이 같아도 함수 밖에서 할당했기 때문에 `전역 변수`로 인식하여 다르다.

- 해결책

  - function block 안에 `global` 선언을 하든가, `nonlocal` 선언을 한다.
  - local scope에서 전역으로 쓸 수 있는 방법이다.

```yml
> c = 30

> def func_v3(a):

>    global c
>    print(a)
>    print(c)
>    c = 40

# 함수 실행 전 이므로 30이 출력된다.
> print('>>',c)
30

> func_v3(10)
10
30

# 함수 실행 후에는 40이 출력된다.
> print('>>>',c)
40

```

- 해결책의 문제점

  - 함수 내에 `global` 또는 `nonlocal`을 쓰는 건 좋은 방법이 아니다.
  - 왜냐하면 함수 내에 전역 변수와 연결되는 게 있다면 _디버깅_ 할 때 쉽지 않다.
  - 그래서 위의 statement로 local에서 수정하는 건 권하지 않는다.

- 그래서 또 다른 방법이 바로 `closure(클로저)` 다.
- 이 클로저에 대해 알아보자.

<br>

---

# 2. Closure

<br>

## 2.1 What is closure ??

> [Reference에 따른 closure 정의]  
> 외부에서 호출된 함수의 변수값, 상태(레퍼런스)를 복사 후 저장. 그 후에 접근(액세스)이 가능하도록 하는 도구

- scope을 기준으로 설명하자면

  - **Closure란 enclosing scope에 있는 자유변수(free variable)를 이 scope의 실행이 종료되도 보유하고 있는 내부 함수 또는 중첩함수를 말한다.**

  - 자유변수(free variable)란??
    - 정의되지 않은 code block에 사용되는 변수
    - 여러 번 호출이 일어나도 상태 정보를 보유하기 위해 closure가 사용하는 원리

- closure는 outer function을 호출해서 innter function을 return 했지만, outer function의 **_enclosing scope_**에 있던 **_자유변수(free variable)를 계속해서 기억한다._**

- 그래서 함수실행이 끝나도 그 시점의 변수를 이어서 작업할 수 있다.

<br>

## 2.2 Why does we need closure ??

- 함수 안에 선언된 것들이 함수의 실행이 끝나서 소멸되면 변수 값도 사라지지만, closure를 사용하면 기억한다.

- 서버 프로그래밍의 관점에서 closure를 바라보면

  - 서버 프로그래밍에서 어려운 것이 한정된 메모리 공간에서 여러 자원이 접근하면 `교착상태(Dead lock)`에 부딪힌다.
  - 이를 해결하는 게 `동시성(Concurrency) 제어`다.
  - closure는 불변자료(immutable, Read Only) 구조 및 atom, STM 이므로 multi-thread 프로그래밍에 강점을 가진다.
  - multi-thread가 아닌 `단일 thread`인데도 동시성을 갖도록 하는 기반이 되는 게 바로 이 closure다.

- 또한 이 클로저는 함수형 프로그래밍에도 연결된다.

- 위 관점에 대해서는 그렇구나 정도만 알고 넘어가자. 나중에 차차 알아보자.

- 그러면 class를 사용하여 closure가 무엇인지 구현해보자.

```yml
# global scope
> class Avenager():

# enclosing scope

>   def __init__(self):
>       self._series = []
# local scope

# special method __call__은 class를 function처럼 호출해서 사용하도록 한다.
>   def __call__(self, v):
>       self._series.append(v)
>       print('inner >>> {} / {}'.format(self._series, len(self, series)))
>       return sum(self._series) / len(self._series)
# local scope

# 인스턴스 생성
> averager_cls = Averager()

# 누적
# instance를 생성했는데, function처럼 사용하고 있다.
# Avenger()를 사용하면 __call_ method의 return 값이 출력된다.
> print(averager_cls(15))
> print(averager_cls(35))
> print(averager_cls(40))
inner >>> [15] / 1
15.0
inner >>> [15, 35] / 2
25.0
inner >>> [15, 35, 40] / 3
30.0
```

- 위의 예시처럼 class 실행이 끝났는데도 불구하고, 변수가 소멸되야 하는데 유지되고 있다.
- 상태를 기억하고 있기 때문에 계속해서 누적된다.
- 그래서 중간부터 해도 이어서 할 수 있다.

<br>

---

# 3. Exercises for closure

- closure는 pattern이 정해져 있다.

```yml
# global scope
> def closure_ex1():

# closure_ex1의 local scope이면서
# averager(v)의 the enclosing scope 또는 자유영역
# 이 영역에 정의된 변수: series
>   series = []


>   def averager(v):
# averager의 local scope
>      series.append(v)
>      print('inner >>> {} / {}'.format(series, len(series)))
>      return sum(series) / len(series)

# 일급 함수의 특징: 함수를 반환할 수 있다.
>   return averager

# make a instance
> avg_closure1 = closure_ex1()

> print(avg_closure1)
<function closure_ex1.<locals>.averager at 0x000001B59D11FC10>

> print(avg_closure1(15))
inner >>> [15] / 1
15.0
> print(avg_closure1(35))
inner >>> [15, 35] / 2
25.0
> print(avg_closure1(40))
inner >>> [15, 35, 40] / 3
30.0
```

- series가 local scope에 있었다면 위 경우처럼 출력할 때 이전 값이 보존되지 않는다.
- 왜냐하면 averager가 실행이 끝나면 그 안에 local scope에 있던 변수는 소멸되기 때문이다.
- 하지만, enclosing scope에 변수를 정의했기 때문에, 함수를 실행할 때마다 자유변수에 접근해서 값이 보존된다.
- 그래서 새로 추가해도 실행이 가능하다.

<br>

- 그러면 파이썬에서 이 closure를 어떻게 취급하는지 확인해보자.

```yml
# function inspection
# __closure__ 로 취급된다.
# __call__이 있으므로, 호출할 수 있다.
> print(dir(avg_closure1))
['__annotations__', '__call__', '__class__', '__closure__', '__dict__', '__dir__', '__doc__', '__init__', '__init_subclass__', '__kwdefaults__', '__le__', '__lt__', '__module__', '__name__']

# detail한 함수 목록이 나온다.
> print(dir(avg_closure1.__code__))

> print(avg_closure1.__code__.co_freevars)
('series',)

# 자유 변수값을 출력할 수 있다.
> print(avg_closure1.__closure__[0].cell_contents)
[15, 35, 40]
```

- `__code__`: 함수가 컴파일되서 바이트코드 상태의 정보를 출력해주는 역할

<br>

---

# 4. Incorrect use of closures: nonlocal, global

- closure의 잘못된 사용법을 예제로 알아보자.

```yml
> def closure_ex3():
>     # Free variable
>     cnt = 0
>     total = 0

>     def averager(v):

# cnt와 total을 자유변수로 만든다.
>         nonlocal cnt, total
>         cnt += 1
>         total += v
>         return total / cnt

>     return averager

> avg_closure3 = closure_ex3()

> print(avg_closure3(15))
15.0
> print(avg_closure3(35))
25.0
> print(avg_closure3(40))
30.0
```

- 바로 위의 예시처럼 `nonlocal`을 사용하는 방법 그리고, UnboundLocalError를 설명할 때 언급한 `global` 을 사용하는 법이 잘못된 closure 사용법이다.
- UnboundLocalError 를 설명했을 때와는 달리 변수를 전역으로 한게 아닌 자유 변수로 만들었다.
- 그래서 closure지만, 좋은 방법이 아니다.
- 왜냐하면 함수 내에 전역 변수와 연결되는 게 있다면 _디버깅_ 할 때 쉽지 않다.

---

# Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [Python Scope & the LEGB Rule: Resolving Names in Your Code](https://realpython.com/python-scope-legb-rule/#using-enclosing-scopes-as-closures)
