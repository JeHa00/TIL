# Python basic 12: method , packing & unpacking, lambda

<br>

## Intro

> 1. [함수 중요성](https://github.com/JeHa00/TIL/blob/master/Python/basic/basic_12_method.md#1-%ED%95%A8%EC%88%98-%EC%A4%91%EC%9A%94%EC%84%B1)
> 2. [함수 선언 및 사용](https://github.com/JeHa00/TIL/blob/master/Python/basic/basic_12_method.md#2-%ED%95%A8%EC%88%98-%EC%84%A0%EC%96%B8-%EB%B0%8F-%EC%82%AC%EC%9A%A9)
> 3. [Packing, Unpakcing](https://github.com/JeHa00/TIL/blob/master/Python/basic/basic_12_method.md#3-packing-unpacking)
> 4. [중첩 함수(Nested function)](https://github.com/JeHa00/TIL/blob/master/Python/basic/basic_12_method.md#4-%EC%A4%91%EC%B2%A9-%ED%95%A8%EC%88%98)
> 5. [람다(Lambda) 함수(익명함수)]()
> 6. [함수 Type Hint]()

<br>

## 1. 함수 중요성

- 첫 번째 , `코드의 흐름`을 원활히 할 수 있다.
  - 요즘은 코드의 복잡도가 커지면서 코드 양이 매우 많아졌다. 그래서 이 코드를 일괄작성하기가 힘들다. 이에 대한 대책으로 단계별로 생각하여 각 단계마다 함수를 사용하여 개발을 원활하게 풀어갈 수 있다.
- 두 번째, 함수로 사용하면 `코드의 재사용성`이 향상된다.
  - 하나의 기능을 각 소스마다 중복하여 집어 넣으면, 그 기능을 수정해야할 경우, 다 수정해야하는 번거로움이 있다. 이런 것들이 비효율적이기 때문에, 함수로 만들면 한 번의 수정으로 다 수정할 수 있다.
- 세 번째, `코드의 안정성`이 좋아진다.
  - 그 이유는 개발자가 자신이 담당하는 함수에만 집중할 수 있기 때문에, 함수 이외의 부분과 나눠서 생각할 수 있다.

---

<br>

## 2. 함수 선언 및 사용

- 함수에서 `return` 사용하지 않으면

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

---

<br>

## 3. Packing, Unpacking

<br>

### 3.1 Positional argument, Keyword argument

- 함수 인자에는 `Positional argument(위치인자)`와 `Keyword argument(키워드 인자)`가 있다.
  - 인자란 `함수 기능에 필요한 값`을 말한다.
  - `기본값`이 있다.
    - `기본값`이란 미리 기본으로 지정된 값을 말한다.
  - `Positional argument`는 인자값이 `위치`에 의해 결정되는 인자다.
    - `순서`가 중요하다.
  - `Keyword argument`는 key value가 `key`에 의해 결정되는 인자다.
    - `순서 상관 없이` `keyword`가 중요하다.

<br>

```yml
# Positional argument(위치인자)
# real number (실수)는 앞에, imaginary number(허수)는 뒤에 위치해야 된다.
# 위치 즉, 순서가 중요하다.
> complex(3, 5)
(3 + 5j)

# Keyword argument(키워드 인자)
# key = value
> complex (real = 3, imag = 5)
(3 + 5j)
```

<br>

### 3.2 Packing

- `print` 함수는 객체의 갯수에 제한 없이 출력한다.

```yml
# 1개
> print('123 456 789')
123 456 789

# 3개
> print('123, '456, '789')
123 456 789
```

- `print` 함수처럼 함수가 받을 인자(argument)의 갯수를 유연하게 지정하기 위해 Python은 `packing`을 지원한다.
- `packing` 은
  - `arguments`를 `하나의 객체`로 합쳐서 받을 수 있도록 한다.
  - `positional argument` packing 과 `keyword argument` packing이 있다.
- `positional argument` packing은
  - `*` 한 개를 매개변수 앞에 붙여서 사용한다.
  - 이 때는 `tuple` type 으로 `하나의 객체`가 된다.
- `keyword argument` packing은
  - `*` 두 개 즉, `**`를 매개변수 앞에 붙여서 사용한다.
  - `keyword`와 `value`로 구성된 `dictionary` type으로 `하나의 객체`가 된다.

<br>

- `positional argument` packing을 사용하는 방법
- parameter를 입력할 때, 입력되는 수만큼 (x, y, z, a, b)로 입력할 수 있다.
- 하지만, `*args`를 사용하여 하나의 객체로서 packing 하여 간단히 관리할 수 있다.
- `args`는 매개변수 명으로, 자유롭게 명명한다.

<br>

- `positional argument`에 대해 알아보기에 앞서, `enumerate ()` method에 대해 알아보겠다.

```yml
# enumerate()
# enumerate는 index 와 value 를 `tuple` 형식으로 하나의 성분으로서 맺어주고, return 해주는 함수다.
> seasons = ['Spring', 'Summber', 'Fall', 'Winter']

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

# args_1, args_2 로 총 2개이므로, print의 매개변수 앞에서 2개까지가 일반적인 positional argument이다.

# 그 뒤에, *args 는 positional argument packing이므로 제한 없다. tuple 로 출력된 걸 확인할 수 있다.

# 맨 마지막 인자는 ** 이므로, keyword argument packing이다. dictionary로 출력된 걸 확인할 수 있다.

```

### 3.3 Unpacking

- `Unpacking`은 `packing`과는 반대로 여러개의 객체를 포함하고 있는 하나의 객체를 풀어준다.
- **`Unpacking` 시 해체되는 `인자의 수`와 매칭되는 `변수의 수`가 동일해야 가능하다.**
- `packing` 시에는 `매개변수`에 `*`을 붙였지만, `unpacking` 시에는 `argument` 앞에 `*`를 붙여서 사용합니다.

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

## 4. 중첩 함수 (Nested function)

- `중첩 함수`는 `함수형 프로그래밍`과 관련 있다.
- `함수형 프로그래밍`에서 중첩함수로 많이 사용된다.
- 함수 내부에 정의된 또 다른 함수를 말한다.
- 호출하는 함수는 `부모 함수` 이다.
- `부모 함수`의 하위 함수를 호출할 수 없다.
  - `부모 함수`의 매개변수를 받아서 사용한다.

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

---

<br>

## 5. 람다(lambda) 함수 (익명함수)

- 람다식의 장점 from python 공식 사이트

  - 메모리 절약
  - 가독성 향상
  - 코드 간결

- 람다식의 단점 (많은 실력자 분들이 람다식을 부정적으로 피력한다.)

  - 과한 사용 시, 가독성 감소된다. 왜냐하면 `익명 함수`이기 때문이다.
  - (빈번히 언급됨)
  - (일반적인 함수는 함수명을 보고 그 기능을 추측할 수 있다.)

- 일반적인 함수와 람다식 함수의 차이
- `일반적인 함수`는 `함수명`이 있기 때문에, `객체 생성` 된다. 그 후, `resource(memory)를 할당`한다.
- 하지만, `람다식 함수`는
  - `즉시 실행 함수` 라서,
  - `Heap` 영역에 저장되고 (Heap 초기화),
  - 메모리 초기화를 한다.
  - 초기화로 메모리를 효율적으로 사용할 수 있다.
  - 함수명이 존재하지 않아, `익명 함수`라 한다. 그래서 별도의 변수에 할당해야 한다.

```yml
> def mul_func(x, y):
>   return x * y

> print(mul_func(10, 50))
500

# 첫 번째: 이미 변수에 할당해 놓은 일반적인 함수를 넣는 방법
> mul_func_var = mul_func
> print(mul_func_var(10, 50))
500

# 두 번째: 자주 쓰는 람다 함수이기 때문에, 정의를 해서 변수로 넘기는 방식
# 일시적으로 그 자리에서 함수가 필요할 때 사용한다.
# def 와 return이 없어도 가능하다.
# 람다식을 넣은 함수
> lambda_mul_func = lambda x,y : x * y
> print(lambda_mul_func(10, 50))
500

# 함수 안에서 함수를 인자로 받는 함수
> def func_final(x, y, func):
>     print(x * y * func(1,1))

# 첫 번째 방식
> func_final(10, 50, mul_func_var)
500

# 두 번째 방식
> func_final(10, 50, lambda_mul_func)
500

# 세 번째 방식: 바로 그 자리에서 람다식을 써서 넘기는 방법
> func_final(10, 50, lambda x,y : x * y)

```

- 위 방식대로 총 함수를 정의하는데 `3가지 방식`이 있다.
- 각 방식에 대해서 언제 무엇을 써야할지 생각해보자.

## 6. 함수 Type Hint

- 함수의 `매개변수`와 함수의 `결과값`이 `무슨 데이터 타입인지` 알려주기 위해 `python 3.5` 부터 나온 기능이다.
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

- `tot_length2`의 data type이 `Nonetype`인 이유는 `return 값`이 없기 때문이다.

---

<br>

## Reference

- [Positional argument, Keyword argument](https://wikidocs.net/22799)
- [Packing, Unpacking](https://wikidocs.net/22801)
- [enumerate](https://docs.python.org/ko/3.6/library/functions.html?highlight=enumerate#enumerate)
- [lambda function](https://wikidocs.net/22804)
