# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- 이번 단원에서는 특정 파일을 지정된 상태로 복구하는데 사용하는 `git restore`에 대해 알아본다.

<br>

---

# git restore

> **_특정 파일을 지정된 상태로 복구하는 것_**

- `git checkout`에서 `git switch`와 `git restore`로 나눠졌다.

- 파일 여러 개를 수정한 후, 명렁어 사용해보기

  - `git restore (파일명)`

    - working directory의 특정 파일의 변화를 staging area에서 working directory로 내린다.
    - 또는 파일명 자리에 `.`을 입력하면 모든 파일이 복구된다.

    ```yml
    # panthers.yaml에서 members를 수정한다.

    # 수정하기 전 상태
    > members:
    >   - Violet
    >   - Stella
    >   - Anthony
    >   - Freddie
    >   - Arachi

    # 수정 후 상태
    > members:
    >   - Violet
    >   - Stella
    >   - Anthony
    >   - Freddie
    >   - Hoki # 변경된 부분
    >   - change # 변경된 부분

    # git 명령어 실행
    > git restore panthers.yaml

    # 수정하기 전 상태로 돌아간 걸 알 수 있다.
    ```

    - 그러면 `panthers.yaml` 이외에도 `pumas.yaml`과 `leopards.yaml`을 마음대로 수정해본 후, `git restore .`을 입력하면 이 두 가지 파일 한 번에 수정 전 상태로 돌아가는 걸 확인할 수 있다.

- **변경 상태를 스테이지에서 워킹 디렉토리로 돌려놓기**

  - 이번에는 위에 3가지 파일 모두 변경 후 상태로 다시 만든 후, `git add .`으로 `staging area`에 올려보자. 그리고 `git status`로 확인해보자.

    ```yml
    $ git status
    On branch main
    Your branch is up to date with 'origin/main'.

    Changes to be committed:
    (use "git restore --staged <file>..." to unstage)
            modified:   leopards.yaml
            modified:   panthers.yaml
            modified:   pumas.yaml
    ```

  - `git restore --stage (파일명)`

    ```yml
    $ git restore --stage leopards.yaml
    $ git status

    $ git status
    On branch main
    Your branch is up to date with 'origin/main'.

    Changes to be committed:
    (use "git restore --staged <file>..." to unstage)
            modified:   panthers.yaml
            modified:   pumas.yaml

    Changes not staged for commit:
    (use "git add <file>..." to update what will be committed)
    (use "git restore <file>..." to discard changes in working directory)
            modified:   leopards.yaml
    ```

- **특정 파일을 특정 커밋의 상태로 되돌리기**

  - `git restore --source=(head 또는 commit hash) 파일명`

    - 첫 번째 commit으로 돌아가려는 상황이라고 가정하자. 그러면 첫 번재 commit의 해쉬번호를 복사하자.

    ```yml
    > $ git restore --source=ed807a60e49db810008b8fcb5fd4deddf4f200ec leopards.yaml

    # 위 커밋 시점을 기준으로 현재 working directory에 있는 것과 다른 부분들은 수정된 부분으로 인식된다.
    > $ git status
    On branch main
    Your branch is up to date with 'origin/main'.

    Changes to be committed:
    (use "git restore --staged <file>..." to unstage)
            modified:   panthers.yaml
            modified:   pumas.yaml

    Changes not staged for commit:
    (use "git add/rm <file>..." to update what will be committed)
    (use "git restore <file>..." to discard changes in working directory)
            deleted:    leopards.yaml
    ```

- 마지막으로 `git restore .`으로 되돌리자.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
