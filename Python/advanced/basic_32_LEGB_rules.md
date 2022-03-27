# Intro

> 1. [LEGB rules(Scoping rules)](#1legb-rulesscoping-rules)
> 2. [Stack and Heap](#2-stack-and-heap)

- `courition`을 공부하면서 global variable과 local variable에 대해서 기본적인 의미만 알았지만,
- 이것이 Scoping Rule과 연관이 되어있다는 것과,stack과 heap이라는 데이터 임시 저장 자료 구조와 연관된 걸 몰랐다.
- 그래서 `Scoping Rule`, `Stack` 그리고, `Heap`에 대해 알아보겠다.

<br>

---

# 1.LEGB rules(Scoping rules)

> 변수(variable)의 생존 범위에 관련된 규칙

- Python scope 개념을 알아야 하는 이유:
  - 신뢰성 있고, 유지보수성이 좋은 프로그램을 작성할 수 있다.
  - name 충돌을 방지할 수 있고, 버그를 줄일 수 있다.
  - 이와 관련된 tool인 `Closure`에 대해 알 수 있다.

<br>

## 1.1 Scope의 일반적인 종류 : 2가지

- **global** scope: 이 scope에서 정의한 name은 모든 코드에서 사용할 수 있다.
- **local** scope: 이 scope에서 정의한 name은 범위 내의 코드에서만 사용할 수 있거나 볼 수 있다.

> name: 변수, 상수, 함수, 클래스 등등의 식별자(identifier)를 참조하는 것으로, 구분하기 위해 존재한다.

<br>

## 1.2 초기 Scope의 부재로 인한 문제

- 초기 프로그래밍은 **global**만 있었기 때문에,
- 변수를 수정해야할 때 모든 코드를 동시에 염두에 둬야했다.
- 그래서 이런 문제를 피하기 위해 **scope**을 사용했다.
- scope을 사용한 후, 프로그램의 어디에서든지 그 scope에 있는 변수들에 함부로 접근할 수 없다. (_out of scope_)
- name들의 scope은 이 name들을 정의한 코드의 block scope과 동일하다. (_in scope_)

<br>

## 1.3 파이썬의 이름과 범위들

- 파이썬에서의 변수들에 값이 할당될 때, 즉 파이썬 names을 다음과 같은 방법들로 만들 때 변수들은 존재하게 된다.
  - 변수(variable): 변수에 값을 할당하면, 변수는 만들어진다.
  - function , classes: 예약어 `def`, `classes`를 사용하여 정의하면 이용할 수 있다.
  - modules: `import`하여 사용할 수 있다.

<br>

## 1.4 assignment operations와 reference operations의 차이

- reference operations: name을 `참조`한다는 건, name에 담겨진 content or value를 `단지 가져온다`는 걸 의미
- assignment operations: name을 `할당`한다는 건, name을 `새롭게` 만들거나, `수정`한다는 것을 의미
- 그리고, '할당' 한다는 건 '특정 scope이 결정'된다는 걸 말한다.

<br>

## 1.5 Python scope와 namespace의 관계

- **Namespace** : python의 여러 scope level이 합쳐진 것으로, dictionary data type로 구현되었다.
- 그리고, Python이 name을 저장하는 구체적인 원리다.
- Namespace는 또한 `__dir__` 명령어에 저장되어 있다.
- 즉, **`__dir__`은 Namespace를 확인할 수 있는 명령어이고, 할당된 name이 가지는 scope을 보여준다.**
  - `.__dict__.keys()` 로 key value로 indexing하여 확인할 수 있다.
  - python은 name의 존재유무를 확인하기 위해서, `여러 scope levels(or namespace)`를 찾아본다.

<br>

## 1.6 Python이 name을 찾는 규칙: LEGB rules

<p align="center"><image src ="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fcf0kvQ%2FbtqzVDW0VvA%2FdDklbytc0lkJ96o5NBicI0%2Fimg.png"/></p>

- local -> enclosing -> global -> built-in scope 순서로 주어진 name을 파이썬이 찾는다.

- **Local(or function) scope**: Python function의 body 또는 code block 부분이 local scope

  - fuction 내에서 정의한 name들만 포함한다.
  - 이 `function의 코드에서만` local scope에 있는 name을 확인할 수 있다.

- **Enclosing(ornonlocal) scope**: 중첩함수(nested functions)를 위해서만 존재하는 scope

  - enclosing function 안에서 정의된 names만 포함한다.
  - 이 `enclosing function의 코드에서만` enclosing scope에 있는 name을 확인할 수 있다.

- **Global(or module) scope**: Python program, script, module 안에서 최고위 수준(Top level)의 scope

  - 프로그램 또는 모듈에서 `최고 수준으로` 정의한 이름들을 포함한다.
  - `어느 코드에서든지` Global scope에 있는 name을 확인할 수 있다.
  - [Top level이란??](https://jeha00.github.io/post/python_basic/python_basic_23_ifnamemain/#22-top-level-%EC%9D%B4%EB%9E%80)

- **Built-in scope**: script를 run할 대마다 만들어지는 특별한 scope

  - `Python 안에 내장된` 예약어들, functions들 등등의 name을 포함한다.
  - 이 scope 또한 Global scope처럼 `어느 코드에서든지` name을 확인할 수 있다.

<br>

## 1.7 LEGB rules: The Local Scope

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

  - square fuction을 호출할 때, 파이썬은 base와 result를 포함하는 local scope을 만든다.
  - `square(10)`으로 호출할 때, base에는 10을 result에는 100을 취한다.
  - 또 다시 호출할 때는 첫 번째 호출 시 취한 값들은 기억하지 않는다.
  - `result`와 `base`는 `square()` 호출에 의해 만들어진 `local scope`에만 존재한다.
  - 그래서 `square` fuction 호출 후, 접근한다면 `NameError`을 얻는 것이다.

- 그러면 추가로 local scope에 동일한 변수 이름을 가진 fuction을 정의해보자.

```yml
> def cube(base):
>        result = base ** 3
>        print(f'The cube of {base} is : {result}')

> cube(30)
The cube of 30 is : 27000
```

- local scope에 동일한 변수이름을 사용했지만, 프로그램 충돌이 일어나지 않았다.
- 왜냐하면 `local scope`을 사용했기 때문이다.
- 이러한 장점 때문에
  - 디버깅과 수정이 쉽고, 가독성이 좋아진다.

<br>

## 1.8 LEGB rules: The Enclosing Scope (Nested Functions)

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
> inner_func()
```

- `outer_func()`을 호출할 때, `outer_func()`의 local scope이 만들어진다. 이 scope은 동시에 `inner_func()`의 `enclosing scope`이라고도 한다.
  - `global scope`과 `local scope` 둘 다 아니고, 이 사이에 놓여있는 특별한 scope을 의미한다.
- 또한, `inner_func()`은 enclosing function인 outer_func이 실행되는 동안에만 유지되는 일시적인 함수다. 즉, `outer_func()`의 code에서만 `inner_func()`은 찾을 수 있다.

<br>

## 1.9 LEGB rules: Modules - The Global Scope

- 1. 프로그램을 실행한 순간부터 `global scope`에 있는 것이다.

- 2. 이 `global scope`은 module file과 깊은 연관이 있기 때문에, `module scope`이라고도 한다.

- 3. 그리고 현재 실행되는 script 또는 module이 entry point 역할을 한다면, 이 시점부터 scope of `__main__`이 된다.

- 4. namespace를 확인하기 위해서 `dir()`을 사용할 때, 아무런 인자 없이 사용하면 `main global Python scope`에서 이용가능한 name list를 얻는다.

- 5. 프로그램 실행할 때 단 하나의 `global Python scope`만이 존재한다. 그리고, 프로그램 실행이 끝나야 scope이 종료된다.

- 6. local scope에 있는 global 변수를 참조하거나 접근할 수 있다.
  - 하지만, local scope에서 global variable에 값을 할당할려고 하면 Error가 발생된다.

```yml
# a global variable
> var = 100

> def increment():
>   var += 1 # global variable 업데이트 시도하기

> increment()
UnboundLocalError: local variable 'var' referenced before assignment
```

- global variable을 업데이트하려고 시도했지만, local scope 내에서는 global variable을 선언할 수 없기 때문에, 파이썬에서는 `var` 이라는 동일한 이름으로 local variable을 새롭게 만들었다.

- 그리고, 이 과정에서 첫 번째 할당 `var + 1` 전에 local var을 사용하려고 시도한 걸 알았기 때문에 Error가 발생된다.

- 그러면 이렇게 코드를 다시 짜보자.

```yml
# a gloabl variable
> var = 100

> def func():

# the global varaible 참조한다.
>   print(var) 'var'

# 동일한 이름으로 새로운 local variable을 정의한다.
>   var = 200

>> func()
UnboundLocalError: local variable 'var' referenced before assignment
```

- global variable을 출력한 후에 `var`을 업데이트 할 수 있다고 생각했겠지만, 다시 동일한 Error가 발생된다.

- global variable을 업데이트한 것이 아닌 function의 body 부분에 있기 때문에 local variable를 새로 만든 것이다.

> Python은 global variable과 동일한 이름으로 function body에 선언해도, local variable로 인식한다.

<br>

---

# 2. Stack and Heap

<br>

---

# Reference

- [파이썬 프로그래밍 기초 - 지역 변수와 전역 변수](https://www.youtube.com/watch?v=xNaBCSnm3Hg&list=PLUWNXmQtLdmDCRVur4eHjKhLZtipVexCE&index=13)
- [스코핑 룰(Scoping rule)](https://blog.hexabrain.net/283)
- [자료구조와 함께 배우는 알고리즘 입문 파이썬편](http://www.yes24.com/Product/Goods/91219874)
- [Python Scope & the LEGB rule: Resolving Names in Your code](https://realpython.com/python-scope-legb-rule/)
