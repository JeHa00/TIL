# 0. Introduction

> 1. [Git의 장점: Snapshot과 DVCS](#1-git의-장점-snapshot과-dvcs)
> 2. [Git의 3가지 공간](#2-git의-3가지-공간)
> 3. [git 명령어로 파일 삭제, 이동](#3-git-명령어로-파일-삭제-이동)
> 4. [reset의 세 가지 옵션](#4-reset의-세-가지-옵션)
> 5. [Git의 HEAD](#5-git의-head)
> 6. [fetch vs pull의 차이](#6-fetch와-pull의-차이)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)를 중심으로 [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)을 참고하여 공부한 내용입니다.

<br>

---

# 1. Git의 장점: Snapshot과 DVCS

Git의 장점인 Snapshot 방식과 DVCS에 대해 알아보자.

[What is git?](https://jeha00.github.io/post/git-github/0_whatisgit/)에 대한 요약버전이다.

<br>

## 1.1 Snapshot 방식

Snapshot 방식에 대해 알아보기 전에 git의 이전 버전들이 사용한 **_델타 방식_**에 대해 알아보자.

- 델타 방식

  - 델타방식은 해당 파일 전체가 처음 시점을 기준으로 이어져서 그 이후 변경지점만 누적되어 저장된다.

    ![image](https://user-images.githubusercontent.com/78094972/155179219-cf4c2d80-642e-484a-9d85-29c3854f64bc.png)

    - 버전 5시점에서는 델타1, 델타2, 델타 3이 저장되는 것이다.

- snapshot 방식

  - 스냅샷 방식은 새로운 버전이 만들어질 때, 해당 버전에서의 각 파일이 최종 상태 그대로 저장되어 있다.

    ![image](https://git-scm.com/book/en/v2/images/snapshots.png)

    - 버전 5에서 A는 변화가 없으니까 버전 4에서 가져온다. 이 저장도 용량을 별로 차지하는 방식으로 저장된다.

그렇다면 이 2가지의 차이점을 이해하기 위해 한 상황을 가정해보자.

만약 VSC 프로젝트처럼 커밋이 몇 만개가 있는 레포지토리를 델타버전으로 다룬다면 어떨까??

**델타 방식은** Git에서 뭘 할 때마다 각 파일들을 그거가 처음 만들어진 시점부터 변경사항들을 쭈욱 더해서 현재 내용을 계산해야하니 관리역사가 길수록 되게 느려진다.

반면에 **스냅샷은** 그냥 현 시점에 각 파일들이 풀로 저장되어 있으니까 아주 빠르다.

<br>

# 1.2 DVCS

CVCS는 원격 저장소에 의존적이라서 인터넷 연결이 끊기면 로컬에서 할 수 잇는 게 제한적이다.

밑에 이미지가 CVCS(Central Version Control System)다.

![image]("https://git-scm.com/book/en/v2/images/centralized.png)

반면에 Git은 clone 명령어로 가져오면 전체 Git 커밋과 branch까지 가져오기 때문에 인터넷 연결 상태와 상관없이 로컬에서 자유롭게 작업할 수 있다.

밑에 이미지가 DVCS(Distributed Version Control System, 분산 버전 관리 시스템)이다.

![image](https://git-scm.com/book/en/v2/images/distributed.png)

<br>

---

# 2. Git의 3가지 공간

> - Git의 3가지 공간: Working directory, Staging area, Repository
> - 각 공간을 이동하는 git 명령어: **Working directory** == (`git add`) ==> **Staging area** == (`git commit`) ==> **Repository**
> - Working directory = Untracked state + Tracked state
> - commit되어 레포지토리에 들어간 후 수정사항이 발생하면 `tracked` 상태로 staging을 기다린다.

[Git basics](https://jeha00.github.io/post/git-github/1_gitbasics/#22-%EC%88%98%EC%A0%95%ED%95%98%EA%B3%A0-%EC%A0%80%EC%9E%A5%EC%86%8C%EC%97%90-%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0)을 참고한다.

- Working directory

  - Untracked: Add된 적 없는 파일, ignore된 파일
    - Add된 적 없는 파일이란? git이 관리한 적이 없는 파일, 새로 생긴 파일을 말한다.
  - Tracked: Add된 적 있고, 변경내역이 있는 파일
  - `git add` 명령어에 의해서 `Working directory`에서 `Staging area`로 올라온다.

- Staging area

  - 커밋을 위한 준비 단계
    - ex) 작업을 위해 선택된 파일들
  - `git commit` 명령어로 repository로 이동
  - Staging area에서 working directory로 CLI로 이동하기
    - `git restore --staged (파일명) `
      - `--staged`를 빼면 `working directory`에서도 제거 = 변화를 제거한다.
    - 소스트리로는 단지 스테이지에서 내리기를 클릭한다.

- Repository

  - `.git repository`라고도 불린다.
  - 커밋된 파일들이 들어간 곳

<br>

---

# 3. git 명령어로 파일 삭제, 이동

- 파일 삭제

  - 삭제 후 area 위치 확인: `git status`로 확인

    | 삭제 방법 | 우클릭 삭제        | `git rm`           |
    | --------- | ------------------ | ------------------ |
    | area 위치 | working directory  | Staging area       |
    | 복원 방법 | `git reset --hard` | `git reset --hard` |

    - `git rm`으로 삭제한 것을 바로 staging area에 있는 걸 알 수 있다.
    - `git rm`은 바로 `git add` 명령어를 적용한 것이다.

    ```yml
    # 우클릭 삭제 후, 상태 확인

    $ git status

    Changes not staged for commit:
            deleted:    tigers.yaml

    no changes added to commit (use "git add" and/or "git commit -a")

    # git rm으로 삭제 후, 상태 확인
    $ git status

    Changes to be committed:
            deleted:    tigers.yaml
    ```

- 파일 이동

  - `tigers.yaml`를 `zzamtigers.yaml`로 이름 변경 뒤, `git status`로 살펴보기

    | 변경 방법 | 우클릭 변경        | `git mv`           |
    | --------- | ------------------ | ------------------ |
    | area 위치 | working directory  | Staging area       |
    | 복원 방법 | `git reset --hard` | `git reset --hard` |

    - `git mv`는 바로 `git add` 명령어를 적용한 것이다.

    ```yml
    # 우클릭 변경
    $ git status

    Changes not staged for commit:
            deleted:    tigers.yaml

    Untracked files:
            zzamtigers.yaml

    # git mv 사용
    $ git status

    Changes to be committed:
            renamed:    tigers.yaml -> zzamtigers.yaml
    ```

<br>

---

# 4. reset의 세 가지 옵션

- `--soft`: `repository`에서 `staging area`로 이동. staging area에 남겨놓는다.
- `--mixed` (default): `repository`에서 `working directory`로 이동. working directory에 남겨놓는다.
- `--hard`: 수정사항 내역을 완전히 삭제. 즉, working directory에서조차 삭제한다.

그러면 실습 해보자. 단, `--hard`는 생략한다.  
파일 일부를 변경한 후, 아래 명령어를 실행한다.

```yml
> git reset --soft 1c1037862c2a5919f31ecf0f55874c8bf236fea5
> $ git status

Changes to be committed:
        modified:   panthers.yaml
        modified:   tigers.yaml
```

`--soft`를 사용하니 `staging area`로 이동된 걸 확인했다.

<br>

---

# 5. Git의 HEAD

> **_HEAD: 현재 위치를 나타내기 위해 임의로 만든 branch_**

history, 시간선은 그대로 두고, 파일들의 상태만 이동하는 방법에 대해 알아보자.

<br>

## 5.1 git checout HEAD^~

바로 `git checkout`을 사용하는 것이다.

- `git checkout HEAD^` 또는 `git checkout HEAD~`에서 `^`와 `~`의 갯수만큼 이전으로 이동하기

  - `git checkout HEAD^^^`
  - `git checkout HEAD~5`

- 실습상황을 만들어보자. 현재 브랜치와 커밋 상황은 다음 이미지와 같다.

  ![image](https://user-images.githubusercontent.com/78094972/175890694-3c05bbec-dbbf-4842-99c5-df76d993a621.PNG)

- 현재 위치는 branch `c`의 HEAD에 위치한다.

  - 여기서 `git checkout HEAD~`을 입력하면 `c second commit`으로 이동된다.
  - `git status`로 현재 commit 위치를 확인할 수 있다.

    ```yml
    $ git status
    HEAD detached at b99000a
    ```

- `aa` branch의 HEAD 지점으로 이동하기 위해서 `git checkout HEAD~2` 를 입력한 후, `git status`로 확인하여 잘 이동된 걸 알 수 있다.

  ```yml
  $ git status
  HEAD detached at d8a94fb
  ```

<br>

## 5.2 git checkout -

> **_한 단계 되돌리기_**

- 이동을 한 단계 되돌리고 싶으면 `git checkout -` 을 입력한다.

  - 되돌아간 걸 확인하기

  ```yml
  $ git status
  HEAD detached at b99000a
  ```

<br>

## 5.3 git checkout 커밋해시

- 이번에는 `커밋 해시`를 사용해서 이동해보자.

  - `b 1st commit` 으로 이동해보자.

  ```yml
  $ git checkout 57386f752a27a3dc953361091d7a384e0fb6d3ea
  $ git status
  HEAD detached at 57386f7
  ```

  ![image](https://user-images.githubusercontent.com/78094972/175895140-851210fc-29fd-4c07-9f1c-4ebaa8faff5d.PNG)

- 위 이미지를 확인하면 `HEAD`의 위치가 현재 위치임을 알 수 있다.

  - 이 상태에서 `git branch`를 입력해보자.

  ```yml
  $ git branch
  * (HEAD detached at 57386f7)
  a
  aa
  b
  c
  ```

<br>

### git switch (branch 명): branch의 최신 위치로 이동

- 우리가 만든 branch가 아닌 익명의 branch가 만들어져서 현재 위치를 나타낸다. 즉, 위에 HEAD는 또 하나의 branch라는 것이다. 그렇다면 해당 브랜치의 최신 버전으로 이동하고 싶다면 `git branch (branch 명)`으로 이동할 수 있다는 걸 알 수 있다.

  ```yml
  $ git switch c
  ```

- 그리고, 소스트리를 업데이트하면 현재 위치가 이동한 브랜치의 최신 위치임을 알 수 있다. 즉 기존 브랜치로 돌아오면 자동적으로 최신 커밋 위치로 온다.

- 그러면 현재 위치에서 새로운 branch를 만들고, 새 commit을 만들어보자.

```yml
$ git switch -c d
$ git commit -am 'd first commit'
```

- **이처럼 다른 branch로 이동해서 분기된 branch에서 다시 새로운 branch를 만들 수 있다.**

<br>

## 5.4 HEAD 사용하여 reset 하기

> **git reset HEAD(원하는 단계) (옵션)**

- `c` branch에서 실행해보자.

```yml
$ git switch c
$ git reset --hard HEAD~2
```

<br>

---

# 6. fetch와 pull의 차이

> **_- fetch: 원격 저장소의 최신 커밋을 로컬로 가져와서 내용만 보고 싶을 때 사용한다._**  
> **_- pull: 원격 저장소의 최신 커밋을 로컬로 가져와 merge 또는 rebase를 실행하는 것으로서 fetch 과정을 포함한다._**

- `fetch`한 내역 적용 전 살펴보기

  - 원격의 `main` branch에 commit을 추가하자. 추가 후, `git checkout origin/main`으로 원격의 브랜치로 이동하기.

  - 원격의 변경사항을 `fetch`로 확인하기
    - `git checkout origin/main`으로 확인해보기. 아무것도 변화된 게 없다. -> 다시 `git checkout main`으로 로컬 branch로 돌아오기
    - 다음으로 `git fetch` 입력하기
    - 그리고, 아직 main branch에 적용하고 싶지 않고 살펴만 보고 싶다. 그러면 `git checkout origin/main`을 입력하여 확인 후,
      다시 `git switch main`으로 돌아와 서 `git pull`을 적용한다.
    - `pull`로 적용

- 원격의 새 브랜치 확인

  - `git checkout origin/(branch명)`
  - `git switch -t origin/(branch명)`

    - `git fetch` -> `git branch -a` 하면 확인가능 -> 이 branch를 확인만 하고 싶으면 `git checkout origin/branch명` 입력 -> `git checkout main` -> `git switch -t origin/remote-local`

    ```yml
    #원격에 remote-local branch를 만든다.

    $ git fetch

    $ git branch -a
    * main
    remotes/origin/main
    remotes/origin/remote-local

    $ git checkout main
    $ git switch -t origin/remote-local
    ```

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
