# Python basic 25 : \_\_str\_\_ vs \_\_repr\_\_

# 0. Introduction

> 1. [Magic method 의 종류: `__str__` 과 `__repr__`](#1magic-method-의-종류-__str__-과-__repr__)
> 2. [`__str__` 과 `__repr__`의 차이](#2-__str__-과-__repr__의-차이)
> 3. [print function과 `__str__` method의 관계](#3-print-function과-__str__-method의-관계)
> 4. [정리](#4-정리)

- class에 대해 공부를 더 시작하면서 매직 메소드에 대해 알기 시작했다.
- 매직 메소드의 종류인 `__str__` 과 `__repr__` 에 대해 각각 알아보고, 차이점도 알아보자.

<br>

---

# 1. Magic method 의 종류: str 과 repr

> **_python에서 이미 만들어놓은 내장된 method로, special method라 한다._**

- magic method의 종류 중 `__str__` 과 `__repr__`에 대해 알아보겠다.

  - `__repr__` 의 repr은 `representation`의 약어다.

- 예시코드는 Python basic 24에서 작성한 코드를 이어서 사용한다.

```yml
> class Airline():
>     def __init__(self, company, details):
>         self._company = company
>         self._details = details
>
>

> Airline1 = Airline('Koreanair', {'uniform_color': 'skyblue', 'kind':'FSC', 'price': 8000})
> Airline2 = Airline('Asiana', {'uniform_color': 'gray', 'kind':'FSC', 'price': 6000})
> Airline3 = Airline('t-wau', {'uniform_color': 'red', 'kind':'LCC', 'price': 3000})

> print(Airline1)
> print(Airline2)
> print(Airline3)
<__main__.Airline object at 0x000002DFDFF06FD0>
<__main__.Airline object at 0x000002DFDFF06F70>
<__main__.Airline object at 0x000002DFDFF06400>
```

- Airline 클래스의 인스턴스를 보이기 위해서, `print()`을 사용하면 출력할 instance의 memory address를 보인다.

<br>

## 1.1 \_\_str\_\_ magic method

- 그러면 Airline class 내부에 `__str__` instance method를 추가해보자.

```yml
> class Airline():
>     def __init__(self, company, details):
>         self._company = company
>         self._details = details
>
>     def __str__(self):
>         return 'str : {} - {}'.format(self._company, self._details)

# instance 코드 부분은 생략한다.
> print(Airline1)
str : Koreanair - {'uniform_color': 'skyblue', 'kind': 'FSC', 'price': 8000}

> print(Airline2)
str : Asiana - {'uniform_color': 'gray', 'kind': 'FSC', 'price': 6000}

> print(Airline3)
str : t-wau - {'uniform_color': 'red', 'kind': 'LCC', 'price': 3000}

```

- `__str__` method 를 사용하니, memory address를 출력하는 것이 아닌, `__str__` method의 return 값을 출력한다.

<br>

## 1.2 \_\_repr\_\_ magic method

- 다음으로 `__repr__` method를 사용해보자.

```yml
> class Airline():
>     def __init__(self, company, details):
>         self._company = company
>         self._details = details
>
>     def __repr__(self):
>         return 'repr : {} - {}'.format(self._company, self._details)

# instance 코드 부분은 생략한다.

> print(Airline1)
> print(Airline2)
> print(Airline3)
repr : Koreanair - {'uniform_color': 'skyblue', 'kind': 'FSC', 'price': 8000}
repr : Asiana - {'uniform_color': 'gray', 'kind': 'FSC', 'price': 6000}
repr : t-wau - {'uniform_color': 'red', 'kind': 'LCC', 'price': 3000}
```

- `__repr__` method 를 사용하니, memory address를 출력하는 것이 아닌, `__repr__` method에서 return 값을 출력한다.
- 그러면 `__str__` 와 `__repr__` 를 같이 사용해보자.

```yml
> class Airline():
>     def __init__(self, company, details):
>         self._company = company
>         self._details = details
>
>     def __str__(self):
>         return 'str : {} - {}'.format(self._company, self._details)
>
>     def __repr__(self):
>         return 'repr : {} - {}'.format(self._company, self._details)

# instance 코드 부분은 생략한다.

> print(Airline1)
> print(Airline2)
> print(Airline3)
str : Koreanair - {'uniform_color': 'skyblue', 'kind': 'FSC', 'price': 8000}
str : Asiana - {'uniform_color': 'gray', 'kind': 'FSC', 'price': 6000}
str : t-wau - {'uniform_color': 'red', 'kind': 'LCC', 'price': 3000}
```

- `__str__` method의 return 문을 출력했다.

<br>

## 1.3. \_\_str\_\_ 과 \_\_repr\_\_ 의 공통점과 차이

- 그러면 여태까지의 증명으로 다음과 같은 사실을 알 수 있다.

- \_\_str\_\_ 과 \_\_repr\_\_ 의 공통점

  - `__str__` 와 `__repr__` 은 클래스의 인스턴스에 대한 memory address를 출력하는 게 아닌, 사용자가 원하는 출력문 즉, 이 magic method에서 반환한 형식으로 출력한다.
    - To customize the algorithum.string representation of a class instance, the class needs to implement the `__str__` magic method. [출처: [python tutorial: \_\_str\_\_](https://www.pythontutorial.net/python-oop/python-__str__/)]
  - `__str__` 와 `__repr__` 이 같이 사용되면 `__str__`이 출력된다.

- \_\_str\_\_ 과 \_\_repr\_\_ 의 차이점 from [Python \_\_repr\_\_](https://www.pythontutorial.net/python-oop/python-__repr__/)

| Magic method       | \_\_str\_\_                        | \_\_repr\_\_                         |
| ------------------ | ---------------------------------- | ------------------------------------ |
| 대상               | 사람이 읽기 쉬운 결과물            | 기계(interpreter)가 읽기 쉬운 결과물 |
| 목적               | 간결히 읽기 위함                   | 문자열로 객체를 다시 생성하기 위함   |
| Informal / Offical | **_Informal_** algorithum.string presentation | **_Offical_** algorithum.string presentation    |

```yml
> a = datetime.datetime(2022,3,13)

> print(str(a))
2022-03-13 00:00:00

> print(repr(a))
datetime.datetime(2022, 3, 13, 0, 0)
```

<br>

---

# 2. print ()와 \_\_str\_\_ method의 관계

> **_print( )는 object.\_\_str\_\_를 통해서 문자열로 반환된 걸 출력하는 function_**

- `str(object)`를 통해 문자열로 변환할 때, `object.__str__`를 호출하여 이 메소드를 통해 변환된 것을 반환한다.

- `print()`는 `str()`에게 출력할 arguments를 전달하여 문자열로 변환 후 출력한다.

```yml
> class Airline():
>     def __init__(self, company, details):
>         self._company = company
>         self._details = details
>
>     def __str__(self):
>         return 'str : {} - {}'.format(self._company, self._details)

> print(str(Airline1))
str : Koreanair - {'uniform_color': 'skyblue', 'kind': 'FSC', 'price': 8000}
```

<br>

- `repr()` 또한 `__repr__`을 통해서 변환된 것을 반환한다.

```yml
> class Airline():
>     def __init__(self, company, details):
>         self._company = company
>         self._details = details
>
>     def __repr__(self):
>         return 'repr : {} - {}'.format(self._company, self._details)

# instance 코드 부분은 생략한다.

> print(repr(Airline1))
repr : t-wau - {'uniform_color': 'red', 'kind': 'LCC', 'price': 3000}

> print(str(Airline1))
repr : t-wau - {'uniform_color': 'red', 'kind': 'LCC', 'price': 3000}
```

- 또한, `__str__`이 별도로 정의되지 않았고 `__repr__`만 정의된 상황일지라도, `str()`을 실행하면 `__repr__`가 실행된다.

<br>

## Summary

- `print()` --(호출)--> `str(object)` --(호출)--> `object.__str__` --(호출)--> `object.__repr__`

  - `print()`는 `object.__str__`를 통해 변환된 걸 출력한다.
  - `__str__`은 내부적으로 default로 `__repr__`을 호출한다.

- `repr(object)` --(호출)--> `object.__repr__`

  - `object.__repr__` 를 통해 변환된 걸 반환한다.

<br>

---

# Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [Python tutorial](https://www.pythontutorial.net/python-oop/python-__str__/)
- [Data model](https://docs.python.org/3/reference/datamodel.html#object.__str__)
