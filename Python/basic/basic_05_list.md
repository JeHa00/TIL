# Python basic 5: python list

# 0. Introduction

> 1. [List 선언](#1-list-선언)
> 2. [List indexing, slicing](#2-list-indexing-slicing)
> 3. [List 연산](#3-list-연산)
> 4. [List 함수](#4-list-함수)
> 5. [List 수정, 삭제](#5-list-수정-삭제)

<br>

- List 자료형은 **sequence**형이고, **mutable**이기 때문에

  - 순서 존재한다. => len, index, slicing 이 가능
  - 중복이 가능하다.
  - 수정, 삭제가 가능하다.

- 다른 언어에서는 배열이라 하는데 알고리즘을 풀기 위해서 굉장히 중요한 자료 형태다.

<br>

---

## 1. List 선언

```python
# 빈 리스트 선언
> a = []
> print(type(a))
<class 'list'>

> b = list()
> print(type(b))
<class 'list'>

# 값은 동일하지만, id 값이 다르다.
> print(id(a), id(b))

# 정수만 list 구성
> c = [70, 75, 80 ,85]

# 문자열, 정수, 실수형으로 list 구성
> d = [1000, 1000.5, 'Ace', 'Base', 'Captine']

# list 안에 list를 넣을 수 있다.
> e = [1000, 1000.5, ['Ace', 'Base', 'Captine']]
> f = [21.42, 'foobar', 3, 4, 'bark', False, 3.14159]

```

---

<br>

## 2. List indexing, slicing

> **_list도 string처럼 sequence 형이기 때문에, len, index, slicing을 사용할 수 있다._**

- indexing: 원하는 데이터를 꺼내는 과정

```python
> print('d - ', type(d), d)
d - <class 'list'> [1000, 10000.1, 'Ace', 'Base', 'Captine']

# index의 시작은 0부터이기 때문
> print('d - ', d[1])
d - 1000.5

> print('d - ', d[0] + d[1] + d[1])
d - 3001

> print('d - ', d[-1])
d - Captine

# list의 성분이 list이기 때문에, 성분 list의 [1] 성분을 말한다.
> print('e - ', e[-1][1])
e - Base

# 문자열을 문자행 단위로 쪼개서 list로 만든다.
> print('e - ', list(e[-1][1]))
e - ['B', 'a', 's', 'e']

```

❗ 문자열을 list로 바꾸면 최소 단위로 쪼갠다.


- slicing: 같은 데이터 타입으로, 원하는 부분의 데이터를 뽑아내는 것

```python
> d = [1000, 1000.5, 'Ace', 'Base', 'Captine']
> e = [1000, 1000.5, ['Ace', 'Base', 'Captine']]

> print('d - ', d[0:3])
d - [1000, 1000.5, 'Ace']

> print('d - ', d[2:])
d - ['Ace', 'Base', 'Captine']

> print('e - ', e[2][1:3])
e - ['Ace', 'Base']

> print('e - ', e[-1][1:3])
e - ['Ace', 'Base']
```

---

<br>

## 3. List 연산

```python
> c = [70, 75, 80 ,85]
> print('c + d -', c + d)
[70, 75, 80 ,85, 1000, 1000.5, 'Ace', 'Base', 'Captine']

> print('c * 3 -', c * 3)
[70, 75, 80 ,85, 70, 75, 80 ,85, 70, 75, 80 ,85]

> print('Test + c[0] - ', 'Test' + str(c[0]))
Test + c[0] - Test70


# 값 비교
> print(c == c[:3] + c[3:])
True

# 동일한 id 값
> python = c
> print (python == c)
True

# 동일한 id가 출력된다.
> print(id(c), id(python))

# in을 통해 리스트 안에 값이 있는지 판단여부를 확인할 수 있다. 
> 85 in c 
> print(85 in c)
True
```

---

<br>

## 4. List 함수

```python

a = [5, 3, 4, 7, 8 ]

# 끝에 데이터를 삽입할 때 사용
# append가 매달다 라는 의미이므로
> a.append(6)
> print(a)
[5, 3, 4, 7, 8, 6]

# 정렬
> a.sort()
> print(a)
[3, 4, 5, 7, 8]

# 뒤집음
> a.reverse()
> print(a)
[8, 7, 5, 4, 3]

# sort와 reverse는 데이터가 많으면 오랜 시간이 걸린다.

# index(x) 는 x 값의 첫 번째 index를 출력한다.
> print('a - ', a.index(3), a[3])
a - 4 4

#  insert(추가할 위치, 추가할 값)
> a.insert(2,7)
> print(a)
[8, 7, 7, 5, 4, 3]

# count(): 원하는 값의 갯수를 새는 method다.
# a list에 7이 2개가 있으므로 출력값 2가 나온다.
> print('a - ', a.count(7))
a - 2

# extend(): 괄호 안에 값을 list에 연장한다.
> ad = [2, 1]
> a.extend(ad)
> print(a)
[8, 7, 7, 5, 4, 3, 2, 1]



```

### 결론

- a.append(): `끝에` 데이터를 삽입한다. `매달은다`
- a.sort(): 데이터를 `정렬`한다.
- a.reverse(): 데이터 방향을 `뒤집는다`.
  - 반환값이 없고, 뒤집어져서 객체에 바로 저장된다. 그러므로, 객체 출력을 따로 해야 한다.
  - reversed는 method가 아닌 함수다. 그래서 인자로 리스트를 입력한다.
- a.index(): 괄호 값의 첫 번째 `index`를 알려준다.
- a.insert(x,y): index x 번째 있는 자리에 y 값을 `삽입`한다.
- a.count(): 원하는 값의 `갯수를 세는` method
  - int를 반환한다.
- a.extend(): 괄호 안의 값을 list에 `연장`한다.
  - reverse()처럼 반환값이 없고, 객체에 바로 저장된다.

---

<br>

## 5. List 수정, 삭제

### 5.1 slicing과 index를 사용하여 수정, 삭제하는 방법

- 수정하기

  ```python
  > c = [70, 75, 80 ,85]

  ## 수정하기

  # index 번호로 접근하여 수정
  > c[0] = 4
  > print(c)
  [4, 75, 80, 85]

  # insert 되는 결과
  > c[1:2] = ['a', 'b', 'c']
  > print(c)
  [4, 'a', 'b', 'c', 80, 85]

  # 하지만 slicing이 아닌 index로 명령하면 선언한 value 그대로 원소가 된다.
  > c[1] = ['a', 'b', 'c']
  > print(c)
  [70, ['a', 'b', 'c'], 80, 85]
  # list 자체가 하나의 원소로 list에 들어갔다.


  # slicing으로 list를 원소로 넣고 싶으면 다음과 같이 한다.
  > c[1:2] = [['a', 'b', 'c']]
  > print(c)
  [70, ['a', 'b', 'c'], 80, 85]

  ## list 안에 list가 들어간 걸 `중첩`이라 한다.
  ```

<br>

- 삭제하기

  ```python
  # 빈 값을 선언하는 것이 삭제하는 걸 의미한다.

  # c[1], c[2] 원소가 삭제된다.
  > c = [70, 75, 80 ,85]
  > c[1:3] = []
  > print(c)
  [70, 85]

  > c[0] = []
  [[], 85]
  ```

- 즉, slicing으로 수정 및 삭제를 하면 list 중괄호 안의 성분들이 원본 객체 index에 들어간다.
- 하지만, indexing으로 하면 할당된 괄호 자체도 다 들어간다.

<br>

### 5.2 함수를 사용하여 삭제하는 방법: remove, pop, del

- `remove()`: 삭제할 데이터 값을 직접 지정한다.

  ```python
  > c = [70, 75, 80 ,85]

  > c.remove(70)
  > print(c)
  [75, 80, 85]
  ```

- `pop()`: 마지막 원소를 뽑아내고, 나머지로 만든다.

  ```python
  > c = [70, 75, 80 ,85]

  > print('c - ', c.pop())
  c - 85
  ```

- `del c[index]`: index를 기준으로 데이터를 삭제하기 때문에, 지울려는 데이터가 몇 번째인지 알아야 한다.
  - 데이터가 많아지면, 세기가 어렵다. 그럴 때는 위에 remove를 사용한다.

  ```python
  > c = [70, 75, 80 ,85]

  > del c[1]
  > print(c)
  75
  ```

- `pop( )`

  - `stack` 자료 구조에서는 마지막에 들어온 애가 가장 먼저 나간다.
    - Last In, First Out로 LIFO라 한다.
    - 예1) 음식을 접시에 담을 때, 마지막에 쌓은 접시를 꺼내서 사용한다.
    - 예2) 웹 브라우저를 뒤로 가기 버튼을 누르면, 마지막 페이지가 먼저 나온다.
    - 이런 자료 구조에서 많이 사용되는 method가 `pop` 입니다.

- `Queue` 는 stack과 반대로 가장 처음에 들어온 것을 빼는 구조로, First In, First Out로 FIFO라 한다.

<br>

- 반복문과 `pop()` 을 활용하여 제거하는 방법도 있다.
- 따로 break 를 사용하지 않아도, a가 비워지면 끝난다.

  ```python
  > a = [8, 7, 7, 5, 4, 3]

  > while a:
  >      data = a.pop()
  >      print(data)

  ```

<br>

---

# Reference

- [Python tutorial](https://www.python-course.eu/python3_formatted_output.php)
- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
