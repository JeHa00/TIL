# 0. Introduction

> 1. [SSH로 접속하기](#1-ssh로-접속하기)
> 2. [GPG로 커밋에 사인하기](#2-gpg로-커밋에-사인하기)
> 3. [GitHub Actions](#3-github-actions)
> 4. [GitHub 추가 팁](#4-github-추가-팁)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- Chapter 14에서는 아래의 것들을 학습한다.  
    - SSH 키를 생성하고, 등록하여, remote 추가 시 HTTP가 아닌 SSH를 사용하는 방법  
    - 

<br>

---
# 1. SSH로 접속하기



## SSH 프로토콜을 통한 인증


- **_SSH protocol을 통한 인증_**
    - 공개키 암호화 방식을 사용하는 방식으로, username과 토큰 사용할 필요 없다.  
    - 컴퓨터 자체에 키 저장  

- SSH 키 등록하기
    - 계정의 `Settings` - `SSH and GPG keys`  

- 해당 페이지의 가이드 참조  

<br>

### 1) SSH키 존재 여부 확인

- GitHub 계정 > `settings` > `SSH and GPG keys` 

- 터미널(윈도우의 경우, Bash Shell)에서 `~/.ssh`로 이동  
    - `cd ~/.ssh`  

- `id_rsa.pub`, `id_ecdsa.pub`, `id_ed25519.pub` 파일 중 하나 존재 여부 확인

    - `ls`

    - `.pub`은 공개키로 다른 살마에게 알려줘도 되지만, `.pub`이 없는 `id_<numbering>` 파일은 다른 사람에게 알려주면 안된다.  
        - 알려줬다면 두 가지 키 다 제거하고 다시해야 한다. 

- 위 3개 파일 중 하나가 존재한다면 3번으로 이동


<br>

### 2) SSH 키 생성

- 터미널(윈도우의 경우 Bash Shell)에서 키 생성  
    - `ssh-keygen -t ed25519 -C "(이메일 주소)"`



- 원할 시 `passphrase` 입력


- 1번의 과정으로 키 생성 확인  

<br>

### 3) Github에 키 등록


- 공개키 열람하여 복사 
    - `cat ~/.ssh/id_ed25519.pub` 또는 `cat id_ed25519.pub`

- `New SSH Key` 클릭하여 키 이름과 함께 등록  


<br>

### 4) SSH로 사용해보기  

- 원격을 SSH 주소로 변경한 뒤 테스트  

<br>

---
# 2. GPG로 커밋에 사인하기

### GitHub 커밋 내역 살펴보기 

- 로컬에서 푸시한 커밋과 GitHub에서 작성한 커밋 비교  

- `Verified`: 신뢰할만한 출처에서 커밋되었다는 인증


<br>

## GPG 사용

### 1) GPG tool 설치

- 윈도우: [다운로드 사이트](https://www.gnupg.org/download/)
- 맥: `brew install gnupg`
- `gpg --version`으로 확인

<br>

### 2) GPG 키 생성

- [이 링크의 가이드](https://docs.github.com/en/authentication/managing-commit-signature-verification/generating-a-new-gpg-key)에 따라 진행

<br>

### 3) New GPG key 클릭하여 등록


- [이 링크의 가이드](https://docs.github.com/en/authentication/managing-commit-signature-verification/telling-git-about-your-signing-key)에 따라 진행
- 맥의 경우, 추가 절차(환경 변수) 있음  


<br>

### 4) 사인하기  


- 커밋에 사인: 명령어에 `-S` dhqtus cnrk 

    - `git commit -S -m '(message)'`

- tag에 사인: 명령어에 `-s` 옵션 추가 

    - `git tag -s (태그명) (커밋 해시) -m (메시지)`   

<br>


---
# 3. GitHub Actions


> **_GitHub Actions_** 를 사용한 자동화  

- CI/CD: 지속적 통합과 배포  
    - [영상 참조](https://youtu.be/UbI0Q_9epDM)
    - 동종: GitLab CI/CD, BitBucket Pipelines

## 3.1 GitHub Actions 살펴보기 

### a) github.io page의 액션

- 해당 레포지토리 페이지에서 `Actions` tab 살펴보기  
- 새로운 커밋 푸시한 직후 다시 살펴보기  

### b) 다른 프로젝트에서 액션 추가하기

- `Actions` tab에서 액션들 살펴보기 적용해보기  
- `Marketplace` 살펴보고 적용해보기  
- 커밋 후 pull 하여 로컬에서 확인  

<br>

## 3.2 GitHub Actions 체험해보기

❗ PR시마다 코드 테스트 후, 실패시 자동 close  

- `.github/workflows/test.yaml` 살펴보기 

- 코드 수정(성공 & 실패)하여 `main` branch로 PR 날려보기  

<br>

---
# 4. GitHub 추가 팁

## 4.1 OctoTree

- GitHub repository의 directory를 보다 편하게 브라우징  
- 크롬 익스테션의 종류 
- [설치 링크](https://chrome.google.com/webstore/detail/octotree-github-code-tree/bkhaagjahfmjljalopjnoealnfndnagc)

## 4.2 GitHub CLI

- GitHub 작업 전용 CLI 툴  
- [설치 링크](https://cli.github.com/)

### 주요 명령어

[GitHub CLI 명령어 매뉴얼](https://cli.github.com/manual/)

- 로그인/로그아웃
    - `gh auth (login/logout)`

- 레포지토리들 보기  
    - `gh repo list`

- 프로젝트 클론
    - `gh repo clone (사용자명)/(레포지토리명)`

- 프로젝트 생성/삭제
    - `gh repo (create/delete)`

- 이슈 목록 보기
    - `gh issue list`

- 이슈 열람/닫기
    - `gh issue (view/close) (이슈 번호)`

- 이슈 생성
    - `gh issure create`

- 풀 리퀘스트 만들기/목록 보기
    - `gh pr (create/list)`

- 풀 리퀘스트 보기/코멘트/닫기/병합
    - `gh pr (view/comment/close/merge) (PR 번호)`

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
