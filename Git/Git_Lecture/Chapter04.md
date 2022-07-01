# 0. Introduction

> 1. [Github이란?](#1-github이란)
> 2. [토큰 만들기](#2-토큰-만들기)
> 3. [원격 저장소 만들기](#3-원격-저장소-만들기)
> 4. [GitHub에서 프로젝트 다운받기](#4-github에서-프로젝트-다운받기)
> 5. [push와 pull](#5-push와-pull) 6.[원격의 브랜치 다루기](#6-원격의-브랜치-다루기)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)를 중심으로 [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)을 참고하여 공부한 내용입니다.

<br>

---

# 1. Github이란?

**_Github이란 Gitlab, Bitbucket과 같이 코드 공유 및 협업 서비스_** 다.

보다 더 자세히 설명하자면 Git으로 관리하는 모든 프로젝트들을 온라인 공간에 공유해서 프로젝트 구성원들이 협업하는데 도와주는 서비스이다.

그런데, 일반 클라우드 서비스와의 차이점은 무엇일까??

일반 클라우드 서비스로 협업을해야한다면 같은 파일을 공유해서 수정할 경우, 일반적인 공유 방법으로는 답이 없다. 왜냐하면 계속해서 덮어씌워지는 문제가 발생하기 때문이다.

하지만, Github을 사용하면 모든 업로드와 다운로드를 커밋 단위로 주고받기 때문에, 서로의 작업을 덮어씌울 걱정할 필요 없이 협업하는데 문제점을 해결해준다.

즉, 협업 시의 교통정리를 해준다.

<br>

---

# 2. 토큰 만들기

**_Personal access token_** 만들고, 이 토큰을 컴퓨터에 저장하고자 한다면,

또한, 새로운 Repository를 생성 후 협업할 팀원을 추가하고 싶으면

아래 링크를 참고한다.

[Section 4. Github 사용하기 - Lesson 2. GitHub 시작하기](https://www.yalco.kr/@git-github/4-2/)

위 링크에서 토큰을 컴퓨터에 저장하고자 할 때, 윈도우 컴퓨터의 경우,

`Window 자격 증명 관리자` 란 제어판 홈에 있는 메뉴를 의미한다.

Window menu bar에 이를 검색하면 바로 뜰 것이다.

지금은 바로 필요하지 않으니 자세한 설명보다 링크를 통해 나중에 더 자세히 보자.

<br>

---

# 3. 원격 저장소 만들기

1. Github repository를 생성 후, 이 링크 주소를 복사한다.

   - 새로운 repository를 생성하여 들어가면 `Code` tab 란에 `|HTTPS|SSH|` 칸이 있는데, 이중 `HTTPS`를 선택한다.

2. local의 Git 저장소에 원격 저장소를 연결하기 위해서 아래 명령어를 입력한다.

   - 이 방식으로 hugo를 사용하여 gitub 기술 블로그를 만들었다.
     - [Window에서 Hugo로 Github page 만들고 배포하기](https://jeha00.github.io/post/hugo%EB%A1%9C-github-page-%EB%A7%8C%EB%93%A4%EA%B3%A0-%EB%B0%B0%ED%8F%AC%ED%95%98%EA%B8%B0/)

   ```yml
   # 원격 저장소를 추가하는 명령어

   git remote add origin (복사한 링크 주소 즉, 원격 저장소 주소)
   ```

   - `origin`은 원격 저장소의 이름이다. 흔히 `origin`을 사용한다. 하지만, 다른 것으로도 사용 가능하다.

   ```yml
   git branch -M main
   ```

3. local 저장소의 commit 내역들을 원격으로 `push` (업로드) 한다.

   ```yml
   git push -u origin main
   ```

   - `-u` 또는 `--set-upstrea`: 현재 브랜치와 명시된 원격 브랜치의 기본 연결을 origin main으로 하겠다는 의미다. 이 명령어를 입력한 이후에는 `git push`만 해도 이와 동일한 의미로 받아들인다.

   - 하지만, 업로드 branch를 여러 개로도 할 수 있다.

💡 2번과 3번을 직접 입력해도 되지만, repository를 만들어서 클릭 후 들어가면 위 3가지 명령어 line을 한 번에 복사할 수 있도록 해놨다. [2. 토큰 만들기](#2-토큰-만들기) 가 잘 되었다면 순탄하게 진행될 것이다.

4. 원격으로 연결된 목록을 보고 싶으면 `git remote` 또는 자세히 보고 싶으면 `git remote -v`를 입력한다.

5. 원격으로 연결된 것을 지우고 싶다면 `git remote remove (origin 등 원격 이름)` 을 입력한다.
   - 이 때 Github repository가 삭제되는 것이 아니다. 단지 로컬과 Github의 연결만을 지운다.

<br>

---

# 4. GitHub에서 프로젝트 다운받기

- GitHub에서 다른 동료의 프로젝트를 다운받으려고 할 때 몇 가지 방식이 있다.

1. `Download ZIP` 으로, ZIP 파일로 다운받아 원하는 폴더에 푸는 방식이다. 하지만 이 방식은 **_파일들만 다운 받고, `.git`은 다운받지 않기 때문에 Git 관리내역은 제외되어 추천하지 않는다._**

2. 다운받기 원하는 폴더에서 터미널이나 Git Bash를 열은 후, 그 경로에서 `git clone (원격 저장소 주소)` 를 입력하는 것이다. 그러면 `.git`도 다운받기 때문에, Git 관리 내역까지 포함된다. 그래서 이 방식을 추천한다.

<br>

---

# 5. push와 pull

## 5.1 원격으로 커밋 push & pull

### 5.1.1 원격으로 커밋 밀어올리기(push)

1. Leopards의 `members`에 `Evie` 추가

   - Commit message: `Add Evie to Leopards`

2. `git push`

   - 이미 `git push -u origin main`으로 대상 원격 브랜치가 지정되었기 때문에 가능하다.

3. GitHub page에서 확인 가능하다.

<br>

### 5.1.2 원격의 커밋 당겨오기(pull)

1. **_local이 아닌 Github에서_** Leopards의 `members`에 `Dongho` 추가

   - Commit message: `Add Dongho to Leopards`

2. `git pull`

3. local에서 file과 log 살펴보기

<br>

## 5.2 pull할 것이 있는데, push를 하면??

이 상황은 local에서 수정한 것과 GitHub에서 동일한 것을 다르게 수정했을 경우, 충돌이 일어났을 때 `pull`을 먼저 하고 나서야 `push`를 할 수 있다.

이 상황을 만들어보자.

1. **_local에서_** Leopards의 `manager`를 `Dooli`로 수정

   - commit message: `Edit Leopards manager`

2. **_GitHub에서_** Leopards의 `coach`를 `Lupi`로 수정

   - commit message: `Edit Leopards coach`

3. `push` 해보기

   - 이 때 오류가 뜰 것이다.
   - pull 해서 GitHub에서의 버전을 받아온 다음 push 가능하다.

4. push 할 것이 있을 시, pull하는 두 가지 방법

   - `git pull --no-rebase`: merge 방식

     - 3번에서 `push` 전에 하는 `pull` 과 동일하다. `git pull`을 하면 자동적으로 `git pull --no-rebase`로 입력한다.
     - 소스트리에서 확인해보기
     - `reset`으로 되돌린 다음 아래 방식도 해보기
       ![image](https://user-images.githubusercontent.com/78094972/175796590-9cf944e5-e3cd-4f50-814c-98161b084d7e.PNG)

   - `git pull --rebase` : rebase 방식
     - pull 상의 rebase는 다르다. (협업 시, 사용 OK)
     - GitHub에 시간선을 맞춘다.
     - pull 상의 rebase는 일반 rebase와 상황이 다르므로, 협업시 사용해도 괜찮다.  
       ![image](https://user-images.githubusercontent.com/78094972/175796732-0e0c1a3b-f7eb-42e5-b693-a86be73a2af6.PNG)

5. `push` 하기

<br>

## 5.3 협업상 충돌 발생 해결하기

1. **_Local에서_** Panthers의 `members`에 `Maruchi` 추가

   - commit messge: `Add Maruchi to Panthers`

2. **_원격에서_** Panthers의 `members`에 `Arachi` 추가

   - commit message: `Add Arachi to Panthers`

3. pull 하여 충돌상황 마주하기

   - `--no-rebase` 와 `--rebase` 모두 해볼 것

     - `git pull --no-rebase` 한 결과
       ![image](https://user-images.githubusercontent.com/78094972/175802541-a2077ccd-8cfb-4102-a71d-100e58094af2.PNG)

     - `git pull --rebase` 한 결과
       ![image](https://user-images.githubusercontent.com/78094972/175802543-48152547-daf8-4996-b242-a14c5ae1429c.PNG)

<br>

## 5.4 local의 내역 강제 push하기

- 언제 사용하는가?

  - local 상의 내용이 원격보다 내용이 뒤쳐지면 push를 할 수 없을 때
  - 그리고, 원격에서의 내용이 잘못되서 강제로 local에서의 내용으로 맞춰야할 때

- 하지만, 사용하기 전 미리 합의 후 실행해야 한다. 왜냐하면 다른 사람이 한 것이 날라갈 수 있기 때문이다.

- `git push --force` 로 덮어씌울 수 있다.

<br>

---

# 6. 원격의 브랜치 다루기

<br>

## 6.1 로컬에서 브랜치 만들어 원격에 push 해보기

1. `from-local` branch 만들기

2. 아래 명령어로 원격에 push

   - `git push`
   - 위 명령어를 입력하면 **원격 저장소의 대상을 명시** 하라는 메시지가 나타난다.

     ```yml
     fatal: The current branch from-local has no upstream branch.
     To push the current branch and set the remote as upstream, use

        git push --set-upstream origin from-local
     ```

   - 그 때 이 명령어를 입력하여 원격 브랜치 명시 및 기본 설정한다.
     - `git push -u origin from-local`
     - 이 명령어로 원격 저장소의 브랜치에 `from-local`이 생긴다.

3. 원격 저장소의 `from-local` branch의 `jaguars.yaml`를 편집한다.

   - manager를 `Cheolsu`로 수정한다.
   - Commit message: `Edit Jaguars Manager`

4. branch 목록 살펴보기

   - GitHub에서 목록 보기
   - 아래 명령어로 local과 원격의 branch 확인

     - `git branch --all` : `git branch`는 local만 확인한다.

     ```yml
     from-local
     main
     remotes/origin/from-local
     remotes/origin/main
     ```

❗ 원격 저장소의 default branch를 바꾸고 싶으면 아래 링크를 참조한다.

- [원격 저장소 Default Branch 변경하기](https://aroundlena.tistory.com/51)

<br>

## 6.2 원격의 브랜치 로컬에 받아오기

1. GitHub에서 `from-remote` branch 만들기

   - GitHub에서 branch를 선택하는 곳의 빈칸에 입력하면 `Create branch: from-remote from 'main'`이 뜬다.
   - `git branch -a`에서 현재는 보이지 않는다.

2. 아래 명령어로 원격의 변경사항 확인

   - `git fetch`

3. 아래 명령어로 로컬에 같은 이름의 브랜치를 생성하여 연결하고 switch

   - `git switch -t origin/from-remote`

   - 소스트리에서 `origin/branch 명` 인 걸 확인할 수 있다. 이는 원격에 있는 branch를 의미한다.
   - `-t`는 `push`의 `-u`와 같은 것이다.

4. Local에서 jaguars의 `manager`를 `cheolsu`로 바꾼다.
   - commit message: `Edit Jaguars Manager`

<br>

## 6.3 원격의 브랜치 삭제

아래 명령어를 입력하여 원격 저장소의 브랜치를 삭제한다.

- `git push (원격 이름) --delete (원격의 브랜치명)`
  - `git push origin --delete from-local`
  - `git push origin --delete from-remote`

위 명령어를 입력하면 다음과 같이 뜬다.

```yml
$ git push origin --delete from-local
To https://github.com/JeHa00/git-practice.git
- [deleted]         from-local

$ git push origin --delete from-remote
To https://github.com/JeHa00/git-practice.git
- [deleted]         from-remote
```

<br>

## 6.4 Sourcetree로 진행하기

1. 원격 추가하기: 원격에 새로운 repository를 만든 후, 새 repo.에 해당하는 HTTPS 주소를 복사한다.

2번부터 7번은 소스트리로 `push`와 `pull`을 해보는 단계다.

2. 소스트리의 위 메뉴들 중 `저장소(R)` -> `원격 저장소 추가` ->`추가` -> 원격 이름에 `origin2` 입력 & URL에 복사한 주소 입력

3. `Push` 클릭 -> 다음 저장소에 푸시: `origin2`로 설정

4. 로컬의 Pumas의 members에 Pororo 추가

   - Commit message: `Add Pororo to Pumas`

5. 소스트리에서 `커밋` 클릭 -> 스테이지에 올라가지 않은 파일을 클릭한 다음, `모두 스테이지에 올리기`를 클릭합니다.

6. Commit message를 입력 후, `origin/main에 바뀐 내용 즉시 푸시` 를 체크한 다음 커밋 실행

마지막으로 소스트리를 사용하여 브랜치를 만들어 푸시한다.

7. 소스트리에서 `브랜치` 클릭 -> `Push` 클릭 -> `from-local`에도 체크 후 푸시 실행

8. 원격에도 `from-local` branch가 생긴 걸 확인할 수 있다.

9. 이번에는 원격에서 branch `from-remote` 를 만든 후, 소스트리에서 fetch(패치)를 실행하면 소스트리의 origin에 추가된 걸 확인할 수 있다.

10. 추가된 `from-remote`를 사용하는 브랜치에 추가하고 싶으면, `원격` > `origin` > `from-remote` 에서 오른쪽 클릭하여 체크아웃을 클릭한다.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)
