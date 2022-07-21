# 0. Introduction

> 1.[git hooks](#1-git-hooks)
> 2.[git submodules](#2-git-submodules)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- Chapter 12로 Git 자체에 대한 마지막 강의로  `git hooks` 와 `git submodules`를 학습한다.  

<br>

---
# 1. git hooks

> **git 상의 이벤트마다 자동적으로 실행될 스크립트를 지정하는 것으로 '_자동화'_ 라고 생각하자.** 

- `git hooks`와 함께  Gitmoji에 대해 알아보자.  

- 커밋을 하면 자동적으로 푸쉬가 실행되게 하던가, 커밋 전 코드가 약속된 형식대로 되어있는지 확인하는 것 등등 '자동화'가 이뤄지게 한다.  

- 프로젝트 폴더 내의 `.git` > `hooks` 폴더를 들어가 확인하면 `.sample`이란 형식의 파일들이 들어있다.  

- 그러면 실습을 하기 위한 환경을 만들기 위해 **gitmoji** 를 설치해보자.  

<br>

## Gitmoji

- **Gitmoji란?**  
    - [gitmoji - README.md](https://github.com/carloscuesta/gitmoji)를 참고한다.  
    - an initiative to standardize and explain the use of emojis on GitHub commit messages.  
    - 깃헙의 커밋 메세지에 대해서 이모지 사용을 표준화하고 설명하는 것으로서, **_커밋 메세지에 관해 이모지를 사용하여 커밋의 의도 또는 목적을 쉽게 식별할 수 있는 방법이다._**  
    - 그래서 [gitmoji - list](https://github.com/carloscuesta/gitmoji-cli#list)에 나와있는대로 각 이모지마다 독립적인 의미를 가진다.  


- **Gitmoji 설치하기**

    - 먼저 node.js를 설치하기. 
        - command에 `npm -v`를 입력하여 version이 뜨면 성공적으로 설치된 것  
    
        ❗ node.js를 설치했어도 npm 명령어가 인식되지 않으면 vsc를 껐다가 다시 켜보자.  

    - 터미널 명령어를 사용하여 `gitmoji-cli`를 설치한다. 
        - `npm i -g gitmoji-cli` 
    
    
    - 그후, `gitmoji -i`를 입력하면, 아래 코드처럼 진행되면서 `prepare-commit.msg` 가 생성된다. 
    
    
    ```yml
    > gitmoji -i
    - Creating the gitmoji commit hook 
    Gitmoji commit hook created successfully 
    ```


<br>


## hook 과 Gitmoji 학습하기  

- 그러면 본격적으로 `hook`과 `gitmoji` 둘 다 학습해보자.  

- 파일의 변화를 준 후, `git add .`를 실행한다. 
    - 그 다음 `git commit` 만 입력하면, 이모지가 뜬다.   
    - 원하는 이모지 스펠링을 입력 후, 선택 및 엔터를 누른다.  
    - 남기기 원하는 commit mesage를 입력 후, 엔터를 누른다. 
    - vim 모드가 뜨면 `:wq`를 입력하여 저장 및 나간다.  


### gitmoji-cli hook 해제하기  

`hooks` 폴더에서 `prepare-commit-msg` file을 삭제하면 된다.  

<br>

---
# 2. git submodules

> 프로젝트 폴더 안에 또 다른 프로젝트가 포함될 때 사용한다.  

- 프로젝트 폴더 구성은 다음과 같다. 

```yml
# 경로: main-project
> dir
main-project-files  module-project-1  module-project-2
```
- main-project와 main-project 안에 module-project-1,2 도 git으로 관리된다.  
    - 그런데, module project가 물리적으로는 main-project 안에 있지만, git 관리는 따로 하고 싶을 때 어떻게 해야할까? 즉, main-project에 대한 git 관리에 module-project-1,2를 별도로 떨어뜨려 관리하고 싶으면 어떻게 해야할까? 
    - module-project 또한 git이 지속해서 관리해야하기 때문에, `.gitignore`에 등록하면 안된다.  




- 여러 프로젝트에 사용되는 공통모듈일 때 유용하다.  

## 사용해보기 

0. `main-project`에 `git remote add origin <Github addess>` 를 추가한다. 

1. 두 개의 프로젝트를 생성  

2. `main-project`에 서브모듈로 `submodule` 프로젝트 추가  

    -   `main-project` 디렉토리상 터미널에서 아래 명령어 실행  
        - `git submodule add (submodule의 GitHub 레포지토리 주소) (하위폴더명, 없을 시 생략)`
        - 프로젝트 폴더 내 `submodule` 폴더와 `.gitmodules` 파일 확인  
        - 스테이지된 변경사항 확인 뒤 커밋  
        - 양쪽 모두 수정사항 만든 뒤 `main-project` 에서 `git status`로 확인  

            ```yml
            $ git status
            On branch main
            Changes not staged for commit:
            (use "git add <file>..." to update what will be committed)
            (use "git restore <file>..." to discard changes in working directory)
            (commit or discard the untracked or modified content in submodules)
                    modified:   main.txt
                    modified:   submodule1 (modified content)
                    modified:   submodule2 (modified content)

            no changes added to commit (use "git add" and/or "git commit -a")
            ```

            - 그러면 `git add .`를 실행한 후 다시 확인해보자. 
            
            ```yml
            $ git add .
            $ git status
            On branch main
            Changes to be committed:
            (use "git restore --staged <file>..." to unstage)
                    modified:   main.txt

            Changes not staged for commit:
            (use "git add <file>..." to update what will be committed)
            (use "git restore <file>..." to discard changes in working directory)
            (commit or discard the untracked or modified content in submodules)
                    modified:   submodule1 (modified content)
                    modified:   submodule2 (modified content)
            ```
            - `submodule`의 변경사항은 포함되지 않음을 확인  


        - `main-project`에서 변경사항 커밋 후 푸시  -> `submodule`에서 변경사항 커밋 후 푸시 -> `main-project`에서 상태 확인 -> `main-project`에 커밋, 푸시 후 GitHub에서 확인  


> 즉, submodule로 등록되었으면 서브 모듈로 등록된 폴더 내부의 파일 변화는 감지할지라도, 서브 모듈 내부 파일들의 변화까지 한 번에 커밋되지는 않는다. 

3. 서브모듈 업데이트 

    - `main-project` 새로운 곳에 clone하기  
        - 서브모듈 폴더들은 존재해도 파일들은 없기 때문에 아래 단계를 진행한다. 

    - 아래 명령어들로 서브모듈 init 후 클론  
        - `git submodule init (특정 서브모듈 지정시 해당 이름만 입력)`
            - `.gitmodules`의 `[submodule "<submodule name>"]` 에서 `<submodule name>`을 입력한다.
            - ex) `git submodule init submodule1`

        - `git submodule update`   

        ```yml
        # 경로: main-project가 있는 folder  
        $ git submodule init
        $ git submodule update
        Cloning into 'C:/Users/rudtl/Desktop/Dev/GitHub/git-test/git-practice1/submodule1'...
        Cloning into 'C:/Users/rudtl/Desktop/Dev/GitHub/git-test/git-practice1/submodule2'...
        Submodule path 'submodule1': checked out '0434727626afe874b8a0ccaa4cd89ce716f14b37'
        Submodule path 'submodule2': checked out '8f86fe62491d0930f4d93963a3c42f7db852447b'
        (base) 
        ```

4. GitHub에서 `submodule`에 수정사항 커밋  
    - `git submodule update --remote`  

    ```yml
    $ git submodule update --remote
    remote: Enumerating objects: 5, done.
    remote: Counting objects: 100% (5/5), done.
    remote: Total 3 (delta 0), reused 0 (delta 0), pack-reused 0
    Unpacking objects: 100% (3/3), 627 bytes | 27.00 KiB/s, done.
    From https://github.com/JeHa00/git-submodule1
    033d9fe..e46b343  main       -> origin/main
    Submodule path 'submodule1': checked out 'e46b3437d1b2d7c3e03ebc6fabbc9956572399c7'
    remote: Enumerating objects: 5, done.
    remote: Counting objects: 100% (5/5), done.
    remote: Total 3 (delta 0), reused 0 (delta 0), pack-reused 0
    Unpacking objects: 100% (3/3), 626 bytes | 34.00 KiB/s, done.
    From https://github.com/JeHa00/sub2
    8de549f..4e300ab  main       -> origin/main
    Submodule path 'submodule2': checked out '4e300ab56e9282da6278decca5e067ee025c7aa7'
    ```

    - 서브모듈 안에 또 서브모듈 있을 시: `--recursive` 추가  


<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
