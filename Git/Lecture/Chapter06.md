# 0. Introduction

> 1. [Git help](#1-git-help)
> 2. [Git config](#2-git-config)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)를 중심으로 [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)을 참고하여 공부한 내용입니다.

<br>

---

# 1. Git help

- git 명령어가 기억나지 않을 때, git 명령어의 상세한 옵션이 기억나지 않을 때 이에 대한 도움 받을 수 있는 `git help`에 대해 알아보자.

- **git의 전체 명령어가 궁금할 때 사용하는 명령어**

  - `git help` : git의 여러 명령어를 간략히 보여준다.

  - `git help -a`: git의 모든 명령어를 보여준다.
    - `j`: 내리기
    - `k`: 올리기
    - `:q` : 닫기

- **특정 명령어에 대해 궁금할 때 사용하는 명령어**

  - `git (명령어) -h`: 해당 명령어의 설명과 옵션 보기

    - ex) git commit -h

  - `git help (명령어)` or `git (명령어) --help`: 명령어에 대한 설명을 웹에서 자세한 설명을 보고 싶을 때
    - 웹에서 열리지 않을 시 끝에 `-w`를 붙여 명시

<br>

---

# 2. Git config

- Git을 설정하는 `git config`에 대해서 보다 자세히 알아보자.

- **global 설정과 local 설정**

  - config를 `--global`과 함께 지정하면 전역으로 설정된다.
    - ex) `git config --global user.name`

- **현재 모든 설정값 보기**

  - `git config (global) --list`
  - 전역으로 모든 설정값을 볼 때랑 아닐 때랑 출력이 다르다.

- **설정값을 에디터에서 보기**

  - 기본 설정값은 Vim editor
  - `git config -e`
  - 기본 에디터인 Vim에서 Visual studio code 등 IDE로 보고 싶으면 아래 명령어를 입력한다.
    - `git config --global core.editor "code --wait"`
    - code 자리에 원하는 편집 프로그램의 .exe 파일 경로를 연결해도 변경할 수 있다.
    - 이 명령어를 실행한 후, git config -e를 실행한다. 이와 같이 설정하면 커밋 메시지 입력창도 해당 에디터에서 열리게 된다.
    - `--wait`: 에디터에서 수정하는 동안 CLI를 정지한다.

- **에디터 설정을 되돌리고 싶으면?**
  - `git config --global -e`로 편집기를 연 뒤, [core] 란에 `excludesfile` 과 `editor` 부분을 삭제하고 저장하면 된다.

<br>

### 이외의 유용한 설정들

- **줄바꿈 호환 문제 해결**

  - `git config --global core.autocrlf (윈도우: true / 맥: input)`

- **pull 기본 설정을 `merge` 또는 `rebase`로 설정**

  - `git config pull.rebase false` 또는 `git config pull.rebase true`

- **기본 브랜치명 설정**

  - `git config --global init.defaultBranch main`

- **push 시, 로컬과 동일한 브랜치명으로 설정**

  - `git config --global push.default current`

<br>

### 단축키 설정

[2.7 Git의 기초 - Git Alias](https://git-scm.com/book/ko/v2/Git%EC%9D%98-%EA%B8%B0%EC%B4%88-Git-Alias) 를 참고한다.

`git config --global alias.(단축키) "명령어"` 를 사용한다.

예를 들어 `git config --global alias.cam "commit -am"` 로 사용한다.

하지만, 이 방식은 나중에 사용하는 게 낫다고 판단된다. 전반적인 명령어를 안보고 사용할 수 있는 수준에 이르면 사용하자.


❗ 단축키 설정을 했지만, `Expansion of alias failed; not a git command` 이와 같은 에러가 발생했다면 `git update-git-for-windows`를 사용하여 업데이트 후, 다시 해보자. 

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
