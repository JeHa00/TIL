# Python basic 15: 파이썬 모듈(module)

## Intro

> 1. [Package 구조]()
> 2. [패키지 경로 및 패키지 함수 실행]()
> 3. [`__init__.py` 가 존재하는 이유]()

## 1. Pacakge 구조

- `Package`는 `module`의 묶음을 의미하며, 폴더에 담아 관리한다.
- 그러면 이 `pacakge`를 불러와서 사용하기 위해서는 2가지 방법이 있다.
  - 첫 번째 방법: 현재 경로를 기준으로 상대 경로 개념을 사용하여, `module 경로를 직접 입력`하여 사용하는 방식
  - 두 번째 방법: `import`와 `from`을 사용하는 방식
- 경로 이동: `cd ..` 을 하면 `상위 directory`로 이동된다.
- 다 같이 여러명과 project를 진행한다면 위 방법 2가지 보다는 컴퓨터 공용 위치에 package 파일들을 두고, `sys.path` 또는 `환경설정`에서 경로를 설정하는 방법을 사용하자.

---

<br>

## 2. 패키지 경로 및 패키지 함수 실행

<br>

### 2.1 패키지 경로 와 `inspect.getfile` 함수

- 예제를 설명하기에 앞서 module로 사용할 package인 `module1.py` 와 `module2.py`의 위치는 다음과 같다.
- `__init__.py` 파일도 기억해놓자.

```yml
sub
├─sub1
│  │─ module1.py
│  │─ __init__.py
│  └─ __pycache__
├─sub2
│  │─ module2.py
│  │─ __init__.py
│  └─__pycache__
└─__pycache__
```

- 그리고 `module` 파일인 `module1.py`와 `module2.py`의 내부 코드는 다음과 같다.
- `inspect` 함수는 파이썬의 객체들로부터 유용한 정보를 얻고자 할 때 사용하는 함수다.
  - `inspect`: get useful imformation from live Python objects
- `inspect` 에 의해 제공되는 함수가 `.getfile` 이다.
- `.getfile`은 `object`가 어느 위치에 있는지 알고자 할 때 사용한다.
- `.currentframe` 은 `실행 중인 파일의 이름과 경로`를 보여준다.
-

```yml
## module1.py
> import sys
> import inspect
# from ..sub2 import module2


> def mod1_test1():
> 	print ("Module1 -> Test1")
> 	print("Path : ", inspect.getfile(inspect.currentframe()))


> def mod1_test2():
> 	print ("Module1 -> Test2")
> 	print("Path : ", inspect.getfile(inspect.currentframe()))

# 만약 inspect.currentframe()만 입력하면 다음과 같이 뜬다.
#  <frame at 0x00000296CE1BB7C0, file c:\\Users ~~ \module1.py, line 9, code mod1_test1>
# 파일 경로와, 파일 내의 몇 번째 줄인지까지 확인할 수 있다.

# 하지만, insepct.getfile(inspect.currentframe())) 을 입력하면 다음과 같이 뜬다.
#  c:\Users\ ~ sub\sub1\module1.py
# 경로만 출력된다.

# 코드 경로를 넘어서 더 상세한 위치를 알고 싶다면 `inspect.currentframe()`을 사용해야겠다.


## module2.py
> import sys
> import inspect

> def mod2_test1():
> 	print ("Module2 -> Test1")
> 	print("Path : ", inspect.getfile(inspect.currentframe()))


> def mod2_test2():
> 	print ("Module2 -> Test2")
> 	print("Path : ", inspect.getfile(inspect.currentframe()))

```

<br>

### 2.2 패키지 함수 실행하기: 2가지 방법

<br>

- 그러면 패키지를 불러오는 방법 2가지에 대해 알아보자.
- 폴더명을 입력하고 `점.`을 입력하면 입력했던 폴더명의 하위 object가 뜬다.

  - 폴더, 파일, method 등등이 뜬다.

<br>

- 첫 번째 방법의 장단점
  - 경로를 하나 하나 입력하는 방법이다.
  - 단점:
    - 경로가 너무 다르면 입력해야할 경로가 너무 길어진다.
    - 그래서 현재 경로와 같을 경우에 사용한다.
    - 경로가 길어질 경우를 대비해서 `from`을 사용한다. (두 번째 방법)
  - 장점:
    - `sys.path.append()` 함수로 경로를 추가하지 않아도 불러올 수 있다.

```yml
## 첫 번째 방법

> sub.sub1.module1.mod1_test1()
> sub.sub2.module2.mod2_test1()

# 또는

> import sub.sub1.module1
> import sub.sub2.module2


```

<br>

- 두 번째 방법: `from` ~ `import` ~ `as` 사용하기
  - 첫 번째보다 경로를 짧게 입력할 수 있기 때문에, 깔끔하고 가독성이 좋다.
  - `from`을 통해 패키지의 정확한 경로를 찾아서 사용하고 싶은 모듈만 `import` 하여 사용한다.
  - `as`는 `alias`로 `별명, 별칭`이다.
  - `as`를 설정하면 모듈 이름을 다 입력할 필요 없이, `as`만 입력하면 된다.

<br>

```yml
## 두 번째 방법
> from sub.sub1 import module1
> from sub.sub1 import module2 as m2

# 호출하기
> module1.mod1_test1()
Module1 -> Test1
Path :  c:\Users\ ~ \sub\sub1\module1.py
> module1.mod1_test2()
Module1 -> Test2
Path :  c:\Users\ ~ \sub\sub1\module1.py

# 아래 2가지는 서로 같다.
> module2.mod2_test1()
> m2.mod2_test1()
Module2 -> Test1
Path :  c:\Users\ ~ \sub\sub2\module2.py
```

<br>

- `from` ~ `import` \* 로 모든 module 파일을 가져올 수도 있다.
- `*` 이 모든 파일을 의미한다.
- 하지만 이런 경우 안쓰는 파일을 가져온다.
- 현재 HW의 발달로 눈에 띄는 성능 저하는 드러나지 않지만, 이런 것들이 쌓이면 `run time`에서 메모리를 잡아먹는다.
- 항상 `메모리`를 신경쓰는 습관을 가지자.

```yml
> from sub.sub1 import *
> from sub.sub2 import *

> module1.mod1_test1()
Module1 -> Test1
Path :  c:\Users\ ~ \sub\sub1\module1.py
> module1.mod1_test2()
Module1 -> Test2
Path :  c:\Users\ ~ \sub\sub1\module1.py


> module2.mod2_test1()
Module2 -> Test1
Path :  c:\Users\ ~ \sub\sub2\module2.py
> module2.mod2_test2()
Module2 -> Test2
Path :  c:\Users\ ~ \sub\sub2\module2.py

```

---

<br>

## 3. `__init__.py` 가 존재하는 이유

<br>

- `__init__.py` 파일은 파이썬에게 해당 폴더가 `package`로 인식하도록 해준다.
- `__init__.py` 이 없으면 파이썬은 그 폴더를 `package`로 인식하지 않는다.
- 하지만, `Python 3.3` 부터는 `__init__.py` 파일이 없어도 `package`로 인식한다.
- 그러면 `__init__.py` 을 작성할 필요가 없을까?? 아니다.
  - `Python 3.3` 이전 버전으로 의뢰가 들어올 경우가 있기 때문에, 그리고 `하위 호환`을 위해 작성한다.
  - `Python 3.3` 이전 버전으로 작성하다가 새롭게 업데이트를 하면 거의 작동되겠지만, 예상치 못한 `side effect`가 있을 수 있기 때문에 작성한다.
- `package` 폴더에 있는 `pyecache` 파일은 빠른 실행을 위해 파이썬 엔진이 만드는 것이기 때문에, 지워도 실행하면 다시 생긴다.

<br>

- 그러면 `__init__.py` 파일 내부를 살펴보자.

```yml
# sub1 폴더에는 module1.py 이 있다.
# sub1 의 __init__.py 작성 명령어는 다음과 같다.
> __all__ = ['module1']

# sub2 폴더에는 module2.py 이 있다.
# sub2 의 __init__.py 작성 명령어는 다음과 같다.
> __all__ = ['module2']
```

- 아래 내용은 `Python 3.3` 이전 버전에 관한 내용이다. 이후부터는 필수가 아니다.
- `__all__ = [ ]` 에서 대괄호에 `module 파일명`이 적혀 있어야, 외부에서 `import` 할 때 해당 `module` 파일을 허가해준다.
- 이 list에 파일명이 다르면 작동할 수 없다.
- 파이썬이 `import`할 때, `__init__`을 먼저 검사하기 때문에, all에 없으면 `error`가 발생된다.
- `Python 3.3`부터는 `__init__.py` 가 필수가 아니어도, 아직도 많은 오픈 소스에는 `__init__.py` 가 존재한다.
