# 0. Introduction

> 5. [Git의 HEAD](#5-git의-head)
> 6. [fetch vs pull의 차이](#6-fetch와-pull의-차이)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)를 중심으로 [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)을 참고하여 공부한 내용입니다.

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

- **이처럼 다른 branch로 이동 후, 분기된 branch에서 다시 새로운 branch를 만들 수 있다.**

<br>

## 5.4 HEAD 사용하여 reset 하기

> **git reset HEAD(원하는 단계) (옵션)**

- HEAD(원하는 단계) 를 `해쉬 번호` 로 생각하자. 

- `c` branch에서 실행해보자.

```yml
$ git switch c
$ git reset --hard HEAD~2
```

<br>

---

# 6. fetch와 pull의 차이

> _- fetch: 원격 저장소의 최신 커밋을 로컬로 가져와서 **내용만 보고 싶을 때** 사용한다._   
> _- pull: 원격 저장소의 최신 커밋을 로컬로 가져와  **merge 또는 rebase를 실행하는 것으로서 fetch 과정을 포함** 한다._  

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
- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)
