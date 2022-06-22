# 0 Introduction

> 2.1 [Git 저장소 만들기](#21-git-저장소-만들기)  
> 2.2 [수정하고 저장소에 저장하기](#22-수정하고-저장소에-저장하기)

<br>

---

# 2.1 Git 저장소 만들기

- Git 저장소를 만드는 방법에는 2가지가 있다.
  - 기존 directory를 Git 저장소로 만들기
  - 기존 저장소를 clone 하기

<br>

## 2.1.1 기존 directory를 Git 저장소로 만들기

<br>

> **_git init_**  
> **_git add_**  
> **_git commit -m 'message'_**

<br>

- 기존 project를 Git으로 관리하고 싶을 때, `프로젝트의 directory 경로`로 이동해서 아래 명령을 실행한다.

```yml
> git init
> git add .
> git commit -m 'Initial project version'
```

<br>

- `git init`은 `.git` 이라는 하위 directory를 만든다.
- `.git` directory 안에는 저장소에 필요한 뼈대 파일이 존재한다.
- 하지만 `.git`이 있다고 프로젝트의 파일을 관리하지 않는다.
- **Git이 프로젝트를 관리하기 위해서는 저장소에 파일을 추가하고, 커밋해야 한다.**

> _주의사항: `.git` 폴더를 지우면 Git 관리내역이 삭제되어 과거의 내역으로 돌아갈 수 없다. (현 파일들은 유지)_

<br>

## 2.1.2 기존 저장소를 clone 하기

> **_git clone [url]_**

<br>

- 언제 clone하는가?
  - 다른 project에 참여하려거나 (contribute)
  - Git 저장소를 복사하고 싶을 때

<br>

- `git clone`은 project history를 전부 복사한다.
  - 그래서, 서버의 디스크가 망가져도 client 저장소 중에서 아무거나 하나 가져와 복구하면 된다.
  - (서버에만 적용하는 설정은 복구 불가능)

<br>

- `git-practice` 코드를 복사하려는 상황이라면

```yml
> git clone https://github.com/git-practice/git-practice
```

- 이 명령으로 `git-practice`라는 directory를 만들고, 그 안에 `.git` directory를 만든다.
- 그리고, 저장소의 데이터를 모두 가져와서 최신 버전으로 checkout 해놓는다.

---

<br>

# 2.2 수정하고 저장소에 저장하기

- '2.1 저장소 만들기' 를 통해서

  - Git 저장소를 하나 만들었다.
  - 만든 Git 저장소를 Working directory에 checkout 했다.

- Chapter 2.2 내용을 들어가기에 앞서 한 가지 내용을 정리하겠다.

  - Working directory 의 모든 파일은 `Tracked` 와 `Untracked` 로 나눠진다.

    - `Tracked` 파일은 `Unmodified` , `Modified` 그리고, `Staged` 로 나눠진다.
    - 나머지 파일은 다 `Untracked` 상태다.
    - `Untracked` 상태는 `snapshot`에도, `staging area`에도 포함되지 않은 파일이다.

  - 처음 저장소를 clone 하면 이 저장소 안에 있는 파일은 checkout 하고 나서 아무것도 수정하지 않았기 때문에, `Tracked` 상태이면서 `Unmodified` 상태다.

<p align="center"><image src ="https://git-scm.com/book/en/v2/images/lifecycle.png" width = '550' height ='400'/></p>

<p align="center">[Life cycle of file]</p>

- 다음으로 '2.2.1 파일의 상태 확인하기' ~ '2.2.3 Modified 상태의 파일을 Stage 하기' 내용을 정리하겠다.
- '2.2.1 파일의 상태 확인하기' 에서는 `git status` 명령어를 배운다.
- '2.2.2 파일을 새로 추적하기' 와 '2.2.3 Modified 상태의 파일을 Stage 하기' 에서는 `git add` 명령어를 배운다.

<br>

## 2.2.1 git status

- `git status` 명령어로 파일의 상태를 먼저 확인하자.

```yml
> git status
On branch master
nothing to commit, working directory clean
```

- 위 코드의 의미는 다음과 같다.

  - 현재 branch는 기본 branch인 master다.
  - 현재 `Tracked` 나 `Modified` 상태인 파일이 없다.

- 만약 `.git`을 삭제한 후, `git status`를 입력하면, 다음과 같은 안내문이 뜬다.

```yml
> fatal: not a git repository (or any of the parent directories): .git
```

- git이 관리하지 않는다는 의미다. 그래서 `.git`을 삭제하면 안된다.

<br>

> **`git status`는 현재 파일의 상태를 확인하기 위해 사용되는 명령어다.**

<br>

## 2.2.2 git add

- `git add` 개념을 설명해보겠다.

```yml
> echo 'My project' > README.md
> git status
On branch master
Untracked files:
  (use "git add <file>..." to include in what will be committed)

  README.md

nothing added to commit but untracked files present (use "git add" to track)
```

- `echo 'My project' > README.md` 명령어는 'My project'라는 내용을 가진 `README.md` 파일을 만들라는 명령어다.
- 파일을 만든 후, `git status` 실행하면 `Untracked files` 안에 새로 만든 파일이 있다는 걸 확인할 수 있다.
- 이는 `README.md`파일이 `Untracked` 상태임을 말한다.
- Git은 `Untracked` 상태의 파일을 `아직 snapshot에 존재하지 않는 파일`로 인식한다.
- 그러면 `Untracked` 상태를 `Trancked`상태로 바꿔보자.

```yml
> git add README.md
> git status
On branch master
Changes to be committed:
  (use "git rest HEAD <file>..." to unstage)

  new file: README.md
```

- 새로 생긴 파일의 상태가 `Changes to be committed` 안으로 들어왔다.
- 이는 `Staged` 상태임을 의미한다. 위에 개념에서 언급했듯이 `Staged`는 `Tracked` 상태에 포함되므로, `Tracked` 상태이면서 `Staged` 상태다.
- 이 상태에서 커밋을 하면 `git add`를 실행한 시점의 파일이 커밋되어, snapshot이 저장되어 저장소 히스토리에 남는다.

<br>

> **`git add` 명령어는 새로운 파일을 추적할 때 사용되는 명령어다. 그리고, `Untracked`상태에서 `Tracked` 상태로 이동된다.**

<br>

- `Tracked` 상태인 `README.md` 파일을 수정한 후, `git status` 를 입력해보자.

```yml
> git status
On branch master

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

  modified: README.md
```

- `README.md` 파일은 `Changes to be committed:` 에 있다가 `Changes not staged for commit:` 으로 옮겨졌다.
- 이는 수정한 파일이 `Tracked` 상태이지만, `Staged` 상태는 아니라는 것이다. 즉, 수정된 파일이므로 `Modified` 상태임을 말한다.
- `Modified` 상태에서 `Staged` 상태로 바꿔보자.

```yml
> git add README.md
> git status
On branch master
Changes to be committed:
  (use "git rest HEAD <file>..." to unstage)

  modified: README.md
```

- `Changes to be committed:` 로 옮겨졌다.
- `Tracked` 상태이면서 `Staged` 상태로 되었다. 이는 커밋을 실행할 때 이 파일이 포함된다는 걸 의미한다.
- 하지만, 아직 더 수정해야 한다는 걸 알게 되어 바로 커밋하지 못하는 상황이라고 생각해보자.
- 수정하고 나서 `git status`를 입력했다.

```yml
> git add README.md
> git status
On branch master
Changes to be committed:
  (use "git rest HEAD <file>..." to unstage)

  modified: README.md

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

  modified: README.md
```

- `Staged` 상태이면서 `Unstaged` 상태로 동시에 나온다.
- 이게 가능한 이유는 `git add` 명령을 실행하면 바로 `Staged` 상태로 만든다는 걸 위 예시를 통해 알았다.
- `git add` 했을 때의 시점에서의 파일이 `Staged`에 오른다.
- `git add` 후에 다시 수정을 했고, 수정한 후 `git add`를 하지 않았기 때문에 `unstaged` 상태로도 나온다.
- 즉, `git add` 명령을 실행한 후에 또 파일을 수정하면 `git add` 명령을 다시 실행해서 최신 버전을 `Staged` 상태로 만들어야 한다.

```yml
> git add README.md
> git status
On branch master
Changes to be committed:
  (use "git rest HEAD <file>..." to unstage)

  modified: README.md
```

- `Changes not staged for commit:`에서 `Changes to be committed:`로 옮겨졌다.
- 최신 상태가 `Staged`에 올랐다는 걸 확인할 수 있다.

> **`git add` 명령어는 새로운 파일을 추적할 때 뿐만 아니라, 수정한 파일을 `Staged`상태로 만들 때에도 사용된다.**  
> **`git add` 명령어는 `프로젝트에 파일을 추가하는` 라기보다는, `다음 커밋에 추가하는` 명령어다.**

---

<br>

# Reference

- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)
