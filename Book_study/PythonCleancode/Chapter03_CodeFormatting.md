# 0. Introduction

> 1. [Why we need ‘code formatting’?](#1-why-we-need-code-formatting)   
> 2. [An inconsistent mess due to non-formatting](#2-an-inconsistent-mess-due-to-non-formatting)   
> 3. [Spaces between a line: Horizontal spacing](#3-spaces-between-a-line-horizontal-spacing)   
> 4. [A space between lines: Vertical spacing](#4-a-space-between-lines-vertical-spacing)   
> 5. [Install black: code formatter](#5-install-black-code-formatter)   


- 아래 book study는 알 스웨이가트가 지었고, 박재호님이 번역하신 [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 를 읽고 진행한 book study 입니다. 영문 원본으로 온라인 공개된 자료가 있어서 영문으로 학습합니다.

- 기존에 읽었던 Clean Code는 자바 코드로 되어 있어서, 먼저 파이썬 클린 코드를 학습 후 시작할려고 합니다.

- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바랍니다.

- 각 chapter를 읽고 내용 정리하는 식으로 진행합니다.

- 이번 chapter의 주제는 **'Chapter 03: CODE FORMATTING WITH BLACK'** 입니다.

<br>

---

# 1. Why we need ‘code formatting’ ?

### What is code formatting

여러 규칙들을 외적으로 소스 코드에 적용하는 걸 ‘**code formatting**’ 이라 한다. 

### First reason: maintaining code

외적으로 소스 코드가 어떻게 보이는지는 컴퓨터에게 중요하지 않지만, 유지보수의 관점에서 중요하다. 

유지 보수의 관점에서 ‘가독성’은 중요하다. 이 가독성이 좋지 않아 다른 사람들이 이해하기 어렵다면 버그를 고치기도, 새로운 기능들을 추가하기도 어렵다. 즉, ‘code formatting’은 단지 외적인 이유에서만 그런 게 아니다. 

### Second reason: python’s critical reason

가독성은 파이썬 언어의 인기에 핵심적인 부분이기 때문에, 파이썬 언어를 사용하는 개발자에게는 **‘code formatting’** 은 필수다.

### And the solution is BLACK

포맷팅을 손쉽게 하는 방법 중 하나가 바로 ‘BLACK’이다. 

프로그램의 행동을 바꾸는 것 없이 자동적으로 포맷팅을 해주는 라이브러리다. 

<br>

---

# 2. An inconsistent mess due to non-formatting

입문자들은 언어의 문법과 프로그램 작동에만 신경쓰고 이 formatting 부분을 간과하는 면이 있어서, 각자 자신만의 스타일로 코드를 작성한다.

만약 우리가 우리 자신만의 스타일로 코드를 작성하기 시작한다면 각 사람마다 작성하는 스타일이 달라서, 코드 작성 시 다른 사람의 코드를 각자 자신만의 스타일로 수정하는데 많은 시간이 할애될 것이다. 

### The answer about the mess is PEP8

(Python Enhancement Proposal 8)

그래서 가독성 있는 코드를 작성하는데 쉬운 방법은 바로 ‘style guide’를 따르는 것이다.  

이 문서는 소프트웨어 프로젝트가 반드시 따라야만 하는 포맷팅 규칙들을 안내해준다.

<br>

---

# 3. Spaces between a line: Horizontal spacing

한 라인에서 적용되는 간격 규칙들에 대해 알아보자. 

### 들여쓸 때, tab이 아닌 space를 사용하라

대부분 tab을 사용하여 들여쓰기를 한다. 하지만 BLACK을 사용하면 이 탭을 자동적으로 빈 문자열로 바꿔준다. 

### 연산자와 식별자 사이에 공백 하나 넣기

```python
# good case 
blanks = blanks[:i] + secretword[i] + blanks[i + 1 :]

# bad case
blanks==blanks[:i]+secretWord[i]+blanks[i+1:]
```

### 쉼표(,)  앞이 아닌 뒤에 공백을 둡니다.

```python
# good case
def span(eggs, bacon, ham):
    weights = [42.0, 3.1415, 2.718]

# bad case
def span( eggs , bacon , ham ):
    weights = [42.0,3.1415,2.718]
```

### 마침표(.) 앞이나 뒤에 공백을 넣지 말기

```python
# good case
'Hello, world'.uppser()

# base case
'Hello, world' . upper()
```

### 함수, 메서드,  슬라이싱을 사용할 변수 이름 뒤에 공백을 넣지 말기

```python
# good case
print('Hello, world!')

# bad case 
print ('Hello, World!')
```

### 열린 괄호 후 또는 닫힌 괄호 전에 공백을 넣지 말기

```python
# good case
def span(eggs, bacon, ham):
    weights = [42.0, 3.1415, 2.718]

# base case
def span( eggs, bacon, hame ):
    weights = [ 42.0, 3.1415, 2.718 ]
```

### 줄 끝 주석 앞에 공백 두 개 넣기

```python
# good case
print('Hello, world!')  # display a greeting

# base case: space 1개
print('Hello, world!') # display a greeting

# base case
print('Hello, world!')#display a greeting
```

<br>

---

# 4. A space between lines: Vertical spacing

다른 줄 사이에서 적용되는 space 규칙들에 대해 알아보자.

PEP 8는 함수들 간 구분은 2줄로, 클래스들 간 구분은 2줄로, 한 클래스 내의 메서드들 구분은 1줄로 하라고 명시한다.

```python
# good case
class ExampleClass:
    def exampleMethod1():
            pass 
    # 1줄
    def exampleMethod2():
            pass 

# 2줄
def exmampleFunction():
    pass 

# base case
class ExampleClass:
    def exampleMethod1():
            pass 
    def exampleMethod2():
            pass 
def exmampleFunction():
		pass 
```

이외에 전역 변수 범위에서 나누는 것에 대해서 PEP8은 언급된 게 없지만, BLACK은 한 줄로 구분하고 있다. 

```python
# good case
def __call__(self, value):
    if not value or '@' not in value:
            raise ValidationError(self.message, code=self.code) 
    
    user_part, domain_part = value.rsplit('@', 1)

    if (domain_part not in self.domain_whitelist and
            not self.validate_domain_part(domain_part)):
        try:
            domain_part = punycode(domain_part)
        except UnicodeError:
            pass
        else:
            if self.validate_domain_part(domain_part):
                return
        raise ValidationError(self.message, code=self.code)
```

import 시에는 아래와 같은 우선순위로 묶으라고 권고하고 있다. 선택사항이다.

- math, os, sys 같은 Python standard libary 안에 모듈들을 묶기
- Django, Requests, Selenium 같은 써드 파트들 끼리 묶기
- 해당 프로그램의 지역 모듈들끼리 묶기

# 5. Install black: code formatter

```python
# install black
python3 -m pip install --user black 
```

또는 특정 파일에 대해서만 실행해보기

```python
# yourScript.py에 대해 실행해보기
python3 -m black yourScript.py
```

Black 실행 후, 변할 부분 미리 보기

```python
python3 -m black --diff yourScript.py
```

<br>

---

# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 