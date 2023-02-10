# 0. Introduction

> 1. [병행성과 병렬성이란??](#1-병행성과-병렬성이란)
> 2. [\_\_iter\_\_와 \_\_next\_\_](#2-iterator와-next)

<br>

- 병행성을 위한 방법으로 generator와 coroutine을 알아보고자 한다.
- 이번 포스팅에서는 \_\_iter\_\_와 \_\_next\_\_ 을, 다음 포스팅에서는 `generator`를 알아본다.
- 그러면 첫 번째로 병행성이 무엇을 의미하는 건지, 이와 유사한 개념으로 병렬성은 무엇인지 학습한다.
- 두 번째로, Generator를 이해하기 위해서 \_\_iter\_\_와 \_\_next\_\_ 을 먼저 학습한다.

---

# 1. 병행성과 병렬성이란??

> - **_동시성, 병행성(Concurrency): 하나의 core에서 하나 이상의 process(또는 thread)가 여러 실행 단위를 번갈아 실행하면서, 동시 진행되는 것처럼 보이는 것_**
> - **_병렬성(parallelism): 물리적으로 둘 이상의 코어를 실행해서 하나 이상의 prcess가 한 꺼번에 진행되는 것- > 속도 향상 목적_**

- **_병행성_** 은 단일 프로그램 안에서 여러 일을 쉽게 해결하기 위해 사용된다.
  - thread는 하나지만, 마치 동시에 일을 하고 있는 것처럼 수행한다.
  - 예) 공부하다가 강의 멈춰놓고, 밥 먹고 와서 강의를 중단한 부분부터 다시 시작하는 것
- 파이썬에서는 **_병행성_** 과 **_병렬성_**을 모두 지원한다.
- 그리고, 파이썬 실력을 결정하는 중요한 내용이다.

<br>

---

# 2. \_\_iter\_\_와 \_\_next\_\_

<br>

## 2.1 \_\_iter\_\_와 \_\_next\_\_ 용어 정리

> - \_\_iter\_\_
>   - \_\_iter\_\_: iter() built-in function이 호출하는 메소드
>   - iter(): 호출된 \_\_iter\_\_의 return 값을 반환한다.
>   - 즉, iterator는 \_\_iter\_\_ method가 만든다.
> - \_\_next\_\_
>   - \_\_next\_\_: next() built-in function이 호출하는 메소드

- **iter** 가 있는 용어들에 대해 정리하면서 알아보자.

<br>

### iteration이란?

- The process of looping through the objects or items in a collection

  - 그러면 파이썬에서 **iteration** 상황은 무엇이 있을까??

  - **Definite** iteration 상황: 미리 반복 횟수를 명백하게 정한 상황. ex) for 문
  - **Indefinite** iteration 상황: 몇 가지 조건이 만족될 때까지 code block을 실행하는 상황. ex) while 문

<br>


### iterable

- An object(or the adjective used to describe an object) that can be iterated over
- `dir()`로 확인했을 때, `__iter__`을 가지고 있는 객체

<br>

### iterator: Type의 한 종류

- `iter()`:  
  - `__iter__` method를 호출하여 이 method의 값을 반환하는 함수 
  - The built-in function used to obtain **an iterator** from **an iterable**

- 즉, **iterator**의 의미는

  - The object that produces successive items or values from its associated iterable
    - iterable object로부터 연속적인 값을 낳는(yield) 값 생성기
  - `iter()`이 반환하는 **객체의 type**  
  - `next()` function의 기준으로 보자면, `__next__`을 가지고 있는 객체

|  

<br>

## 2.2 Iterator 관련 data type

- iterator를 반환하는 data type들:
  - for, collections, string, list, dict, set, tuple, unpacking, \*args

  ```yml
  # string
  > iter('foobar')
  <str_iterator object at 0x036E2750>

  # list
  > iter(['foo', 'bar', 'baz'])
  <list_iterator object at 0x036E27D0>

  # tuple
  > iter(('foo', 'bar', 'baz'))
  <tuple_iterator object at 0x036E27F0>

  # set
  > iter({'foo', 'bar', 'baz'})
  <set_iterator object at 0x036DEA08>

  # dict
  > iter({'foo': 1, 'bar': 2, 'baz': 3})
  <dict_keyiterator object at 0x036DD990>
  ```

<br>

- iterator를 반환하지 않는 data type들
  - Integer, foat, built-in functions

  ```yml
  # Integer
  >> iter(42)
  Traceback (most recent call last):
    File "<pyshell#26>", line 1, in <module>
      iter(42)
  TypeError: 'int' object is not iterable

  # Float
  >>> iter(3.1)
  Traceback (most recent call last):
    File "<pyshell#27>", line 1, in <module>
      iter(3.1)
  TypeError: 'float' object is not iterable

  # Built-in functions
  >>> iter(len)
  Traceback (most recent call last):
    File "<pyshell#28>", line 1, in <module>
      iter(len)
  TypeError: 'builtin_function_or_method' object is not iterable
  ```

<br>

## 2.3 iter() 과 next() 예제

- 내장함수 `next()`는 값 생성기 iterator으로부터 다음 값을 얻기 위해 사용된다.

  ```yml
  # a는 iterable list다.
  > a = ['foo', 'bar', 'baz']

  # iter을 통해서 a의 iterator를 만든다.
  > itr = iter(a)
  > itr
  <list_iterator object at 0x031EFD10>

  # nest()를 통해서 itr 안의 다음 값을 얻는다.
  > next(itr)
  'foo'
  > next(itr)
  'bar'
  > next(itr)
  'baz'

  # 값이 다 소진되면 다음과 같은 Error를 띄운다.
  > next(itr)
  StopIteration
  ```

- 만약 iterator로 전환하지 않고, 그냥 사용한다면???
- iterator가 아니므로 `next()`를 사용할 수 없다.
- 즉 `__iter__`을 호출하여 전환해야 사용이 가능하다.

  ```yml
  > w = [1,2,3,4,5]

  # iter()로 iterator로 전환하지 않았기 때문에 __next__가 없다.
  > print(dir(w))
  ['__add__', '__class__',  '__iter__', ..... 'sort']

  > print(next(w))
  TypeError: 'list' object is not an iterator
  ```

<br>

## 2.4 iter() 과 next() 토대로 for문 이해하기

- 그러면 `__iter__` 과 `__next__`를 토대로 for 문을 이해해보자.

  ```yml
  > t = 'ABCDEF'
  > for c in t:
  >   print(c)
  A
  B
  C
  D
  E
  F
  ```

- 어떻게 해서 하나씩 출력되는 것일까???
- for문의 구조를 다시 확인해보자

  ```yml
  > for <var> in <iterable>:
  >     <statement(s)>
  ```

- in 다음에는 iterable이 온다. 그러면 어떻게 하나씩 반환될까??

  - `__iter__` 과 `__next__` 와 연결시켜보자.
  - iteralbe을 입력하면 for문에서 `iter()`을 호출하여 iterable객체를 `iterator`로 바꾼 후, `__next__` method를 통해 하나씩 출력된는 원리라는 걸 이해할 수 있다.

- 위에 for문을 while문으로 만들어서 구체적으로 이해해보자.

```yml
> t = 'ABCDEF'

> while True:
>   try:
>       print(next(t))
# iterator의 내부 성분이 다 출력되면 Error가 발생되고, 중단된다.
>   except Stopiteration
>       break
```

<br>

## 2.5 \_\_iter\_\_ 확인하는 방법

```yml
> t = 'ABCDEF'

# 첫 번째 방법
# 이 방법은 눈으로 찾아봐야하기 때문에, 추천하지 않는다.
> print(dir(t))
['__add__',  '__iter__', '__le__', ....']

# 두 번째 방법
# hasattr = has attribution
# t가 __iter__를 가지고 있는가??
> print(hasattr(t, '__iter_'))
True

# 세 번째 방법
> print(isinstance(t, abc.Iterable))
True
```

<br>

## 2.6 class로 \_\_next\_\_ 구현하기

```yml
> class WordSplitter:
>   def __init__(self, text):
>       self._idx = 0
>       self._text = text.split('')

>   def __next__(self):
>       print('Called __next__')
>       try:
>           word = self._text[self._idx]
>       except IndexError:
>           raise StopIteration('Stopped Iteration')
>       self._idx += 1
>       return word

>   def __repr__(self):
>       return 'WordSplit(%s)' % (self._text)


> wi = WordSplitter('Do today what you could do tomorrow')

> print(wi)
WordSplit(['Do', 'today', 'what', 'you', 'could', 'do', 'tomorrow'])

> print(next(wi))
Do
> print(next(wi))
today
> print(next(wi))
what
> print(next(wi))
you
> print(next(wi))
could
> print(next(wi))
do
> print(next(wi))
tomorrow
> print(next(wi))
Stopped Iteration
```

- 구현했지만, 코드량이 많아진다. 그러면 제네레이터를 사용해서 구현해보자.

```yml
> class WordSplitter:
>   def __init__(self, text):

# 인덱스를 기억하지 않아도 된다.
>       self._text = text.split('')

>   def __iter__(self):
>       for word in self._text:

# 이것이 제네레이터이며, 이 제네레이터가 위치 정보를 기억한다.
# 따로 예외처리를 하지 않아도 된다.
>           yield word
>       return

>   def __repr__(self):
>       return 'WordSplit(%s)' % (self._text)


> wi = WordSplitter('Do today what you could do tomorrow')

> wt = iter(wg)

# 출력 코드는 동일.
```

<br>

---

# Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [04-3 제너레이터와 yield(feat. return 문) (널널한 교수의 고급 파이썬) ft. 파이썬 코딩](https://www.youtube.com/watch?v=NjKqcQ_eIpA)
