# 0. Introduction

> 1. [수동 마운트 실습](#1-수동-마운트-실습)  
> 2. [자동 마운트 실습](#2-자동-마운트-실습)  

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 데이터 저장과 패키지에 대해 학습했다.

- 이번 챕터에서는 HDD와 파티션에 대해 학습한다. 

<br>

---

# 1. 수동 마운트 실습

## 1.1 파티션 확인하기


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

- 🔆 위에 생성된 파티션들은 모두 포맷을 해야 사용가능한 상태가 되므로 포맷 명령어를 실행해보자.

<br>

---

## 1.2 /dev/sdf 포맷하기

### /dev/sdf1 포맷하기

- 포맷 명령어: `mkfs -t ext4 /dev/sdf1`

- /dev/sdf1: primary partition

```yml
[root@ip-172-31-8-107 ~]# mkfs -t ext4 /dev/sdf1
mke2fs 1.42.9 (28-Dec-2013)
Filesystem label=
OS type: Linux
Block size=4096 (log=2)
Fragment size=4096 (log=2)
Stride=0 blocks, Stripe width=0 blocks
44832 inodes, 179200 blocks
8960 blocks (5.00%) reserved for the super user
First data block=0
Maximum filesystem blocks=184549376
6 block groups
32768 blocks per group, 32768 fragments per group
7472 inodes per group
Superblock backups stored on blocks:
	32768, 98304, 163840

# 이 부분을 보면 done으로서, 포맷된 것을 알 수 있다.
Allocating group tables: done
Writing inode tables: done
Creating journal (4096 blocks): done
Writing superblocks and filesystem accounting information: done
```

<br>

### /dev/sdf2 와 /dev/sdf5 포맷하기

- 아래처럼 오류가 뜬 이유는 `/dev/sdf2`는 Extended Partition으로 단지 상자역할이기 때문에 다음과 같이 오류가 난다.

- 그래서 실질적인 내용인 `/dev/sdf5` 인 `logicial partition`을 초기화해야 한다.    

- `/dev/sdf2` Error

```yml
[root@ip-172-31-8-107 ~]# mkfs -t ext4 /dev/sdf2
mke2fs 1.42.9 (28-Dec-2013)
mkfs.ext4: inode_size (128) * inodes_count (0) too big for a
	filesystem with 0 blocks, specify higher inode_ratio (-i)
	or lower inode count (-N).
```

- `/dev/sdf5` 포맷하기

```yml
[root@ip-172-31-8-107 ~]# mkfs -t ext4 /dev/sdf5
mke2fs 1.42.9 (28-Dec-2013)
Filesystem label=
OS type: Linux
Block size=1024 (log=0)
Fragment size=1024 (log=0)
Stride=0 blocks, Stripe width=0 blocks
51000 inodes, 203776 blocks
10188 blocks (5.00%) reserved for the super user
First data block=1
Maximum filesystem blocks=33816576
25 block groups
8192 blocks per group, 8192 fragments per group
2040 inodes per group
Superblock backups stored on blocks:
	8193, 24577, 40961, 57345, 73729

# /dev/sdf1 처럼 done을 확인할 수 있다. 
Allocating group tables: done
Writing inode tables: done
Creating journal (4096 blocks): done
Writing superblocks and filesystem accounting information: done
```

<br>

### /dev/sdf3 포맷하기

- 이번에는 다른 방식으로 포맷을 해보자
    - `mkfs.ext4 /dev/sdf3`
    - 아래와 같이 또 다른 Primary partition을 포맷한다. 

```yml
[root@ip-172-31-8-107 ~]# mkfs.ext4 /dev/sdf3
mke2fs 1.42.9 (28-Dec-2013)
Filesystem label=
OS type: Linux
Block size=1024 (log=0)
Fragment size=1024 (log=0)
Stride=0 blocks, Stripe width=0 blocks
31488 inodes, 125952 blocks
6297 blocks (5.00%) reserved for the super user
First data block=1
Maximum filesystem blocks=33685504
16 block groups
8192 blocks per group, 8192 fragments per group
1968 inodes per group
Superblock backups stored on blocks:
	8193, 24577, 40961, 57345, 73729

Allocating group tables: done
Writing inode tables: done
Creating journal (4096 blocks): done
Writing superblocks and filesystem accounting information: done
```

<br>

---

## 1.3 Mount 진행하기

### 마운트된 partition 정보 확인하기

> **_df -h_**

```yml
[root@ip-172-31-8-107 ~]# df -h
Filesystem      Size  Used Avail Use% Mounted on
devtmpfs        474M     0  474M   0% /dev
tmpfs           483M     0  483M   0% /dev/shm
tmpfs           483M  468K  483M   1% /run
tmpfs           483M     0  483M   0% /sys/fs/cgroup
/dev/xvda1      8.0G  2.3G  5.8G  29% /
tmpfs            97M     0   97M   0% /run/user/0
```

<br>

### 마운트 진행을 위해 directory 만들기

- 정보를 찾을 수 없기 때문에, 마운트를 진행해야 한다. 

- ❗️**_마운트를 하기 위해서 mount point directory는 임의의 directory여야 하므로_** 디렉토리를 만든다. 

```yml
# 임의의 directory 생성
[root@ip-172-31-8-107 ~]# mkdir /mntdrive
[root@ip-172-31-8-107 ~]# mkdir /mnttest
[root@ip-172-31-8-107 ~]# ls -l
total 0
[root@ip-172-31-8-107 ~]# cd /
[root@ip-172-31-8-107 /]# ls -l
total 64
lrwxrwxrwx   1 root root     7 Aug 16 05:20 bin -> usr/bin
dr-xr-xr-x   4 root root  4096 Sep  6 16:41 boot
dr-xr-xr-x   2 root root 16384 Sep  9 23:57 dalkom
drwxr-xr-x  15 root root  3100 Sep 11 21:43 dev
drwxr-xr-x  92 root root  8192 Sep 11 16:58 etc
drwxr-xr-x   3 root root    18 Sep  7 17:36 export
drwxr-xr-x   5 root root    56 Sep  6 12:53 home
lrwxrwxrwx   1 root root     7 Aug 16 05:20 lib -> usr/lib
lrwxrwxrwx   1 root root     9 Aug 16 05:20 lib64 -> usr/lib64
drwxr-xr-x   2 root root     6 Aug 16 05:20 local
drwxr-xr-x   2 root root     6 Apr 10  2019 media
drwxr-xr-x   2 root root     6 Apr 10  2019 mnt
drwxr-xr-x   2 root root     6 Sep 11 23:59 mntdrive
drwxr-xr-x   2 root root     6 Sep 11 23:59 mnttest
drwxr-xr-x   4 root root    27 Aug 16 05:22 opt
dr-xr-xr-x 166 root root     0 Aug 30 13:09 proc
dr-xr-x---   3 root root   140 Sep 11 17:11 root
drwxr-xr-x  32 root root  1060 Sep 11 16:58 run
lrwxrwxrwx   1 root root     8 Aug 16 05:20 sbin -> usr/sbin
drwxr-xr-x   2 root root     6 Apr 10  2019 srv
dr-xr-xr-x  13 root root     0 Aug 30 13:09 sys
drwxr-xr-x   2 root root    54 Sep 11 13:28 temp
-rw-r--r--   1 root root  1312 Sep  2 17:51 testfile
drwxr-xr-x   2 root root     6 Sep  4 02:47 testhome
drwxrwxrwt  10 root root  8192 Sep 11 16:58 tmp
drwxr-xr-x   2 root root    76 Sep  4 00:48 usertest
drwxr-xr-x  13 root root   155 Aug 16 05:20 usr
drwxr-xr-x  20 root root   280 Sep  6 17:49 var

# testfile 생성
[root@ip-172-31-8-107 /]# touch /mntdrive/testfile1
[root@ip-172-31-8-107 /]# touch /mnttest/testfile2
```

<br>


### mount 실행

- mount 전 `ls -l`

```yml
[root@ip-172-31-8-107 /]# ls -l /mntdrive/ /mnttest/
/mntdrive/:
total 0
-rw-r--r-- 1 root root 0 Sep 12 00:23 testfile1

/mnttest/:
total 0
-rw-r--r-- 1 root root 0 Sep 12 00:24 testfile2
```

- mount 하기

```yml
[root@ip-172-31-8-107 /]# mount /dev/sdf1 /mntdrive/
```

- mount 유무 확인하기

```yml
# 맨 마지막 줄을 보면 /mntdrive로 올라간 걸 알 수 있다.
[root@ip-172-31-8-107 ~]# df -h
Filesystem      Size  Used Avail Use% Mounted on
devtmpfs        474M     0  474M   0% /dev
tmpfs           483M     0  483M   0% /dev/shm
tmpfs           483M  468K  483M   1% /run
tmpfs           483M     0  483M   0% /sys/fs/cgroup
/dev/xvda1      8.0G  2.3G  5.8G  29% /
tmpfs            97M     0   97M   0% /run/user/0
/dev/xvdf1      672M   24K  623M   1% /mntdrive
```

- mount 후 `ls -l`
    - mount 하고 나서는 mount point 안의 file이 확인되지 않는다.
    - 여기서는 testfile1이 확인되지 않는다.

```yml
[root@ip-172-31-8-107 /]# ls -l /mntdrive/
total 16
drwx------ 2 root root 16384 Sep 11 23:49 lost+found

[root@ip-172-31-8-107 /]# touch testfile3
[root@ip-172-31-8-107 /]# ls -l
total 24
drwx------  2 root root 16384 Sep 11 23:49 lost+found
-rw-r--r--  1 root root     0 Sep 12 00:37 testfile3
```


<br>

---

## 1.4 Unmount 진행하기

- `umount` 명령어 사용 시, partition 또는 mount point를 사용하는데, 번호보다 mount point가 기억하기 더 좋다.

### Unmount 시, error

```yml
# 아래 명령어 2개의 결과는 동일하다. 
[root@ip-172-31-8-107 /]# umount /dev/sdf1 
[root@ip-172-31-8-107 /]# umount /mntdrive/
[root@ip-172-31-8-107 mntdrive]# umount /mntdrive/
umount: /mntdrive/: target is busy.
```

### mount point 외 경로에서 unmount 시도하기

- ❗️ **_umount 시에는 mount point 외의 부분에서 해야 한다._**


```yml
[root@ip-172-31-8-107 mntdrive]# cd ..
[root@ip-172-31-8-107 /]# umount /mntdrive/
```

- 목록에서 확인해보자. 

```yml
[root@ip-172-31-8-107 /]# df -h
Filesystem      Size  Used Avail Use% Mounted on
devtmpfs        474M     0  474M   0% /dev
tmpfs           483M     0  483M   0% /dev/shm
tmpfs           483M  468K  483M   1% /run
tmpfs           483M     0  483M   0% /sys/fs/cgroup
/dev/xvda1      8.0G  2.3G  5.8G  29% /
tmpfs            97M     0   97M   0% /run/user/0

[root@ip-172-31-8-107 /]# ls -l /mntdrive/
total 0
-rw-r--r-- 1 root root 0 Sep 12 00:23 testfile1
```

- 빌려서 잠깐 사용한 것일뿐 언마운트로 반납하면 원래대로 사용가능하다.  

- 그래서 기존에 사용하던 directory에 마운트를 하면 사용할 수 없기 때문에, 임의의 directory에 해야한다.


<br>

---

# 2. 자동 마운트 실습

### /etc/fstab 수정하기

- 자동 마운트를 하기 위해서는 `/etc/fstab`에 내용을 입력해야 하기 때문에, vim 에 들어간다. 

```yml
[root@ip-172-31-8-107 /]# vi /etc/fstab
```

- yy 와 p를 눌러서 바로 아랫 줄에 붙인 후, 아래처럼 내용을 수정한다. 

```yml
#
UUID=44322bfa-376f-498c-9617-cd51e46a67d5     /           xfs    defaults,noatime  1   1
/dev/sdf1     /mnttest           ext4    defaults     1   1
~
```

<br>

### Instance 재부팅하기 in AWS console

- 그 후, AWS console log에 들어와서 사용하고 있는 인스턴스를 재부팅한 후, 다시 로그인한다. 

```yml
ssh -i ~/.ssh/dalkom.pem root@43.200.244.241
[root@ip-172-31-8-107 ~]# df -h
Filesystem      Size  Used Avail Use% Mounted on
devtmpfs        474M     0  474M   0% /dev
tmpfs           483M     0  483M   0% /dev/shm
tmpfs           483M  412K  483M   1% /run
tmpfs           483M     0  483M   0% /sys/fs/cgroup
/dev/xvda1      8.0G  2.3G  5.8G  29% /
/dev/xvdf1      672M   24K  623M   1% /mnttest
tmpfs            97M     0   97M   0% /run/user/0
```

<br>

### 자동 mount 확인

- 자동 마운트된 걸 확인할 수 있다. 

- 수동과의 차이점은 기존에 있던 testfile3가 lost+found로 변하지 않았다는 것이다. 

```yml
[root@ip-172-31-8-107 ~]# ls -l /mnttest
total 16
drwx------ 2 root root 16384 Sep 11 23:49 lost+found
-rw-r--r-- 1 root root     0 Sep 12 00:37 testfile3
```

🔆 요즘 linux에서는 mount point directory에 잘못 입력하여 없는 directory여도 이를 생성하여 연결시킨다.

- **자동 마운트에서 중요한 건 정확한 paritition 명과 mount point 명을 입력해야 한다.**


### umount 하기

- 그러면 이번에는 partition으로 unmount 해보자.

```yml
[root@ip-172-31-8-107 ~]# umount /dev/sdf1
[root@ip-172-31-8-107 ~]# ls -l /mnttest
total 0
-rw-r--r-- 1 root root 0 Sep 12 00:24 testfile2
```

<br>

---

## 자동 mount가 필요한 곳


- 위와 같은 자동 mount가 필요한 곳이 NFS다.

- NFS의 특징은 다른 위치에 있는 사용자에게 필요한 파티션을 마운트하여 사용할 수 있다.

- 🔆 시스템은 계속 킬 수 없기 때문에, 문제가 생겼을 때 컸다가 켜야 한다. 이 때 다른 서버를 마운트하여 사용할 수가 있다. 



<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)