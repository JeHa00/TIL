# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- 이번 단원에서는 git에 추적되지 않는 파일들 즉, 관리되지 않는 파일들을 삭제하는 `git clean`에 대해 알아본다.

<br>

---

# git clean option

- 아래 옵션들을 조합하여 사용하자.

| 옵션 | 설명                                |
| ---- | ----------------------------------- |
| -n   | 삭제될 파일들 보여주기              |
| -i   | interactive mode 시작               |
| -d   | 폴더 포함                           |
| -f   | 강제로 바로 지워버리기              |
| -x   | `.gitignore`에 등록된 파일들도 삭제 |

- `git clean -x`의 경우, `git clean`은 기본적으로 `.gitignore`에 등록된 파일은 삭제하지 않기 때문에 존재하는 명령어다.

- interactive mode는 관리하지 않은 파일들에 대해 하나하나 체크하고 싶을 때 사용하는 모드다.
- `git clean -x`는 함부로 사용하지 않는다.

<br>

# 실습 상황 구현해보기

- 아래 3개의 파일을 생성하자.
  - `toClean1.txt`
  - `toClean2.txt`
  - `dir/toClean3.txt`

```yml
> $ git clean -n
Would remove toClean1.txt
Would remove toClean2.txt


> $ git clean -dn
> $ git clean -nd
Would remove dir/
Would remove toClean1.txt
Would remove toClean2.txt

# 폴더를 포함시켜서 interactive mode를 시작한다.
> $ git clean -id
> $ git clean -di
Would remove the following items:
  dir/          toClean1.txt  toClean2.txt
*** Commands ***
    1: clean                2: filter by pattern    3: select by numbers
    4: ask each             5: quit                 6: help
# select by numbers mode를 선택한다.
What now> 3

# 어떤 것을 선택할지 선택하세요.
    1: dir/            2: toClean1.txt    3: toClean2.txt
Select items to delete>> 1, 3

# * 으로 선택된 파일들을 확인할 수 있다.
* 1: dir/            2: toClean1.txt  * 3: toClean2.txt
Select items to delete>>
Would remove the following items:
  dir/          toClean2.txt

*** Commands ***
    1: clean                2: filter by pattern    3: select by numbers
    4: ask each             5: quit                 6: help

# 삭제될 것으로 선택된 파일들에 대해 각각 물어봐달라
What now> 4
Remove dir/ [y/N]? y
Remove toClean2.txt [y/N]? N
Removing dir/

# 결국 'dir/' 만 삭제된 걸 알 수 있다.
```

- 그런데, 각 파일이 어떻든 상관 없이 폴더까지 포함하여 삭제하고 싶다면 아래 명령어를 사용한다.

  ```yml
  $ git clean -fd
  Removing toClean1.txt
  Removing toClean2.txt
  Removing /dir
  ```

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
