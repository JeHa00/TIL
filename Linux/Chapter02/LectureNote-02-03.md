# 0. Introduction

> 1. [cat](#31-cat)
> 2. [head](#32-head)
> 3. [tail](#33-tail)
> 4. [more](#34-more)
> 5. [less](#35-less)
> 6. [nl](#36-nl)
> 7. [파이프 사용하기](#37-파이프-사용하기)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- 지난 챕터에는 AWS를 사용한 Linux 명령어 학습 환경 조성을 하는데 목적을 두었다면, 이번에는 Linux 기본 명령어를 실습해보겠다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 이번 소챕터에서는 cat, head, tail, more를 학습해본다.  

    - cat는 전체 내용을 확인할 수 있으나, 너무 긴 내용일 경우 화면을 넘어가면 확인할 수 없다는 단점이 있다.

    - head와 tail은 전체 내용을 확인할 수 없고, head는 앞부분만 확인할 수 있고, tail은 끝 부분만 확인할 수 있다는 단점이 있어서 가운데 내용을 확인하기가 어렵다.  

    - more의 단점: 지나간 내용을 볼 수 없다.  

<br>

---

# 3. cat, head, tail, more

이번 챕터 내용은 더 많이 중요하다.  

<br>

## 3.1 cat

> **_기본 기능: 파일의 내용을 출력[보기]_**  
> 텍스트 파일의 내용을 표준 출력장치(모니터를 의미)로 출력하는 명령어

- 5가지 기능
    - 보기: 문서 파일 안을 보는 것   
    - 생성
    - 추가
    - 출력 생성: 생성된 내용에 추가한 내용을 합쳐서 출력  
    - 병합  

<br>

### 보기

```yml
[root@ip-172-31-8-107 ec2-user]# cat /etc/passwd
...
tcpdump:x:72:72::/:/sbin/nologin
ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
```

<br>

### 생성

- `cat` 로 생성된 파일은 `ls -al`로도 확인할 수 없다.

```yml
[root@ip-172-31-8-107 ec2-user]# cat /etc/passwd > /testfile
[root@ip-172-31-8-107 ec2-user]# ls -l /testfile
-rw-r--r-- 1 root root 1312 Sep  2 08:51 /testfile

# 아래 명령어 2개로 출력된 결과는 동일하다.
[root@ip-172-31-8-107 ec2-user]# cat /testfile more
[root@ip-172-31-8-107 ec2-user]# cat /etc/passwd
```

<br>

### 추가 

- 아무런 경로 없이 `cat > '생성될 파일 명칭'` 을 입력하면 내용을 입력하기 위해 아무것도 뜨지 않는다. 입력을 완료했으면 `fn + enter` 를 입력하여 종료한다.  

```yml
[root@ip-172-31-8-107 ec2-user]# cat > a 
Learningspoons
nanodegree

[root@ip-172-31-8-107 ec2-user]# ls -l
-rw-r--r--  1 root root   29 Sep  2 09:45 a
...

[root@ip-172-31-8-107 ec2-user]# cat a
Learningspoons
nanodegree
```

- 그러면 동일한 파일명으로 다시 추가하면, 내용이 달라지고 생성 시간과 크기도 달라졌다는걸 알 수 있다.

```yml
[root@ip-172-31-8-107 ec2-user]# cat > a 
Learningspoons: nanodegree
Python & Django backend course
 
# 까지 입력 후 fn + enter를 입력

[root@ip-172-31-8-107 ec2-user]# cat a
learningspoons: nanodegree
Python & Django backend course

[root@ip-172-31-8-107 ec2-user]# ls -l
...
total 20
-rw-r--r--  1 root root   58 Sep  2 09:54 a
```

❗️❗️ 만약 리눅스의 한 시스템과 동일한 명칭의 파일을 위 명령어로 추가한다면 리눅스가 작동하지 않아, 다시 깔아야할 일이 발생하므로 매우 조심해야 한다.

<br>

### 출력 생성

> 기존 내용에 추가하여 출력하기: cat >> <기존에 있던 파일명>

```yml
[root@ip-172-31-8-107 ec2-user]# cat >> a
jeha00 trainee majoring aerospace and mechanical engineering

[root@ip-172-31-8-107 ec2-user]# cat a
learningspoons: nanodegree
Python & Django backend course
jeha00 trainee majoring aerospace and machanical engineering
```

<br>

### 병합

> **_다른 파일과 합치기_**

- 다른 파일과 합치기 위해서 2개의 파일을 준비하자. 
    - 그래서 위의 코드에서 만든 `a`를 이어서 사용한다.

```yml
# b를 생성
[root@ip-172-31-8-107 ec2-user]# cat > b
I'm on boarding of this bootcamp

[root@ip-172-31-8-107 ec2-user]# ls -l
total 20
-rw-r--r--  1 root root  119 Sep  2 14:48 a
-rw-r--r--  1 root root   34 Sep  2 14:54 b
...

[root@ip-172-31-8-107 ec2-user]# cat a b > c
[root@ip-172-31-8-107 ec2-user]# ls -l
total 24
-rw-r--r--  1 root root  119 Sep  2 14:48 a
-rw-r--r--  1 root root   34 Sep  2 14:54 b
-rw-r--r--  1 root root  153 Sep  2 14:58 c


[root@ip-172-31-8-107 ec2-user]# cat c
learningspoons: nanodegree
Python & Django backend course
jeha00 trainee majoring aerospace and machanical engineering
I'm on boarding of this bootcamp
```

- 위 c를 보면 a와 b의 크기가 합쳐진 값임을 알 수 있다.  

- 반대로 `cat b a > d`를 하면 입력된 내용이 반대인 d file이 생성된다.

<br>

## 3.2 head

> **_기본 기능: 파일의 내용 중 위에서부터 아래로 10줄 출력_**  
> **_head [-n] [파일명]_**

- n의 수 만큼 줄을 읽는다.

```yml
# 위에 cat은 다 읽었지만, head를 써보면 다르다.
# 위에 언급한 대로 위에서부터 10줄을 읽었다.

[root@ip-172-31-8-107 ec2-user]# head /etc/passwd
root:x:0:0:root:/root:/bin/bash
bin:x:1:1:bin:/bin:/sbin/nologin
daemon:x:2:2:daemon:/sbin:/sbin/nologin
adm:x:3:4:adm:/var/adm:/sbin/nologin
lp:x:4:7:lp:/var/spool/lpd:/sbin/nologin
sync:x:5:0:sync:/sbin:/bin/sync
shutdown:x:6:0:shutdown:/sbin:/sbin/shutdown
halt:x:7:0:halt:/sbin:/sbin/halt
mail:x:8:12:mail:/var/spool/mail:/sbin/nologin
operator:x:11:0:operator:/root:/sbin/nologin
```

- 이번에는 위에서부터 5줄만 읽어보자.

```yml
[root@ip-172-31-8-107 ec2-user]# head -5 /etc/passwd
root:x:0:0:root:/root:/bin/bash
bin:x:1:1:bin:/bin:/sbin/nologin
daemon:x:2:2:daemon:/sbin:/sbin/nologin
adm:x:3:4:adm:/var/adm:/sbin/nologin
lp:x:4:7:lp:/var/spool/lpd:/sbin/nologin
```


<br>

## 3.3 tail

> **_기본 기능: 파일의 내용 중 아래(마지막)에서부터 위로 10줄 출력_**  
> **_tail [-n] [파일명]_**

- n의 수 만큼 줄을 읽는다. 하지만 기본적으로 n을 입력하지 않고, 파일명만 입력하면 10줄을 읽는다.  

```yml
# 마지막에서부터 10줄임을 알 수 있다.

[root@ip-172-31-8-107 ec2-user]# tail /etc/passwd
libstoragemgmt:x:999:997:daemon account for libstoragemgmt:/var/run/lsm:/sbin/nologin
sshd:x:74:74:Privilege-separated SSH:/var/empty/sshd:/sbin/nologin
rpcuser:x:29:29:RPC Service User:/var/lib/nfs:/sbin/nologin
nfsnobody:x:65534:65534:Anonymous NFS User:/var/lib/nfs:/sbin/nologin
rngd:x:998:996:Random Number Generator Daemon:/var/lib/rngd:/sbin/nologin
ec2-instance-connect:x:997:995::/home/ec2-instance-connect:/sbin/nologin
postfix:x:89:89::/var/spool/postfix:/sbin/nologin
chrony:x:996:994::/var/lib/chrony:/sbin/nologin
tcpdump:x:72:72::/:/sbin/nologin
ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
```

- 이번에는 아래에서부터 5줄만 읽어보자.

```yml
[root@ip-172-31-8-107 ec2-user]# tail -5 /etc/passwd
ec2-instance-connect:x:997:995::/home/ec2-instance-connect:/sbin/nologin
postfix:x:89:89::/var/spool/postfix:/sbin/nologin
chrony:x:996:994::/var/lib/chrony:/sbin/nologin
tcpdump:x:72:72::/:/sbin/nologin
ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
```

<br>

## 3.4 more

> **_기본 기능: 내용이 많은 파일을 화면 단위로 끊어서 출력_**  
> **_more [파일명]_**  

- 출력장치(모니터)의 높이만큼 출력한다.
    - 엔터를 누르면 한 줄씩 보여준다. 
    - 스페이스를 누르면 한 화면씩 넘어가준다.

- 실습을 위해 터미널 화면의 세로 폭을 반으로 줄여보자.  

    ```yml
    [root@ip-172-31-8-107 ec2-user]# more ./etc/passwd
    ```
- 하지만, 단점으로 지나간 내용은 볼 수 없다. 이럴 경우, 마우스 휠을 사용해야 한다.


<br>

## 3.5 less

> **_more의 단점을 해결한 명령어로 Vim 모드처럼 j와 k로 볼 수 있다._**

```yml
[root@ip-172-31-8-107 ec2-user]# less ./etc/passwd
```

- 위 명령어대로 입력한 후, enter를 누르면 j와 k로 움직이도록 변한다.  
- `q` 를 사용하여 나간다.


<br>

## 3.6 nl

> **_cat처럼 보여주지만, 각 줄에 넘버가 붙여져 출력된다._**

```yml
[root@ip-172-31-8-107 ec2-user]# nl ./etc/passwd
...
22	ec2-instance-connect:x:997:995::/home/ec2-instance-connect:/sbin/nologin
23	postfix:x:89:89::/var/spool/postfix:/sbin/nologin
24	chrony:x:996:994::/var/lib/chrony:/sbin/nologin
25	tcpdump:x:72:72::/:/sbin/nologin
26	ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
```

## 3.7 파이프 사용하기

> **_두 개의 명령어를 `|` 파이프라인을 사용하여 함께 사용한다._**

❗️ 파이프라인 앞에 명령어가 뒤 명령어에 영향을 미친다는 걸 알고 있어야 한다.

### nl 과 less 함께 사용하기

```yml
[root@ip-172-31-8-107 ec2-user]# nl ./etc/passwd | less
...
    20  nfsnobody:x:65534:65534:Anonymous NFS User:/var/lib/nfs:/sbin/nologin
    21  rngd:x:998:996:Random Number Generator Daemon:/var/lib/rngd:/sbin/nologin
:
```

### more을 부가적인 명령어로 사용하기

- 만약 메인 명령어로 사용한다면, 뒤에 파일이 와야 한다.

```yml
[root@ip-172-31-8-107 ec2-user]# ls -l /etc/ | more
...
-rw-------  1 root root      541 Jan 16  2020 anacrontab
-rw-r--r--  1 root root        1 Jan 16  2020 at.deny
drwxr-x---  3 root root       43 Aug 15 20:22 audisp
drwxr-x---  3 root root       83 Aug 30 04:09 audit
drwxr-xr-x  2 root root       94 Aug 15 20:22 bash_completion.d
-rw-r--r--  1 root root     2853 Feb 21  2020 bashrc
--More--
```



<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)