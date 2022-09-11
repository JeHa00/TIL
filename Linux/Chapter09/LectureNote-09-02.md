# 0. Introduction

> 1. [Mount](#1-mount)  
> 2. [Unmount](#2-unmount)  
> 3. [NFS(Network File System)](#3-nfsnetwork-file-system)    
> 4. [자동 Mount](#4-자동-mount)  

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 데이터 저장과 패키지에 대해 학습했다.

- 이번 챕터에서는 HDD와 파티션에 대해 학습한다. 
    - 특히 Mount에 대해 학습한다.

<br>

---
# 1. Mount

## Mount란?

> **_정의: 디스크와 같은 물리적인 장치를 특정 위치, 디렉토리에 연결시키는 작업_**    

- 설정한 파티션들을 데이터들이 저장 가능하게 포맷해야 한다. 마지막에 마운트를 해야 한다. 

- 리눅스 환경에서 마운트 명령어는 논리적인 디스크와 디렉토리를 붙이는 것으로, 리눅스 환경에서 하드 디스크를 필요에 맞게 나눈 공간들을 실질적으로 사용할 수 있도록 리눅스에 임의의 디렉토리를 생성함과 동시에, 하드디스크의 공간과 연결하는 것을 말한다.

- 수동 마운트: mount 명령을 통해서 수동으로 연결  
    - 쉘에서 mount 명령을 직접 입력한다.

- 자동 마운트: 부팅 시 자동으로 마운트
    - `/etc/fstab (File System Table)`
    - 쉘에서 직접 입력하는 것 외에 이 table에 추가하면 자동으로 마운트된다. 

- 언마운트: Unmount 명령을 통해서, 마운트된 File System을 해제

## 하드웨어와 사용자의 관점에서 Mount

![image](https://user-images.githubusercontent.com/78094972/189529264-3e5a22e6-6dbf-4785-845c-5de5e763030c.png)

> **_하드웨어를 User가 사용하기 위해 device driver를 os가 인식하도록 하는 작업이 바로 Mount다. 그 반대는 Unmount다._**

- CPU, 그래픽 카드는 맨 하단의 Device에 해당한다. 

- OS가 하드웨어를 인식할 수 있도록 돕는 것이 Device Driver인데, 모든 하드웨어 장치들은 다 Device driver가 필요하다.  
    - 예 1) 들어서 인터넷을 사용하기 위해서 LAN card를 사용하고, OS가 이 랜카드를 인식하기 위해 이 랜카드에 해당하는 드라이버가 필요하다. 
    - 예 2) 화면을 정상적으로 출력하기 위해서 그래픽 카드를 장착하는데, 이 그래픽 카드를 OS에서 인식할 수 있도록 해당하는 드라이버가 있어야 한다. 

<br>


## Mount 명령어

> **_정의: 디스크와 같은 물리적인 장치를 특정 위치, 디렉토리에 연결시키는 작업_**    
> mount [Option] [Device:Partition] [Directory:Mount Point]  

- Partition: HW 부분
- Mount point: SW 부분  

### Mount Point

> **_마운트 작업으로 물리적인 장치에 연결된 디렉토리를 mount point라 한다._**

- Mount point 주의사항
    - 첫 번째: 마운트 포인트는 사용자와 관련된 디렉토리를 사용하면 안된다. 

    - 두 번째: ❗️ **_마운트 포인트는 사용되지 않는 임의의 디렉토리를 사용_**  

    - 세 번쨰: 한 개의 파티션은 반드시 한 개의 마운트 포인트만 사용해야 한다.

    - 네 번째: 마운드 포인트는 Primary Partition, Logical Partition만 가능  

    - 예시: `mount /dev/sda1 /d_drive`

<br>

### Option

| 옵션 | 내용 |
| ----|----|
|-a|/etc/fstab에 명시된 파일 시스템을 마운트|
|-f|실제로 마운트하지 않고, 파운트 할 수 있는지 확인|
|-t|파일 시스템 타입을 선택|
|-r|읽기만 가능하게 마운트|
|-w|읽기/쓰기 모드로 마운트|

- 사전 설정 세팅과 파일의 종류에 따라 Option과 Device는 생략될 수 있다. 
    - `-t ex4`의 경우, 아래 파티션 설정으로 Format type을 ext4로 정했기 때문에 생략가능하다. 
    - `ro`의 경우, 파일 자체가 read only이기 때문에, `-o ro`는 생략가능하다.

```yml
# 생략 전
mount -t ex4 /dev/sda1 /d_drive

# 생략 후
mount /dev/sda1 /d_drive
(파티션 설정: mkfs -t ext4 /dev/sda1)

mount -t nfs fedora.net:/home/nfs /fedora-nfs

# ro: read only
mount -o ro /dev/cdrom /media/cdrom
```

<br>

### Mount 확인

> **_마운트 된 파티션 정보 확인_**     
> df -h  

- `df -h` 반드시 외우기

<br>

---
# 2. Unmount

> **_정의: 마운트 되어 있는 File System, 즉 파티션을 해제_**
> **_umount [Option] [Device:Partition] or [Directory:Mount Point]_**

- 마운트와 달리 partition과 mount point 중 하나만 입력한다. 

- ❗️ **_마운트 포인트가 아닌 이외의 곳_** ,다른 위치에서만 가능하다. 

| 옵션 | 내용 |
| ---- | ---- |
|-a|/etc/fstab에 기록되어 있는 파일 시스템을 모두 언마운트|
|-f|강제로 연결 해제할 때 사용|

```yml
unmount /dev/sda1

unmount /d_drive
```


<br>

---
# 3. NFS(Network File System)

> **_네트워크 파일 시스템(NFS)으로 공유된 영역을 마운트_**  
> mount **-t nfs** [IP:/EXPORT DIR] [LOCAL DIR]

- 로컬 컴퓨터 외 나머지로, 다른 곳으로 설정된 파티션을 로컬 컴퓨터에 마운트하는 걸 말한다. 

- Local 파일 시스템에 연결

- 실제 실무에서 많이 사용하는 부분  

```yml
# Ex 
mount -t nfs fedora.net:/home/nfs /fedora-nfs
```

<br>

---

# 4. 자동 Mount

> **_정의: /etc/fstab 파일에 설정하고, 부팅 시 설정한대로 자동으로 마운트_**    
> /etc/fstab (File System Table)

- File System Table은 다음과 같이 구성되어 있다. 

| Field | meaning|
| ---- |---- |
|**fs_spec**|디스크 이름 or 디스크 UUID _(H/W)_|
|**fs_file**|Mount point _(S/W)_|
|fs_vfstype|File system (ext4, nfs)|
|fs_mntops|Mount Option(defaults, usrquota, grpquota, acl 등)|
|fs_freq|덤프(= 백업) 유무 (0, 1, 2)|
|fs_passno|fsck 검사 순서 (1,2)|

- fs_freq: 2일마다 수행, 매일 수행 등등 의미 
    - 불특정 다수가 사용하여 사용 후, 초기화가 필요할 때 사용 ex) 컴퓨터 과목 시간의 컴퓨터 초기화 설정 
    - 0은 백업 x 
    - File system이 swap면 fs_freq는 0에 해당된다. 
    - swap 영역 빼고는 거의 다 1에 해당한다. 

- fs_passno
    - 0으로 하면 점검을 하지 않는다. 
    - root는 1로, 나머지는 2로 설정하여 root를 먼저 점검한다. 


- 예시

```yml
mount -t ext4 /dev/sdb1 /d_drive
=>
/dev/sdb1   /d_drive    ext4    defaults    1   2
```

|디바이스 명| 마운트 포인트|파일 시스템 종류 |마운트 옵션 | dump 설정| 파일 시스템 점검 옵션 |
| ----|----|----|----|----|----|
|/dev/sdb1|/d_drive|ext4|defaults|1|2|


<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)