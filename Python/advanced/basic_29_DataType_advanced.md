# Intro

> 1. [Python data type 상세 분류](#1-python-data-type-상세-분류)
> 2. [Mutable 과 Immutable](#2-mutable-과-immutable)
> 3. [list comprehension](#3-list-comprehension)
> 4. [지능형 tuple](#4-지능형-tuple)
> 5. [지능형 dictionary](#5-지능형-dictionary)
> 6. [지능형 set](#6-지능형-set)

<br>

- 파이썬에 존재하는 모든 sequence type 및 data structure에 대해 조금 더 깊이 알아보자.
  - `data structure`: 논리적인 관계로 이루어진 데이터 구성
- 이러한 Data type 들이 있고, 각 각의 특징을 이해하여 코드에 녹여내자.
- 각 data type에 대해서 여기에 언급된 내용이 전부가 아니니 더 공부하자.

<br>

---

# 1. Python data type 상세 분류

- Python의 data type은 **여러 기준**으로 분류될 수 있다.

<br>

- **1. _무슨 형태의 자료형_ 을 담을 수 있는가???**

  - **Container 형**: 서로 다른 자료형을 담을 수 있다.  
     ex) list, tuple, collections.deque..

  - **flat 형**: 한 가지 자료형만 담을 수 있다.  
     ex) string, bytes, byte array, array, memoryview...

```yml

# Container의 예: 정수, 실수, 문자열 같이 서로 다른 자료형을 담을 수 있다.
> a = [3, 3.0, 'a']

# flat 형
> chars = '+_)(*'
```

- **2. element가 _수정_ 될 수 있는가?? 없는가??**

  - **Mutable**: 변경할 수 있는 date type
    ex) list, dictionary, set, bytearray, array, memoryview, deque..

  - **Immutable**: 변경할 수 없는 data type
    ex) tuple, str, bytes, int, float...

  - 수정될 수 있으면 element를 교체, 삭제, 추가가 가능하다.
    ex) del, append 등등 가능

- **3. _순서_ 가 있는가 없는가???**

  - **Sequence**: 순서가 존재한다.
    ex) list, tuple, string ..

  - **Collections**: 순서가 존재하지 않는다.
    ex) set, dictionary ..

  - 순서가 존재하면 slicing, indexing 이 가능하다.

<br>

---

# 2. Mutable 과 Immutable

- `id()` 을 사용하여 mutable과 immutable에 대해 자세히 알아보자.

- mutable: 함수 안에서 매개변수의 값을 변경하면 객체 자체를 업데이트한다. 따라서, 매개변수의 값을 변경하면 호출하는 쪽의 실제 인수는 변경된다.

- immutable: 함수 안에서 매개변수의 값을 변경하면 다른 객체를 생성하고, 그 객체에 대한 참조로 업데이트된다. 따라서 매개변수의 값을 변경해도 호출하는 쪽의 실제 인수에는 영향을 주지 않는다.

- 예시를 들어보자.

```yml
> chars = '+_)(*'

# immutable data type인 string을 수정하려 하면 다음과 같은 error 가 뜬다.
> chars[2] = 'h'
TypeError: 'str' object does not support item assignment

> n = 12
> print(id(n))
2041592179344

> n = 13
> print(id(n))
2041592179376

> n += 1
> print(n, id(n))
14 2041592179408

```

- int형은 분명 immutable인데 어떻게 수정이 가능할까???

  - 이는 int형 객체 12의 값 자체를 변경한 것이 아니라, 다른 정수형 객체 13을 참조하도록 업데이트된 것이다.

- `immutable`은 값 자체를 변경할 수 없기 때문에, 다른 객체를 참조한다. 그래서 `id`가 바뀐다.

- 그래서 `누적 변수`를 출력하면 `id`가 달라지는 걸 알 수 있다.

  - `누적 변수`: 변수값에 특정값을 더한 결과값을 다시 대입하여 업데이트한 변수

<br>

- 이번에 mutable 예시를 들어보자.

```yml
> k = [1, 2, 3]

> print(id(k))
2249826233536

> k[0] = 0

> print(k, id(k))
[0, 2, 3] 2249826233536
```

- list는 `mutable`이라 성분값을 수정할 수 있다. 수정 후, `id`을 확인하면 수정 전과 동일한 걸 알 수 있다.

<br>

> - **정리**
>   - **_`immutable`_**은 value가 변경되지 못하기 때문에, **`id`** 값을 바꾼다.
>   - **_`mutable`_**은 value를 수정할 수 있기 때문에, **`id`** 값을 바꾸지 않는다.

<br>

---

# 3. List comprehension

- list comprehension에 대해 찾아보니 번역이 다양하고, 딱 들어맞는게 없었다.
- 그래서 그대로 사용하는게 좋다고 판단했다.

- 그러면 `list comprehension`은 뭘까??

  - list를 만드는 간결한 문법을 말한다.
  - List comprehensions provide a concise way to create lists.
    - from [list comprehension](https://shoark7.github.io/programming/python/about-list-comprehension-python)

- 그러면 일반적으로 list를 만드는 방법과 비교해보자.

```yml
# 일반적으로 list를 만드는 방법
> code_list1 = []

> chars = '+_)(*&^'

# ord: Return the Unicode code point for a one-character string.
# ord: 유니 코드로 전환하는 함수
> for s in chars:
>   code_list1.append(ord(s))
[43, 95, 41, 40, 42, 38, 94]


## list comprehension을 이용한 방법
> code_list2 = [ord(s) for s in chars]
[43, 95, 41, 40, 42, 38, 94]

# for문과 if문을 사용한 list comprehension
> code_list3 = [ord(s) for s in chars if ord(s) > 40]
[43, 95, 41, 40, 42, 38, 94]

> vec = [-4, -2, 0, 2, 4]
> [x for x in vec if x >= 0]
[0, 2, 4]
```

- 그러면 위 예시를 통해 `list comprehension`의 문법에 대해 정리해보자.

  - 1. [`변수(B)를 사용하여 list의 성분이 될 값(A)` for `사용할 변수 이름(B)` in `iterator`]

  - 2. `list comprehension`에서 `if 조건문`은 `for문` 표현식 뒤에 설정할 수 있다.

- 지난 번에 알아본 `namedtuple`을 사용해서 예시를 만들어보자.

```yml
# list comprehension을 통해 3개의 반을 만들고, 각 반마다 6명의 학생이 있는 list를 만들자.
# 예제 2-1
> numbers = [str(n) for n in range(1,6)]
> ranks = 'A B C'.split( )


> print(numbers)
> print(ranks)
['1', '2', '3', '4', '5']
['A', 'B', 'C']

# namedtuple 만들기
> Classes = namedtuple('Classes', ['rank', 'number'])

# 2개의 for 반복문을 사용하여 list의 성분값으로 만들어질 인수들에 각각 mapping 했다.
> students = [Classes(rank, number) for rank in ranks for number in numbers]

> print(students)
[Classes(rank='A', number='1'), Classes(rank='A', number='2'), Classes(rank='A', number='3'), Classes(rank='A', number='4'), Classes(rank='A', number='5'),

Classes(rank='B', number='1'), Classes(rank='B', number='2'), Classes(rank='B', number='3'), Classes(rank='B', number='4'), Classes(rank='B', number='5'),

Classes(rank='C', number='1'), Classes(rank='C', number='2'), Classes(rank='C', number='3'), Classes(rank='C', number='4'), Classes(rank='C', number='5'),

Classes(rank='D', number='1'), Classes(rank='D', number='2'), Classes(rank='D', number='3'), Classes(rank='D', number='4'), Classes(rank='D', number='5')]

# 예제 2-2
> student = [Classes(rank, number)
>                   for rank in 'A B C'.split( )
>                     for number in [str(n) for n in range(1,6)]]
```

- 예제 2-2의 경우, 여러 for문을 사용했다.
- 한 줄로 표현할 수 있지만, 가독성이 떨어진다.
- 코드 몇 줄을 줄이기 위해서 가독성이 많이 떨어진다면 오히려 삼가해야할 방법이라 생각한다.

<br>

---

# 4. 지능형 tuple

<br>

---

# 5. 지능형 dictionary

<br>

---

# 6. 지능형 set

<br>

---

# Reference

- [Data Model](https://docs.python.org/3/reference/datamodel.html#special-method-names)
- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [python 공식문서: list comprehension](https://docs.python.org/3/tutorial/datastructures.html?highlight=list%20comprehension#list-comprehensions)
- [[Python] list comprehension에 대한 즐거운 이해](https://shoark7.github.io/programming/python/about-list-comprehension-python)
