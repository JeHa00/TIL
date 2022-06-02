# Python basic 14: 파이썬 클래스(class)

<br>

## Introduction

> 1. [OOP란?](#1-oop란)
> 2. [Class와 instance의 차이](#2-class와-instatnce의-차이)
> 3. [Self 의 이해: class method, instance method](#3-self-의-이해-class-method-instance-method)
> 4. [class, instance variable](#4-class-instance-variable)

<br>

- `OOP`를 요약하여 간단히 핵심만 짚고, 이 이론을 바탕으로 python에서 class와 instance를 구현하겠다.

<br>

# 1. OOP란?

- OOP : Object Oriented Programming 의 약자로, `객체 지향 프로그래밍' 이라 한다.
- Object(객체) : 소프트웨어로 구현할 대상
- **OOP의 특징: _Encapsulation(캡슐화)_** 로 `attribute` 와 `method`를 하나로 묶어서 객체로 구성하는 것
- `OOP`의 이점
  - `Encapsulation(캡슐화)`를 통해서 내부 정보를 외부로부터 보호하면서, `주변에 악영향 (side effect)`을 최소화할 수 있다. 이를 `Infomation hiding(정보 은폐)`이라 한다.
  - `class`를 통해 만들기 때문에 `코드의 재사용성`이 용이하다. => `경제적`
    - 코드의 개선, 수정이 용이하다.
    - 버그가 발생했을 때 유지보수 또한 용이하다.
- 하지만, `OOP`가 항상 빠르진 않다. 경우에 따라서는 `객체 지향`보다 `절차 지향`이 더 빠른 퍼포먼스를 가질 수 있으므로, `객체 지향`과 `절차 지향`을 적절히 섞어 사용하자.
  - `절차 지향` : 위에서부터 아래로 실행하는 것

> **_절차지향과 OOP 비교해보기_** : [[TIL] Python basic 24: Procedural Programming vs OOP](https://jeha00.github.io/post/python_basic/python_basic_24_oop/)

<br>

---

# 2. Class와 instatnce의 차이

- 눈에 보이는 `실체`들 중에서 `소프트웨어로 구현할 대상`을 선정한다.
  - 소프트웨어로 구현할 대상을 `객체(Object)`라 한다.
- 그리고, 이 **_객체(Object)_** 를 **_class라는 틀_** 을 통해서 소프트웨어적으로 묘사한 것을 **_instance_** 라 한다.

<br>

<br>

## 2.1 Class 만들고 호출하기

- **class 만들기 위한 상황**

  - 애완견 용품에서 사용하는 소프트웨어를 개발한다고 가정하자.
  - 개의 종은 매우 다양하기 때문에, 개의 종이 추가될 때마다 변수 입력한다면 그 양이 매우 많아지고, 가독성도 떨어진다.
  - 하지만, 클래스를 이용한다면 눈에 보이는 애완견 실체를 `클래스 형태`로 구성해서 `instance`로 만들 수 있다.

<br>

- `class` 라는 예약어를 통해 클래스를 만들기 시작한다.
  - 모든 클래스는 object를 상속받기 때문에, 선언 방법은 자유롭다.
  - `__init__`은 파이썬에서 클래스가 초기화될 때, 반드시 호출되는 함수다.

```yml
# Dog라는 class를 생성한다.
> class Dog():
# 또는
> class Dog(object):

>    species = "firstdog"
>
>    # 모든 class는 초기화 method 및 인스턴스 속성을 가질 수 있다.
>    # self 후에, 인스턴스에 사용할 변수 속성들을 입력한다.
>    def __init__(self, name, age):

>       # 초기화 method를 입력하고 나서, 입력한 인스턴스 속성들에 정확히 mapping 한다.
>       self.name = name
>       self.age = age
```

- `class` 정보를 호출해보자.

```yml
## class 정보 호출하기
# 클래스가 코드로 구현된 걸 확인할 수 있다.
> print(Dog)
<class '__main__.Dog'>
```

<br>

## 2.2 인스턴스화하기

> **_'인스턴스화'란 class를 통해 구현된 instance를 할당하는 것을 말한다. 인스턴스화한 것은 모두 다 다른 id값을 가진다._**

- `instance`는 변수에 할당하여 활용되며, 이는 메모리에 올라가서 각각의 다른 `id`값을 가진다. 전혀 다른 객체로 간주된다.
- 또한, 각 instance는 동일한 속성값을 가져도, 파이썬에게는 전혀 다른 객체로 간주된다.

```yml
## 인스턴스화
# 할당될 변수 = 클래스 이름(instance 속성들)
> a = Dog("mikky", 2)
> b = Dog("baby", 3)
> c = Dog("mikky", 2)

# 모두 다른 id값을 가진다.
> print(a == c, id(a), id(b), id(c))
False 2542532857088 2542532856992 2542532856560
```

<br>

## 2.3 namespace 확인하기

> **_'namespace'란 python의 attribute name이 dictionary data type으로 구현 및 저장된 공간_** 으로서, dictionary가 python의 naming system에 사용하기 때문에, 각 name은 key로서 중복되지 않는다.

- `dir()` 과 `.__dict__`를 통해서 namespace를 확인할 수 있는데, 그럼 이 두 가지의 차이는 무엇일까??

  |                    | dir( )                                             | .\_\_dict\_\_                                            |
  | ------------------ | -------------------------------------------------- | -------------------------------------------------------- |
  | function or method | built-in function(내장함수)                        | magic method(special method)                             |
  | Data type          | list                                               | dictionary                                               |
  | 호출 내용          | 객체가 가진 속성 name과 method 정보 ( key name만 ) | 객체의 속성 name과 name의 value까지 ( key name과 value ) |

```yml
# dict로 instance의 namespace 확인하기
> print('dog1', a.__dict__)
> print('dog2', b.__dict__)

dog1 {'name': 'mikky', 'age': 2}
dog2 {'name': 'baby', 'age': 3}

# dir로 instance의 namespace 확인하기
> print('instance dir > ', dir(a))
instance dir >  ['__class__','__dict__', '__dir__', ..., 'age', 'name', 'species']

# dict로 class의 namespace 확인하기
> print('class dict > ', Dog.__dict__)
class dict >  {'__module__': '__main__', 'species': 'firstdog',
'__init__': <function Dog.__init__ at 0x0000019C2641AA60>,
'__dict__': <attribute '__dict__' of 'Dog' objects>,
'__weakref__': <attribute '__weakref__' of 'Dog' objects>, '__doc__': None}

# dir로 class의 namespace 확인하기
> print('class dir > ', dir(Dog))
class dir >  ['__class__', '__delattr__', '__dict__', '__dir__',..., 'species']
```

- namespace 를 통해서 class와 instance들이 가지고 있는 속성들을 확인할 수 있다.
- class 는 하나지만, instance를 만들 때 다른 속성 값을 입력했기 때문에, `dir()` 이 아닌, `.__dict__`를 통해서 이를 확인할 수 있다. 그래서 instance의 속성값을 확인할 때는 `.__dict__` 를 사용하자.

<br>

---

# 3. Self 의 이해: class method, instance method

> - **_class method_** : 클래스 변수를 인자로 받는 method
> - **_instance method_** : 인스턴스 변수를 인자로 받는 method
> - **_self_** : instance를 인자로 받는 매개변수

- **method 호출방법**

  - **_class method 1가지 방법_**
    - _주의 사항: 인스턴스화한 변수를 통해서 **class method** 를 호출하면 error가 뜬다._
    - class로 바로 호출하는 방법 (1-1 방법)
  - **_instance method 2가지 방법_**
    - 첫 번째: 인스턴스화 변수를 통해서 **_instance method_** 를 호출하는 방법 (2-1 방법)
    - 두 번째: 클래스로 접근하여 인자에 인스턴스를 넘겨주는 방법 (2-2 방법)

- instance 변수를 만들지 않고 사용할 것이기 때문에, '\_\_init\_\_' 생성자를 만들지 않는다.

  - '\_\_init\_\_' 이 없으면 파이썬이 알아서 클래스를 만들 때 내부적으로 실행한다.

  ```yml
  > class SelfTest:
  >   def func1():
  >       print('Func1 called')
  >   def func2(self):
  >       print(id(self))
  >       print('Func2 called')

  # 변수를 인스턴스화 한다.
  > f = SelfTest()

  # dir로 변수 내부를 확인한다.
  > print(dir(f))
  ['__class__', '__delattr__', '__dict__', '__dir__', '__doc__',..., 'func1', 'func2']

  # 인스턴스화된 f를 통해서 func1을 호출해보자.
  # func1에는 매개변수가 없는데, 1개가 넘어갔다는 걸 알 수 있다.
  > f.func1()
  TypeError: func1() takes 0 positional arguments but 1 was given
  ```

  - func1은 매개변수가 없는데, 왜 매개변수로 1개가 넘어간 것일까? 넘어간 매개변수는 무엇일까??
    - 바로 인스턴스가 인자로 넘어간 것이다. 인스턴스에 dot operator로 class의 method에 접근하면 인스턴스가 인자로 넘어가기 때문에, 위와 같은 TypeError가 발생했다.

- 2-1 방법으로 인스턴스화된 f를 통해서 func2를 호출해보자.

  ```yml
  > f.func2()
  2799723753424
  Func2 called

  # f의 id 값을 호출해보자.
  > print(id(f))
  2799723753424
  ```

  - 위 결과를 통해서 다음과 같은 이유로 **_self는 instance를 인자로 받는 매개변수_** 이며, **self가 있는 method** 는 **instance의 method** 인 걸 알 수 있다.
    - 첫 번째, func1을 호출한 방법과 동일한 방법으로 self가 매개변수로 있는 func2를 호출하니 Error가 뜨지 않았다.
    - 두 번째, `f.func2()` 에 의해서 출력된 `id` 와 `id(f)`의 값이 동일하다.

- 1-1 방법으로 class method를 호출해보자.

  ```yml
  ## class method 호출하기 (1-1 방법)
  > SelfTest.func1()
  Func1 called
  ```

  - 위 결과를 통해서 다음과 같은 이유로 **self가 없는 method** 는 **class method**인 걸 알 수 있다.
    - `func1`을 인스턴스로 접근하여 호출했을 때는 TypeError가 떴었다.
    - 하지만, class로 직접 접근하여 호출하니, 정상적으로 출력된 걸 알 수 있다.

- 만약 class로 접근하여 func2를 호출한다면 어떻게 될까???

  ```yml
  > SelfTest.func2()
  Typeerror: func2() missing 1 required positional argument: 'self'
  ```

  - func2가 요구하는 매개변수 1개를 놓쳤다는 TypeError를 확인할 수 있다.

- 그러면 2-2 방법으로 매개변수 1개를 입력해보자.

  ```yml
  > SelfTest.func2(f)
  2332370018256
  Func2 called
  ```

  - 그래서 클래스로 접근해도 instance method에 인스턴스화한 변수를 인자로 넘기니 정상적으로 작동됨을 알 수 있다.

<br>

---

# 4. class, instance variable

<br>

- 다른 클래스를 만들어보자.

  - `instance attribute`를 만들어서 사용할 것이기 때문에 `__init__` 생성자를 사용한다.
  - 그리고 `생성자`와는 반대로 `소멸자`를 사용했다.
    - `소멸자`: 객체가 소멸될 때 즉, 메모리에서 지워질 때 자동으로 호출되는 함수

```yml
> class Warehouse():

>    # class variable
>    stock_num = 0
>
>    def __init__(self, name): # 생성자
>        # instance variable
>        # mapping
>        self.name = name
>        Warehouse.stock_num += 1
>
>    def __del__(self): # 소멸자
>        Warehouse.stock_num -= 1

# user1 instance의 name을 Lee로 한다.
> user1 = Warehouse('Lee')

# user2 instance의 name을 Cho로 한다.
> user2 = Warehouse('Cho')
```

## 4.1 class 속성, instance 속성 확인하기

> **_namespace에는 객체의 속성들이 있는 곳임을 확인했다. 그러면 class와 instance의 각 속성들을 한층 더 들어가 확인해보자._**

- **Class variable(클래스 변수)** 는

  - class 내부 method를 정의하기 전인 enclosing-scope에 정의한 변수
  - 직접 접근이 가능하다.
  - 클래스 변수는 공유되기 때문에, 모든 인스턴스에서 공통으로 가지고 있는 변수
  - instance의 namespace에 있지 않고, class의 namespace에 존재한다.

- **instance variable(인스턴스 변수)** 는

  - `self`가 붙은 것들이 `instance varible(인스턴스 변수)`다.
  - 직접 접근이 아닌, 인스턴스화된 변수로 접근이 가능하다.
  - 객체마다 별도로 존재한다.
  - `namespace` 라는 **_인스턴스만의 공간_** 을 별도로 갖고 있어서, namespace를 통해 인스턴스 변수를 확인한다.

<br>

- **클래스 변수에 접근하기**

  - class name으로 접근하기
  - 인스턴스화된 변수를 통해서 접근하기

  ```yml
  # 클래스로 직접 접근하기
  > print(Warehouse.stock_num)
  2

  # 인스턴스화된 변수를 통해서 접근하기
  > print(user1.stock_num)
  2
  ```

  - 직접 접근이 가능하며, 모든 인스턴스가 공유한다는 걸 알 수 있다.

- **인스턴스 변수에 접근하기**

  - 인스턴스화된 변수를 통해서 접근하기

  ```yml
  > print(user1.name)
  Lee

  > print(user2.name)
  Cho
  ```

- class와 instance의 각 namespace에서 class 변수와 instance 변수를 확인해보자.

  ```yml
  # instance의 namespace에서는 공통적으로 가지고 있는 클래스 변수를 찾을 수 없다.
  # instance 변수는 찾을 수 있다.
  > print(user1.__dict__)
  {'name': 'Lee'}

  > print(user2.__dict__)
  {'name': 'Cho'}

  # class의 namespace에서 찾을 수 있다.
  > print(Warehouse.__dict__)
  {'__module__': '__main__', 'stock_num': 2,
   '__init__': <function Warehouse.__init__ at 0x000002DC6B59F8B0>,
   '__del__': <function Warehouse.__del__ at 0x000002DC6B59F940>,
   '__dict__': <attribute '__dict__' of 'Warehouse' objects>, ...}
  ```

  - 위 코드를 통해서 다음을 알 수 있다.

    - 클래스 변수는 인스턴스의 공통된 변수이지만, 인스턴스의 네임스페이스에서는 찾을 수 없다.
    - 클래스 변수는 클래스의 네임스페이스에서 찾을 수 있다.

- 그리고 소멸자를 사용하여 instance를 삭제하면, 클래스 변수인 stock_num이 감소한 걸 확인할 수 있다.

  ```yml
  > del user1
  > print('after', Warehouse.__dict__)
  {'__module__': '__main__', 'stock_num': 1, ...}
  ```

<br>

## 4.2 Python의 namespace lookup 원리

> **_파이썬은 object의 이름을 다음 순서로 찾는다._**  
> _super class는 해당 class의 상위 class를 의미한다._  
> _`instance`의 namespace -> `class`의 namespace -> `super`의 namespace_

- **여기서 한 가지 의문이 있다.**
  - instance의 namespace에서는 class 변수가 없다. 그러면 인스턴스화한 변수를 통해서 클래스 변수가 어떻게 출력되는걸까???
  - 이는 python이 instance의 namespace에서 찾지 못하자, class의 namespace에서 찾아 출력하는 것이다.

<br>

---

# 5. Class의 장점

- 마지막으로 또 다른 클래스를 만들어보면서 위 내용들을 음미해보자.

```yml
## Dog라는 class를 만든다.
> class Dog():
>       # 클래스 변수 선언
>     species = 'Firstdog'

>       # 생성자 및 인스턴스 속성 생성 그리고 mapping
>     def __init__(self, name, age):
>         self.name = name
>         self.age = age

>       # 인스턴스 메소드
>     def info(self):
>        return '{} is {} years old'.format(self.name, self.age)

>       # 인스턴스 메소드
>       # sound는 호출 시에 입력한다.
>    def speak(self, sound):
>        return '{} says {}!'.format(self.name, sound)

## 인스턴스화 + 인스턴스 속성값 입력
> c = Dog('july', 4)
> d = Dog('Marry', 10)

## 인스턴스 메소드 호출
> print(c.info())
july is 4 years old
> print(d.info())
Marry is 10 years old

## 인스턴스 메소드 호출 + sound 매개변수 입력
> print(c.speak('wal wal'))
july says wal wal!
> print(d.speak('Mung Mung'))
Marry says Mung Mung!
```

- 위 예제들을 통해서 `class`의 장점을 다시 한 번 확인할 수 있다.
  - `class` 하나를 만들어놓고 찍어내듯이 사용할 수 있다.
  - `instance`만의 공간도 있고, 공유하는 공간이 있다.
  - 그래서 `코드의 재사용성`이 좋다는 것이다.
- `코드의 재사용성`이 좋다는 의미는 더 구체적으로 말하자면

  - 객체지향에 입각하여 불필요한 중복을 방지한다.
  - 깔끔한 코드를 통해 프로그램 개발을 할 수 있다.
  - 생산성이 향상되고, 성능도 코드에 따라 좋아진다.

<br>

---

# Reference

- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
