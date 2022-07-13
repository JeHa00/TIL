# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- 이번 단원에서는 `git merge --squash` 명령어를 사용해서 다른 가지의 마디들을 묶어서 가져와본다.

<br>

---

# 다른 가지의 마디들 묶어서 가져오기

> **git merge --squash (대상 브랜치): _다른 branch의 구체적인 commit 내역을 기억하고 싶지 않아서 다른 branch의 커밋들을 묶어서 한 커밋으로 가져오는 명령어_**

- 소단원 2,3과 동일한 branch와 commit을 사용한다.
- 현재 branch는 `main`이며, `root` branch에 있는 커밋들을 묶어서 한 커밋으로 가져오겠다.

```yml
$ git merge --squash root

$ git status
On branch main
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file:   beet
        new file:   potato
        new file:   radish

# git commit -m 으로 commit message를 별도로 적어도 되고, 아래와 같이 해도 된다.
$ git commit
Squashed commit of the following:
```

- `git log`를 통해서 합쳐진 것을 알 수 있다.

```yml
$ git log
commit b2d15aa2b4b9f7b7d63036fce1256274168fb7b7 (HEAD -> main)
Author: Jeha00 <rudtls0611@naver.com>
Date:   Wed Jul 13 12:48:30 2022 +0900

    Squashed commit of the following:
```

<br>

## 일반 merge와의 차이

- 일반 `merge`와 `merge --squash`는 실행 후, 코드의 상태는 같지만 내역 면에서 큰 차이가 있는 것이라 이해해자.
- 일반 merge: A와 B 두 브랜치를 한 곳으로 이어붙인다.
- merge --squash: B 브랜치의 마디들을 복사하여, 한 마디로 모아 staged state로 A 브랜치에 붙인다.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
