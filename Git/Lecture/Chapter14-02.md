# 0. Introduction

> 3. [GitHub Actions](#3-github-actions)
> 4. [GitHub 추가 팁](#4-github-추가-팁)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- Chapter 14에서는 아래의 것들을 학습한다.  

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

❗ 아래 명령어로 오류가 뜬다면, 해당 명령어 앞에 `winpty`를 붙인다.


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
