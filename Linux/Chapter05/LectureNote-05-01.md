# 0. Introduction

> 1. [허가권(Permission)](#1-허가권permission)  
> 2. [허가권 practice](#2-허가권-practice)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 그룹 생성, 수정, 삭제에 관한 명령어를 실행해보았다. 

- 이번 챕터에서는 권한에 대해 학습한다. 
    - 허가권(Permission) 
    - 소유권(Ownership)

<br>

---
# 1. 허가권(Permission)

리눅스에서 허가권은 매우 중요한 역할을 하는데, `ls -l`을 해서 파일과 디렉토리 맨 앞에 오는 것이 **_허가권_** 을 의미한다.

```yml
[root@ip-172-31-8-107 /]# ls -l
total 20
lrwxrwxrwx   1 root root    7 Aug 16 05:20 bin -> usr/bin
dr-xr-xr-x   4 root root 4096 Aug 16 05:23 boot
drwxr-xr-x  15 root root 2900 Aug 30 13:09 dev
...
```

- 위 내용에서 `lrwxrwxrwx `, `dr-xr-xr-x `, `drwxr-xr-x` .. 가 허가권이다.

그러면 허가권에 대해서 보다 자세히 알아보자. 

- **허가권은 총 10자리다.** 
- **ex) drwxr-xr-x**
    - **맨 첫 글자: type of file**
        - 'd': directory
        - '-': file
        - 'l': link  
    - **맨 첫 글자 이외에는 다 rwx로 표시하는데 rwx 표시가 없으면 '할 수 없음'을 의미한다.** 
        - '-': can't
        - r or w or x: can read or write or execute  
    - **그 다음 세글자 - User/Owner: 일반 유저(_u_ )의 허가권**
        - UID를 의미
        - 'r': can read    
        - 'w': can write    
        - 'x': can execute    
    - **그 다음 세글자 -  Group(_g_ )의 허가권**
        - GID를 의미  
        - 'r': can read
        - '-': can't write
        - 'x': can execute  
    - **그 다음 세글자 -  Other: 일반 사용자(_o_ )의 허가권**
        - 'r': can read
        - '-': can't write
        - 'x': can execute  


그렇다면 *'754 권한을 가지고 있다'* 라는 말은 무슨 말일까?

아래 이미지를 참고해보자.  

![image](https://user-images.githubusercontent.com/78094972/188528170-3e5b9575-2b0e-474a-83ae-f284c4df39db.png)

즉, 아래 의미를 가진다.
- user로서는 r/w/x 가 다 가능하다.
- group으로서는 r/x만 가능하다. 
- Other은 r만 가능하다.  


<br>


---

<br>


## 1.1 허가권 변경

> **_- 기본 기능: 파일이나 디렉토리의 허가권을 변경_**
> **_chmod [인자값] [대상 파일명 or 디렉토리] ... [대상 파일명 or 디렉토리]_**


- 인자값
    - **숫자[Numeric Method]**
        - 내용: ex) chmod 644 test
            - 644 : rw-r--r--
    - **문자[Symbolic Method]**
        - 내용
            - ex) chmod g+rx test
                - g이므로, group 부분에 rx 권한을 부여한다는 의미
                - : rw-r--r-- => rw-r-xr-- (654)
            - ex) -wx-w--wx: 323
                - 위에 654 권한에서 아래 명령어를 입력하여 323으로 수정한다. 
                - chmod u-r, u+x, g-r,g+w,g-x, o-r, o+w, o+x test
                - 위 명령어를 보다 간단히 입력하면 다음과 같다. 
                    - chmod u-r+x,g-r+w-x,o-r+w+x test
                    - ❗️ 쉼표를 사용하여 열거할 때, 스페이스바로 띄우면 안된다. 

<br>


---
## 1.2 File: 문서 or 실행 파일

- 대상이 file일 때, rwx가 가지는 의미

| 권한 | 설명 |
| ---- | ---- |
| r 권한 | cat, more, vim, cp 등과 같은 파일의 내용을 읽기 |
| w 권한 | cat, echo, vim 등과 같은 파일의 내용을 수정, 변경 |
| x 권한 | 실행파일의 실행 |


<br>


---
## 1.3 Directory

- 대상이 directory일 때, rwx가 가지는 의미

| 권한 | 설명 |
| ---- | ---- |
|r 권한| ls 등과 같은 디렉토리의 내부를 읽기 |
|w 권한| mkdir, touch, rm, mv 등과 같은 디렉토리 내부에 생성, 삭제 |
|x 권한 | **디렉토리의 접근** |

- x 권한을 가지고 있어야 내부를 읽을 수 있다.  

<br>

---

# 2. 허가권 practice

## 사전 준비: Apache 웹 서버 설치

- [EC2 인스턴스 생성 및 웹 서버 설치](https://docs.aws.amazon.com/ko_kr/AmazonRDS/latest/UserGuide/CHAP_Tutorials.WebServerDB.CreateWebServer.html)를 따라서 진행하자.  

- 인스턴스를 새로 생성하여 진행했다. 
    - 그 이유는 인스턴스의 네트워크 설정에서 인바운드 규칙으로 `인터넷에서 HTTPs 트래픽 허용` 과 `인터넷에서 HTTP 트래픽 허용`이 추가되어있지 않기 때문에 Apache 웹서버가 생성되지 않는다.

- 만약 `systemctl start httpd` 를 입력했더니 
    - `Failed to start httpd.service:Unit not found.`가 떴다면 이전 단계들이 제대로 설치되지 않은 것이므로, 다시 실행한다. 

```yml
[root@ip-172-31-3-4 ~]# cd /home/ec2-user
[root@ip-172-31-3-4 ec2-user]# yum install -y httpd.x86_64
...
[root@ip-172-31-3-4 ec2-user]# sytemctl start httpd.service
[root@ip-172-31-3-4 ec2-user]# sytemctl enable httpd.service
Created symlink from /etc/systemd/system/multi-user.target.wants/httpd.service to /usr/lib/systemd/system/httpd.service.
```

❗️ 기존에 생성한 인스턴스를 가지고 Apache 서버에 연결하는 방법은 인스턴스의 `인바운드 규칙`으로 들어가서 그룹 규칙에 `HTTPS`와 `HTTP`를 추가하면 가능하다.  

<br>

## 서로 연동되는 디렉토리와 파일

### 파일의 생성 및 권한 수정

> **_사이트의 내용을 읽기 위해서는 일반 사용자의 읽기 권한이 있어야 한다._**

파일을 생성한 후, 권한을 수정해보면서 파일 조회에 대해 알아본다. 

아래 결과를 통해서 사이트의 내용을 읽기 위해서는 _일반 사용자의 `r` 권한_ 이 있어야 한다는 걸 알 수 있다. 

```yml
# cd /var/www/html
[root@ip-172-31-8-107 html]# cat > index.html
[root@ip-172-31-8-107 html]# ls -l
total 4
-rw-r--r-- 1 root apache 5 Sep  7 16:35 index.html

# 권한을 244로 수정: 사용자의 r 권한 제거 -> 변경 사항 x
[root@ip-172-31-8-107 html]# chmod 244 index.html
[root@ip-172-31-8-107 html]# ls -l
total 4
--w-r--r-- 1 root apache 5 Sep  7 16:35 index.html

# 권한을 640으로 수정 -> 403 Forbidden
[root@ip-172-31-8-107 html]# chmod 640 index.html
[root@ip-172-31-8-107 html]# ls -l
total 4
-rw-r----- 1 root apache 5 Sep  7 16:35 index.html

# 원래 권한으로 복귀
[root@ip-172-31-8-107 html]# chmod 644 index.html
[root@ip-172-31-8-107 html]# ls -l
total 4
-rw-r--r-- 1 root apache 5 Sep  7 16:35 index.html
```


<br>

### 디렉토리의 권한 수정

> **_디렉토리의 접근 권한을 가지고 있지 않으면 디렉토리 내부 파일에 접근할 수 없다_**

디렉토리의 권한을 수정해보면서 접근이 어떻게 변하는지 알아보자.

- `/var/www/html` directory를 통해 학습해본다. 
    - 파일이 아닌 디렉토리이므로, `html/index.html`은 더 사용하지 않는다.  


아래 내용을 통해 디렉토리 내부 파일에 접근하기 위해서는 디렉토리의 접근 권한을 가져야 한다는 걸 알 수 있다.

```yml
# 기존 권한은 755임을 알 수 있다. 
[root@ip-172-31-8-107 www]# ls -l
total 0
drwxr-xr-x 2 root root 24 Sep  7 13:12 html

# 권한을 655로 수정: user의 directory 접근 권한 삭제 -> 변화 X
# 웹 상에서는 r 권한이 필요하기 때문에, 변화 x
[root@ip-172-31-8-107 www]# chomod 655 html
[root@ip-172-31-8-107 www]# ls -l
total 0
drw-r-sr-x 2 ec2-user apache 24 Sep  7 16:32 html

# 권한을 751로 수정: 일반 사용자의 읽기 권한 삭제 -> 변화 x
[root@ip-172-31-8-107 www]# chmod 751 html/
[root@ip-172-31-8-107 www]# ls -l
total 0
drwxr-x--x 2 root root 24 Sep  7 13:12 html

# 권한을 754로 수정: 일반 사용자의 디렉토리 접근 권한 삭제 -> 변화 o
[root@ip-172-31-8-107 www]# chmod 754 html/
[root@ip-172-31-8-107 www]# ls -l
total 0
drwxr-xr-- 2 root root 24 Sep  7 13:12 html
```

- 원래 권한인 755로 복귀시킨다.

<br>

---

## 데이터 업로드 권한 알아보기

> **_'x' 권한을 가질 때 외부에서 접속할 수 있고, 'w' 권한을 가져야 데이터 업로드가 가능하기 때문에, 'wx' 모두 가지고 있어야 데이터 업로드가 가능하다. _**

각 user는 자신만의 권한을 가지므로, 절대 다른 권한을 줄 수 없다.

ec2-user의 권한 값을 바꿔보겠다.

먼저 `sftp user2`으로 접속이 되는지 확인해보면 접속이 가능할 것이다.


```yml
# cd /export/home/
# 원래 권한은 700이다. 
[root@ip-172-31-3-4 home]# ls -l
total 0
drwx------ 3 ec2-user ec2-user 95 Sep  7 06:51 ec2-user

# 권한을 600, 400, 200 다 시도해보자. 
[root@ip-172-31-8-107 home]# chmod 600 ec2-user/
[root@ip-172-31-8-107 home]# ls -l
total 0
drw------- 3 root root 27 Sep  7 17:36 ec2-user

[root@ip-172-31-8-107 home]# chmod 400 ec2-user/
[root@ip-172-31-8-107 home]# ls -l
total 0
dr-------- 3 root root 27 Sep  7 17:36 ec2-user

[root@ip-172-31-8-107 home]# chmod 200 ec2-user/
[root@ip-172-31-8-107 home]# ls -l
total 0
d-w------- 3 root root 27 Sep  7 17:36 ec2-user
```

`exit` 후, 접속을 다시 시도해보면 600, 400, 200 모두 `ec2-user@43.200.244.241: Permission denied (publickey,gssapi-keyex,gssapi-with-mic).` 임을 확인할 수 있다.

그러면 마지막으로 권한 500, 300, 100을 다 시도해보자. 

```yml
# 500
[root@ip-172-31-8-107 home]# chmod 500 ec2-user/
[root@ip-172-31-8-107 home]# ls -l
total 0
dr-x------ 3 root root 27 Sep  7 17:36 ec2-user

# 300
[root@ip-172-31-8-107 home]# chmod 300 ec2-user/
[root@ip-172-31-8-107 home]# ls -l
total 0
d-wx------ 3 root root 27 Sep  7 17:36 ec2-user

# 100
[root@ip-172-31-8-107 home]# chmod 100 ec2-user/
[root@ip-172-31-8-107 home]# ls -l
total 0
d--x------ 3 root root 27 Sep  7 17:36 ec2-user

> sftp user2
Connected to user2.
```

권한의 100자리 숫자가 홀수일 때 즉, `x` 권한을 가질 때 외부에서 접속이 가능하다는 걸 알 수 있다.

그리고 접속 후, 데이터를 업로드하기 위해서는 `w` 권한이 필요하므로, 결국 `700`, `300` 만이 데이터 업로드가 가능하다는 걸 알 수 있다. 

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)