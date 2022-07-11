# 0. Introduction

> 1. [Fastforward vs 3-way merge](#1-fastforward-vs-3-way-merge)
> 2. []()
> 3. []()
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

![image](https://user-images.githubusercontent.com/78094972/178200439-b46cc225-bca6-4430-8cd2-8990d39fa7f7.PNG)

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

# 2. 다른 branch에서 원하는 커밋만 따오기

<br>

---

# 3. 다른 가지의 잔가지만 가져오기

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
