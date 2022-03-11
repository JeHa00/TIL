# Python basic 23 : if \_\_name\_\_ == '\_\_main\_\_'

## Intro

> 1. [\_\_name\_\_ 에 대해 알아보자.](#1-__name__-에-대해-알아보자)
> 2. [\_\_main\_\_ 에 대해 알아보자.](#2-__main__-에-대해-알아보자)
> 3. [if \_\_name\_\_ == '\_\_main\_\_' 에 대해 알아보자.](#3-if-__name__--__main__-에-대해-알아보자)

<br>

- [[TIL] Python basic 15: module](https://jeha00.github.io/post/python_basic/python_basic_15/)에서 학습했지만, 내용이 빈약하여 별도로 정리한다.

---

## 1. \_\_name\_\_ 에 대해 알아보자.

<br>

### 1.1 \_\_name\_\_이란??

> The file name is the module name with the suffix `.py` appended.  
> Within a module, the module’s name (as a string) is available as the value of the global variable `\_\_name\_\_`
> 출처: [파이썬 공식 문서](https://docs.python.org/3/tutorial/modules.html#modules)

- 위 내용을 요약하면 \_\_name\_\_이란?

> `\_\_name\_\_`은 `.py`인 파이썬 모듈 파일이 가지고 있는 global variable(전역변수)이며, 모듈의 이름을 담고 있다.

<br>

### 1.2 \_\_name\_\_이 다르게 출력되는 상황 2가지

- 그리고, 다음 두 가지 상황에서 다르게 출력된다.

> - 첫 번째, script 프로그램이 '직접' 실행될 때 변수 \_\_name\_\_은 '\_\_main\_\_' 이다.
> - 두 번째, 스크립트 프로그램이 import될 때 변수 \_\_name\_\_은 원래의 모듈 이름이다.

- 2개의 파일 (hello.py 와 python.py) 을 작성하자.
- 그리고 이 두 개의 파일을 module로서 import하는 test.py 를 작성하자.
- 그러면 총 3개의 파일을 작성한 상태다.

<br>

- hello.py, python.py 그리고 test.py의 내용은 다음과 같다.

```yml
# hello.py
> print("hello.py: ", __name__)

# python.py
> print("python.py: ", __name__)

# test.py
> import hello
> import python

```

- 첫 번째 경우
  - hello.py 와 python.py 를 직접 실행해보겠다.
  - 두 파일 모두 다음과 같이 출력되었다.

```yml
hello.py: __main__
python.py: __main__
```

<br>

- 두 번째 경우
  - test.py 를 실행해보겠다.
  - 아래 코드를 보면 `__main__` 이 아닌, `import된 모듈명`이 출력된다.
  - 즉, 원래의 모듈 이름이 출력된다.

```yml
hello.py: hello
python.py: python
```

> - 결론
>   \_\_name\_\_은 직접 실행될 때 \_\_main\_\_'이 출력되거나, import될 때에는 원래의 모듈이름이 담겨진다.

<br>

- 하지만, 이걸로 완전히 의문점을 해결되지 않았다.
- 그러면 `__main__`은 무엇을 의미하는 것인가???

---

<br>

## 2. \_\_main\_\_ 에 대해 알아보자.

### 2.1 Main Module이란??

- 파이썬 공식 문서를 보면 \_\_main\_\_에 대해 다음과 같이 설명한다.

> the name of the main module is always "\_\_main\_\_"
> 출처: [Modules](https://docs.python.org/3/tutorial/modules.html#intra-package-references)
> main module에서의 \_\_name\_\_ 은 항상 "\_\_main\_\_" 이다.

<br>

- 그러면 `main module`의 정의는 무엇인가???

> main module (the collection of variables that you have access to in a script executed at the top level and in calculator mode)
> 출처: [파이썬 공식 문서: Module](https://docs.python.org/3/tutorial/modules.html#modules)
> top level에서 실행되는 script 안에서, 접근 권한을 가지고 있는 변수들의 집합을 말한다.

<br>

---

### 2.2 Top level 이란??

> \_\_main\_\_ is the name of the environment where top-level code is run.  
> “Top-level code” is the first user-specified Python module that starts running.  
> It’s “top-level” because it imports all other modules that the program needs.  
> Sometimes “top-level code” is called an entry point to the application.  
> 출처: [파이썬 공식 문서: \_\_main\_\_](https://docs.python.org/3/library/__main__.html)

- \_\_main\_\_은 top-level code 가 운영되는 환경의 이름인데,
- Top-level code는 사용자가 지정한 Python module 중에서 최초로 실행하기 시작하는 Python module이다.
- 즉, 프로그램이 필요한 다른 모듈들을 import 하는 module이다.
- "top-level code" 는 애플리케이션의 관점에서 `entry point` (시작점) 이라 불린다.

<br>

- \_\_main\_\_이 출력되는 모듈이 `entry point`라는 걸 알 수 있다. 그리고, 다른 module들을 import 하는 파일을 말한다.

<br>

> [결론]  
> **_ \_\_name\_\_ 변수를 통해서 현재 진행되는 파일이 entry point인지, module인지 판단할 수가 있다._**

---

<br>

## 3. if \_\_name\_\_ == '\_\_main\_\_' 에 대해 알아보자.

<br>

### 3.1 `if \_\_name\_\_ == '\_\_main\_\_'` 이란 무엇인가??

- 그러면 `if \_\_name\_\_ == '\_\_main\_\_` 의미는 다음과 같다.

> 이 조건문이 있는 파일을 import가 아닌 직접 실행을 한다면 아래 코드들을 실행하라.

- 마지막으로 한 예를 들고 끝내겠다.
- 예시의 출처는 [What does if **name** == "**main**": do?](https://stackoverflow.com/questions/419163/what-does-if-name-main-do)이다.

```yml
> print("before import")
> import math

> print("before functionA")
> def functionA():
>     print("Function A")

> print("before functionB")
> def functionB():
>     print("Function B {}".format(math.sqrt(100)))

> print("before __name__ guard")
> if __name__ == '__main__':
>     functionA()
>     functionB()
>  print("after __name__ guard")

```

- 위 code가 import 되어 실행된다면 다음과 같다.

```yml
before import
before functionA
before functionB
before __name__guard <-
after __name__guard <-
```

- 직접 실행한다면 다음과 같다.

```yml
before import
before functionA
before functionB
before __name__guard <-
Function A
Function B 10.0
after __name__guard <-
```

- import되어 실행할 떄는 `<-` 사이에 아무것도 출력되지 않았지만, 직접 실행할 때는 무언가 출력된 걸 확인할 수 있다.
- `if \_\_name\_\_ == '\_\_main\_\_'` 조건문에서 출력된 결과물이다.
- 직접 실행되었기 때문에 출력되었다.

<br>

---

### 3.2 `if \_\_name\_\_ == '\_\_main\_\_'` 을 왜 사용하는 걸까??

1. stack-over-flow [What does if\_\_name\_\_ == '\_\_main\_\_': do?](https://stackoverflow.com/questions/419163/what-does-if-name-main-do)
2. [[TIL] Python basic 15: module](https://jeha00.github.io/post/python_basic/python_basic_15/)

- 위 두 링크에 따르면 다음과 같은 이유로 작성한다.

  - 외부 module file을 import 시, import된 파일에 있는 script 중 의도치 않게 호출한 것으로부터 보호하기 위해 사용하는 상용구 코드다.
  - 스크립트에서 이 `guard`를 생략하면 구체적으로 다음과 같은 문제가 발생한다.
  - 첫 번째, run time 시 의도치 않게 불러온 script에 있는 여러 인자들로 main script가 작동된다.
  - 두 번째, `guard`를 생략한 script를 저장할 파일에 담아 저장하면, 이 파일을 불러올 때 `guard`를 생략한 script를 import 할 수 있다.

- 즉, `if \_\_name\_\_ == '\_\_main\_\_'` code는 의도치 않고 불필요한 코드를 호출하는걸 방지하기 위한 `guard`로서 사용한다.

<br>

---

## Reference

- [[TIL] Python basic 15: module](https://jeha00.github.io/post/python_basic/python_basic_15/)
- [[python] if \_\_name\_\_ == '\_\_main\_\_' : 의 정체](https://youngminieo1005.tistory.com/82)
- [자료구조와 함께 배우는 알고리즘 입문 파이썬편](https://book.naver.com/bookdb/book_detail.nhn?bid=16419115)
- [What does if\_\_name\_\_ == '\_\_main\_\_': do?](https://stackoverflow.com/questions/419163/what-does-if-name-main-do)
- [Modules](https://docs.python.org/3/tutorial/modules.html#intra-package-references)
- [파이썬 공식 문서](https://docs.python.org/3/tutorial/modules.html#modules)
- [\_\_main\_\_](https://docs.python.org/3/library/__main__.html)
