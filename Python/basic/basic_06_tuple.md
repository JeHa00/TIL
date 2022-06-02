# Python basic 6: python tuple

<br>

# Introduction

> 1. [tuple 선언](#1-tuple-선언)
> 2. [tuple indexing, slicing, 연산](#2-tuple-indexing-slicing-연산)
> 3. [tuple 함수: index, count](#3-tuple-함수-index-count)
> 4. [tuple의 중요한 특징: packing & unpacking](#4-tuple의-중요한-특징-packing--unpacking)

- list와 tuple의 차이를 알아야 비교해서 무엇을 쓸 지 결정한다.

- **tuple은 sequence형, immutable, 중복가능하기 때문에**
  - 순서가 있다.
    - 하지만, list의 순서는 변할 수 있고, tuple의 순서는 불변이다.
    - tuple의 순서는 한 번 생성되면 변경할 수 없다.
  - 중복 가능 => list와 동일
  - 수정 X => del, remove, slicing, insert 로 값 변경 X
  - list 와 마찬가지로 다양한 타입이 함께 포함될 수 있다.

---

<br>

## 1. tuple 선언

- `list`는 `대괄호`다.
- `tuple`은 `소괄호` 또는 `무괄호`다.
- 소괄호는 괄호만 해도 tuple로 인식된다.
- 무괄호는 `최소 원소 하나 이상`이어야 하며, `쉼표`가 있어야 한다.
  - 쉼표가 있어야 하는 이유는 성분 integer일 때, 쉼표가 없으면 tuple이 아닌 numeric data로 인식한다.
- 소괄호 또한 `최소 원소 하나 이상` 입력할 때, `쉼표`가 있어야 한다.

```yml

# tuple 선언

> a = ()
> b = (1,)
> b = 1,
> print(type(a))
# 위 방식 다 tuple 선언법이다.
<class 'tuple'>

> c = (11, 12, 13, 14)
> d = (100, 1000, 'Ace', 'Base', 'Captine')
> e = (100, 1000, ('Ace', 'Base', 'Captine'))

```

---

<br>

## 2. tuple indexing, slicing, 연산

- `tuple`에 indexing 사용하기

```yml
> c = (11, 12, 13, 14)
> d = (100, 1000, 'Ace', 'Base', 'Captine')
> e = (100, 1000, ('Ace', 'Base', 'Captine'))

> print('d - ', type(d), d)
d - <class 'tuple'>, (100, 1000, 'Ace', 'Base', 'Captine')

> print('d - ', d[1])
d - 1000

> print('d - ', d[0] + d[1] * 2)
d - 2100

> print('d - ', d[-1])
d - Captine

> print('e - ', e[-1])
e - ('Ace', 'Base', 'Captine')

> print('e - ', e[-1][1])
e - Base

> print('e - ', list(e[-1][1]))
e - ['B', 'a', 's', 'e']

```

- tuple의 value 수정은 안되지만, 형 변환은 가능하다.

- `tuple`에 slicing 사용하기

```yml
> print('d - ', d[0:3])
d - (100, 1000, 'Ace')

> print('d - ', d[2:])
d - ('Ace', 'Base', 'Captine')

> print('e - ', e[2][1:3])
e - ('Ace', 'Base')

```

- `tuple`로 연산하기

```yml
> print('c + d - ', c + d)
c + d - (11, 12, 13, 14, 100, 1000, 'Ace', 'Base', 'Captine')

> print('c * 3 - ', c * 3)
c * 3 -(11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14)

> print("'Test' + c[0] - ", 'Test' + str(c[0]))
'Test' + c[0] - Test11
```

---

<br>

## 3. tuple 함수: index, count

- index(): 원하는 성분 값의 index를 구하는 function
- count(): 원하는 성분의 수량을 구하는 function

```yml
> a = (5, 2, 3, 1, 4)
> print('a - ', a)
a - (5, 2, 3, 1, 4)

> print('a - ', a.index(5))
a - 0

> print('a - ', a.count(4))
a - 1
```

<br>

## 4. tuple의 중요한 특징: packing & unpacking

- `packing`이란 단어 그대로의 의미로, 하나로 묶는 것을 말한다.
- `unpacking`은 하나로 묶여있던 tuple을 풀어서 각각 할당하는 것을 말한다.

```yml
# packing
> t = ('foo', 'bar', 'baz', 'qux')

#출력 확인
> print(t)
('foo', 'bar', 'baz', 'qux')

> print(t[0])
foo

> print(t[-1])
qux

# unpacking 1
> (x1, x2, x3, x4) = t

# 출력 확인
> print(x1, x2, x3, x4)
foo bar baz qux

# unpacking 2
> (x1, x2, x3, x4) = ('foo', 'bar', 'baz', 'qux')
> print(x1, x2, x3, x4)
foo bar baz qux

# unpacking 3
> t2 = 2, 3, 4
> t3 = 4,
> x1, x2, x3 = t2
> x4, x5, x6 = 4, 5, 6

# tuple을 출력하는 것이므로 괄호가 존재한다.
> print(t2)
(2, 3, 4)

> print(t3)
(4, )

# 각 원소 값을 출력하는 것이므로 괄호가 없다.
> print(x1, x2, x3)
2 3 4

> print(x4, x5, x6)
4 5 6
```

<br>

---

# Reference

- [Python tutorial](https://www.python-course.eu/python3_formatted_output.php)
- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
