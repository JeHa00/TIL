# 0. Introduction

> 3. [소유권(Ownership)](#3-소유권ownership)
> 4. [소유권 practice](#4-소유권-practice)  


- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 그룹 생성, 수정, 삭제에 관한 명령어를 실행해보았다. 

- 이번 챕터에서는 권한에 대해 학습한다. 
    - 허가권(Permission) 
    - 소유권(Ownership)

<br>

---
# 3. 소유권(Ownership)

```yml
[root@ip-172-31-8-107 /]# ls -l
total 20
lrwxrwxrwx   1 root root    7 Aug 16 05:20 bin -> usr/bin
dr-xr-xr-x   4 root root 4096 Aug 16 05:23 boot
drwxr-xr-x  15 root root 2900 Aug 30 13:09 dev
drwxr-xr-x  80 root root 8192 Sep  4 03:28 etc
```

- 위 내용을 봤을 때, `root root`를 확인할 수 있다. 
    - 각각 UID, GID로 소유권에 해당한다.  
    - 첫 번째 UID는 허가권에서 첫 번째 rwx를 의미하며, 두 번째 UID는 허가권에서 두 번째 rwx를 의미한다.  


<br>


---
## 3.1 소유권 변경

> **_- 기본 기능: 파일이나 디렉토리의 허가권을 변경_**
> **_chown [uid: gid] [대상 파일명 or 디렉토리] ... [대상 파일명 or 디렉토리]_**


- Example
    - chown testuser1 vimrc
        - UID에 해당되는 부분만 testuser1으로 변경된다.  
    - chown testuser1:testuser1 vrc
        - UID:GID로 해당되므로, UID와 GID 다 testuser1으로 바뀐다.
    - chown testuser2:root vrc
        - UID는 testuser2, GID는 root로 바뀐다.

- Example
    - chown testuser1.root test
        - 점(.)을 기준으로 2개 입력되면 점 앞에 있는 것은 변경되고, 뒷 부분은 유지된다.
    - chown .root test
        - 점 앞에 UID는 유지하고, 점 뒷 부분은 root로 변경한다는 의미다.  
    - chown testuser1. test
        - 점 앞에 UID는 변경하고, 뒷 부분은 유지한다는 의미

<br>


---
## 3.2 그룹 소유권 변경


> **_기본 기능: 파이르이 소유 그룹을 변경_**
> **_chrgp [그룹명] [대상 파일명] ... [대상 파일명]_**

<br>


---
# 4. 소유권 practice

> **_- 허가권보다 소유권을 우선시한다._**  
> **_- 허가권이 동일할 경우, 소유권에 따라 결정된다._**  
> **_- 소유권에 따라 접속자가 누군지에 따라서 파일 접속, 업로드가 가능하다._**  

- 첫 번째 
    - 지난 실습 때 만들었던 `/var/www/html/index.html`은 삭제한다. 
    - 그리고 `/export/home/ec2-user/html` 을 만든다.

- 두 번째
    - `~` 경로에 `index.html`를 생성한다.  
    - 내용으로는 JEHA 를 입력해보자.
        - 입력 종료는 `fn` + `enter`를 입력한다.

- 세 번째
    - `/export/home/ec2-user/` 경로에서 `chmod 325 html/`을 실행
    - `/var/www` 경로에서 `chmod 325 html/`을 실행

- 네 번째: sftp 접속 테스트 -> 다른 터미널에 진행
    - `sftp user2` 입력 
    - `ls -l` 를 입력하여 첫 번째 단계에서 생성한 html을 확인한다.  
    - `cd /export/home/ec2-user/html`로 이동  
    - `put index.html` 실행하여 Permission denied 확인

- 다섯 번째: html의 `ls -l` 확인하기 
    - `d-wx-w-r-x 2 root root 6 Sep  7 17:20 html`
    - UID, GID가 다 root라는 건 제 3자인 일반 사용자를 의미한다.
    - 그래서 맨 뒷 3자리 `r-x` 로서 `w`가 없기 때문에 Permission denied가 발생했고, 이는 정상적인 것이다.  

- 여섯 번째: 소유권만 변경해보기
    - `chown ec2-user.root html`: `.`을 통해 UID와 GID를 구분한다.  

- 일곱 번째: 다시 테스트해보기 
    - `put index.html` 을 다시 실행해보면 Error가 발생하지 않는 걸 알 수 있다. 


- 여덟 번째: `mv html/index.html /var/www/html/` 후, 테스트 사이트 새로고침을 하면 index.html 내부 내용이 나온다.

<br>


---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)