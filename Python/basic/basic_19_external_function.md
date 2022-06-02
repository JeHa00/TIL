# Python basic 19: External functions (외장 함수)

- 여러 외장 함수들 중 sys, pickle, os, time, random, webbrowser 에 대해 예제 실습으로 알아보겠다.
- 특히 sys, os, time은 훨씬 자주 사용되므로 중요하다.
- 외장함수는 `import`를 하는 것부터 시작한다.
- 이 포스팅의 목적은 외장 함수에는 이런 것들이 있다는 기록하기 위해서다. 해당 포스팅으로는 각 외장 모듈에 대한 내용이 부족하니, 추가적인 학습을 하자.

## 1. sys

> **_파이썬 인터프리터가 제공하는 변수와 함수를 직접 제어할 수 있게 해주는 모듈_**

- `sys.argv`는 명령행에 인수를 전달하도록 하는 명령어다.

```yml
> import sys

## module 파일이 있는 위치들이 출력된다.
> print(sys.path)

## 강제 종료 함수다. 함부로 사용하지 않는다.
# visual studio code에서는 작동되지 않는다. 해당 언어 shell에서 작동한다.
> sys.exit()
```

---

<br>

## 2. pickle

> **_텍스트 상태의 데이터가 아닌 객체의 형태를 그대로 유지하면서, 파일에 저장하고 불러올 수 있게 하는 파이썬이 제공하는 모듈_**

- 파이썬 객체를 파일에 저장하는 과정을 피클링(pickling)이라 하고, 파일에서 객체를 읽어오는 과정을 언피클링(unpickling)이라 한다.
- `test.obj`라는 파일이 binary 형식으로 작성된다.
- 이 test.obj에 `pickle.dump()` 명령어로 `obj` 변수 내용을 저장한다.
- 그리고 나서, `pickle.load()` 명령어로 `test.obj` 파일을 읽는다.

```yml
> import pickle

# w: write , b: binary, r: read
> f = open('test.obj', 'wb')
> obj = {1: 'python', 2: 'study', 3: 'basic'}
> pickle.dump(obj, f)
# 열고 나서 반드시 닫아야 한다.
# 쓴 resource는 컴퓨터한테 반드시 반환해야 한다.
> f.close()

## binary file은 컴퓨터가 처리하는 파일 형식이다.
## 사람이 알아보기 힘든 상태로, txt 파일은 이 binary 파일을 사람이 읽기 쉽게 만든 파일 형식이다.
## 그러면 이걸 어떻게 열 수 있을까??

> f = open("test.obj", 'rb')
> data = pickle.load(f)
> print(data)
> f.close()

```

---

<br>

## 3. os

> **_환경 변수나 디렉터리, 파일 등의 OS 자원을 제어할 수 있게 해주는 모듈_**

```yml
> import os

# 사용자의 운영체제 정보를 파이썬에게 넘겨준다.
> print(os.environ)
environ({{'ALLUSERSPROFILE': 'C:\\ProgramData', 'APPDATA':  C:\\Users\\rudtl\\AppData\\Roaming', ....})


> print(os.environ['USERNAME'])
rudtl

```

---

<br>

## 4. time

> **_시간과 관련된 모듈_**

```yml
> import time

## 1970년 1월 1일 0시 0분 0초를 기준으로 지난 시간을 초 단위로 반환한다.
> print(time.time())
1646220813.7009172

## 하지만 위 방식은 알아보기가 힘들다.
# time.time이 반환한 시간을 사용하여 연도,월,일,시,분,초의 형태로 바꿔주는 함수다.
> print(time.localtime(time.time()))
time.struct_time(tm_year=2022, tm_mon=3, tm_mday=2, tm_hour=20, tm_min=41, tm_sec=0, tm_wday=2, tm_yday=61, tm_isdst=0)

## local.time 보다 더 간단히 표현하는 모듈이다.
> print(time.ctime())
Wed Mar  2 20:42:50 2022

## 원하는 형식으로 시간을 출력해주는 모듈이다.
# Year, Month, Day, hour , Minute, Second
> print(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time())))
2022-03-02 20:42:50


## 시간 간격 발생
# 출력 간 delay를 1초로 한다.
> for i in range(5):
>   print(i)
>   time.sleep(1)
0
1
2
3
4
```

---

<br>

## 5. random

> **_난수(규칙이 없는 임의의 수)를 발생시키는 모듈_**

```yml
> import random

## 0에서 1 사이의 아무 난수를 반환한다.
> print(random.random())


## 1에서 45 사이의 정수값을 랜덤으로 갖고 온다.
> print(random.randint(1, 45))

## 1에서 44 사이의 정수값을 랜덤으로 갖고 온다.
> print(random.randrange(1, 45))

## Iterable object의 argument의 순서를 섞어서 출력한다.
# 출력할 때마다 순서가 바뀐다.
> d = [1, 2, 3, 4, 5]
> random.shuffle(d)
> print(d)
[2, 5, 3, 4, 1]
[2, 4, 1, 3, 5]
...

## Iterable object의 argument들 중 무작위로 선택하는 함수
> d = [1, 2, 3, 4, 5]
> c = random.choice(d)
> print(c)
1
4
3
2

```

---

<br>

## 6. webbrowser

> **_본인 OS의 web browser를 실행_**

```yml
> import webbrowser

## 기본 웹 브라우저를 사용하여 아래 url로 자동으로 이동한다. 이미 실행된 상태라면 기존에 있던 tab이 이동된다.
> webbrowser.open("https://google.com")

## 기본 웹 브라우저에 새로운 탭이 생기면서 아래 url로 이동한다.
> webbrowser.open_new("https://google.com")


```

<br>

---

## Reference

- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [라이브러리](https://wikidocs.net/33)
- [pickling](https://www.youtube.com/watch?v=Z24atwS8TZ0)
