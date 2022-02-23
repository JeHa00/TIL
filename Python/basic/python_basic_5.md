# Python basic 4: python list

# Intro

> 1. List 선언
> 2. List 인덱싱, 슬라이싱
> 3. List 연산
> 4. List 함수
> 5. List 수정, 삭제

<br>

- List 자료형은 **sequence**형이고, **mutable**이기 때문에

  - 순서 존재한다. => len, index, slicing 이 가능
  - 중복이 가능하다.
  - 수정, 삭제가 가능하다.

- 다른 언어에서는 배열이라 하는데 알고리즘을 풀기 위해서 굉장히 중요한 자료 형태다.

<br>

---

## 1. List 선언

```yml
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

> list도 string처럼 sequence 형이기 때문에, len, index, slicing을 사용할 수 있다.

- indexing: 원하는 데이터를 꺼내는 과정

```yml
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

- slicing: 같은 데이터 타입으로로, 원하는 부분의 데이터를 뽑아내는 것

```yml
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

```yml
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

```

---

<br>

## 4. List 함수

---

<br>

## 5. List 수정, 삭제

<br>

### 5.1 slicing과 index를 사용하여 수정, 삭제하는 방법

수정하기

```yml

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

삭제하기

```yml

# 빈 값을 선언하는 것이 삭제하는 걸 의미한다.

> c = [70, 75, 80 ,85]
> c[1:3] = []
> print(c)
[70, 85]
# c[1], c[2] 원소가 삭제된다.


```

<br>

### 함수를 사용하여 수정, 삭제하는 방법: remove, pop, del

```yml

```
