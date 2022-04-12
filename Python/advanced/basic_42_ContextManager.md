# Intro

> 1. [Context manager란??](#1-context-manager란)
> 2. [Context manager: no use 'with'](#2-context-manager-no-use-with)
> 3. [Context manager: use 'with'](#3-context-manager-use-with)
> 4. [Context manager: use 'class'](#4-context-manager-use-class)
> 5. [Context manager: Measure execution](#5-context-manager-measure-execution)
> 6. [Context manager: use 'decorator'](#6-context-manager-use-decorator)

<br>

---

# 1. Context Manager란?

> 원하는 타이밍에 정확하게 resource를 할당 및 제공하며, 반환하는 역할을 한다.  
> 그래서, special method `.__enter__` 와 `.__exit__`을 가지고 있는 객체를 말한다.

<br>

## 1.1 Context Manager가 필요한 이유???

- 외부와 connection될 때, 한정된 H/W resource를 사용하기 때문에, resource가 제 때 반환되지 않으면 system이 느려지거나 특정 상황에서 error가 발생할 수 있다.
- 즉, 문을 열고 들어갔으면 문을 닫아야 하고, 도서관에서 책을 빌리면 책을 반납해야 하듯이 memory resource 또한 사용되면 반환되야 한다.
- 그래서 `원하는 시점에` resource 할당 및 회수를 위해서 context manager가 중요하다.
- 이러한 특징으로 외부 resource를 처리하는 작업을 할 때, 안전하게 할 수 있는 기능을 만들 수 있다.

<br>

## 1.2 Context Manager의 magic method

- [What is a "runtime context?"](https://stackoverflow.com/questions/19652662/what-is-a-runtime-context) stackoverflow 내용을 덧붙인다.

  - with statement 아래의 code block에 들어가기 위해서 `.__enter__` special method가 호출된다.
  - with statement 아래의 code block에서 나가기 위해서 `.__exit__` special method가 호출된다.

- [Python docs: Context Manager Types](https://docs.python.org/3/library/stdtypes.html?highlight=__enter__#context-manager-types)의 내용을 추가한다.

  - context manager는 context manager 자체를 반환하는데 이것의 example 중 하나는 `file object`다.

    - file obejct는 `open()` function이 with statement 에서 사용되기 위해 `__enter__` 로부터 file object 자신들을 반환한다.

    - `.__enter__()` magic method에 의해 반환된 값은 with statement의 as 절의 식별자(identifier)에 연결된다.

    - `.__exit__(exc_type, exc_val, exc_tb)` 은 runtime context를 빠져나오고, 발생한 exception을 무시해야하는지를 가리키는 boolean flag를 반환한다.
      - with 문의 body를 실행하는 동안 예외가 발생하면 예외 타입, 값, 추적정보가 포함된다.
      - 이 method에서 True를 반환하면, with문이 예외를 막고 계속해서 실행한다.

- Context manager의 대표적인 구문인 with를 이해해야한다.
  - with문에 관한 내용은 [[TIL] Python basic 20: with open as](https://jeha00.github.io/post/python_basic/python_basic_20_filewriteread_1/) 와 [[TIL] Python basic 21: csv.read, write](https://jeha00.github.io/post/python_basic/python_basic_21_filereadwrite_2/)을 참고한다.

<br>

---

# 2. Context manager: no use 'with'

```yml
> file = open('./testfile1.txt', 'w')

# 파일을 열고
> try:
>     file.write('Context Manager Test1.\nContextlib Test1.')

# 파일을 닫는다.
> finally:
>     file.close()
```

- python이 업데이트 되어 나온게 with문이다.

<br>

---

# 3. Context manager: use 'with'

- 위에 no use 'with' 에서의 코드가 with를 사용해서 다음 code로 바뀐다.
- close를 입력하지 않아도, 자동으로 반환한다.
- 그래서 이 with문으로 Internet connection을 맺고 끊는 것으로 사용할 수 있다.

```yml
> with open('testfile1.txt', 'w') as f:
>   f.write('Context Manager Test1.\nContextlib Test1.')
```

- 위 코드의 결과로 testfile2.txt 가 생성되고, 그 안에는 Context Manager Test2. \nContextlib Test2. 가 작성되어 있다.

<br>

---

# 4. Context manager: use 'class'

- magic method를 사용하여 class로 customizing 한 후, with문을 실행해보자.
- with문을 실행하면 magic method는 이와 같은 순서로 실행된다.
  - `__init__` -> `__enter__`가 실행된다.
  - 실행된 결과로 어느 값이 반환되고, 이 반환 값으로 write 작업을 실행한다.
  - write 작업이 완료 후, 빠져나갈 때 error가 발생되면 `__exit__`의 print에서 처리되고, `close()`가 실행된다.

```yml
> class FileWriter():

> def __init__(self, file_name, method):
>   print('FileWriter started : __init__')
>   self.file_obj = open(file_name, method)

> def __enter__(self):
>   print('MyfilerWriter started : __enter__')
>   return self.file_obj

> def __exit__(self, exc_type, value, trace_back):
>   print('FileWriter started : __exit__')
>   if exc_type:
>       print('Logging exception {}'.format(exc_type, value, trace_back))
>   self.file_obj.close()

> with FileWriter('testfile1.txt', 'w') as f:
>   f.write('Context Manager Text1.\nContextlib Test1')

FileWriter started : __init__
FileWriter started : __enter__
FileWriter started : __exit__

# 그리고 testfile3.txt가 생성된다.
```

<br>

---

# 5. Context manager: Measure execution

- Github에서 많이들 제작하는 timer다.
- 이 timer로 with문이 걸린 시간을 알 수 있다.

```yml
> class Timer(object):
>   def __init__(self,msg):
>       delf._msg = msg

>   def __enter__(self):

# 시간을 숫자형태로 가져와서, self의 start 변수에 저장한다.
>       self._start = time.monotonic()
>       return self._start

>   def __exit__(self, exc_type, exc_value, exc_traceback):
>       if exc_type:
>           print("Logging exception {}".format(exc_type, exc_value, exc_traceback))
>       else:
>           print('{} : {}s'.format(self._msg, time.monotonic() - self._start))
# with문을 잘 빠져나왔다는 의미
>       return True

> with Timer("Start!") as j:
>   print('Received start monotonic1 : {}'.format(v))

# self._start가 v에 연결된 걸 확인했다.
Received start monotonic1 : 590914.968

# else문이 실행된 결과
Start! job: 0.35999999998603016 s
```

<br>

---

# 6. Context manager: use 'decorator'

- contextlibary를 annotation을 사용하여 class 형태가 아닌 함수 형태로 구현해본다.
- 코드의 line을 줄일 수 있고, 직관적으로 코드를 작성할 수 있다.
- class로 context manager를 구현하는 것보다 확실히 간결하다.
- 하지만, '예외 처리'를 꼼꼼히 정석대로 할려면 class로 구현하는 게 낫다.

```yml
> import contextlib
> import time

# annotation
> @contextlib.contextmanger
> def writer(file_name, method):
>   f = open(file_name, method)

# __enter__
>   yield f

# __exit__
>   f.close()

# yield된 f가 with문의 alias f와 연결된다.
> with writer('testfile1.txt', 'w') as f:
>   f.write('Context manager Test4.\nContextlib test4')
```

- 마지막 예제로 decorator를 사용하여 timer를 만들어보자.

```yml
> @contextlib.contextmanager
> def Timer(msg):
>   start = time.monotonic()

# __enter__
>   try:
>       yield start
>   except BaseException as e:
>       print('Logging exception {} : {}'.format(msg, e))
>       raise

# __exit__
>   else:
>       print('{} : {} s'.format(msg, time.monotonic() - start))

> with Timer("Start!") as v:
>   print('Received start monotonic2 : {}'.format(v))

Received start monotonic2 : 594934.0
Start! : 0.42099999997299165 s
```

<br>

---

# Reference

- [모두를 위한 파이썬 : 필수 문법 배우기 Feat. 오픈소스 패키지 배포 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B3%A0%EA%B8%89/dashboard)
- [[TIL] Python basic 20: with open as](https://jeha00.github.io/post/python_basic/python_basic_20_filewriteread_1/)
- [[TIL] Python basic 21: csv.read, write](https://jeha00.github.io/post/python_basic/python_basic_21_filereadwrite_2/)
- [What is a "runtime context?"](https://stackoverflow.com/questions/19652662/what-is-a-runtime-context)
- [Python docs: Context Manager Types](https://docs.python.org/3/library/stdtypes.html?highlight=__enter__#context-manager-types)
