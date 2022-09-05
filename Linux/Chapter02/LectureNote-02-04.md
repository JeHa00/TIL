# 0. Introduction

> 1. [date 와 rdate](#41-date와-rdate)
> 2. [file](#43-file)
> 3. [find](#44-find)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- 지난 챕터에는 AWS를 사용한 Linux 명령어 학습 환경 조성을 하는데 목적을 두었다면, 이번에는 Linux 기본 명령어를 실습해보겠다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 이번 소챕터에서는 date, rdate, file, find를 학습해본다.  

<br>

---

# 4. date, rdate, file, find

<br>

## 4.1 date와 rdate

> **_타임 서버에서의 시간 정보를 시스템에 반영_**



### UTC를 KST로 전환하기

- UTC 를 KST로 전환하기

```yml
[root@ip-172-31-8-107 ec2-user]# data
Fri Sep  2 15:17:03 UTC 2022 

[root@ip-172-31-8-107 ec2-user]# sudo rm /etc/localtime
[root@ip-172-31-8-107 ec2-user]# sudo ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
[root@ip-172-31-8-107 ec2-user]# data
Sat Sep 3 01:00:31 KST 2022
```

### Linux 시간을 현재 시간으로 세팅하기 

- `date 월 일 시 분 년` 을 직접 입력하여 맞출 수 있으나, 정확히 맞히기 어렵다. 
    - ex) date 073115302022

- 그래서 아래 절차를 통해 일치시킨다.

- 타임 서버: 각 나라의 표준 시간을 관리하는 서버  

1. rdate 명령을 이용하여 타임 서버의 현재 시간을 화인  
    
    ```yml
    # -p 에서 P는 Present를 의미
    [root@ip-172-31-8-107 ec2-use]# rdate -p time.bora.net
    rdate: [time.bora.net]	Fri Sep  2 15:57:42 2022
    ```

2. rdate 명령을 이용하여 HOST 시간을 타임서버와 동기화  

    ```yml
    # -s 에서 s는 setting을 의미 
    [root@ip-172-31-8-107 ~]# rdate -s time.bora.net
    ```


<br>

## 4.3 file

> **_확장자를 기본으로 사용하지 않는 파일 종류 확인_**

1. file 명령어를 이용한 파일 유형[type] 확인  

```yml
# ls 명령어는 bin 폴더에 포함되어 있다. 
# 빈 디렉토리 속에 있는 ls 명령에 해당되는 파일의 유형을 확인하라는 의미
[root@ip-172-31-8-107 ec2-user]# file ./temp/
./temp/: directory
```

2. file 명령어를 이용한 디스크 filesystem 종류 확인  

```yml
[root@ip-172-31-8-107 ~]# file ./lib/
./lib/: symbolic link to 'usr/lib'
```

<br>

## 4.4 find

> ❗️ **_파일 및 디렉토리 검색_**

**1. 시스템에 있는 '이름'으로 검색**

- `find [경로] - name [파일명 or 디렉토리]`


```yml
[root@ip-172-31-8-107 ec2-user]# ls -l
total 0
drwxr-xr-x 2 root root 24 Sep  3 14:44 temp
[root@ip-172-31-8-107 ec2-user]# find ./temp 
./temp
./temp/login.defs
```

2. Access Time이 n일보다 작거나 큰 파일 및 디렉토리 검색  

- `find [경로] - atime -n(+n)`


3. test 파일 이후에 수정된 모든 파일들을 검색

    - `find /home/ -newer test`


4. 명령 수행

- `find -name [문자열] - exec [명령] {} \;`
    - `find.-name"test*" -exec rm {}\;`


5. root 권한으로 실행되는 파일

- `find . -user root -perm +4000 2> /dev/nul`




<br>

---
# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)