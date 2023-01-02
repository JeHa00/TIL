# 0. Introduction

> 1. [실세계 비유: 온라인 양식 기입](#1-실세계-비유-온라인-양식-기입)    
> 2. [클래스에서 객체 생성하기](#2-클래스에서-객체-생성하기)    
> 3. [WizCoin이라는 간단한 클래스 생성하기](#3-wizcoin이라는-간단한-클래스-생성하기)    
> 4. [Type() and __qualname__](#4-type-and-qualname)    
> 5. [OOP vs Non-OOP program comparision: Titackto game](#5-oop-vs-non-oop-program-comparision-titackto-game)   
> 6. [실세계를 위한 클래스 설계는 어렵다.](#6-실세계를-위한-클래스-설계는-어렵다)    

- 아래 book study는 알 스웨이가트가 지었고, 박재호님이 번역하신 [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 를 읽고 진행한 book study 입니다. 영문 원본으로 온라인 공개된 자료가 있어서 영문으로 학습합니다.

- 기존에 읽었던 Clean Code는 자바 코드로 되어 있어서, 먼저 파이썬 클린 코드를 학습 후 시작할려고 합니다.

- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바랍니다.

- 각 chapter를 읽고 내용 정리하는 식으로 진행합니다.

- 이번에 학습하는 chapter의 주제는 **'Chapter 15: Object-Oriented Programming and Classes'** 입니다.

<br>

---

OOP는 변수와 함수를 함께 묶어서 클래스라는 새로운 데이터 타입을 만들 수 있는 프로그래밍 언어의 특징이다. 이를 알아보고자 이번 장에서는 클래스의 정의, 클래스를 사용하는 이유, 클래스의 프로그래밍 개념에 대해 학습해본다.

# 1. 실세계 비유: 온라인 양식 기입

파이썬에서는 **클래스, 타입, 데이터 타입** 의 의미가 모두 동일하다.

클래스는 비어있는 양식 템플릿과 같으며, 해당 클래스에서 생성된 객체 (또는 인스턴스)는 실제 데이터를 채워넣은 양식과 같다.

클래스와 객체는 다음과 같이 실세계의 동일한 인물을 다양한 클래스의 객체로 표현하고, 정보를 저장할 수 있다.

![image](https://user-images.githubusercontent.com/78094972/210138751-875b48ea-f113-4e02-b295-f9334446ccb0.pngs)

### 클래스에 포함된 이 정보들은 프로그램의 필요에 따라 달라져야 한다.

많은 예제에서 Car 라는 객체에 대한 클래스를 생성하여 설명한다. 그래서 Car의 속성에 바퀴 수나, 좌석 수, 차 종류, 제조사 같은 것들만 입력하는데 이 Car라는 객체를 사용하는 곳이 매우 다양한 만큼 속성의 종류도 다양해진다. 즉, 이 Car라는 객체를 사용하는 소프트웨어의 목적에 따라 속성이 결정된다. 그리고 이 소프트웨어의 목적은 도메인에 따라 결정된다.

<br>

---

# 2. 클래스에서 객체 생성하기

`datetime`이라는 모듈을 가져와서 객체를 생성해보자.

그리고 dot annotation을 사용하여 속성과 메서드를 사용하자.

```python
import datetime
birthday = datetime.date(1999, 10, 31) # 연월일을 전달
birthday.year # 1999
birthday.month # 10 
birthday.day # 31 
birthday.weekday() # 위의 것들과 달리 괄호가 있다는 걸 보면 메소드다.
```

<br>

---

# 3. WizCoin이라는 간단한 클래스 생성하기

```python
class WizCoin:
    def __init__(self, galleons, sickles, knuts):
        """새로운 WizCoin 객체 생성한다."""
        self.galleons = galleons
        self.sickles  = sickles
        self.knuts    = knuts
    
    def value(self):
        """WizCoin 객체에 포함된 모든 동전의 가치(크넛 단위)"""
        return (self.galleons * 17 * 29) + (self.sickles * 29) + self.knuts

    def weightInGrams(self):
        """그램 단위로 동전의 무게를 반환한다.""" 
        return (self.galleons * 31.103) + (self.sickles * 11.34) + (self.knuts * 5.0)

purse = WizCoin(2, 5, 99)
coninJar = WizCoin(13, 0, 0)
```

그리고, dot annotation을 사용하여 속성에 접근할 수 있다.

<br>

## Method, __init__, self

- 메서드란? 특정 클래스의 객체와 관련된 함수다. 그래서 문자열과 관련된 메소드라면 'Hello'.lower() 는 가능하지만, ['dog', 'cat'].lower()는 불가능하다.

- `__init__`은 속성의 초깃값을 설정하는데 사용된다.  그리고, `__init__` method에는 반환값과 return 문이 없다는 걸 기억하자.

- 클래스 객체를 새롭게 생성하면, 객체를 파이썬이 만든 후, 인수 3개를 `__init__` method를 전달한다. 

- self는 반드시 self로 작성할 필요는 없지만, 관례이기 때문에 다르게 작성하면 가독성이 떨어진다.  그리고, 이 self가 메서드와 함수를 구별할 수 있는 가장 빠른 방법이다.

<br>

## Attribute

속성(attribute)란 객체와 연관된 변수를 말한다. 

이 속성은 아래 코드처럼 딕셔너리의 키와 비슷하기 때문에, 연관된 값을 읽고 수정하며 객체에 새로운 속성을 대입할 수 있다. 엄밀히 따지면 메소드는 클래스의 속성으로 간주된다.


```python
import wizcoin 

change = wizoin.WizCoin(9, 7, 20)
print(change.sickles) # 7
change.sickles += 10  
print(change.sickles) # 17

pile = wizoin.WizCoin(2, 3, 31)
print(pile.sickles) # 3
pile.someNewAttribute = '새로운 속성'
print(pile.someNewAttribute) # 새로운 속성
```

<br>

## Private attribute and method

c++ 이나 자바 등의 언어에서는 Private access로 속성을 지정할 수 있는데, 이는 이 객체 속성에 접근하거나 속성을 수정하는 권한을 허용한다.

하지만, 파이썬에서는 이를 강제할 수 없고 모든 속성과 메소드는 public access를 허용한다.

단지 밑줄(_) 하나를 붙여서 private 속성으로 지정할 뿐이다.

```python
class BankAccount: 
    def __init__(self, accountHolder):
        self._balance = 0 
        self._name = accountHolder
        
        with open(self._name + 'Ledger.txt', 'w') as ledgerFile:
            ledgerFile.write('Balance is 0\n')
    
    def deposit(self, amount):
        if amount <= 0:
            return 
        self._balance += amount 
        with open(self._name + 'Ledger.txt', 'a') as ledgerFile:
            ledgerFile.write('Deposit' + str(amount) + '\n')


    def withdraw(self, amount):
        if self._balance < amount or amount < 0:
            return 
        self._balance -= amount
        with open(self._name + 'Ledger.txt', 'a') as ledgerFile:
            ledgerFile.write('Withdraw' + str(amount) + '\n')
            ledgerFile.write('Balance is' + str(self._balance) + '\n')

acct = BankAccount('Alice') # Alice를 위한 계좌를 생성한다.
acct.deposit(120) # _balance는 deposit()을 통해 영향받을 수 있다.
acct.withdraw(40) # _balance는 withdraw()를 통해 영향받을 수 있다.

acct._balance = 100000
acct._name = 'Bob'
```



<br>

---

# 4. Type() and __qualname__

### type()

type() 내장 함수에 객체를 전달하면 반환값을 통해 객체의 데이터 타입을 알려준다.

```python
type(42) # <class 'int'>
type(42) == int # True
type('Hello') == int # False 

import wizcoin

type(42) == wizcoin.WizCoin # False
purse = wizcoin.WizCoin(2, 5, 10)
type(purse) == wizcoin.WizCoin # True 
```

### __qualname__

디버깅에 활용하도록 프로그램의 변수에 대한 정보를 기록해야 한다고 하자. 

로그 파일에는 문자열만 쓸 수 있는데, 타입 객체를 str()로 전달하면 지저분하기 때문에, `__qualname__` 속성을 이용하여 다음과 같이 작성하면 가독성 높은 문자열을 기록할 수 있다.

이 `__qualname__`은 주로 `__repr__` method를 오버라이드하여 사용한다. 이는 17장에서 나타난다.

```python
str(type(42)) # "<class 'int'>"
type(42).__qualname__ # 'int'
```

<br>

---

# 5. OOP vs Non-OOP program comparision: Titackto game

https://autbor.com/compareoop/ 이 링크로 들어가 비교해보라. 

class로 작성하는 게 보다 깔끔하고, 직관적이라는 걸 알 수 있다. 

매개변수를 넘길 필요 없이 다른 메소드를 호출하여 사용하면 된다. 이때 `self.` 를 부여준다. 

<br>

---

# 6. 실세계를 위한 클래스 설계는 어렵다.

### 실제 객체를 단순화하기
서류 양식인 클래스 설계는 매우 간단해 보인다. 양식과 클래스는 본질적으로 표현할 실제 객체를 단순화한다.

### 어떻게 단순화하지?
그런데, 이 객체들을 단순화하는 방법이 문제다. 

예를 들어 Customer 클래스를 만드는 경우, 고객의 이름과 성을 속성으로 부여해야 한다. 그런데 서부권이냐 동양권이냐에 따라서 성이 먼저 올지, 이름이 먼저 올지가 다르며, 일부 문화권에서는 성을 사용하지도 않는다. 또한, 나이의 경우에도 계산하는 방식이 다르기 때문에 birthday를 사용하여 나이를 계산하는 방식이 좋다. 

이처럼 현실 세계는 복잡하기 때문에, 이 복잡성이 잘 녹아진 클래스로 설계하는게 어렵다.

나라면 이런 경우, 나라별 사이트를 다르게 하여 문화에 따른 복잡성을 해소할 것 같다.

<br>

---


# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 