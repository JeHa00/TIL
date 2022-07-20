# 0. Introduction

> 1.[git hooks](#1-git-hooks)
> 2.[git submodules](#2-git-submodules)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- Chapter 12에서는 `git hooks` 와 `git submodules`를 학습한다.  

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

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
