# Intro

> 1. [Thread, Multi-thread 란???](#1-thread-multi---thread란)
> 2. [코루틴(Coroutine)이란??](#2-코루틴coroutine이란)
> 3. [코루틴 기본예제 1: 코루틴 이해하기](#3-코루틴-기본예제-1-코루틴-이해하기)
> 4. [코루틴 기본예제 2: 상태값 확인하기](#4-코루틴-기본예제-2-상태값-확인하기)
> 5. [코루틴 기본예제 3: 중첩 코루틴 처리](#5-코루틴-기본예제-3-중첩-코루틴-처리)
> 6. [Summary](#6-summary)

<br>

---

# 1. Thread, Multi - thread란??

- 코루틴이 왜 필요한지 이해하기 위해서,
- 먼저 `Thread`, `Multi-thread` 가 무엇인지 이해해야 한다.
- 그리고, 이들을 이해하기 위해서는 `process`가 뭔지 알아야 한다.
- 그러면 이 개념들에 대해 알아보자.

<br>

- `process`는 코드로 구현된 파일인 `program`이 실행된 것을 의미한다.
- `Thread(쓰레드)`는 '한' process 내에서 나뉘어진 하나 이상의 실행 단위를 말한다.
- 만일 이 thread가 프로세스 내에 하나만 존재하면 `단일 스레드` 상태로, 이 때 **실행 단위는 프로세스 자체가 된다.**
- 하지만, 한 프로세스 내에서 분리하여 여러 thread로 나뉘어 실행 단위가 나눠지고 많아지면 이를 `Multi-thread`라 한다.

  - 한 process 내부의 각 thread가 한 applicaiton이 여러 가지 일을 처리해야하는 상황에서 각 작업들을 담당하는 거다.

  - multi-thread 장점

    - 한 process 내에 thread끼리는 긴밀히 연결되어 있다.
    - 한 process 내에 있으므로, 공유된 자원으로 통신 비용을 절감할 수 있다.
    - 공유된 자원으로 메모리가 효율적이다.
    - **multi-process보다 _Context switching_ 비용이 적다.**

  - multi-thread 단점
    - 하나의 thread에 문제가 생기면 다른 여러 thread에도 영향을 준다.

- `Single thread`(단일 thread)를 하다가 속도를 높이기 위해 `multi-thread`를 사용하기 시작하면,
  - thread 끼리 공유되는 자원 때문에 교착 상태(Dead Lock)가 발생할 수 있다.
  - 또한, **_single thread에 비해 `context switching` cost가 크다._**
  - 또한, 자원 소비 가능성이 증가한다.
- 이런 이유로 Single thread가 더 빠른 이유가 switching cost 때문이다.

<br>

---

# 2. 코루틴(Coroutine)이란??

> - Cooperative routine의 약어로, 서로 협력하는 routine을 의미한다.
> - Generator의 특별한 형태로, yield와 send를 통해서 main routine과 sub routine의 데이터 상호 교환 방식을 말한다.

- 코루틴은 위의 의미를 가지고 있지만, `thread`의 관점에서 보자면 `single thread` 환경에서 `yield` keyword를 통해서 stack을 기반으로 동작하는 비동기 작업이다.

- 함수에서 `single thread`에서도 동시성이 가능하도록 프로그래밍 언어가 발전하고 있는데,

- 바로 코루틴이 이런 발전의 한 예이다.

- coroutine은 routine 실행 중 중지 후, 다시 이어서 실행 가능한 `동시성` 프로그래밍 방법이다.

  - 그래서 coroutine은 thread에 비해서 overhead가 감소된다.
  - overhead: 어떤 처리를 하기 위해 들어가는 간접적인 처리 시간 · 메모리

- 흐름 제어: 메인 루틴에서 서브 루틴을 호출하면 서브루틴에서 수행한다.

<br>

- 이어지는 예제들을 통해서 아래 3가지를 유심히 보자.

  - Coroutine을 어떻게 제어하는지
  - 상태 저장
  - 양방향 전송

<br>

---

# 3. 코루틴 기본예제 1: 코루틴 이해하기

<br>

- 앞으로 `def`를 보면 단지 function이라 생각하지 말자.

  - `def`로 function, generator, coroutine을 구현할 수 있기 때문이다.

- for문에서는 예외가 잡히기 때문에, `StopIterator`가 발생되지 않는다.

<br>

```yml
## Generator

## sub routine
> def corountine1():
>   print('>>> coroutine started')

# yield는 외부 main routine에서 주는 값을 받아오고, 이 yield가 i로 할당된다.
>   i = yield
>   print('>>> coroutine received: {}'.format(i))

## main routine: 함수를 호출하기 때문에

> cr1 = coroutine1()

> print(cr1, type(cr1))
<generator object coroutine1 at 0x00000158B4A1EB30> <class 'generator'>

> next(cr1)
 >>> coroutine started.

> next(cr1)
 >>> coroutine received : None
 StopIteration
```

- 위 코드에서는 sub routine이 main routine에 주는 것 밖에 하지 못 했다.
- 그러면 어떻게 main과 sub가 상호작용 할 수 있을까??
- `send`를 통해서 main routine과 sub routine이 상호작용할 수 있다.
- `send`를 사용해야 yield keyword가 값을 받을 수 있다.
- 이것이 바로 `coroutine`이다.

  > Generator의 특별한 형태로, yield와 send를 통해서 main routine과 sub routine의 데이터를 상호 교환할 수 있다.

<br>

```yml
> cr1 = coroutine1()

# yield 지점까지 sub routine 실행
> next(cr1)
 >>> coroutine started.

## 값 전송
> cr1.send(100)
 >>> coroutine received : 100
 StopIteration
```

<br>

- `send`와 `next` 비교
  - 위 code를 통해서 `send`도 `next` 기능이 포함된 걸 알 수 있다.
  - 하지만, `next`는 값을 sub routine에 보낼 수 없고, `send`만 보낼 수 있다는 차이가 있다.
- send() 에 100을 입력하니, 'coroutine received: 100'이 출력되었다.
- main routine에서 보낸 100이 sub routine인 `coroutine1`의 `yield`가 받았고, 이를 i에 할당한 걸 알 수 있다.

<br>

- 그러면 `send`를 잘못 사용한 경우를 알아보자.

```yml
> cr1 = coroutine1()

# next(cr1)을 생략하고, 바로 send한다.

# 시작되지 않고 보내는 상황이다.

## 값 전송
> cr1.send(100)
TypeError: can't send non-None value to a just-started generator
```

<br>

- Error를 통해 알 수 있는 건 sub-routine의 yield 지점에서 context가 멈춰야, send로 보냈을 때 받을 수 있다는 걸 알 수 있다.
- 즉, `next`를 먼저 실행하여, `coroutine`을 실행한 후 `yield` keyword를 실행해야 한다.

<br>

---

# 4. 코루틴 기본예제 2: 상태값 확인하기

> GEN_CREATED: 처음 대기 상태를 뜻한다.
> GEN_RUNNING: 실행 상태를 뜻한다.
> **GEN_SUSPENDED: yield 대기 상태**를 뜻한다.
> GEN_CLOSED: 실행 완료 상태를 뜻한다.

<br>

```yml
# 이번에는 매개변수가 존재
> def coroutine2(x):
>   print('coroutine started : {}'.format(x))

## main과 sub routine의 양방향 통신 -> 동시성 개발
# 좌변 = 우변
# 좌변은 main routine에서 sub routine으로 입력을 받는 것
# 우변은 sub routine에서 나한테 준 것
>   y = yield x
>   print('coroutine y received : {}'.format(y))
>   z = yield x + y
>   print('coroutine z received : {}'.format(z))

> cr3 = coroutine2(10)

> print(next(cr3))
coroutine started : 10
10

> print(cr3.send(100))
coroutine y received : 100
110

> print(cr3.send(100))
coroutine z received : 100
Stop Iteration
```

<br>

- 상태값 확인을 해보겠다.
- 상태값 확인은 `getgeneratestate`를 import하는 것부터 시작한다.

```yml
> from inspect import getgeneratorstate

> cr3 = coroutine2(10)

> print(getgeneratorstate(cr3))
GEN_CREATED

> next(cr3)
coroutine started : 10

> print(getgeneratorstate(cr3))
GEN_SUSPENDED

> cr3.send(100)
coroutine y received : 100

> print(getgeneratorstate(cr3))
GEN_SUSPENDED

> cr3.send(20)
coroutine z received : 20
StopIteration
```

<br>

---

# 5. 코루틴 기본예제 3: 중첩 코루틴 처리

```yml
> def generator1():
>   for x in 'AB':
>       yield x
>   for y in range(1,4):
>       yield y

> t1 = generator1()

> print(next(t1))
A

> print(next(t1))
B

# 이어서 출력되는 걸 알 수 있다.
> print(next(t1))
1

> print(next(t1))
2

> print(next(t1))
3

> print(next(t1))
StopIteration

> t2 = generator1()

> print(list(t2))
['A', 'B', 1, 2, 3]
```

<br>

- `yield from`을 사용해서 위 code를 구현해보자.
- 그냥 `yield`보다 더 직관적으로 이해가 가능하다.

<br>

```yml
> def generator2():

# from 뒤에 iterator가 다 소진될 때까지 반환하겠다.
>     yield from 'AB'
>     yield from range(1,4)


> t3 = generator2()

> print(next(t3))
A

> print(next(t3))
B

> print(next(t3))
1

> print(next(t3))
2

> print(next(t3))
3
```

<br>

---

# 6. Summary

- `coroutine`은 `generator`에서 시작되며, 동시성이 무엇인지 이해했다.
- `coroutine`은 함수가 종료되지 않은 상태에서 main routine의 code를 실행한 뒤, 다시 돌아와서 coroutine의 code를 실행한다.
- `coroutine`이 종료되지 않았으므로, coroutine의 내용도 계속 유지된다.
- 즉, **일반함수는 호출하면 코드를 한 번만 실행할 수 있지만, `coroutine`은 여러 번 실행이 가능하다**

- `coroutie`이 `generator`에서 시작되었다면, 둘의 구체적인 차이는 무엇일까???
  - `generator`는 yield로 값을 발생시켰지만, `coroutine`은 yield로 값을 받아올 수 있다.
  - 호출하는 과점에서 보면
  - 제네레이터는 next 함수(\_\_next\_\_ method)를 반복 호출하여 값을 얻어내는 방식
  - 코루틴은 next 함수(\_\_next\_\_ method)를 한 번만 호출한 뒤, `send` method를 사용하여 값을 주고, 받는 방식

<br>

---

# Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [코다의 Process vs. Thread](https://www.youtube.com/watch?v=1grtWKqTn50)
- [파이썬 강의 UNIT 41.1 코루틴에 값 보내기](https://www.youtube.com/watch?v=-ffBU0nj8b0)
