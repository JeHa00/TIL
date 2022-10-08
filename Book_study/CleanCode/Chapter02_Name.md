# 0. Introduction

- 아래 book study는 로버트 C. 마틴이 지은 [클린 코드(Clean code)](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&barcode=9788966260959) 를 읽고 진행한 book study 입니다.
- 각 chapter를 읽고 내용 정리하는 식으로 진행했습니다.
- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바라며 시작합니다.

<br>

### 어떤 자세로 읽어야 하는가?

- 장인 정신을 익히는 과정은 두 단계(: 이론 과 실전)로 나뉜다고 한다.
    - 이론: 장인에게 필요한 원칙, 패턴, 기법, 경험이라는 지식을 습득하는 단계
    - 실전: 열심히 일하고 연습하여 이론을 체화시키는 단계
- 예로 들자면 ‘자전거 타기’를 들 수 있다. 이 자전거 타는 것에 대해 수학적으로 미적분을 활용하여 물리적인 원리를 설명하고 이해할 수 있으나, 이를 가지고 자전거를 탈 수 있는 건 아니다.
- 코드도 그렇다.  이론만 안다고 깨끗한 코드를 작성할 수 없고, 직접 부딪히고 고생해보고 깍아내는 노력을 통해서 나오는 결과물이 ***‘Clean code’*** 다.
- 그래서 이 책은 ***마음 편히 읽을 책이 아닌, 손과 마음으로 고생할 준비로 읽어야 하는 책*** 이다.

<br>


### 책의 큰 구성 설명

- 책 구성 순서: ***클린 코드(clean code)를 작성하는 원칙,패턴 → 여러 사례 연구를 보면서 타당한 이유를 통해 문제가 적은 코드로 바꿔보기 → 결론***
- 그러면 첫 번째 순서부터 진행해보자.

<br>


---

# 2장 의미 있는 이름

소프트웨어에서 이름은 변수, 함수에도 이름을 붙이고, 인수 - 클래스 - 패키지, 소스 파일, 디렉토리 에도 붙인다.

그 만큼 명명은 개발자에게 많은 비중을 차지하고, 중요하므로 **이름을 잘 짓는 간단한 규칙을 몇 가지 소개한다.**

<br>


### 의도를 분명히 밝히는 것의 중요성

좋은 이름에는 시간이 걸리지만, 그 후에는 이 이름으로 절약하는 시간이 더 많다.

그러므로, **이름을 주의 깊게 살펴 더 나은 이름이 떠오르면 개선하라.**

이름을 듣고, ‘존재이유 + 수행 기능 + 사용방법은 무엇인가?’ 라는 질문이 생각나고, 주석이 필요하다고 느낀다면 의도를 분명히 드러내지 못한 것이다.  

**이름이 주석이 되도록 하라.**

```python
## case 1
# 잘못된 변수명 + 주석 O
d = 0 # 경과 시간(단위: 날짜)

# 좋은 변수명 + 주석 X
elapsedTimeInDays = 0
daysSinceCreation = 0
daysSinceModification = 0
fileAgeInDays = 0

####################################

## case 2
# 잘못된 변수명 + 주석 O
a = 0 # A 학점을 받은 학생 수
b = [80, 20, 30, 50, 90, 95, 98] # 점수 목록
for i in len(b):
	if b[i] >= 90: # b[i] 점수를 의미
		a += 1

# 좋은 변수명 + 주석 X
countStudentsGetA = 0
aScoreStandard = 90 
scoreList = b = [80, 20, 30, 50, 90, 95, 98]
for i in len(scoreList):
	if	scoreList[i]  >= aScoreStandard
		countStudentsGetA += 1 

```

<br>


### 그릇된 정보를 피하라

코드에 그릇된 정보를 남겨서는 안된다. 

그러면 그릇되었다는 기준이 무엇일까? 

- 첫 번째, **그 단어가 나름대로 널리 쓰이는 의미가 있다면 다른 의미로 작성했을 지라도, 의미가 그릇되어 전달될 수 있다.**
    - 여러 계정을 그룹으로 묶을 때, 실제 List가 아니면 accountList라 하면 안되는데, 왜냐하면 프로그래머에게 List는 특수한 의미를 가지기 때문이다.  실제 데이터 타입이 list가 아니면 그릇된 정보를 제공하는 것
- 두 번째, **서로 흡사한 이름을 사용하여 차이를 알아차리지 못하는 것**도 그릇된 정보로서, **일관성이 떨어지는 표기법**
    - 이름 몇 글자만 입력한 후, 후보 목록이 뜨는데 그 목록들이 이름만 보고 각 개념 차이가 명백한다면 코드 자동 완성 기능은 굉장히 유용하다.

<br>


### 의미 있게 구분하라: 읽는 사람이 차이를 알도록 작명하라

**단지 컴파일러나 인터프리터만 통과하려는 생각으로 코드를 구현하지 마라.**

**읽는 사람이 차이를 알도록 작명하라.** 

- 연속적인 숫자를 사용하지 말라
    - ex)a1, a2, …, aN
    - 아무런 정보를 제공하지 못하는 이름으로, **저자 의도가 전혀 드러나지 않는다.**
    
    ```python
    # 잘못된 예시
    a1 = ''
    a2 = ''
    ```
    
- 불용어(noise word)를 추가한 이름도 아무런 정보를 제공하지 못 한다.
    - 아래 예시의 함수명은 확실하게 차이를 느낄 수 없다.
    - info, data, record는 a, an, the와 마찬가지로 의미가 불분명한 불용어다.
        - 확실한 차이가 있다면 사용해도 된다.
    
    ```python
    # 잘못된 예시
    def getUserInfo(): pass
    def getClientData(): pass
    def getCustomerRecord(): pass
    
    # 좋은 예시
    def getUser(): pass 
    ```
    
    - 또 다른 잘못된 예시
    
    ```python
    # 잘못된 예시
    class GetActiveAccount:
    class GetActiveAccounts:
    class GetActiveAccountInfo:
    ```
    

<br>


### 발음하기 쉬운 이름을 사용하라

개발은 여러 사람과 의논을 하면서 진행하는 것이기 때문에, 변수를 언급하는 경우가 많다. 그래서 발음하기 쉬운 변수명으로 작성하라.

```python
## 잘못된 예시
# generate date, year, month, day, hour, minute, second
genymdhms = '' 
modymdhms = ''

## 좋은 예시
generation_time_stamp = '' 
modification_time_stamp = ''
```

<br>


### 검색하기 쉬운 이름을 사용하라

나중에 버그를 해결하기 위해서, 변수를 찾을 때 쉽게 찾을 수 있도록 작명하라.

- 이런 관점에서 볼 때, 긴 이름이 짧은 이름보다 좋다.
- 검색하기 쉬운 이름이 상수보다 낫다.

```python
# 잘못된 코드
for j in range(0, 34):
	s += (t[j]*4) / 5

# 좋은 코드
real_days_per_ideal_day = 4
work_days_per_week = 5 
sum = 0 
for j in range(number_of_tasks):
	real_task_days = task_estimate[j] * real_days_per_ideal_day
	real_task_weeks = real_task_days / work_days_per_week
	sum += real_task_weeks
```

<br>


### 인코딩을 피하라

유형이나 범위 정보까지 넣으면 해독이 어렵고, 발음하기도 어렵다. 

<br>


### 자신의 기억력을 자랑하지 말라.

- **문제 영역이나 해법 영역에서 사용하지 않는 이름을 변수로 사용하지 말라.**
    - 예를 들어서 자신이 아는 이름으로 작성한 경우를 말한다.
- 루프에서 반복 횟수를 세는 변수 i, j, k 는 괜찮다(l은 절대 안된다).
    - 왜냐하면 전통적으로 이 부분에서는 한 글자를 사용하기 때문이다. 그 이외에는 적절하지 않다.
- **똑똑한 개발자가 아닌 전문가 개발자는 ‘명료함이 최고’라는 사실을 이해**한다.
    - 전문가 개발자는 남들이 이해하는 코드를 내놓는다.

<br>


### 해법 영역, 문제 영역에서 가져온 이름 사용하기

**먼저, 각 영역에 관련이 깊은 코드면 그 영역에서 이를 가져와야 한다.**

그렇지 않다면 다음과 같은 순서를 따르자.  

- 해법 영역에서 가져오기
    - 전산 용어, 알고리즘 이름, 패턴 이름, 수학 용어 등을 사용해도 괜찮다.  모든 이름을 문제 영역에서 가져온다면 변수를 이해하기 위해 매번 고객에게 의미를 물어봐야하기 때문이다. **기술 개념에는 기술 이름이 가장 적합한 선택이다.**
- 문제 영역에서 가져오기
    - **위에 해법 영역에서 적절한 용어가 없을 경우, 문제 영역에서 이름을 가져온다.**  개발자는 그러면 전문가에게 의미를 물어 파악할 수 있다.

우수한 개발자와 설계자라면 해법 영역과 문제 영역을 구분할 줄 알아야 한다. 

<br>


### 클래스, 메서드 이름

- 클래스 이름
    - 명사, 명사구 적합  + 동사 사용 x
    - Manager, Processor, Data, Info 등 같은 단어는 피하기
- 메서드 이름
    - 동사, 동사구 적합
    - ex) 좋은 예시: postPayment, deletePage, save

<br>


### 기발한 이름 피하기

- **재미난 이름보다 명료한 이름을 선택하라. 의도를 분명하고, 솔직하게 표현해라.**
- 특정 문화에서만 사용하는 농담은 피하는 편이 낫다.

<br>


### 한 개념에 한 단어 사용하기 + 말장난 피하기

- 첫 번째, **추상적인 개념 하나에 단어 하나만을 사용하도록 선택하여 이를 고수하라.**
    - **일관성 있는 어휘는 코드를 사용할 프로그래머가 반갑게 여겨야 한다.**
    - 예를 들어 Manager, Controller 는 근본적으로 무엇이니 다른가? 이름이 다르면 독자는 당연히 클래스도 다르고, 타입도 다르리라 생각한다.
- 두 번째, **‘일관성’보다 문맥이 우선이다.**
    - 기존 add method는 기존 값 두 개를 더하거나, 새로운 값을 만드는 기능이라고 할 때, 집합에 값 하나를 추가하는 method를 만들려고 한다. 변수명을 어떻게 할 것인가?

**맥락이 다르기 때문에, 동일하게 작성하면 안된다.** 

**집중적인 탐구가 필요한 코드가 아닌, 대충 훑어봐도 이해할 수 있는 코드 작성이 목표!**

<br>


### 의미 있는 맥락 추가하고, 불필요한 맥락 제거하기

의미가 분명한 경우에 한해서 짧은 이름이 긴 이름보다 낫다. 즉, **길이보다 분명한 의미 전달이 더 중요**하다.

- 의미가 분명하지 않은 이름들이 대다수이기 때문에 알고리즘, 클래스, 함수, 이름 공간에 넣어 맥락을 표현하라.
- 그래도 불분명하면 접두어를 사용

아래 코드는 끝까지 읽어야만 의미를 알 수 있다. 즉, 메서드만 훑어서는 변수의 의미가 불분명하다. 

```python
## Case 1: 접두어 사용 예시

# 나쁜 코드: 맥락이 불분명한 변수
firstName = ''
lastName = ''

# 좋은 코드: 맥락이 분명한 변수

class Address():

    def addFirstName():
            pass

    def addLastName():
            pass 

###########################

## Case 2

# 나쁜 코드: 초반부만 읽어서 변수 세 개가 어디에 사용되는지 명확하지 않음
def printGuessStatistis(candidate: str, count: int) -> None:
    number = str() 
    verb = str()
    pluralModifier = str() 
    
    if not count: 
            number = 'no'
            verb = 'are'
            pluralModifier = 's'

    elif count == 1:
            number = '1'
            verb = 'is'
            pluralModifier = ''
    
    else:
            number = str(count)
            verb = 'are' 
            pluralModifier = 's'

    print("There {} {} {}{}".format(verb, number, candidate, pluralModifier))

# 좋은 코드
class GuessStatisticsMessage():
    
    def __init__(self, number: str, verb: str, plural_modifier: str):
        self.number = number
        self.verb = verb
        self.plural_modifier = plural_modifier  				

    def make(self, candidate: str, count: int) -> str:
        self.candidate = candidate
        self.count = count 
        self.createPluralDependentMessageParts(self.count)
        return f'There {self.verb} {self.number} {self.candidate}{self.plural_modifier}'

    def createPluralDependentMessageParts(self, count):

        if not count:
            self.thereAreNoLetters()

        elif count == 1: 
            self.thereIsOneLetter()

        else:
            self.thereAreManyLetters(count)

    def thereAreManyLetters(self, count):
        self.number = str(count)
        self.verb = 'are'
        self.plural_modifier = 's'

    def thereIsOneLetter(self):
        self.number = '1'
        self.verb = 'is'
        self.plural_modifier = ''
        
    def thereAreNoLetters(self):
        self.number = 'no'
        self.verb = 'are'
        self.plural_modifier = 's'
```

다음으로 불필요한 맥락에 대해서 얘기해보겠다.

- GSD(고급 휘발유 충전소, Gas Station Deluxe의 약어) 라는 애플리케이션을 만든다고 하여, 클래스 이름 앞에 GSD를 붙이지 말자. G를 입력하고, 자동 완성을 누를 경우, 모든 클래스가 나타나서 효율적이지 못하다.
- GSD 회계 모듈에 메일 주소 클래스를  GSDAccountAddress로 했다. 이와 비교해서 MailingAddress class와 비교했을 때 무엇이 적절할까?
- 바로 후자다. 전자는 불필요한 맥락이 많다.

<br>


### 제일 어려운 점

- **좋은 설명 능력 + 동일한 문화적 배경 → 좋은 이름 선택**
    - 이 부분이 제일 어렵다.
- 이름을 바꾸지 않으려는 이유가 다른 개발자가 반대할까 두려워서다.
- 개발자들 대다수는 클래스와 메서드 이름을 모두 암기하지 못 하기 때문에, 암기는 도구에게 맡기자.
- 이름을 바꿔서 코드 가독성을 높여보자.

---

# Reference

- [클린 코드(Clean code)](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&barcode=9788966260959)