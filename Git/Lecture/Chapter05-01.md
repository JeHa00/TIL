# 0. Introduction

> 1. [Git의 장점: Snapshot과 DVCS](#1-git의-장점-snapshot과-dvcs)
> 2. [Git의 3가지 공간](#2-git의-3가지-공간)
> 3. [git 명령어로 파일 삭제, 이동](#3-git-명령어로-파일-삭제-이동)
> 4. [reset의 세 가지 옵션](#4-reset의-세-가지-옵션)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)를 중심으로 [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)을 참고하여 공부한 내용입니다.

<br>

---

# 1. Git의 장점: Snapshot과 DVCS

Git의 장점인 Snapshot 방식과 DVCS에 대해 알아보자.

[What is git?](https://jeha00.github.io/post/git-github/0_whatisgit/)에 대한 요약버전이다.

<br>

## 1.1 Snapshot 방식

Snapshot 방식에 대해 알아보기 전에 git의 이전 버전들이 사용한 **_델타 방식_** 에 대해 알아보자.

### 델타 방식

- 델타방식은 해당 파일 전체가 **처음 시점을 기준으로 이어져서** 그 이후 변경지점만 누적되어 저장된다.

  ![image](https://user-images.githubusercontent.com/78094972/155179219-cf4c2d80-642e-484a-9d85-29c3854f64bc.png)

  - 버전 5시점에서는 델타1, 델타2, 델타 3이 저장되는 것이다.

### snapshot 방식

- 스냅샷 방식은 새로운 버전이 만들어질 때, 해당 버전에서의 각 파일이 최종 상태 그대로 저장되어 있다.

  ![image](https://git-scm.com/book/en/v2/images/snapshots.png)

  - 버전 5에서 A는 변화가 없으니까 버전 4에서 가져온다. 이 저장도 용량을 별로 차지하는 방식으로 저장된다.

### 차이점

그렇다면 이 2가지의 차이점을 이해하기 위해 한 상황을 가정해보자.

VSC 프로젝트처럼 커밋이 몇 만개가 있는 레포지토리를 델타버전으로 다룬다면 어떨까??

**델타 방식은** Git에서 뭘 할 때마다 각 파일들을 그거가 처음 만들어진 시점부터 변경사항들을 쭈욱 더해서 현재 내용을 계산해야하니 history가 길수록 되게 느려진다.

반면에 **스냅샷은** 그냥 현 시점에 각 파일들이 풀로 저장되어 있으니까 아주 빠르다.

<br>

# 1.2 DVCS

CVCS는 원격 저장소와의 인터넷 연결이 끊기면 로컬에서 할 수 있는 게 제한적이다.

반면에 Git은 clone 명령어로 가져오면 전체 Git commit 내역과 branch까지 가져오기 때문에 인터넷 연결 상태와 상관없이 로컬에서 자유롭게 작업할 수 있다.

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

### Working directory

- Untracked: Add된 적 없는 파일, ignore된 파일
  - Add된 적 없는 파일이란? git이 관리한 적이 없는 파일, 새로 생긴 파일을 말한다.
- Tracked: Add된 적 있고, 변경내역이 있는 파일
- `git add` 명령어에 의해서 `Working directory`에서 `Staging area`로 올라온다.

### Staging area

- 커밋을 위한 준비 단계
  - ex) 작업을 위해 선택된 파일들
- `git commit` 명령어로 repository로 이동
- Staging area에서 working directory로 CLI로 이동하기
  - `git restore --staged (파일명) `
    - `--staged`를 빼면 `working directory`에서도 제거 = 변화를 제거한다.
  - 소스트리로는 단지 스테이지에서 내리기를 클릭한다.

### Repository

- `.git repository`라고도 불린다.
- 커밋된 파일들이 들어간 곳

<br>

---

# 3. git 명령어로 파일 삭제, 이동

### 파일 삭제: git rm

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

  $ gim rm tigers.yaml
  $ git status
  Changes to be committed:
          deleted:    tigers.yaml
  ```

<br>

### 파일명 변경: git mv

- `git mv (파일명을 변경할 파일) (바꿀 이름)`
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

  $ git mv tigers.yaml zzamtigers.yaml
  $ git status
  Changes to be committed:
          renamed:    tigers.yaml -> zzamtigers.yaml
  ```

<br>

---

# 4. reset의 세 가지 옵션


### --soft

> `repository`에서 `staging area`로 이동. 

- staging area에 남겨놓는다.
- `--mixed`에서 `git add`로 추가한 옵션  

### --mixed

> default로 `repository`에서 `working directory`로 이동. 

- working directory에 남겨놓는다.

### --hard
> 수정사항 내역을 완전히 삭제. 

- working directory에서조차 삭제한다.

### 실습

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

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)
