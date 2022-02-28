# Python basic 14: 파이썬 클래스(class)

<br>

## Intro

> 1. OOP란?
> 2. Class와 instance의 차이
> 3. Self 의 이해: class method, instance method
> 4. class, instance variable

<br>

- `OOP`를 요약하여 간단히 핵심만 짚고, 이 이론을 바탕으로 python에서 class와 instance를 구현하겠다.

<br>

## 1. OOP란?

- `OOP` : `Object Oriented Programming`으로, `객체 지향 프로그래밍`이라 한다.
- `Object(객체)` : 소프트웨어로 구현할 대상
- `OOP`의 특징
  - `Encapsulation(캡슐화)` : `attribute` 와 `method`를 하나로 묶어서 객체로 구성하는 것
- `OOP`의 이점
  - 눈에 보이는 사물들을 객체화할 수 있다 == 소프트웨어로 구현할 수 있다
  - `Encapsulation(캡슐화)`를 통해서 `주변에 악영향 (side effect)`을 최소화할 수 있다.
    - 이를 `Infomation hiding(정보 은폐)`이라 한다.
  - `class`를 통해 만들기 때문에 `코드의 재사용성`이 용이하다. => `경제적`이다.
    - 코드의 개선, 수정이 용이하다.
    - 버그가 발생했을 때 유지보수 또한 용이하다.
- 하지만, `OOP`가 무조건 빠른 건 아니다. 경우에 따라서는 `객체 지향`보다 `절차 지향`이 더 빠른 퍼포먼스를 가질 수 있다. 그래서 `객체 지향`과 `절차 지향`을 적절히 섞어서 사용하자.
  - `절차 지향` : 위에서부터 아래로 실행하는 것

---

<br>

## 2. Class와 instatnce의 차이

- 눈에 보이는 `실체`들 중에서 `소프트웨어로 구현할 대상`을 선정한다.
- 소프트웨어로 구현할 대상을 `객체(Object)`라 한다.
- 그리고, 이 `객체(Object)`를 `class`라는 `틀`을 통해서 소프트웨어적으로 묘사한 것을 `instance`라 한다.
- 그렇기 때문에 `instance`는 `객체(Object)`라는 개념에 포함된다.

<br>

- `Class variable(클래스 변수)` 는
  - 직접 접근이 가능하다.
  - 클래스 변수는 공유된다. == 모든 인스턴스에서 동일하게 가지고 있는 것
- `instance variable(인스턴스 변수)` 는
  - `self`가 붙은 것들이 `instance varible(인스턴스 변수)`다.
  - 인스턴스화된 변수로 접근이 가능하다.
  - 객체마다 별도로 존재한다.
  - 위에 설명한대로 `namespace`라는 그 인스턴스만의 공간을 별도로 갖고 있어서, namespace를 통해 확인한다.

<br>

### 2.1 Class 만들기

- `class` 만들기 위한 상황
  - 애완견 용품에서 사용하는 소프트웨어를 개발한다고 가정하자.
  - 개의 종은 매우 다양하기 때문에, 개의 종이 추가될 때마다 변수를 입력한다면 그 양이 매우 많아지고, 가독성도 떨어진다.
  - 하지만, 클래스를 이용한다면 눈에 보이는 애완견 실체를 `클래스 형태`로 구성해서 `instance`로 만들 수 있다.

<br>

- `class`를 만들어보자.
- `__init__`은 파이썬에서 클래스가 초기화될 때, 반드시 호출되는 함수다.

```yml
## class 만들기

# class라는 예약어로 class를 만들기 시작한다.
# Dog라는 class를 생성한다.
# 파이썬의 모든 class는 object를 상속받기 때문에, 선언 방법은 자유롭다.
> class Dog():  # class Dog 도 가능하다.

>    ## 클래스 변수 지정
>    # 클래스 변수는 모든 인스턴스에서 동일하다.
>    species = "firstdog"
>
>    # 모든 class는 초기화 method 및 인스턴스 속성을 가질 수 있다.
>    # self 후에, 인스턴스에 사용할 변수 속성들을 입력한다.
>    def __init__(self, name, age):

>       # 초기화 method를 입력하고 나서, 입력한 인스턴스 속성들에 정확히 mapping 한다.
>       self.name = name
>       self.age = age
```

<br>

### 2.2 Class 호출하기

- `class` 정보를 호출해보자.

```yml
## class 정보 호출하기
> print(Dog)
<class '__main__.Dog'>
# 클래스가 코드로 구현된 걸 확인할 수 있다.
```

<br>

### 2.3 인스턴스화하기

- `인스턴스화` == class를 통해 구현된 instance를 변수에 할당하여 사용하도록 한다.
- 즉, `instance`는 변수에 할당하여 활용될 수 있다.
- 변수에 할당했기 때문에, 메모리에 올라가고 `id`값을 받는다.
- 각 instance는 동일한 속성값을 가져도, 파이썬에게는 전혀 다른 객체로 간주된다.
- **인스턴스화한 것은 모두 다 다른 id값을 가진다.**

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

### 2.4 namespace 확인하기

- `namespace` 확인하기
- `namespace` 란
  - 객체를 인스턴스화 할 때, instance의 속성들이 `dictionary` 형태로 저장되는 공간으로, instance들이 가지고 있는 속성들을 확인할 수 있다.
- `class`는 하나지만, 서로 다른 속성들을 확인할 수 있다.
- `instance`만의 공간이다.
- `instance`의 `namespace`를 확인할 때는 `__dict__` 를 사용한다.

```yml
## namespace 확인하기
> print('dog1', a.__dict__)
> print('dog2', b.__dict__)

dog1 {'name': 'mikky', 'age': 2}
dog2 {'name': 'baby', 'age': 3}
```

### 2.5 class 속성, instance 속성 확인하기

- `Class variable(클래스 변수)` 는
  - 직접 접근이 가능하다.
  - 클래스 변수는 공유된다. == 모든 인스턴스에서 동일하게 가지고 있다.
- `instance variable(인스턴스 변수)` 는
  - `self`가 붙은 것들이 `instance varible(인스턴스 변수)`다.
  - 인스턴스화된 변수로 접근이 가능하다.
  - 객체마다 별도로 존재한다.
  - 위에 설명한대로 `namespace`라는 그 인스턴스만의 공간을 별도로 갖고 있어서, namespace를 통해 확인한다.

<br>

- `클래스 변수`에 접근하기

```yml
## 클래스로 직접 접근하기
> print(Dog.species)
firstdog

## 인스턴스화된 변수를 통해서 접근하기
> print(a.species)
firstdog
> print(b.species)
firstdog

# 직접 접근이 가능하며, 하나의 값을 공유한다는 걸 알 수 있다.
```

- `instance 변수`에 접근하기

```yml

## 인스턴스화된 변수를 통해서 instance 속성에 접근하기
# a와 b를 인스턴스화했기 때문에 a와 b로 접근이 가능하다.
> print('{} is {} and {} is {}'.format(a.name, a.age, b.name, b.age))
mikky is 2 and baby is 3


> if a.species == 'firstdog':
>    print('{0} is a {1}'.format(a.name, a.species))
mikky is a firstdog

```

- 위 예제들을 통해서 `class`의 장점을 다시 한 번 확인할 수 있다.

- `class`의 장점
  - `class` 하나를 만들어놓고 찍어내듯이 사용할 수 있다.
  - `instance`만의 공간도 있고, 공유하는 공간이 있다.
  - 그래서 `코드의 재사용성`이 좋다는 것이다.
  - `코드의 재사용성`이 좋다는 의미는 더 구체적으로 말하자면
    - 객체지향에 입각하여 불필요한 중복을 방지하고, 깔끔한 코드를 통해 프로그램 개발을 할 수 있다.
    - 생산성이 향상되고, 성능도 코드에 따라 좋아진다.

---

<br>

## 3. Self 의 이해: class method, instance method

- `__init__`이 없으면 파이썬이 내부적으로 알아서 클래스를 만들 때 내부적으로 실행한다.
- `instance 변수`를 만들지 않고 사용할 것이기 때문에 `__init__`을 만들지 않는다.
- `dir()`로 변수 내부를 확인할 수 있다.
- 매개변수에 `self` 가 있으면 `instance method`다.
- 매개변수에 `self`가 없다면 `class method` 다.
- `method` 호출하기
  - `class method` 1가지 방법
    - 주의 사항: 인스턴스화한 변수를 통해서 `class method`를 호출하면 error가 뜬다.
    - `class`로 바로 호출하는 방법 (1-1 방법)
  - `instance method` 2가지 방법
    - 첫 번째: 인스턴스화한 변수를 통해서 `instance method`를 호출하는 방법 (2-1 방법)
    - 두 번째: 클래스로 접근하여 매개변수에 인스턴스를 넘겨주는 방법 (2-2 방법)

```yml
> class SelfTest:
>   def func1():
>       print('Func1 called')
>   def func2(self):
>       print(id(self))
>       print('Func2 called')

## 변수를 인스턴스화 한다.
> f = SelfTest()

## dir로 변수 내부를 확인한다.
> print(dir(f))
['__class__', '__delattr__', '__dict__', '__dir__', '__doc__', '__eq__', '__format__', '__ge__', '__getattribute__', '__gt__', '__hash__', '__init__', '__init_subclass__', '__le__', '__lt__', '__module__', '__ne__', '__new__', '__reduce__', '__reduce_ex__', '__repr__', '__setattr__', '__sizeof__', '__str__', '__subclasshook__', '__weakref__', 'func1', 'func2']

## 인스턴스화된 f를 통해서 func1을 호출해보자.
# func1에는 매개변수가 없는데, 1개가 넘어갔다는 걸 알 수 있다.
> f.func1
TypeError: func1() takes 0 positional arguments but 1 was given

## 그러면 인스턴스화된 f를 통해서 func2를 호출해보자. (2-1 방법)
> f.func2
2799723753424
Func2 called

## 그리고, f의 id 값을 호출해보자.
> print(id(f))
2799723753424

## id(f.func2) 와 id(f) 가 같다는 걸 알 수 있다.
## 즉, self가 있는 method는 instance의 method인 걸 알 수 있다.
## f는 인스턴스화한 변수이기 때문에 func1로 넘어가는 것이 아닌, self가 있는 func2로 넘어간다.

## class method 호출하기 (1-1 방법)
> SelfTest.func1()
Func1 called


## 만약 역으로 클래스로 접근하여 func2를 호출한다면??
# func2가 요구하는 매개변수 1개를 놓쳐다는 error를 확인했다.
> SelfTest.func2()
Typeerror: func2() missing 1 required positional argument: 'self'
# 그러면 매개변수 1개를 입력해보자. (2-2 방법)
> SelfTest.func2(f)
2332370018256
Func2 called
```

---

<br>

## 4. class, instance variable

- `class variable`은 `instance`의 공통된 변수라서, `instance`의 `namespace`에 있을 것 같지만 없다.
- `class variable`은 `class`의 `namespace`에 있다.
- 하지만, 인스턴스화된 변수를 통해서 `class variable`에 접근한다면 `namespace`에서 찾을 수 없어도 `class variable`만 출력할 수 있다.
- `instance attribute`를 만들어서 사용할 것이기 때문에 `__init__` 생성자를 사용한다.
- 그리고 `생성자`와는 반대로 `소멸자: 객체가 소멸될 때 즉, 메모리에서 지워질 때 자동으로 호출되는 함수`를 사용했다.

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

> print(Warehouse.stock_num)
2

> print(user1.name)
Lee

> print(user2.name)
Cho

## instance의 namespace에서는 공통적으로 가지고 있는 클래스 변수를 찾을 수 없다.
> print(user1.__dict__)
{'name': 'Lee'}
> print(user2.__dict__)
{'name': 'Cho'}

## class의 namespace에서 찾을 수 있다.
> print(Warehouse.__dict__)
{'__module__': '__main__', 'stock_num': 2, '__init__': <function Warehouse.__init__ at 0x000002DC6B59F8B0>, '__del__': <function Warehouse.__del__ at 0x000002DC6B59F940>, '__dict__': <attribute '__dict__' of 'Warehouse'
objects>, '__weakref__': <attribute '__weakref__' of 'Warehouse' objects>, '__doc__': None}

## 그런데 <instance name>.<class variable name> 을 입력하면 `class variable`의 값이 출력된다.
## 이는 class의 namespace에서 python이 찾아서 출력한 것이다.
> print(user1.stock_num)
2

## 소멸자를 사용하여 instance를 삭제하면, stock_num이 1로 감소한 걸 확인할 수 있다.
> del user1
> print('after', Warehouse.__dict__)
{'__module__': '__main__', 'stock_num': 1, '__init__': <function Warehouse.__init__ at 0x000002DC6B59F8B0>, '__del__': <function Warehouse.__del__ at 0x000002DC6B59F940>, '__dict__': <attribute '__dict__' of 'Warehouse'
objects>, '__weakref__': <attribute '__weakref__' of 'Warehouse' objects>, '__doc__': None}

```

- 파이썬은 object의 이름을 다음 순서로 찾는다.
  - `instance`의 namespace -> `class`의 namespace -> `super`의 namespace (해당 class의 상위 class를 의미한다.)

<br>

- 마지막 예제

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
