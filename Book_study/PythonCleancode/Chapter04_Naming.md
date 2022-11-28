# 0. Introduction

> 1. [Concise and descriptive names](#1-concise-and-descriptive-names)   
> 2. [Casing style](#2-casing-style)   
> 3. [PEP 8’s Naming Conventions](#3-pep-8s-naming-conventions)   
> 4. [Appropriate Name Length](#4-appropriate-name-length)   

- 아래 book study는 알 스웨이가트가 지었고, 박재호님이 번역하신 [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 를 읽고 진행한 book study 입니다. 영문 원본으로 온라인 공개된 자료가 있어서 영문으로 학습합니다.

- 기존에 읽었던 Clean Code는 자바 코드로 되어 있어서, 먼저 파이썬 클린 코드를 학습 후 시작할려고 합니다.

- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바랍니다.

- 각 chapter를 읽고 내용 정리하는 식으로 진행합니다.

- 이번 chapter의 주제는 **'Chapter 04: Naming'** 입니다.

<br>

---


# 1. Concise and descriptive names

작명은 cs 지식과, 알고리즘과는 전혀 상관이 없지만, 가독성 있는 코드에서 중요한 부분을 차지한다. 

간결한 이름을 작명할 수 있지만, 이 코드가 무엇을 위해 만들어졌는지 설명되지 않는다. 

충분히 설명했지만, 간결하지 않은 이름도 있다.

이 두 가지 조건을 만족하는 작명을 하기는 어렵다.

그래서 이번에는 나쁜 이름은 피하면서 좋은 이름을 선택할 수 있는 가이드 라인을 이번 챕터에서 제공한다.

<br>

---

# 2. Casing style:

### snake_case

- underscore 를 각 단어들 사이에 사용하는 작명법
- 모든 문자들은 소문자를 사용하나, 상수는 대문자로만 작성한다.
    - ex) UPPER_SNAKE_CASE

### camelCase

- 첫 단어 이후로는 각 단어들의 첫 글자는 대문자로 작성하는 작명법

### PascalCase

- 첫 단어부터 각 단어들의 첫 글자는 대문자로 작성하는 작명법

<br>

---


# 3. PEP 8’s Naming Conventions

- 영어 글자들은 악센트 표시를 하지 않는다.
- 모듈들의 이름은 짧고, 모두 소문자로 작명한다.
- 클래스 이름은 PascalCase로 작성
- 상수는 모두 대문자인 SNAKE_CASE로 작성한다.
- function, method 그리고 변수는 소문자 snake_case로 작성한다.
- 메서드의 첫 번째 인자인 self는 소문자로 작성한다.
- 클래스 메서드의 첫 번째 인자인 cls는 소문자로 작성한다.
- 클래스의 private 속성들은 1개의 underscore로 시작한다.
- 클래스의 public 속성들은 underscore로 시작하면 안된다.
- 개발자들은 위 사항들을 엄격히 따를 필요는 없다.
    - 그래서 이 책의 저자는 변수 이름을 snake_case로 작성하는 게 아닌 camelCase로 작성하고 있다.

<br>

---

# 4. Appropriate Name Length

## Too short names

작명할 때 가장 흔히들 하는 싫수가 바로 너무 짧은 작명이다.  

짧은 작명은 처음에는 이해가 될 지 몰라도 몇 일 뒤에는 그 의미를 잊은다.

- 영문자 1개 또는 2개: g
- 생략된 단어: mon
    - 무엇을 의미하는지 정확히 알 수 없다.
- 하나의 영어 단어

하지만 허용되는 몇 가지 경우가 있다. 

- 반복문의 i(index)
- 좌표계를 나타내는 x, y

## Too long names

변수명의 길이는 전역 범위인지 지역 범위인지에 따라서도 달라진다. 

매우 긴 코드의 경우, 많은 내용을 포함하기 때문에 예를 들어 한 단어만으로는 부족할 수 있다. 그러나, 지역 변수라면 충분할 수 있다.

### Prefixes in names

예를 들어서 Cat class 안에 속성으로 weight를 넣는다면 catWeight라고 할 필요가 없다. weight만 하면 된다. 

### 작명에 숫자는 사용하지 않는다.

payment1, payment2 는 읽는 사람으로 하여끔 충분히 디테일한 정보를 전달하기 어렵다. 

### Make names Searchable

너무 변수명이 짧으면 검색해도 원하는 결과를 얻기 어렵다. 

예를 들어서 ‘email’은 너무 짧기 때문에, ‘emailAddress’, ‘downloadEmailAttachment’, ‘emailMessage’, ‘replyAddress’ 로 리팩토링하자. 

### Avoid jokes, puns, and cultural references

작명 시에, 조크나, 문화적인 배경들을 집어넣어 만들 수 있지만, 이는 뒤에 다른 사람이 봤을 때 이해하기가 어렵다. 

그래서 영어가 모국어가 아닌 사람들도 코드를 보고 쉽게 이해할 수 있도록 작성해야 한다.

### Don’t overwrite built-in names

파이썬의 내장 함수로 사용되는 이름들과, ‘data’ 는 사용하지 않는다.


<br>

---

# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 