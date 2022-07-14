# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- 이번 단원에서는 Gitflow를 사용해서 협업을 위한 브랜치 활용법을 알아본다.

<br>

---

# 협업을 위한 branch 활용법

- 체계적으로 협업하기 위한 브랜칭 전략이 gitflow다.

### 사용되는 브랜치들

![image](https://nvie.com/img/git-model@2x.png)

| 브랜치  | 용도                            |
| ------- | ------------------------------- |
| main    | 제품 출시/배포                  |
| develop | 다음 출시/배포를 위한 개발 진행 |
| release | 출시/배포 전 테스트 진행(QA)    |
| feature | 기능 개발                       |
| hotfix  | 긴급한 버그 수정                |

- main

  - 실제로 사용자들에게 최종적으로 출시될 것들로서, tag가 붙여진다.

- develop

  - main을 만들어내기 위한 개발 작업은 이 브랜치에서 이뤄진다.
  - 여기서 새로운 기능을 추가하거나, 문제들을 해결해가면서 커밋들을 추가해간다.

- feature:

  - develop 과정에서 굵직한 것들은 이 feature branch에서 만들어진다.
  - 그래서 여러 개의 feature branch가 만들어 질 수 있다.
  - ex) `feature - 기능 이름`
  - 기능이 완성되면 develop branch를 합쳐진다.

- release:

  - develop branch에서 개발이 이뤄지다가, 이제 출시를 해도 될 정도로 성능과 버그 등등이 괜찮을 때 해당 브랜치에서 테스트하기 위해 이동된다.
  - 이 브랜치에서 수정되면 develop branch에 합쳐진다.
  - 이 브랜치에서 작업하다가 확실하게 출시해도 괜찮으면 main branch로 옮겨진다.

- hotfix:
  - main branch로 출시된 제품들 중에서 갑자기 오류가 발생했을 경우, hotfix branch에서 작업한다.
  - 해결되면 다시 main branch를 병합하여 출시한다.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
- [A successful Git branching model](https://nvie.com/posts/a-successful-git-branching-model/)
