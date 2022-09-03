# 0. Introduction

> 1. [pwd](#11-pwd)
> 2. [cd](#12-cd)
> 3. [ls](#13-ls)
> 4. [cp](#14-cp)
> 5. [mv](#15-mv)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- 지난 챕터에는 AWS를 사용한 Linux 명령어 학습 환경 조성을 하는데 목적을 두었다면, 이번에는 Linux 기본 명령어를 실습해보겠다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 이번 소챕터에서는 pwd, cd, ls, cp, mv를 학습해본다.  

<br>

---

# 1. pwd, cd, ls, cp, mv

<br>

## 1.1 pwd

> **_현재 경로 보기(parent working directory)_**

```yml
[root@ip-172-31-8-107 ~]# pwd
/root
```

<br>

## 1.2 cd

> **_원하는 디렉토리로 이동 (change directory의 약자)_**

|인자값| 의미|
|----| ----|
| . | 현재 디렉토리 |
| .. | 상위 디렉토리 |
| ~ | 로그인 되어 있는 계정(사용자)의 Home directory|
| ~계정명 | 지정한 계정(사용자)의 Home directory |

```yml
[root@ip-172-31-8-107 ~]# pwd
/root

# 아래 명령어는 동일
[root@ip-172-31-8-107 ~]# cd /home/
[root@ip-172-31-8-107 ~]# cd /home
[root@ip-172-31-8-107 home]# pwd
/home

[root@ip-172-31-8-107 home]# cd .
[root@ip-172-31-8-107 home]# cd ./
[root@ip-172-31-8-107 home]# pwd
/home

[root@ip-172-31-8-107 ~]# cd ~
/root

# 계정명
[root@ip-172-31-8-107 ~]# cd ~ec2-user
[root@ip-172-31-8-107 ec2-user]# pwd
/home/ec2-user


[root@ip-172-31-8-107 ec2-user]# cd /
[root@ip-172-31-8-107 /]# pwd
/
[root@ip-172-31-8-107 /]# ls -l
total 16
lrwxrwxrwx   1 root root    7 Aug 15 20:20 bin -> usr/bin
dr-xr-xr-x   4 root root 4096 Aug 15 20:23 boot
drwxr-xr-x  15 root root 2900 Aug 30 04:09 dev
drwxr-xr-x  80 root root 8192 Aug 30 04:09 etc
....

[root@ip-172-31-8-107 /]# cd boot
[root@ip-172-31-8-107 boot]# pwd
/boot

# 아래 error는 올바르다. 왜냐하면 boot는 계정이 아닌, directory이기 때문이다.
# 현재 계정에서 계정은 Root와 user 2가지 밖에 없기 때문이다.
[root@ip-172-31-8-107 /]# cd ~boot
-bash: cd: ~boot: No such file or directory

[root@ip-172-31-8-107 /]# cd home/ec2-user

# 상위 경로로 이동해보자. 
# 아래 두 명령어는 동일하다.
[root@ip-172-31-8-107 ec2-user]# cd ../ 
[root@ip-172-31-8-107 ec2-user]# cd .. 

[root@ip-172-31-8-107 home]# pwd 
/home
[root@ip-172-31-8-107 home]# cd ..
[root@ip-172-31-8-107 /]# pwd
/

# 또는 한 번에 최상위 경로로 이동할 수 있다. 
[root@ip-172-31-8-107 ec2-user]# cd ../../../
[root@ip-172-31-8-107 /]# pwd
/
# 아래 ls 실습으로 이어진다.
```

<br>

## 1.3 ls

> **_기본 기능: 디렉토리 목록을 확인_**    
> **_ls [옵션] [디렉토리 or 파일]_**  

- 각 옵션들을 한 번에 합쳐서 입력 가능하다. 
    - ex) -ald, 순서는 상관 X 

|옵션| 의미 |
| ---- | ---- |
| -a, -all | .을 포함한 경로 안의 모든 파일 및 디렉토리 |
| -l, --format = long | 지정한 디렉토리의 내용을 상세하게 출력|
| -d, --directory | 지정한 디렉토리의 정보를 출력 |
| -F, --classfiy | 파일 형식을 알리는 문자를 각 파일 뒤에 추가 |
| -R,--recursive | 하위 경로와 그 안에 있는 모든 파일들도 같이 나열 |


- 위에 cd 명령어 실습으로부터 바로 이어진다. 

### 디렉토리 목록 확인


```yml
[root@ip-172-31-8-107 /]# ls
bin boot dev etc home lib lib64 local media mnt opt proc root run sbin srv sys tmp usr var
```

### 숨긴 내용까지 다 확인

```yml
[root@ip-172-31-8-107 /]# ls -a
.  ..  .autorelabel  bin  boot  dev  etc  home  lib  lib64  local  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
```

### 목록 상세히 확인 

- 하지만, 위 명령어로는 무엇이 폴더이고, directory인지 구분할 수 없다. 
    - d는 최상위 디렉토리를 의미

    ```yml
    [root@ip-172-31-8-107 /]# ls -l
    lrwxrwxrwx   1 root root    7 Aug 15 20:20 bin -> usr/bin
    dr-xr-xr-x   4 root root 4096 Aug 15 20:23 boot
    drwxr-xr-x  15 root root 2900 Aug 30 04:09 dev
    drwxr-xr-x  80 root root 8192 Aug 30 04:09 etc
    drwxr-xr-x   3 root root   22 Aug 30 04:09 home
    ...
    ```

### 옵션 합쳐서 사용하기

- 만약 숨겨진 내용까지 상세히 목록을 보고 싶으면 `ls -al`로 옵션을 합쳐서 사용한다.  

### 경로 이동 없이 목록 보기

- 경로 이동 없이, 찾고 싶은 경로의 목록을 보고 싶을 때: `ls -l /`
    
    ```yml
    # 현재 경로
    [root@ip-172-31-8-107 ec2-user]# pwd
    /home/ec2-user

    [root@ip-172-31-8-107 ec2-user]# ls -l /
    # 그러면 위에 / 경로에서 ls -l 을 입력한 결과와 동일하다.  
    ```

- 이번에는 경로 이동 없이, 원하는 디렉토리 정보만을 출력해보자. 

    ```yml
    [root@ip-172-31-8-107 ec2-user]# ls -ld /var/
    drwxr-xr-x 19 root root 269 Aug 30 04:09 /var/   
    ```

- `-d` 옵션 없이 사용하면 `/var/` directory 안의 리스트를 볼 수 있다.
    - 하지만, 디렉토리 경로를 모르면 속성을 확인해볼 수 없다는 단점이 있다.

    ```yml
    [root@ip-172-31-8-107 ec2-user]# ls -l /var/
    total 8
    drwxr-xr-x  2 root root   19 Aug 15 20:22 account
    drwxr-xr-x  2 root root    6 Apr  9  2019 adm
    drwxr-xr-x  6 root root   63 Aug 15 20:22 cache
    drwxr-xr-x  3 root root   18 Aug 15 20:22 db
    drwxr-xr-x  3 root root   18 Aug 15 20:22 empty
    ...
    ```



### 파일 형식 자세히 알기   

- `-F`를 사용하니, `/`가 뜬 것을 알 수 있다. 
- 이는 directory임을 의미한다.  

- 화살표가 있는 것들은 앞에 `l`임을 알 수 있다.  

- 이 `l`이 의미하는 바는 symbolic link를 의미한다.  
    - 실제로 존재하는 것이 아닌, 화살표가 가리키는 것을 가리킨다는 의미다.
    - 그리고 10이란 숫자를 볼 수 있는데, 이는 10byte를 의미하며, 이 크기는 바로가기 파일의 크기와 동일하다.  

```yml
[root@ip-172-31-8-107 ec2-user]# ls -lF /var/
total 8
...
lrwxrwxrwx  1 root root   11 Aug 15 20:20 lock -> ../run/lock/
drwxr-xr-x  7 root root 4096 Sep  1 03:50 log/
lrwxrwxrwx  1 root root   10 Aug 15 20:20 mail -> spool/mail/
...
```


- 또한, 아래 출력을 보면  `*`이 명칭 뒤에 붙어 있고, 각 출력행들의 맨 앞을 보면 `-`이 붙어있다. 이는 **파일** 을 의미한다.   

    ```yml
    [root@ip-172-31-8-107 ec2-user]# ls -lF /bin/
    total 153928
    ...
    -rwxr-xr-x 1 root root       32552 Aug 17  2018 auvirt*
    lrwxrwxrwx 1 root root           4 Aug 15 20:20 awk -> gawk*
    -rwxr-xr-x 1 root root         818 Sep 28  2020 aws*
    -rwxr-xr-x 1 root root        1139 Sep 28  2020 aws_completer*
    ...
    ```

    - 그리고, 아래 출력과 비교를 해보면 `/`가 없는 것들은 위와 같이 파일인데, 차이점은 `-x`가 아니라, `--` 임을 알 수 있다. 
        - 그 차이는 `-x`가 붙은 건 **실행파일** 을 의미한다.  

    ```yml
    [root@ip-172-31-8-107 home]# ls -lF /etc/
    total 1112  
    ...
    drwxr-xr-x  6 root root      100 Aug 15 20:22 yum/
    -rw-r--r--  1 root root      862 Jun 23 22:26 yum.conf
    drwxr-xr-x  2 root root       54 Aug 15 20:24 yum.repos.d/
    ```

### 경로의 하위 내용까지 출력하기

- 경로에 최상위 경로를 입력하면 다 출력되기 때문에, 이런 문제점으로 사용하지 않는 것이 나을 수도 있다.  

```yml
[root@ip-172-31-8-107 ec2-user]# ls -lR /var/
...
/var/cache/man/es/cat8:
total 0

/var/cache/man/fr:
total 20
-rw-r--r-- 1 root root   190 Aug 31 03:39 CACHEDIR.TAG
drwxr-xr-x 2 root root     6 Aug 30 05:48 cat1
drwxr-xr-x 2 root root     6 Aug 30 05:48 cat3
drwxr-xr-x 2 root root     6 Aug 30 05:48 cat5
drwxr-xr-x 2 root root     6 Aug 30 05:48 cat8
-rw-r--r-- 1 root root 16384 Sep  1 03:50 index.db
...
```


## 1.4 cp

> **_기본 기능: 파일 또는 디렉토리를 복사 (copy)_**  
> **_cp [옵션] [원본] [목적지]_**  

|옵션 | 의미 |
| ---- | ---- |
|-r, -R, --recursive | 하위 디렉토리와 파일을 모두 복사 |
| -p, --preserve | 원본 파일의 권한과 함께 복사 | 


- 디렉토리 하나를 만들어보자.

```yml
# 현재 ec2-user 경로에는 아무것도 없다.
[root@ip-172-31-8-107 ec2-user]# ls -l
total 0

# 디렉토리 생성 후, 생성한 디렉토리로 파일 복사
[root@ip-172-31-8-107 ec2-user]# mkdir /home/ec2-user/test
[root@ip-172-31-8-107 ec2-user]# ls
test

[root@ip-172-31-8-107 ec2-user]# cp /etc/adjtime /home/ec2-user
adjtime test

[root@ip-172-31-8-107 ec2-user]# cd test
[root@ip-172-31-8-107 test]# cp /etc/adjtime /home/ec2-user
[root@ip-172-31-8-107 test]# ls
adjtime

# test 안에 만들어지는 게 아닌, /home/ec2-user 안에 만들어진다.
```

### -r 사용하기 

```yml
# 상위 경로에 있는 adjtime을 현재 test로 복사해온다는 의미
[root@ip-172-31-8-107 test]# cp -r ../adjtime . 
[root@ip-172-31-8-107 test]# cd ../../../

# 다음과 같은 error가 뜬다. 
[root@ip-172-31-8-107 /]# cp /home/ec2-user/test/ ./
cp: omitting directory '/home/ec2-user/test/'

[root@ip-172-31-8-107 /]# cp -r /home/ec2-user/test/ ./
[root@ip-172-31-8-107 /]# ls 
... test ...

# 21 Sep 로 복사한 날짜와 시간을 확인할 수 있다.
[root@ip-172-31-8-107 /]# ls -l
...
drwxr-xr-x   2 root root   21 Sep  2 05:21 test
...

[root@ip-172-31-8-107 /]# ls -l /test/
...
-rw-r--r--  1 root root  16 Sep  2 05:21 adjtime

```

<br>

## 1.5 mv

> **_기본 기능: 파일 또는 디렉토리 원본을 이동(move)_**
> **_mv [옵션] [원본] [목적지]_**  

- 위에 cp 실습에서 이어진다.

```yml
[root@ip-172-31-8-107 /]# mv /test/ /home/
[root@ip-172-31-8-107 home]# ls
ec2-user test

[root@ip-172-31-8-107 home]# mv ./test/adjtime ./
[root@ip-172-31-8-107 home]# ls -l ./ ./test
./:
total 4
-rw-r--r-- 1 root     root      16 Sep  2 05:21 adjtime
drwx------ 4 ec2-user ec2-user 122 Sep  2 04:52 ec2-user
drwxr-xr-x 2 root     root       6 Sep  2 05:31 test

./test:
total 0
```

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)