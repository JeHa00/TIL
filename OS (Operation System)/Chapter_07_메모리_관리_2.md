# 0. Introduction

> 4. [페이징 기법](#4-페이징-기법)
> 5. [세그먼테이션](#5-세그먼테이션)
> 6. [페이지드 세그먼테이션](#6-페이지드-세그먼테이션)

<br>

- 해당 내용은 [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 와 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)를 보고 정리한 내용이다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

- 이번 chapter 내용인 메모리 관리는 물리적인 메모리 관리로, 주요 내용은 address binding이다.
- address binding에서의 OS의 역할은 없고, 다 HW가 해야한다.
- address binding을 할 때마다 OS에게 CPU 제어권을 양도해도, 결국 물리적 메모리에 instruction을 실행하는 건 CPU다. 그래서 HW가 해야한다.

<br>

---

# 4. 페이징 기법

> 프로세스의 주소 공간을 동일한 크기의 **페이지 단위**로 나누어, 물리적 메모리의 서로 다른 위치에 **불연속적으로** 페이지들을 저장하는 방식

- **각 프로세스의 주소 공간 전체를 물리적 메모리에 한꺼번에 올릴 필요 없이, 스와핑을 사용하여 일부만 메모리에 올릴 수 있다.**

- **또한, 이 메모리는 페이지와 동일한 크기의 _프레임(frame)_ 으로 미리 나누어둔다.**

  - 그래서 빈 프레임이 있으면 _메모리의 어떤 위치이든_ 사용될 수 있다.
  - 이 특징으로 연속할당에서의 동적 메모리 할당 문제와 외부조각 문제가 발생하지 않는다.
  - 그러나, 프로그램의 크기가 항상 페이지 크기의 배수라는 보장이 없기 때문에 내부조각이 발생할 가능성은 있다.

- **Problem**

  - 물리적 메모리의 불연속적인 위치에 각 페이지를 올리기 때문에, 논리적 주소에서 물리적 주소로 변환하는 작업이 복잡하다.

- **Solution**

  - 위 문제에 대한 해결책으로 **페이지 테이블(page table)** 을 가진다.
  - page table이 사용하는 주소 변환기법에 대해 알아보자.

<br>

## 4.1 주소 변환(address translation) 기법

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/165469969-3e44f3d0-df6b-48ac-bc15-5eefd902cdeb.PNG"/></p>

- page table에는 각 page마다 page가 물리적 메모리에 올라간 frame 주소가 mapping되어 있다.

- logical address에서 physical address로 변환하는 구체적인 과정은 다음과 같다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/165468793-e71603b1-ef02-49a3-96e6-6cca6ba84e75.PNG"/></p>

    - p =  페이지 번호: page table 접근 시 인덱스(index)로 사용
    - 각 index의 항목(entry)에는 그 페이지의 물리적 메모리상의 기준 주소(base address)인 시작위치가 저장
    - d = 페이지 offset
    - page table에서 위에서 p 번째를 찾으면 frame 번호(f)가 나온다.
    - 이렇게 해서 logical address에서 physical address로 바뀐다.

<br>

## 4.2 페이지 테이블의 구현

> - **page table: paging 기법에서 주소 변환을 하기 위한 자료 구조로, 물리적 메모리에 위치한다.**
> - **page table에 접근하기 위해 2개의 레지스터를 사용한다.**

- **page table에 접근하기 위한 2개의 레지스터는 다음과 같다.**

  - 페이지 테이블 기준 레지스터(page-table base register, PTBR): 물리적 메모리 내에서의 페이지 테이블의 시작 위치
  - 페이지 테이블 길이 레지스터(page-table length register, PTLR): 페이지 테이블의 크기를 보관

- **Problem**

  - 페이징 기법에서 메모리 접근 연산은 **총 두 번** 이뤄진다.
    - 첫 번째: 주소 변환을 위해 메모리에 있는 페이지 테이블에 접근하기
    - 두 번째: 변환된 주소로 실제 데이터에 접근하기
  - 즉, 두 번 접근해야 하는 오버헤드가 뒤따른다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/165494535-8f3e85d0-620a-4bf1-84b7-bb3b804e4708.PNG"/></p>

- **Solution: TLB**

  - TLB란?
    - Translation Look-aside Buffer 의 약어로,
    - 테이블 접근 오버헤드를 줄이고, 메모리 접근 속도를 높이기 위한,
    - 고속 주소 변환용 하드웨어 캐시
    - page table에서 빈번히 찾는 일부 entry를 저장하고 있다.
  - TLB mechanism
    - CPU가 논리적인 주소를 주면 page table보다 먼저 TLB를 검색한다.
    - TLB 저장된 정보를 통해서 주소 변환이 가능한지 확인한다.
      - TLB에 저장되어 있다면 TLB를 통해서 바로 주소 변환을 한다.
      - 이 경우, 메모리에 한 번만 접근한다.
      - 저장되어 있지 않다면 page table을 통해서 일반적인 주소 변환을 실행한다.

- **TLB의 문제점**

  - page table의 경우
    - page 번호만큼 떨어진 항목에 곧바로 접근해 대응되는 프레임 번호를 얻는다.
  - 하지만, TLB의 경우
    - 모든 페이지에 대한 주소 변환 정보 X -> 페이지 번호와 이에 대응하는 프레임 번호가 쌍으로 저장되야 한다.
    - 해당 페이지에 대한 주소 변환 정보가 TLB에 있는지 확인하기 위해 TLB의 모든 항목을 다 찾아봐야 하는 오버헤드가 발생한다.

- **Associative register**

  - 위의 언급한 오버헤드를 줄이기 위해 병렬 탐색(parallel search)이 가능한 연관 레지스터(associative register)를 사용한다.
    - Parallel search: TLB 내의 모든 항목을 동시에 탐색할 수 있는 기능

- page table은 각 process마다 논리적인 주소 체계가 다르기 때문에, 각 프로세스마다 존재한다.
  - TLB도 그래서 각 process마다 다르게 존재한다.

<br>

## 4.3 계층적 페이징

> **2개 이상의 page table을 통해서 물리적 메모리에 접근하는 기법으로, 각 페이지를 다시 페이지화시키는 기법**

<br>

### 4.3.1 Twp-level page table이란???

- **2단계 페이징 기법(Two-level page table)은 outer-page table과 inner-page table을 통해서 Physical memory에 접근한다.**

<br>

### 4.3.2 Two-level page table을 사용하는 이유

- **현대의 컴퓨터는 address space가 매우 큰 프로그램을 지원한다.**

  - 컴퓨터 시스템에서의 K, M, G
    - K = 2^(10) = Kilo
    - M = 2^(20) = Mega
    - G = 2^(30) = Giga
  - 32 bit address 사용 시: 2^(32) byte (= 4G byte)의 주소 공간

  - 페이지의 크기가 4KB일 때, 4GB / 4KB = 1M 개의 page table entry(항목)이 필요

  - 페이지의 항목이 4 byte 라면 한 프로세스 당 페이지 테이블을 위해 1M x 4byte = 4MB 크기의 메모리 공간이 필요하다.

- **이런 상황에서 왜 2단계 페이징 기법을 사용하는가??**

  - page table이 2개라서 공간 낭비일 것 같지만, 다음과 같은 이유로 효과가 더 크기 때문에 사용한다.
  - 프로그램의 대부분은 방어용 코드로 주로 사용하는 페이지 수는 적다.
  - 그래서, 사용되지 않는 주소 공간에 대해서는 outer page table의 항목을 NULL로 설정하며, 여기에 대응하는 inner page table을 생성하지 않는다.
  - 그 결과, page table의 공간을 줄일 수 있기 때문에, 속도가 느려도 사용한다.
  - 사용하지 않는 주소 공간에 대해서 outer page table에 생성하는 이유는 page table의 자료구조 특성상 index로 작용하기 때문이다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/165732044-6ce1d363-0a50-484b-bc9a-2bfbc16e9bd6.PNG"/></p>

<br>

### 4.3.3 Two-level page table의 구성과 갯수, 크기 계산

- **logical address의 구성**

  - two level 이므로, 두 종류의 페이지 번호(P1,P2)
  - 페이지 오프셋(d)
  - P1: outer page table의 index
  - P2: inner page table의 index

- outer page table의 entry 하나 당 inner page table이 하나 만들어진다.
- inner page table 하나의 크기가 page 크기와 동일하다.
- page table entry 하나의 크기가 **_4 byte_** 라고 했는데, 그러면 entry 갯수는 **_1K_** 개다.
- page 크기가 **_4KB_** 이고, **_32bit_** 주소체계라고할 때, page number와 page offset의 크기는 다음과 같다.

  - page 크기가 4K = 2^(12) 이므로, 한 페이지를 구분하기 위해서는 page offset은 **_12bit_** 가 필요하다.
  - page table entry가 4byte이므로, 내부 페이지 테이블은 1KB = 2^(10) 개의 항목을 가진다.
  - 2^(10)개를 구분하기 위해서는 P2는 **_10bit_** 가 필요하다.
  - 그러면 총 32bit 주소체계에서 22bit를 사용했으므로, P1에는 **_10bit_** 가 할당된다.
    | P1 | P2 |Page offset|
    |----|----|----|
    | 10bit | 10bit | 12bit |

- 다음과 같은 순서로 찾는다.
  - 첫 번째: outer page table로부터 P1만큼 떨어진 위치에서 _inner page table의 주소_ 를 얻는다.
    - inner page table은 여러개가 있다. outer page table의 한 entry당 하나의 inner page table이 만들어진다.
  - 두 번째: innter page table로부터 P2만큼 떨어진 위치에서 요청된 페이지가 존재하는 _프레임의 위치_ 를 얻는다.
  - 세 번째: 해당 프레임으로부터 d 만큼 떨어진 곳에서 원하는 정보에 접근한다.

<br>

### 4.3.4 multi-level page의 문제점과 해결책

- **Problem**
  : 프로그램의 주소공간의 크기와 메모리 메모리 접근 시간이 비례하여 증가하는 문제 발생.

  - process의 address space가 커질수록 page table의 크기도 커지므로, 주소 변환을 위한 메모리 공간 낭비 점점 심각해지기 때문에, 다단계 페이지 테이블이 필요.

  - 하지만, 이에 따라 공간은 절약할 수 있지만 메모리 접근시간이 크게 늘어나는 문제가 발생.

- **Solution: TLB**  
  : TLB 와 함께 사용하여 메모리 접근 시간을 줄일 수 있고, 다단계 page table을 사용하여 메모리 공간의 효율적 사용 효과는 매우 크다.

<br>

## 4.4 메모리 보호(Memory Protection)

> **메모리 보호를 위해 page table의 각 entry마다 보호 비트(protection bit)와 유효-무효 비트(valid-invalid bit)를 둔다.**

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/165677100-dcb70c52-30a2-478e-a463-52344bdf835c.jpg"/></p>

- **보호 비트(Protection bit)**: 각 page에 대한 연산 접근 권한을 설정하는데 사용

  - read / write / read-only

- **유효-무효 비트(Valid-Invalid bit)**: 해당 페이지의 내용에 접근을 허용하는지 결정

  - `valid` 로 세팅: 해당 메모리 프레임에 그 페이지가 존재 -> 접근 허용
  - `invalid` 로 세팅 -> 유효한 접근 권한 X
    - 첫 번재 경우, 프로세스가 그 주소 부분을 사용 X
    - 두 번째 경우, 해당 페이지가 물리적 메모리에 올라있지 않고, 백킹스토어에 존재

<br>

## 4.5 역페이지 테이블(Inverted page table)

- **page table이 매우 큰 이유**

  - 모든 process 별로 그 logical address에 대응하는 모든 page에 대해 page table entry를 다 구성해야 하기 때문이다.
  - 대응하는 page가 메모리에 존재하든 안하든 page table에는 entry로 존재

- **Inverted page table**

> **logical address에 대해 page table을 만드는 것이 아닌, physical address에 대해 page table을 만드는 것**

- **시스템 전체에(system-wide) page table을 하나만 두는 방법**

  - physical address는 1개이기 때문에, physical address에 대해 page table을 만든다는 건 하나만 만드는 걸 의미한다.
  - 각 프로세스마다 page table을 두는 게 아니다.

- **page table entry 수 = Physical memory의 page frame 수**

  - Physical memory의 page frame 하나당 page table에 하나의 entry를 둔 것
  - page table entry 수 =! process의 page 갯수

- **각 page table entry는 각각의 물리적 메모리의 page frame이 담고 있는 내용 표시**

  - process의 id(pid), logical address(p)
  - 어떤 process의 p번째 페이지인지를 확인하기 위해 pid를 저장해야 한다.

- **단점: 테이블 전체를 탐색해야 한다.**

  - 역페이지 테이블에 주소 변환 요청이 들어오면, 그 주소를 담은 페이지가 물리적 메모리에 존재하는지 여부를 판단하기 위해, 페이지 테이블 전체를 다 탐색해야한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/165745426-e37842f5-596e-4d6d-95b1-87f80a05315a.PNG"/></p>

- physical address를 보고 logical address로 바꾸는 것이기 때문에, 목적에 맞지 않다.
- 그러면 이 table을 통해서 어떻게 전환할 것인가??

  - 논리주소에 해당되는 P가 물리적 메모리 어디에 올라가는지를 찾을라면 이 entry를 다 찾아서 해당되는 P가 F 번째에 나오면, f번째에 있는 물리적 프레임에 있다는 걸로 파악한다.
  - table의 장점인 index를 통해서 찾을 수 있는 장점이 없다.

- 그래서 시간이 아닌 단지 공간을 줄이기 위해서 사용되는 것이다.

- **해결책: associative register 사용한다.**
  - 연관 레지스터를 사용하여 병렬탐색을 하여 시간적 효율성을 높인다.
  - 단, 비용이 비싸다.

<br>

## 4.6 공유 페이지(Shared page)

> **shared code(공유 코드)를 담고 있는 페이지**

- **shared code란??**

  - 메모리 공간의 효율적인 사용을 위해, 여러 프로세스에서 공통으로 사용되도록 작성된 코드
  - 재진입 가능코드 (re-entrant code) 또는 순수 코드(pure code)라 한다.
  - read-only 특성을 가진다.
    -shared memory 기법에서는 read - write다.

- **프로세스 간 공유 페이지이므로 물리적 메모리에 하나만 적재하여 효율적으로 사용한다.**
- **하지만, 이 특성으로 모든 프로세스의 _logical address space_ 의 동일한 위치에 존재해야하는 제약점이 있다.**

  - 왜냐하면 logical address에서 실행 시작하여 physical address에 올라갈 때, logical address에 연결되기 때문이다.
  - Address binding 내용에서 이미지를 참고하자.

- **private page(사유 페이지)**
  - 공유 페이지와 대비되는 개념으로, 프로세스끼리 공유하지 않고 독자적으로 사용하는 페이지
  - 사유 페이지는 해당 프로세스의 논리적 주소 공간 중 어더한 위치에 있어도 무방하다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/165756401-a43070d6-6222-47ea-8f59-e9d181db0d42.PNG"/></p>

<br>

---

# 5. 세그먼테이션

> 프로세스의 virtual memory를 의미 단위의 segment로 나눠서 물리적 메모리에 올리는 기법

- **프로세스의 주소 공간을 크기 단위가 아닌 의미 단위(logical unit)로 나눈 것이기 때문에, 크기가 균일하지 않다.**

  - main (), function, global variables, stack...

- **그래서 부가적인 관리 오버헤드가 뒤따른다.**

- **segment 크기 기준**

  - 프로그램은 의미 단위인 여러 개의 segment로 구성한다.
  - 작게는 프로그램을 구성하는 함수 하나 하나를 segment로 정의한다.
  - 크게는 프로그램 전체를 하나의 세그먼트로 정의한다.
  - 일반적으로는 code, data, stack 부분이 하나씩의 segment로 정의된다.

<br>

## 5.1 Segmentation Architecture

<br>

## 5.1.1 Logical address

> 두 가지 [s: segment-number, d: offset]로 구성

<br>

### 5.1.2 Segment table

> Segmentation에서 주소 변환을 위해 사용하는 table

- 이 table은 **기준점(base)** 와 **한계점(limit)** 을 가진다.

  - 기준점:
    - 물리적 메모리에서 각 세그먼트의 시작위치를 의미.
  - 한계점:
    - 각 세그먼트의 길이를 의미.
    - 페이징 기법과는 달리 각 segment의 길이가 균일하지 않기 때문이다.

- segment의 갯수에 따라 table entry 수가 결정된다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/165777227-608c1f88-9365-41d8-adaa-1c4113b57e24.PNG"/></p>

- **CPU 안에 주소 변환을 위한 2개의 레지스터**

  - **Segment Table Base Register(STBR)** : 물리적 메모리에서의 segment table의 시작위치

  - **Segment Table Length Register(STLR)** : 프로세스의 segment의 길이와 갯수

- **Logical address를 physical address로 변환하기 위한 두 가지 사항**

  - 첫 번째: segment number(s)가 STLR에 저장된 값보다 작은 값인지 확인
    - 아니라면 trap 발생시키기
  - 두 번째: 논리적 주소의 오프셋 값(d)이 세그먼트의 길이보다 작은 값인지 확인
    - 세그먼트 테이블의 한계점과 요청된 논리적 주소의 오프셋값을 비교해 확인한다.
    - d가 더 크다면 trap 발생시키기

- **균일하지 않은 segment로 인한 paging과의 차이점들**

  - 첫 번째 차이
    - paging 기법에서는 크기가 균일하기 때문에, offset의 크기가 page 크기에 의해서 결정된다.
    - segment 기법에서는 offset 크기가 segment 크기를 제한하는 요소다.
  - 두 번째 차이
    - paging 기법에서는 크기가 균일하기 때문에, 시작 주소가 frame 번호다.
    - segment 기법에서는 크기가 다르기 때문에, 이 segment가 어디서 시작되는지 정확한 byte 단위 주소로 알려줘야 한다.

- **장점: paging과 달리 의미 단위로 끊기 때문에 segment의 갯수가 상대적으로 많이 적다.**
  - 그래서 table로 인한 메모리 낭비를 비교하자면 일반적인 시스템에서는 적다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/165791144-186c1785-f254-4097-8e25-8ca186fd6a71.PNG"/></p>

<br>

## 5.2 세그먼테이션에서의 보호비트와 유효비트

- 보호 비트(protection bit): 각 세그먼트 별로 가지고 있어서 각각에 대해 읽기/쓰기/실행 등의 권한이 있는지 나타낸다.
- 유효 비트(valid bit): 각 세그먼트의 주소 변환 정보가 유효한지, 즉 해당 세그먼트가 현재 물리적 메모리에 적재되어 있는지 나타낸다.
  - valid bit = 0 : illegal segment

<br>

## 5.3 공유 세그먼트(shared segment)

- **공유 세그먼트(shared segment)**

  - 여러 프로세스가 특정 세그먼트를 공유해 사용한다.
  - 이 세그먼트를 공유하는 모든 프로세스의 주소 공간에서 _동일한 논리적 주소에 위치_ 해야 한다.

- **장점: 공유(sharing)와 보안(protection) 측면에서 세그먼테이션**

  - 의미 단위로 나눠져 있어서 페이징 기법보다 훨씬 효과적이다. -> 5.2 와 연결하기
  - 왜냐하면 크기 단위로 나누다 보면 공유 코드와 사유 데이터 영역이 동일 페이지에 공존하는 경우가 발생할 수 있기 때문이다. 이러면 어떤 권한을 줘야할지 결정하기가 어렵다.

<br>

## 5.4 세그먼트 할당 방식

- **세그먼트를 가용 공간에 할당하는 방식**
  - 세그먼트 크기가 균일하지 않기 때문에, 외부 조각 같은 문제점이 발생한다.
    - 내부 조각은 없다는 장점
    - paging은 fragmentation이 발생하지 않는다.
  - 그래서 동적 메모리 할당 문제가 존재한다.
  - 이 문제에 대해서는 first-fit 방식과 best-fit 방식을 사용한다.

<br>

---

# 6. 페이지드 세그먼테이션

> 의미 단위로 끊은 segmentation을 기반으로, 각 segmentation을 크기가 동일한 page로 구성

<br>

## 6.1 pure segmentaton과의 차이점

> **_segment-table entry_** 가 segment의 **_base address_** 를 가지고 있는 것이 아닌, segment를 구성하는 **_page table_** 의 **_base address_** 를 가지고 있다.

<br>

## 6.2 Paged segmentation의 logical address

> 두 가지 [s: segment-number, d: offset]로 구성

<br>

## 6.3 Paged segmentation의 특징과 장점

- **물리적 메모리에 적재하는 단위: page**

- **address binding을 위해 외부의 segment table과 내부의 page table을 이용한다.**

- **장점: segmentation에서 발생하는 외부조각 문제와 paging 기법의 접근 권한 보호 문제를 해결**

<br>

## 6.4 address binding 과정 설명

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/165794299-d37ff422-11c8-4111-9667-486737368314.PNG"/></p>

- **첫 번째**
  : 논리적 주소의 상위 비트인 segment number(s)를 통해 segment table의 해당 항목에 접근

- **두 번째**
  : 이 segment table entry = segment 길이 + segment의 page table 시작 주소

- **세 번째**
  : 세그먼트 길이를 넘어서는 메모리 접근 시도인지 여부를 체크하기 위해, segment length와 logical address의 하위 비트 offset(d) 값과 비교.
  -> If segment lenth < offset: 유효 X -> trap 발생.  
  -> If segment lenth > offset: offset 값을 다시 상위 하위 비트로 나눔.

      - 나눠진 상위비트(p): 그 segment 내에서 page number를 의미.
      - 나눠진 하위비트(d'): page 내에서의 변위를 의미.

- **네 번째**
  : segment table entry에 있는 segment의 page-table base를 기준으로, p만큼 떨어진 page table entry로부터 물리적 메모리의 page frame 위치(f)를 얻음.

- **다섯 번째**
  : 이 얻어진 위치에서 d'만큼 떨어진 곳 = 물리적 메모리 주소.

- **page table for segment s 의 entry 갯수는 segment table의 segment 길이를 보면 알 수 있다.**

<br>

---

# Reference

- [운영체제와 정보기술의 원리](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=)
- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
