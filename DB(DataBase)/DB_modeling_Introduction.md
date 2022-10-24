# 0. Introduction


- 해당 내용은 [생활코딩 관계형 데이터 모델링](https://www.youtube.com/watch?v=1d38YZKCM88&list=PLuHgQVnccGMDF6rHsY9qMuJMd295Yk4sa)을 보고 부분적으로 학습한 내용입니다. 

<br>

---

# DB modeling의 단계

> **_업무파악 -> 개념적 데이터 모델링(ERD) -> 논리적 데이터 모델링(table) -> 물리적 데이터 모델링(code)_**  

## 업무파악

업무파악 단계에서는 UI를 그려보면서 사용자의 요구사항에 대해 말이 아닌 시각적으로 서로 맞춰보며 '동기화' 하는 작업이다. 

말로는 의도를 정확히 표현하기 어렵기 때문에, 만나서 UI를 그려보면서 의도를 맞춰보는 것이다.  

<br>

## 개념적 데이터 모델링

이 단계를 잘 해야 다음 단계들을 잘 진행할 수 있다.  

이 단계에서는 **_ERD(Entity Relationships Diagram)_** 를 그려본다. 

매우 복잡한 현실를 ERD로 그리는 단계를 통해서 그룹(객체)들이 가지는 정보들은 무엇이고, 여러 그룹들 간의 관계를 확인할 수 있다.  

그래서 문제를 보다 더 잘 이해할 수 있다. 

### ERD
ERD 기호와 선에 대한 설명은 다음 링크를 참고한다. 

- [ERD 란 무엇이고 어떻게 사용할까?](https://mulmandu17.tistory.com/68)

1:N, 1:1, N:M 관계뿐만 아니라, Mandatory, Optional 또한 고려해야 한다. 이 선을 나타낼 때는 A와 B 객체에 대해 관계 선을 표현하고자 한다면, A의 기준에서 B를 바라보고, 그 반대의 경우도 바라봐야 정확하게 표현할 수 있다. 


❗️ ERD를 그리는데 있어서 RDB는 내포관계로 즉, 도형 안에 도형을 그리는 관계를 허용하지 않는다. 왜냐하면 거대 단일 table로 표현하면 중복이 발생하기 때문이다.


### 식별자와 인조키

ERD를 작성하고 나면 각 그룹에 대해서 식별자는 무엇으로 할지를 정한다.

만약 없다면 인조키를 추가한다. 


### 1:1, M:N 관계  

- 1:N
    - 1:N 관계인 경우, ERD 선을 그리는 것은 쉽고 명확하다.

    - 하지만, 1:1인 경우에는 의존하려는 객체에게 외래키를 줘야 한다.

- M:N 
    - 이 관계의 경우에는 `Mapping table`을 만들어서 1:N 관계들로 쪼갠다.  

<br>

---

## 논리적 데이터 모델링

ERD의 Entity는 논리적 데이터 모델링 단계에서 table이 되고, attribute는 column이 되며, relation은 PK, FK가 된다. 

<br>


## 물리적 데이터 모델링 

이 단계에서는 django로 치자면 Model.py 를 작성하는 단계로 이해했다. 

---

# Reference 

- [생활코딩 관계형 데이터 모델링](https://www.youtube.com/watch?v=1d38YZKCM88&list=PLuHgQVnccGMDF6rHsY9qMuJMd295Yk4sa)