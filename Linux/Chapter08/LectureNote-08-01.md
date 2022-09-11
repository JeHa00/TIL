# 0. Introduction

> 1. [tar](#1-tar)  
> 2. [gzip / bzip2](#2-gzip--bzip2)  
> [practice](#practice)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 프로세스 관리에 대해 학습했다.

- 이번 챕터에서는 데이터 저장과 패키지에 대해 학습해보자.
    
<br>

---
# 1. tar

> **_기본 기능: 시스템 안에 있는 모든 데이터들을 묶음 형태로 보관(파일 형식)_**    
> tar [인자값] [묶음 파일명: Archiving File Name.tar] [묶음 대상]    

- gz, bz2와 함께 자주 사용한다. 

| 옵션 | 내용 |
| ---- | ---- |
|-c|Create, tar 아카이브 생성|
|-x|eXtract, tar 아카이브에서 파일 추출|
|-t|lisT, tar 아카이브에 포함된 내용 확인|
|-f|File, 대상 tar 아카이브 지정 (기본 옵션)|
|-p|Permission, 파일권한을 보존|
|-v|Verbose, 아카이브 파일을 묶어나 풀 때 과정을 보여줌|
|-k|Keep, tar 아카이브 추출 시, 기존 파일 유지하고 처리|
|-z|tar + gzip => *.tar.gz|
|-j|tar + bzip2 => *.tar.bz2|
|-J|tar + xz => *.tar.xz|

- 압축

```yml
# 뒤에 있는 디렉토리를 앞에 있는 파일로 묶어서 생성하라는 의미
tar cvf dalkom.tar /home/dalkom/

# 압축까지 원하고, gz로 압축을 원한다면 z를 사용해야 한다.
tar cvzf dalkom.tar.gz /home/dalkom/

# bz2를 원한다면 j를 사용
tar cvjf dalkom.tar.bz2 /home/dalkom/

# xz를 원한다면 J를 사용
tar cvJf dalkom.tar.xz /home/dalkom/
```

- 해제: 압축 해제는 `x`를 사용

```yml
tar xvf dalkom.tar

tar xvzf dalkom.tar.gz

tar xvjf dalkom.tar.bz2

tar xvJf dalkom.tar.xz
```

<br>

---
# 2. gzip / bzip2

### 압축하기
> **_gzip / bzip2 기본 기능: 시스템 안에 있는 모든 데이터들을 압축_**  

- gzip: `gz` 확장자명으로 압축  
- bzip2: `bz2` 확장자명으로 압축

- bz2가 gz를 개선하여 나온 것이다. 

<br>

### 압축 해제하기- 방법 1

> **_옵션 -d를 사용하여 압축 해제하기_**

| 옵션 | 내용 |
|----| ----|
|-d| Decompress, 압축해제|


```yml
gzip -d [압축 파일명]
bzip2 -d [압축 파일명]
```

<br>

### 압축 해제하기- 방법2

> **_gunzip과 bunzip 사용하기_**

- 이 방법은 AWS linux에서는 지원하지 않는 방법이다.

```yml
gunzip [압축 파일명: *.gz]
bunzip2 [압축 파일명: *.bz2]
```

<br>

---
# practice

## 실습 사전 준비

```yml
[root@ip-172-31-8-107 ~]# mkdir /temp/

# /temp/ directory에 앞에 경로 3가지에 있는 것들을 복사해온다.  
[root@ip-172-31-8-107 ~]# cp /etc/inittab /etc/login.defs /etc/crontab /temp/

# /temp/ 밑에 있는 것을 ec2-user로 옮긴다.
[root@ip-172-31-8-107 ~]# cp /temp/* /export/home/ec2-user

[root@ip-172-31-8-107 ~]# cd /export/home/ec2-user
[root@ip-172-31-8-107 ec2-user]# ls -l
total 12
-rw-r--r-- 1 root root    0 Sep  7 17:36 b
-rw-r--r-- 1 root root  451 Sep 11 13:28 crontab
drwxr-xr-x 2 root root    6 Sep  7 17:36 html
-rw-r--r-- 1 root root  511 Sep 11 13:28 inittab
-rw-r--r-- 1 root root 2028 Sep 11 13:28 login.defs
```


<br>


## gzip, bz2 명령어


<br>

### 압축하기

- 파일 개별로 압축하기
    - 압축 전 후 용량 비교해보기

```yml
# 압축 전이랑 비교하면 inittab은 511 -> 349로 압축
# crontab은 451 -> 259로 압축
[root@ip-172-31-8-107 ec2-user]# gzip crontab
[root@ip-172-31-8-107 ec2-user]# bzip2 inittab
[root@ip-172-31-8-107 ec2-user]# ls -l
total 12
-rw-r--r-- 1 root root    0 Sep  7 17:36 b
-rw-r--r-- 1 root root  291 Sep 11 13:28 crontab.gz
drwxr-xr-x 2 root root    6 Sep  7 17:36 html
-rw-r--r-- 1 root root  349 Sep 11 13:28 inittab.bz2
-rw-r--r-- 1 root root 2028 Sep 11 13:28 login.defs
```

<br>

### 압축 해제하기

```yml
# 압축 해제하기
[root@ip-172-31-8-107 ec2-user]# gzip -d crontab
[root@ip-172-31-8-107 ec2-user]# bzip2 -d inittab.bz2

[root@ip-172-31-8-107 ec2-user]# ls -l
total 12
-rw-r--r-- 1 root root    0 Sep  7 17:36 b
-rw-r--r-- 1 root root  451 Sep 11 13:28 crontab
drwxr-xr-x 2 root root    6 Sep  7 17:36 html
-rw-r--r-- 1 root root  511 Sep 11 13:28 inittab
-rw-r--r-- 1 root root 2028 Sep 11 13:28 login.defs

# 한 번에 동일 파일 형식 해제하기
[root@ip-172-31-8-107 ec2-user]# gzip -d *.gz
[root@ip-172-31-8-107 ec2-user]# ls -l
total 12
-rw-r--r-- 1 root root    0 Sep  7 17:36 b
-rw-r--r-- 1 root root  451 Sep 11 13:28 crontab
drwxr-xr-x 2 root root    6 Sep  7 17:36 html
-rw-r--r-- 1 root root  511 Sep 11 13:28 inittab
-rw-r--r-- 1 root root 2028 Sep 11 13:28 login.defs
```

### 다중 압축 x

- gzip은 여러 파일을 모아서 압축할 수 없다.

```yml
# 여러 파일 한 번에 압축 x: 다중 압축 x 
# bz2 도 동일
[root@ip-172-31-8-107 ec2-user]# gzip crontab inittab login.defs
[root@ip-172-31-8-107 ec2-user]# ls -l
total 12
-rw-r--r-- 1 root root    0 Sep  7 17:36 b
-rw-r--r-- 1 root root  291 Sep 11 13:28 crontab.gz
drwxr-xr-x 2 root root    6 Sep  7 17:36 html
-rw-r--r-- 1 root root  316 Sep 11 13:28 inittab.gz
-rw-r--r-- 1 root root 1025 Sep 11 13:28 login.defs.gz
```

<br>

## tar 명령어 사용하기

### 여러 파일 한 파일로 압축하기

```yml
[root@ip-172-31-8-107 ec2-user]# tar cvf dalkom.tar crontab inittab login.defs
crontab
inittab
login.defs
[root@ip-172-31-8-107 ec2-user]# ls -l
total 24
-rw-r--r-- 1 root root     0 Sep  7 17:36 b
-rw-r--r-- 1 root root   451 Sep 11 13:28 crontab
-rw-r--r-- 1 root root 10240 Sep 11 14:03 dalkom.tar
drwxr-xr-x 2 root root     6 Sep  7 17:36 html
-rw-r--r-- 1 root root   511 Sep 11 13:28 inittab
-rw-r--r-- 1 root root  2028 Sep 11 13:28 login.defs

[root@ip-172-31-8-107 ec2-user]# rm -rf crontab inittab login.defs
[root@ip-172-31-8-107 ec2-user]# ls -l
total 12
-rw-r--r-- 1 root root     0 Sep  7 17:36 b
-rw-r--r-- 1 root root 10240 Sep 11 14:03 dalkom.tar
drwxr-xr-x 2 root root     6 Sep  7 17:36 html
```

<br>

### 압축 파일을 다시 압축하기

```yml
[root@ip-172-31-8-107 ec2-user]# gzip dalkom.tar
[root@ip-172-31-8-107 ec2-user]# ls -l
total 4
-rw-r--r-- 1 root root    0 Sep  7 17:36 b
-rw-r--r-- 1 root root 1648 Sep 11 14:03 dalkom.tar.gz
drwxr-xr-x 2 root root    6 Sep  7 17:36 html
```

<br>

### 압축 파일 해제하기

```yml
[root@ip-172-31-8-107 ec2-user]# gzip -d dalkom.tar.gz
[root@ip-172-31-8-107 ec2-user]# ls -l
total 12
-rw-r--r-- 1 root root     0 Sep  7 17:36 b
-rw-r--r-- 1 root root 10240 Sep 11 14:03 dalkom.tar
drwxr-xr-x 2 root root     6 Sep  7 17:36 html

[root@ip-172-31-8-107 ec2-user]# tar xvf dalkom.tar
crontab
inittab
login.defs
```


<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)