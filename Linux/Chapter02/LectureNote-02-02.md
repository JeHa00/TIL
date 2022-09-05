# 0. Introduction

> 1. [mkdir](#21-mkdir)
> 2. [rmdir](#22-rmdir)
> 3. [rm](#23-rm)
> 4. [alias](#24-alias)
> 5. [touch](#25-touch)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- 지난 챕터에는 AWS를 사용한 Linux 명령어 학습 환경 조성을 하는데 목적을 두었다면, 이번에는 Linux 기본 명령어를 실습해보겠다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 이번 소챕터에서는 mkdir, rmdir, rm, alias, touch를 학습해본다.  

<br>

---

# 2. mkdir, rmdir, rm, alias, touch

<br>

## 2.1 mkdir

> **_기본 기능: 디렉토리 생성(make directory)_**  
> **_mkdir [옵션] [디렉토리명]_**  

| 옵션 | 의미|
| ---- | ---- |
| -m, --mode | 디렉토리의 기본 권한을 지정 |
| -p, --parents | 필요한 경우, 상위 경로까지 생성|
| --help | 도움말 표시 |
| -version | 버전 정보 표시 | 


### 디렉토리 생성하기

```yml
[root@ip-172-31-8-107 ~]# cd /home/ec2-user
[root@ip-172-31-8-107 ec2-user]# mkdir ./test2
[root@ip-172-31-8-107 ec2-user]# ls
adjtime test test2
```

- 하지만 아래와 같이 생성하려고 한다면 Error가 발생한다. 
    - 왜냐하면 존재하지 않은 디렉토리 안에다가 생성하려고 했기 때문이다.

    ```yml
    [root@ip-172-31-8-107 ec2-user]# rmdir test2
    [root@ip-172-31-8-107 ec2-user]# mkdir ./test2/test3
    mkdir: cannot create directory 'test2/test3': No such file or directory
    ```

### 위 Error의 해결책: -p 

```yml
[root@ip-172-31-8-107 ec2-user]# mkdir -p ./test2/test3
[root@ip-172-31-8-107 ec2-user]# cd ./test2/test3
[root@ip-172-31-8-107 test3]# pwd
/home/ec2-user/test2/test3
```



<br>

## 2.2 rmdir

> **_기본 기능: 빈 디렉토리만 삭제(remove directory)_**  
> **_mkdir [옵션] [디렉토리명]_**  

| 옵션 | 의미 |
| ---- |---- |
|-p, --parents | 필요한 경우 상위 경로까지 삭제 |
| --help | 도움말 표시 |
| -version | 버전 정보 표시 |


- 빈 디렉토리만 삭제한다는 걸 확인해보자.

```yml
[root@ip-172-31-8-107 test3]# pwd
/home/ec2-user/test2/test3

[root@ip-172-31-8-107 test3]# cd .. 
[root@ip-172-31-8-107 test2]# rmdir test3 
[root@ip-172-31-8-107 test2]# ls
# 아무것도 확인할 수 없다.

[root@ip-172-31-8-107 ec2-user]# cp ./adjtime ./test2
[root@ip-172-31-8-107 ec2-user]# ls ./test2
adjtime

[root@ip-172-31-8-107 ec2-user]# rmdir ./test2
rmdir: failed to remove './test2': Directory not empty
```

- 옵션 `-p`의 경우, 상위 경로도 빈 directory여야만 삭제 가능하다.

```yml
[root@ip-172-31-8-107 ec2-user]# mkdir -p ./a/b/c
[root@ip-172-31-8-107 ec2-user]# ls -lR ./a
./a:
total 0
drwxr-xr-x 3 root root 15 Sep  2 06:20 b

./a/b:
total 0
drwxr-xr-x 2 root root 6 Sep  2 06:20 c

./a/b/c:
total 0

[root@ip-172-31-8-107 ec2-user]# rmdir -p ./a/b/c
[root@ip-172-31-8-107 ec2-user]# ls
adjtime test test2
# 즉, ./a 까지 다 삭제되었다. 

[root@ip-172-31-8-107 ec2-user]# mkdir -p ./test2/test3/test4
[root@ip-172-31-8-107 ec2-user]# rmdir -p ./test2/test3/test4
rmdir: failed to remove directory './test2': Directory not empty
# ./test2 에는 파일이 존재하기 때문에 ./a와 달리 삭제되지 않았다. 
```

<br>

## 2.3 rm

> ❗️**_기본 기능: 파일 or 디렉토리 삭제_**  
> **_rm [옵션] [디렉토리명]_**   

- rm 명령을 사용하면 삭제를 진짜할 것인지 물어본다.  

| 옵션 | 의미 |
| ---- |---- |
| -f, --force | 파일 및 디렉토리 삭제 시, **의사(y/n)를 물어보지 않는다** |
| -r, -R, --recursive | 일반 파일이면 그냥 삭제. 하지만, 디렉토리이면 **디렉토리를 포함한 하위 경로와 파일을 모두 삭제** |
| -v, --verbose | 각 파일 지우는 정보를 상세히 확인 |
| --version | 버전 정보 표시 |


❗️❗️ rm 명령어를 사용할 때는 항상 경로를 확인하고 나서, 사용해야 하며 -rf 옵션도 신중히 사용해야 한다.


```yml
[root@ip-172-31-8-107 ec2-user]# cd ./test2
[root@ip-172-31-8-107 test2]# ls
adjtime

[root@ip-172-31-8-107 test2]# rm adjtime
rm: remove regular file 'adjtime'? y
[root@ip-172-31-8-107 test2]# ls
```

### -f 사용하여 삭제하기

- 다시 다른 directory에 복사해오자.

```yml
[root@ip-172-31-8-107 ec2-user]# mkdir -p ./a/b/c/d/
[root@ip-172-31-8-107 ec2-user]# cp ./adjtime ./a
[root@ip-172-31-8-107 ec2-user]# cp ./adjtime ./a/b/c
[root@ip-172-31-8-107 ec2-user]# cp ./adjtime ./a/b/c/d/
[root@ip-172-31-8-107 ec2-user]# rm -f ./a/b/c/d/adjtime/
[root@ip-172-31-8-107 ec2-user]# ls -l ./a/b/c/d/
total 0
```

- 위에서 삭제한 것과 달리, 물어보지 않고 바로 삭제했다.

```yml
[root@ip-172-31-8-107 ec2-user]# rm ./a/b/c/d/adjtime
```

### -r 사용하여 삭제하기

- 삭제하려는 디렉토리를 포함하여 그 안에 있는 디렉토리와 파일들도 삭제하도록 묻는 옵션

```yml
[root@ip-172-31-8-107 ec2-user]# rm -r ./a/
rm: descend into directory './a/'?  y
rm: descend into directory './a/b'? y
rm: descend into directory './a/b/c'? y
rm: remove directory './a/b/c/d'? y
rm: remove regular file './a/b/c/adjtime'? y
rm: remove directory './a/b/c'? y
rm: remove directory './a/b'? y
rm: remove regular file './a/adjtime'? y
rm: remove directory './a/'? y
```

### -rf 로 함께 사용하여 삭제하기

- 묻지 않아서 손쉽게 삭제할 수 있지만,

```yml
[root@ip-172-31-8-107 ec2-user]# mkdir -p ./a/b/c/d/
[root@ip-172-31-8-107 ec2-user]# cp ./adjtime ./a
[root@ip-172-31-8-107 ec2-user]# cp ./adjtime ./a/b/c
[root@ip-172-31-8-107 ec2-user]# cp ./adjtime ./a/b/c/d/
[root@ip-172-31-8-107 ec2-user]# rm -r ./a/
[root@ip-172-31-8-107 ec2-user]# ls
adjtime test

# 'a' directory가 삭제된 걸 알 수 있다.
```

### 삭제 시, 경로에 * 입력 

```yml
[root@ip-172-31-8-107 ec2-user]# rm -rf ./*
[root@ip-172-31-8-107 ec2-user]# ls -l
total 0
```

- 하지만 `./*`이 아니라, `/*`을 입력한다면??
    - 시스템 전체가 삭제되므로, 재설치해야한다.

<br>

## 2.4 alias

> **_기본 기능: 별칭 지정, 복잡한 명령어와 옵션을 간단한 문자열로 치환(❗️일회성)_**
> **_alias [변수] = [값]_**

```yml
[root@ip-172-31-8-107 ec2-user]# alias rrf='rm -rf'
[root@ip-172-31-8-107 ec2-user]# mkdir -f ./a/b/c/d
[root@ip-172-31-8-107 ec2-user]# rrf ./a/
[root@ip-172-31-8-107 ec2-user]# ls
total 0
```

<br>

## 2.5 touch

> **_기본 기능: 파일 시간정보 변경 및 파일 생성_**
> **_touch [파일명]_**

- 크기가 0인 신규파일을 생성할 때, 많이 사용된다.  

    ```yml
    [root@ip-172-31-8-107 ec2-user]# touch jeha00
    [root@ip-172-31-8-107 ec2-user]# ls -l
    total 0
    -rw-r--r-- 1 root root 0 Sep  2 07:39 jehakim

    # 위에 root root 0 에서 0을 통해서 파일 크기가 0임을 알 수 있다.
    ```

- 기존에 동일 이름의 파일이 있을 경우, 생성시간 및 최종 수정시간 변경  

    ```yml
    # 위 코드에서 이어서 진행한다.

    # 다시 touch를 실행해보자.
    [root@ip-172-31-8-107 ec2-user]# touch jeha00
    [root@ip-172-31-8-107 ec2-user]# ls -l
    total 0
    -rw-r--r-- 1 root root 0 Sep  2 07:41 jehakim

    # 생성된 시간이 달라진 것을 알 수 있다.
    ```



<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)