# 0. Introduction

> 1. [사용자 계정관리](#1-사용자-계정관리)  
> 2. [/etc/passwd](#2-etcpasswd)  
> 3. [/etc/shadow](#3-etcshadow)  
> 4. [/etc/group](#4-etcgroup)  
> 5. [사용자 계정 생성](#5-사용자-계정-생성)  
> 6. [useradd](#6-useradd)  
> [practice](#practice)  

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- 지난 소챕터에서는 text 기반에서 사용할 수 있는 에디터인 **문서편집기 vi editor** 에 대해 학습했다.

- 이번 챕터에서는 사용자 계정을 관리하는 법 아래의 소챕터 3개 중 첫 번째를 학습한다. 
    - 사용자 계정 관리 및 생성  
    - 사용자 변경  
    - 그룹  

- 학습한 이론을 바탕으로 실습을 할 때에는 사용자 정보를 '기본 경로에 생성하는 것' 과 '다른 경로에 생성하는 것' 총 두 가지를 해보겠다.
    - 이번 학습을 통해 사용자 정보를 내가 원하는 대로 만들 수 있다. 

<br>

---

# 1. 사용자 계정관리

> **_사용자 설정 파일_**


| 파일 | 내용 |
| ---- | ---- | 
| **/etc/passwd** | 사용자 계정의 정보가 저장된 파일|
| /etc/shadow | 사용자 계정의 중요 정보(실제 비밀번호)가 저장된 파일 |
| /home | 일반 사용자의 홈 디렉토리가 생성되는 Directory |
| /etc/group | 사용자 계정이 속한 그룹의 정보가 저장된 파일|
| /etc/login.defs | 사용자 생성 시 부여되는 설정 값들을 미리 정리해놓은 파일 |
| /etc/default/useradd | 사용자 생성 시 사용되는 기본 설정 값이 저장된 설정 파일 |
| /etc/skel |  환경설정 파일 |

- /etc/shadow는 다른 사용자들은 볼 권한 x


<br>

---

# 2. /etc/passwd 

> **_사용자 계정의 정보가 저장된 파일_**

- `User_ID : x: 500 : 500 :: /home/user_id : /bin/bash`


| 항목 | 내용 |
| ----| ----|
| User_ID | 사용자 계정명 |
|x | 사용자 계정에 부여된 패스워드 |
| 500 | User ID(UID) |
|500 | Group ID(GID) |
| NULL | 사용자 게정에 부여되는 임의의 정보 |
| /home/user_id | 사용자 계정의 Home Directory 경로|
| /bin/bash | 사용자 계정이 로그인 시 사용하는 Shell의 위치 |

- 사용자가 만들어질 때, 기본값은 500부터 만들어진다.
- 사용자가 정의를 잘못하여 Home directory 말고 다른 곳에 만들어지면 find 명령어를 통해 찾아야한다.


<br>

---

# 3. /etc/shadow 

> **_사용자 계정의 중요 정보가(실제 비밀번호)가 저장된 파일_**


- `User_ID : !! : 13479 : 0 : 99999 : 7 : : :`


| 항목 | 내용 |
| ---- | ---- |
|User_ID |사용자 계정명 |
| !! | 사용자 계정에 부여된 기본 암호(인증 불가) |
|13479 | 암호 생성일자|
| 0 | 암호를 변경할 수 있는 최소 일자| 
| 99999 | 부여된 암호를 변경 없이 사용할 수 있는 유효기간|
| 7 | 만료일 지정 시 만료 경고 일 수 |
|NULL |계정 만료 일자와 비활성화 일 수, 즉 무한대 |
|NULL | 계정의 만료일, 즉 무한대로 사용|

- 13479: 리눅스가 처음 카운트됬을 때부터 지금까지의 일자 수를 말한다.   

- 이 파일은 관리자만이 접근할 수 있고, 일반사용자에게는 절대로 오픈하면 안된다.

## 직접 확인해보기

```yml
[root@ip-172-31-8-107 ~]# cat /etc/shadow
...
ec2-user:!!:19234:0:99999:7:::
```

- 비밀번호는 난수로 확인되는데, 아직 비밀번호를 설정하지 않았기 때문에, `!!`로 확인된다.



<br>

---

# 4. /etc/group 

> **_사용자 계정이 속한 그룹의 정보가 저장된 파일_**

- `Group_ID : x : 500 : `


| 항목 | 내용 |
|---- | ---- | 
| Group_ID| 그룹의 이름|
| x|패스워드 |
|500 | |Group_ID(GID)|
|NULL |Group Member의 사용자 계정명 | 


<br>

---

# 5. 사용자 계정 생성

> **_사용자 생성 시 설정 파일_**

| 파일 | 내용 |
|---- | ---- | 
|/etc/login.defs | 사용자 생성 시 부여되는 설정값들을 미리 정해놓은 파일 |
| /etc/default/useradd |사용자 생성 시 생성되는 기본 설정 값이 저장된 설정 파일 |
| /etc/skel | 환경설정 파일|

- `/etc/login.defs`
    - 계정 별 패스워드 유효 기간
    - 계정 생성 시 자동으로 할당되는 UID 및 GID 범위

- `/etc/default/useradd`
    - /etc/passwd 파일에 생성되는 기본 값
    - SKEL Directory

- `/etc/skel`
    - Home directory를 생성하면서 기본적으로 생성할 기본이 되는 파일들이 존재하는 directory


<br>

---

# 6. useradd

> **_기본 기능: 사용자 계정을 새로 생성_**

### 사용자 계정 새로 생성

- `useradd [옵션] [옵션에 대한 값] [추가할 계정명]`
    - 여러 옵션과 값들을 추가해도 추가할 계정명은 앞으로 추가한다.  



| 옵션 | 내용 |
|---- | ---- | 
|-u |사용자 계정의 UID 정보를 임의로 지정 |
|-g |사용자 계정의 기본 그룹명 지정 |
|-c|사용자 계정의 설명 |
|**-d** |사용자 계정의 Home directory 변경 |
| -s| 사용자 계정의 로그인 Shell 변경|

- practice 명령어: `useradd -c testuser -s /bin/sh usertest2 `
    - `-c`를 통해서 계정 설명을 추가하는데, 기본은 [/etc/passwd](#2-etcpasswd)에서 설명한 것처럼 `NULL`이다. 
    - 이 명령어는 설명으로 testuser를 추가했다.
    - 또한, `-s`를 추가하여 사용자 계정의 로그인 Shell을 `/bin/sh`으로 옵션에 대한 값을 정했다.
    - 계정명은 `usertest2`다.  


- practice 명령어: `useradd -s /bin/tcsh -d /testhome/usertest3 usertest3`
    - `-d`를 사용하여 사용자 계정의 home directory 를 `testhome/usertest3`으로 정했다.  
    - `-s`를 사용하여 사용자 계정 로그인 shell를 `/bin/tcsh`로 정했다.  

<br>

### useradd -m -k [경로명]

- `useradd -m -k /userteset 사용자명` 
    - 위와 같이 입력하면 /usertest에 있는 내용을 다 복사하여 '사용자명'의 계정으로 생성된다.  


<br>

### 기본 경로 설정 수정하기

- `useradd -D [옵션] [인자값]`
    - 여기서 대문자 D는 default의 d다. 
    - 많이 사용하는 옵션은 -b 다. 


| 항목 | 내용 |
|---- | ---- | 
| **-b**| Home directory의 기본 생성 위치가 인자값으로 변경|
| -e|기본 만료 일자 변경 |
| -f|만료 일자에 해당하는 Inactive Days변경 |
| -g|기본 그룹이 [인자값]으로 변경 |
| -s|기본 로그인 Shell이 [인자값]으로 변경 |

<br>

---

# Practice

### /etc/passwd 에서 기본 계정 정보 확인하기

```yml
[root@ip-172-31-8-107 ~]# tail /etc/passwd 
...
chrony:x:996:994::/var/lib/chrony:/sbin/nologin
tcpdump:x:72:72::/:/sbin/nologin
ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
```

- 맨 아래 `ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash` 를 보자.
    - User_ID: ec2-user
    - x: password
    - UID, GID: 500, 500 
    - 사용자 계정의 Home Directory 경로: /home/ec2-user
    - 사용자 계정이 로그인 시 사용하는 Shell의 위치: /bin/bash


- 계정을 추가해보자. 
- 아래와 같이 추가로 한 줄이 생긴 걸 알 수 있다.

```yml
[root@ip-172-31-8-107 ~]# useradd userteset1
[root@ip-172-31-8-107 ~]# tail /etc/passwd
...
ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
usertest1:x:1001:1001::/home/usertest1:/bin/bash
```

- 하지만 사용자의 비밀번호 정보는 알 수 없다.

<br>

### 계정 다양한 방식으로 추가하기

- 계속해서 계정을 다양하게 추가해보자. 

```yml
[root@ip-172-31-8-107 ~]# useradd -c testuser -s /bin/sh usertest2 
[root@ip-172-31-8-107 ~]# mkdir /testhome
[root@ip-172-31-8-107 ~]# useradd -s /bin/tcsh -d /testhome/usertest3 usertest3
[root@ip-172-31-8-107 ~]# tail /etc/passwd
...
ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
usertest1:x:1001:1001::/home/usertest1:/bin/bash
# 아래 usertest2는 위 아래 testuser 1, 3과 달리 계정 설명이 있다. 
usertest2:x:1002:1002:testuser:/home/usertest2:/bin/sh
userteset3:x:1003:1003::/testhome/usertest3:/bin/tcsh
```

- 그래서 위 코드를 보면 usertest2와 usertest3이 추가된 것을 알 수 있다.  
- 이를 `ls -l`로 확인해보자.

```yml
[root@ip-172-31-8-107 ~]# ls -l /home/ /testhome/
/home/:
total 4
-rw-r--r-- 1 root      root       16 Sep  2 14:21 adjtime
drwx------ 4 ec2-user  ec2-user  107 Sep  3 14:41 ec2-user
drwxr-xr-x 2 root      root        6 Sep  2 14:31 test
drwx------ 2 usertest1 usertest1  62 Sep  3 22:53 usertest1
drwx------ 2 usertest2 usertest2  62 Sep  3 23:02 usertest2

/testhome/:
total 0
drwx------ 2 userteset3 userteset3 62 Sep  3 23:02 usertest3
```

- 확실하게 추가된 것을 알 수 있는데, 이를 통해서 **_원하는 경로에 사용자 계정을 추가할 수 있다는 걸 알 수 있다._**

<br>

### /home/에서 사용자 계정 정보 확인하기

```yml
[root@ip-172-31-8-107 ~]# useradd usertest4
[root@ip-172-31-8-107 ~]# ls -l /home/
total 4
-rw-r--r-- 1 root      root       16 Sep  2 14:21 adjtime
drwx------ 4 ec2-user  ec2-user  107 Sep  3 14:41 ec2-user
drwxr-xr-x 2 root      root        6 Sep  2 14:31 test
drwx------ 2 usertest1 usertest1  62 Sep  3 22:53 usertest1
drwx------ 2 usertest2 usertest2  62 Sep  3 23:02 usertest2
drwx------ 2 usertest4 usertest4  76 Sep  4 00:02 usertest4
```

<br>


### 계정 생성 시, /etc/skel/ 파일 복사 확인

- 새로운 계정이 생성되면 기본적으로  `/etc/skel` 안에 있는 파일을 복사해서 가져온다.

```yml
[root@ip-172-31-8-107 ~]# ls -al /home/usertest4 /etc/skel/
/etc/skel/:
total 24
drwxr-xr-x  2 root root   76 Sep  4 00:02 .
drwxr-xr-x 80 root root 8192 Sep  4 00:02 ..
-rw-r--r--  1 root root   18 Jul 15  2020 .bash_logout
-rw-r--r--  1 root root  193 Jul 15  2020 .bash_profile
-rw-r--r--  1 root root  231 Jul 15  2020 .bashrc
-rw-r--r--  1 root root    0 Sep  4 00:02 dalkom

/home/usertest4:
total 12
drwx------ 2 usertest4 usertest4  76 Sep  4 00:02 .
drwxr-xr-x 7 root      root      100 Sep  4 00:02 ..
-rw-r--r-- 1 usertest4 usertest4  18 Jul 15  2020 .bash_logout
-rw-r--r-- 1 usertest4 usertest4 193 Jul 15  2020 .bash_profile
-rw-r--r-- 1 usertest4 usertest4 231 Jul 15  2020 .bashrc
-rw-r--r-- 1 usertest4 usertest4   0 Sep  4 00:02 dalkom
```

<br>

### useradd -m -k [directory 경로] [사용자명]

- 그러면 이번에는 `/etc/skel`이 아닌 다른 경로에 있는 것을 복사해서 새로운 계정을 생성해보겠다. 

```yml
# /etc/skel/ 에 있는 파일을 다른 경로의 directory로 복사해오자.
[root@ip-172-31-8-107 ~]# mkdir /usertest/
[root@ip-172-31-8-107 ~]# cp /etc/skel/* /etc/skel.* /usertest/
cp: omitting directory '/etc/skel/.'
cp: omitting directory '/etc/skel/..'

# 위와 같은 error가 발생했을지라도 아래 명령어를 통해 확인해보면 복사되었다는걸 알 수 있다. 
[root@ip-172-31-8-107 ~]# ls -al /usertest/
total 12
drwxr-xr-x  2 root root  76 Sep  4 00:48 .
dr-xr-xr-x 20 root root 305 Sep  4 00:47 ..
-rw-r--r--  1 root root  18 Sep  4 00:48 .bash_logout
-rw-r--r--  1 root root 193 Sep  4 00:48 .bash_profile
-rw-r--r--  1 root root 231 Sep  4 00:48 .bashrc
-rw-r--r--  1 root root   0 Sep  4 00:48 dalkom

# 즉 디렉토리는 빼고 그 안에 파일들은 복사되었다는 의미다.
```

- 다음 스텝으로 진행해보자.

```yml
[root@ip-172-31-8-107 /]# useradd -m -k /usertest/ -d /testhome/usertest5 usertest5 
[root@ip-172-31-8-107 /]# tail /etc/passwd
...
ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
usertest1:x:1001:1001::/home/usertest1:/bin/bash
usertest2:x:1002:1002:testuser:/home/usertest2:/bin/sh
userteset3:x:1003:1003::/testhome/usertest3:/bin/tcsh
usertest4:x:1004:1004::/home/usertest4:/bin/bash
usertest5:x:1005:1005::/testhome/usertest5:/bin/bash
```

- 맨 마지막 줄을 보면 home directory 경로가 바뀐 걸 알 수 있다. 이제 내부 파일을 확인해보자. 
- 밑에 확인해보면 동일하다는 걸 알 수 있다. 

```yml
[root@ip-172-31-8-107 /]# ls -al /usertest/ /testhome/usertest5/
/testhome/usertest5:
total 12
drwx------ 2 usertest5 usertest5  76 Sep  4 00:57 .
drwxr-xr-x 4 root      root       40 Sep  4 00:57 ..
-rw-r--r-- 1 usertest5 usertest5  18 Sep  4 00:48 .bash_logout
-rw-r--r-- 1 usertest5 usertest5 193 Sep  4 00:48 .bash_profile
-rw-r--r-- 1 usertest5 usertest5 231 Sep  4 00:48 .bashrc
-rw-r--r-- 1 usertest5 usertest5   0 Sep  4 00:48 dalkom

/usertest/:
total 12
drwxr-xr-x  2 root root  76 Sep  4 00:48 .
dr-xr-xr-x 20 root root 305 Sep  4 00:47 ..
-rw-r--r--  1 root root  18 Sep  4 00:48 .bash_logout
-rw-r--r--  1 root root 193 Sep  4 00:48 .bash_profile
-rw-r--r--  1 root root 231 Sep  4 00:48 .bashrc
-rw-r--r--  1 root root   0 Sep  4 00:48 dalkom
```

<br>

### 기본 경로 설정 바꾸기

- `useradd -D`를 사용하여 기본 경로 설정을 바꿔보자. 

- 그렇다면 먼저 알아볼 것은 계정이 생성될 때마다 생성되는 기본값은 어느 경로에 위치할까? 
    - 위에 이론에서 학습했다시피 `/etc/default/useradd`에 존재한다.  

```yml
[root@ip-172-31-8-107 /]# cat /etc/default/useradd
# useradd defaults file
GROUP=100
HOME=/home
INACTIVE=-1
EXPIRE=
SHELL=/bin/bash
SKEL=/etc/skel
CREATE_MAIL_SPOOL=yes

[root@ip-172-31-8-107 /]# useradd -D
GROUP=100
HOME=/home
INACTIVE=-1
EXPIRE=
SHELL=/bin/bash
SKEL=/etc/skel
CREATE_MAIL_SPOOL=yes

## 위 두 내용이 동일하다.
```

- 그러면 명령어를 통해 기본 HOME 경로를 수정해보자.

```yml
[root@ip-172-31-8-107 /]# useradd -D -b /testhome

[root@ip-172-31-8-107 /]# cat /etc/default/useradd
# useradd defaults file
GROUP=100
HOME=/testhome
INACTIVE=-1
EXPIRE=
SHELL=/bin/bash
SKEL=/etc/skel
CREATE_MAIL_SPOOL=yes

[root@ip-172-31-8-107 /]# useradd -D
GROUP=100
HOME=/testhome
INACTIVE=-1
EXPIRE=
SHELL=/bin/bash
SKEL=/etc/skel
CREATE_MAIL_SPOOL=yes
```

- 이번에는 편집기로 들어가서 직접 수정한 후, 다시 출력해보자.
    - HOME과 SHELL을 수정했다. 

```yml
[root@ip-172-31-8-107 /]# useradd -D
GROUP=100
HOME=/testhome
INACTIVE=-1
EXPIRE=
SHELL=/bin/tcsh
SKEL=/usertest
CREATE_MAIL_SPOOL=yes

[root@ip-172-31-8-107 /]# useradd usertest6
[root@ip-172-31-8-107 /]# cat /etc/passwd
...
ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
usertest1:x:1001:1001::/home/usertest1:/bin/bash
usertest2:x:1002:1002:testuser:/home/usertest2:/bin/sh
userteset3:x:1003:1003::/testhome/usertest3:/bin/tcsh
usertest4:x:1004:1004::/home/usertest4:/bin/bash
usertest5:x:1005:1005::/testhome/usertest5:/bin/bash
```

- 맨 아랫 줄을 보면 사용자 계정을 새로 추가할 때 옵션을 사용하지 않았음에도 기존 추가한 계정들과 HOME과 SHELL이 다르다는 걸 알 수 있다. 

<br>

---


# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)