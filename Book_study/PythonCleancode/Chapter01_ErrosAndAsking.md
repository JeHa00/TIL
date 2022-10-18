# 0. Introduction

> 1. [파이썬 에러 메세지를 이해하는 방법](#1-파이썬-에러-메세지를-이해하는-방법)  
> 2. [질문하는 방법](#질문하는-방법)  


- 아래 book study는 알 스웨이가트가 지었고, 박재호님이 번역하신 [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 를 읽고 진행한 book study 입니다. 영문 원본으로 온라인 공개된 자료가 있어서 영문으로 학습합니다.

- 기존에 읽었던 Clean Code는 자바 코드로 되어 있어서, 먼저 파이썬 클린 코드를 학습 후 시작할려고 합니다.

- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바랍니다.

- 각 chapter를 읽고 내용 정리하는 식으로 진행합니다.

- 이번에 학습하는 chapter의 주제는 **'Chapter 01: DEALING WITH ERRORS AND ASKING FOR HELP'** 입니다.


<br>

---
# 1. 파이썬 에러 메세지를 이해하는 방법

파이썬 에러 메세지를 이해하는 방법에는 총 2가지가 있다. 
- 첫 번째는 에러 메세지에 있는 `traceback`을 면밀히 살펴보는 것
- 두 번째는 이 에러 메세지를 검색으로 찾는 것

첫 번째 방법부터 찾아보자. 

<br>

## Traceback을 면밀히 살펴보자. 

파이썬은 예외처리문이 다루지 않는 예외를 코드가 발생시킬 때 중단되고, 화면에 예외 메세지와 `Traceback`이 나타난다. 이 traceback에는 예외가 발생된 코드 장소와, 맨 처음에 호출된 시점을 보여준다.  

```python
1. def a():
2.     print('Start of a()')
3.     b()  # Call b().
4.
5. def b():
6.     print('Start of b()')
7.     c()  # Call c().
8.
9. def c():
10.     print('Start of c()')
11.     42 / 0  # This will cause a zero divide error.
12.
13. a()  # Call a().


### Error message
Start of a()
Start of b()
Start of c()
Traceback (most recent call last):
  File "abcTraceback.py", line 13, in <module>
    a()  # Call a().
  File "abcTraceback.py", line 3, in a
    b()  # Call b().
  File "abcTraceback.py", line 7, in b
    c()  # Call c().
  File "abcTraceback.py", line 11, in c
    42 / 0  # This will cause a zero divide error.
ZeroDivisionError: division by zero
```

<br>

### most recent call last 의 의미

가장 처음에 호출된 게 Traceback 바로 밑에 메세지이고, 가장 최근의 것이 끝에 있다는 의미

<br>

### frame summary의 의미

다음과 같은 단위를 한 개의 `frame summary`라 한다. 즉, 위에 Error는 총 4개의 frame summary를 보여준다. 

```python
File "abcTraceback.py", line 13, in <module>
    a()  # Call a().
```

- 이 frame summary는 하나의 frame object의 내부 정보를 보여준다. 
- Frame object는 함수가 호출될 때 생성되고, 반환될 때 파괴된다. 

그리고 위에 소스 코드에서의 `print`는 frame summary에서 보여지지 않는다. 
- 왜냐하면 예외가 발생시킨 함수 호출이 포함된 line만 traceback에 나타나기 때문이다.

<br>

### 마지막 frame summary

- 마지막 frame summary에는 '예외메세지'는 물론, '예외 에러의 타입명'까지 알려준다.
- 그리고, traceback이 준 line number는 파이썬이 마지막으로 에러를 발견한 장소를 의미한다. 그래서 버그의 원인이 있는 소스는 이 알려준 line number 전 어딘가에 존재한다는 걸 말해준다. 

<br>

### 문법적 에러를 늦게 발견하는 파이썬 인터프리터

```python
# 예시 코드
print('Hello.'
print('How are you?')

## error message
File "example.py", line 2
    print('How are you?')
        ^
SyntaxError: invalid syntax
```

위 frame summary에서 원인 위치를 두 번째 라인을 가리켰으나, 사실 원인은 첫 번째 줄이다. 

왜 늦게 알려주는 것일까?

왜냐하면 **_파이썬 인터프리터는 다음 라인을 읽을 때까지 문법적 오류를 알아채지 못하기 때문_** 이다. 

그래서 오류가 난 줄의 다음 줄을 가리킨다. 

<br>

### debuggr 또는 로깅 메세지 확인

위의 경우처럼 원인의 위치를 정확하게 가리키지 않는 경우, 디버거를 사용하여 프로그램 내부를 살펴보거나, 로깅 메세지를 확인해야한다. 

<br>

### 에러를 직접 검색하기

하지만, 이런 경우는 상당히 많은 시간이 소모되기 때문에, 마지막 방법인 Error messages를 인터넷 상에서 검색해보는 것이 더 빠른 솔루션이다. 

<br>

### Linter 사용하기

파이썬을 작성하면서 잠정적인 에러 부분을 보여주는 소프트웨어를 사용하는 방법이다. 
그 한 예로 Linter가 있다. 이외에도 요즘은 다양하다. 이런 툴을 사용해서 맨 첫 장소에, 처음에 에러를 방지하는 것이 제일 좋은 방법이다.

`pip install --user pyflakes`를 사용하면 설치할 수 있다. 

<br>

---
# 2. 질문하는 방법

### 피해야할 질문 방법

- 질문해도 되는지 묻는 것
- 직접 질문하지 않고, 돌려서 말하는 것
- 적절하지 포럼이나 웹사이트에 질문하는 것
- 구체적이지 않은 게시물 표제나 이메일 제목 쓰는 것
- 구체적으로 어떻게 작동하기 원하는지를 설명하지 않는 것: `프로그램이 안돌아간다`
- 전체 에러 메세지를 명시하지 않는 것
- 작성한 나의 에러 코드를 공유하지 않는 것
- 잘못 포매팅된 코드를 공유하는 것
- os 또는 version에 대한 정보를 주지 않는 것
- 누군가가 나를 대신하여 프로그램을 작성해주기를 바라는 것  

<br>

### 질문 잘 작성하는 방법

- 미리 충분한 정보를 제공하여 질문 응답 시간을 줄이자.
    - 오프라인에서는 질문해도 되는지 묻는 것은 좋은 예의다. 
    - 하지만 온라인에서는 응답하는데 시간이 걸리기 때문에, 충분한 정보를 제공하라.

- 확실하게 물음표(?)를 가진 질문 형식으로 질문하라. 
    - 무엇을 말하는지 이해할거라 생각하지만, 그렇지 않다.
    - ex) 잘못된 예시: 이렇게 되면 좋겠습니다, 코드가 작동하지 않습니다.

- 적절한 웹사이트에 질문하라. ex) 자바스크립트 사이트에 파이썬 지문 하지 말기

- 질문 제목 란에 질문 내용을 한 문장으로 약하라. 
- 해당 작성된 코드로 무엇을 하고 싶은지, 그 의도를 설명하라. 
    - 이거 왜 작동안하는 거에요? 라고 질문하지 말고, 이런 목적으로 작성했는데 왜 안될까요?
- Error message를 일부가 아닌 전체를 공유하라.

- 소스 코드도 일부가 아닌, 전체를 공유하라. 

- 적절한 포매팅으로 코드를 읽기 쉽게 만들기 
- 이 에러를 해결하기 위해 무슨 시도를 했는지 알리기  
- 컴퓨터 및 환경 설정 사항을 공유하기 ex) version


<br>

### 좋은 질문 예시

```markdonw
[Title] Selenium 웹드라이버 : 어떻게 해야 엘리먼트의 '모든' 속성을 찾을 수 있을까요?

파이썬 'Selenium 모듈' 에서 'WebElement' 객체에 대한 속성들을 다음과 같이 'get_attribute()' 함수로 가져오려고 했습니다.

그런데, 'href' 로 이름 붙은 속성이 존재하지 않아 'None' 이 반환됐습니다.

엘리먼트가 가진 모든 속성을 어떻게 가져오는지가 궁금합니다.

'get_attributes( )' 나 'get_attribute_name( )' 같은 메소드를 찾지 못했습니다.

저는 파이썬에서 2.44.0 버전 Selenium 모듈을 사용하고 있습니다.
```

- 질문 제목에 내용 요약
- 물음표 형식 사용
- 무엇을 시도했는지 설명
- 질문이 명확  
- 버전 명시 



<br>

---

# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB)