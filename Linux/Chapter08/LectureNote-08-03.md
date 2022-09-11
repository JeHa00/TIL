# 0. Introduction

> 5. [yum](#5-yum-패키지)
> [practice](#practice)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 프로세스 관리에 대해 학습했다.

- 이번 챕터에서는 데이터 저장과 패키지에 대해 학습해보자.

- 실제 시스템 속에서 파일들을 사용할 때는 패키지인 rpm과 yum을 반드시 습득해야 한다.
    
<br>

---


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
# 5. yum 패키지

## 5.1 yum package란?

> **_기본 기능: rpm 기반의 시스템을 위한 자동 업데이터 겸 패키지 설치/제거 도구_**


- yum(Yellow Updater, Modified)의 약어


- **설치/삭제/확인**
    - 설치: `rpm -ivh [패키지명.버전.아키텍처.rpm]`
    - 삭제: `rpm -e [패키지명]`
    - 확인: `rpm -qa | grep [패키지명]`


- 기본 기능
    - 1) rpm 명령어가 해결하지 못 했던 패키지 의존성 문제해결
    - 2) yum 명령어를 사용하면 패키지 의존성 문제를 자동으로 처리하면서 설치, 업데이트, 삭제를 진행
    - 3) 필요한 패키지들은 인터넷상에 존재하는 패키지 저장소 서버로부터 다운로드 받아 설치

- **yum 설정 파일**: `/etc/yum.conf`
        - 리눅스에서 `/etc/`는 매우 중요한 의미를 가진다. 

<br>

## 5.2 yum 명령어

> **_yum -y [install / update / remove] [패키지명]_**

- 위에 명령어에서 업데이트를 권하지 않는 이유는 install만 입력하면 알아서 최신 패키지명으로 바꿔주기 때문이다. 

- 하지만, Update 명령어를 써야할 때가 있다. 
        - os 설치 후, 최신 모듈로 바꿔줘야하기 때문이다.  
        - 일반적으로 단위 패킷 업데이트할 때는 install과 remove만 사용한다.

- 예시

```yml
# 업데이트 가능한 패키지 목록을 보여준다.
yum list

# 옵션 -y를 사용하면 물어보지 않고 알아서 업데이트할 것들을 처리한다. 
yum update

# 개별적으로 패키지를 설치하는 방법
yum -y install vim or vim-*

# 패키지 제거
rpm -y remove vim
```

<br>

---

# Practice

### vim 패키지 삭제 후, yum 명령어로 재설치하기 

```yml
# vim 패키지 목록 확인
[root@ip-172-31-8-107 ~]# rpm -qa | grep vim
vim-common-8.2.5172-1.amzn2.0.1.x86_64
vim-filesystem-8.2.5172-1.amzn2.0.1.noarch
vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64
vim-data-8.2.5172-1.amzn2.0.1.noarch
vim-minimal-8.2.5172-1.amzn2.0.1.x86_64

# yum 명령어 사용하여 패키지 삭제하기
[root@ip-172-31-8-107 ~]# yum remove vim-data
...
ependencies Resolved

============================================================================================================================
 Package                      Arch                   Version                                Repository                 Size
============================================================================================================================
Removing:
 vim-data                     noarch                 2:8.2.5172-1.amzn2.0.1                 installed                  18 k
Removing for dependencies:
 vim-common                   x86_64                 2:8.2.5172-1.amzn2.0.1                 installed                  33 M
 vim-enhanced                 x86_64                 2:8.2.5172-1.amzn2.0.1                 installed                 3.4 M
 vim-minimal                  x86_64                 2:8.2.5172-1.amzn2.0.1                 installed                 1.3 M

Transaction Summary
============================================================================================================================
Remove  1 Package (+3 Dependent packages)

Installed size: 38 M
Is this ok [y/N]: y
...
Warning: RPMDB altered outside of yum.
  Erasing    : 2:vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64                                                               1/4 
  Erasing    : 2:vim-common-8.2.5172-1.amzn2.0.1.x86_64                                                                 2/4 
  Erasing    : 2:vim-minimal-8.2.5172-1.amzn2.0.1.x86_64                                                                3/4 
  Erasing    : 2:vim-data-8.2.5172-1.amzn2.0.1.noarch                                                                   4/4 
  Verifying  : 2:vim-common-8.2.5172-1.amzn2.0.1.x86_64                                                                 1/4 
  Verifying  : 2:vim-minimal-8.2.5172-1.amzn2.0.1.x86_64                                                                2/4 
  Verifying  : 2:vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64                                                               3/4 
  Verifying  : 2:vim-data-8.2.5172-1.amzn2.0.1.noarch                                                                   4/4 

Removed:
  vim-data.noarch 2:8.2.5172-1.amzn2.0.1                                                                                    

Dependency Removed:
  vim-common.x86_64 2:8.2.5172-1.amzn2.0.1                     vim-enhanced.x86_64 2:8.2.5172-1.amzn2.0.1                   
  vim-minimal.x86_64 2:8.2.5172-1.amzn2.0.1                   

Complete!

[root@ip-172-31-8-107 ~]# rpm -qa | grep vim
vim-filesystem-8.2.5172-1.amzn2.0.1.noarch

# 나머지 Vim 패키지 마저 삭제하기
[root@ip-172-31-8-107 ~]# yum -y remove vim-filesystem
...
Dependencies Resolved

============================================================================================================================
 Package                       Arch                  Version                                 Repository                Size
============================================================================================================================
Removing:
 vim-filesystem                noarch                2:8.2.5172-1.amzn2.0.1                  installed                 40  

Transaction Summary
============================================================================================================================
Remove  1 Package

Installed size: 40  
Downloading packages:
Running transaction check
Running transaction test
Transaction test succeeded
Running transaction
  Erasing    : 2:vim-filesystem-8.2.5172-1.amzn2.0.1.noarch                                                             1/1 
  Verifying  : 2:vim-filesystem-8.2.5172-1.amzn2.0.1.noarch                                                             1/1 

Removed:
  vim-filesystem.noarch 2:8.2.5172-1.amzn2.0.1                                                                              

Complete!

# vim package가 다 삭제된 걸 알 수 있다.  
[root@ip-172-31-8-107 ~]# vi
-bash: /usr/bin/vi: No such file or directory

# vim package 다시 전체 설치하기  
[root@ip-172-31-8-107 ~]# yum install vim-*
...
Is this ok [y/d/N]: y
...
Complete!

# 설치 목록 확인
[root@ip-172-31-8-107 ~]# rpm -qa | grep vim
vim-filesystem-8.2.5172-1.amzn2.0.1.noarch
vim-enhanced-8.2.5172-1.amzn2.0.1.x86_64
vim-data-8.2.5172-1.amzn2.0.1.noarch
vim-common-8.2.5172-1.amzn2.0.1.x86_64
vim-X11-8.2.5172-1.amzn2.0.1.x86_64
vim-minimal-8.2.5172-1.amzn2.0.1.x86_64
```

- yum 과 rpm의 차이
        - yum 을 통해서 설치가 불가능하면 사이트를 뒤져서 직접 패키지를 찾은 후, 직접 업로드해야 한다. 

- 인터넷이 안되도 직접 설치해야하기 때문에, rpm을 어떻게 의존성을 해결하고 설치해야하는지 알아야하기 때문에, 반드시 yum과 rpm을 알아야 한다. 

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)