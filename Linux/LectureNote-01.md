# 0. Introduction

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

<br>

---

# 리눅스 구조 실습

aws 사이트가 아닌 terminal에서 손쉽게 인스턴스에 접속할 수 있는 환경 조성해보자.

- 이 환경을 조성하는 이유는 aws 사이트에서 인스턴스 생성 및 연결 시, SSH 클라이언트 탭에서 예시로 입력한 명령어를 입력할 수 있지만, 매우 길고 효율적이지 않기 때문이다. 

🔆 ssh란 secure shell의 줄임말로, 원격 호스트에 접속하는데 사용되는 보안 프로토콜을 의미한다. 이는 비밀번호로 인증하는 것이 아닌, key로 인증하는 방식이다.  


사용자와 관리자 명령 프롬프트 차이에 대해 알아보자.  

### ssh directory 생성부터 시작

- 첫 번째, Pem key가 있는 경로로 이동한다.
- 두 번째, `mkdir .ssh`
- 세 번째, `chmod 700 .ssh`

<br>

### .ssh/config 생성하기

cp dalkom.pem ~/.ssh 으로 복사

- 경로는 위에 언급된 경로와 동일

- pem key의 권한을 수정
    - `chmod 600 <pem key 이름>.pem`

- config 생성하기 
    - `vi .ssh/config`

- 그러면 vim 모드로 전환하면 `i`를 입력하면 내용 입력이 가능하다. 밑에 내용을 입력한다.

    ```yml
    # 여기서 user는 서비스명을 의미한다.
    Host user
        # 자신이 생성한 인스턴스의 IPv4를 입력한다. 
        HostName 43.200.244.241
        user ec2-user
        IdentityFile ~/.ssh/dalkom.pem

    Host root
        # 자신이 생성한 인스턴스의 IPv4를 입력한다. 
        HostName 43.200.244.241
        User root
        IdentityFile ~/.ssh/dalkom.pem
    ```

- 입력을 다 맞쳤으면 `esc`를 누르고, `:wq`를 입력한다.

<br>

### config 생성 잘 되었는지 확인하기  


- `cat .ssh/config` 를 입력했을 때, 위에 입력한 값들이 뜨면 잘 나온 것이다.


### 마지막으로 권한 수정

- `chmod 700 ~/.ssh/config`를 입력하여 프로그램의 실행 권한을 수정한다.  


### 접속 확인

- `ssh <host name>`

<br>

---

# root 권한으로 로그인하기  
aws-ec2 는 보안상의 이유로 루트를 통한 접근을 보안 상의 이유로 금지하고 있다.
보통은 sudo su 명령어로 루트 권한을 사용할 수 있으나, 혹시 모르니 루트 권한을 활성화하는 방법을 알아보자.

`sudo vi /etc/ssh/sshd_config` 을 입력한다.

보다 보기 편하게 line을 표시하기 위해서 `set nu`를 사용하자.  

밑으로 내리다보면 38번째 줄에 PermitRootLogin에 yes가 있는지 확인한다.  

확인되면 `:q!` 를 입력하여 저장 없이 나간다.

그후, `sudo cp /home/ec2-user/.ssh/authorized_keys /root/.ssh`를 입력한 후, `sudo systemctl restart sshd` 를 마저 입력한다.

마지막으로 `exit` 을 입력하여 나간다.


리눅스는 클라이언트가 아닌 서버 형태로 운영하기 떄문에, Root로만 로그인하겠다.

만일 user로서 로그인 하여 `pwd`를 입력하면 `/home/ec2-user`가 뜬다.  

하지만, root로 접근하여 `pwd`를 입력하면 `/root`가 뜬다.  

리눅스 구조에서 설명드린 디렉토리와 폴더의 차이는 디렉토리는 내가 현재 위치한 것 밖에 볼 수 없다.

하지만, 폴더는 내가 현재 보고 있는 디렉토리 이외의 디렉토리도 볼 수 있는지가 차이다.  

`ls -l`를 입력하면 `d`가 맨 앞에 있는 것을 볼 수 있는데, 이것이 directory이고, `l`로 된 것이 폴더이다. 

`ls -l`은 현재 디렉토리 외의 디렉토리를 확인하고자 할 때 사용하는 명령어

<br>


---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)