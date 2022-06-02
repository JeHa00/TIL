# Python basic 21: 파일 입출력 2

<br>

## 0. Introduction

> 1. [Read csv file (csv 파일 읽기)](#1-read-csv-file-csv-파일-읽기)
> 2. [Write csv file (csv 파일 쓰기)](#2-write-csv-file-csv-파일-쓰기)

<br>

- csv 파일이란??
  - `Comma-Separated Values`의 약자로, 콤마로 구분된 텍스트 파일이다.
  - 확장자 명은 `.csv`이다.
  - csv 파일은 data science 분야에서 주로 사용하여, 데이터를 주고 받을 때 사용하는 형식이다.
  - data science 분야나 전처리할 때, csv로 임시로 저장을 했다가 나중에 활용하는 형식으로도 많이 사용하고 있다.
- csv의 MIME 형식은 text/csv 이다.
  - MIME type이란 client에서 전송하는 문서의 종류를 표시하는 기능이다. 서버에서 이 type을 이용해서 각각 파일의 확장자를 확인할 수 있다.
- csv의 파일 1행을 열머리글로 `header`라고 한다.
- 콤마(,)로 반드시 구분되는 게 아니지만, 정석은 콤마다.

<br>

---

## 1. Read csv file (csv 파일 읽기)

- 두 개의 예제를 통해 읽기 실습을 해보겠다.
- 첫 번째 예제는 정석으로 콤마(,)로 구분되는 파일이다. (text1.csv)
- 두 번째 예제는 코마가 아닌 합기호로 구분되는 파일이다. (text2.csv)
- csv 파일을 읽기 위해서는 `csv.reader` 함수를 사용해야한다.
- csv.reader: Return a reader object which will iterate over lines in the given csvfile. csvfile can be any object which supports the iterator protocol.
- the iterator protocol을 지원하는지 알기 위해서 `dir()` 함수를 사용하여 `__iter__`이 있는지 확인한다.
- 그리고, 파이썬은 `list` 형태로 가져오고, 작성한다.

<br>

- 예제 파일 내용은 아래와 같다.

```yml
## text1.csv
# 콤마로 구분
Name,Code
Afghanistan,AF
Åland Islands,AX
Albania,AL
Algeria,DZ
American Samoa,AS
Andorra,AD



## text2.csv
# 합기호로 구분
Name|Code
Afghanistan|AF
Åland Islands|AX
Albania|AL
Algeria|DZ
American Samoa|AS
Andorra|AD
```

- 첫 번째 예제
- csv 파일의 첫 행 부분을 Header라고 한다. Header skip을 원한다면 `next()` 함수를 사용한다.
- `next()`: Return the next item from the iterator.
  - 괄호 안에 입력한 iterator의 두 번째 행부터 출력하겠단 의미다.
  - `seek()` 함수처럼 cursor를 이동한다.

```yml

# 외장 함수를 import 한다.
> import csv


# 별도로 close 하지 않기 위해 with open 함수를 사용한다.

# 현재 경로에서 resource 폴더에 있는 tes1.csv 파일을 읽고, f에 연결한다.
> with open('./resource/test1.csv', 'r') as f:
>   reader = csv.reader(f)

>   print(reader)
<_csv.reader object at 0x00000206CB2F5100>

>   print(type(reader))
<class '_csv.reader'>

## __iter__을 확인한다.
>   print(dir(reader))
['__class__', '__delattr__', '__dir__', '__doc__', ... '__iter__',...]

## __iter__이면 for문에도 사용할 수 있다.
# list 형식으로 가져온다는 걸 확인할 수 있다.
> for c in reader:
>    print(type(c))
>    print(c)
<class 'list'>
['Name', 'Code']
<class 'list'>
['Afghanistan', 'AF']
<class 'list'>
['횇land Islands', 'AX']
<class 'list'>
['Albania', 'AL']
<class 'list'>
['Algeria', 'DZ']
<class 'list'>
['American Samoa', 'AS']
<class 'list'>
['Andorra', 'AD']

## list를 string 형식으로 바꿔보자.
# 첫 번째 Name, Code가 헤더로, 열정보 다.
>   print(''.join(c))
NameCode
AfghanistanAF
횇land IslandsAX
AlbaniaAL
AlgeriaDZ
American SamoaAS
AndorraAD

## 헤더를 출력하고 싶지 않으면??
> with open('./resource/test1.csv', 'r') as f:
>     reader = csv.reader(f)
# with open 문 안에 아래 함수를 추가한다.
# Header Skip:  csv 파일의 첫 행 부분은 보통 헤더값이라 하며, 생략한다.
>     next(reader)

# 다시 실행시켜보자.

# NameCode가 없는 걸 확인할 수 있다.
>   print(''.join(c))
AfghanistanAF
횇land IslandsAX
AlbaniaAL
AlgeriaDZ
American SamoaAS
AndorraAD

```

- 두 번째 예제 (test2.csv)

```yml
> import csv

> with open('./resource/test2.csv', 'r') as f:
>     reader = csv.reader(f, delimiter = '|')
>     next(reader)
>     for c in reader:
>         print(c)
['Afghanistan', 'AF']
['횇land Islands', 'AX']
['Albania', 'AL']
['Algeria', 'DZ']
['American Samoa', 'AS']
['Andorra', 'AD']

## 만약 delimiter = '|' 를 입력하지 않는다면??
# name과 code가 하나의 값으로 인식된다.
['Afghanistan|AF']
['횇land Islands|AX']
['Albania|AL']
['Algeria|DZ']
['American Samoa|AS']
['Andorra|AD']
```

- `DictReader` 를 사용하여 test2.csv의 내용을 dictionary 형태로 formatting한다.

```yml
> import csv

> with open('./resource/test1.csv', 'r') as f:
>    reader = csv.DictReader(f)
>    print(reader)
<csv.DictReader object at 0x000001DD1A8A2F70>
>    print(type(reader))
<class 'csv.DictReader'>
>    print(dir(reader))
['__class__', '__delattr__', '__dict__', '__dir__', ...', '__iter__']

## __iter__ 확인 완료
>   for c in reader:
>       print(c)
{'Name': 'Afghanistan', 'Code': 'AF'}
{'Name': '횇land Islands', 'Code': 'AX'}
{'Name': 'Albania', 'Code': 'AL'}
{'Name': 'Algeria', 'Code': 'DZ'}
{'Name': 'American Samoa', 'Code': 'AS'}
{'Name': 'Andorra', 'Code': 'AD'}


## 위 내용을 Name과 Code로 나누고 싶으면??
>   for c in readers:
>       for k, v in c.items()
>           print(k)

Name
Code
Name
Code
Name
Code
Name
Code
Name
Code
Name
Code
>           print(v)

Afghanistan
AF
횇land Islands
AX
Albania
AL
Algeria
DZ
American Samoa
AS
Andorra
AD

>           print(k,v)

Name Afghanistan
Code AF
Name 횇land Islands
Code AX
Name Albania
Code AL
Name Algeria
Code DZ
Name American Samoa
Code AS
Name Andorra
Code AD
```

---

<br>

## 2. Write csv file (csv 파일 쓰기)

```yml
> import csv


> w = [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10, 11, 12], [13, 14, 15], [16, 17, 18], [19, 20, 21]]


> with open('./resoucre/write1.csv', 'w', encoding = 'utf-8') as f:
>   wt = csv.writer(f)
<class '_csv.writer'>
>   for v in w
>      # v를 wt에 작성한다. 하나의 list가 하나의 record가 된다.
>      wt.writerow(v)
1,2,3

4,5,6

7,8,9

10,11,12

13,14,15

16,17,18

19,20,21
```

- dict의 key 값을 field명으로 활용해서 써보기
- `.writeheader`: Write a row with the field names (as specified in the constructor) to the writer’s file object
- `.writerow`: Write the row parameter to the writer’s file object, formatted according to the current Dialect.

```yml
> import csv

> w = [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10, 11, 12], [13, 14, 15], [16, 17, 18], [19, 20, 21]]

> with open('./resource/write2.csv', 'w', encoding = 'utf - 8') as f:
>       # w의 list 성분이 3개의 성분을 가지고 있으므로, 3개를 입력
>       fields = ['one', 'two', 'three']
>       wt = csv.DictWriter(f, fieldnames = fields)
>       # header write
>       # header로 one, two, three가 작성된 상태다.
>       wt.writeheader()
>       for v in w:
>           wt.writerow({'one': v[0], 'two':v[1], 'three':v[2]})
one,two,three

1,2,3

4,5,6

7,8,9

10,11,12

13,14,15

16,17,18

19,20,21
```

<br>

---

## Reference

- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [CSV란??](<https://ko.wikipedia.org/wiki/CSV_(%ED%8C%8C%EC%9D%BC_%ED%98%95%EC%8B%9D)>)
