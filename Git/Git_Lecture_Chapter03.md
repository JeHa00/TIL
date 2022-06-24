# 0. Introduction

> 1. [여러 branch 만들어보기](#1-여러-branch-만들어보기)
> 2. [branch를 합치는 두 가지 방법](#1-여러-branch-만들어보기)
> 3. [branch 합치기 실습](#3-branch-합치기-실습)
> 4. [충돌 해결하기](#4-충돌-해결하기)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)를 중심으로 [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)을 참고하여 공부한 내용입니다.

<br>

---

# 1. 여러 branch (다른 차원) 만들어보기

> **_아래의 모든 것을 하나의 프로젝트 폴더에서 진행할 수 있도록 여러 branch를 만든다._**

![image](https://www.yalco.kr/images/lectures/git-github/3-1/branches.png)

언제 branch를 여러 개 만들어서 작업을 할까??

- 프로젝트를 하나 이상의 모습으로 관리해야할 때

  - 예) 실배포용, 테스트 서버용, 새로운 시도용

- 여러 작업들이 각각 독립되어 진행될 때

  - 예) 신기능 1, 신기능 2, 코드개선, 긴습 수정 ...
  - 각각의 차원에서 작업한 뒤, 확정된 것을 메인 차원에 통합한다.

<br>

## 1.1 브랜치 생성 / 이동 / 삭제하기 / 이름 바꾸기

- branch 생성: `add-coach`란 이름의 브랜치 생성

  - `git branch add-coach`

- branch 목록 확인

  - `git branch`
  - `*`은 현재 branch를 의미한다.

```yml
> $ git branch add-coach
> $ git branch
  add-coach
* main
```

- 생성된 branch로 이동

  - `git switch add-coach`
  - `*`가 `add-coach`로 옮겨진 걸 알 수 있다.
  - checkut 명령어가 Git 2.23버전부터 `switch`와 `restore`로 분리되었다.

```yml
> $ git switch add-coach
Switched to branch 'add-coach'

> $ git branch
* add-coach
  main
```

- 💡 branch 생성과 동시에 이동하기

  - `git swtich -c new-teams`
  - 기존의 `git checkout -b (새 브랜치명)`이 이를 의미한다.

```yml
> $ git switch -c new-teams
Switched to a new branch 'new-teams'

> $ git branch
  add-coach
* main
  new-teams
```

- 브랜치 삭제하기

  - `git branch -d (삭제할 브랜치명)`
  - `to-delete`란 branch 만들고, 삭제해보기

- 브랜치 이름 바꾸기

  - `git branch -m (기존 브랜치명) (새 브랜치명)`

```yml
> $ git branch
  add-coach
* main
  new-teams
  to-delete

> $ git branch -m to-delete to-eraser

> $ git branch
  add-coach
* main
  new-teams
  to-eraser

> $ git branch -m to-eraser to-delete

> $ git branch -d to-delete
Deleted branch to-delete (was 1589712).

> $ git branch
  add-coach
* main
  new-teams
```

- 💡 브랜치 강제 삭제

  - 지워질 브랜치에만 있는 내용의 커밋이 있을 경우, 다른 브랜치로 가져오지 않은 내용이 있는 브랜치를 지울 때는 `-d` 대신 `-D` (대문자)로 강제 삭제해야 한다.

<br>

## 1.2 각각의 브랜치에서 서로 다른 작업해보기

> **_총 3개의 branch: main, add-coach, new-teams branch에서 작업한다._**

각 작업을 실행할 때, `git add`를 실행한 후 `git commit -m'()'`을 해야 반영된다.

Chapter 02에서 알아봤듯이 모두 다 tracked file일 때, `git commit -am'(commit message)'` 로 한 번에 할 수 있다.

### 1.2.1 main branch

1. Leopards의 `members`에 `Olivia` 추가

   - 커밋 메시지: `Add Olivia to Leopards`

2. Panthers의 `members`에 `Freddie` 추가

   - 커밋 메시지: `Add Freddie to Panthers`

<br>

### 1.2.2 add-coach branch

1. Tigers의 매니저 정보 아래 coach: Grace 추가

   - 커밋 메시지: `Add Coach Grace to Tigers`

2. Leopards의 매니저 정보 아래 coach: Oscar 추가

   - 커밋 메시지: `Add Coach Oscar to Leopards`

3. Panthers의 매니저 정보 아래 coach: Teddy 추가

   - 커밋 메시지: `Add Coach Teddy to Panthers`

<br>

### 1.2.3 new-teams branch

1. `pumas.yaml` 추가

   - 커밋 메세지: `Add team Pumas`

2. `jaguars.yaml` 추가
   - 커밋 메세지: `Add team Jaguars`

<br>

## 1.3 결과 살펴보기

`git log`로도 볼 수 있지만 `git log`로 볼 경우에는 현재 branch 와 갈라지기 전 main일 때의 log만 볼 수 있다.

별표는 하나의 줄기다.

```yml
$ git log --all --decorate --oneline --graph
* 672d65f (HEAD -> new-teams) Add team Jaguars
* af9742d Add team Pumas
| * 2641114 (add-coach) Add Coach Teddy to Panthers
| * e22aa3c Add Coach Oscar to Leopards
| * f91d19a Add Coach Grace to Tigers
|/
| * 7618a7e (main) Add Freddle to Panthers
| * a9fe922 Add Olivia to Leopards
|/
* 1589712 (to-eraser) replace cheetas with Panthers
* f86046e Add team Cheetas
* 679d1f1 add George to tigers
* 3183106 Replace Lions with Leopards
* ed807a6 first commit
```

하지만 이런 흐름을 볼 때는 실무에서는 CLI보다 source tree로 본다.

source tree로 보면 다음과 같다.

![image](https://user-images.githubusercontent.com/78094972/175245516-9cfdfbc4-456c-494d-8ae6-ec468afae0b3.PNG)

<br>

---

# 2. branch를 합치는 두 가지 방법

branch를 합치는 실습을 해보기 전에, 위 실습과정을 이미지로 보자면 다음과 같다.

주요 branch는 `main` branch다.

그리고 양 옆에 `add-coach` branch, `new-teams` branch에서 실험적인 시도를 하고 있다.

![image](https://user-images.githubusercontent.com/78094972/175248509-20e483ee-755a-4f9e-ad91-cf41eb2c40f9.PNG)

그리고 이 두 branch를 아래 이미지처럼 `main` branch로 합칠려고 한다.

![image](https://user-images.githubusercontent.com/78094972/175248538-1966ed55-8bf7-4e96-b668-163bc558b3d4.PNG)

이를 위해서 2가지 방법으로 진행할 것이다.

> **_merge vs rebase_**

- merge: 두 브랜치를 한 커밋에 이어붙이는 방식으로, **_두 branch의 끝 가지를 이어붙힌다._**

  - **_브랜치 사용 내역을 남길 필요가 있을 때_**, 적합한 방식
  - `main`과 `add-coach` branch를 합칠 방식

![image](https://user-images.githubusercontent.com/78094972/175249759-950a34de-d0c9-4508-8366-db925296866f.PNG)

- rebase: 브랜치를 다른 브랜치에 이어붙이는 방식으로, **_곁가지들을 싹 다 잘라다가 몸통 줄기에 이어 붙여서 히스토리를 한 줄로 유지가능하다._**

  - **_한 줄로 깔끔히 정리된 내역을 유지하기 원할 때_** 적합하다.
  - 이미 팀원과 공유된 커밋들에 대해서는 사용하지 않는 것이 좋다. 즉, 있던 거를 없애다가 딴 데 이어붙이는 거인 만큼 같이 일하는 도중에 이러면 문제가 발생할 수 있다.
  - `main` 과 `new-teams` branch를 합칠 방식

![image](https://user-images.githubusercontent.com/78094972/175249777-1b3a3a26-72b7-4fe2-b6e5-c475745cb3af.PNG)

❗ 이 두 가지 중 무엇을 사용하냐는 프로젝트의 성격에 달려있다. 브랜치의 사용 내역들을 남겨둘 필요가 있으면 `Merge`를, 그보다는 히스토리를 깔끔하게 만드는게 중요하면 `Rebase`를 사용한다.

<br>

## 2.1 Merge로 합치기

1. `main` branch로 먼저 이동
2. `git merge add-coach` 명령어로 병합

```yml
$ git merge add-coach
```

❗ 위 과정에서 충돌이 났을 경우, [3. 충돌해결하기](#3-충돌-해결하기)를 참고한다.

- 병합된 브랜치는 아래 명령어로 삭제한다.
  - 삭제 전 소스트리에서 `add-coach` 위치 확인한다.

```yml
git branch -d add-coach
```

💡 Rebase와의 차이점: `merge`는 `reset`으로 되돌리기 가능하다.

- `merge` 도 하나의 커밋이기 때문에, `merge` 하기 전 해당 브랜치의 마지막 시점으로 되돌리는 게 가능하다.

Merge한 결과는 다음 이미지와 같다.

![image](https://user-images.githubusercontent.com/78094972/175466251-92caa8cc-85af-448f-a477-c38cf4ab09ab.PNG)

<br>

## 2.2 Rebase로 합치기

- `new-teams` 브랜치를 `main` 브랜치로 rebase

💡 Merge와의 차이점: `merge`와는 반대로 `new-teams`로 이동하여 아래 명령어로 병합한다.

```yml
$ git rebase main
```

- 소스트리에서 상태를 확인하면 아래 이미지처럼 `main` 브랜치가 뒤쳐져있다.

![image](https://user-images.githubusercontent.com/78094972/175467079-7c16ac5a-e653-4ae7-80e0-7a71c38560ee.PNG)

- `main` 브랜치로 이동 후, 아래 명령어로 `new-teams`의 시점으로 앞으로 이동한다.

```yml
$ git merge new-teams
```

위 이미지와 달리 앞으로 이동된 걸 알 수 있다.

![image](https://user-images.githubusercontent.com/78094972/175467082-9e29ca4e-08f5-42c7-9f16-e202b1c7b992.PNG)

그리고 `new-teams` 브랜치를 삭제한다.

<br>

---

# 3. 충돌 해결하기

<br>

## 3.1 충돌 상황 만들기

1. `conflict-1`, `conflict-2` 브랜치 생성

2. `main` branch

- Tigers의 `manager`를 `Kenneth`로 변경
- Leopards의 `coach`를 `Nicholas`로 변경
- Panthers의 `coach`를 `Shirley`로 변경
- 커밋 메시지: `Edit Tigers, Leopards, Panthers`

3. `conflict-1` branch

- Tigers의 `manager`를 `Deborah`로 변경
- 커밋 메시지: `Edit Tigers`

4. `conflict-2` branch

- Leopards의 `coach`를 `Melissa`로 변경
- 커밋 메시지: `Edit Leopards`

5. `conflict-2` branch

- Panthers의 `coach`를 `Raymond`로 변경
- 커밋 메시지: `Edit Panthers`

<br>

## 3.2 merge 충돌 해결하기

1. `main` 브랜치에서 `git merge conflict-1` 로 병합을 시도하기

   - 그러면 아래와 같은 충돌이 발생한다.

   ```yml
   $ git merge conflict-1
   Auto-merging tigers.yaml
   CONFLICT (content): Merge conflict in tigers.yaml
   Automatic merge failed; fix conflicts and then commit the result.
   ```

   - 그러면 VSC에서 해당 파일 옆에 `!`로 뜬다.
   - 해당 파일을 클릭하면 `Accept Current Change`, `Accept Incoming Changes` 등을 선택라고 한다.
   - 이 때, 해당 파일은 일반 폴더에서 더블 클릭하여 열면 단순 text로 나타난다. 이를 VSC에서 보기 좋게 표현한 것이다.
   - 충돌되는 부분만 찾고 싶다면 VSC 검색에서 `<<<<<` 을 입력하여 찾을 수 있다.

2. `Accept Current Change`, `Accept Incoming Changes` 등을 선택하여, 충돌 부분을 수정한 뒤, `git add.` -> `git commit`으로 병합을 완료한다.

   - 하지만, 당장 충돌 해결이 어려울 경우, 아래 명령어로 `merge`를 중단한다.

   ```yml
   git merge --abort
   ```

<br>

## 3.3 rebase 충돌 해결하기

1. `conflict-2`에서 `git rebase main`으로 rebase 시도하면 충돌 발생한다.

   - 오류 메시지와 `git status` 확인한다.

   ```yml
    $ git rebase main
    Auto-merging leopards.yaml
    CONFLICT (content): Merge conflict in leopards.yaml
    error: could not apply f8bddeb... Edit Leopards
    hint: Resolve all conflicts manually, mark them as resolved with
    hint: "git add/rm <conflicted_files>", then run "git rebase --continue".
    hint: You can instead skip this commit: run "git rebase --skip".
    hint: To abort and get back to the state before "git rebase", run "git rebase --abort".
    Could not apply f8bddeb... Edit Leopards


    $ git status
    interactive rebase in progress; onto 528ecc7
    ...
    Unmerged paths:
    (use "git restore --staged <file>..." to unstage)
    (use "git add <file>..." to mark resolution)
        both modified:   leopards.yaml
   ```

   - VS Code에서 해당 부분을 확인한다.

2. `Accept Current Change`, `Accept Incoming Changes` 등을 선택하여, 충돌 부분을 수정한 뒤, `git add.` -> `git commit`으로 병합을 완료한다.

   - 하지만, 당장 충돌 해결이 어려울 경우, 아래 명령어로 `merge`를 중단한다.

   ```yml
   git rebase --abort
   ```

   - 해결 가능 시,

     - 충돌 부분을 수정한 뒤 `git add .`를 입력한다.
     - 아래 명령어를 입력한다.

     ```yml
     git rebase --continue
     ```

     - 충돌이 모두 해결될 때까지 반복한다.

3. `main`에서 `git merge conflict-2`로 마무리한다.

   - main을 앞으로 이동

4. `conflict-1` 과 `conflict-2`를 삭제한다.

   - 다 사용한 branch는 바로 바로 지워서 혼란스럽게 만들지 말자.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)
