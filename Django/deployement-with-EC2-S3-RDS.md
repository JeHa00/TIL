# 0. Introduction 

> 1. [EC2, S3, RDS란??](#1-ec2-s3-rds란)    
> 2. [인프라 구조](#2-인프라-구조)    
> 3. [EC2 인스턴스 생성하기 + 탄력적 IP 설정](#3-ec2-인스턴스-생성하기--탄력적-ip-설정)  
> 4. [pem key에 권한 부여하기](#4-pem-key에-권한-부여하기)    
> 5. [Install package & File structure](#5-install-package--file-structure)    
> 6. [EC2 public IPv4와 django server 연결](#6-ec2-public-ipv4와-django-server-연결)    
> 7. [uWSGI 설치 및 적용하기](#7-uwsgi-설치-및-적용하기)  
> 8. [nginx 설치 및 적용하기](#8-nginx-설치-및-적용하기)


- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 김형종 강사님의 django 강의를 학습한 내용입니다.

- 만든 프로젝트를 EC2에 올리고, S3에는 STATIC 파일들을 저장하고, RDS를 연결해보는 실습을 해보고 나서 정리한 것을 올린다.

- 또한, 실제 현업에서는 STATIC 파일을 올리는 방법들 중 S3를 사용하는 방식을 사용한다고 한다. 

<br>

---

# 1. EC2, S3, RDS란?

AWS의 EC2, S3, RDS에 대해 간단히 알아보자.

- AWS EC2
    - 클라우드 인스턴스가 돌아가는 서버 

- AWS S3
    - 정적 데이터(static, media etc..)가 올라가는 서버 
    - 확장성이 높아서, 스토리지 규모를 확장/축소할 수 있다. 
    - 버킷: S3에 저장되는 파일들이 담기는 바구니  

- AWS RDS
    - 클라우드 스토리지로서, 인터넷 공간에 데이터를 저장하는 저장소를 의미

<br>

---

# 2. 인프라 구조 

> **_로컬 서버와 달리 다음과 같이 분산시키는 이유는 트래픽을 분산시키고, 확장성을 갖추기 위함이다._**

![image](https://user-images.githubusercontent.com/78094972/195893277-4c665390-8b60-446e-8e05-aa681c40d86a.png)

로컬 서버로 돌릴 때는 request가 들어오면 local에 있는 runserver가 모든 걸 해결한다. 하지만, 실제 서비스에서는 그렇지 않다. 

🔆 NGINX 와 uWSGI 란?
무엇인지에 대해서는 [[TIL]Web Application Basic study: client와 server / web server structure](https://jeha00.github.io/post/network/webapplicationbasic01/)를 참고한다. 


위 구조에서는 'NGINX' 와 'uWSGI'를 사용했다. 

### 정적 데이터: NGINX -> S3

NGINX는 HTTP request가 들어오면 url를 통해서 정적 데이터인지, 동적 데이터인지를 구별하여 정적 데이터를 처리하는 서버다. 

그래서, 정적 데이터이면 AWS S3로 이동한다. 

### 동적 데이터: NGINX -> uWSGI -> Django -> AWS RDS

하지만, 그렇지 않은 루트로 시작되는 것들은 uWSGI로 보내지고, uWSGI가 EC2 위의 django application에게 전달한다. 그리고, 이를 거쳐서 AWS RDS로 저장된다. 

<br>

---

# 3. EC2 인스턴스 생성하기 + 탄력적 IP 설정

### EC2 인스턴스 생성

- 첫 번째, AWS EC2로 이동하여 로그인 후, 인스턴스를 생성한다.   
- 두 번째, ubuntu 20.04 프리티어 선택 -> t2.micro 선택 -> 키 페어 생성(RSA, .pem 선택)   

- 세 번째, 네트워크 설정
    - 보안그룹 생성 클릭: 보안 그룹 이름은 임의의로 정한다.  
    - '인터넷에서 HTTPs 트래픽 허용'과 '인터넷에서 HTTP 트래픽 허용' 둘 다 체크
    - 그리고, 인바운드 보안 그룹 규칙 1가지를 추가
        - 유형: '사용자 지정 TCP'
        - 포트 번호: 8000
            - 왜냐하면 장고 시작하는 서버가 8000번 포트이기 때문
        - 소스 유형: 위치 무관

**🔆 보안그룹의 인바운드 그룹이란?** 
- request를 받을 때, 일부 프로토콜만 허용하는데 이 범위를 정하는 게 범위 그룹의 인바운드라는 그룹이다.   
- 브라우저로 접근하기 때문에 HTTP에 대한 접근을 허용했다. 

<br>

### 탄력적 IP 설정

- 첫 번째, 모든 인스턴스 목록이 보이는 창에 들어가서 왼쪽에 '네트워크 및 보안' 탭의 '탄력적 IP'를 클릭
- 두 번째, 오른쪽 위에 '탄력적 IP 할당'을 클릭
- 세 번째, region만 'ap-northeast-2' (서울을 의미)인지 확인하고 '할당' 버튼 클릭
- 네 번째, 생성된 인스턴스에 탄력적 IP 주소 연결하기 
    - 생성했으면 연결된 인스턴스가 없기 때문에, 연결작업을 해야 한다.
    - '탄력적 IP 할당' 버튼 왼쪽에 작업 버튼을 클릭하여, '탄력적 IP 주소 연결'을 클릭한다. 그 후, 위에서 생성한 인스턴스를 클릭하여 연결한다.


**🔆 왜 탄력적 IP 주소라고 하는가?**
- EC2가 종료되었다가, 다시 시작하면 IP가 바뀐다.
    - 예를 들어서 공유지 전원을 겄다가 키면 주소가 바뀐다. 그런데 사용 중인 IP주소가 바뀌는 걸 막고자 탄력적 IP를 사용한다.
- 탄력적 IP 주소를 사용한 결과, 외부에서는 이 IP가 고정된다.

<br>

---

# 4. pem key에 권한 부여하기 

터미널을 실행한 후, 아래 명령어를 입력한다. 

```yml
$ pwd
/Users/jehakim

$ mkdir .ssh-ls

# pem key 를 옮긴 directory로 이동하기
$ cd .ssh-ls 

# 다운 받은 pem key를 옮기기
$ mv ~/Downloads/<perm key name>.pem . 

# pem key에 권한 부여하기
$ chmod 400 <perm key name>.pem
```

그러면 ssh로 접근하기 위해서 생성한 인스턴스의 Public IP address를 복사하자.

```yml
$ ssh -i <perm key name>.pem ubuntu@<public IP address>
...
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes

ubuntu@ip-172-31-3-151:~$
```

리눅스 수업에서 학습했듯이 root로도 접근할 수 있다.

하지만, 현업에서는 거의 root로는 접근하지 않는다. 



**- ❗️ ssh -i 과정에서 'Permission denied(publickey)'가 뜬다면 원인을 다음 경우들로 생각해보자.**
- IP 주소가 잘못된 게 아닌 이상 어딘가 오타가 존재한다는 의미다.
- 현재 경로가 pem key file이 존재하지 않은 directory에 있다는 것


**- ❗️ Ubuntu OS에서는 우분투 계정을 기본적으로 제공하므로, 별도로 계정을 추가할 필요가 없다.**  
**- ❗️ pem 파일은 Owner read 권한만 존재해야 접근 가능하다.**  

<br>

---

# 5. Install package & File structure

## 5.1 Install package

> **_ubuntu에서는 패키지 관리를 `sudo apt-get`을 사용한다._**

```yml
$ubuntu@ip-172-31-3-151:~$ sudo apt-get update
...

$ubuntu@ip-172-31-3-151:~$ sudo apt-get install build-essential
```

- 위 명령어를 다 실행했으면 파이썬 규격을 맞추기 위해서 아래 명령어를 입력하여 파이썬을 설치한다.
    - 아래 명령어가 없으면 **uWSGI** 를 실행할 수 없다. 
    - `sudo apt-get install python3.9 python3.9-dev`

- 마지막으로 가상환경 모듈을 설치한다.
    - `sudo apt-get install virtualenv`

<br>

## 5.2 File structure

- 우분투 로그인 시, 기본 경로
    - `/home/ubuntu/`

- 프로젝트 경로
    - `/home/ubuntu/www/<project name>`
    - ex) project name: ls-django


    ```yml
    ubuntu@ip-172-31-3-151:~$ pwd
    /home/ubuntu

    ubuntu@ip-172-31-3-151:~$ cd www

    ubuntu@ip-172-31-3-151:~/www$ git clone <git repository address>

    ubuntu@ip-172-31-3-151:~/www$ cd ls-django

    ubuntu@ip-172-31-3-151:~/www/ls-django$ 

    ```

- 가상환경 경로
    - `/home/ubuntu/www/<project name>/<가상환경 이름>`
    - ex) 가상환경 이름: venv
    
    ```yml
    ubuntu@ip-172-31-3-151:~/www/ls-django$ virtualenv venv --python=3.9

    ubuntu@ip-172-31-3-151:~/www/ls-django$ source ./venv/bin/activate

    (venv) ubuntu@ip-172-31-3-151:~/www/ls-django$
    ```

- 장고 패키지 설치하기
    - 이미 만들어 놓은 프로젝트를 git clone 경우 이기 때문에 `requirements.txt` 가 이미 존재한다.
    - 그래서 `pip install -r requirements.txt` 를 실행하면 자동적으로 장고 패키지들을 설치한다.



<br>

---

# 6. EC2 public IPv4와 django server 연결 

- `python manage.py runserver 0:8000` 을 입력하여 실행해보자.
    - `0:8000`을 입력하지 않으면 EC2 인스턴스의 public IPv4를 사용할 수 없다. 
    
- 위 명령어를 실행한 후, 브라우저 url 창에 `<public IP address>:8000`을 입력했을 때, 다음과 같은 Error가 뜨면 잘된 것.
    - 위 8000번을 사용하기 위해서 인바운드 그룹에 추가했다.

    ```yml
    DisallowedHost at /
    Invalid HTTP_HOST header: '13.125.32.21:8000'. 
    You may need to add '13.125.32.21' to ALLOWED_HOSTS.
    ```

- 그러면 Host 부분을 수정하기 위해 `vi settings.py`를 입력하여, `ALLOWED_HOST` 부분을 다음과 같이 수정해보자.
    - `ALLOWED_HOST = ['*']`

- `python manage.py runserver 0:8000` 실행 후, `<public IP address>:8000` 을 입력하면 정상적으로 화면이 나타난다.

🔆 uwsgi 를 설치하지 않아도, 화면이 정상적으로 뜬다. 하지만 이 경우, uwsgi가 없기 때문에 nginx 와 연결될 수 없다.


<br>

---
# 7. uWSGI 설치 및 적용하기

- local에서 하는 게 아니기 때문에 uwsgi를 반드시 설치해야 한다.
-  uwsgi 설명: 파이썬으로 된 장고 프로젝트를 실행하는 역할을 한다.

## uWSGI installation


- uWSGI 설치 명령어
    - `pip install uwsgi` 
    - 만약 uwsgi가 설치되지 않는다면 아래 명령어를 실행하지 않은 것이다.
    - `sudo apt-get install python3.9 python3.9-dev`

<br>

## uWSGI 파일 수정 및 적용


### uWSGI 파일 수정

```yaml
# 경로: /home/ubuntu/www/ls-django
$ mkdir .conf
$ cd .conf

# 아래 명령어를 입력하여, 생성 및 내용 입력
$ vi uwsgi.ini 

## uwsgi.ini
# 아래 내용 입력 후 esc → `:wq!` 눌러 저장 종료한다. 
[uwsgi]
# 아래 3가지 변수는 장고가 uWSGI를 실행할 수 있는 환경을 만들어주는 것
chdir = /home/ubuntu/www/ls-django
module = config.wsgi:application
home = /home/ubuntu/www/ls-django/venv

socket = /tmp/app.sock
chmod-socket = 666

vacuum = true
master = true
daemonize = /home/ubuntu/www/ls-django/uwsgi.log
```

- **uwsgi 내부 변수 설명**
    - django가 uWSGI를 실행할 수 있는 환경을 만들어주는 변수 3가지
        - **chdir** : django project 경로
        - **module** : django project wsgi module 위치
        - **home** : 가상환경 위치
    - **socket** : IP 네트워크로도 이용할 수 있으나, 같은 os에 있는 프로세스 있으므로 이럴 때는 소켓을 사용하여 속도 개선을 한다.
        - 동일 서버 내 요청을 중개하는 것으로 소켓 전송 방식으로 진행
        - 대부분 이런 방식을 사용한다.
    - **chmod-socket** : 권한 부여
    - **vacuum** : uWSGI 통하여 생성된 파일을 삭제하는 옵션
    - **master** : uWSGI 프로세스를 master로 동작하도록 하는 옵션
    - **daemonize** : 백그라운드로 동작하기 위한 옵션이며, log file을 남길 경로 지정
        - log file이란 `uwsgi.log` 를 의미

- 실제로는 더 많은 옵션들이 존재하므로, 더 찾아보자.

<br>

### uWSGI 파일 적용

- `uwsgi --ini uwsgi.ini` 명령어를 통해 변경된 설정값을 적용한다. 
    - **변경된 설정값을 적용하는 명령어**


```yaml
# 경로: www/ls-django/.conf
$ uwsgi --ini uwsgi.ini

# 실행결과
[uWSGI] getting INI configuration from uwsgi.ini 

# 그리고, uwsgi.log 가 생성될 것이다.

# 이 log를 확인하는 방법은 tail -f uwsgi.log 를 실행한다.
# -f 의 의미는 log에 새로 생기면 바로 출력된다는 걸 의미한다. 
```

<br>

## 🔆 uWSGI 과정에서 발생한 Error 정리

### Case 1

- 만약 위 명령어를 입력했을 때 다음과 같은 Error가 뜬다면 다음과 같은 절차로 확인한다.
    - `Command 'uwsgi' not found, but can be installed with:`
    - 첫 번째, 가상환경이 활성화되어 있는지 확인하라.
    - 두 번째, `pip install uwsgi`을 실행했는지 생각해라.

<br>

### Case 2

- 만약 다른 Error로 다음과 같이 뜬다면 경로를 `uwsgi.ini` file이 있는 경로로 이동하라.
    - `realpath() of uwsgi.ini failed: No such file or directory [core/utils.c line 3662]`
- 만약 또 다른 다음 Error가 뜬다면 `uwsgi.ini` 에 입력한 프로젝트 경로에 root를 입력하지 않은 것이니, 수정하라.
    - 수정 전: `home/ubuntu/www/ls-django/uwsgi.log`
    - 수정 전: `/home/ubuntu/www/ls-django/uwsgi.log`

<br>

### 로그 확인

- `uwsgi --ini uwsgi.ini` 의 실행 결과, `uwsgi.log`가 생성된다. 

- `tail -f uwsgi.log`를 실행하여 로그를 확인할 수 있다.
    - `-f`의 의미: follow의 약어로서, 계속해서 파일의 상태를 감시하며 파일에 내용이 뒤에 추가될 때마다 새로 추가된 내용을 보여준다.


<br>

---
# 8. NGINX 설치 및 적용하기


## nginx 설치하기

Nginx 설치 명령어 실행하기

- `sudo apt-get install nginx`

<br>

## nginx.conf 수정하기

> **_Nginx를 수정하기 위해서는 프로젝트 내부 파일이 아니기 때문에 'sudo'를 사용해야 한다._**

아래 경로 파일 두 가지를 수정할 것이다.

-  `/etc/nginx/nginx.conf`  
    - `sudo vi /etc/nginx/nginx.conf`
    - 만약 위 명령어를 입력 시, tab을 눌렀는데 위 단어들이 안나오면 nignx가 설치되지 않은 것이기 때문에 `sudo apt-get install nginx` 명령어를 실행하자.

-  `/etc/nginx/sites-enabled/default`  
    - `sudo vi /etc/nginx/sites-enabled/default`

<br>

### nginx에 uWSGI socket 연결하기

> **_nginx 안에 uWSGI 소켓을 입력하여 연결시킨다. 그래서 Nginx를 설치하기 전에 uWSGI를 먼저 설치했다._**

- uWSGI에서 소켓을 열었다. 
    - 이를 어떻게 알 수 있냐면 uwsgi file의 `socket = /tmp/app.sock`이 바로 소켓을 열었다는 의미다. 
- 이 path를 아래에 나와있다시피 location에 추가하여 연결하자.
- 그리고, uwsgi의 parameters(params)를 포함시키자.

```yaml
## 다음 경로 파일 두 가지의 내용 값을 아래와 같이 수정하기

# 파일 경로: /etc/nginx/nginx.conf
user ubuntu;

# 파일 경로: /etc/nginx/sites-enabled/default
location / { 
        uwsgi_pass     unix:///tmp/app.sock;
        include        uwsgi_params;
}
```

<br>

### nginx와 uwsgi가 어떻게 연결되는가?

파일 경로: /etc/nginx/sites-enabled/default 내용을 살펴보자.

- 포트번호 80을 보고 있다가, 일로 들어온 url 경로가 `/` 면 location / { } 의 내부 내용 중 uwsgi_pass로 전달해준다. uwsgi가 django app을 실행한다.
    - location 옆은 url 주소를 의미하고, 내부는 url 주소에 대응되는 프로젝트 디렉토리 경로를 의미한다.


```yaml
# Default server configuration
#
server {
        listen 80 default_server;
        listen [::]:80 default_server;

				root /var/www/html;

        index index.html index.htm index.nginx-debian.html;

        server_name _;

        location / {
                uwsgi_pass  unix:///tmp/app.sock;
                include     uwsgi_params;
				}
```

그래서 httprequest가 들어오면 NGINX가 uWSGI로 전달해준다. 

static file을 실행시켜주는 것도 위에 location에서 실행한다. 

<br>

## nginx 적용하기

위 내용을 파일에 입력했으면 다음 명령어 2가지를 실행한다.

- `sudo service nginx reload`: nginx 재적용
- `sudo service nginx status`: nginx 상태 확인

```yaml
ubuntu@ip-172-31-3-151:~$ sudo service nginx reload
ubuntu@ip-172-31-3-151:~$ sudo service nginx status
nginx.service - A high performance web server and a reverse proxy server
     Loaded: loaded (/lib/systemd/system/nginx.service; enabled; vendor preset:>
     Active: active (running) since Thu 2022-10-13 16:55:50 UTC; 12h ago
       Docs: man:nginx(8)
    Process: 24456 ExecReload=/usr/sbin/nginx -g daemon on; master_process on; >
   Main PID: 21207 (nginx)
      Tasks: 2 (limit: 1143)
     Memory: 7.9M
     CGroup: /system.slice/nginx.service
             ├─21207 nginx: master process /usr/sbin/nginx -g daemon on; master>
             └─24457 nginx: worker process

Oct 13 16:55:50 ip-172-31-3-151 systemd[1]: Starting A high performance web ser>
Oct 13 16:55:50 ip-172-31-3-151 systemd[1]: Started A high performance web serv>
Oct 14 05:21:09 ip-172-31-3-151 systemd[1]: Reloading A high performance web se>
Oct 14 05:21:09 ip-172-31-3-151 systemd[1]: Reloaded A high performance web ser>
lines 1-16/16 (END)
```

위에 `sudo service nginx reload` 를 실행한 결과, `:8000`을 입력하지 않고 public IPv4 만 입력해도 애플리케이션을 불러올 수 있다.  

하지만, css가 전혀 적용되지 않는다. 

이를 직접 보고 싶으면 불러온 사이트의 inspection tool로 들어가서 Network > CSS 를 클릭하면 status code를 보고 알 수 있다.

<br>

# ❗️ 502 Bad Gateway Error

위 작업을 다 완료했음에도 불구하고도, `sudo service nginx reload` 후, 502 Bad Gateway Error가 뜬다면 무엇이 원인일까?

더 정확하게 원인을 알고자 log에 접근했다.

- `vi /etc/nginx/nginx.conf` 로 들어가서 error_log 경로를 찾아보자. 
    - 찾은 경로로 `tail -f <찾은 경로>` 를 입력하여 error 확인하기

    ```yml
    2022/10/15 13:18:01 [error] 19512#19512: *71 upstream prematurely closed connection while reading response header from upstream, client: 27.34.20.255, server: _, request: "POST / HTTP/1.1", upstream: "uwsgi://unix:///tmp/app.sock:", host: "13.125.216.246"
    ``` 

위에 메세지에서도 HTTP response message의 header를 읽는 동안 연결이 완전히 닫혀졌다는 걸 알 수 있다. 

그러면 무엇과 무엇 사이가 닫힌 것일까? 

nginx와 uwsgi 사이일까?  

- 그래서 django과 nginx에서 '502 Bad Gateway'로 검색해봤지만, 마땅한 결과가 뜨지 않아 stackoverflow에 검색한 결과 다음과 같은 결과를 알 수 있었다.

출처: [django with nginx uwsgi bad gateway 502](https://stackoverflow.com/questions/42063718/django-with-nginx-uwsgi-bad-gateway-502)

- nginx는 들어온 request를 전달했지만, nginx와 연결된 대상이 없어서 response를 받지 못한 상황이라고 한다. 즉, nginx와 uwsgi 와의 연결이 끊어져있다는 상황이다. 

- 그래서 uwsgi 가 제대로 설치되고 있는지 확인하라고 했다.
    - `which uwsgi` 를 입력해보니 확실한 경로가 나와서 설치된 걸 확인할 수 있다.

- 그러면 uwsgi가 실제로 실행되고 있는지를 확인해보자.
    - 단지 명령어만 입력하면 실행될거라 생각했지만, 그렇지 않았던 것이다. 

    ```
    $ tail -f uwsgi.ini
    ...
    --- no python application found, check your startup logs for errors ---
    [pid: 20584|app: -1|req: -1/13] 210.123.107.252 () {42 vars in 791 bytes} [Sat Oct 15 16:45:11 2022] GET / => generated 21 bytes in 0 msecs (HTTP/1.1 500) 2 headers in 83 bytes (1 switches on core 0)
    ```

    - python application을 발견할 수 없다고 뜬다. 


그러면 uwsgi 를 설치하는 것에 문제가 생긴 것으로 판단된다. 직접 확인해보자.

- `ls -al ./venv/lib/python3.9/site-packages` 를 입력하여, `uWSGI-2.0.20.dist-info` 를 확인해보자. 그러면 존재하지 않는다는 걸 알 수 있다.

- 원인을 분석해보면 위에 pip install uwsgi 명령어를 입력하여 오류가 떠서, 구글링을 통해 `sudo apt install uwsgi`이 이를 대체할 수 있다고 판단하여 설치했으나 이걸로 오류가 계속 발생한 것이다.





🔆 위 접근 방식에 대해서 아래 블로그에서도 언급하고 있다. 
- [Set up Nginx and uWSGI](https://monicalent.com/blog/2013/12/06/set-up-nginx-and-uwsgi/)에서 맨 마지막 부분에 언급된다. 











<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)
