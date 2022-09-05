# 0. Introduction

> 1. [사용자 계정 관리](#1-사용자-계정-관리)  
> 2. [usermod](#2-usermod)  
> 3. [userdel](#3-userdel)  
> 4. [passwd](#4-passwd)  
> [practice](#practice)  

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- 지난 소챕터에서는 text 기반에서 사용할 수 있는 에디터인 **문서편집기 vi editor** 에 대해 학습했다.

- 이번 챕터에서는 사용자 계정을 관리하는 법 아래의 소챕터 3개 중 두 번째를 학습한다. 
    - 사용자 계정 관리 및 생성  
    - 사용자 변경  
    - 그룹  

<br>

---
# 1. 사용자 계정 관리

> **_사용자 생성 시 설정 파일_**

| 파일 | 내용 |
| ---- | ---- |
| /etc/login.defs | 사용자 생성 시 부여되는 설정 값들을 미리 정리해놓은 파일 |
| /etc/default/useradd | 사용자 생성 시 사용되는 기본 설정 값이 저장된 설정 파일 |
| /etc/skel |  환경설정 파일 |


<br>

---
# 2. usermod

> **_사용자 계정의 정보 변경_**

- `usermod [옵션] [인자 값] ... [계정명]`
    - 여기서 mod란 modification의 약어다. 


| 옵션 | 내용 |
| ---- | ---- |
|-i | 사용자 계정의 이름 변경 |
|-u | 사용자 계정의 UID를 변경 |
|-g | 사용자 계정의 기본 그룹 변경 |
|-G | 사용자 계정의 추가 그룹 지정 |
|-c | 사용자 계정의 설명 |
|-d | 사용자 계정의 Home directory 변경 |
|-s | 사용자 계정의 로그인 shell 변경 |

- 사용자 수정과 생성은 모든 게 다 동일하다. 

<br>

---
# 3. userdel

> **_사용자 계정을 삭제_**

- `userdel [옵션] [계정명]`

| 옵션 | 내용 |
| ---- | ---- |
|-f | 사용자 계정 강제 삭제|
|**-r** | 사용자 계정의 생성 시, 생성된 모든 정보를 함께 삭제 |
|-h | 도움말 표시 |

- 사용자 계정 삭제
    - 로그인 되어 사용 중인 계정 
    - 삭제하려는 계정의 그룹이 다른 계정의 기본 그룹으로 사용 

<br>

---
# 4. passwd

> **_사용자 password 관련 작업_**

- `passwd [옵션] [계정명]`

| 옵션 | 내용 |
| ---- | ---- |
|-d | 사용자 계정의 password를 삭제(Null로 변경) |
|❗️ **-l** | 사용자 계정의 password를 잠금 설정(Lock) |
|-u | 사용자 계정의 password를 잠금 해제(Unlock |
|-S |사용자 계정의 password 상태 출력 |
|--stdin | 사용자 계정의 password를 표준 출력장치로 입력받아서 생성 |



<br>

---
# practice

## 사용자 정보 수정: usermod
### 현재 사용자 계정 현황

- 현재 생성된 사용자 계정 현황은 다음과 같다. 

```yml
[root@ip-172-31-8-107 /]# tail /etc/passwd | nl
    1	ec2-instance-connect:x:997:995::/home/ec2-instance-connect:/sbin/nologin
    2	postfix:x:89:89::/var/spool/postfix:/sbin/nologin
    3	chrony:x:996:994::/var/lib/chrony:/sbin/nologin
    4	tcpdump:x:72:72::/:/sbin/nologin
    5	ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
    6	usertest1:x:1001:1001::/home/usertest1:/bin/bash
    7	usertest2:x:1002:1002:testuser:/home/usertest2:/bin/sh
    8	usertest3:x:1003:1003::/testhome/usertest3:/bin/tcsh
    9	usertest4:x:1004:1004::/home/usertest4:/bin/bash
    10	usertest5:x:1005:1005::/testhome/usertest5:/bin/bash
```

<br>


### 계정 상의 잘못된 정보 수정하기

- 여기서 잘못된 정보로 계정을 생성했을 경우, 수정하는 걸 해보자. 
    - `usertest5`를 수정해보자.  
    - 사용자 계정은 디렉토리와 파일 삭제하듯이 할 수 없다. 디렉토리라고 해도, 특별한 디렉토리이기 때문이다.

- 변경된 경로로 덮어씌우기를 시도해도 이미 생성되어 바뀌지 않는다. 

```yml
[root@ip-172-31-8-107 /]# useradd -d /home/usertest5 usertest5
useradd: user 'usertest5' already exists
```

- 그래서 `usermod`를 사용해보자.

🔆 해당 directory 경로에 있는 데이터들 중 특정 데이터에 관한 것만 보고 싶으면 파이프라인과 함께 사용하여 `grep`을 사용하자.  

```yml
[root@ip-172-31-8-107 /]# usermod -d /home/usertest5 usertest5

[root@ip-172-31-8-107 /]# cat /etc/passwd | grep usertest5
usertest5:x:1005:1005::/home/usertest5:/bin/bash

[root@ip-172-31-8-107 /]# ls -al /home/ /testhome/
/home/:
total 4
...
drwx------  2 usertest1 usertest1  62 Sep  3 22:53 usertest1
drwx------  2 usertest2 usertest2  62 Sep  3 23:02 usertest2
drwx------  2 usertest3 usertest3  76 Sep  4 02:18 usertest3
drwx------  2 usertest4 usertest4  76 Sep  4 00:02 usertest4

/testhome/:
total 0
drwxr-xr-x  4 root       root        40 Sep  4 00:57 .
dr-xr-xr-x 20 root       root       305 Sep  4 00:47 ..
drwx------  2 userteset3 userteset3  62 Sep  3 23:02 usertest3
drwx------  2 usertest5  usertest5   76 Sep  4 00:57 usertest5
```

<br>

### 계정 상 정보와 실제 home directory 정보 일치시키기

- `usermod`를 통해서 `/etc/passwd` 상의 내용은 수정했지만, 실제 directory 상에는 반영되지 않았다.
- 이를 `mv` 명령어를 통해서 해결하자.

❗️ 사용자 계정 상의 정보와 사용자 정보 상의 home directory 실제 정보는 반드시 일치해야 한다. 

```yml
[root@ip-172-31-8-107 /]# mv /testhome/usertest5 /home/
[root@ip-172-31-8-107 /]# ls -al /home/ /testhome/
/home/:
total 4
drwxr-xr-x  9 root      root      134 Sep  4 02:35 .
dr-xr-xr-x 20 root      root      305 Sep  4 00:47 ..
-rw-r--r--  1 root      root       16 Sep  2 14:21 adjtime
drwx------  4 ec2-user  ec2-user  107 Sep  3 14:41 ec2-user
drwxr-xr-x  2 root      root        6 Sep  2 14:31 test
drwx------  2 usertest1 usertest1  62 Sep  3 22:53 usertest1
drwx------  2 usertest2 usertest2  62 Sep  3 23:02 usertest2
drwx------  2 usertest3 usertest3  76 Sep  4 02:18 usertest3
drwx------  2 usertest4 usertest4  76 Sep  4 00:02 usertest4
drwx------  2 usertest5 usertest5  76 Sep  4 00:57 usertest5

/testhome/:
total 0
drwxr-xr-x  3 root       root        23 Sep  4 02:35 .
dr-xr-xr-x 20 root       root       305 Sep  4 00:47 ..
drwx------  2 userteset3 userteset3  62 Sep  3 23:02 usertest3
```

<br>

## 사용자 계정 삭제하기: userdel

- `-r` option을 사용하여 삭제할 경우, home directory에 남아있지 않는다.
- 하지만, 사용하지 않은 usertest1의 경우 Home directory에 남아있는 확인할 수 있다. 
    - 즉, 계정만 삭제된 것이다.  

🔆 계정만 삭제하고 디렉토리는 삭제하지 않은 이유는 디렉토리만 남아있다면 계정을 언제든지 복구할 수 있기 때문이다. 

```yml
[root@ip-172-31-8-107 /]# userdel usertest1
[root@ip-172-31-8-107 /]# userdel -r usertest2
[root@ip-172-31-8-107 /]# userdel -r userteset3
[root@ip-172-31-8-107 /]# userdel -r usertest3
[root@ip-172-31-8-107 /]# userdel -r usertest4
[root@ip-172-31-8-107 /]# userdel -r usertest5
[root@ip-172-31-8-107 /]# tail /etc/passwd
...
ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash

[root@ip-172-31-8-107 /]# ls -al /home/
total 4
drwxr-xr-x  7 root      root      100 Sep  4 02:48 .
dr-xr-xr-x 20 root      root      305 Sep  4 00:47 ..
-rw-r--r--  1 root      root       16 Sep  2 14:21 adjtime
drwx------  4 ec2-user  ec2-user  107 Sep  3 14:41 ec2-user
drwxr-xr-x  2 root      root        6 Sep  2 14:31 test
drwx------  2      1001      1001  62 Sep  3 22:53 usertest1
```

<br>

---
# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)