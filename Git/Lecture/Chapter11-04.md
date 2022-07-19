# 0. Introduction

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- Chapter 11에서는 분석하고 디버깅하는 것에 대해 다룬다.  
- 그래서 이번 소단원에서는 `git bisect` 라는 명령어로 코드에서 문제가 발생한 지점을 찾아내보겠다. 

<br>

---

# git bisect

- `git log`로 commit 내역을 확인해본다.  

    ```yml
    $ git log
    ...
    commit 9a9304295288c28e0ddf6f66997c1f453831c14d
    Author: yalco <yalco@kakao.com>
    Date:   Tue Jan 4 14:10:28 2022 +0900

        v4

    commit 7d167e9e36af71c5d42c77a30640610a1ba57164
    Author: yalco <yalco@kakao.com>
    Date:   Tue Jan 4 14:10:10 2022 +0900

        v3 - suspicious!

    commit 37dd5cc8d9c8f93e293fa024dbdb48bf9d5b0170
    Author: yalco <yalco@kakao.com>
    Date:   Tue Jan 4 14:09:28 2022 +0900

        v2

    commit aded506c5583e1556ba052facb5aeb169afbc880
    Author: yalco <yalco@kakao.com>
    Date:   Tue Jan 4 14:09:20 2022 +0900

        v1
    ```

- 많은 버전을 하나 하나 실행해보면서 오류가 어디서부터 시작했는지 찾을려면 시간이 많이 걸린다. 

- 그럴 때 `git bisect`를 사용한다.  
    - 이진 탐색으로 점차 탐색 범위를 줄여가면서 오류의 원인을 찾아나간다.  

- 그러면 오류 원인을 찾아보자!

- 첫 번째, **이진 탐색 시작**
    - `git bisect start`

- 두 번쨰, **오류발생 지점임을 표시**
    - 현재 시점에서는 오류가 발생된 걸 확인했으므로, `git bisect bad`


- 세 번째, **의심 지점으로 이동**
    - `git checkout (해당 커밋 해시)`
    - commiet message가 v3 지점으로 이동해본다.  


- 네 번째, **오류 발생 않을 시 양호함 표시**
    - `git bisect good`
    - 이동했지만, 에러가 없는 시점이므로, 위와 같이 입력한다.  
    - 그러면 `git bisect good` 과 `git bisect bad`를 입력한 두 커밋의 중간 지점으로 자동적으로 이동한다.  


- 다섯 번째, **원인을 찾을 때까지 반복**
    - `git bisect good/bad`를 입력하면서 반복하며 좁혀지다가 끝에 다다르면 밑 메세지와 같이 동일한 메세지가 뜬다.  


    ```yml
    eb18f28cad35687a712ff2c58dbfcba6ac6d97a9 is the first bad commit
    commit eb18f28cad35687a712ff2c58dbfcba6ac6d97a9
    Author: yalco <yalco@kakao.com>
    Date:   Tue Jan 4 14:10:39 2022 +0900

        v5

    program.yaml | 4 ++--
    1 file changed, 2 insertions(+), 2 deletions(-)
    ```

- **이진 탐색 종료**
    - `git bisect reset`


<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
