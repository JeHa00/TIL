# Python basic 24 : 절차지향 프로그래밍 VS 객체지향 프로그래밍 비교

<br>

## Intro

> 1. [절차지향 프로그래밍 (Procedural Programming)](#1-절차지향-프로그래밍-procedural-programming)
> 2. [객체지향 프로그래밍 (Object Oriented Programming)](#2-객체지향-프로그래밍-object-oriented-programming)

<br>

- basic 14 class 에서 언급한 `절차지향 프로그래밍`이 구체적으로 무엇인지 간단히 구현한다.
- 그 다음 클래스를 사용한 `객체지향 프로그래밍`에 대해 알아보겠다.

<br>

- `절차지향 프로그래밍`은 일반적인 과거의 코딩방식으로, 함수 중심이기 때문에, 데이터가 방대하여 복잡하다는 단점이 있다.

<br>

- 이에 대한 해결책이 `OOP(Object Oriented Programming)` 으로 `객체지향 프로그래밍`이다.
- 장점을 다시 한번 언급하자면 클래스 중심으로 사용하기 때문에 객체로 관리한다. 그래서,
  - 코드의 재사용성이 낮다.
  - 코드 중복을 방지할 수 있다.
  - 유지보수가 좋다.
  - 대형 프로젝트에 적합하다.

---

<br>

## 1. 절차지향 프로그래밍 (Procedural Programming)

- 그럼 예시 코드로 절차지향 프로그래밍을 구현해보자.
- 절차지향으로 프로그래밍할 경우, 항공사의 종류 갯수대로 직접 다 입력해야 한다.

```yml

# 항공사 1
> airline_company_1 = 'Koreaair'
> airline_detail_1 = [
>     {'uniform_color': 'skyblue'},
>     {'kind':'FSC'},
>     {'price': 8000},
> ]

# 항공사 2
> airline_company_2 = 'Asiana'
> airline_detail_2 = [
>     {'color': 'gray'},
>     {'kind':'FSC'},
>     {'price': 6000},
> ]

# 항공사 3
> airline_company_3 = 't-way'
> airline_detail_3 = [
>     {'color': 'red'},
>     {'kind': 'LCC'},
>     {'price': 3000},
> ]

```

- 리스트 구조로 또 입력해보자.

```yml

> airline_company_list = ['Koreanair', 'Asiana', 't-way']


> airline_detail_list = [
>     {'uniform_color': 'skyblue', 'kind':'FSC', 'price': 8000},
>     {'color': 'gray', 'kind':'FSC', 'price': 6000},
>     {'color': 'red', 'kind': 'LCC', 'price': 3000}
> ]


> del airline_company_list[1]
> del airline_detail_list[1]

> print(airline_company_list)
['Koreanair', 't-way']

> print(airline_detail_list)
[{'uniform_color': 'skyblue', 'kind': 'FSC', 'price': 8000},
 {'color': 'red', 'kind': 'LCC', 'price': 3000}]

```

- 이런 방식으로 입력할 경우, 데이터를 삭제하기가 불편하다.
- index를 사용하여 삭제할 때, 데이터 양이 매우 많으면 index 번호를 알기가 어렵기 때문이다.
- 그리고, index로 접근 시, 실수할 가능성이 높다.

<br>

- 다음으로 딕셔너리 구조로 입력해보자.

```yml
> airlines_dicts = [
>     {'airline_company': 'Koreanair', 'airline_detail': {'uniform_color': 'skyblue', 'kind':'FSC', 'price': 8000}},
>     {'airline_company': 'Asiana', 'airline_detail': {'uniform_color': 'gray', 'kind':'FSC', 'price': 6000}},
>     {'airline_company': 't-way', 'airline_detail': {'uniform_color': 'red', 'kind':'LCC', 'price': 3000}}
> ]


> airlines_dicts[1].pop('airline_company')

> print(airlines_dicts)
[{'airline_company': 'Koreanair', 'airline_detail': {'uniform_color': 'skyblue', 'kind': 'FSC', 'price': 8000}},
  {'airline_detail': {'uniform_color': 'gray', 'kind': 'FSC', 'price': 6000}},
  {'airline_company': 't-way', 'airline_detail': {'uniform_color': 'red', 'kind': 'LCC', 'price': 3000}}]

> del airlines_dicts[1]
[{'airline_company': 'Koreanair', 'airline_detail': {'uniform_color': 'skyblue', 'kind': 'FSC', 'price': 8000}},
  {'airline_company': 't-way', 'airline_detail': {'uniform_color': 'red', 'kind': 'LCC', 'price': 3000}}]


```

- 딕셔너리 안에 딕셔너리가 있는 형식으로 작성했다.
- 여전히 코드의 반복은 지속되어, 개발자에게 피로도를 증가시킨다.
- 키 중첩 문제가 존재하며, 키 조회 시 예외 처리를 해야한다.

<br>

---

## 2. 객체지향 프로그래밍 (Object Oriented Programming)

```yml
> class Airline():
>     def __init__(self, company, details):
>         self._company = company
>         self._details = details


> Airline1 = Airline('Koreanair', {'uniform_color': 'skyblue', 'kind':'FSC', 'price': 8000})
> Airline2 = Airline('Asiana', {'uniform_color': 'gray', 'kind':'FSC', 'price': 6000})
> Airline3 = Airline('t-wau', {'uniform_color': 'red', 'kind':'LCC', 'price': 3000})

> print(Airline1)
> print(Airline2)
> print(Airline3)

<__main__.Airline object at 0x000001AC6C87CFD0>
<__main__.Airline object at 0x000001AC6C87CF70>
<__main__.Airline object at 0x000001AC6C87CEE0>

> print(Airline1.__dict__)
> print(Airline2.__dict__)
> print(Airline3.__dict__)
{'_company': 'Koreanair', '_details': {'uniform_color': 'skyblue', 'kind': 'FSC', 'price': 8000}}
{'_company': 'Asiana', '_details': {'uniform_color': 'gray', 'kind': 'FSC', 'price': 6000}}
{'_company': 't-wau', '_details': {'uniform_color': 'red', 'kind': 'LCC', 'price': 3000}}
```

- class 로 틀을 만들어서 airline instance를 손쉽게 만들었다.
- instance에 담긴 구체적인 정보를 알고싶을 때는 `__dict__` 를 사용하는 것도 알 수 있다.

<br>

- 이렇듯 절차지향과 객체지향 다 장단점이 있어 적절한 곳에 사용해야 한다.
- 하지만, 절차지향의 단점을 해결하는 객체지향을 잘 사용해서 코드의 재사용성과 유지보수까지 고려하자.

---

<br>

## Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
