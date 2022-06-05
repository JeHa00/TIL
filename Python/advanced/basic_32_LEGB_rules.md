# 0. Introduction

> 1. [LEGB rules(Scoping rules)](#1legb-rulesscoping-rules)
> 2. [Python memory structure](#2-python-memory-structure)

<br>

- `coroutin`을 공부하면서 global variable과 local variable 라는 것이 Scoping Rule과 연관이 되어있다는 것과, `Stack` 과 `Heap` 이라는 데이터 임시 저장 자료 구조와 연관된 걸 확인하고 이에 대해 정리해보겠다.

<br>

---

# 1.LEGB rules(Scoping rules)

> **_변수(variable)의 생존 범위(lifetime)에 관련된 규칙으로, 이 변수가 적용되는 범위를 말하는 것으로 이해했다._**

- Python scope 개념을 알아야 하는 이유:

  - 신뢰성 있고, 유지보수성이 좋은 프로그램을 작성할 수 있다.
  - name 충돌을 방지할 수 있고, 버그를 줄일 수 있다.
  - 이와 관련된 tool인 `Closure`에 대해 알 수 있다.

<br>

## 1.1 초기 Scope의 부재로 인한 문제

- 초기 프로그래밍은 **global**만 있었기 때문에, 변수를 수정해야할 때 모든 코드를 동시에 염두에 둬야했다. 그래서 이런 문제를 피하기 위해 **scope**을 사용했다.
- scope을 사용한 후, 프로그램의 어디에서든지 해당되는 scope에서 벗어나 있는 변수들에 함부로 접근할 수 없다. (_out of scope_)
- name들의 scope은 이 name들을 정의한 코드의 block scope과 동일하다. (_in scope_)

<br>

## 1.2 파이썬의 이름(name) 만들기

- 파이썬에서의 변수들에 값이 할당될 때, 즉 파이썬 names을 다음과 같은 방법들로 만들 때 변수들은 존재하게 된다.
  - 변수(variable): 변수에 값을 할당하면, 변수는 만들어진다.
  - function , classes: 예약어 `def`, `classes`를 사용하여 정의하면 이용할 수 있다.
  - modules: `import`하여 사용할 수 있고, as를 통해서 별칭으로 정의할 수 있다.

<br>

## 1.3 Reference operations과 Assignment operations의 차이

| operations    | Reference operations                        | Assignment operations                             |
| ------------- | ------------------------------------------- | ------------------------------------------------- |
| 참조 / 할당   | name을 **_'참조'_** 한다                    | name을 **_'할당'_** 한다                          |
| 구체적인 의미 | name에 담겨진 value를 **_'단지 가져온다'_** | name을 **_'새롭게'_** 만들거나, **_'수정'_** 한다 |

- 그리고, 할당한다는 건 특정 scope이 결정된다는 걸 말한다.

<br>

## 1.4 Python scope와 namespace의 관계

> **_Namespace_ 설명은 [[TIL] Python basic 14: class](https://jeha00.github.io/post/python_basic/python_basic_14_class/#23-namespace-%ED%99%95%EC%9D%B8%ED%95%98%EA%B8%B0)을 참고한다.**

- namespace는 각각 다른 지점에서 만들어지기 때문에, 다른 `수명 시간(life time)`을 가지고 있다. 어느 위치에서 사용할 수 있는지가 결정되어 있다. (from [Python-course.eu: Namespaces](https://python-course.eu/python-tutorial/namespaces.php))

- **`__dir__`을 통해서 할당된 name이 가지는 scope을 보여준다.**

  - `.__dict__.keys()` 로 key value로 indexing하여 확인할 수 있다.

<br>

## 1.5 Python이 name을 찾는 규칙: LEGB rules

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbOsMlk%2FbtqSScz12Dt%2F76hosfnaej6J0798NwJKV0%2Fimg.png)

| Scope                | Local                                         | Enclosed(or Non local)                               | Global(or Module)                    | Built-in                              |
| -------------------- | --------------------------------------------- | ---------------------------------------------------- | ------------------------------------ | ------------------------------------- |
| 해당 이름            | function의 body (= code block)에 정의한 이름  | 지역 범위에 있는 중첩 함수를 둘러싼 범위에 있는 name | 최고 수준으로(Top level) 정의한 이름 | python 안에 내장된 예약어들           |
| 확인 범위            | name이 정의된 function의 코드에서만 확인 가능 | name이 정의된 function의 코드에서만 확인 가능        | 어느 코드에서든지 확인 가능          | 어느 코드에서든지 확인 가능           |
| 수명 시간(life time) | 정의한 function이 종료되면 소멸               | 중첩 함수가 있는 외부 function이 종료되면 소멸       | script가 끝날 때까지 지속            | 인터프리터가 시작되면 만들어져 소멸 X |

- [Top level이란??](https://jeha00.github.io/post/python_basic/python_basic_23_ifnamemain/#22-top-level-%EC%9D%B4%EB%9E%80)

![image](https://user-images.githubusercontent.com/78094972/160286468-2f395646-c83f-44ae-a5c9-b34f8c638d7d.PNG)

- From: [Python Scope & the LEGB rule: Resolving Names in Your code](https://realpython.com/python-scope-legb-rule/)

<br>

- **_Local(or function) scope: 지역 범휘_**

  - Python function의 body 또는 code block 부분이 local scope이다.
  - function이 호출될 때, 이 function에 대한 namespace가 생성된다.
  - 지역 함수 내에 로직을 해결하는 값을 사용한다.

- **_Enclosed(or nonlocal or free) scope: 자유 영역_**

  - 중첩함수(nested functions)를 위해서만 존재하는 scope

  - enclosing function 안에서 정의된 names만 포함한다.
  - 이 `enclosing function의 코드에서만` enclosing scope에 있는 name을 확인할 수 있다.

<br>

- **_Global(or module) scope: 전역 범위_**

  - Python program, script, module 안에서 Top level의 scope으로, 이 scope에는 주로 변하지 않는 고정값을 사용한다.

- **_Built-in scope: 내장 범위_**

  - script를 run할 때마다 만들어지는 특별한 scope

- python은 name의 존재유무를 확인하기 위해서, 여러 scope levels(or namespace)를 찾아보는데, 찾는 순서는 다음과 같다.
  - local -> global -> global or module -> built-in namespace

```yml
# This area is the global or module scope
> number = 100
> def outer_func():
>      # This block is the local scope of outer_func()
>      # It's also the enclosing scope of inner_func()
>     def inner_func():
>         # This block is the local scope of inner_func()
>         print(number)
>
>     inner_func()

> outer_func()
100
```

- Inside inner_func(): local scope 이지만, number 변수는 존재하지 않는다.
- Inside outer_func(): the enclosing scope 이다. number 변수가 정의되지 않았다.
- In the module scope(or global scope): number 변수를 찾을 수 있어서 출력할 수 있다.
- 만약 number 변수가 the global scope에서 정의되지 않는다면, 파이썬은 built-in scope에서 찾을 것이다.

<br>

# 2. LEGB rules를 code로 이해해보기

<br>

## 2.1 LEGB rules: The Local Scope

- 그러면 code를 보면 이해해보자.

  ```yml
  > def square(base):
  >   result = base ** 2
  >   print(f'The square of {base} is : {result}')

  > square(10)
  The square of 10 is : 100

  > result
  NameError: name 'result' is not defined

  > base
  NameError: name 'base' is not defined
  ```

  - 위 코드에 대해 알아보자.

    - square 함수를 호출 시, 파이썬은 base와 result를 포함하는 local scope을 만든다.
      - `square(10)`으로 호출할 때, base에는 10을, result에는 100을 취한다.
    - 다시 호출할 때는 첫 번째 호출 시 취한 값들은 기억하지 않는다. `result`와 `base`는 `square()` 호출에 의해 만들어진 `local scope`에만 존재한다. 그래서 square fuction 종료 후 접근한다면 `NameError`을 얻는다.

- 그러면 추가로 square function의 local scope에 정의한 변수 이름과 동일한 변수 이름을 가진 function 정의해보자.

  ```yml
  > def cube(base):
  >        result = base ** 3
  >        print(f'The cube of {base} is : {result}')

  > cube(30)
  The cube of 30 is : 27000
  ```

  - local scope에 동일한 변수이름을 사용했지만, 프로그램 충돌이 일어나지 않은 이유는 `local scope`에만 살아있는 `local variable(지역 변수)`이기 때문에, 함수 실행이 끝나면 local scope에서 벗어나 지역 변수의 수명은 끝난다.
  - 이러한 장점 때문에, 디버깅과 수정이 쉽고 가독성이 좋아진다.

<br>

## 2.2 LEGB rules: The Enclosing Scope (Nested Functions)

```yml
>  def outer_func():
>     # 이 block은 외부 함수(outer_func)의 local scope이면서
>     # 내부 함수(inner_func)의 enclosing scope이기도 하다.
>     var = 100
>     def inner_func():
>         print(f'Printing var from inner_func() : {var}')
>     inner_func()
>     print(f'Printing var from outer_func() : {var}')

> outer_func()
Printing var from inner_func(): 100
Printing var from outer_func(): 100

> inner_func()
NameError: name 'inner_func' is not defined
```

- `outer_func()`이 호출될 때, `outer_func()`의 local scope이 만들어진다.
- 이 scope은 동시에 `inner_func()`의 `enclosing scope`이기도 한다.
  - `global scope`과 `local scope` 둘 다 아니고, 이 사이에 놓여있는 특별한 scope을 의미.
- 또한, `inner_func()`은 enclosing function인 outer_func이 실행되는 동안에만 유지되는 일시적인 함수로서, `outer_func()`의 code에서만 `inner_func()`을 찾을 수 있다. `outer_func()`의 실행이 종료되면 `inner_func()`은 사라진다.

<br>

## 2.3 LEGB rules: Modules - The Global Scope

1. 프로그램을 실행한 순간부터 `global scope`에 있는 것이다.

2. 이 `global scope`은 `module scope`이라고도 한다.

3. 그리고 현재 실행되는 script 또는 module이 entry point 역할을 한다면 `__main__` module의 범위가 된다.

4. namespace를 확인하기 위해서 `dir()`을 사용할 때, 아무런 인자 없이 사용하면 `main global Python scope`에서 이용가능한 name list를 얻는다.

5. 프로그램 실행할 때 단 하나의 `global Python scope`만이 존재한다. 그리고, 프로그램 실행이 끝나야 scope이 종료된다.

6. local scope에 있는 global 변수를 참조할 수 있지만, local scope에서 global variable에 값을 할당할려고 하면 Error가 발생된다.

```yml
# a global variable
> var = 100

> def increment():
>   var += 1 # global variable 업데이트 시도하기

> increment()
UnboundLocalError: local variable 'var' referenced before assignment
```

- global variable(전역 변수)를 할당하려고 시도했지만, local scope(지역 범위) 내에서는 global variable에 값을 할당할 수 없다. (6번)

- 그래서 전역 변수가 지역 변수와 이어지지 않기 때문에, 할당 없이 지역 변수 'var'을 참조하여 Error가 발생했다.

- 그러면 이렇게 코드를 다시 짜보자.

  ```yml
  # 전역 변수
  > var = 100

  > def func():

  # 동일한 이름으로 새로운 지역 변수를 정의한다.
  >   var = 200

  # 전역 변수인 var를 참조하는 게 아닌, 지역 변수인 var를 참조한다.
  >   print(var)
  ```

  - 전역 변수를 업데이트한 것이 아닌 function의 body 부분에 있기 때문에 지역 변수를 새로 만든 것이다.

- 즉, 다음 사실을 알 수 있다.

> **_Python은 global variable과 동일한 이름으로 function body에 선언해도 local variable로 인식한다._**

<br>

## 2.4 Local variable 또는 global variable 찾아보기

- `locals()` 과 `globals()` function을 통해서 지역 변수와 전역 변수를 출력해보자.

<br>

### 2.4.1 locals()

> **_locals(): Return a dictionary containing the current scope's local variables._**

```yml
> def func(var):
>   x = 10
>   def printer():
>       print('Ex > 5', "Printer Func Inner")
>   print(locals())

> func('Hi')
{'var': 'Hi',
'x': 10,
'printer': <function func.<locals>.printer at 0x000001D53343FDC0>}
```

- **var** :`func()` 함수를 호출하기 위해 인자로 넘겼던 'Hi' 또한 지역변수임을 알 수 있다.
- **x** : enclosing scope에 있는 것 또한 지역변수임을 확인할 수 있다.
- **printer** : outer function의 local scope에 정의했기 때문에 printer 또한 지역 변수로 확인할 수 있다.

<br>

### 2.4.2 globals()

- globals는 이 코드를 실행할 때 입력한 모든 전역 변수가 입력되기 때문에, 다음과 같이 하여 알아본다.
- `globals()`는 global 영역의 변수를 입력할 때 호출된다.

```yml
> print('Ex >', globals())
Ex > {.....}

> globals()['text_variable'] = 100
> print('Ex >', globals())
Ex > {'__name__': '__main__', '__doc__': None, ..., '__cached__':
None, 'func': <function func at 0x0000028708197F70>, 'text_variable': 100}
```

<br>

- `globals()`를 사용한 변수 자동화 생성: 지역 -> 전역 변수로 작성한다.

```yml
> for i in range(1, 6):
>   for k in range(1, 6):
>       globals()['plus_{}_{}'.format(i, k)] = i + k

> print(globals())
{'plus_1_1': 2, 'plus_1_2': 3, 'plus_1_3': 4, 'plus_1_4': 5, 'plus_1_5': 6,
'plus_2_1': 3, 'plus_2_2': 4, 'plus_2_3': 5, 'plus_2_4': 6, 'plus_2_5': 7,
'plus_3_1': 4, 'plus_3_2': 5, 'plus_3_3': 6, 'plus_3_4': 7, 'plus_3_5': 8,
'plus_4_1': 5, 'plus_4_2': 6, 'plus_4_3': 7, 'plus_4_4': 8, 'plus_4_5': 9,
'plus_5_1': 6, 'plus_5_2': 7, 'plus_5_3': 8, 'plus_5_4': 9, 'plus_5_5': 10}

> print(plus_3_5)
8
> print(plus_5_5)
10
```

<br>

## 1.12 LEGB rules: Built-in scope

- Built-in scope은 `builtins` 라 불리는 표준 라이브러리 모듈로서 실행되는 특별한 파이썬 scope이다.
- 파이썬은 LEGB 에서 마지막으로 built-in을 찾는다.
- 이 scope에서는 어느 모듈이든지 import할 필요 없이 names을 사용할 수 있다.
- `builtins` 안에 있는 name들은 언제나 Python의 global scope에, `__builtins__`로 담겨진다. 밑에 예제를 보자.

```yml
> dir()
['__annotations__', '__builtins__',..., '__package__', '__spec__']

> dir(__builtins__)
['ArithmeticError', 'AssertionError',..., 'tuple', 'type', 'vars', 'zip']
```

- `dir()`의 첫 호출에서 `__builtins__`을 확인할 수 있다.
- 그리고 `__builtins__`를 dir로 내부를 들여다보면, 파이썬의 built-in names의 전체 목록을 얻을 수 있다.

<br>

- 또 한 가지 특징은 global scope에서 어떠한 built-in names이든 오버라이드할 수 있다.
- 하지만 우연히 또는 부주의하게 이렇게 오버라이드가 된다면 위험하며, 버그를 찾기 어렵다. 그래서 이런 종류의 실행은 최대한 피하는 게 낫다.

```yml
# a built-in fuction의 표준 사용
> abs(-15)
15

# global scope에서 built-in name을 재정의한다.
> abs = 20

> abs(-15)
TypeError: 'int' object is not callable

> del abs
> abs(-15)
15
```

<br>

---

# 2. Python memory structure

<br>

## 2.1 코드 영역

> **실행할 프로그램의 코드가 저장**되는 영역 (text 영역이라고도 한다)

<br>

## 2.2 데이터 영역

> 프로그램의 **global variable과 정적(static) variable**가 저장되는 영역

- 프로그램이 시작하고, 끝날 때까지 메모리에 계속 남아 있는다.

<br>

## 2.3 Stack

> - 데이터를 임시 저장할 때 사용하는 자료구조로, 데이터의 입력과 출력 순서는 **_후입선출(Last In First Out, LIFO)_** 방식
> - 지역 변수 와 매개변수가 저장된다.

![image](https://media.vlpt.us/images/awesomeo184/post/c6d03dfa-ca41-46ef-b3bd-3524c7c704c4/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-10-07%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%208.25.11.png)

- **`push(푸쉬)`**: stack에 데이터를 넣는 작업
- **`pop(팝)`**:stack에서 데이터를 꺼내는 작업
- 데이터를 넣고 꺼내는 작업에서 윗 부분을 `top`, 아랫 부분을 `bottom` 이라 한다.

<br>

- stack 영역은 `함수의 호출과 함께 생성`되고, 함수의 호출이 완료되면 소멸한다.
- `스택 프레임(stack frame)`: 스택 영역에 저장되는 함수의 호출 정보
- 메모리의 높은 주소에서 낮은 주소의 방향으로 할당된다.
- 한계가 있어서, 한계를 초과하도록 삽입할 수 없다.
- Stack overflow: 함수는 변수를 저장하기 위해 stack을 만드는데, 만들어진 stack이 메모리 용량을 넘어서면 `Stack overflow`가 발생한다.

<br>

## 2.2 Heap

> **_사용자가 직접 관리할수 잇는 영역으로, 객체가 생성된다._**

- 사용자에 의해 메모리 공간이 동적으로 할당되고, 해제된다.
- heap 영역은 `런타임 시`에 크기가 결정된다 (메모리가 할당된다)
- 메모리의 낮은 주소에서 높은 주소로 할당된다.

<br>

---

# Reference

- [파이썬 프로그래밍 기초 - 지역 변수와 전역 변수](https://www.youtube.com/watch?v=xNaBCSnm3Hg&list=PLUWNXmQtLdmDCRVur4eHjKhLZtipVexCE&index=13)
- [Python-course.eu: Namespaces](https://python-course.eu/python-tutorial/namespaces.php)
- [스코핑 룰(Scoping rule)](https://blog.hexabrain.net/283)
- [자료구조와 함께 배우는 알고리즘 입문 파이썬편](http://www.yes24.com/Product/Goods/91219874)
- [Python Scope & the LEGB rule: Resolving Names in Your code](https://realpython.com/python-scope-legb-rule/)
