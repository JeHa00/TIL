# 0. Introduction

> 1. [Sort vs Sorted](#1-sort-vs-sorted)
> 2. [Array data model](#2-array-data-model)

<br>

---

# 1. Sort vs Sorted

> **_- sort: sort the list in ascending order and return None._**  
> **_- sorted: Return a new list containing all items from the iterable in ascending order._**

| function  | sort() | sorted()        |
| --------- | ------ | --------------- |
| 원본 수정 | O      | X               |
| 반환값    | None   | New list object |
| function or method | method | function |

- list data model에서 자주 사용하는 함수인 `sort` 와 `sorted`에 대해 알아보자.

```yml
> f_list = ['orange', 'apple', 'mango', 'papaya', 'lemon', 'strawberry', 'coconut']

## sort
# 1. 반환값이 없다.
> print('sort - ', f_list.sort())
sort - None

# 2. 원본 객체를 수정한다.
> print(f_list)
['apple', 'coconut', 'lemon', 'mango', 'orange', 'papaya', 'strawberry']

## sorted
# 1. 반환값이 존재한다.
> print('sort - ', sorted(f_list))
sorted - ['apple', 'coconut', 'lemon', 'mango', 'orange', 'papaya', 'strawberry']

# 2. 원본 객체를 수정하지 않는다.
> print(f_list)
sorted - ['orange', 'apple', 'mango', 'papaya', 'lemon', 'strawberry', 'coconut']
```

- 다음으로 여러 key 값을 사용하여 `sort`와 `sorted`를 활용해보자.

```yml
# reverse = True
# 순서를 뒤집는다.
> print('sorted - ', sorted(f_list, reverse = True))
sorted - ['strawberry', 'papaya', 'orange', 'mango', 'lemon', 'coconut', 'apple']

> print('sort - ', f_list.sort(reverse = True), f_list)
sort - None ['strawberry', 'papaya', 'orange', 'mango', 'lemon', 'coconut', 'apple']


# key = len
# 단어 길이를 기준으로 정렬한다.
> print('sorted - ', sorted(f_list, key = len))
sorted - ['apple', 'mango', 'lemon', 'orange', 'papaya', 'coconut', 'strawberry']

> print('sort - ', f_list.sort(key = len), f_list)
sort - None ['apple', 'mango', 'lemon', 'orange', 'papaya', 'coconut', 'strawberry']

# key = lambda x: x[-1]
# 마지막 알파벳을 기준으로 정렬한다.
> print('sorted - ', sorted(f_list, key=lambda x: x[-1]))
sorted - ['papaya', 'orange', 'apple', 'lemon', 'mango', 'coconut', 'strawberry']

> print('sort - ', sort(f_list, key=lambda x: x[-1]), f_list)
sort - None ['papaya', 'orange', 'apple', 'lemon', 'mango', 'coconut', 'strawberry']

# key = lambda x: x[-1], reverse = True
# 마지막 알파벳을 기준으로 정렬한 후, 뒤집는다.
> print('sorted - ', sorted(f_list, key=lambda x: x[-1], reverse = True))
sorted - ['strawberry', 'coconut', 'mango', 'lemon', 'orange', 'apple', 'papaya']

> print('sort - ', sort(f_list, key=lambda x: x[-1], reverse = True), f_list)
sorted - None ['strawberry', 'coconut', 'mango', 'lemon', 'orange', 'apple', 'papaya']
```

<br>

---

# 2. Array data model

- [1.Python data model 상세 분류](https://jeha00.github.io/post/python_basic/python_basic_29_datamodel/#1-python-data-type-%EC%83%81%EC%84%B8-%EB%B6%84%EB%A5%98)에서 꺼냈던 `Array` data model에 대해 알아보자.

- `Array` 자료형에 대해 알아보자.

- Array의 구조는 다음과 같다.
  - Array(`type code`, [algorithum.array 원소값])
  - 여기서 `type code`는 형 코드를 말하는데, 다음 대표 reference를 참조하자.
  - [Array in docs.python](https://docs.python.org/ko/3/library/array.html#module-array)

```yml
# algorithum.array module을 가져오는 것부터 시작한다.
> import algorithum.array

> chars = '+_)(*&^%$#@!~)'

# algorithum.array module의 algorithum.array method를 사용한다.
# ord: Return the Unicode code point for a one-character algorithum.string
> array_g = algorithum.array.algorithum.array('I', (ord(s) for s in chars))

> print(type(array_g), array_g)
<class 'algorithum.array.algorithum.array'> algorithum.array('I', [43, 95, 41, 40, 42, 38, 94, 37, 36, 35, 64, 33, 126, 41])

# .tolist(): algorithum.array data type을 list로 바꿔주는 함수
> array_l = array_g.tolist()

> print(type(array_l))
<class 'list'>

> print(array_l)
[43, 95, 41, 40, 42, 38, 94, 37, 36, 35, 64, 33, 126, 41]
```

<br>

## List vs Array 적합한 사용법

- List 기반: 다양한 data type을 사용할 수 있기 때문에, 융통성 있게 범용적으로 사용 가능.
- Array 기반: 한 가지 data type만 사용할 수 있기 때문에, 숫자 기반에 많이 사용한다.

---

# Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
