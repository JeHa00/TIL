# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- Chapter 11에서는 분석하고 디버깅하는 것에 대해 다룬다.  
- 그래서 이번 소단원에서는 `git log`를 옵션들과 함께 사용하여 더 자세히 살펴보겠다.  

<br>

---

# 옵션들을 활용한 다양한 사용법

<br>

### 각 커밋마다의 변경사항 함께 보기

> **_git log -p_**

- 커밋 해쉬 번호만 보여주는 게 아닌, 각 커밋마다의 변경사항을 함께 보여준다.  

```yml
$ git log -p
commit 51075e540e06075761bd17080ef3a01b79f25056
Author: Jeha00 <rudtls0611@naver.com>
Date:   Mon Jul 11 18:15:06 2022 +0900

    edit members of leopards.yaml

diff --git a/leopards.yaml b/leopards.yaml
index 2aaf3d2..ed823d4 100644
--- a/leopards.yaml
+++ b/leopards.yaml
@@ -13,5 +13,3 @@ members:
   - Dongho
   - Drax
   - Groot
-  - I\'m groot
-  - Guardians of Glaxy

commit 7a6d9965f2f366fb4d10e1739b804572beef0fcf (main2)
Author: Jeha00 <rudtls0611@naver.com>
Date:   Mon Jul 11 18:14:35 2022 +0900

    edit memebers in leopards.yaml

diff --git a/leopards.yaml b/leopards.yaml
index ed823d4..fb150cc 100644
--- a/leopards.yaml
+++ b/leopards.yaml
@@ -1,6 +1,6 @@
 team: Leopards

-manager: Peter
+manager: Harry Poter^M
 
 coach: Rocket

@@ -13,3 +13,4 @@ members:
   - Dongho
   - Drax
   - Groot
+  - No jam Thore^M
```

<br>

### 최근 n개 커밋만 보기

> **_git log -(갯수)_**

- 최근 커밋을 원하는 갯수만큼 볼 수 있다.  

```yml
# 이전 커밋 3개만 보고 싶다.
$ git log -3
commit 1dccdb61b999634cba358a0a27c5dd4d9fca7a30 (HEAD -> main)
Merge: f217dc2 7a6d996
Author: Jeha00 <rudtls0611@naver.com>
Date:   Fri Jul 15 13:50:21 2022 +0900

    Merge branch 'main2'

commit f217dc2eec6877db8a3e8828ba24e05c05f742f9
Author: Jeha00 <rudtls0611@naver.com>
Date:   Mon Jul 11 18:15:34 2022 +0900

    edit memebers in leopards.yaml

commit 51075e540e06075761bd17080ef3a01b79f25056
Author: Jeha00 <rudtls0611@naver.com>
Date:   Mon Jul 11 18:15:06 2022 +0900

    edit members of leopards.yaml
```

- 또한 `-p`와 함께 사용하여 원하는 만큼의 커밋을 보는데, 각 커밋의 변경사항과 함께 볼 수 있다.  

```yml
$ git log -p -3
commit 1dccdb61b999634cba358a0a27c5dd4d9fca7a30 (HEAD -> main)
Merge: f217dc2 7a6d996
Author: Jeha00 <rudtls0611@naver.com>
Date:   Fri Jul 15 13:50:21 2022 +0900

    Merge branch 'main2'

commit f217dc2eec6877db8a3e8828ba24e05c05f742f9
Author: Jeha00 <rudtls0611@naver.com>
Date:   Mon Jul 11 18:15:34 2022 +0900

    edit memebers in leopards.yaml

diff --git a/leopards.yaml b/leopards.yaml
index ed823d4..24e6957 100644
--- a/leopards.yaml
+++ b/leopards.yaml
@@ -13,3 +13,4 @@ members:
   - Dongho
   - Drax
   - Groot
+  - Onepiece^M

commit 51075e540e06075761bd17080ef3a01b79f25056
Author: Jeha00 <rudtls0611@naver.com>
Date:   Mon Jul 11 18:15:06 2022 +0900

    edit members of leopards.yaml
```

<br>

### 통계와 함께 보기  

> **_git log --stat_**

```yml
$ git log --stat
commit f217dc2eec6877db8a3e8828ba24e05c05f742f9
Author: Jeha00 <rudtls0611@naver.com>
Date:   Mon Jul 11 18:15:34 2022 +0900

    edit memebers in leopards.yaml

 leopards.yaml | 1 +
 1 file changed, 1 insertion(+)

commit 51075e540e06075761bd17080ef3a01b79f25056
Author: Jeha00 <rudtls0611@naver.com>
Date:   Mon Jul 11 18:15:06 2022 +0900

    edit members of leopards.yaml

 leopards.yaml | 2 --
 1 file changed, 2 deletions(-)
```

<br>
  
### 한 줄로 보기  

> **_git log --oneline_**

- `--pretty=oneline --abbrev-commit`을 줄인 것 


```yml
$ git log ---oneline
1dccdb6 (HEAD -> main) Merge branch 'main2'
f217dc2 edit memebers in leopards.yaml
51075e5 edit members of leopards.yaml
7a6d996 (main2) edit memebers in leopards.yaml
9302244 edit memebers in leopards.yaml
...
```


<br>

###  변경사항 내 단어 검색  

> **_git log -S (검색어)_**

- `S`는 반드시 대문자를 입력해야 한다.   
- `George`를 검색해본다고 하자.    

```yml
$ git log -S George
commit 679d1f1788575666f8b368c67dfbb14f69c6a637
Author: Jeha00 <rudtls0611@naver.com>
Date:   Wed Jun 22 18:28:44 2022 +0900

    add George to tigers
```

<br>

### 커밋 메시지로 검색  

> **_git log --grep (검색어)_**

```yml
$ git log --grep Olivia  
commit 904db06ba9495801734d1fa81580a269e6f37ba6
Author: Jeha00 <rudtls0611@naver.com>
Date:   Fri Jun 24 13:40:58 2022 +0900

    Add Olivia to Leopards
```

- 커밋 메시지로 검색하는 것에 대한 그 밖에 옵션들은 [기타 옵션 보기](https://git-scm.com/book/ko/v2/Git%EC%9D%98-%EA%B8%B0%EC%B4%88-%EC%BB%A4%EB%B0%8B-%ED%9E%88%EC%8A%A4%ED%86%A0%EB%A6%AC-%EC%A1%B0%ED%9A%8C%ED%95%98%EA%B8%B0#limit_options)를 참고한다.   

<br>

### 자주 사용되는 그래프 로그 보기  

> **_git log --all --decorate --oneline --graph_**

- 위 명령어 사용된 옵션들에 대한 설명은 다음과 같다. 
    - `--all`: 모든 브랜치 보기  
    - `--graph`: 그래프 표현  
    - `--decorate`: 브랜치, 태그 등 모든 레퍼런스 표시  
        - `--decorate=no`
        - `--decorate=short`: 기본
        - `--decorate=full`


🔅 이 명령어로 그래프를 보는 것보다 소스 트리로 보는 걸 추천한다.  


- 단축키를 설정하여 이 명령어를 자주 사용하기도 한다.  
    - 단축키 설정은 Chapter06을 참고하자.  

- 다음 명령어는 포맷된 로그의 한 종류다.  
    - `log --graph --all --pretty=format:'%C(yellow) %h  %C(reset)%C(blue)%ad%C(reset) : %C(white)%s %C(bold green)-- %an%C(reset) %C(bold red)%d%C(reset)' --date=short`  
    - 여기서 `data`를 `relative`로 바꿔보자.  

- 위 명령어를 단축키를 통해 사용하고자 한다면 
    - `git config --global alias.(단축키) "명령어"` 
    - 위 명령어에 입력할 때, `git`은 빼고 입력한다.  
    - `git config --global alias.gg "log --graph --all --pretty=format:'%C(yellow) %h  %C(reset)%C(blue)%ad%C(reset) : %C(white)%s %C(bold green)-- %an%C(reset) %C(bold red)%d%C(reset)' --date=short"`

    - 그리고 나서 `git gg`를 입력하면 뜬다. 


❗ 단축키 설정을 했지만, `Expansion of alias failed; not a git command` 이와 같은 에러가 발생했다면 `git update-git-for-windows`를 사용하여 업데이트 후, 다시 해보자. 

<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
