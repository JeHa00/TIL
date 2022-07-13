# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- 이번 단원에서는 `git cherry-pick` 명령어를 사용하여 다른 브랜치에 있는 원하는 커밋만 따오는 실습을 해본다.

<br>

---

# 다른 브랜치에서 원하는 커밋만 따오기

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

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
