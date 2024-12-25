# 0. Introduction

> 1. [Python data type 상세 분류](#1-python-data-type-상세-분류)
> 2. [Mutable 과 Immutable](#2-mutable-과-immutable)
> 3. [list comprehension](#3-list-comprehension)
> 4. [Advanced tuple with unpacking](#4-advanced-tuple-with-unpacking)
> 5. [Advanced dictionary](#5-advanced-dictionary)
> 6. [Advanced set](#6-advanced-set)

<br>

- 파이썬에 존재하는 모든 sequence type 및 data structure에 대해 조금 더 깊이 알아보자.
  - `data structure`: 논리적인 관계로 이루어진 데이터 구성
- 이러한 Data type 들이 있고, 각각의 특징을 이해하여 코드에 녹여내자.
- 각 data type에 대해서 여기에 언급된 내용이 전부가 아니니 더 공부하자.

<br>

---

# 1. Python data type 상세 분류

Python의 data type은 **여러 기준**으로 분류될 수 있다.

<br>

### 1.1 무슨 형태의 자료형을 담을 수 있는가???

- **Container 형**: 서로 다른 자료형을 담을 수 있다.  
   ex) list, tuple, collections.deque..

- **flat 형**: 한 가지 자료형만 담을 수 있다.  
   ex) algorithum.string, bytes, byte algorithum.array, algorithum.array, memoryview...

```yml
# Container의 예: 정수, 실수, 문자열 같이 서로 다른 자료형을 담을 수 있다.
> a = [3, 3.0, 'a']

# flat 형
> chars = '+_)(*'
```

<br>

### 1.2 element가 수정될 수 있는가?? 없는가??

- **Mutable**: 변경할 수 있는 date type  
  ex) list, dictionary, set, bytearray, algorithum.array, memoryview, deque..

- **Immutable**: 변경할 수 없는 data type  
  ex) tuple, str, bytes, int, float...

- 수정될 수 있으면 element를 교체, 삭제, 추가가 가능하다.  
  ex) del, append 등등 가능

<br>

### 1.3 순서가 있는가 없는가???

- **Sequence**: 순서가 존재한다.  
  ex) list, tuple, algorithum.string ..

- **Collections**: 순서가 존재하지 않는다.  
  ex) set, dictionary ..

- 순서가 존재하면 slicing, indexing 이 가능하다.

<br>

---

# 2. Mutable 과 Immutable

> **_mutable: value를 수정할 수 있기 때문에, 'id' 값을 바꾸지 않는다._**  
> **_immutable: value가 변경되지 못하기 때문에, 'id' 값을 바꾼다._**

`id()` 을 사용하여 mutable과 immutable에 대해 자세히 알아보자.

- mutable:

  - 함수 안에서 매개변수의 값을 변경하면 **_객체 자체를 업데이트_** 한다.
  - 따라서, 매개변수의 값을 변경하면 호출하는 쪽의 **_실제 인수는 변경_** 된다.
  - 그 대신 id 값은 변경되지 않는다.

- immutable:

  - 함수 안에서 매개변수의 값을 변경하면 **_다른 객체를 생성_** 하고, 그 객체에 대한 참조로 업데이트된다.
  - 따라서 매개변수의 값을 변경해도 호출하는 쪽의 **_실제 인수에는 영향을 주지 않는다._**
  - 그 대신 id 값은 변경된다.

| Data type             | Mutable              | Immutable        |
| --------------------- | -------------------- | ---------------- |
| 매개변수 값 변경 시도 | 객체 자체를 업데이트 | 다른 객체를 생성 |
| 실제 인수 영향        | 변경 O               | 변경 X           |
| id 값                 | 변경 X               | 변경 O           |

<br>

## 2.1 Immutable

- 문자열과 누적변수 예시를 통해 알아보자.

```yml
> chars = '+_)(*'

# immutable data type인 string을 수정하려 하면 다음과 같은 error 가 뜬다.
> chars[2] = 'h'
TypeError: 'str' object does not support item assignment

> n = 5
> whlie n > 0:
>   print(n, id(n))
>   n -= 1

5 1670466136496
4 1670466136464
3 1670466136432
2 1670466136400
1 1670466136368
```

- int형은 분명 immutable인데 어떻게 수정이 가능할까???

  - 이는 int형 객체 12의 값 자체를 변경한 것이 아니라, 다른 정수형 객체 13을 참조하도록 업데이트됐다.

- `immutable`은 값 자체를 변경할 수 없기 때문에, 다른 객체를 참조하여 `id` 값이 바뀐다.

- 그래서 `누적 변수`를 출력하면 `id`가 달라지는 걸 알 수 있다.

  - `누적 변수`: 변수값에 특정값을 더한 결과값을 다시 대입하여 업데이트한 변수

<br>

## 2.2 Mutable

- 이번에 mutable 예시를 들어보자.

```yml
> k = [1, 2, 3]

> print(id(k))
2249826233536

> k[0] = 0

> print(k, id(k))
[0, 2, 3] 2249826233536
```

- list는 `mutable`로 성분값을 수정할 수 있어서, `id` 값이 수정 전과 동일하다.

<br>

---

# 3. List comprehension

<br>

## 3.1 List comprehension의 의미와 구조

> **_List comprehension이란 list를 만드는 간결한 문법을 말한다._**  
> **_List comprehensions provide a concise way to create lists._**  
> **_from: [list comprehension](https://shoark7.github.io/programming/python/about-list-comprehension-python)_**

- list comprehension에 대해 찾아보니 번역이 다양하고, 딱 들어맞는게 없어서 고유 명사의 의미로 그대로 사용하겠다.

- 그러면 일반적으로 list를 만드는 방법과 비교해보자.

```yml
# 일반적으로 list를 만드는 방법
> code_list1 = []

> chars = '+_)(*&^'

# ord: Return the Unicode code point for a one-character algorithum.string.
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

  - **['변수(B)를 사용하여 list의 성분이 될 값(A)' for '사용할 변수 이름(B)' in 'iterator']**

  - `list comprehension`에서 `if 조건문`은 `for문` 표현식 뒤에 설정할 수 있다.

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
- 코드 몇 줄을 줄이기 위해서 가독성이 많이 떨어진다면 재고할 방법이다.

<br>

## 3.2 list comprehension의 주의사항

> **_깊은 복사와 얕은 복사를 주의하라. 깊은 복사와 얕은 복사에 대한 자세한 내용은 [[TIL] Python basic 41: Shallow copy & Deep copy](https://jeha00.github.io/post/python_basic/python_basic_41_shallowdeepcopy/)을 참고한다._**

```yml
# 반복은 하지만 사용하지 않는 변수라면 '_' 로 표현한다.
> marks1 = [['~'] * 3 for _ in range(4)]

# 하나의 값이 4개로 복사된 것
> marks2 = [['~'] * 3] * 4

# 동일한 출력물을 갖는다.
> print(marks1)
> print(marks2)
[['~', '~', '~'], ['~', '~', '~'], ['~', '~', '~'], ['~', '~', '~']]

# 수정하기
> marks1[0][1] = 'x'
> marks2[0][1] = 'x'

> print(marks1)
[['~', 'x', '~'], ['~', '~', '~'], ['~', '~', '~'], ['~', '~', '~']]


> print(marks2)
[['~', 'x', '~'], ['~', 'x', '~'], ['~', 'x', '~'], ['~', 'x', '~']]
```

- 'marks2'

  - 하나의 값이 4개로 복사되었다는 건 하나의 id값이 복사된 걸 의미한다.
  - 한 객체를 참조하는 게 4개다.
  - 그래서 하나의 객체만 수정해도 이 객체를 참조하는 값들까지 다 수정된다.
  - 이를 `얕은 복사(copy)`라고 한다.

- 'marks1'

  - 'marks2' 와 달리 for문을 통해 **_다 새로 만들어졌다._**
  - 그래서 **_각각 다른 객체를 참조_**한다.
  - 이를 `깊은 복사(deepcopy)`라 한다.

- 이를 `id`값으로 확인해보자.

```yml
# marks1은 다 다른 id 값을 가진다.
> print([id(i) for i in marks1])
[2922096396544, 2922096396480, 2922096396352, 2922096396288]

# marks2는 다 동일한 id 값을 가진다.
> print([id(i) for i in marks2])
[2922096395200, 2922096395200, 2922096395200, 2922096395200]
```

- 그래서 copy 형식을 사용할 때는 조심히 다뤄야 한다.
- `id`값을 확인해보고, 꼼꼼히 개발하자.

<br>

---

# 4. Advanced tuple with unpacking

> **_1. 인자를 입력할 때 upacking을 사용할 수 있다._**  
> **_2. 반환값을 unpacking하여 출력할 수 있다._**  
> **_3. unpacking으로 여러 값을 담을 수 있다._**

- tuple에 `unpacking`을 사용하여 더 깊이 들어가자.
- tuple은 `immutable`이지만, `unpacking`으로 풀을 수 있다.

- 오픈 소스들을 보면 아래 방식으로 코딩한 경우가 많으므로, 아래 3가지 경우를 눈에 익혀두자.

```yml
# divmod: Return the tuple (x//y, x%y).
# divmode: tuple data type으로 (몫, 나머지)를 반환하는 함수

# 정상적인 입력
> print(divmod(100, 9))
(11, 1)

# divmod는 인자를 2개 받아야하는데, 다음과 같이 입력하면 1개만 받은 걸로 인식한다.
> print(divmod((100, 9)))
TypeError: divmod expected 2 arguments, got 1


## 1. 인자를 입력할 때, unpacking을 사용할 수 있다.
# 인자를 1개만 입력하고 싶다면 unpacking을 사용하자.
> print(divmond(*(100, 9)))
(11, 1)

## 2. 반환값을 unpacking하여 출력할 수 있다.
# divmond가 반환하는 걸 unpacking할 수도 있다.
> print(*(divmond(100, 9)))
11 1
```

- range:
  - Return an object that produces a sequence of integers from start (inclusive) to stop (exclusive) by step

```yml
> x, y, rest = range(10)
 ValueError: too many values to unpack (expected 3)

## 3. unpacking하여 여러 값을 담을 수 있다.
# 그러면 unpacking을 이용해보자.
> x, y, *rest = range(10)
> print(x, y, rest)
0 1 [2, 3, 4, 5, 6, 7, 8, 9]

> x, y, *rest = range(2)
> print(x, y, rest)
0 1 []
```

<br>

---

# 5. Advanced dictionary

<br>

## 5.1 hash table이란??

> **_key를 사용하여 적은 리소스로 많은 데이터를 효율적으로 관리하는 데이터 구조 타입으로 그 예가 dictionary다. key : value 로 된 자료형을 의미한다. from reference_**

- dictionary에 대해 간단히 정리하면

  - `key` : `value`로 구성된 data type을 말한다.

  - dictionary의 `key`는 중복을 허용하지 않는다.

    - ex) 각 사람이 가지고 있는 주민등록번호

- python의 dictionary는 **_key_** 를 hash 함수를 통해서 hash 주소로 변환하는 원리이기 때문에, **_key_** 를 통해서 **_value_** 에 접근할 수 있다.

> 참고: 파이썬 언어 자체가 강력한 hash table 엔진으로 만들어졌기 때문에, 파이썬에서는 hash table을 별도로 구현할 필요가 없다.

- 그러면 직접 이에 대해서 알아보자.

```yml
> t1 = (10, 20, (30, 40, 50))
> t2 = (10, 20, [30, 40, 50])

# hash: Return the hash value for the given object.
# 출력되는 hash index
> print(hash(t1))
465510690262297113

> print(hash(t2))
TypeError: unhashable type: 'list'
```

- hash 값을 확인할 수 있다는 건 `고유하다`는 의미로, `수정 불가능`하다는 걸 말한다.
- 그래서 list type의 hash number를 확인할려고 했으나, TypeError가 뜬 것이다.
- list는 `mutable`이기 때문이다.

<br>

## 5.2 key가 중복되는 dictionary 만들기

> **_' setdefault '를 사용하여 만든다. 이 방법은 tuple로 dictionary를 만들 때, 권고되는 방법이다._**

- 이런 방식으로 자주 구현하므로 눈에 익혀두자.

```yml
# 이중 tuple
> source = (('k1', 'val1'),
>             ('k1', 'val2'),
>             ('k2', 'val3'),
>             ('k2', 'val4'),
>             ('k2', 'val5'))

> new_dict1 = {}
> new_dict2 = {}


## No use setdefault

 # k에는 k1, k2가, v에는 val가 할당된다.
> for k, v in source:

    # new_dict1 에 k1 이나 k2가 있다면, 이에 대한 value 값으로 v를 끝에 추가한다.
>   if k in new_dict1:
>       print(k, v)
>       new_dict1[k].append(v)

    # new_dict1 에 k1 이나 k2가 없다면 k를 추가하고, 이 k에 대한 value로 v를 추가한다.
>   else:
>       new_dict1[k] = [v]
>       print(new_dict1)
k1 val1
{'k1': ['val1']}
k1 val2
{'k1': ['val1', 'val2']}
k2 val3
{'k1': ['val1', 'val2'], 'k2': ['val3']}
k2 val4
{'k1': ['val1', 'val2'], 'k2': ['val3', 'val4']}
k2 val5
{'k1': ['val1', 'val2'], 'k2': ['val3', 'val4', 'val5']}

> print(new_dict1)
{'k1': ['val1', 'val2'], 'k2': ['val3', 'val4', 'val5']}

## use setdefault
> for k, v in source:

# k는 default로 들어가고, 나머지는 list type에 담는다는 의미다.
>      new_dict2.setdefault(k, []).append(v)

> print(new_dict2)
{'k1': ['val1', 'val2'], 'k2': ['val3', 'val4', 'val5']}
```

- `setdefault`를 사용하여 훨씬 짧은 코드로 key가 중복된 tuple을 dictionary로 구현했다.
- `setdefault`를 사용하여 `[]` 가 아닌 `()`로 했다면 tuple이므로, 만들 수 없다.

<br>

- 만약, dictionary를 만들 때 키가 중복되면 나중 값으로 overwritten된다.

```yml
> new_dict3 = {k : v for k , v in source}
> print(new_dict3)
{'k1': 'val2', 'k2': 'val5'}
```

<br>

## 5.3 Immutable Dictionary 생성하기

> **_immutable dictionary 즉, '읽기 전용' dictionary를 만들어보자._**

- **왜 읽기 전용을 만들까???**

  - '읽기 전용'을 만들지 않고, 파일을 그냥 두어도 된다.
  - 하지만, communication의 문제로 팀원이 이 데이터를 수정할수도 있다.
  - 그래서 수정하면 안되는 file은 '읽기 전용'으로 만든다.

- **'읽기 전용'으로 만들기 위해서**
  - `MappingProxyType` 를 사용할 것이다.
  - data 이름에는 `_frozen`을 작성한다. (외국에서는 이렇게 한다.)

```yml

> from types import MappingProxyType

> d = {'key1': 'value1'}

> d_frozen = MappingProxyType(d)

> print(d, id(d))
{'key1': 'value1'} 2586113038272

> print(d_frozen, id(d_frozen))
{'key1': 'value1'} 2586113478608

> print(type(d), type(d_frozen))
<class 'dict'> <class 'mappingproxy'>

> print(d is d_frozen, d == d_frozen)
False True
```

- `d`와 `d_frozen`이 `==`을 통해서 value가 같다는 건 확인했다.
- 하지만, 서로 다른 객체라는 걸 `id`를 통해 확인했다.
- 다른 객체를 참조했기 때문에 `is`로 확인했을 때, false가 뜬 것이다.

---

# 6. Advanced set

<br>

## 6.1 Immutable set

> **_' frozenset '을 사용하여 'mutable' 인 set을 ' immutable' 로 바꾸기_**

- set data type을 선언하는 방법은 다음과 같다.

```yml
# {}만 사용
> s1 = {'Apple', 'Orange', 'Apple', 'Orange', 'Kiwi'}
> s3 = {3}

# set([]) 사용
> s2 = set(['Apple', 'Orange', 'Apple', 'Orange', 'Kiwi'])
> s4 = set() # Not {}
```

- 그러면 `frozenset` 을 사용하여 선언해보자.

```yml
> s5 = frozenset({'Apple', 'Orange', 'Apple', 'Orange', 'Kiwi'})
```

- 그러면 s1과 s5에 각각 원소를 추가한 후, 출력한다.

```yml
> s1.add('Melon')

# s5인 경우, immutable로 바꼈기 때문에, 수정할 수 없다는 걸 확인했다.
> s5.add('Melon')
AttributeError: 'frozenset' object has no attribute 'add'

## s1 ~ s5까지 출력해보자.

> print(s1, type(s1))
{'Kiwi', 'Orange', 'Melon', 'Apple'} <class 'set'>

> print(s2, type(s2))
{'Kiwi', 'Apple', 'Orange'} <class 'set'>

> print(s3, type(s3))
{3} <class 'set'>

> print(s4, type(s4))
set() <class 'set'>

> print(s5, type(s5))
frozenset({'Kiwi', 'Orange', 'Apple'}) <class 'frozenset'>
```

- `frozenset`을 통해서 immutable set인 frozenset으로 type이 바뀐 걸 알 수 있다.

<br>

## 6.2 선언 최적화

> **_from dis import dis 사용하여, 더 빠른 선언법을 확인하기_**

- 요즘은 하드웨어의 성능이 매우 좋기 때문에, 소량의 데이터에서는 큰 영향이 없다.
- 하지만, 데이터량이 늘어남에 따라 작은 최적화가 쌓여 큰 성능 개선을 이룰 수 있으므로, 확인해보자.

- 위의 여러 set 선언 방법들 중 어느 것이 제일 빠를까???

```yml
> from dis import dis

> print(dis('{10}'))
  1           0 LOAD_CONST               0 (10)
              2 BUILD_SET                1
              4 RETURN_VALUE
None


> print(dis('set([10])'))
 1           0 LOAD_NAME                0 (set)
              2 LOAD_CONST               0 (10)
              4 BUILD_LIST               1
              6 CALL_FUNCTION            1
              8 RETURN_VALUE
None
```

- set([10]) 은 5단계, {10}은 3단계로 s1처럼 선언하는 방식이 더 빠르다는 걸 알 수 있다.

<br>

## 6.3 Set comprehension

- [list comprehension](#3-list-comprehension)에서 알아봤기 때문에, comprehension의 의미는 생략한다.

- 바로 실습해보자.

```yml
## 예제 1
> l = {(m, n) for n in range(2) for m in range(3, 5)}
> l = set([(3, 0), (3, 1), (4, 0), (4, 1)])

> print(l)
{(3, 1), (4, 0), (4, 1), (3, 0)}

## 예제 2
> sentence = "The cat in the hat had two sidekicks, thing one and thing two."

> words = sentence.lower().replace('.', '').replace(',', '').split()

> unique_word = {word for word in words}

> print(unique_word)
{'in', 'and', 'the', 'had', 'cat', 'two', 'thing', 'one', 'sidekicks', 'hat'}


## 예제 3
# if 조건문을 함께 사용해보자.
> unique_words = {word for word in words if len(word) <= 3}

> print(unique_word)
{'and', 'cat', 'had', 'hat', 'in', 'one', 'the', 'two'}
```

- set 또한 list처럼 comprehension의 방법으로 선언할 수 있다는 걸 확인했다.
- 간결한 선언법이 장점이지만, 과하면 가독성이 좋지 않다는 걸 기억하자.

<br>

---

# Reference

- [Data Model](https://docs.python.org/3/reference/datamodel.html#special-method-names)
- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [python 공식문서: list comprehension](https://docs.python.org/3/tutorial/datastructures.html?highlight=list%20comprehension#list-comprehensions)
- [[Python] list comprehension에 대한 즐거운 이해](https://shoark7.github.io/programming/python/about-list-comprehension-python)
- [자료구조와 함께 배우는 알고리즘 입문 - 파이썬편](https://book.naver.com/bookdb/book_detail.nhn?bid=16419115)
