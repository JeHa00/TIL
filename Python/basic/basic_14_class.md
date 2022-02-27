# Python basic 14: 파이썬 클래스(class)

<br>

## Intro

> 1. OOP란?
> 2. Class, instance
> 3. Self 개념
> 4. instance method
> 5. class, instance variable

<br>

- `OOP`를 요약하여 간단히 핵심만 짚고, 이 이론을 바탕으로 python에서 class와 instance를 구현하겠다.

<br>

## 1. OOP란?

- `OOP` : Object Oriented Programming으로, `객체 지향 프로그래밍`이라 한다.
- `Object(객체)` : 소프트웨어로 구현할 대상
- `OOP`의 특징
  - `Encapsulation(캡슐화)` : `attribute` 와 `method`를 하나로 묶어서 객체로 구성하는 것
- `OOP`의 이점
  - 눈에 보이는 사물들을 객체화할 수 있다 == 소프트웨어로 구현할 수 있다
  - `Encapsulation(캡슐화)`를 통해서 주변에 악영향 (side effect)을 최소화할 수 있다.
    - 이를 `Infomation hiding(정보 은폐)`이라 한다.
  - `class`를 통해 만들기 때문에 `코드의 재사용성`이 용이하다.
    - 코드의 개선, 수정이 용이하다.
    - 버그가 발생했을 때 유지보수 또한 용이하다.
- OOP가 반드시 빠른 건 아니다. 경우에 따라서는 `객체 지향`보다 `절차 지향`이 더 빠른 퍼포먼스를 가질 수 있다. 그래서 `객체 지향`과 `절차 지향`을 적절히 섞어서 코딩하자.
  - `절차 지향` : 위에서부터 아래로 실행하는 것

---

<br>

## 2. Class, instatnce

- 눈에 보이는 `실체`들 중에서 `소프트웨어로 구현할 대상`을 선정한다.
- 소프트웨어로 구현할 대상을 `객체`라 한다.
- 그리고, 이 `객체`를 `class`라는 틀을 통해서 소프트웨어적으로 묘사한 것을 `instance`라 한다.

<br>

- `namespace(이름공간)` : 객체를 인스턴스화 할 때 `dictionary` 형태로 저장되는 공간
  - 인스턴스만의 공간
- Class variable : 직접 접근이 가능하며, 클래스 변수는 공유한다.
- instance variable : 객체마다 별도 존재하며, 네임스페이스를 통해 확인한다.
