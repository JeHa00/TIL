# 0. Introduction

> 1. [인스턴스 접속 환경 조성하기](#인스턴스-접속-환경-조성하기)
> 2. [root 권한으로 로그인하기](#root-권한으로-로그인하기)
> 3. [Error 보고](#error-보고)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

-  AWS ec2 sinstance 접속 환경 조성해보고, 그 과정에서 error 원인과 대처 과정을 기록해보았다.

<br>

---

# 인스턴스 접속 환경 조성하기

aws 사이트가 아닌 terminal에서 손쉽게 인스턴스에 접속할 수 있는 환경 조성해보자.

- 이 환경을 조성하는 이유는 aws 사이트에서 인스턴스 생성 및 연결 시, SSH 클라이언트 탭에서 예시로 입력한 명령어를 입력할 수 있지만, 매우 길고 효율적이지 않기 때문이다. 

🔆 ssh란 secure shell의 줄임말로, 원격 호스트에 접속하는데 사용되는 보안 프로토콜을 의미한다. 이는 비밀번호로 인증하는 것이 아닌, key로 인증하는 방식이다.  


사용자와 관리자 명령 프롬프트 차이에 대해 알아보자.  

### 사전 준비


- 인스턴스 생성 시, `네트워크 설정`에서 `인터넷에서 HTTPs 트래픽 허용`, `인터넷에서 HTTP 트래픽 허용` 을 체크한다.  
    - 또한 `편집` 을 클릭하여 서브넷에 하나를 임의로 선택한다.   
    - 만약 이미 생성한 인스턴스를 바꾸고자 한다면 실행 중인 인스턴스에 들어가서, 인스턴스를 선택한 후 `네트워크 및 보안`의 보안 그룹 들어간다. 
    - `인바운드 규칙 편집`에 들어가 추가한다.  

- aws에 가입하여 `pem` key 를 생성한 후, `.ssh` directory에 복사해놓는다. 

- 그리고 인스턴스 생성 버튼을 클릭하여 인스턴스의 IPv4 주소를 알아낸다.  

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


# Error 보고

## 첫 번째: Error

위 과정 중에서 다음과 같은 error가 발생했다.

```yml
cd: .ssh: No such file or directory
```

위 명령어를 입력한 경로에 `.ssh`는 존재했다. 

하지만 계속해서 이와 같은 error가 발생하여 stackoverflow에서 확인했다. 

[.ssh directory not being created](https://stackoverflow.com/questions/15190391/ssh-directory-not-being-created) 에서는 `ssh-keygen`으로 `.ssh`를 생성하려고 시도했으나, 나와 동일한 error가 발생한 것이다. 

기존에 있던 것을 삭제하고, 내가 직접 `mkdir ~/.ssh`를  입력하여 `.ssh` directory를 생성했다.  

생성을 해보고 나서, 기존에 있던 것은 directory 형태가 아니었음을 알았다. 

맥북을 처음 사용하면서 기본적으로 있었기 때문에, 맥북이어서 특이한 것이라 생각했지만 잘못된 판단이었다. 

디렉토리 생성 후, `chmod 700 ~/.ssh`를 입력하여 권한을 부여했다.

그 후, `ssh-gen`을 입력하여 key를 생성했고,  위와 같은 error는 뜨지 않았다.


<br>

## 두 번째, Error

하지만, 또 다른 Error가 발생했다. 

```yml
ssh: connect to host localhost port 22: Connecction refused
```

구글링을 하여 스택오버플로우도, 다른 여러 사이트들도 찾아봤지만 이에 대해 기초 지식이 없어서 섣부르게 시도하기가 어려웠다. 

그래서 먼저 내가 강의에서 말한 의도를 정확히 이해하여 했는지 처음부터 다시 한 번 체크했다. 

기존에 만든 config 파일을 수정하는 과정에서 원인을 알았다.

Config 내에 `IdentityFile` 의 경로가 잘못 입력된 것이었다. 

왜 잘못 입력했는지 생각을 해보니, 강사님과 다른 경로에서 해보고 싶어서 시도를 했고 이 과정에서 `~`에 대해 정확히 이해하지 못 했기 때문에 생긴 오류였다.

그래서 경로를 올바르게 작성한 후, 다시 시도해보니 Error가 완전히 해결되었다.

이제 `ssh root` 와 `ssh user`를 사용하면 손쉽게 AWS를 통해서 Linux를 학습할 수 있게 되었다.  


<br>


---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)