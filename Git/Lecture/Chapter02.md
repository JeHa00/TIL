# 0. Introduction

> 1. [프로젝트의 변경사항을 타임캡슐(버전)에 담기](#1-프로젝트의-변경사항을-타임캡슐버전에-담기)
> 2. [변경사항 만들기](#2-변경사항-만들기)
> 3. [과거로 돌아가는 두 가지 방법](#3-과거로-돌아가는-두-가지-방법)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)를 중심으로 [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)을 참고하여 공부한 내용입니다.

<br>

---

# 1. 프로젝트의 변경사항을 타임캡슐(버전)에 담고, 묻기

파일을 저장해야 git이 변경사항을 인식할 수 있다.

## 1.1 git add

> - **_untracked state -> tracked state_**
> - **_git add 개별 파일 추가와 전체 파일 추가_**

`git status`로 확인하면 `Untracked files`가 뜬다.

```yml
On branch master

No commits yet

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        .gitignore
        lions.yaml
        tigers.yaml
```

이 `Untracted files`란 **_Git의 관리에 들어간 적 없는 파일_**을 의미한다.

tigers.yaml 파일을 수정한다.

`git add tigers.yaml` 로, `tigers.yaml`의 변경사항을 stage 상태로 올린다.

```yml
On branch master

No commits yet

Changes to be committed:
  (use "git rm --cached <file>..." to unstage)
        new file:   tigers.yaml

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        .gitignore
        lions.yaml
```

이렇게 `git add`로 개별 파일을 `tracked` 상태로 바꾼다.

또한, 모든 파일이 git 관리에 들어가기 위해서는 `git add .`를 입력한다.

그러면 아래와 같은 의문이 들 수 있다.

_'처음부터 `git add .` 으로 모든 file을 tracked 상태로 만들면 되지 않느냐?_

여러 file 중 A file만 A project에 필요하고, B file은 B project에 필요하다면 한꺼번에 하는 것이 아닌 개별적으로 해야 한다.

위와 같은 경우를 제외하고는 통상적으로 한 프로젝트에 관련된 모든 것을 `git add .`로 추가한다.

<br>

## 1.2 git commit

다음으로 아래 명령어를 사용하여 타임캡슐을 묻어보자.

```yml
git commit
```

이 명령어를 입력하면 밑에 이미지와 같은 Vim 입력모드로 진입한다.

Vim mode에서 명령어는 다음과 같다.

| 작업                | Vi 명령어 | 상세                                         |
| ------------------- | --------- | -------------------------------------------- |
| 입력시작            | i         | 명령어 입력 모드에서 텍스트 입력 모드로 전환 |
| 입력 종료           | `ESC`     | 텍스트 입력 모드에서 명령어 입력 모드로 전환 |
| 저장 없이 종료      | :q        |                                              |
| 저장 없이 강제 종료 | :q!       | 입력한 것이 있을 때 사용                     |
| 저장하고 종료       | :wq       | 입력한 것이 있을 때 사용                     |
| 위로 스크롤         | k         | `git log` 등에서 내역이 길 때 사용           |
| 아래로 스크롤       | j         | `git log` 등에서 내역이 길 때 사용           |

출처: [제대로 파는 Git & GitHub - by 얄코 ](https://www.yalco.kr/@git-github/2-1/)

`FIRST COMMIT` 을 입력한 뒤, 저장하고 종료한다.

`FIRST COMMIT`은 보통 통상적으로 프로젝트의 첫 버전이 만들어질 때 쓰는 메세지다.

COMMIT와 Message를 같이 입력하고 싶으면 아래와 같이 입력한다.

```yml
git commit -m 'FIRST COMMIT'
```

아래 명령어와 소스트리로 확인한다.

    - 종료는 `:q`를 사용한다.

```YML
git log
```

<br>

---

# 2. 변경사항 만들기

다음 소단원에서 과거로 돌아가는 실습을 수행하기 위해 몇 가지 변경사항을 만들어보자.

그 결과, 소스트리의 히스토리에 다음과 같이 결과가 뜰 것이다.

위로 올라갈수록 최근 commit message다.

![image](https://www.yalco.kr/images/lectures/git-github/2-1/logs.png)

## 2.1 첫 번째 변경사항

- 변경사항

  - `lions.yaml` 파일 삭제
  - `tigers.yaml`의 manager를 `Donald` 로 변경
  - `leopards.yaml` 파일 추가

- `git status`로 확인

```yml
On branch master
Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        deleted:    lions.yaml
        modified:   tigers.yaml

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        leopards.yaml

no changes added to commit (use "git add" and/or "git commit -a")
```

- 그리고 message와 함께 commit 한다.

```yml
git commit -m "Replace Lions with Leopards"
```

- commit과 함께 add를 한 번에 하는 방법도 있다.
  - 하지만 이 경우, 새로 추가된(untracked) 파일이 없을 때만 가능하다.

```yml
git commit -am "(메세지)"
```

<br>

## 2.2 두 번째 변경사항

- 변경사항

  - Tigers의 `members`에 `George` 추가
  - 커밋 메세지: `Add George to Tigers`

- add 및 commit

```yml
git add .

# commit message를 구분한다.
git commit -m 'add George to tigers'
```

<br>

## 2.3 세 번째 변경사항

- 변경사항
  - `cheetas.yaml` 추가

```yml
git add .

# commit message를 구분한다.
git commit -m 'Add team Cheetas'
```

<br>

## 2.4 네 번째 변경사항

- 변경사항
  - `cheetas.yaml` 삭제
  - Leopards의 `manager`를 `Nora`로 수정
  - `panthers.yaml` 추가

```yml
git add .

# commit message를 구분한다.
git commit -m 'Replace Cheetas with Panthers'
```

<br>

---

# 3. 과거로 돌아가는 두 가지 방법

커밋을 묻어놓은 타임캡슐을 버전이라고 생각하면 다음과 같은 순서로 버전들이 만들어졌다.

그리고, 안에 무엇이 있는지를 알기 위해서 캡슐마다 작업한 것을 적어서 꼬리표를 달아놓은 것이다.

![image](https://user-images.githubusercontent.com/78094972/175048317-a442d609-e629-4ba3-8959-8231df0d80d8.PNG)

Git에서 과거로 돌아가는 방법은 2가지 방법이 있다.

> **_Reset vs Revert_**  
> **_- reset: 원하는 시점으로 돌아간 뒤 이후 내역들을 지운다._**  
> **_- revert: 되돌리기 원하는 시점의 커밋을 거꾸로 실행한다._**

**_revert_** 에 대해 더 설명하자면

Revert: 해당 과거 이후의 행적을 삭제하는 것이 아니라, 이 행적을 거꾸로 수행하는 commit을 하나 넣음으로서, 결과적으로 reset과 동일한 결과를 갖는 것이다. 하나 하나를 기록으로 남길 때 사용한다.

우리가 한 남긴 commit message를 기준으로 `Add team Cheetas`로 돌아간다고 하자.

`reset`은 다음과 같이 움직인다.

![image](https://user-images.githubusercontent.com/78094972/175049821-87b83f1d-940d-4d92-baf2-751d35775c96.PNG)

하지만 `revert`는 이와 다르게 움직인다.

![image](https://user-images.githubusercontent.com/78094972/175049840-9bdad453-d62c-426e-a15f-4a9505143d3c.PNG)

Replace Cheetas with Panthers, Add team cheetas, Add Georage to Tigers는 그대로 두고, Replace Lions with Leopards만 삭제할려는 상황에서 revert를 사용한다.

만약 협업 시에 reset으로 특정 history를 삭제하면 이 history를 기반으로 작업한 다른 개발자와 심각한 충돌을 일으킨다. 그래서 한 번 공유된 commit들은 revert를 이용해서 되돌려야 한다.

<br>

---

# 4. 과거로 돌아가기 실습

먼저 `.git`을 백업 후, 원래 폴더에 있던 `.git`을 삭제한다.

그러면 VSC에서 `git status`를 하면 `fatal: not a git repository`라는 오류가 발생하겠고, Sourcetree에서도 `저장소 없음` 이라는 에러가 발생한다.

다시 원래 폴더에 `.git` 을 복사 붙여넣기하면 위 에러들은 다 사라진다.

위 이미지들에서 commit message를 순서대로 확인할 수 있듯이, `git log`를 통해서도 순서대로 볼 수 있다. `git log`에서 나가기 위해서는 `:q`를 입력한다.

그러면 `Add team Cheetas` message를 남긴 시점의 과거로 돌아가보자.

**_돌아가기 위해서는 위 message를 남긴 시점의 hash 번호가 필요하다._**

```yml
$ git log
commit 1589712e4324d8a017a8bbc5945e8f98a8085aad (HEAD -> master)
Author: Jeha00 <rudtls0611@naver.com>
Date:   Wed Jun 22 18:30:06 2022 +0900

commit 1589712e4324d8a017a8bbc5945e8f98a8085aad (HEAD -> master)
Author: Jeha00 <rudtls0611@naver.com>
Date:   Wed Jun 22 18:30:06 2022 +0900

    replace cheetas with Panthers

commit f86046e7549dde524f3be3729bd9d43281d2a486
Author: Jeha00 <rudtls0611@naver.com>
Date:   Wed Jun 22 18:29:13 2022 +0900

    Add team Cheetas
```

hash 번호란 위 log에서 commit 옆에 있는 알파벳과 숫자가 섞인 것을 의미한다.

<br>

## 4.1 reset

### 4.1.1 reset으로 돌아가기

> **_git reset --hard (돌아갈 커밋 해시)_**

```yml
$ git reset --hard f86046e7549dde524f3be3729bd9d43281d2a486

$ git log
commit f86046e7549dde524f3be3729bd9d43281d2a486 (HEAD -> master)
Author: Jeha00 <rudtls0611@naver.com>
Date:   Wed Jun 22 18:29:13 2022 +0900

    Add team Cheetas

commit 679d1f1788575666f8b368c67dfbb14f69c6a637
Author: Jeha00 <rudtls0611@naver.com>
Date:   Wed Jun 22 18:28:44 2022 +0900

    add George to tigers
```

`git log`를 통해 원하는 시점으로 돌아간 것을 알 수 있다.

이번에는 '첫 커밋 시점' 으로 돌아가보자.

```yml
$ git reset --hard ed807a60e49db810008b8fcb5fd4deddf4f200ec

$ git log
commit ed807a60e49db810008b8fcb5fd4deddf4f200ec (HEAD -> master)
Author: Jeha00 <rudtls0611@naver.com>
Date:   Wed Jun 22 18:22:01 2022 +0900

    first commit
```

위에서 `git log`로 확인했듯이 첫 커밋 시점으로 돌아간 걸 알 수 있다.

소스트리에서도 `first commit` message만 남겨진 걸 확인할 수 있다.

❗ `reset --hard` Error
`git reset --hard (commit의 해시태그)`를 입력했지만, 소스트리로 확인하면 계속해서 남아있다.

왜 이런 것일까???

local 상에는 원하는 커밋 시점으로 돌아갔지만, 원격 저장소 상에서는 그대로 남아있기 때문이다.
즉, **_원격보다 local이 뒤쳐진 상황_** 이다.  
원하는 커밋 시점으로 돌아가서 `git push --force` 로 로컬의 내역을 강제로 push 하면 덮어씌워지면서 해결된다.  
하지만, 조심해서 쓰도록 하자.

<br>

### 4.1.2 reset 전 시점으로 복원하기

실무해서 하는 방식은 아니지만, `.git` 을 이해해보자는 차원으로 공부한다. 실무에서는 `.git` 폴더를 직접 건드리는 일은 없다.

1. 백업해둔 `.git` 폴더를 복원
2. `git log`와 `git status`로 상태 확인

   - `.git`을 복원하면서 현재 git은 맨 처음 상태에서 파일에 새롭게 변화가 된 것이라고 인식한다.
   - 그래서 아래 명령어로 초기화하자.

3. `git reset --hard`로 현 커밋 상태로 초기화

   - 커밋 해시가 없으면 마지막 커밋을 가리킨다.

4. `lions.yaml` 삭제
   - 3번 명령을 실행한 후, `git status`로 확인해보면 달라진 것을 알 수 있다.
   - `lions.yaml`은 추가된 것이므로, 삭제해보자.

<br>

## 4.2 revert로 돌아가기

### 4.2.1 add George to tigers 시점으로 돌아가기

source tree로는 해당 커밋 메세지를 클릭하면 바로 commit hash를 구할 수 있지만, `git log`를 입력하여 찾아보자.

```yml
git revert 679d1f1788575666f8b368c67dfbb14f69c6a637
# :wq로 저장
```

### 4.2.2 Replace Lions with Leopards의 커밋으로 되돌려보기

이번에는 `Replace Lions with Leopards` 시점으로 돌아가려고 했으나, 다음과 같이 Error가 발생했다.

또한, 이 다음 명령 창에는 `(master|REVERTING)` 로, revert가 진행 중임을 알 수 있다.

```yml
> rudtl@DESKTOP-R1USJ9D MINGW64 ~/Desktop/Dev/GitHub/Git-practice (master)
$ git revert  3183106276f5315380d6722971159db9d72e7fd1
CONFLICT (modify/delete): leopards.yaml deleted in parent of 3183106 (Replace Lions with Leopards) and modified in HEAD.  Version HEAD of leopards.yaml left in tree.
error: could not revert 3183106... Replace Lions with Leopards
hint: After resolving the conflicts, mark them with
hint: "git add/rm <pathspec>", then run
hint: "git revert --continue".
hint: You can instead skip this commit with "git revert --skip".
hint: To abort and get back to the state before "git revert",
hint: run "git revert --abort".

> rudtl@DESKTOP-R1USJ9D MINGW64 ~/Desktop/Dev/GitHub/Git-practice (master|REVERTING)
```

이처럼 에러가 뜨는 것은 컴퓨터가 결정할 수 없기 때문에, 내가 결정을 하라고 알려준 것이다. 그래서 어떻게 해결하면 되는지 hint를 알려주고, 그 후에 `git revert --continue` 를 하라고 안내해준다.

`git rm <pathspec>` 이란 <pathspec>에 있는 파일을 삭제하라는 명령어다.

```yml
$ git rm leopards.yaml
rm 'leopards.yaml'

$ git revert --continue

:wq

$ git log
commit e27e462a65904a4f3ea890c6389c988423b40990 (HEAD -> master)
Author: Jeha00 <rudtls0611@naver.com>
Date:   Thu Jun 23 11:22:12 2022 +0900

    Revert "Replace Lions with Leopards"

    This reverts commit 3183106276f5315380d6722971159db9d72e7fd1.
```

<br>

### commit을 동반하지 않은 revert

revert는 기본적으로 commit을 동반한다. commit 없이 하고 싶다면 아래와 같이 명령어를 입력한다.

```yml
git revert --no-commit (되돌릴  커밋 해시)
```

그래서 원하는 다른 작업을 추가한 후, 커밋을 별도로 해야 한다.

마무리로 reset을 사용해서 revert 전으로 되돌아가보자.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)
