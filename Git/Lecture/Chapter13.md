# 0. Introduction

> 1. [Pull request](#1-pull-request)
> 2.[Issure](#2-issue)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- Chapter 13에서는 GitHub 여러 기능 중 pull request와 issue에 대해 학습한다. 

<br>

---

# 1. Pull request

> **_변경사항을 merge하기 전 리뷰를 거치기 위함_**  

- 오픈 소스 외 다른 사람들과 프로젝트를 하기 위해서는 `settings` -> `Collaborators`로 이동한다. 그 후, `Manage access` 에서 `Add poeple` 로 추가한다. 

- 또한, 보다 조심스럽게 팀원들의 동의를 거친 뒤 대상 브랜치에 적용하기 위한 방식인 `pull request`를 사용해보자.  

<br>

### 1.1 풀 리퀘스트 사용해보기

1. local과 remote repository에 동일한 명칭의 새로운 브랜치 생성 후, 생성한 브랜치에 변경사항 커밋하여 푸시
    - `git branch develop`  
    - 임의의 파일 수정 및 커밋하기  
    - `git push origin develop` 로 remote에 `main` branch 외에 생성한 브랜치에 push하기  


2. GitHub 레포지토리 페이지에서 Compare & pull request 버튼 클릭
    - 또는 ~ branches에서 New pull request 클릭

3. 메시지 작성 후 Create pull request 클릭


❗ Open a pull request 시, `Create pull request` 외에 `Create draft pull request` 가 존재하는데, 이는 작업이 끝나지 않았지만 코드 리뷰를 요청하는 걸 말한다. 

<br>

### 1.2 풀 리퀘스트 검토 후 처리하기

1. GitHub 레포지토리 페이지에서 Pull requests 탭 클릭

2. 대상 풀 리퀘스트 클릭하여 내용 검토

    - 의견이 있을 시 코멘트 달기
    - 반려해야 할 시 `Close pull request` 클릭
    - 승인할 시 `Merge pull request`

- Merge 또한 Pull request에서 여러 방법을 선택할 수 있다.   


- merge 했으면, working directory에서 `git pull`을 사용하여 가져온다. 

🔅 그래서 [Git flow 전략](https://github.com/JeHa00/TIL/blob/main/Git/Lecture/Chapter10-05.md) 과 함께 사용한다.  

<br>

---

# 2. Issue

> **_버그나 문제 제보, 추가할 기능 등의 이슈 소통에 사용_**  

### 예시

- [네이버 지도 API 예제](https://github.com/navermaps/maps.js)
- [Flutter](https://github.com/flutter/flutter)

<br>

### 이슈 작성해보기

1. GitHub 레포지토리 페이지에서 `Issues` 탭 클릭


2. 필요시 label 또는 milestone 생성

    - milestone: 이슈의 주제 묶음 (특정 목표 등)
    - label: 기존에 만들어진 것을 사용하거나, 다른 것을 선택하여 사용할 수도 있다. 

3. 이슈 작성

    - 필요시 label, milestone, asignee 지정
        - asignee 누가 담당해서 하는지를 지정

4. 이슈 확인 후 처리

    - 코멘트 달기
    - 관련 개발 착수 (브랜치명이나 커밋 footer에 이슈 번호 반영)
    - 해결 뒤: `Close issue`
    - `Close issue`를 클릭 후, `Issues` tab에서 바로 확인할 수 없다. 하지만, `Open`이 아니라, `Closed`를 클릭하면 해결된 이슈들만 볼 수 있다.  
    - [Chapter 07-01](https://github.com/JeHa00/TIL/blob/main/Git/Lecture/Chapter07-01.md) 에서 `Closes #125`가 이슈 125번을 닫는다는 의미다 .

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
