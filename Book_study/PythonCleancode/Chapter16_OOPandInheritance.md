# 0. Introduction

> 1. [상속의 작동 방식](#1-상속의-작동-방식)  
> 2. [isinstance() 와 issubclass 함수](#2-isinstance-와-issubclass-함수)  
> 3. [Class method](#3-class-method)  
> 4. [Class attribute](#4-class-attribute)  
> 5. [Static method](#5-static-method)  
> 6. [class와 정적 객체지향 기능을 사용할 때](#6-class와-정적-객체지향-기능을-사용할-때)  
> 7. [객체지향과 관련된 전문 용어들](#7-객체지향과-관련된-전문-용어들)  
> 8. [상속을 사용하지 않아야할 경우](#8-상속을-사용하지-않아야할-경우)  
> 9. [다중 상속(Multiple inheritance)](#9-다중-상속multiple-inheritance)  
> 10. [Method 결정 순서](#10-method-결정-순서)  

- 아래 book study는 알 스웨이가트가 지었고, 박재호님이 번역하신 [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 를 읽고 진행한 book study 입니다. 영문 원본으로 온라인 공개된 자료가 있어서 영문으로 학습합니다.

- 기존에 읽었던 Clean Code는 자바 코드로 되어 있어서, 먼저 파이썬 클린 코드를 학습 후 시작할려고 합니다.

- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바랍니다.

- 각 chapter를 읽고 내용 정리하는 식으로 진행합니다.

- 이번에 학습하는 chapter의 주제는 **'Chapter 16: Object-Oriented Programming and Inheritance'** 입니다.

<br>

---

상속은 클래스에 적용할 수 있는 코드 재사용 기법이다. 

클래스들은 부모-자식 관계로 만들어 자식 클래스가 부모 클래스의 메소드 사본을 상복받는 방식으로, 여러 클래스들에 걸쳐 메소드를 복제하지 않아도 된다.

하지만, 상속된 클래스들은 거미줄처럼 얽힌 관계가 되어 복잡성이 커지기 때문에 상속을 위험하다고 생각하는 프로그래머들도 존재한다.

그렇기 때문에, 상속을 제한적으로 사용하는 게 필요하며, 이 방법으로 코드를 구성할 때 상당한 시간을 절약할 수 있다.

부모-자식 관계에 있는 클래스들을 다음과 같이 불리기도 한다. 

- **부모 클래스: parent class, super class, base class**    
- **자식 클래스: child class, sub class, derived class**   

<br>

# 1. 상속의 작동 방식

> **_하위 클래스는 상속받는 상위 클래스의 메소드를 사용할 수 있다. 하지만, 상위 클래스는 하위 클래스의 메소드를 사용할 수 없다._**

아래 코드를 통해서 상속이 어떻게 작동되는지 이해해보자.

```python
class ParentClass:
    def parentMethod(self):
        print("I'm a parent")

class ChildClass(ParentClass):
    def childMethod(self):
        print("I'm a child")

class GrandChildClass(ChildClass):
    def grandChildMethod(self):
        print("I'm a grand child")

# 인스턴스 생성
parent = ParentClass()
child = ChildClass()
grand_child = GrandChildClass()

parent.parentMethod() # I'm a parent
child.childMethod() # I'm a child
grand_child.grandChildMethod() # I'm a grand child
```

각 인스턴스의 메소드를 보면 자신이 속한 클래스에서 선언된 메소드는 사용이 가능하다. 그러면 상위 클래스의 메소드도 사용가능한지 알아보자.

```python
child.parentMethod() # I'm a parent
grand_child.childMethod() # I'm a child
grand_child.parentMethod() # I'm a grand child
```

위 결과를 통해 사용 가능한 걸 알 수 있다! 

그렇다면 상위 클래스는 하위 클래스의 메소드를 사용할 수 있을까?

```python
parent.childMethod() # AttributeError: 'ParentClass' object has no attribute 'childMethod'
parent.grandChildMethod() # AttributeError: 'ParentClass' object has no attribute 'grandChildMethod'
child.grandChildMethod() # AttributeError: 'ChildClass' object has no attribute 'grandChildMethod'
```

위 결과와 같이 AttributeError가 뜨는 걸 확인했다.

**🔆 결론: 하위 클래스는 상속받는 상위 클래스의 메소드를 사용할 수 있다. 하지만, 상위 클래스는 하위 클래스의 메소드를 사용할 수 없다는 걸 알 수 있다.** 

<br>

## method override

<br>

## super() function

<br>

## 상속보다는 합성을 

<br>


## 상속의 단점


<br>

---
# 2. isinstance() 와 issubclass 함수


<br>

---
# 3. Class method

<br>

---
# 4. Class attribute

<br>

---
# 5. Static method

<br>

---
# 6. class와 정적 객체지향 기능을 사용할 때

<br>

---
# 7. 객체지향과 관련된 전문 용어들

<br>

---
# 8. 상속을 사용하지 않아야할 경우

<br>

---
# 9. 다중 상속(Multiple inheritance)

<br>

---
# 10. Method 결정 순서

<br>

---


# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) s