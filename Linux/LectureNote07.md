# 0. Introduction

> 1. [Process 및 Process 종류](#1-process-및-process-종류)
> 2. [Process 명령어](#2-process-명령어)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 특수 권한에 대해 학습했다.

- 이번 챕터에서는 **프로세스 관리** 에 대해 학습해보자.
    - 반드시 기억해야하는 것들은 실습을 했다. 

<br>

---

# 1. Process 및 Process 종류

## 1.1 Process

> **_실행 중인 모든 프로그램, 컴퓨터의 CPU에서 실해오디는 모든 프로그램_**


## 1.2 Multitasking

> **_같은 시간(동시)에 여러 개의 프로그램을 실행_**

## 1.3 Linux의 Process

**기본 규칙**

- 각각의 프로세스마다 고유의 PID를 하나씩 증가시키면서 부여
- 더 이상 할당할 PID가 없으면, 가장 낮은 미사용 숫자로 되돌아가서 PID 할당
- 파일의 소유권과 유사한 방식의 소유권
- 프로세스를 실행하는 사용자의 UID가 실제 사용자 UID로 할당
- SetUID와 같은 특수한 경우, 실행하는 사용자 ID가 아닌 실제 소유자가 실행한 것처럼 실행: 특수 권한의 특징과 동일

## 1.4 Process 종류

### Foreground process: ex) 게임

> **_기본 기능: 화면 실행되는 것이 보이는 프로세스_**

- 화면에 보이는 상태에서 동작하는 프로세스

- 화면에 보이면서 동작하며 그 와중에 다른 작업은 불가한 프로세스 


- ex: `cp -r /usr/sbin /dalkom/`


### Background process: ex) 음악

> **_기본 기능: 사용자에게 직접적으로 보이지 않고, 뒤에서 돌아가고 있는 프로세스_**

- 화면에 보이지 않는 상태에서 동작하는 프로세스

- 다른 작업 가능

- foreground 명령어에 끝에 `&`을 붙이면 Background process로 작동된다. 

### 대화형 프로세스: 대부분의 프로세스

- 터미널과 세션을 통해 사용자와 정보를 주고 받으며 동작하는 프로세스

### 🔆데몬 프로세스: 서비스 관리자

- 특정 서비스(프로그램) 실행을 위해서 백그라운드 상태에서 동작하는 서버 프로세스
- 서버 구축에 반드시 필요한 프로세스
    - 데몬 프로세스가 정상적으로 작동되지 않으면 서비스를 잘 제공할 수 없다.

- foreground process가 일시 중지 -> background process로 전환 -> 터미널 제어는 background process의 기본 process인 shell에게 넘어간다.

### 좀비 프로세스

- 자식 프로세스가 종료되었으나, 그 신호가 부모 프로세스에게 전달되지 않아 마치 살아있는 것처럼 보이는 프로세스
- 정상적인 방법으로 프로세스를 종료하지 않을 경우, 좀비 프로세스가 생길 수 있다.

`프로세스 이름: Int / PID: 1` => `프로세스 이름: A / PID: 10000 / PPID: 1` => `프로세스 이름: B / PID: 10001 / PPID: 10000`

- 정상적으로는 A가 종료되면 B도 종료되야 한다.  

### 배치 Process: 예약 프로세스

- 실무에서 많이 사용한다. 
- 특정한 작업을 특정 시각에 실행해주는 프로세스
- 동작시켜야하는 일련의 작업이 있을 경우, 일괄로 처리하는 프로세스  
- 실제 시스템의 처리량이 낮은 밤이나 새벽에 처리한다. 
    - 동일한 작업을 단순히 반복적으로 처리할 때, 다른 것에 영향을 주지 않는 범위에서 일련의 작업을 묶어서 한 번에 처리한다.  ex) 백신 프로그램


<br>

---
# 2. Process 명령어
## 2.1 ps(Process State)

> **_기본 기능 첫 번째: 현재 실행 중인 프로세스와 상태를 출력. Proc 파일 시스템에 있는 stat의 내용_**    
> ps [옵션]  

| 옵션 | 내용 | 
| ---- | ---- |
|-a|현재 실행 중인 전체 사용자의 모든 프로세스 출력|
|**-e**|커널 프로세스를 제외한 모든 프로세스를 출력|
|**-f**|Full format으로 표기 (UID, PID, PPID 등이 함께 표시)|
|-l|출력을 long forma으로 표기 (PRI, NI 값 등)|
|-u|Process의 소유자(실행한)를 기준으로 출력|
|-x|데몬 프로세스처럼 터미널에 종속되지 않은 프로세스를 출력|
|USER|Process 소유자 이름, UID를 의미|
|PID|Process의 식별 번호|
|PPID|Parent Process의 PID|
|%CPU|최근 1분간 Process가 CPU 사용한 비율의 추정치(백분율)|
|%MEM|최근 1분간 Process가 Memory 사용 비율의 추정치(백분율)|
|TTY|Process와 연결된 터미널|
|STAT|현재 Process의 상태 코드|
|STIME|Process가 시작된 시간 혹은 날짜|

- ef 반드시 기억하기  

> **_기본 기능 두 번째: 시스템 사용량을 실시간으로 확인 (window의 작업관리자와 유사)_**  
> top [옵션]    


### practice

```yml
[root@ip-172-31-3-4 ~]# ps -ef
UID        PID  PPID  C STIME TTY          TIME CMD
root         1     0  0 Sep07 ?        00:00:19 /usr/lib/systemd/s
root         2     0  0 Sep07 ?        00:00:00 [kthreadd]
root         3     2  0 Sep07 ?        00:00:00 [rcu_gp]
root         4     2  0 Sep07 ?        00:00:00 [rcu_par_gp]
...

[root@ip-172-31-3-4 ~]# ps -ef | grep sshd
root      5127     1  0 Sep07 ?        00:00:00 /usr/sbin/sshd -D
root     31261  5127  0 05:09 ?        00:00:00 sshd: root@pts/0
root     32483 31279  0 05:31 pts/0    00:00:00 grep --color=auto s

# 마지막에 출력된 프로세스를 입력해야 한다.
# 아래와 같이 결과가 나오는 건 실행되고 나서 바로 종료되었기 때문이다. 
[root@ip-172-31-3-4 ~]# kill 32483   
-bash: kill: (32483) - No such process

# 아래의 경우가 올바르게 프로세스를 종료시킨 결과
[root@ip-172-31-3-4 ~]# kill 5127
[root@ip-172-31-3-4 ~]# kill 31261Connection to 3.36.89.64 closed by remote host.
Connection to 3.36.89.64 closed.
```

- sshd: SSH Daemon을 의미하는데, SSH 연결을 받아주기 위해(inbound) 대기하는 프로세스

- 그러면 지금 사용 중인 터미널은 그대로 두고, 새로운 터미널을 열어서 사용하자.

```yml
[root@ip-172-31-8-107 ~]# ps -ef | grep sshd
root       814     1  0 Aug30 ?        00:00:03 /usr/sbin/sshd -D
root     16847   814  0 17:14 ?        00:00:00 sshd: root@pts/0
root     16890   814  0 17:17 ?        00:00:00 sshd: root@pts/1
root     16962 16908  0 17:32 pts/1    00:00:00 grep --color=auto sshd

[root@ip-172-31-8-107 ~]# kill 814
[root@ip-172-31-8-107 ~]# exit
logout
Connection to 43.200.244.241 closed.
> ssh root2
ssh: connect to host 3.36.89.64 port 22: Connection refused

# 기존에 연결을 유지하고 있던 터미널에서 아래 명령어를 입력한다.
[root@ip-172-31-8-107 ~]# systemctl restart sshd

# 연결을 끊었던 터미널에 로그인하면 잘 들어가진다.
```

- PID가 바껴진 걸 확인할 수 있다.  
- kill 을 하고 나가서 재로그인을 시도하니, Connection refused가 떴다. 왜냐하면 ssh가 안에서 죽어버렸기 때문이다. 
    - 다른 터미널에서 보면 위 kill한 PID를 확인할 수 없다.

- 이렇게 ssh가 죽어버리면 `/etc/rc.d/init.d/sshd restart`를 하면 되지만, 이 부분은 중요한 부분이기 때문에 AWS에서는 이를 막았다. 

- 그래서 `systemctl restart sshd`를 입력하자. 
    - 아무것도 뜨지 않지만, 원래 리눅스에서는 무언가를 확인할 수 있다. 




### ❗️ 위에 다른 터미널에 유지하는 것 없이 kill했을 경우

> **_root process를 Kill하면 시스템의 가장 최상단 프로세스를 강제로 죽였기 때문에, 시스템을 재시작해줘야한다._**

AWS의 경우를 생각해보자. 

- 1) AWS에 로그인 -> 사용하고 있는 리눅스의 인스턴스를 선택  

- 2) 마우스 우클릭 -> 인스턴스 상태에서 재부팅 클릭  

- 3) 리눅스 시스템을 강제로 재부팅하고 30 ~ 60초 후, 로그인 하시면 정상적으로 접속 가능하다.  



<br>

---

## 2.3 pstree

> **_기본 기능: 현재 실행 중인 프로세스들을 '트리 구조'로 출력_**  
> pstree [옵션]


| 옵션 | 내용 |
| ---- | ---- | 
| -a | Process들이 실행될 때 사용되었던 인자값, 옵션 등이 모두 출력 |
| -h | 현재 Process와 Parent Process를 하이라이트 형태로 출력 |
| -n | Process의 PID를 기준으로 정렬하여 출력 |
| -p | Process명과 PID도 출력 |

- `pstree -np` 를 많이 사용한다.

```yml
[root@ip-172-31-8-107 ~]# pstree
systemd-+-acpid
        |-2*[agetty]
        |-amazon-ssm-agen-+-ssm-agent-worke---7*[{ssm-agent-worke}]
        |                 `-7*[{amazon-ssm-agen}]
        |-atd
        |-auditd---{auditd}
        |-chronyd
        |-crond
        |-dbus-daemon
        |-2*[dhclient]
        |-gssproxy---5*[{gssproxy}]
        |-httpd-+-8*[httpd---5*[{httpd}]]
        |       `-httpd---21*[{httpd}]
        |-lsmd
        |-lvmetad
        |-master-+-pickup
        |        `-qmgr
        |-php-fpm---5*[php-fpm]
        |-rngd
        |-rpcbind
        |-rsyslogd---2*[{rsyslogd}]
        |-sshd---sshd---bash---pstree
        |-systemd-journal
        |-systemd-logind
        `-systemd-udevd
```

<br>


## 2.4 kill

> **_기본 기능: 프로세스나 프로세스 그룹에게 시그널을 보내는 명령어_**  
> kill [옵션] [PID]

|시그널|번호|내용|
| ---- | ---- | ---- | 
|SIGHUP|1|로그아웃과 같은 세션이 종료될 때 시스템이 보내는 시그널|
|SIGINT|2|키보드로부터 오는 인터럽트 시그널로 실행을 중지 시그널(Ctrl + C)|
|**SIGKILL**|9|무조건 종료, 강제로 종료시키는 시그널|
|SIGEGV|11|메모리 침범이 일어날 대, 시스템이 보내는 시그널|
|SIGTERM|15|기본 값, 정상 종료시키는 시그널|
|SIGHLD||17|자식 프로세스가 stop되거나 종료되었을 때, 부모에게 전달하는 신호|
|SIGSTOP|19|터미널에 입력된 정지 시그널, SIGCONT로 재실행|
|SIGTSTP|20|실행 정지 후, 다시 실행을 계속하기 위해 대기시키는 시그널(ctrl + z)|

- ps 명령어의 practice와 함께 보기

- 예시

```yml
# 1개의 프로세스
kill 1234

# 여러 개의 프로세스
kill 1234, 5678, 3456 

# SIGKILL 시그널을 보낸다.
kill -9  PID
```

<br>


## 2.5 killall

> **_기본 기능: 프로세스 이름으로 프로세스 종료_**  
> killall [옵션] [프로세스명 or 데몬명]

- 같은 데몬에 있는 것을 한 번에 종료시킬 때 사용 

```yml
# httpd 데몬을 종료시킨다.
killall httpd
```


<br>



## 2.6 skill

> **_기본 기능: 시스템에 접속해 있는 사용자 혹은 특정 터미널을 종료시키는데 사용되는 명령어_**    
> skill -KILL [사용자 or 터미널]

- 부적절하게 접속한 사용자를 종료시킬 때, 또는 특정 사용자가 권한 밖을 접근하던가 과도한 트래픽을 초래할 때 사용  

- 예시
    - 사용자를 종료시킬 때: `skill -KILL ec2-user`  
    - 특정 터미널에 접속 중인 사용자를 종료시킬 때: `skill -KILL pts/o`  
    - pts는 가상 터미널을 의미  

<br>

---
# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)