# Python basic 10: python 흐름 제어문 (For 반복문)

<br>

# 0. Introduction

> 1. [for ~ range pattern: 3가지](#1-for--range-pattern-3가지)
> 2. [Iterables 자료형 활용과 Iterable 함수](#2-iterator-자료형-활용과-iterable-함수)
> 3. [break, continue 문](#3-break-continue-문)
> 4. [for ~ else 구문](#5-for--else-구문)

<br>

- for 문은 코딩에서 중요하다.
- 파이썬의 for 문은 다른 for 문과 달리 독자적인 특징이 있다.
- if else 처럼 for else도 가능하나, 자주 사용하지 않는다.
- 파이썬 공식 사이트에서는 다음과 같은 구조로 설명한다.
  - `<collection>` 이란 반복 가능한 객체, `iterable object (= interator)`를 말한다.
  - 그러면 어떤 게 `iterator` 인가?? `dir()`를 사용했을 때 `__iter__` 을 확인할 수 있으면 `iterator`다.
  - 예를 들어서 string, list, tuple, dictionary, set을 말한다.

```yml
> for i in <collection>:
>   <loop body>
```

<br>

---

# 1. for ~ range pattern: 3가지

- 첫 번째 패턴
  - `for n in range(j)` : 변수 n이 0부터 j가 아닌 (j-1)까지 반복된다.

```yml
> for v in range(10):
>   print("v is : ", v)
v is :  0
v is :  1
v is :  2
v is :  3
v is :  4
v is :  5
v is :  6
v is :  7
v is :  8
v is :  9
```

- 두 번째 패턴
  - `for n in range(i,j)`: n이 i부터 (j-1)까지 반복된다.

```yml
> for v in range(1, 11):
>   print("v is : ", v)
v is :  1
v is :  2
v is :  3
v is :  4
v is :  5
v is :  6
v is :  7
v is :  8
v is :  9
v is :  10
```

- 세 번째 패턴
  - `for n in range(i,j,k)`: n이 i부터 k씩 증가하여 (j-1)까지 반복된다.

```yml
> for v in range(1, 11, 2):
>   print("v is :", v)
v is : 1
v is : 3
v is : 5
v is : 7
v is : 9

> for v in range(1, 11, 3):
>   print("v is :", v)
v is : 1
v is : 4
v is : 7
v is : 10

```

- 1 ~ 1000까지 합 구하기

```yml
# 첫 번째 방법
> j = 0
> for v in range(1, 1001):
>   j += v
> print('1 ~ 1000 Sum : ', j)
1 ~ 1000 sum : 500500

# 두 번째 방법
> print('1 ~ 1000 Sum : ', sum(range(1, 1001)))
1 ~ 1000 Sum :  500500

> print('1 ~ 1000 안에 4의 배수의 합 : ', sum(range(1, 1001, 4)))
1 ~ 1000 안에 4의 배수의 합 :  124750

> print(type(range(1,11)))
<class 'range'>

```

<br>

---

# 2. Iterator 자료형 활용과 Iterable 함수

```yml

# iterable 리턴 함수 : range, reversed, enumerate, filter, map, zip
# (이런 것들 다 for 문에서 사용할 수 있다.)

# 예제1

# Iterator: string으로 구성된 list
> names = ["Kim", "Park", "Cho", "Lee", "Choi", "Yoo"]
> for name in names:
>   print("You are", name)
You are Kim
You are Park
You are Cho
You are Lee
You are Choi
You are Yoo

# 예제2
# Iterator: interger 로 구성된 list
> lotto_numbers = [11, 19, 21, 28, 36, 37]
> for number in lotto_numbers:
>   print("Current number : ", number)
Current number : 11
Current number : 19
Current number : 21
Current number : 28
Current number : 36
Current number : 37

# 예제3
# Iterator : string
> word = 'Beautiful'
> for s in word:
>   print('word : ', s)
word :  B
word :  e
word :  a
word :  u
word :  t
word :  i
word :  f
word :  u
word :  l

# 예제4
# Iterator: dictionary
> my_info = {
>   "name": "Lee",
>   "Age": 33,
>   "City": "Seoul"
>   }

# key 값들이 출력된다.
> for key in my_info:
>   print("value :", my_info[key])
value : Lee
value : 33
value: Seoul

# 또는 아래 방법으로 value만 순차적으로 출력할 수 있다.

> for val in my_info.values():
>   print(val)
Lee
33
Seoul
```

- Iterable에 사용되는 함수를 사용하여 대문자로 출력해보자.
- `<string iterator>.isupper` : 문자가 대문자인지 확인하는 함수
- `<string iterator>.islower` : 문자가 소문자인지 확인하는 함수
- `<string iterator>.upper` : 문자열을 대문자로 변경하는 함수
- `<string iterator>.lower` : 문자열을 소문자로 변경하는 함수

```yml
# Iterator : string
> name = 'FineApplE'

# 지난 시간에 배운 중첩 조건문을 의미
> for n in name:
>    if n.isupper():
>       print(n)
> else:
>   print(n.upper())
F
I
N
E
A
P
P
L
E

```

<br>

---

# 3. break, continue 문

- `break` 문: 가장 가까운 반복문을 강제로 탈출한다.
  - 내가 원하는 특정 조건에서, 멈추기 원할 때 사용된다.
  - 현업에서는 수집하는 데이터량이 매우 많기 때문에, break로 반복문을 조절하는 게 중요하다.
- `continue` 문: `break`문과 달리 특정 조건이 되면 탈출하는 것이 아니라, `continue` 문 아래의 코드가 실행되지 않고, 조건을 판단하는 곳으로 점프한다.
  - 많은 데이터 중에 내가 보기 싫은 또는 불필요하게 출력되거나 계산되지 말아야 하는 것이 list에 있을 때, 스킵할 수 있다.

```yml
# break
> numbers = [14, 3, 4, 7, 10, 24, 17, 2, 33, 15, 34, 36, 38]
> for num in numbers:
>   if num == 34:
>       print("Found : 34!")
>       break
>    else:
>        print("Not found : ", num)
Not found :  14
Not found :  3
Not found :  4
Not found :  7
Not found :  10
Not found :  24
Not found :  17
Not found :  2
Not found :  33
Not found :  15
Found : 34!


# continue
> lt = [2, 5, True, 4.3, complex(4)]
> for v in lt:
>   if type(v) is bool:
>       continue
>    print("current type : ", type(v))
>    print("multiply by 2:", v * 2)
>    print(True * 3)
current type :  <class 'int'>
multiply by 2: 4
3
current type :  <class 'int'>
multiply by 2: 10
3
current type :  <class 'float'>
multiply by 2: 8.6
3
current type :  <class 'complex'>
multiply by 2: (8+0j)
3
# true가 1이기 때문에 3이 나온다.
```

<br>

---

# 4. for ~ else 구문

- for ~ else 구문: python에만 있는 for-else 구문으로, 자주 사용하지는 않지만, 알고 있자.

```yml
> numbers = [14, 3, 4, 7, 10, 24, 17, 2, 33, 15, 34, 36, 38]
> for num in numbers:
>   if num == 34:
>       print("Found : 34!")
>       break
> else:
>       print("Not Found 45...")

```

<br>

---

# Reference

- [Python tutorial](https://www.python-course.eu/python3_formatted_output.php)
- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
