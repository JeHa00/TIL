# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- Chapter 11에서는 분석하고 디버깅하는 것에 대해 다룬다.  
- 이번 단원에서는 차이를 살펴보는 `git diff` 명령어에 대해 알아본다.  

<br>

---

# Git diff

> working directory의 변경사항을 확인하는 명령어  

- `git diff` 명령어를 통해서 현재 파일들의 변경사항, staging area에 올라간 파일들의 변경사항, 브랜치 간의 변경사항, 커밋 간의 변경사항들 여러 관계 사이의 변경사항을 알 수 있다.  


- 먼저 실습하기 위한 변경 사항을 임의로 만들어본다.  

    ```yml
    $ git diff
    # leopards.yaml
    diff --git a/leopards.yaml b/leopards.yaml
    index 9c8086a..eca1191 100644
    --- a/leopards.yaml
    +++ b/leopards.yaml
    @@ -13,5 +13,3 @@ members:
    - Dongho
    - Drax
    - Groot
    -  - Onepiece
    -  - No jam Thore

    # panthers.yaml
    diff --git a/panthers.yaml b/panthers.yaml
    index 8e2ee18..fb9738a 100644
    --- a/panthers.yaml
    +++ b/panthers.yaml
    @@ -11,3 +11,4 @@ members:
    - Freddie
    - Arachi
    - Hoki
    +  - Harus
    \ No newline at end of file

    # tigers.yaml
    diff --git a/tigers.yaml b/tigers.yaml
    index cd48481..c8ca3e0 100644
    --- a/tigers.yaml
    +++ b/tigers.yaml
    @@ -11,5 +11,3 @@ members:
    - George
    - Tyler
    - Kim
    -  - Gamora
    -  - Nebula
    ```


- **파일명만 확인**: 
    - 변경사항 있는 파일의 이름들만 확인
    - `git diff --name-only`

    ```yml
    $ git diff --name-only
    leopards.yaml
    panthers.yaml
    tigers.yaml
    ```


- **스테이지의 확인**: 
    - working directory에 있다가 staging area에 올라간 파일들의 변경사항을 확인하고자 할 때 사용
    - `git diff --staged`: `git diff --caced`도 이와 동일한 명령어  

    ```yml
    $ git add .
    $ git diff --staged

    # git diff를 한 것과 동일한 결과가 나온다. 
    ```


- **커밋간의 차이 확인**
    - `git diff (커밋 1) (커밋 2)`
        - 또는 커밋 해시나 HEAD 번호로도 가능하다.  
        - 현재 커밋과 비교하려면 이전 커밋만 입력한다.  

    ```yml
    # 지난 번에 학습한 단축키 설정 명령어로 정한 git log 명령어를 사용한다.  
    # 그래서 두 개의 커밋을 골라보자.  
    $ git gg

    $ git diff 7a6d996 09994e8 

    # 또는 HEAD 번호를 사용해보자. 
    $ git diff HEAD~ HEAD~10
    ```

- **그러면 git diff의 다른 옵션과 같이 사용해보자.**  
    ```yml
    $ git diff --name-only HEAD~3 HEAD~7
    leopards.yaml
    panthers.yaml
    pumas.yaml
    tigers.yaml
    ```


- **브랜치간의 차이 확인**
    - `git diff (브랜치 1) (브랜치 2)`

    ```yml
    $ git branch -a 
    citrus
    fruit
    * main
    root

    $ git diff main root
    diff --git a/onion b/onion
    deleted file mode 100644
    index e69de29..0000000
    ```


<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
