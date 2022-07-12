# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

---

# 7-2. 보다 꼼꼼히 staging 하기

### staging 환경 만들기

1. Tigers 변경

   - manager: `Thanos`
   - coach: `Ronan`
   - new members: `Gamora`, `Nebula`

2. Leopards 변경
   - manager: `Peter`
   - coach: `Rocket`
   - new members: `Drax`, `Groot`

### hunk 별로 staging 진행하기

- hunk란 code가 수정되는 부분에서 수정되지 않는 부분까지를 의미한다.

  - 위에 Tigers를 기준으로 보자면 manager와 coach는 연달아 있으므로 한 hunk에 해당하지만, members까지 수정안되는 부분이 있기 때문에 끊어지고, 추가되는 members가 별도의 hunk가 된다.

- 이 hunk 단위로 staging area에 올리고 싶으면 `git add -p` 이고, `-p`는 `patch`의 약자다.

```yml
 team: Leopards

-manager: Dooli
+manager: Peter

-coach: Lupi
+coach: Rocket

 members:
   - Linda

(1/2) Stage this hunk [y,n,q,a,d,j,J,g,/,s,e,?]?
```

- 위와 같이 뜬다.

  - 각 옵션 설명을 보려면 `?` 입력 후, 엔터
  - `y` 또는 `n`으로 각 헝크를 선택한다.
  - 일부만 staging 하여 진행해보고, git status와 소스트리로 확인해보자.

```yml
$ git status
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        modified:   leopards.yaml
        modified:   tigers.yaml

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   leopards.yaml
        modified:   tigers.yaml

```

- 부분적으로 staging area에 올렸기 때문에, 위와 같이 뜬다.
- 그러면 아래 명령어를 입력하여 부분적으로 나눠서 commit 하자.

```yml
$ git commit -m 'Edit Tigers and Leopards'
$ git add .
$ git commit -m 'Edit Leopards and Tigers'
```

- 마지막으로 `git diff --staged`와 비교해보자.

  - 이 명령어는 이번 commit에서 변경된 사항을 확인하는 명령어다.

- 이 `git commit -v`는 커밋과 이 `git diff --staged`를 같이하는 명령어라고 생각하면 된다.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
