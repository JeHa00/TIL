# Python basic 8: python sets

<br>

# 0. Introduction

> 1. [Sets 선언](#1-sets-선언)
> 2. [Sets type conversion](#2-sets-type-converison)
> 3. [집합 자료형 활용: Sets 함수](#3-집합-자료형-함수-sets-함수)
> 4. [Sets 수정, 추가, 제거](#4-sets-수정-추가-제거)

<br>

- Sets은 한국어로 집합을 의미한다. 수학의 집합을 생각해보자.
- sequence X, 중복 X
  - 순서가 없기 때문에, 출력할 때마다 달라진다.
- mutable 자료형 => 수정 O, 삭제 O
- 집합 자료형 활용에 핵심이다.

---

<br>

## 1. Sets 선언

- dictionary 처럼 sets 도 다양한 선언 방식이 있다.
- 빈집합, list 형식으로도, 중괄호로도 가능하다.

```yml
> a = set()
> b = set([1,2,3,4,4])
> c = set([1, 4, 5, 6])

 # 서로 다른 자료형을 저장할 수 있다.
> d = set([1, 2, 'Pen', 'Cap', 'Plate'])

> e = {'foo', 'bar', 'bz', 'foo', 'qux'}

# 튜플도, 실수형도 넣을 수 있다.
> f = {42, 'foo', (1,2,3), 3.14159}

> print('a', type(a), a, 2 in a)
a <class 'set'> set() False

# b에 4를 중복으로 입력했지만, 출력은 하나만 나온다.
> print('b', type(b), b)
b <class 'set'> {1, 2, 3, 4}

> print('c', type(c), c)
c <class 'set'> {1, 4, 5, 6}

> print('d', type(d), d)
d <class 'set'> {1, 2, 'Pen', 'Cap', 'Plate'}

# foo를 중복으로 입력했지만, 출력은 하나만 나온다.
> print('e', type(e), e)
e <class 'set'> {'qux', 'bz', 'foo', 'bar'}

> print('f', type(f), f)
f <class 'set'> {42, 3.14159, 'foo', (1, 2, 3)}


```

---

<br>

## 2. Sets type converison

- 파이썬의 장점 중 하나: 간단한 형 변환
- `sets`에 중복으로 값을 입력해도, 중복을 허락하지 않기 때문에 type conversion 시에도 중복된 값들은 하나만 있는 걸 확인할 수 있다.
- `tuple`로 변환

```yml

> t = tuple(b)

> print('t - ', type(t), t)
t -  <class 'tuple'> (1, 2, 3, 4)

> print('t - ', t[0], t[1:3])
t -  1 (2, 3)

```

- `list`로 변환

```yml
> l = list(c)
> print('l - ', type(l), l)
l -  <class 'list'> [1, 4, 5, 6]

> print('l - ', l[0], l[1:3])
l -  1 [4, 5]

> le = list(e)
> print('le - ', type(le), le)
le -  <class 'list'> ['bz', 'foo', 'qux', 'bar']

# reversed() 함수와 함께 해보자.
> name = 'Aceman'
# id 값이 나오므로, 뒤집어진 값을 원하면 형 변환을 해야 한다.
> print('Reversed : ', reversed(name))
> print('List : ', list(reversed(name)))
> print('Tuple : ', tuple(reversed(name)))
> print('Set : ', set(reversed(name)))

Reversed :  <reversed object at 0x000001F1E690AFA0>
List :  ['n', 'a', 'm', 'e', 'c', 'A']
Tuple :  ('n', 'a', 'm', 'e', 'c', 'A')
Set :  {'m', 'a', 'c', 'e', 'n', 'A'}

# set은 출력할 떄마다 순서가 달라진다.
```

- 또한 `len` 함수로 길이를 구할 수 있다.

```yml

> print(len(a))
0

> print(len(b))
4

> print(len(c))
4

> print(len(d))
5

> print(len(e))
4

```

---

<br>

## 3. 집합 자료형 함수: Sets 함수

- **교집합**

  - `A & B`
  - `A.intersection(B)`

- **합집합**

  - `A | B`
  - `A.union(B)`

- **차집합**

  - `A - B`
  - `A.difference(B)`

- **교집합 유무 판단**

  - `A.isdisjoint(B)`: A와 B에 교집합이 존재하는가??

- **부분집합 유무 판단**

  - `A.issubset(B)`: A는 B의 부분집합인가??

- **상위집합 유무 판단**
  - `A.issupset(B)`: A는 B의 상위집합인가??

```yml
> s1 = set([1, 2, 3, 4, 5, 6])
> s2 = set([4, 5, 6, 7, 8, 9])

# 교집합
> print('l - ', s1 & s2)
> print('l - ', s1.intersection(s2))
l - {4, 5, 6}

# 합집합
> print('l - ', s1 | s2)
> print('l - ', s1.union(s2))
l -  {1, 2, 3, 4, 5, 6, 7, 8, 9}

# 차집합
> print('l - ', s1 - s2)
> print('l - ', s1.difference(s2))
l -  {1, 2, 3}

> print('l - ', s2 - s1)
> print('l - ', s2.difference(s1))
l -  {8, 9, 7}

# 중복 원소 확인
# 겹치는 원소가 없는지에 대해 알려주는 함수
# 겹치는 원소가 없으면 True, 있으면 False 다.
> print(s1.isdisjoint(s2))
False

# 부분집합 확인
# s1은 s2의 부분 집합인가요??
# 아니면 False, 맞으면 True
> print(s1.issubset(s2))
False

# 상위 집합 확인
# s1은 s2의 상위 집합인가요?
# 아니면 False, 맞으면 True
> print(s1.issuperset(s2))
False

```

---

<br>

## 4. Sets 수정, 추가, 제거

- 추가하는 건 `.add(추가하려는 원소)` 를 사용한다.
- 삭제하는 건 `.remove(삭제하려는 원소)` 또는 `.discard(삭제하려는 원소)`를 사용한다.

  - 전자는 error가 뜨지만, 후자는 error를 발생시키지 않는다.

- 모두 제거하는 건 `.clear()` 함수를 사용한다.

```yml
> s1 = set([1,2,3,4])

> s1.add(5)
> print(sl)
{1, 2, 3, 4, 5}

> s1.remove(5)
> print(sl)
{1, 2, 3, 4}

> s1.discard(4)
> print(sl)
{1, 2, 3}

> s1.clear()
> print(s1)
set()

```

<br>

---

# Reference

- [Python tutorial](https://www.python-course.eu/python3_formatted_output.php)
- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
