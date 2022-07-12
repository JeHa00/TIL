# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

---

# 7-1. Commit message 작성 시, 유의사항

<br>

## 7-1.1 작업 커밋할 때 권장사항

**Commit message 작성 시 유의사항**

1. **어떤 작업이 이뤄졌는지 다른 누가 보더라도 알아볼 수 있는 내용이 담겨져야 한다.**

   - 왜냐하면 혼자서만 프로젝트를 진행하는 게 아니기 때문이다.

2. **하나의 커밋에는 한 단위의 작업을 넣는다.**

   - 한 작업을 여러 버전에 걸쳐 커밋하지 않는다.

   - 여러 작업을 한 버전에 커밋하지 않는다.

3. **합의된 방식을 잘 준수하여 '일관된 형태의 커밋'을 작성해야 한다.**

<br>

## 7-1.2 Commit message convention

> **_'Commit message convention'이란 commit message를 작성하는 방식인데 전세계 개발자들 사이에 많이 공유되고 권장되는 방식을 알아보겠다._**

- Convention: 팀원들끼리 어떤 것의 작성하는 방식을 합의를 해 놓은 것을 말한다.

  - 정해진 답이 아닌, 각 팀과 그 업무에 가장 적합한 걸 택한 것

- **_Commit message 방식_**

  ```yml
  type: subject

  body (optional)
  ...
  ...
  ...

  footer (optional)
  ```

  - 예시

  ```yml
    feat: 압축파일 미리보기 기능 추가

    사용자의 편의를 위해 압축을 풀기 전에
    다음과 같이 압축파일 미리보기를 할 수 있도록 함
        - 마우스 오른쪽 클릭
        - 윈도우 탐색기 또는 맥 파인더의 미리보기 창

    Closes #125
  ```

- **그러면 type, subject, body, footer에 대해 알아보자.**

  - type

    | type     | explanation                   |
    | -------- | ----------------------------- |
    | feat     | 새로운 기능 추가              |
    | fix      | 버그 수정                     |
    | docs     | 문서 수정                     |
    | style    | 공백, 세미콜론 등 스타일 수정 |
    | refactor | 코드 리팩토링                 |
    | perf     | 성능 개선                     |
    | test     | 테스트 추가                   |
    | chore    | 빌드 과정 또는 보조 기능 수정 |

  - subject

    - 커밋의 작업 내용 간략히 설명

  - body

    - 길게 설명할 필요가 있을 시 작성

  - footer
    - **breaking point** 가 있을 때
    - 특정 이슈에 대한 해결 작업일 때

<br>

## 7-1.3 Gitmoji

Commit message에 이모지를 넣어서 입력하는 방식도 있다.
이는 Chapter 12에서 학습한다.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
