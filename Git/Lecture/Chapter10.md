# 0. Introduction

> 1. [Fastforward vs 3-way merge](#1-fastforward-vs-3-way-merge)
> 2. [git cherry-pick](#2-git-cherry-pick)
> 3. [git rebase --onto](#3-git-rebase---onto)
> 4. []()
> 5. []()

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- 이번 단원에서는 무료파트에서 배웠던 branch를 보다 더 자세히 알아볼 것이다.

<br>

---

# 1. Fastforward vs 3-way merge

- Git에서 `merge`가 이뤄지는 두가지 방식인 **Fast forward** 와 **3-way merge**를 비교해보자.

## 1.1 Fast forward(빨리 감기)

> **_두 브랜치가 공통 커밋을 조상으로 가지고 있는데, 한 쪽 브랜치에만 이후의 커밋이 있을 때, 병합하기 위한 다른 커밋을 만들지 않고, HEAD만 이동하여 병합한 방식_**

- 아래 이미지처럼 `A` branch에서 `B` branch가 분기되었다.
- _`B` branch의 최신 버전에는 `A` branch의 최신 버전을 가지고 포함하고 있다._

![image](https://user-images.githubusercontent.com/78094972/178503782-ebe73ab3-75f8-49df-bb29-48e5b09cebeb.PNG)

- 이런 상황에서 **Fast forward**는 `A` branch의 HEAD를 단지 아래처럼 `B` branch의 최근 commit으로 옮긴 후, `B` branch를 제거하는 방법이다.

![image](https://user-images.githubusercontent.com/78094972/178200445-89343ebd-9e00-422d-b694-ff67e4c83ae3.PNG)

❗ 단점: 이런 작업을 하고나서 어떤 브랜치를 사용했고, 언제 병합했는지 기록에 남지 않는다.

- 이 방식으로 merge 작업해보기

```yml
> $ git branch another-branch
> $ git switch another-branch

# leopards.yaml의 memeber에 하나를 추가한다.

> $ git commit -am'Add memebers in Leopards.yaml'

# leopards.yaml의 memeber에 또 하나를 추가한다.

> $ git commit -am'Add memebers in Leopards.yaml'

> $ git switch main
> $ git merge another-branch
Updating 3d75f7f..09994e8
Fast-forward
leopards.yaml | 2 ++
1 file changed, 2 insertions(+)
(base)

# 그리고 another-branch를 제거한다.
> $ git branch -d another-branch
```

- 위 코드를 보면 `Fast-forward` 단어를 확인할 수 있다.

- 만약 이 방식으로 하지 않고, **_병합 커밋_**을 만들어서 merge하려면 `git merge --no-ff (병합할 branch명)`

<br>

## 1.2 3-way merge

> 이 방식은 **Fastforward**와 달리 기록이 남는 방법으로, 한 branch에서 두 branch로 분기되고, 분기된 후 각 branch에서 추가적인 commit이 발생한 상황에서 merge를 할 때의 원리를 말한다.

- 아래 이미지 같은 상황에서 분기가 시작된 부분을 기준으로, `A` branch의 최근 commit 부분, 그리고 `B` branch의 최근 commit 부분 **_총 3 군데_**를 비교하여 어느 변화를 받아들일지 또한 어느 부분을 충돌로 인식하여 사용자에게 맡겨야 하는지를 결정한는 걸 **_3-way merge_** 라 한다.

![image](https://user-images.githubusercontent.com/78094972/178225465-93b740c0-3bc6-48ca-98ce-b03c8ada03c6.PNG)

- 그래서 새로운 merge commit이 생성된다.

![image](https://user-images.githubusercontent.com/78094972/178228093-1ba0f57b-ae5d-4d17-bc47-cefd94af4568.PNG)

- `git merge --no-ff (병합할 branch명)` 를 사용한다.

  - `ff`는 fastforward를 의미한다.

  ```yml
  $ git merge --no-ff main2
  Auto-merging leopards.yaml
  CONFLICT (content): Merge conflict in leopards.yaml
  Automatic merge failed; fix conflicts and then commit the result.
  ```

<br>

---

# 2. git cherry-pick

> **git cherry-pick (가져올 commit hash): _다른 브랜치의 원하는 특정 커밋을 복사해서 가져오는 명령어_**

- 새롭게 branch와 commit을 형성하여 다음과 같은 commit tree를 구성했다.

![image](https://user-images.githubusercontent.com/78094972/178503782-ebe73ab3-75f8-49df-bb29-48e5b09cebeb.PNG)

- 위 image에서 `fruit` branch에서 `cherry` commit을 `main` branch로 가져오기 위해, 가져올 브랜치의 커밋 해쉬 번호를 가져온다.

```yml
# git cherry-pick (가져올 commit hash)
$ git cherry-pick cadfd026
[main 53ad573] Cherry
 Author: yalco <yalco@kakao.com>
 Date: Sat Jan 1 15:33:36 2022 +0900
 1 file changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 cherry
```

- 그러면 파일 목록에 새로운 파일이 추가된 걸 확인할 수 있다.

- 소스 트리에서도 확인해보면 `cherry` commit을 복사해서 가져왔기 때문에, `fruit` branch에는 여전히 존재한다.

<br>

---

# 3. git rebase --onto

> **git rebase --onto (도착 브랜치) (출발 브랜치) (이동할 브랜치): _다른 브랜치에서 파생된 브랜치 옮겨붙이기_**

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

### rebse --onto 되돌리기

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

<br>

---

# 4. 다른 가지의 마디들 묶어서 가져오기

<br>

---

# 5. 협업을 위한 branch 활용법

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
- [누구나 쉽게 이해할 수 있는 Git 입문 - merge](https://backlog.com/git-tutorial/kr/stepup/stepup1_4.html)
