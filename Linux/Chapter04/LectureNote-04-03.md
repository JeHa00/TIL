# 0. Introduction

> 1. [그룹관리](#1-그룹관리)  
> 2. [groupadd](#2-groupadd)  
> 3. [groupmod](#3-groupmod)  
> 4. [groupdel](#4-groupdel)  
> [practice](#practice)  

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- 지난 소챕터에서는 text 기반에서 사용할 수 있는 에디터인 **문서편집기 vi editor** 에 대해 학습했다.

- 이번 챕터에서는 사용자 계정을 관리하는 법 아래의 소챕터 3개 중 세 번째를 학습한다. 
    - 사용자 계정 관리 및 생성  
    - 사용자 변경  
    - 그룹  

<br>

---


# 1. 그룹관리

> **_그룹 생성 시 설정 파일_**

|파일 |내용 |
|/etc/group |사용자 그룹에 대해 정의되어 있는 파일 |

- cat를 사용하여 확인할 수 있다.  

<br>

---
# 2. groupadd

> **_groupadd [옵션] [인자값] [사용하고자 하는 그룹명]_**

| 옵션 | 내용 | 
| ---- | ---- | 
| -f | 이미 존재하는 그룹과 동일한 그룹을 강제 생성 |
| **-g** | 그룹 생성 시 임의의 GID 강제 지정 |
| -r | 그룹의 GID를 1~499 사이의 값으로 생성 |

- 그룹에서는 옵션을 거의 사용하지 않는다. 주로 -g를 사용한다.
- 슈퍼 유저가 가지고 있는 0번부터 499번까지는 시스템이 관리하고, 500번부터는 유저들이 임의로 지정 가능하다.

<br>

---
# 3. groupmod

> **_groupmod [옵션] [인자값] [변경할 그룹명]_**

| 옵션 | 내용 | 
| ---- | ---- | 
| -g | 기존 그룹의 GID 값 변경 |
| -n | 그룹의 이름을 변경 |

- 그룹은 임의대로 지정할 수 있기 때문에, vi에서 얼마든지 수정가능하다. 


<br>

---
# 4. groupdel

> **_그룹의 정보를 변경_**

- `groupdel [삭제할 그룹명]`

<br>

---
# practice

## useradd 기본 정보 초기화하기
### 사용자 그룹 관련 정보 확인하기

```yml
[root@ip-172-31-8-107 /]# tail /etc/group
slocate:x:21:
postdrop:x:90:
postfix:x:89:
chrony:x:994:
stapusr:x:156:
stapsys:x:157:
stapdev:x:158:
screen:x:84:
tcpdump:x:72:
ec2-user:x:1000:
```

<br>

### useradd default 정보 원상복귀

```yml
[root@ip-172-31-8-107 /]# vi /etc/default/useradd
GROUP=100
HOME=/home
INACTIVE=-1
EXPIRE=
SHELL=/bin/bash
SKEL=/etc/skel
CREATE_MAIL_SPOOL=yes

# /home/ directory 초기화하기
[root@ip-172-31-8-107 /]# ls -l /home/
total 0

[root@ip-172-31-8-107 /]# useradd usertest1
[root@ip-172-31-8-107 /]# useradd usertest2
[root@ip-172-31-8-107 /]# tail /etc/passwd
...
ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
usertest1:x:1001:1001::/home/usertest1:/bin/bash
usertest2:x:1002:1002::/home/usertest2:/bin/bash
```

<br>

## group 사용하기

### group 정보 확인하기

- 생성된 계정 이름과 동일한 그룹명이 생겼다.  

```yml
[root@ip-172-31-8-107 home]# tail /etc/group
...
ec2-user:x:1000:
usertest1:x:1001:
usertest2:x:1002:
```


<br>

### group 생성하기: groupadd

- `-g`를 사용하여 GID를 강제로 지정한 것이 그 다음 그룹 생성에도 영향을 미친다. 

```yml
[root@ip-172-31-8-107 home]# groupadd grouptest1
[root@ip-172-31-8-107 home]# groupadd -g 1100 grouptest2
[root@ip-172-31-8-107 home]# groupadd grouptest3
[root@ip-172-31-8-107 home]# tail /etc/group
...
ec2-user:x:1000:
usertest1:x:1001:
usertest2:x:1002:
grouptest1:x:1003:
grouptest2:x:1100:
grouptest3:x:1101:
```

<br>

### group 삭제하기: groupdel

- 그룹을 재생성하기 위해서 그룹을 삭제하자.  

```yml
[root@ip-172-31-8-107 home]# groupdel grouptest2
[root@ip-172-31-8-107 home]# groupdel grouptest3
[root@ip-172-31-8-107 home]# tail /etc/group
...
ec2-user:x:1000:
usertest1:x:1001:
usertest2:x:1002:
grouptest1:x:1003:
```

- 그러면 다시 그룹을 생성해보자. 

```yml
[root@ip-172-31-8-107 home]# groupadd grouptest2
[root@ip-172-31-8-107 home]# groupadd grouptest3
[root@ip-172-31-8-107 home]# tail /etc/group
...
ec2-user:x:1000:
usertest1:x:1001:
usertest2:x:1002:
grouptest1:x:1003:
grouptest2:x:1004:
grouptest3:x:1005:
```

<br>

### user의 group 정보 변경 및 추가 그룹 지정

- 그룹 정보 변경: `-g`  
- 추가 그룹 지정: `-G`

- `id 계정명`: uid, gid 확인

```yml
# 기본 그룹 변경
[root@ip-172-31-8-107 home]# usermod -g grouptest1 usertest1

# 기본 그룹 추가
[root@ip-172-31-8-107 home]# usermod -G grouptest2 usertest2

[root@ip-172-31-8-107 home]# tail /etc/group
stapsys:x:157:
stapdev:x:158:
screen:x:84:
tcpdump:x:72:
ec2-user:x:1000:
usertest1:x:1001:
usertest2:x:1002:
grouptest1:x:1003:
grouptest2:x:1004:usertest2

[root@ip-172-31-8-107 home]# id usertest1
uid=1001(usertest1) gid=1003(grouptest1) groups=1003(grouptest1)
[root@ip-172-31-8-107 home]# id usertest2
uid=1002(usertest2) gid=1002(usertest2) groups=1002(usertest2),1004(grouptest2)
```

- usertest1 계정은 `-g`를 사용했기 때문에 gid가 usertest1에서 grouptest1로 옮겨졌다. 이는 **_grouptest1이 사용할 수 있는 권한을 사용할 수 있도록 변경된 것이다._**

- usertest2 계정은 `-G`를 사용했기 때문에, groups에 usertest2 뿐만 아니라, grouptest2도 추가됬다. 이는 **_usertest2와 groupstest2의 권한 모두를 사용할 수 있다는 걸 의미한다._** 

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)