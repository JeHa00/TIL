# Intro

> 1. [Map](#1-map)
> 2. [Filter](#2-filter)
> 3. [Reduce](#3-reduce)

- [[TIL] Python basic 31: First-class](https://jeha00.github.io/post/python_basic/python_basic_31_firstclass/)에서 학습한 고위 함수(High-Order Function)의 대표적인 예인 map, filter, reduce function에 대해 집중적으로 학습하기 위해 작성한다.
- 그래서 고위 함수(High-Order Function)란 무엇인지 간단히 알아보고, 각 fuction에 대해 기존에 했던 예제에 추가하여 더 알아보자.

<br>

- **High - Order Function (고위 함수)란??**

  - 함수들 중 인수로 전달 가능하고, 결과값으로서 반환 가능한 함수
  - 일급 함수(first - class)의 특징이기도 한다.

- 이번 예에서 lambda function을 사용할 것이다.
  - [[TIL] Python basic 12: Method](https://jeha00.github.io/post/python_basic/python_basic_12/#5-%EB%9E%8C%EB%8B%A4lambda-%ED%95%A8%EC%88%98-%EC%9D%B5%EB%AA%85%ED%95%A8%EC%88%98) 에서 학습했으므로, 이를 참조한다.

<br>

---

# 1. Map

> - map(func, iterable)
> - iterable에 있는 모든 요소에 지정한 function을 적용하여 그 결과를 iterator로 반환한다.
> - return 된 객체는 map oject 다.

- map의 인자로 넘어가는 function은 3가지 방식으로 구현해본다.
  - lambda (Ex1-1)
  - def (Ex1-2)
  - closure (Ex1-3)

```yml
> digist1 = [x * 10 for x in range(1, 6)]
> print('Ex1 > ', digist1)
Ex1 >  [10, 20, 30, 40, 50]

> result = map(lambda x: x ** 2, digist1)
> print('Ex1-1 lambda > 'result)
Ex1-1 lambda >  <map object at 0x000002786FE62D90>

# map object로 뜨기 때문에 type conversion을 한 후, result에 할당한다.
# lambda를 사용했기 때문에, 메모리에 저장되지 않고 garbage collector에 의해서 제거된다.

> result = list(map(lambda x: x ** 2, digist1))
> print('Ex1-1 lambda > 'result)
Ex1-1 lambda >  [100, 400, 900, 1600, 2500]

# lambda로 구현한 함수를 선언형으로 해보자.

> def ex2_func(x):
>   return x ** 2

> result = list(map(ex2_func, digist1))
> print('Ex1-2 function > ', result)
Ex1-2 function >  [100, 400, 900, 1600, 2500]

# closure를 통해서 선언해보자.
> def also_square(nums):
>   def double(x):
>       return x * 2
>   return map(double, nums)

> print('Ex1-3 Closure', list(also_square(digist1)))
Ex1-3 Closure >   [20, 40, 60, 80, 100]
```

<br>

---

# 2. Filter

> - filter(func, iterable)
> - iterable 중에서 function 조건에 True인 요소들만 뽑아서 새로운 sequence 형으로 만드는 함수
> - return된 객체는 filter object 다.

- filter의 인자로 넘어가는 function은 2가지 방식으로 구현해본다.
  - lambda (Ex1-1)
  - closure (Ex1-3)

```yml

# list comprehension으로 iterator를 만든다.
> digist2 = [x for x in range(1, 6)]

# 홀수만 출력한다.

# lambda 사용
> result = list(filter(lambda x: x % 2, digist2))
> print('Ex2-1 lambda > ', result)
Ex2-1 lambda >  [1, 3, 5]

# closure 사용
> def odd(nums):
>   def is_oven(x):
>       return x % 2
>   return filter(is_oven, nums)

> print('Ex2-2 closure', odd(digist2))
Ex2-2 closure >  [1, 3, 5]
```

<br>

---

# 3. Reduce

> - reduce(func, iterable)
> - iterable의 각 요소를 왼쪽부터 오른쪽 방향으로 function을 적용하여 하나의 값으로 합친다.

- reduce는 built-in fuction이 아니기 때문에, 별도로 import를 해야 한다.
- from functools import reduce

- reduce의 인자로 넘어가는 function은 2가지 방식으로 구현해본다.
  - lambda (Ex1-1)
  - closure (Ex1-3)

```yml
> from functools import reduce

> digit3 = [x for x in range(1, 101)]

# lambda 사용
> reduce = reduce(lambda x,y: x + y, digit3)
> print('Ex3 lambda > ', reduce)
5050

# closure 사용
> def also_add(nums):
>   def add_plus(x,y):
>       return x + y
>   return reduce(add_plus, nums)

> print('Ex3 Closure > ', also_add(digit3))
5050
```

<br>

---

# Reference

- [모두를 위한 파이썬 : 필수 문법 배우기 Feat. 오픈소스 패키지 배포 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B3%A0%EA%B8%89/dashboard)
- [[TIL] Python basic 31: First-class](https://jeha00.github.io/post/python_basic/python_basic_31_firstclass/)
