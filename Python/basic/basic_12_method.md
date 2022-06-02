# Python basic 12: method , packing & unpacking, lambda

<br>

# 0. Introduction

> 1. [함수 중요성](#1-함수-중요성)
> 2. [함수 선언 및 사용](#2-함수-선언-및-사용)
> 3. [Packing, Unpakcing](#3-packing-unpacking)
> 4. [중첩 함수(Nested function)](#4-중첩-함수-nested-function)
> 5. [람다(Lambda) 함수(익명함수)](#5-람다lambda-함수-익명함수)
> 6. [함수 Type Hint](#6-함수-type-hint)

<br>

# 1. 함수 중요성

- 첫 번째 , `코드의 흐름`을 원활히 할 수 있다.
  - 요즘은 코드의 복잡도가 커지면서 코드 양이 매우 많아졌다. 그래서 이 코드를 일괄작성하기가 힘들다. 이에 대한 대책으로 단계별로 생각하여 각 단계마다 함수를 사용하여 개발을 원활하게 풀어갈 수 있다.
- 두 번째, 함수로 사용하면 `코드의 재사용성`이 향상된다.
  - 하나의 기능을 각 소스마다 중복하여 집어 넣으면, 그 기능을 수정해야할 경우, 다 수정해야하는 번거로움이 있다. 이런 것들이 비효율적이기 때문에, 함수로 만들면 한 번의 수정으로 다 수정할 수 있다.
- 세 번째, `코드의 안정성`이 좋아진다.
  - 그 이유는 개발자가 자신이 담당하는 함수에만 집중할 수 있기 때문에, 함수 이외의 부분과 나눠서 생각할 수 있다.

---

<br>

# 2. 함수 선언 및 사용

## 2.1 return의 유무

- 함수에서 `return`을 사용하지 않으면

  - `print`로 출력할 수 없다.
  - `unpacking`을 사용할 수 없다.

- 여러 값을 `return` 으로 반환하는 것을 `다중 반환`이라 한다.

```yml
## return 명령어가 없을 경우
> def func_mul(x):
>    y1 = x * 10

> x1 = func_mul(10)
> print(x1, type(x1))
None <class 'NoneType'>
# 출력할 수 없다.

## return 명령어가 있을 경우
> def func_mul(x):
>    y1 = x * 10
>    return y1

> x1 = func_mul(10)
> print(x1)
100


## No return and unpacking
> def func_mul1(x):
>    y1 = x * 10
>    y2 = x * 20
>    y3 = x * 30

# unpacking
> x1, x2, x3 = func_mul1(10)

> print(x1, x2, x3)
TypeError: cannot unpack non-iterable NoneType object

# 다중 반환 확인하기
> def func_mul1(x):
>    y1 = x * 10
>    y2 = x * 20
>    y3 = x * 30
>    return y1, y2, y3
# unpacking
> x1, x2, x3 = func_mul1(10)
> print(x1, x2, x3)
100 200 300
```

<br>

## 2.2 원하는 data type으로 return하기

- 원하는 `data type`으로 함수값을 출력하려면 어떻게 해야하는지 알아보자.
- return 할 data의 type이 출력할 data type이 된다.

```yml
## tuple return
> def func_mul1(x):
>    y1 = x * 10
>    y2 = x * 20
>    y3 = x * 30
>    return (y1, y2, y3)  # tuple 형식

> t1 = func_mul1(10)
> print(t1, type(t1))
(100, 200, 300)  <class 'tuple'>

## list return
> def func_mul2(x):
>    y1 = x * 10
>    y2 = x * 20
>    y3 = x * 30
>    return [y1, y2, y3]  # list 형식

> t1 = func_mul2(10)
> print(t1, type(t1))
[100, 200, 300] <class 'list'>

## dictionary return
> def func_mul3(x):
>    y1 = x * 10
>    y2 = x * 20
>    y3 = x * 30
>    return {'v1' : y1, 'v2' : y2, 'v3' : y3} # keyword 형식

> t1 = func_mul3(10)
> print(t1, type(t1))
> print(t1.values())
{'v1': 100, 'v2': 200, 'v3': 300} <class 'dict'>
dict_values([100, 200, 300])

> d = func_mul3(30)
> print(type(d), d, d.get('v2'), d.items(), d.keys())
<class 'dict'> {'v1': 300, 'v2': 600, 'v3': 900} 600 dict_items([('v1', 300), ('v2', 600), ('v3', 900)]) dict_keys(['v1', 'v2', 'v3'])
```

<br>

---

# 3. Packing, Unpacking

<br>

## 3.1 Positional argument, Keyword argument

- 함수 인자에는 `Positional argument(위치인자)`와 `Keyword argument(키워드 인자)`가 있다.
  - `인자`란 함수 기능에 필요한 값을 말한다.
  - `기본값`이란 미리 기본으로 지정된 값을 말한다.
  - `Positional argument`는 인자값이 **_위치_** 에 의해 결정되는 인자다.
    - **_순서_** 가 중요하다.
  - `Keyword argument`는 key value가 **_key_** 에 의해 결정되는 인자다.
    - **_순서 상관 없이 `keyword`_** 가 중요하다.

<br>

```yml
## Positional argument(위치인자)
# real number (실수)는 앞에, imaginary number(허수)는 뒤에 위치해야 된다.
# 위치 즉, 순서가 중요하다.
> complex(3, 5)
(3 + 5j)

## Keyword argument(키워드 인자)
# key = value
> complex (real = 3, imag = 5)
(3 + 5j)
```

<br>

## 3.2 Packing의 두 종류

> print함수처럼 **_함수가 받을 인자의 갯수를 유연하게 지정_** 하기 위해 사용하는 것

- `print` 함수는 객체의 갯수에 제한 없이 출력한다.

```yml
# 1개
> print('123 456 789')
123 456 789

# 3개
> print('123, '456, '789')
123 456 789
```

- `packing` 은
  - `arguments`를 `하나의 객체`로 합쳐서 받을 수 있도록 한다.
  - `positional argument` packing 과 `keyword argument` packing이 있다.
  - 다음 table과 같은 특징을 가진다.
    - args: arguments / kwargs: keyword arguments
    - args와 kwargs는 매개변수 명으로, 자유롭게 명명한다.

| packing    | positional argument packing | keyword argument packing |
| ---------- | --------------------------- | ------------------------ |
| expression | \*args                      | \*\*kwargs               |
| data type  | tuple                       | dictionary               |

<br>

### 3.2.1 Positional arguments packing

- `positional argument`에 대해 앞서서 `enumerate ()` 에 대해 알아보겠다.
- enumerate는 index 와 value 를 `tuple` 형식으로 하나의 성분으로서 맺어주고, return 해주는 함수다.

```yml
## enumerate()
> seasons = ['Spring', 'Summer', 'Fall', 'Winter']

# enumerate()를 하면 바로 id 값만 출력된다.
> print(enumerate(seasons))
<enumerate object at 0x000002957E6DE640>

> print(list(enumerate(seasons)))
[(0, 'Spring'), (1, 'Summber'), (2, 'Fall'), (3, 'Winter')]

> print(tuple(enumerate(seasons)))
((0, 'Spring'), (1, 'Summber'), (2, 'Fall'), (3, 'Winter'))

```

- `enumerate ()` 를 사용하여 `positional arguments` packing을 설명하겠다.
- `enumerate ()`를 `for ~ in`문에 사용하겠다.

```yml
# args == arguments
> def args_func(*args):
>   for i, v in enumerate(args):
>     print('Result : {}'.format(i), v)

# 인자의 수가 다양해도 다 받아지는 걸 알 수 있다.
> args_func('Lee')
Result : 0 Lee

# 위치인자로 보낸 모든 객체들('Lee', 'Park')을 *args로 하나의 객체로서 관리해준다.
> args_func('Lee', 'Park')
Result : 0 Lee
Result : 1 Park


> args_func('Lee', 'Park', 'Kim')
Result : 0 Lee
Result : 1 Park
Result : 2 Kim
```

<br>

### 3.2.2 Keyword arguments packing

- `keyword argument` packing을 사용하는 방법

```yml
> def kwargs_func(**kwargs):
>     for v in kwargs.keys():
>       print("{}".format(v), kwargs[v])

# keyword arguments를 packing 하여 dictionary로 관리한다.
> kwargs_func(name1='Apple')
name1 Apple

> kwargs_func(name1='Apple', name2='Window')
name1 Apple
name2 Window

> kwargs_func(name1='Apple', name2='Window', name3='Choice')
name1 Apple
name2 Window
name3 Choice
```

- `positional argument` 와 `keyword argument`를 같이 사용해보자.

```yml
> def example(args_1, args_2, *args, **kwargs):
>    print(args_1, args_2, args, kwargs)
> example(10, 20, 'Lee', 'Kim', 'Park', 'Cho', age1=20, age2=30, age3=40)
10 20 ('Lee', 'Kim', 'Park', 'Cho') {'age1': 20, 'age2': 30, 'age3': 40}
```

- args_1, args_2 로 총 2개이므로, print의 매개변수 앞에서 2개까지가 일반적인 positional argument이다.

- 그 뒤에, \*args 는 positional argument packing이므로 제한 없다. tuple 로 출력된 걸 확인할 수 있다.

- 맨 마지막 인자는 \*\* 이므로, keyword argument packing이다. dictionary로 출력된 걸 확인할 수 있다.

<br>

## 3.3 Unpacking

> 주의사항: **_Unpacking 시 해체되는 `인자의 수`와 매칭되는 `변수의 수`가 동일해야 가능하다._**

- `Unpacking`은 `packing`과는 반대로 여러개의 객체를 포함하고 있는 하나의 객체를 푼다.
- `packing` 시에는 function (or method) 정의 시, `parameter`에 `*`을 붙였지만,
- `unpacking` 시에는 function (or method) 사용 시, `argument` 앞에 `*`를 붙여서 사용한다.

```yml
> def sum(a, b, c):
>  return a + b + c
> number = (1, 2, 3)
> print(sum(*number))
6
```

- 또는 다음과 같은 방식으로 `unpacking` 할 수 있다.

```yml
> def func_mul1(x):
>    y1 = x * 10
>    y2 = x * 20
>    y3 = x * 30
>    return y1, y2, y3
# unpacking
> x1, x2, x3 = func_mul1(10)
```

---

<br>

# 4. 중첩 함수 (Nested function)

> 함수 내부에 정의된 또 다른 함수

- **중첩 함수는 `함수형 프로그래밍`에서 많이 사용된다.**

- **부모 함수(외부 함수)와 하위 함수(내부 함수)**

  - 호출하는 함수는 부모 함수다. 하위 함수는 호출할 수 없다.
    - 그 이유는 [[TIL] Python basic 32: LEGB rules and Memory structures](https://jeha00.github.io/post/python_basic/python_basic_32_legb_rules/) 을 참고하자.
  - 하위 함수는 부모 함수의 매개변수를 받아서 사용한다.

```yml
# 중첩 함수
> def nested_func(num):  # 부모 함수
>    def func_in_func(num): # 부모 함수의 매개변수를 받아서 사용 가능
>      print(num)
>    print("In func")
>    func_in_func(num + 100)

> nested_func(100)
200

# 부모 함수의 하위 함수를 호출하여 사용할 수 없다.
> func_in_func(100)
NameError: name 'func_in_func' is not defined
```

<br>

---

# 5. 람다(lambda) 함수 (익명함수)

- **람다식의 장점 from python 공식 사이트**

  - 메모리 절약
  - 가독성 향상
  - 코드 간결

- **람다식의 단점 (많은 실력자 분들이 람다식을 부정적으로 피력한다.)**

  - 과한 사용 시, 가독성 감소된다. 왜냐하면 `익명 함수`이기 때문이다.
    - 일반적인 함수는 함수명을 보고 그 기능을 추측할 수 있으나, 익명 함수라 추측할 수 없다.

- **일반적인 함수와 람다식 함수의 차이**
  - `일반적인 함수`는 **함수명** 이 있기 때문에, **객체 생성** 된다. 그 후, **resource(memory)를 할당** 한다.
  - 하지만, `람다식 함수`는
    - **_즉시 실행 함수_** 라서 일회성이고,
    - `Heap` 영역에 저장되고 (Heap 초기화),
    - 파이썬의 garbage collection에 의해 메모리 초기화가 되기 때문에, 메모리를 효율적으로 사용할 수 있다.
    - 함수명이 존재하지 않아, `익명 함수`라 한다. 그래서 별도의 변수에 할당해야 한다.

```yml
## 첫 번째: 이미 변수에 할당해 놓은 일반적인 함수를 넣는 방법
> def mul_func(x, y):
>   return x * y

> print(mul_func(10, 50))
500

> mul_func_var = mul_func
> print(mul_func_var(10, 50))
500

## 두 번째: 자주 쓰는 람다 함수이기 때문에, 정의를 해서 변수로 넘기는 방식
# 일시적으로 그 자리에서 함수가 필요할 때 사용한다.
# def 와 return이 없어도 가능하다.
# 람다식을 넣은 함수
> lambda_mul_func = lambda x,y : x * y
> print(lambda_mul_func(10, 50))
500

# 함수 안에서 함수를 인자로 받는 함수
> def func_final(x, y, func):
>     print(x * y * func(1,1))

## 첫 번째 방식
> func_final(10, 50, mul_func_var)
500

## 두 번째 방식
> func_final(10, 50, lambda_mul_func)
500

## 세 번째 방식: 바로 그 자리에서 람다식을 써서 넘기는 방법
> func_final(10, 50, lambda x,y : x * y)

```

- 위 방식대로 총 함수를 정의하는데 `3가지 방식`이 있다.
- 각 방식에 대해서 언제 무엇을 써야할지 생각해보자.

---

<br>

# 6. 함수 Type Hint: Annotation

> 함수의 매개변수와 함수의 결과값의 각 데이터 타입을 알려주기 위해 `python 3.5` 부터 나온 기능

- `def <function-name>(parameter1: <data type>) -> <함수 결과값의 data type>`

```yml
# 아래 예시처럼 각 매개변수의 데이터 타입이 무엇인지 알려준다.
# 그리고, 함수의 결과값의 데이터 타입도 알려준다.
> def tot_length1(word: str, num: int) -> int:
>    return len(word) * num

# 아래 함수는 위 함수와 동일하다.
> def tot_length1(word, num):
>    return len(word) * num

> print('hint exam1 : ', tot_length1("i love you", 10))
> print(type(tot_length1("i love you", 10)))
hint exam1 : 100
<class 'int'>

> def tot_length2(word: str, num: int) -> None:
>    print('hint exam2 : ', len(word) * num)


> print(tot_length2("niceman", 10))
> print(type(tot_length2("i love you", 10)))
hint exam2 : 70
<class 'Nonetype'>
```

> **_'tot_length2'의 data type이 'Nonetype'인 이유는 return 값이 없기 때문이다._**

---

# 7. Method와 function 과의 차이

> **_Method: 객체에 속한 function_**

- 해당 내용은 다음 문서를 참고했다.

  - [Difference between Method and Function in Python](https://www.tutorialspoint.com/difference-between-method-and-function-in-python)

- 기본적인 function의 expression은 다음과 같다.

```yml
> def functionName(arg1, arg2, ...):
>  """
>  # Function _body
>  """

> def sum(num1, num2):
>   return num1 + num2

> sum(5,6)
11
```

- Method의 expression은 다음과 같다.

```yml
> class ClassName:
>    def method_name():
>       …………..
>       # Method_body
>       ………………

> class Pet(object):
>   def my_method(self):
>     print("I am a Cat")

> cat = Pet()
> cat.my_method()
I am a Cat
```

- 그러면 위 두 가지 코드를 통해서 function과 method의 차이는 무엇일까??

  - function과 달리 method는 object와 관련하여 호출된다. 위 코드를 보자면 "cat" 객체에 관련된 "my_methd" 를 호출했다. 하지만, function "sum" 은 객체 없이 호출된다.
  - 또한, method는 객체에 관련하여 호출되기 때문에, 객체 안에 있는 data에 접근할 수 있다. 그래서 객체의 상태를 바꿀 수 있지만, function은 할 수 없다.

- 즉, method는 객체에 속한 function임을 알 수 있다.

- 이를 보다 깊이 들어가자면 [Docs.python - Functions and methods](https://docs.python.org/3/howto/descriptor.html#functions-and-methods) 이 공식 문서를 참고해보자.

  - 클래스의 namespace에 저장된 functions들은 호출될 때, method로 바뀐다고 한다. 단지 method는 "객체 인스턴스"가 다른 인자들보다 앞에 오는 점에서 일반 function들과 다르다고 한다.

---

<br>

## Reference

- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [Positional argument, Keyword argument](https://wikidocs.net/22799)
- [Packing, Unpacking](https://wikidocs.net/22801)
- [enumerate](https://docs.python.org/ko/3.6/library/functions.html?highlight=enumerate#enumerate)
- [lambda function](https://wikidocs.net/22804)
- [Docs.python - Functions and methods](https://docs.python.org/3/howto/descriptor.html#functions-and-methods)
