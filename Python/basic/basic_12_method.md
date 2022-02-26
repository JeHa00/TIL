# Python basic 12: method , packing&unpacking, lambda

<br>

## Intro

> 1. [함수 중요성]()
> 2. [함수 선언 및 사용]()
> 3. [Packing, Unpakcing]()
> 4. [중첩 함수]()
> 5. [함수 Hint]()
> 6. [기타 사용법]()
> 7. [람다(Lambda) 개념]()

<br>

## 1. 함수 중요성

- 첫 번째 , `코드의 흐름`을 원활히 할 수 있다.
  - 요즘은 코드의 복잡도가 커지면서 코드 양이 매우 많아졌다. 그래서 이 코드를 일괄작성하기가 힘들다. 이에 대한 대책으로 단계별로 생각하여 각 단계마다 함수를 사용하여 개발을 원활하게 풀어갈 수 있다.
- 두 번째, 함수로 사용하면 `코드의 재사용성`이 향상된다.
  - 하나의 기능을 각 소스마다 중복하여 집어 넣으면, 그 기능을 수정해야할 경우, 다 수정해야하는 번거로움이 있다. 이런 것들이 비효율적이기 때문에, 함수로 만들면 한 번의 수정으로 다 수정할 수 있다.
- 세 번째, `코드의 안정성`이 좋아진다.
  - 그 이유는 개발자가 자신이 담당하는 함수에만 집중할 수 있기 때문에, 함수 이외의 부분과 나눠서 생각할 수 있다.

<br>

- 함수 정의 구조

> def function_name(parameter):
> \t code

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
>    return (y1, y2, y3)

> t1 = func_mul1(10)
> print(t1, type(t1))
(100, 200, 300)  <class 'tuple'>

## list return
> def func_mul2(x):
>    y1 = x * 10
>    y2 = x * 20
>    y3 = x * 30
>    return [y1, y2, y3]

> t1 = func_mul2(10)
> print(t1, type(t1))
[100, 200, 300] <class 'list'>

## dictionary return
> def func_mul3(x):
>    y1 = x * 10
>    y2 = x * 20
>    y3 = x * 30
>    return {'v1' : y1, 'v2' : y2, 'v3' : y3}

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
  - `Positional argument`는 인자값이 `위치`에 의해 결정되는 방법이다.
    - `순서`가 중요하다.
  - `Keyword argument`는 `매개변수 이름`에 의해 결정되는 방법이다.
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
  - `positional argument` 와 `keyword argument`를 사용하는 방식이 있다.
  - `positional argument`는 `*` 한개를 매개변수 앞에 붙여서 사용한다. 이 때는 `tuple` type 으로 `하나의 객체`가 됩니다.
  - `keyword argument`는 `*` 두 개 즉, `**`를 매개변수 앞에 붙여서 사용한다.
  - `keyword argument`는 패킹한 인자들을 keyword와 value로 넘길 때 사용합니다. 즉, `dictionary` type으로 관리합니다.

<br>

- `positional argument`를 사용하는 방법
- parameter를 입력할 때, 입력되는 수만큼 (x, y, z, a, b)로 입력할 수 있다.
- 하지만, `*args`를 사용해서 packing 한 후, unpacking 하여 사용할 수 있다.
- `args`는 매개변수 명으로 자유롭게 명명한다.

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

- `enumerate ()` 를 사용하여 `positional arguments`를 설명하겠다.

```yml
# args == arguments
> def args_func(*args):
>   for i, v in enumerate(args):
>     print('Result : {}'.format(i), v)

# 인자의 수가 다양해도 다 받아지는 걸 알 수 있다.
> args_func('Lee')
Result : 0 Lee

> args_func('Lee', 'Park')
Result : 0 Lee
Result : 1 Park

> args_func('Lee', 'Park', 'Kim')
Result : 0 Lee
Result : 1 Park
Result : 2 Kim
```

- `dictionary unpakcing`을 사용하는 방법

```yml
> def kwargs_func(**kwargs):
>     for v in kwargs.keys():
>       print("{}".format(v), kwargs[v])

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

---

<br>

## 4. 중첩 함수

- `중첩 함수`는 `함수형 프로그래밍`과 관련 있다.
- `함수형 프로그래밍`에서 중첩함수로 많이 사용된다.

---

## Reference

- [Positional argument, Keyword argument](https://wikidocs.net/22799)
- [Packing, Unpacking](https://wikidocs.net/22801)
