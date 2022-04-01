# Intro

> 1. [병행성과 병렬성이란??](#1-병행성과-병렬성이란)
> 2. [Generator](#2-generator)

<br>
  
- 병행성을 위한 방법으로 generator와 coroutine을 알아보고자 한다.  
- 저번 포스팅에서는 \_\_iter\_\_와 \_\_next\_\_ 을 알아봤으니, 이번 포스팅에서는 `generator`를 알아본다.  
- 그러면 첫 번째로 병행성이 무엇을 의미하는 건지, 이와 유사한 개념으로 병렬성은 무엇인지 학습한다.  
- 두 번째로, Generator를 ㅗ본격적으로 알아본다.

---

# 1. 병행성과 병렬성이란??

> - 병행성(Concurrency): 한 컴퓨터가 여러 일을 동시에 수행하는 것 -> 단일 프로그램 안에서 여러 일을 쉽게 해결 목적
> - 병렬성(parallelism): 여러 컴퓨터가 여러 작업을 동시에 수행하는 것 -> 속도 향상 목적

- **_병행성_** : thread는 하나지만, 마치 동시에 일을 하고 있는 것처럼 수행한다.
  - 예) 공부하다가 강의 멈춰놓고, 밥 먹고 와서 강의를 중단한 부분부터 다시 시작하는 것
- 파이썬에서는 **_병행성_** 과 **_병렬성_**을 모두 지원한다.
- 그리고, 파이썬 실력을 결정하는 중요한 내용이다.

<br>

# 2. Generator

<br>

## 2.1 Generator란?

> - 모든 값을 메모리에 올려두고 이용하는 게 아닌,
> - 필요할 때마다 한 번에 한 개의 항목을 생성해서 메모리에 올려두고 반환하는 객체.
> - 그래서 메모리를 유지하지 않기 때문에, 효율적으로 사용할 수 있다.

- Generator는 iterator의 한 종류로, 위와 같은 이유로 매우 강력한 iterator다.
- 그래서, 출력하기 위해 `next()` 를 사용한다.

- Generator function이 일반 function과의 차이는 **`yield`** statement다.

- Generator = iterator + yield

  - **`yield`**를 사용하면 generator로 인식한다.

- `yield` 또한 `return` 처럼 값을 반환한다.

- 차이점:

  - `return`을 사용할 경우 지역 변수가 사라지지만, `yield`는 local을 나가도 사라지지 않는다.

- **Generator의 장점**

  1. list comprehension, dictionary comprehension 등 데이터 양이 증가하면 메모리 사용량이 증가하는데, 이 때 제네레이터를 사용하여 메모리 사용량을 줄일 수 있다.
  2. 단위 실행 가능한 코루틴(Coroutine) 구현과 연동이 가능하다.
  3. 작은 메모리 조각으로 사용 가능하다.

## 2.2 Generator 예제

- 첫 번째 예제

```yml
# Generator Ex1
> def generator_ex1():
>     print('Start')

# yield 뒤에 값을 return하고 멈춘다.
>     yield 'A Point.'
>     print('continue')

# 멈춘 후, 다음 yield value를 return할 때까지 진행된다.
>     yield 'B Point.'
>     print('End')

> temp = iter(generator_ex1())

> print(temp)
<generator object generator_ex1 at 0x000001E8B2549510>

> print(next(temp))
Start
A Point.

> print(next(temp))
Continue
B point.

> print(next(temp))
End
StopIteration
```

- `yield` 까지 출력한 후, 다음 출력은 다음 `yield`까지 한다.
- 이처럼 위의 next처럼 `위치 인자`를 계속해서 유지하는 게 `병행성`의 핵심이다.
- `위치 인자`를 계속해서 기억하는 것 즉, 다음 할 일을 계속해서 기억하는 걸 의미한다.

<br>

- 두 번째 예제
  - 이 예제가 단순히 동일한 일을 하는 것처럼 보이지만,
  - 생성된 값을 미리 메모리에 만들어 두는 게 아닌,
  - for 문에서 필요한 때마다 generator로부터 받아온다.
  - 즉, 메모리에서 보관하지 않는다.
  - `list comprehension` 과 유사해보이지만, `소괄호()`를 사용하여 `generator expression`을 만들 수 있다.

```yml
# list comprehension
> temp2 = [x * 3 for x in generator_ex1()]

# Generator
> temp3 = (x * 3 for x in generator_ex1())

> print(type(temp2))
<class 'list'>

> print(type(temp3))
<class 'generator'>

# yield로 반환되는 값만 list로 만들어진 걸 확인했다.
> print('temp2 - ', temp2)
temp2 -  ['A Point.A Point.A Point.', 'B Point.B Point.B Point.']

# 아래와 같이 출력되기 때문에, for문에서 출력하자.
> print('temp3 - ',temp3)
temp3 -  <generator object <genexpr> at 0x000002895A5AE9E0>

# list comprehension
> for i in temp2:
>   print(i)
A Point.A Point.A Point.
B Point.B Point.B Point.

# Generator
# Generator이기 때문에, yield까지 있는 단계를 다 출력했다.
> for i in temp3:
>   print(i)
Start
A Point.A Point.A Point.
continue
B Point.B Point.B Point.
End
```

- 그러면 list comprehension과 generator를 더 자세히 비교해보자.

```yml



```

<br>

## 2.3 Generator 관련 중요 함수들

- Generator 관련 함수들은 `itertools`를 import하는 것부터 시작한다.
- 계속 복습을 하면서 활용해보도록 하자.

<br>

- 첫 번째는 `itertools.count(시작값, 증가값)` 이다.
- 시작값에서 증가하여, 증가값만큼 커져서 무한히 출력된다.

```yml

> import itertools

# 숫자 무한대로 만들기
> gen1 = itertools.count(1, 2.5)

> print(next(gen1))
1

> print(next(gen1))
3.5

> print(next(gen1))
6.0

> print(next(gen1))
8.5
```

<br>

- 두 번째는 `itertools.takewhile(predicate, iter)` 다.
- iter의 원소들 중 predicate의 조건에 참인 값들을 반환한다.
- predicate는 영어 단어 자체의 의미로는 영어 문법의 서술부를 의미한다.

```yml

# gen1 에 람다함수로 조건을 추가한다.
# 아래 조건은 range(1,1000, 2.5) 와 동일하다.
> gen2 = itertools.takewhile(lambda n : n < 1000, itertools.count(1, 2.5))


# 이렇게 for문과 같이 쓰인다.
> for v in gen2:
>     print(v)
1
3.5
...
998.5
```

- 세 번째는 `itertools.filterfalse(predicate, iter)` 이다.
- 두 번째인 `itertools.takewhile`과 반대 의미를 가진 함수다.
- iter 원소들 중에서 predicate의 조건에 부정인 값들을 반환한다.

```yml
# 필터 반대
> gen3 = itertools.filterfalse(lambda n : n < 3, [1,2,3,4,5])

> for v in gen3:
>     print(v)
3
4
5
```

- 네 번째는 `itertools.accumulate(iterable, func=operator.add)` 이다.
- iterable의 누적 합계나, 다른 이항함수 func의 누적 결과를 반환하는 iterator를 만든다.
- 총 원소 수가 n개라고 할 때,
  - iterable[0], iterable[0] + iterable[1], ..., iterable[0]+ ~ + iterable[n-1]

```yml
# 누적 합계
> gen4 = itertools.accumulate([x for x in range(1, 101)])

> for v in gen4:
>     print(v)
1
3
6
...
5050

```

- 다섯 번째는 `itertools.chanin(*itertables)`이다.
- 첫 번째 iterable에서 소진될 때까지 원소들을 반환한 후, 다음 이터러블로 넘어간다.
- 이런 식으로 iterables의 모든 iterable이 소진될 때까지 진행하는 iterator를 만든다.

```yml
# 연결1
> gen5 = itertools.chain('ABCDE', range(1,11,2))

> print(list(gen5))
['A', 'B', 'C', 'D', 'E', 1, 3, 5, 7, 9]

# 연결2
# enumerate를 통해서 index와 value를 mapping 했다.
> gen6 = itertools.chain(enumerate('ABCDE'))

> print(list(gen6))
[(0, 'A'), (1, 'B'), (2, 'C'), (3, 'D'), (4, 'E')]
```

- 여섯 번째는 `itertools.product(*iterables, repeat=1)` 다.
- 입력 이터러블들(iterables)의 데카르트 곱을 반환한다.
- 대략 제너레이터 표현식에서의 중첩된 for-루프와 동일하다.
- 예를 들어 product(A, B)는 ((x,y) for x in A for y in B)와 같은 것을 반환한다.

```yml
# 개별
> gen7 = itertools.product('ABCDE')

> print(list(gen7))
gen7 -  [('A',), ('B',), ('C',), ('D',), ('E',)]


# 연산(경우의 수)
# repeat = 2는 .product('ABCDE', 'ABCDE') 와 동일하다.
> gen8 = itertools.product('ABCDE', repeat=2)

> print(list(gen8))
[('A', 'A'), ('A', 'B'), ('A', 'C'), ('A', 'D'), ('A', 'E'),
('B', 'A'), ('B', 'B'), ('B', 'C'), ('B', 'D'), ('B', 'E'),
('C', 'A'), ('C', 'B'), ('C', 'C'), ('C', 'D'), ('C', 'E'),
('D', 'A'), ('D', 'B'), ('D', 'C'), ('D', 'D'), ('D', 'E'),
('E', 'A'), ('E', 'B'), ('E', 'C'), ('E', 'D'), ('E', 'E')]
```

- 일곱 번쨰는 `itertools.groupby(iterable, key = none)` 이다.
- (분류기준, 분류기준으로 묶인 데이터) 순서인 tuple로 값을 반환한다.

```yml

# 그룹화
> gen9 = itertools.groupby('AAABBCCCCDDEEE')

> for chr, group in gen9:
>     print(chr, ' : ', list(group))
A  :  ['A', 'A', 'A']
B  :  ['B', 'B']
C  :  ['C', 'C', 'C', 'C']
D  :  ['D', 'D']
E  :  ['E', 'E', 'E']
```

<br>

---

# Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [itertools — Function to create an etherator for efficient looping](https://docs.python.org/ko/3/library/itertools.html#itertools.product)
