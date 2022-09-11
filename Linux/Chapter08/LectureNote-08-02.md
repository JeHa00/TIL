# 0. Introduction

> 3. [rpm(Redhat Package Manager)](#3-rpmredhat-package-manager)  
> 4. [질의(query)](#4-질의query)  
> [practice](#practice)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 프로세스 관리에 대해 학습했다.

- 이번 챕터에서는 데이터 저장과 패키지에 대해 학습해보자.

- 실제 시스템 속에서 파일들을 사용할 때는 패키지인 rpm과 yum을 반드시 습득해야 한다.
    
<br>

---
# 3. rpm(Redhat Package Manager)

## 3.1 rpm과 rpm의 기본 명령어

> **_기본 기능: 패키지 개념을 도입해서 만들어진 것으로, 설치 / 삭제 / 업그레이드 등을 매우 편리하게 기능을 제공_**

아래 3가지 반드시 외워야 하며, 각 작업 전후로 확인 작업을 병행해야 한다. 

아래 명령어를 rpm에서 가장 많이 사용된다.  

### rpm 설치

- `rpm -ivh [패키지명.버전.아키텍처.rpm]`

### 삭제

- `rpm -e [패키지명]`

### 확인

- `rpm -qa | grep [패키지명]`

<br>

---
## 3.2 rpm 패키지 파일

> **_기본 기능: 관리의 용이성을 위하여 일정한 규칙을 가지고 구성_**  

- `vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64.rpm`

- 1) 패키지명: vim-enhanced
- 2) 버전
    - 패키지 버전: 8.2.5172
        - 메이저 버전: 8
        - 마이너 버전: 2
        - 패치: 5172
    - 릴리즈 버전: 1.amzn2.0.1
- 3) 아키텍처: x86_64

|Architecture|내용 |
|---- |---- |
|i386 |Intel 호환 386 CPU급 이상|
|i586|Intel 호환 586 펜티엄급 CPU 이상|
|i686|Intel 호환 686 펜티엄 프로급 CPU 이상|
|x86_64|인텔 계열 또는 AMD 계열 64bit CPU|
|noarch|특정 아키텍처를 의미하지 않음.|


<br>

---
# 3.3 rpm 명령어 옵션

> **_기본 기능: 관리의 용이성을 위하여 일정한 규칙을 가지고 구성_**     
> rpm [옵션] [패키지명.버전.아키텍처.rpm or 패키지명]

| 옵션 | 내용 |
| ----|----|
|-i|설치되어 있지 않은 일반적인 패키지 설치|
|-U|패키지가 설치되어 있지 않은 경우, -i와 동일하게 설치|
|-U|패키지가 이미 설치되어 있는 경우, 버전을 업그레이드하거나 다운 그레이드|
|-F |패키지가 이미 설치되어 있는 경우, 버전을 업그레이드|
|-v |작업 과정을 상세하게 출력|
|-h |패키지를 설치할 때 해쉬마크의 형태로 표시|
|-e|설치되어 있는 패키지 삭제|
|--test|실제로 설치하지 않고, 가상으로 설치해본 뒤 오류나 충돌 사항이 있는지 점검|
|--force|설치 중 발생하는 에러를 무시하고, 강제로 설치 진행|
|--nodeps|설치 or 삭제 시, 패키지가 필요로 하는 의존성 여부를 검사하지 않음|
|-q|설치된 rpm 패키지를 조회|


- `-U`보다는 `-i`를 사용한다.

- 예시

```yml
rpm -ivh vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64.rpm
rpm -Uvh vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64.rpm
rpm -Uvh vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64.rpm -nodeps
rpm -e vim-enhanced-8.2.5172-1
```

<br>

---

# 4. 질의(query)

> **_기본 기능: 설치된 rpm 패키지를 조회_**

|옵션| 내용|
|----|----|
|-q [name]|[name]패키지의 전체 흐름을 조회 (버전 포함)|
|-qa |설치된 패키지 전체 목록을 조회|
|-ql [name]|[name] 패키지의 설치 파일 리스트를 조회|
|-qd [name]|[name] 패키지의 파일 리스트 중 문서 파일만 조회|
|-qc [name]|[name] 패키지의 파일 리스트 중 설정 파일만 조회|
|-qf [file]|[file]이 속해 있는 패키지 조회|


<br>

---

# practice


## 사전 준비

> 설치 환경 조성을 위한 Package 삭제 진행

```yml
# vim 관련 설치된 패키지
[root@ip-172-31-8-107 /]# rpm -qa | grep vim
vim-data-8.2.5172-1.amzn2.0.1.noarch
vim-filesystem-8.2.5172-1.amzn2.0.1.noarch
vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64
vim-common-8.2.5172-1.amzn2.0.1.x86_64
vim-minimal-8.2.5172-1.amzn2.0.1.x86_64
```

- 삭제 시도 시, 발생한 의존성 오류
    - enhanced 로 인해 삭제 불가능: enhanced에서 common을 사용하고 있다는 의미
    - 이 에러 내용을 잘 보고 삭제해야 한다.

```yml
[root@ip-172-31-8-107 /]# rpm -e vim-common
error: Failed dependencies:
        vim-common = 2:8.2.5172-1.amzn2.0.1 is needed by (installed) vim-enhanced-2:8.2.5172-1.amzn2.0.1.x86_64

[root@ip-172-31-8-107 /]# rpm -e vim-enhanced
[root@ip-172-31-8-107 /]# rpm -e vim-common

# 5개에서 3개로 줄어든 걸 확인
[root@ip-172-31-8-107 /]# rpm -qa | grep vim
vim-data-8.2.5172-1.amzn2.0.1.noarch
vim-filesystem-8.2.5172-1.amzn2.0.1.noarch
vim-minimal-8.2.5172-1.amzn2.0.1.x86_64


[root@ip-172-31-8-107 /]# rpm -e sudo
error: Failed dependencies:
        sudo is needed by (installed) cloud-init-19.3-45.amzn2.noarch

[root@ip-172-31-8-107 /]# rpm -e cloud-init
[root@ip-172-31-8-107 /]# rpm -e sudo
[root@ip-172-31-8-107 /]# rpm -e vim-minimal

[root@ip-172-31-8-107 /]# rpm -qa | grep vim
vim-data-8.2.5172-1.amzn2.0.1.noarch
vim-filesystem-8.2.5172-1.amzn2.0.1.noarch

[root@ip-172-31-8-107 /]# rpm -e vim-data
[root@ip-172-31-8-107 /]# rpm -e vim-filesystem
```

<br>

- 삭제했기 때문에, vim package를 사용할 수 없다. 

```yml
[root@ip-172-31-8-107 /]# vi
-bash: vi: command not found
```

## rpm 패키지 설치하기

```yml
[root@ip-172-31-8-107 /]# yum install vim-* -y --downloadonly --downloaddir=/tmp
...
(85/91): vim-X11-8.2.5172-1.amzn2.0.1.x86_64.rpm           | 1.8 MB  00:00:00     
(86/91): vim-data-8.2.5172-1.amzn2.0.1.noarch.rpm          |  76 kB  00:00:00     
(87/91): vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64.rpm      | 1.7 MB  00:00:00     
(88/91): vim-filesystem-8.2.5172-1.amzn2.0.1.noarch.rpm    |  71 kB  00:00:00     
(89/91): vim-common-8.2.5172-1.amzn2.0.1.x86_64.rpm        | 7.7 MB  00:00:00     
(90/91): vim-minimal-8.2.5172-1.amzn2.0.1.x86_64.rpm       | 705 kB  00:00:00 

[root@ip-172-31-8-107 /]# cd /tmp

# vim으로 시작한 파일들 확인
[root@ip-172-31-8-107 tmp]# ls -l vim-*
-rw-r--r-- 1 root root 8044284 Jul 20 04:18 vim-common-8.2.5172-1.amzn2.0.1.x86_64.rpm
-rw-r--r-- 1 root root   77880 Jul 20 04:17 vim-data-8.2.5172-1.amzn2.0.1.noarch.rpm
-rw-r--r-- 1 root root 1732472 Jul 20 04:17 vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64.rpm
-rw-r--r-- 1 root root   72280 Jul 20 04:17 vim-filesystem-8.2.5172-1.amzn2.0.1.noarch.rpm
-rw-r--r-- 1 root root  722144 Jul 20 04:17 vim-minimal-8.2.5172-1.amzn2.0.1.x86_64.rpm
-rw-r--r-- 1 root root 1883936 Jul 20 04:17 vim-X11-8.2.5172-1.amzn2.0.1.x86_64.rpm

# 의존성 에러 발생 -> vim-common 설치해보자.
[root@ip-172-31-8-107 tmp]# rpm -ivh vim-X11-8.2.5172-1.amzn2.0.1.x86_64.rpm
error: Failed dependencies:
        ...
        vim-common = 2:8.2.5172-1.amzn2.0.1 is needed by vim-X11-2:8.2.5172-1.amzn2.0.1.x86_64

# 의존성 에러 발생 -> vim-data 와 vim-filesystem 설치해보자.
[root@ip-172-31-8-107 tmp]# rpm -ivh vim-common-8.2.5172-1.amzn2.0.1.x86_64.rpm
error: Failed dependencies:
        vim-data = 2:8.2.5172-1.amzn2.0.1 is needed by vim-common-2:8.2.5172-1.amzn2.0.1.x86_64
        vim-filesystem is needed by vim-common-2:8.2.5172-1.amzn2.0.1.x86_64

# vim-data 설치 완료
[root@ip-172-31-8-107 tmp]# rpm -ivh vim-data-8.2.5172-1.amzn2.0.1.noarch.rpm
Preparing...                          ################################# [100%]
Updating / installing...
   1:vim-data-2:8.2.5172-1.amzn2.0.1  ################################# [100%]

# vim-filesystem 설치 완료
[root@ip-172-31-8-107 tmp]# rpm -ivh vim-filesystem-8.2.5172-1.amzn2.0.1.noarch.rpm
Preparing...                          ################################# [100%]
Updating / installing...
   1:vim-filesystem-2:8.2.5172-1.amzn2################################# [100%]

# vim-common 설치 완료
[root@ip-172-31-8-107 tmp]# rpm -ivh vim-common-8.2.5172-1.amzn2.0.1.x86_64.rpm
Preparing...                          ################################# [100%]
Updating / installing...
   1:vim-common-2:8.2.5172-1.amzn2.0.1################################# [100%]

[root@ip-172-31-8-107 tmp]# rpm -qa | grep vim
vim-common-8.2.5172-1.amzn2.0.1.x86_64
vim-filesystem-8.2.5172-1.amzn2.0.1.noarch
vim-data-8.2.5172-1.amzn2.0.1.noarch

[root@ip-172-31-8-107 tmp]# ls -l vim*
-rw-r--r-- 1 root root 8044284 Jul 20 04:18 vim-common-8.2.5172-1.amzn2.0.1.x86_64.rpm
-rw-r--r-- 1 root root   77880 Jul 20 04:17 vim-data-8.2.5172-1.amzn2.0.1.noarch.rpm
-rw-r--r-- 1 root root 1732472 Jul 20 04:17 vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64.rpm
-rw-r--r-- 1 root root   72280 Jul 20 04:17 vim-filesystem-8.2.5172-1.amzn2.0.1.noarch.rpm
-rw-r--r-- 1 root root  722144 Jul 20 04:17 vim-minimal-8.2.5172-1.amzn2.0.1.x86_64.rpm
-rw-r--r-- 1 root root 1883936 Jul 20 04:17 vim-X11-8.2.5172-1.amzn2.0.1.x86_64.rpm

# vim-enhanced 설치 완료
[root@ip-172-31-8-107 tmp]# rpm -ivh vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64.rpm 
Preparing...                          ################################# [100%]
Updating / installing...
   1:vim-enhanced-2:8.2.5172-1.amzn2.0################################# [100%]

# vim-minimal 설치 완료
[root@ip-172-31-8-107 tmp]# rpm -ivh vim-minimal-8.2.5172-1.amzn2.0.1.x86_64.rpm 
Preparing...                          ################################# [100%]
Updating / installing...
   1:vim-minimal-2:8.2.5172-1.amzn2.0.################################# [100%]

# vim package 설치 목록
[root@ip-172-31-8-107 tmp]# rpm -qa | grep vim
vim-common-8.2.5172-1.amzn2.0.1.x86_64
vim-filesystem-8.2.5172-1.amzn2.0.1.noarch
vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64
vim-data-8.2.5172-1.amzn2.0.1.noarch
vim-minimal-8.2.5172-1.amzn2.0.1.x86_64
```

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)