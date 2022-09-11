# 0. Introduction

> 1. [SetUID](#1-setuid)  
> 2. [SetGID](#2-setgid)  
> 3. [Stickbit(sb)](#3-stickbitsb)  
> 4. [비교](#4-비교)  
> 5. [umask](#5-umask)  

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 권한: **허가권, 소유권** 에 대해 학습했다.

- 이번 챕터에서는 **특수 권한** 에 대해 학습해보자.
    
<br>

---

# 1. SetUID

> **_기본 기능: 소유자만이 접근 가능한 파일을 '일반 유저'도 접근할 수 있도록 일시적 권한 부여_**


- 허가권의 UID 부분의 `x`를 `s`로 바꿔서 일시적인 권한을 부여하는 걸 말한다.  
    - `rwxr-xr-x : 755` --(SetUID)--> `rwsr-xr-x : 4755`

- 리눅스에서는 setUID로 설정된 게 매우 많기 때문에, setGID보다 더 중요하다. 



<br>

---

# 2. SetGID

> **_기본 기능: 소유자만이 접근 가능한 파일을 '그룹 유저'도 접근할 수 있도록 일시적 권한 부여_**


- 허가권의 GID 부분의 `x`를 `s`로 바꿔서 일시적인 권한을 부여하는 걸 말한다.  
    - `rwxr-xr-x : 755` --(SetGID)--> `rwxr-sr-x : 2755`




<br>

---

# 3. Stickbit(sb)

> **_기본 기능: 디렉토리에 파일을 생성, 삭제 가능한 권한을 부여하며, ❗️삭제는 파일의 소유권자 및 슈퍼 유저(root)만 가능_**


- 허가권의 other 부분의 `x`를 `t`로 바꿔서 일시적인 권한을 부여하는 걸 말한다.  
    - `rwxr-xr-x : 755` --(stickbit)--> `rwxr-xr-t : 1755`


<br>

---

# 4. 비교

```yml
# 변경 전             
#      setUID   setGID    stickybit        
755 - r  w 'x'  r  -  x   r  -  x
755 - r  w 'x'  r  - 'x'  r  -  x 
755 d r  w 'x'  r  -  x   r  - 'x' 

# 변경 후
#      setUID     setGID   stickybit        
4755 - r  w 's'  r  -  x   r  -  x
2744 - r  w  x   r  - 's'  r  -  x
1755 d r  w  x   r  -  x   r  - 't'
```


<br>

---

# 5. umask

> **_기본 기능: 사용자가 새로 생성되는 파일이나 디렉토리에 자동으로 부여되는 권한(허가권)을 설정하는 값_**
> **_umask [mask]_**

![image](https://user-images.githubusercontent.com/78094972/188956769-189265ac-226f-4b78-9395-634ad09a7ca0.png)

여러 사용자가 파일과 디렉토리를 생성하려고 할 때, 동일한 권한을 부여한다고 하자. 

이 때 umask 값이 설정되어 있으면 손쉽게 동일한 권한을 가지는 파일과 디렉토리를 여러 명의 사용자가 생성할 수 있다.

그래서 사전에 umask 값을 설정해놓으면 원하는 허가권을 가진 데이터를 생성할 수 있다.

<br>

---
# Practice

## 준비 과정

```yml
[root@ip-172-31-3-4 ~]# mkdir /sb

[root@ip-172-31-3-4 ~]# ls -ld /sb
drwxr-xr-x 2 root root 6 Sep  7 19:10 /sb

# 위 허가권과 달리 일반 사용자 부분에 t가 붙여진 걸 알 수 있는데, 이게 Stickbit 다.
[root@ip-172-31-3-4 ~]# chmod 1777 /sb
[root@ip-172-31-3-4 ~]# ls -ld /sb/
drwxrwxrwt 2 root root 6 Sep  7 19:10 /sb

# 테스트를 하기 위해서 user를 더 생성한다.
[root@ip-172-31-3-4 ~]# useradd usertest1
[root@ip-172-31-3-4 ~]# useradd usertest2

# 비밀번호 설정 명령어: passwd [user name]
[root@ip-172-31-3-4 ~]# passwd usertest2
Changing password for user usertest2.
New password: 
Retype new password: 
passwd: all authentication tokens updated successfully.

[root@ip-172-31-3-4 ~]# passwd usertest2
[root@ip-172-31-3-4 ~]# tail /etc/shadow
...
ec2-user:!!:19242:0:99999:7:::
apache:!!:19242::::::
usertest1:!!:19242:0:99999:7:::
usertest2:$6$LdUGwIvr$kBRUCRdtZ8exyWyQzhghyBKdubAGDxXZzh0.h10Q2JlmfUE9O/Y6uvHTQ81lyClS6Atrcrhw6Y8a/yTXfJgVS/:19242:0:99999:7:::

# 다른 terminal 창을 켜서, 사용자 계정으로 로그인하기
> ssh -i dalkom.pem ec2-user@3.36.89.64

[ec2-user@ip-172-31-3-4 /]$ cd sb
[ec2-user@ip-172-31-3-4 /]$ touch testfile-ec2
```

- 다음으로 ec2-user 계정으로 로그인하여 `/sb` 경로에 `touch estfile-ec`
- 생성한 usertest2 계정으로 로그인하기: `ssh -i ~/.ssh/dalkom.pem usertest2@3.36.89.64`
    - 하지만, `Permission denied` 발생  


```yml
# root 계정 터미널에 입력하기
[ec2-user@ip-172-31-3-4 /]$ mkdir /home/usertest2/.ssh
[ec2-user@ip-172-31-3-4 /]$ cp /home/ec2-user/.ssh/authorized_keys /home/usertest2/.ssh
[root@ip-172-31-3-4 /]# chown -R usertest2:usertest2 /home/usertest2/.ssh
[root@ip-172-31-3-4 /]# service sshd restart
Redirecting to /bin/systemctl restart sshd.service

# 다른 터미널에서 usertest2 로그인 하여 확인해보기
> ssh -i ~/.ssh/dalkom.pem usertest2@3.36.89.64
[usertest2@ip-172-31-3-4 ~]$ 

# 나머지 usertest1도 위와 동일한 과정을 거쳐서 로그인한 결과
[usertest1@ip-172-31-3-4 ~]$ 
```

<br>

## 다른 계정으로 삭제해보기

```yml
# usertest2 계정으로 cd /sb
[usertest2@ip-172-31-3-4 sb]$ rm -rf testfile-ec2
rm: cannot remove ‘testfile-ec2’: Operation not permitted

# ec2-user 계정으로 들어가 삭제하기
[ec2-user@ip-172-31-3-4 sb]$ rm -rf testfile-ec2

# ec2-user가 삭제할 수 있는 것은 이 파일의 생성자이기 때문이고, 이외에 root 계정에서도 삭제 가능하다.

# 이번에는 testuser2에서 생성해보고, ec2-user에서 삭제시도를 해보자.
# 그러면 똑같이 아래와 같은 에러가 뜬다.
rm: cannot remove ‘testfile-ec2’: Operation not permitted
```

## stickbit 확인하기

- 허가권에서 맨 뒷 자리에 `t`를 확인할 수 있다. 

```yml
[root@ip-172-31-3-4 /]# ls -l /
total 16
...
drwxrwxrwt   2 root root    6 Sep  7 20:12 sb
...
drwxrwxrwt   9 root root  249 Sep  7 07:52 tmp
```

🔆 tmp 안에는 삭제해도 상관없는 파일들 예를 들어 다운로드 받은 파일들을 담아둔다. 왜냐하면 이 안에있는 곳은 시스템 종료 후 키면 다 삭제되고 초기 상태로 돌아가기 때문이다. 

<br>

## umask

- 4자리가 나왔는데, 맨 앞 자리 즉 네 번째 자리는 특수 자리이므로 고려하지 않고, 세 번째자리까지 고려하자.

### root

```yml
[root@ip-172-31-3-4 /]# umask
0022
```

- 위 unmask에 의하면 관리자의 경우, directory는 755로 생성되고, File은 644로 생성되는 걸 알 수 있다.

    ```yml
    [root@ip-172-31-3-4 sb]# mkdir a
    [root@ip-172-31-3-4 sb]# touch b
    [root@ip-172-31-3-4 sb]# ls -l
    total 0
    drwxr-xr-x 2 root root 6 Sep  7 20:29 a
    -rw-r--r-- 1 root root 0 Sep  7 20:29 b
    ```

    - `drwxr-xr-x`: 755 의미 
    - `-rw-r--r--`: 644 의미

- 그러면 umask 수정 후, 다시 directory와 file을 생성해보자.

    ```yml
    [root@ip-172-31-3-4 sb]# umask 0033
    [root@ip-172-31-3-4 sb]# mkdir a1
    [root@ip-172-31-3-4 sb]# touch b1
    [root@ip-172-31-3-4 sb]# ls -l
    total 0
    drwxr--r-- 2 root     root     6 Sep  7 20:36 a1
    -rw-r--r-- 1 root     root     0 Sep  7 20:36 b1
    
    # 원상복귀
    [root@ip-172-31-3-4 sb]# umask 0022
    ```

    - `drwxr--r--`: 744 의미 
    - `-rw-r--r--`: 644 의미
        - File은 동일한 이유: 기존 umask에서도 w,x 권한은 없었기 때문



<br>

### user

```yml
[ec2-user@ip-172-31-3-4 /]# umask
0002
```

- 위 unmask에 의하면 user의 경우, directory는 775로 생성되고, File은 664로 생성되는 걸 알 수 있다.


    ```yml
    [ec2-user@ip-172-31-3-4 sb]# mkdir c
    [ec2-user@ip-172-31-3-4 sb]# touch d
    [ec2-user@ip-172-31-3-4 sb]# ls -l
    total 0
    drwxrwxr-x 2 ec2-user ec2-user 6 Sep  7 20:31 c
    -rw-rw-r-- 1 ec2-user ec2-user 0 Sep  7 20:31 d
    ```

    - `drwxrwxr-x`: 775 의미 
    - `-rw-rw-r--`: 664 의미

- 그러면 umask 수정 후, 다시 directory와 file을 생성해보자.

    ```yml
    [ec2-user@ip-172-31-3-4 sb]# umask 0003
    [ec2-user@ip-172-31-3-4 sb]# mkdir c1
    [ec2-user@ip-172-31-3-4 sb]# touch d1
    [ec2-user@ip-172-31-3-4 sb]# ls -l
    total 0
    drwxrwxr-- 2 ec2-user ec2-user 6 Sep  7 20:48 c1
    -rw-rw-r-- 1 ec2-user ec2-user 0 Sep  7 20:48 d1
    ```

    - `drwxrwxr--`: 774 의미 
    - `-rw-rw-r--`: 664 의미



<br>

## passwd

- `/etc/passwd`에는 사용자 정보들이 담겨져있는데, 직접 찾기에는 매우 많기 때문에 `find` 명령어를 사용하자.
    - 우리가 찾고 싶은 곳은 파일이므로 `-type f`를 사용한다.
    - 그중에서 실행파일을 찾아야 하므로 `which passwd`를 사용한다.

```yml
[root@ip-172-31-3-4 ~]# find / -name passwd -type f
/etc/pam.d/passwd
/etc/passwd
/usr/bin/passwd
/usr/share/bash-completion/completions/passwd

[root@ip-172-31-3-4 ~]# which passwd
/usr/bin/passwd

[root@ip-172-31-3-4 ~]# ls -l /usr/bin/passwd
-rwsr-xr-x 1 root root 27776 Feb 13  2020 /usr/bin/passwd
```

- `-rwsr-xr-x`: s가 있으므로 4000이 추가되기 때문에, 4755다. 
- 패스워드를 바꾸고 싶으면 `passwd`를 사용하자.  

    ```yml
    [usertest2@ip-172-31-3-4 ~]$ passwd
    Changing password for user usertest2.
    Changing password for usertest2.
    (current) UNIX password:
    New password:
    ...
    ```

    - 위와 같이 진행되어 결국 바뀐다. 
    - `usr/bin/passwd` 의 일반 사용자 부분(other)에 `x`가 있어서 실행이 가능한 것이고, 실행 후 바꿀 수 있던 것은 `s`가 있기 때문이다.

### passwd 권한 수정하기

- passwd 의 권한을 수정해보자.

    ```yml
    [root@ip-172-31-3-4 ~]# chmod 755 /usr/bin/passwd
    -rwxr-xr-x 1 root root 27776 Feb 13  2020 /usr/bin/passwd
    ```

    - 위 내용을 보면 `s`가 사라진 걸 알 수 있다.


- user 계정으로 접속하여 password를 수정해보자.  

    ```yml
    [usertest2@ip-172-31-3-4 ~]# passwd
    (current) UNIX password:
    New password:
    Retype new password:
    passwd: Authentication token manipulation error
    ```

    - 실행은 되지만 권한으로 인한 error가 발생했다.  
    - 위 /usr/bin/passwd의 소유권이 root이기 때문에, user는 변경이 불가능하다. 그런데 user가 스스로 변경이 가능하기 위해서 `s`를 사용한다. 위 경우에서 본 것처럼 사용자가 변경하면 안되는 것은 `s`가 없어야 한다. 

- 위 경우 외에도 root 권한이지만 user에 permission 권한을 줘서 접근, 수정, 읽기가 가능한 파일이 또 있다.

    ```yml
    [root@ip-172-31-3-4 ~]# ls -l /bin/su
    -rwsr-xr-x 1 root root 32032 Apr 14 21:36 /bin/su
    ```
    - 위 파일도 user permission에 권한을 다 빼버리면 사용자가 할 수 있는 게 없다.  


<br>

---
# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)