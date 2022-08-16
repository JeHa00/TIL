# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- 이번 단원에서는 `git rebase --onto` 를 사용하여 다른 브랜치에서 파생된 브랜치를 다른 브랜치로 옮겨 붙여본다.

<br>

---

# 다른 브랜치에서 파생된 브랜치 옮겨붙이기

> **git rebase --onto (옮겨지는 브랜치가 붙여지는 브랜치) (옮겨지는 브랜치의 base branch) (옮겨지는 브랜치): _다른 브랜치에서 파생된 브랜치 옮겨붙이기_**

- 소단원 2와 동일한 branch와 commit을 사용한다.

![image](https://user-images.githubusercontent.com/78094972/178503782-ebe73ab3-75f8-49df-bb29-48e5b09cebeb.PNG)

- `fruit` branch에서 파생된 `citrus` branch를 `main` branch로 옮겨붙이기
  - `citrus`로 fast forward

```yml
$ git rebase --onto main fruit citrus
```

- 실행한 결과 다음과 같다.

![image](https://user-images.githubusercontent.com/78094972/178503788-c94d20e7-cb5a-4954-a160-ad3d408264a9.PNG)

- 그러면 마지막으로 `main` branch의 HEAD를 옮겨보자.

```yml
# 위 명령어를 실행하면서 branch가ㅏ citrus로 전환됬다.
$ git switch main
$ git merge citrus
$ git branch -d citrus
```

![image](https://user-images.githubusercontent.com/78094972/178504858-b799f995-cc23-48d8-bc48-724a816b62c9.PNG)

<br>

---

# rebse --onto 되돌리기

> **되돌리기 위해서 관련 브랜치는 삭제되지 않았다는 전제 하에, 해당 명령어 이전으로 되돌리기 위해서는 이 명령어로 영향을 받은 모든 브랜치들에서 하나하나 리셋을 진행해주어야 한다.**

```yml
# git reflog를 사용해서 내역을 살펴보면 rebase --onto 명령시 여러 내역들이 진행된 것을 볼 수 있다.
$ git reflog
c519fac (HEAD -> main) HEAD@{0}: merge citrus: Fast-forward
a8bfbbf HEAD@{1}: checkout: moving from citrus to main
c519fac (HEAD -> main) HEAD@{2}: rebase (finish): returning to refs/heads/citrus
```

- **main branch**
- main이 fast-forward 되기 이전 기록으로 `git reset --hard`를 실행한다.

- **citrus branch**

  - **방법 A**

    - `citrus` branch는 해당 branch가 옮겨지기 전 마지막 커밋인 `commit: Lime` 부분을 `reflog`에서 찾아 그리로 `reset --hard`한다.

  - **방법 B**
    - 다시 `rebase --onto`를 사용해서 `citrus`의 커밋들을 `main`으로부터 도로 `fruit` branch의 `orange` 부분으로 옮긴다. 이를 위해서 `orange` commit으로 checkout 후, 새로운 브랜치를 만들고 아래 명령어를 실행한다.
    - `git rebase --onto temp main citrus`
      - `citrus` branch는 `main` branch에 붙여진 것이므로, 시작 브랜치가 `main`이다.
    - 그리고, `citrus`의 두 커밋들을 해당 위치로 옮겨붙인 뒤, 새로 만든 브랜치를 삭제한다.

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
