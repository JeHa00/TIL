# 0. Introduction

> 1. [File System](#1-file-system)  
> 2. [Partition](#2-partition)  
> 3. [fdisk](#3-fdisk)  
> 4. [Partition 구성](#4-partition-구성)  
> [practice](#practice)  

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 데이터 저장과 패키지에 대해 학습했다.

- 이번 챕터에서는 HDD와 파티션에 대해 학습한다. 
  
    
<br>

---
# 1. File System

> **_정의: 파일, 디렉토리를 효율적 / 구조적으로 관리하기 위한 트리 구조 시스템_**    
> /lib/modules/[kernel_version]/kernel/fs

- 하드 디스크나 CD-ROM과 같은 물리적 저장 공간을 저장장치로 활용
- 네트워크를 이용해서 여러 서버 간의 마운트를 통해 마치 하나의 파일 스토리지처럼 사용 (NFS)

- 리눅스가 지원하는 File System은 Window보다 다양하다.

- 리눅스의 File system은 Kernel에 포함되어 있고, 대부분의 기본 구성에 포함. 

- shell을 통해서 Kernel에 전달한다. 



<br>

---
# 2. Partition

> **_정의: 하나의 물리적 디스크를 여러 개의 연속되는 논리적인 디스크로 분할_**

- 비유하자면 건물의 1층 2층 그리고, 각 층의 호수들

- 물리적인 저장장치에 남아있는 연속된 공간이 필요

- 서로 다른 물리적인 저장 장치를 하나의 파티션으로 구성할 수 없다.  

- '단일 파티션' 과 '다중 파티션' 의 논리적인 차이는 `MBR(#0)` 의 유무
    - 논리적인 차이: 각 파티션을 관리하는 테이블이 존재 
    - 물리적인 위치: disk의 가장 첫 번째 섹터  

## Partition의 종류

> **_DOS Partition Table, BSD Disk Label, Apple Partition Map, Solaris Disk Label_**  

- DOS Partition Table
    - 현재 가장 많은 OS가 채택하여 사용
     

- BSD Disk Label
    - 하나의 섹터 안에서 파티션 정보를 포함한 디스크 정보를 저장

- Apple Parition Map
    - BSD 계열 기록 방식을 채택하여 사용

- Solaris Disk Label
    - Sun Sparc에서는 0번 섹터에 Disk Label 위치 



<br>

---
# 3. fdisk

> **_기본 기능: File System(디스크 파티션)을 생성_**  
> fdisk [옵션] [File System]

- sdb2는 하드 디스크에 해당된다.  
    - 숫자가 붙은 것은 파티션을 의미한다.  

- `mkfs`: make file system의 약어로, 포맷을 한다는 의미

```yml
mke2fs -j/dev/sdb2
mkfs -t ext4 /dev/sdb2
mkfs .ext4 /dev/sdb2
```

<br>

---
# 4. Partition 구성

![image](https://t1.daumcdn.net/cfile/tistory/12397B1F4B6BA47784)

- Extended partition이 있어야 logical partition을 그 안에 담을 수 있다. 

- 하드디스크를 가져와 리눅스에서 사용한다면 먼저 포맷을 하기 위해서 파티션을 설정한다.

- 참고 사이트: [파티션(Partition)의 개념](https://dakuo.tistory.com/60)

<br>

---
# practice

## 사전 준비

- aws console에 로그인하기  -> 인스턴스(실행 중) 클릭 -> `Elastic Block Store`에서 볼륨 클릭

- 하드디스크 생성을 위해 오른쪽 위에 `볼륨 생성` 클릭 -> GiB를 1로 수정 -> 가용 영역 선택: 생성된 인스턴스의 가용영역 참고하여 선택 -> 볼륨 생성 클릭  

- 그러면 화면에 즉, `Elastic Block Store`의 볼륨 탭에 크기가 1 GiB인 볼륨을 확인할 수 있는데, 이를 마우스 우클릭 또는 오른쪽 위의 작업 탭을 클릭하여 `볼륨 연결`을 클릭한다.  

- 만약 `볼륨 연결`이 뜨지 않으면 새로 고침하여 다시 확인한다. 볼륨 연결이 뜨면, 인스턴스를 현재 작동 중인 것을 선택하고, '디바이스 이름'을 확인한 후, 볼륨 연결을 한다.

<br>

## 추가한 볼륨 확인하기


- `fdisk -l /dev/sd` 를 확인하면 현재 파티션 목록을 확인할 수 있다. 

- 맨 마지막을 보면 추가한 볼륨을 확인할 수 있다. 

```yml
# /dev/sd는 디바이스 이름에 적힌 경로다.
[root@ip-172-31-8-107 ~]# fdisk -l /dev/sd*
Disk /dev/sda: 8 GiB, 8589934592 bytes, 16777216 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: gpt
Disk identifier: 1300FF3B-F4C7-4EB0-A787-3225EBC97D6B

Device      Start      End  Sectors Size Type
/dev/sda1    4096 16777182 16773087   8G Linux filesystem
/dev/sda128  2048     4095     2048   1M BIOS boot

Partition table entries are not in disk order.


Disk /dev/sda1: 8 GiB, 8587820544 bytes, 16773087 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes


Disk /dev/sdf: 1 GiB, 1073741824 bytes, 2097152 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
```

<br>

## 디스크의 파티션 만들기

- `fdisk /dev/sdf`

```yml
# command 란에 m을 입력한다.
[root@ip-172-31-8-107 ~]# fdisk /dev/sdf
Welcome to fdisk (util-linux 2.30.2).
Changes will remain in memory only, until you decide to write them.
Be careful before using the write command.

Device does not contain a recognized partition table.
Created a new DOS disklabel with disk identifier 0x7c6de18b.

Command (m for help): m

# 그러면 아래 메뉴들을 확인할 수 있다.  
Help:

  DOS (MBR)
   a   toggle a bootable flag
   b   edit nested BSD disklabel
   c   toggle the dos compatibility flag

  Generic
   d   delete a partition
   F   list free unpartitioned space
   l   list known partition types
   n   add a new partition
   p   print the partition table
   t   change a partition type
   v   verify the partition table
   i   print information about a partition

  Misc
   m   print this menu
   u   change display/entry units
   x   extra functionality (experts only)

  Script
   I   load disk layout from sfdisk script file
   O   dump disk layout to sfdisk script file

  Save & Exit
  # 완료
   w   write table to disk and exit

   # 처음부터 하고 싶을 때 이 명령어를 사용한다
   q   quit without saving changes

  Create a new label
   g   create a new empty GPT partition table
   G   create a new empty SGI (IRIX) partition table
   o   create a new empty DOS partition table
   s   create a new empty Sun partition table


Command (m for help): p
Disk /dev/sdf: 1 GiB, 1073741824 bytes, 2097152 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x7c6de18b

# 새로운 파티션을 추가하자 
Command (m for help): n
Partition type
   p   primary (0 primary, 0 extended, 4 free)
   e   extended (container for logical partitions)
Select (default p): 

Using default response p.
Partition number (1-4, default 1): 1
First sector (2048-2097151, default 2048): 
Last sector, +sectors or +size{K,M,G,T,P} (2048-2097151, default 2097151): +700M

Created a new partition 1 of type 'Linux' and of size 700 MiB.

Command (m for help): p
Disk /dev/sdf: 1 GiB, 1073741824 bytes, 2097152 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x7c6de18b

Device     Boot Start     End Sectors  Size Id Type
/dev/sdf1        2048 1435647 1433600  700M 83 Linux

Command (m for help): w
The partition table has been altered.
Calling ioctl() to re-read partition table.
Syncing disks.
```

<br>

## 생성된 파티션 확인하기

- `fdisk -l /dev/sdf`

```yml
[root@ip-172-31-8-107 ~]# fdisk -l /dev/sdf
Disk /dev/sdf: 1 GiB, 1073741824 bytes, 2097152 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x7c6de18b

Device     Boot Start     End Sectors  Size Id Type
/dev/sdf1        2048 1435647 1433600  700M 83 Linux
```

<br>

## Extended partition 추가하기

```yml
[root@ip-172-31-8-107 ~]# fdisk /dev/sdf
Welcome to fdisk (util-linux 2.30.2).
Changes will remain in memory only, until you decide to write them.
Be careful before using the write command.


Command (m for help): p
Disk /dev/sdf: 1 GiB, 1073741824 bytes, 2097152 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x7c6de18b

Device     Boot Start     End Sectors  Size Id Type
/dev/sdf1        2048 1435647 1433600  700M 83 Linux

Command (m for help): n
Partition type
   p   primary (1 primary, 0 extended, 3 free)
   e   extended (container for logical partitions)
Select (default p): e
Partition number (2-4, default 2): 
Last sector, +sectors or +size{K,M,G,T,P} (1435648-2097151, default 2097151): +200M

Created a new partition 2 of type 'Extended' and of size 200 MiB.

Command (m for help): p
Disk /dev/sdf: 1 GiB, 1073741824 bytes, 2097152 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x7c6de18b

Device     Boot   Start     End Sectors  Size Id Type
/dev/sdf1          2048 1435647 1433600  700M 83 Linux
/dev/sdf2       1435648 1845247  409600  200M  5 Extended
```

<br>

## Logical partition 추가하기

- extended partition을 추가한 후, 확인해보면 목록에 없다는 걸 알 수 있다.

- ❗️ 그 이유로는 하드 디스크에는 단 하나의 extended partition이 존재하기 때문이다.

```yml
Command (m for help): n
Partition type
   p   primary (1 primary, 1 extended, 2 free)
   l   logical (numbered from 5)
Select (default p):  l

Adding logical partition 5
First sector (1437696-1845247, default 1437696): 
Last sector, +sectors or +size{K,M,G,T,P} (1437696-1845247, default 1845247): 

Created a new partition 5 of type 'Linux' and of size 199 MiB.

Command (m for help): p

Disk /dev/sdf: 1 GiB, 1073741824 bytes, 2097152 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x7c6de18b

# 199M의 용량을 가진 logical partition을 확인할 수 있다. 
Device     Boot   Start     End Sectors  Size Id Type
/dev/sdf1          2048 1435647 1433600  700M 83 Linux
/dev/sdf2       1435648 1845247  409600  200M  5 Extended
/dev/sdf5       1437696 1845247  407552  199M 83 Linux

Command (m for help): w
```


- /dev/sdf5 가 logical partition이다.  

<br>

## primary partition 추가하기

- 추가한 결과만을 정리한다.

- /dev/sdf3 이 추가된 걸 확인할 수 있다.  

```yml
[root@ip-172-31-8-107 ~]# fdisk -l /dev/sdf
Disk /dev/sdf: 1 GiB, 1073741824 bytes, 2097152 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x7c6de18b

Device     Boot   Start     End Sectors  Size Id Type
/dev/sdf1          2048 1435647 1433600  700M 83 Linux
/dev/sdf2       1435648 1845247  409600  200M  5 Extended
/dev/sdf3       1845248 2097151  251904  123M 83 Linux
/dev/sdf5       1437696 1845247  407552  199M 83 Linux

Partition table entries are not in disk order.
```

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)
- [파티션(Partition)의 개념](https://dakuo.tistory.com/60)