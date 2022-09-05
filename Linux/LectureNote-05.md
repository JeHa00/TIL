# 0. Introduction

> 1. [허가권(Permission)](#1-허가권permission)  
> 2. [소유권(Ownership)](#2-소유권ownership)  


- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 그룹 생성, 수정, 삭제에 관한 명령어를 실행해보았다. 

- 이번 챕터에서는 권한에 대해 학습한다. 
    - 허가권(Permission) 
    - 소유권(Ownership)

<br>

---
# 1. 허가권(Permission)

리눅스에서 허가권은 매우 중요한 역할을 하는데, `ls -l`을 해서 파일과 디렉토리 맨 앞에 오는 것이 **_허가권_** 을 의미한다.

```yml
[root@ip-172-31-8-107 /]# ls -l
total 20
lrwxrwxrwx   1 root root    7 Aug 16 05:20 bin -> usr/bin
dr-xr-xr-x   4 root root 4096 Aug 16 05:23 boot
drwxr-xr-x  15 root root 2900 Aug 30 13:09 dev
...
```

- 위 내용에서 `lrwxrwxrwx `, `dr-xr-xr-x `, `drwxr-xr-x` .. 가 허가권이다.

그러면 허가권에 대해서 보다 자세히 알아보자. 

- **허가권은 총 10자리다.** 
- **ex) drwxr-xr-x**
    - **맨 첫 글자: type of file**
        - 'd': directory
        - '-': file
        - 'l': link  
    - **맨 첫 글자 이외에는 다 rwx로 표시하는데 rwx 표시가 없으면 '할 수 없음'을 의미한다.** 
        - '-': can't
        - r or w or x: can read or write or execute  
    - **그 다음 세글자: User/Owner(_u_ )의 허가권**
        - 'r': can read    
        - 'w': can write    
        - 'x': can execute    
    - **그 다음 세글자: Group(_g_ )의 허가권**
        - 'r': can read
        - '-': can't write
        - 'x': can execute  
    - 그 다음 세글자: Other(**o** )의 허가권
        - 'r': can read
        - '-': can't write
        - 'x': can execute  


<br>


---
## 1.1 허가권 변경

> **_- 기본 기능: 파일이나 디렉토리의 허가권을 변경_**
> **_chmod [인자값] [대상 파일명 or 디렉토리] ... [대상 파일명 or 디렉토리]_**


- 인자값
    - 숫자[Numeric Method]
        - 내용: ex) chmod 644 test
            - 644 : rw-r--r--
    - 문자[Symbolic Method]
        - 내용
            - ex) chmod g+rx test
                - : rw-r--r-- => rw-r-wr- (654)
            - ex) -wx-w--wx: 323

<br>


---
## 1.2 File: 문서 or 실행 파일

| 권한 | 설명 |
| ---- | ---- |
| r 권한 | cat, more, vim, cp 등과 같은 파일의 내용을 읽기 |
| w 권한 | cat, echo, vim 등과 같은 파일의 내용을 수정, 변경 |
| x 권한 | 실행파일의 실행 |


<br>


---
## 1.3 Directory

| 권한 | 설명 |
| ---- | ---- |
|  r 권한| ls 등과 같은 디렉토리의 내부를 읽기 |
| w 권한| mkdir, touch, rm, mv 등과 같은 디렉토리 내부에 생성, 삭제 |
|x 권한 | **디렉토리의 접근** |

- x 권한: ex) drw-r-xr--

<br>


---
# 2. 소유권(Ownership)

<br>


---
## 2.1 소유권 변경

> **_- 기본 기능: 파일이나 디렉토리의 허가권을 변경_**
> **_chown [uid: gid] [대상 파일명 or 디렉토리] ... [대상 파일명 or 디렉토리]_**


- Example
    - chown testuser1 vimrc
    - chown testuser1:testuser1 vrc
    - chown testuser2:roott vrc

- Example
    - chown testuser1.root test
    - chown .root test
    - chown testuser1. test

<br>


---
## 2.2 그룹 소유권 변경


> **_기본 기능: 파이르이 소유 그룹을 변경_**
> **_chrgp [그룹명] [대상 파일명] ... [대상 파일명]_**

<br>


---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)