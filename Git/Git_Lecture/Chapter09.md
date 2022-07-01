# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)를 중심으로 [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)을 참고하여 공부한 내용입니다.

<br>

- 이번 단원에서는 Commit에 tage를 다는 것에 대해 알아본다.

<br>

---

# 9.1 Commit에 tag 달기

- Commit에 tage를 다는 이유는 다음과 같다.

  - 특정 시점을 키워드로 저장하고 싶을 때
  - 커밋에 버전 정보를 붙이고자 할 때

- Github repository에 들어가보면 tage를 발견할 수 있다.

  - branch를 선택하는 곳 옆옆에를 보면 현재 시점 기준 225 tags 임을 알 수 있다.
  - 이 tags를 클릭하면 다음 이미지를 볼 수 있다.
    ![image](https://user-images.githubusercontent.com/78094972/176830351-7c1ff36c-5f24-4698-952b-ec2f125b5739.PNG)

<br>

## 9.1 tag 규칙

> **_tags를 입력할 때는 이 문서의 규칙 [Semantic Versioning 정보](https://semver.org/lang/ko/)을 따른다._**

- 요약하자면 다음과 같다.
  - 주.부.수 숫자로 버전을 명시한다.
  - 주: 기존 버전과 호환되지 않게 API가 바뀌면 '주 버전'을 올린다.
  - 부: 기존 버전과 호환되면서, 새로운 기능이 추가되면 '부 버전'을 올린다.
  - 수: 기존 버전과 호환되면서, 버그를 수정한 것이라면 '수 버전'을 올린다.

<br>

## 9.2 tag 종류

> **_tag 종류는 lightweight와 annotated로 나눠진다._**

- **lightweight**: 단지 특정 커밋을 가리키는 용도
- **annotated**: 작성자 정보와 날짜, 메시지, GPG 서명 포함 가능

| tag 종류               | lightweight tag | annotated tag     |
| ---------------------- | --------------- | ----------------- |
| 마지막 커밋에 tag 달기 | git tag v2.0.0  | git tag -a v2.0.0 |

```yml
# lightweight tag
$ git tag v2.0.0

# annotated tag
# 아래 명령어를 입력 후, 뜨는 창에 메시지를 작성
$ git tag -a v2.0.0
```

- 또는 아래와 같이 메시지를 같이 입력한다.

```yml
$ git tag v2.0.0 -m '(message)'
```

- 그리고 tag 명령어 종류는 다음과 같다.

| tag 명령어 종류   | 설명                    |
| ----------------- | ----------------------- |
| git tag           | 현존하는 태그 확인      |
| git show v2.0.0   | 원하는 태그의 내용 확인 |
| git tag -d v2.0.0 | 태그를 삭제             |

```yml
# 현재 tag가 version 몇인지 보여준다.
$ git tag
v2.0.0

# 해당 태그가 달린 커밋에 무슨 변화가 있는지 보여준다.
$ git show v2.0.0.
Tagger: ---
Date: ---

# 위에 입력한 메세지
tag


# 해당 태그 삭제
$ git tag -d v2.0.0
Deleted tag 'v2.0.0' (was 3d75f7f)
```

- 원하는 방식으로 태그를 달거나, 필터링, checkout 할 수 있다.

| tag 명령어                               | 설명                       |
| ---------------------------------------- | -------------------------- |
| git tag (태그명) (커밋 해시) -m (메시지) | 원하는 커밋에 태그 달기    |
| git tag -l 'v1.\*'                       | 원하는 패턴으로 필터링하기 |
| git checkout v1.2.1                      | 원하는 버전으로 체크 아웃  |

```yml
$ git tag v1.2.3 (커밋 해시) -m 'Second version'

$ git tag -l 'v1.*'
v1.2.3
```

<br>

---

# 9.2 원격의 태그 관리

### 특정 태그 원격에 올리기

> **_git push (원격명) (태그명)_**

```yml
$ git push origin v1.0.5
```

- 위 명령어를 입력한 후, GitHub 원격 저장소를 들어가면 tag의 수가 늘어난 걸 알 수 있다.

<br>

### 특정 태그 원격에서 삭제

> **_git push --delete (원격명) (태그명)_**

```yml
$ git push --delete origin v1.0.5
```

- tag의 갯수가 다시 줄어든 걸 확인할 수 있다.

<br>

### 로컬의 모든 태그 원격에 올리기

> **_git push --tags_**

- tag의 갯수가 다시 늘어난 걸 알 수 있다. tag 버전명을 따로 적지 않으면 모든 tag에 원격에 올라간다.

<br>

### GitHub의 release 기능

> **_GitHub의 올라간 tag들 중, 배포하는 버전인 배포버전들을 의미하는게 Releases 다._**

- 이에 대해 보다 이해하기 위해서 [네이버 나눔고딕 코딩글꼴 예시](https://github.com/naver/nanumfont)를 참고해보자.

  - 17개의 tags가 있지만, Releases는 14개다. 즉, tags 중에서 배포버전을 정하는 것이다.

- tags에 들어가서 원하는 태그의 오른쪽을 보면 점이 3개 있다. 이를 클릭하여 `Create release`를 클릭한다.

- 그러면 배포하기 원하는 title과 그 내용을 markdown 명령어로 입력한다.

- `Pubishing`을 클릭하여 배포한다.

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291)
