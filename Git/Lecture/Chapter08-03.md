# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- 이번 단원에서는 `reset`으로 사라진 커밋을 복구할 수 있는 `reflog` 명령어를 알아본다.

<br>

---

# git reflog

> 프로젝트가 위치한 commit이 바뀔 때마다 기록되는 내역을 보여주고, 이를 사용하여 `reset` 하기 이전 시점으로 프로젝트를 복구할 수 있다. 즉, 내가 사용한 Git 작업을 기준으로 과거내역을 살펴보고 원하는 시점으로 프로젝트를 되돌릴 수 있다.

- 현재 기준 15번째 전 commit으로 reset 해본 후, `git reflog`를 입력해보면 여태 입력했던 명령어들과 각 명령어에 해당되는 commit 번호를 확인할 수 있다.

```yml
$ git reset --hard HEAD~15
$ git reflog
ed807a6 (HEAD -> main) HEAD@{0}: reset: moving to HEAD
ed807a6 (HEAD -> main) HEAD@{1}: reset: moving to HEAD~15
c99c341 HEAD@{2}: reset: moving to HEAD~15
3d75f7f (origin/main) HEAD@{3}: merge remote-branch: Fast-forward
1b2bbcb (tag: v1.0.5) HEAD@{4}: checkout: moving from remote-branch to main
3d75f7f (origin/main) HEAD@{5}: checkout: moving from main to remote-branch
1b2bbcb (tag: v1.0.5) HEAD@{6}: checkout: moving from remote-branch to main
3d75f7f (origin/main) HEAD@{7}: checkout: moving from main to remote-branch
1b2bbcb (tag: v1.0.5) HEAD@{8}: pull origin main: Fast-forward
...
```

- 그러면 제일 최근 단계로 돌아가기 위해서는 `commit 해쉬번호`
  를 복사한 후, `git reset --hard <복사한 commit 해쉬번호>` 를 입력한다.
  - 가장 최근 상태의 커밋 번호가 `3d75f7f` 이므로 `git reset --hard 3d75f7f`을 입력하면 원 상태로 돌아온다.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
