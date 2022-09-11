# 0. Introduction

> 1. [vi editor 개요](#1-vi-editor-개요)  
> 2. [실행 모드 실습](#2-실행-모드-실습)  
> [vim 들어가기](#vim-들어가기)  
> [set nu, set nonu](#set-nu-set-nonu)  
> [h, j, k, l 로 상하 좌우 움직이기](#h-j-k-l-로-상하-좌우-움직이기)  
> [행(줄) 단위 이동](#행줄-단위-이동)  
> [L, M, H 로 화면 단위 이동하기](#l-m-h-로-화면-단위-이동하기)  
> [W, E, B로 단어 단위 이동하기](#w-e-b로-단어-단위-이동하기)  
> [마크 이동: 책갈피 선정](#마크-이동-책갈피-선정)  
> [검색](#검색)    

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- 지난 소챕터에서는 linux의 기본적인 명령어에 대해서 학습했었다. 

- 이번에는 text 기반에서 사용할 수 있는 에디터인 **문서편집기 vi editor** 에 대해 학습한다.  
    - vi editor를 다루지 못하면 linux를 제대로 다룰 수 없다.

<br>

---


# 1. vi editor 개요

> **_Visual Editor의 약자로 과거에 사용하던 줄 단위의 라인 에디터가 불편하여 만들어진 텍스트 에디터_**

- 한번에 전체 파일을 화면에 출력이 가능

- 각종 문서를 편집할 수 있는 텍스트 기반의 편집기

- 명령 모드, 실행 모드, 입력 모드


- `Esc`, `Enter`: Ex mode -> 명령 모드
- `:`, `/`, `?`: 명령 모드 -> Ex mode

- `Esc`: 입력 모드 -> 명령 모드
- `i`, `a`, `o`, `s`: 명령 모드 -> 입력 모드

## 단축키 모음


![image](https://user-images.githubusercontent.com/78094972/188254248-eb1ac515-91f0-4ea8-8ce9-8270e4508f42.jpeg)

## 입력 모드

- 명령 모드 상태에서 [i]키나 [a], [o]등의 키를 누르면, 커서가 생기면서 입력 모드로 진입

- 자유롭게 코드나 텍스트 작성이 가능하며, 버퍼에 내용을 입력할 수 있는 모드

- [ESC] 키를 누르면, 다시 명령 모드로 전환

- [--INSERT--] or [--REPLACE--] 2가지가 존재
    

<br>

## 명령모드

> **_하고자 하는 명령을 하는 모드_**

- Esc 키를 먼저 누르는 게 선행되야 함

<br>

---

# 2. 실행 모드 실습


### 실습 환경 구성

- 시스템 파일을 사용하여 실습을 해볼 것이기 때문에, 별도의 폴더에다가 복사하자. 

- 왜냐하면 시스템 파일 직접 다루면 변경되면 문제가 생길 수 있기 때문이다.  

```yml
[root@ip-172-31-8-107 ec2-user]# 
[root@ip-172-31-8-107 ec2-user]# mkdir ./temp/
[root@ip-172-31-8-107 ec2-user]# cp /etc/login.defs ./temp/
[root@ip-172-31-8-107 ec2-user]# cd ./temp/
[root@ip-172-31-8-107 ec2-user]# ls -l
total 4
-rw-r--r-- 1 root root 2028 Sep  3 14:44 login.defs
```

<br>

### vim 들어가기

```yml
[root@ip-172-31-8-107 temp]# vi login.defs
```

- 위 명령어를 입력하면 vim에 들어가진다. 

<br>

### set nu, set nonu

```yml
...
# System accounts
SYS_GID_MIN               201
SYS_GID_MAX               999
```

- 콜론을 누르면 자동적으로 입력창이 뜬다. 

- 그래서 `:set nu`를 입력하면 아래와 같이 앞에 [Line Number] 가 활성화된다. 

- 비활성화는 `:set nonu`를 입력한다.


```yml
...
# System accounts
45 SYS_GID_MIN               201
46 SYS_GID_MAX               999
:set nu
```

<br>

### h, j, k, l 로 상하 좌우 움직이기

- vim 화면에 `:`이 떠 있다면 esc를 누른 후, h j k l 을 눌러서 커서를 상하좌우 움직여보자. 그러면 터미널 내에서 커서가 움직이고, 계속 밑으로 내리면 맨 끝 줄로 내려간다. 

- 또한, 화살표 키보드 버튼으로도 움직일 수 있다.

| Key | 동작 |
| ---- | ---- |
| h | 왼쪽 방향 화살표 키, 커서를 왼쪽으로 이동 |
| j | 아래쪽 방향 화살표 키, 커서를 아래로 이동 |
| k | 위쪽 방향 화살표 키, 커서를 위로 이동 |
| l | 오른쪽 방향 화살표 키, 커서를 오른쪽으로 이동 |

<br>


### 행(줄) 단위 이동 

- `gg`로 맨 위, `G`로 맨 아래로 커서 이동
    - 콜론도 입력하지 않은 상황에서 즉, '명령모드'에서 gg를 누르면 커서가 맨 위로 이동하며, G를 누르면 커서가 맨 아래로 이동한다. 

- `0`: Home 키로서, 행의 처음으로 이동  
- `$`: End 키로서, 행의 마지막으로 이동

<br>

### L, M, H 로 화면 단위 이동하기

- L: 화면 하단으로 이동 (맨 아래는 아니다.)
- M: 화면 가운데로 이동 (set nu로 가운데 확인)
- H: 화면 상단으로 이동 (맨 위는 아니다.)

<br>

### W, E, B로 단어 단위 이동하기

- `W(w)`: 다음 단어의 처음으로 이동 (Next word)
- `E(e)`: 다음 단어의 끝으로 이동 (End word)
- `B(b)`: 전 단어의 처음으로 이동(Prev word)

<br>

### 마크 이동: 책갈피 선정

> **_작업할 때 주로 사용_**

- 커서를 원하는 위치로 이동 후, 명령 모드 상태에서 `m`을 누르고, a~z, A~Z 중 원하는 알파벳 책갈피를 입력한다. 

- `gg`를 눌러 맨 앞으로 이동 후, `~` + `책갈피로 입력한 알파벳`을 누르면 마크한 위치로 바로 이동한다.

<br>

### 검색

| KEY | 동작 |
| ---- | ---- |
| /Pattern | 입력한 {Pattern}을 아래쪽으로 검색 |
| ?Pattern | 입력한 {Pattern}을 위 쪽으로 검색 |

- `n`: **_정방향_** 으로 {Pattern}을 계속 검색
- `N`: **_역방향_** 으로 {Pattern}을 계속 검색

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)