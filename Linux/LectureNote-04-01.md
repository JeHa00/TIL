# 0. Introduction

> 1. [사용자 계정관리 및 생성](#1-사용자-계정관리-및-생성)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- 지난 소챕터에서는 text 기반에서 사용할 수 있는 에디터인 **문서편집기 vi editor** 에 대해 학습했다.

- 이번 챕터에서는 사용자 계정을 관리하는 법 아래의 소챕터 3개 중 첫 번째를 학습한다. 
    - 사용자 계정 관리 및 생성  
    - 사용자 변경  
    - 그룹  

<br>

---

# 1. 사용자 계정관리

> **_사용자 설정 파일_**


| 파일 | 내용 |
| ---- | ---- | 
| /etc/passwd | 사용자 계정의 정보가 저장된 파일|
| /etc/shadow | 사용자 계정의 중요 정보(실제 비밀번호)가 저장된 파일 |
| /home | 일반 사용자의 홈 디렉토리가 생성되는 Directory |
| /etc/group | 사용자 계정이 속한 그룹의 정보가 저장된 파일|
| /etc/login.defs | 사용자 생성 시 부여되는 설정 값들을 미리 정리해놓은 파일 |
| /etc/default/useradd | 사용자 생성 시 사용되는 기본 설정 값이 저장된 설정 파일 |
| /etc/skel |  환경설정 파일 |


<br>

---

# 2. /etc/passwd 

> **_사용자 계정의 정보가 저장된 파일_**

- `User_ID : x: 500 : 500 :: /home/user_id : /bin/bash`


| 항목 | 내용 |
| ----| ----|
| User_ID | 사용자 계정명 예시)ec2-user, dalkom 등등 |
|x | 사용자 계정에 부여된 패스워드 |
| 500 | User ID(UID) |
|500 | Group ID(GID) |
| NULL | 사용자 게정에 부여되는 임의의 정보 |
| /home/user_id | 사용자 계정의 Home Directory 경로|
| /bin/bash | 사용자 계정이 로그인 시 사용하는 Shell의 위치 |


<br>

---

# 3. /etc/shadow 

> **_사용자 계정의 중요 정보가(실제 비밀번호)가 저장된 파일_**


- `User_ID : !! : 13479 : 0 : 99999 : 7 : : :`


| 항목 | 내용 |
| ---- | ---- |
| 99999 | 부여된 암호를 변경 없이 사용할 수 있는 유효기간|
| 7 | 만료일 지정 시 만료 경고 일 수 |
|NULL |계정 만료 일자와 비활성화 일 수 |
|NULL | 계정의 만료일|


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


- `useradd [옵션] [값] [계정명]`
| 옵션 | 내용 |
|---- | ---- | 
|-u |사용자 계정의 UID 정보를 임의로 지정 |
|-g |사용자 계정의 기본 그룹명 지정 |
|-c|사용자 계정의 설명 |
|-d|사용자 계정의 Home directory 변경 |
| -s| 사용자 계정의 로그인 Shell 변경|



- `useradd -D [옵션] [인자값]`
| 항목 | 내용 |
|---- | ---- | 
| -b| Home directory의 기본 생성 위치가 인자값으로 변경|
| -e|기본 만료 일자 변경 |
| -f|만료 일자에 해당하는 Inactive Days변경 |
| -g|기본 그룹이 [인자값]으로 변경 |
| -s|기본 로그인 Shell이 [인자값]으로 변경 |


<br>

---


# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)