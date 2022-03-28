
# 2. Python memory structure

<p align="center"><image src ="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbYKs0Z%2FbtqyfrLQeeZ%2FXoBHO9N7IZc2fvlLZLSwHK%2Fimg.png"/></p>

<br>

## 2.1 코드 영역

> **실행할 프로그램의 코드가 저장**되는 영역 (text 영역이라고도 한다)

- lifetime: 프로그램이 시작하고, 끝날 때까지 메모리에 계속 남아 있는다.

<br>

## 2.2 데이터 영역

> 프로그램의 **global variable과 정적(static) variable**가 저장되는 영역

- 프로그램이 시작하고, 끝날 때까지 메모리에 계속 남아 있는다.

<br>

## 2.3 Stack

> - 데이터를 임시 저장할 때 사용하는 자료구조로, 데이터의 입력과 출력 순서는 `후입선출(Last In First Out, LIFO)` 방식
> - loca var. 와 parameter var.가 저장된다.

<p align="center"><image src ="
https://media.vlpt.us/images/awesomeo184/post/c6d03dfa-ca41-46ef-b3bd-3524c7c704c4/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-10-07%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%208.25.11.png
"/></p>

- **`push(푸쉬)`**: stack에 데이터를 넣는 작업
- **`pop(팝)`**:stack에서 데이터를 꺼내는 작업
- 데이터를 넣고 꺼내는 작업에서 윗 부분을 `top`, 아랫 부분을 `bottom` 이라 한다.

<br>

- stack 영역은 `함수의 호출과 함께 생성`되고, 함수의 호출이 완료되면 소멸한다.
- `스택 프레임(stack frame)`: 스택 영역에 저장되는 함수의 호출 정보
- 메모리의 높은 주소에서 낮은 주소의 방향으로 할당된다.
- 한계가 있어서, 한계를 초과하도록 삽입할 수 없다.
- Stack overflow: 함수는 변수를 저장하기 위해 stack을 만드는데, 만들어진 stack이 메모리 용량을 넘어서면 `Stack overflow`가 발생한다.

<br>

## 2.2 Heap

> 사용자가 직접 관리할수 잇는 영역으로, 객체가 생성된다.

- 사용자에 의해 메모리 공간이 동적으로 할당되고, 해제된다.
- heap 영역은 `런타임 시`에 크기가 결정된다 (메모리가 할당된다)
- 메모리의 낮은 주소에서 높은 주소로 할당된다.

<br>
