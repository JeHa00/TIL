# 0. Introduction

> 1. [File and File system](#1-file-and-file-system)
> 2. [open() 연산](#2-open-연산)
> 3. [File Protection](#3-file-protection)
> 4. [File system의 Mounting](#4-file-system의-mounting)
> 5. [Access Methods](#5-access-methods)

<br>

- 해당 내용은 [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 와 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)를 보고 정리한 내용이다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

---

# 1. File and File System

- **File**

  > **_A named collection of related information_**

  - 저장 장소:
    - 일반적으로 비휘발성의 보조기억장치(disk)에 저장
  - 메모리는 주소를 통해서 접근하는 장치지만, disk에 일반적으로 저장되는 file은 이름을 통해서 접근한다.
  - 운영체제는 다양한 저장 장치를 file이라는 동일한 논리적 단위로 볼 수 있고 관리한다.
    - 운영체제의 관리 방법으로 사용되는 file을 'device special file' 이라 한다.
  - file 관련 Operation(연산):
    - create, delete,read, write, reposition(파일 내부의 위치 저장), open, close 등

- **File attribute(or metadata of file)**

  > **_파일 자체의 내용이 아니라 파일을 관리하기 위한 각종 정보들_**

  - 파일 이름, 유형, 저장된 위치, 파일 사이즈
  - 접근 권한(읽기 / 쓰기 / 실행), 시간(생성 / 변경 / 사용), 소유자 등

- **File system**

  > **_운영체제에서 파일을 관리하는 체계_**

  - 파일 및 파일의 메타 데이터, 디렉토리 정보 등을 관리
  - 파일의 저장 방법 결정
  - 파일 보호 등

- **Directory**

  - 파일의 metadata 중 일부를 보관하고 있는 일종의 특별한 파일
  - 그 디렉토리에 속한 파일 이름 및 파일 attribute들을 가지고 있다.
  - directory로 정의되는 연산
    - file 찾기, 만들기, 지우기
    - list a directory, rename a file, traverse(탐색) the file system

- **Partition (= Logical Disk)**

  - 하나의 물리적 디스크 안에 여러 파티션을 두는 게 일반적이다.
    - 쪼개진 파티션을 C drive, D drive라 불린다.
  - 반대로 여러 개의 물리적인 디스크를 하나의 파티션으로 구성하기도 한다.
  - (물리적) 디스크를 파티션으로 구성한 뒤 각각의 파티션에 **file system**을 설치하거나, 가상 메모리의 **swapping** 등 다른 용도로 크게 2가지로 나눠 사용할 수 있다.

<br>

---

# 2. open() 연산

> **_system call을 통해서 OS가 물리적 디스크에 있는 file의 metadata를 memory로 올리는 연산으로, read와 write 같은 연산들 모두 system call을 통해서 수행한다._**

![image](https://user-images.githubusercontent.com/78094972/170816109-5e0503c4-ec5e-4b72-b225-b61c86b5c595.PNG)

<br>

## 2.1 open() 연산의 구체적인 과정

![image](https://user-images.githubusercontent.com/78094972/170819466-1ea2991e-c881-43f2-b3bd-4f827c49c0f9.PNG)

- **왼쪽: memory, 오른쪽: disk**
- **open('/a/b/')**: Process A가 '/a/b/' 경로에 있는 file을 open하겠다.

  - system call 이므로, CPU 제어권이 OS에게 넘어간다.
  - disk로부터 파일 c의 metadata를 메모리로 가져오기 위해, directroy path를 search.

    - open 작업: 그 안에 있는 metadata를 memory에 올리는 것
    - read 작업: open 후, memory에 올린 metadata 를 열어서 disk 상의 실제 위치를 얻는 것
    - root directory '/'의 metadata는 미리 알고 있다.

    - root directory '/'를 open하고, read하여 그 안(= disk 상의 실제 위치 안)에서 'a'의 metadata를 획득.
    - 파일 'a'의 metadata를 open한 후, read하여 그 안에서 파일 'b'의 metadata를 획득.
    - 파일 'b'의 metadata를 open한 후, read하여 그 안에서 원하는 data를 가져온다.

  - 이런 tree 구조의 directory는 search에 너무 많은 시간을 소요한다.

    - 한 번 open한 파일은 kernel의 메모리 영역 일부에 copy하여 두고 나서 동일한 경로의 파일에 관한 read / wrtie 시, kernel의 메모리 영역에 둔 file을 다시 copy해서 사용하기 때문에 directory search를 하지 않아도 된다.
    - kernel memory 일부 영역에 copy하여 별도로 저장된 file을 `buffer cache` 라 한다. 그래서 LRU와 LFU 알고리즘을 자연스럽게 사용할 수 있다.

<br>

## 2.2 file system 연산의 두 종류 table

- File descriptor table(fd) (file handle, file control block)

  - 프로세스마다 존재하여 프로세스 별 open file table에 대한 위치 정보를 나타낸다.
    - 위 이미지에서는 b file의 index 정보를 가지고 있다.

- Open file table

  - 현재 open된 file들의 metadata 보관소 (in memory)
  - 디스크의 metadata보다 몇 가지 정보가 추가된다.

    - Open한 process의 수
    - File offset: 파일 어느 위치 접근 중인지 표시 (별도 테이블 필요)

<br>

---

# 3. File Protection

- 각 파일에 대해 누구에게 어떤 유형의 접근(read / wrtie / execution)을 허락할 것인가??
- Access Control 방법: 3가지
  - Access control Matrix, Grouping, Password

![image](https://user-images.githubusercontent.com/78094972/170819465-e01ecc99-605a-4c18-84db-39259e57762e.PNG)

- Access control Matrix로 하는 건 overhead가 커서 일반적인 OS에서는 Grouping 방법을 사용한다.
- Grouping 방식은 단 9 비트만 있으면 된다.

<br>

---

# 4. File System의 Mounting

> **_Mounting 연산이란 A partition의 root file system에 있는 특정 directory 이름에다가 또 다른 파티션 B의 file system을 mounting 하여, A parition이지만 B partition과 연결하는 연산_**

![image](https://user-images.githubusercontent.com/78094972/170826254-b1eb4558-1031-45e3-b90d-65ffc5967266.PNG)

![image](https://user-images.githubusercontent.com/78094972/170826480-293a2f09-85c1-4048-93a6-13306600a154.PNG)

<br>

---

# 5. Access Methods

- **시스템이 제공하는 파일 정보의 접근 방식**

  - 순차 접근 (sequential access)

    - 카세트 테이프를 사용하는 방식처럼 접근
    - 읽거나 쓰면 offset은 자동적으로 증가

  - 직접 접근 (direct access, random access)

    - LP 레코드 판과 같이 접근하도록 한다.
    - 파일을 구성하는 레코드를 임의의 순서로 접근할 수 있다.

<br>

---

# Reference

- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
- [운영체제와 정보기술의 원리 - 반효경 지음 -](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=)
- [metadata](https://www.techtarget.com/whatis/definition/metadata)
