# Python basic 20: 파일 입출력 1

<br>

## Intro

> 1. [Read file (파일 읽기)](#1-read-file-파일-읽기)
> 2. [Write file (파일 쓰기)](#2-write-file-파일-쓰기)

<br>

- 이번 chapter에서는 외부에서 수집하거나 작성한 어떠한 text 파일, csv 파일, json 같은 다양한 형식의 외부 파일들을 읽고 쓰는 작업을 알아본다.

- 이번 chapter에서 중요한 것은
  - 첫 번째, 외부 resource를 파이썬에 `list` type으로 읽어와서 저장하고, `list` type으로 원하는 파일을 쓴다는 것
    - `readlines` 함수와 `writelines` 함수
  - 두 번째, 사용한 resource는 반드시 close를 해야 한다. 그래서 `with문`을 사용한다.
    - close를 하는 이유는 하지 않을 경우 다음 코드를 사용할 때 원활하지 않을 수 있다.

<br>

## 1. Read file (파일 읽기)

- 파일을 읽고 쓰는 작업을 하기 위해 `open` 이란 함수를 사용한다.
- 이 함수에서 사용하는 몇 가지 용어와 경로의 두 종류에 대해 알아보자.
  - r: 읽기모드 (read)
  - w: 쓰기모드 (write)
  - a: 추가모드 (append)
  - t: text 모드 (기본모드라 생략 가능)
  - b: binary 모드
  - 경로의 종류: 상대 경로와 절대 경로
    - 상대 경로(../, ./): 점 하나는 현재 위치를, 점 두 개는 상위 폴더를 의미한다.
    - 절대 경로: C:\Django\example..'
    - 다른 컴퓨터에 다운을 받을 때는 `절대 경로`보다 `상대 경로`가 맞다. 그 이유는 경로가 절대적으로 같지 않기 때문이다.

<br>

- 외부에 있는 파일을 읽을 때는 먼저 함수를 사용하여 연결한 후, 내용을 읽는다.
- Encoding 이란 사람의 언어를 컴퓨터 언어로 암호화하는 걸 의미한다.
  - Ecoding의 한 방식이 'UTF-8' 이다.
  - 원문이 무엇으로 인코딩되었는지를 알아야 파이썬으로 불러왔을 때 깨지지 않는다.
- 먼저 원하는 경로에 파일을 만들어놓은 후, 실습을 진행했다.

```yml

## open('경로', 'rt' 'rb' 'w' 'a' 'a', A)
# 1) 현재 경로를 기준으로 상대 경로로 입력한다.
# 2) rt이지만, t는 기본값이므로 r만 입력한다.
# 3) encoding 방식으로 UTF-8 사용
> f = open('./resource/it_news.txt', 'r', encoding = 'UTF-8')

## 인코딩 방식 확인
> print(f.encoding)
UTF - 8

## 파일 이름
> print(f.name)
./resource/it_news.txt

## 모드 확인
> print(f.mode)
r

## 외부 파일 읽은 후, 변수에 할당하기
> content = f.read()
> print(content)
Right now gamers can pay just $1 for access to hundreds of titles across PC
and Xbox via Microsoft Xbox Game Pass Ultimate service?but dont
activate that insanely cheap one-month trial just yet. You can lock in up to
three years of Xbox Game Pass Ultimate with that same dollar if you play
your cards right.


## 사용 후 반드시 close 한다.
> f.close()
```

- open하여 사용 후, 반드시 close를 해야한다.
- 하지만, `with 문`을 사용하면 `close`를 하지 않아도, 저절로 반환하기 때문에 `with`문을 사용하자.

```yml
> with open('./resource/it_news.txt', 'r', encoding = 'UTF-8') as f:
>   c = f.read()
>   print(c)

# it_news.txt 파일의 내용이 문자 하나 하나로 쪼개져서 list로 출력된다.
>   print(list(c))

```

- `read()` 함수에 intger type의 인자를 넣으면, 입력한 값만큼의 Byte를 읽는다. 그리고, 또 실행하면 이어서 읽는다. 왜냐하면 cursor가 움직이기 때문이다.
- `seek()` 함수를 사용하여 이 cursor의 위치를 초기화할 수 있다.
-

```yml
> with open('./resource/it_news.txt', 'r', encoding='UTF-8') as f:
>    c = f.read(20)
>    print(c)
Right now gamers can

# 다시 처음부터 20Byte를 읽어오는 것이 아니라, 전 마지막 읽은 부분부터 시작한다.
>    c = f.read(20)
>    print(c)
 pay just $1 for acc

# seek은 커서의 이동 위치를 말해준다. 0,0으로 이동하고 다시 20byte 만큼 읽겠다.
>   f.seek(0,0)

>    c = f.read(20)
>    print(c)
Right now gamers can

```

- **`readline` 함수를 사용하여 한 줄 씩 읽기**

```yml
> with open('./resource/it_news.txt', 'r', encoding='UTF-8') as f:

>    line = f.readline()
>    print(line)
Right now gamers can pay just $1 for access to hundreds of titles across PC

>    line = f.readline()
>    print(line)
and Xbox via Microsoft Xbox Game Pass Ultimate service?but dont
```

- 처음부터 다시 읽는 것이 아닌, 이어서 읽기 때문에 반복문을 통해서 처리하자.
- **`readlines` 함수를 사용한다. 전체를 읽은 후, 라인 단위 리스트로 저장한다. 즉, 파일을 list로 만든다.**
- list로 만들어 원하는 부분만 가져와 텍스트 처리를 할 수 있기 때문에, 반드시 알고 있어야 하는 함수다.

```yml
> with open('./resource/it_news.txt', 'r', encoding='UTF-8') as f:
>      cts = f.readlines()
>      for c in cts:
>        print(c, end='')
Right now gamers can pay just $1 for access to hundreds of titles across PC
and Xbox via Microsoft Xbox Game Pass Ultimate service?but dont
activate that insanely cheap one-month trial just yet. You can lock in up to
three years of Xbox Game Pass Ultimate with that same dollar if you play
your cards right.

```

<br>

---

<br>

## 2. Write file (파일 쓰기)

- 없는 파일을 쓰고자 할 때도 연결하기 위해 `open`함수를 사용한다.
- write이기 때문에 t 말고 w를 입력한다.

```yml
## contents1.txt 라는 파일 만들기
> with open('./resources/contents1.txt', 'w') as f:
>    f.write('I love python/n')

## 위 ./resources 경로로 contents1.txt 파일이 생성된다. 그 파일 안에 내용은 I love python이 있다.
```

- `a(append)` 를 사용하여 내용 추가하기

```yml
> with open('./resources/contents1.txt', 'a') as f:
>    f.write('I love python2\n')

## contents1.txt 파일 내용을 보면 다음과 같이 되어 있다.
I love python
I love python2

```

<br>

- `writelines` 함수를 사용하여 line list를 파일에 작성하기

<br>

```yml

> with open('./resource/content2.txt', 'w') as f:
>   list = ['Orange\n', 'Apple\n', 'Banana\n', 'Melon\n']
>   f.writelines(list)
Orange
Apple
Banana
Melon
```

- terminal이 아닌 파일로 출력을 해주는 방법

```yml
> with open('./resource/contents3.txt', 'w') as f:
>     print('Test Text Write!', file=f)
>     print('Test Text Write!', file=f)
>     print('Test Text Write!', file=f)

# contents3.txt 파일을 보면 다음과 같다.
Test Text Write!
Test Text Write!
Test Text Write!


# 위에 file = f 를 없애면 terminal로 출력된다.
```

---

<br>
