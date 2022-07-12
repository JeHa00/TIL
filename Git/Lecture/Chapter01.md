# 0. Introduction

> 1. [Git을 배워야 하는 이유](#1-git을-배워야-하는-이유)
> 2. [Git과 Sourcetree 설치](#2-git과-sourcetree-설치)
> 3. [Git 설정 vs 프로젝트 관리 시작하기](#3-git-설정-vs-프로젝트-관리-시작하기)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)를 중심으로 [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)을 참고하여 공부한 내용입니다.

<br>

---

# 1. Git을 배워야 하는 이유

> **_Git을 통해서 내가 만들고 있는 프로젝트의 시간과 차원을 종횡으로 넘나들 수 있다._**

- 시간: 프로젝트의 버전을 과거로 되돌리거나 특정 내역을 취소할 수 있다.
- 차원: 프로젝트의 여러 모드(버전)를 쉽게 전환하고 관리할 수 있다.

<br>

## 1.1 시간 관점에서 Git 장점

모든 버전을 백업하면 프로젝트가 진행될수록 차지하는 용량이 커진다.

v1 -> v2 -> v3 -> v4 -> v5 순서로 프로젝트가 진행되고 각 버전마다 백업할 때, v2에 잘못된 것을 찾았을 경우 v2부터의 버전을 다 수정해야 한다.

매우 큰 cost를 지불해야하지만, git은 손쉽게 가능하다.

<br>

## 1.2 차원과 관련된 Git 장점

회사에 현재 진행되고 있는 프로젝트에 나의 아이디어를 적용하고 싶다면??

그런데, 회사 프로젝트에 멋대로 논의되지 않는 코드를 추가할 수 없다.

이런 경우, 메인 프로젝트의 폴더를 복사해서 작업을 하면 되지만, 그러면 용량을 많이 차지할 것이다.

또한, 이 모든 안들의 변경사항을 모두 메인 프로젝트로 가져와야 한다면 어떻게 해야할까?

다른 버전의 프로젝트들의 변경사항을 하나 하나 확인해서 가져와야 하는데, 같은 파일에 다른 수정이 되어 있으면 어떻게 해야할까??

이런 문제 역시 Git에서 쉽게 해결할 수 있다.

<br>

---

# 2. Git과 Sourcetree 설치

Git과 sourcetree 설치 관련 설명은 이 링크 [Git, Sourcetree 설치](https://www.yalco.kr/@git-github/1-2/) 를 참고한다.

잘 설치되었는지를 확인하기 위해서 아래 명령어로 확인한다.

```yml
> git --version
```

<br>

### Git bash를 사용하는 이유

Git bash를 설치하고 사용하는 이유는 **_Git 사용에 적합한 터미널이고, Linux/Mac(Unix)에서 사용되는 CLI 명령어들을 윈도우에서 사용 가능_** 하다.

그래서 VSC 에서 git bash를 사용하도록 다음과 같이 설정한다.

VSC의 여러 터미널 중 하나를 Git bash로 설정한다.

VScode에서 `Ctrl` + `Shift` + `P` 를 입력하여, `Select Default Profile`을 검색하여 선택한다.

Git bash를 선택한 후, VSC의 termianl에서 `+` 로 새 창을 열어서 기본으로 Git Bash가 설정된 것을 확인한다.

<br>

### CLI vs GUI

실무에서 GIt을 매일 사용하는 사람으로서 이 2가지를 다 사용하는데, 어떻게 나눠서 사용하면 될까???

Git에서 뭔가를 실행하기 위한 어떤 **_명령들을 사용_** 할 때는 **_CLI_** 를 사용한다.

**_프로젝트의 상태를 Git상에서 자세히 살펴볼 때_** 는 **_Source Tree_** 를 사용한다.

하지만, **_학습자로서는 CLI를 먼저 학습한다._**

GUI는 편하지만 Git의 기능을 모두 사용할 수 없다.

그래서 CLI로 다 익혀놓은 후, Git을 잘 알게되면 GUI와 혼용해서 사용하는 방식으로 학습한다.

<br>

---

# 3. Git 설정 vs 프로젝트 관리 시작하기

## 3.1 Git 최초 설정

### 3.1.1 Git 전역으로 사용자 이름과 이메일 주소를 설정

이 설정은 Github 계정과는 별개로 나중에 나중에 협업할 때 이 작업을 누가했는지 알아서 연락하기 위함이다.

[What Is Git](https://github.com/JeHa00/TIL/blob/master/Git/1_WhatIsGit.md) 을 참고한다.

```yml
> git config --global user.name "(본인 이름)"

> git config --global user.email "(본인 이메일)"

> git config --global user.name

> git config --global user.email
```

<br>

### 3.1.2 기본 branch명 변경

기본 branch명이 `master` 였지만, 이 용어가 옛날에 흑인 노예의 주인을 연상시켜서 `main`으로 변경하고 있다.

그래서 Github 또한 기본 branch 명을 master로 바꿨다.

```yml
> git config --global init.defaultBanch main
```

<br>

## 3.2 프로젝트 생성 & Git 관리 시작

[git basics_1](https://github.com/JeHa00/TIL/blob/master/Git/2_GitBasics_1.md)을 참고한다.

<br>

---

# 4. Git에게 맡기지 않을 것들

Git의 관리에서 배제할 파일/폴더들은 어떠한 이유로 배제하고, 어떻게 배제할까??

- Git의 관리에서 특정 파일/폴더를 배제해야 할 경우

  - 포함할 필요가 없을 때

    - 자동으로 생성 또는 다운로드 되는 파일들 (빌드 결과물, 라이브러리)

  - 포함하지 말아야 할 때

    - 보안상 민감한 정보를 담은 파일

배제하는 방법으로 `.gitignore` 파일을 사용하여 배제할 요소들을 지정할 수 있다.

각 언어의 framework에도 이 파일로 무시해도 되는 파일들이 적혀있다.

<br>

### .gitignore 형식

자세한 형식은 https://git-scm.com/docs/gitignore 을 참조한다.

프로젝트 때마다 위 사이트를 참조하여 작성한다.

```yml
# 이렇게 #을 사용해서 주석을 단다.

# 모든 file.c를 무시
file.c

# 최상위 폴더의 file.c를 무시
/file.c

# 확장자가 .a인 모든 파일 무시
*.a

# 확장자가 .a 여도 lib.a는 무시하지 않는다.
!lib.a

# logs란 이름의 파일 또는 폴더와 그 내용들 무시
logs

# logs란 이름의 폴더와 그 내용들을 무시
logs/

# logs 폴더 바로 안의 debug.log와 .c 파일들 무시
logs/debug.log
logs/*.c

# doc directory 아래의 모든 .pdf 파일을 무시
doc/**/*.pdf

# logs 폴더 바로 안, 또는 그 안의 다른 폴더(들) 안의 debug.log 무시
log/**/debug.log
\
```

<br>

### .gitignore 사용해보기

폴더에 `secrets.yaml` 을 생성한다.

다음과 같은 내용을 가진다.

```yml
id: admin
pw: 1234abcd
```

`git status`로 상태를 확인한다.

```yml
On branch master

No commits yet

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        lions.yaml
        secrets.yaml
        tigers.yaml
```

`.gitignore`을 생성하여, 그 안에 `secrets.yaml` 을 입력한다.

다시 `git status`로 상태를 확인한다.

```yml
On branch master

No commits yet

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        .gitignore
        lions.yaml
        tigers.yaml
```

`secrets.yaml`이 없다는 걸 확인할 수 있다.

이처럼 `.gitignore.yaml`을 통해서 git의 관리 하에 두지 않을 것들을 정할 수 있다.

다음 chapter 내용은 위 상태에서 이어서 진행된다.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)
