# 0. Introduction

<br>

- 파이썬에서 모든 객체는 데이터에 대한 추상화로 표현될 수 있다.
- 파이썬에서는 `namedtuple`외에도 많은 Container datatypes가 있다.
- 이 종류들은 Collections library에서 찾을 수 있다.
- 객체를 사용하기보다 튜플 활용을 공식 레퍼런스에서 추천하고 있다.

<br>

---

# 1. Namedtuple이란??

> **_namedtuple(typename: str, field_names: str)_**

- tuple의 기본 성질인 `immutable`을 가지고 있으며, 다양한 선언법을 지원한다.
- Dictionary Key와 같이 사용되기 때문에, key 값을 통해서 access할 수 있다.
- 일반 class 형태보다 적은 메모리를 사용한다.

<br>

---

# 2. 실습 예제

- 3차원 좌표 사이의 거리를 구하는 예제를 통해서 namedtuple에 대해 알아보자.

<br>

### 2.1 namedtuple 없이 구하기

- namedtuple 없이 거리 구하기

```yml
# 두 점을 정의한다.
> pt1 = (2.0, 4.0, 5,0)
> pt2 = (4.0, 10.0, 25.0)

# sqrt를 import 한다. root를 의미한다.
> from math import sqrt

# index를 통해서 직접 value에 접근해야 한다.
> leng1 = sqrt((pt2[0] - pt1[0]) ** 2 + (pt2[1] - pt1[1]) ** 2 + (pt2[2] - pt1[2]) ** 2 )
```

<br>

### 2.2 namedtuple로 구해보기

- namedtuple을 사용하여 거리를 구해보자.

```yml
> from collections import namedtuple

# namedtuple 선언
> Point = namedtuple('Point', 'x y z')

# 두 점을 정의한다.
> pt3 = Point(2.0, 4.0, 5.0)
> pt4 = Point(4.0, 10.0, 25.0)

# index로도 접근이 가능하지만
# value에 직접 접근하지 않고, key 값을 통해서 접근한다.
> leng2 = sqrt((pt4.x - pt3.x) ** 2 + (pt4.y - pt3.y) ** 2 + (pt4.z - pt3.z) ** 2)

> print(leng1 == leng2)
True
```

- 이처럼 namedtuple을 사용하면 dictionary처럼 key 값이 생기기 때문에, index로 직접 접근하지 않아 오류를 낼 가능성이 낮다.

<br>

### 2.3 namedtuple의 다양한 선언법

> **_namedtuple은 2개의 위치인자를 취한다._**

- 다음으로 namedtuple의 다양한 선언법에 대해 알아보자.

```yml
# list 안에 algorithum.string 성분으로 입력하는 방법
> Point = namedtuple('Point', ['x', 'y', 'z'])

# algorithum.string 묶음으로 입력하는 방법
> Point = namedtuple('Point', 'x y z')
> Point = namedtuple('Point', 'x, y, z')

## field_name으로 예약어를 사용하고 싶을 때
# 일반적으로 예약어를 name에 사용하면 안된다. 하지만, 사용하고 싶다면??
> Point = namedtuple('Point', 'x y z class', rename = True)
```

- rename을 입력하지 않으면 `rename = False` 가 default다.

<br>

### 2.4 namedtuple의 다양한 객체 생성법

- 객체 생성 방법도 다양하다.
- 이에 대해서도 알아보자.

```yml
# field name을 직접 입력하여 할당하기
> p1 = Point(x=10, y=35)

# 값만 입력
> p2 = Point(20, 40)

# field name 부분 입력
> p3 = Point(45, y=20)

# 여러 개로도 가능하다.
> p4 = Point(10, 20, 30, 40)

## unpacking을 통해 만들기
> temp_dict = {'x':75, 'y':55}
> p5 = Point(**temp_dict)

> temp_dict = (2.0, 4.0, 5.0)
> p5 = Point(*temp_dict)

## 출력해보기
> print(p1, p2, p3, p4, p5)
Point(x=10, y=35) Point(x=20, y=40) Point(x=45, y=20) Point(x=10, y=20, _2=30, _3=40) Point(x=75, y=55)
```

<br>

### 2.5 namedtuple 메소드

- namedtuple에 사용되는 메소드를 몇 가지 알아보자.

  - `_make()`: list를 namedtuple로 만드는 함수
  - `_fields()`: field_name 조회 함수
  - `_asdict()`: namedtuple을 dictionary로 전환하는 함수

```yml
> temp = [52, 38]
> p4 = Point._make(temp)
Point(x=52, y=38)

> print(p1._fields, p2._fields, p3._fields)
('x', 'y') ('x', 'y') ('x', 'y')

> print(p1._asdict(), p4._asdict())
{'x': 10, 'y': 35} {'x': 52, 'y': 38}
```

<br>

---

# Reference

- [Data Model](https://docs.python.org/3/reference/datamodel.html#special-method-names)
- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [Python- namedtuple 사용 예제 및 소스 코드](https://niceman.tistory.com/197?category=940952)
