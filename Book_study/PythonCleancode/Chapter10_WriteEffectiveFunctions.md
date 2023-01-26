# 0. Introduction

> 1. [함수명](#1-함수명)  
> 2. [함수 크기의 트레이드 오프](#2-함수-크기의-트레이드-오프)  
> 3. [함수 파라미터와 인수](#3-함수-파라미터와-인수)  
> 4. [함수형 프로그래밍](#4-함수형-프로그래밍)  
> 5. [결과값은 항상 동일한 데이터 타입이어야 한다](#5-결과값은-항상-동일한-데이터-타입이어야-한다)  
> 6. [예외 발생시키기 vs 에러 코드 반환하기](#6-예외-발생시키기-vs-에러-코드-반환하기)  


- 아래 book study는 알 스웨이가트가 지었고, 박재호님이 번역하신 [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 를 읽고 진행한 book study 입니다. 영문 원본으로 온라인 공개된 자료가 있어서 영문으로 학습합니다.

- 기존에 읽었던 Clean Code는 자바 코드로 되어 있어서, 먼저 파이썬 클린 코드를 학습 후 시작할려고 합니다.

- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바랍니다.

- 각 chapter를 읽고 내용 정리하는 식으로 진행합니다.

- 이번에 학습하는 chapter의 주제는 **'Chapter 11: Common Python Gotchas - Writing effective functions'** 입니다.


<br>

---

# 1. 함수명  

### 함수명: 동사 + 대상 명사
함수명은 식별자에 사용되는 규칙을 따라야 한다. 그런데, 함수는 통상적으로 동작을 수행하기 때문에 함수명에 동사가 들어가며, 행위 대상을 설명하는 명사가 들어가기도 한다. 
- ex) refreshConnection(), setPassword(), extract_version()


클래스나 메서드의 이름은 명사가 필요하지 않을 수 있다. 왜냐하면 이해를 위한 문맥이 제공되기 때문이다. Webbrowser 라는 class 안에 open() 이라는 메서드를 만들면 굳이 openWebbrowser를 하지 않아도, 웹 브라우저를 여는 함수인 걸로 이해할 수 있기 때문이다. 


### 약어나 너무 짧은 이름보다, 길고 충분히 설명이 되는 이름  

수학자라면 gcd가 뭔지 알 수 있다. 하지만 그 외의 사람들은 gcd를 보고 Greatest Common Denominator, 최대 공약수 를 생각하지 못 한다. 그래서 약어나 짧은 이름보다는 충분히 설명이 되는 이름으로 작성한다.

### 예약어를 피한다.
이미 파이썬 내장 함수로 사용되는 명칭들은 사용하지 않는다.  

<br>

---
# 2. 함수 크기의 트레이드 오프  

긴 코드로 큰 함수 하나를 만들건지, 아니면 짧은 코드로 여러 개의 함수를 만들 건지 고려해야 한다. 각각의 장단점에 대해 알아보자. 

- 짧은 함수의 장점
    - 함수 코드를 이해하기가 더 쉽다. 
    - 필요한 파라미터가 더 적다.   
    - 테스트와 디버그가 더 쉽다.  
    - 예외가 더 적게 발생한다.  

- 짧은 함수의 단점
    - 프로그램에서 필요한 함수 개수가 늘어나기 쉽다.  
    - 함수가 더 많다는 건 프로그램이 더 복잡해지는 걸 의미한다.  
    - 함수 간의 관계가 더욱 복잡해진다.  
    - 함수 개수가 늘어나면서 정확한 이름을 작성하기 어렵다.  
    - 함수를 많이 사용할수록 작성할 문서도 많아진다.  

<br>

---
# 3. 함수 파라미터와 인수  

<br>

---
# 4. 함수형 프로그래밍   

<br>

---
# 5. 결과값은 항상 동일한 데이터 타입이어야 한다  

<br>

---
# 6. 예외 발생시키기 vs 에러 코드 반환하기  

<br>

---




<br>

---

# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB)