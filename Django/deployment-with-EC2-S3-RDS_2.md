# 0. Introduction 

> 1. [Static file Serving](#static-file-serving)  
> 2. [RDS 연결하기](#rds-연결하기)
> 3. [개발자 DB와 운영 DB로 나누기](#개발자-db-와-운영-db로-나누기)


- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 김형종 강사님의 django 강의를 학습한 내용입니다.

- 만든 프로젝트를 EC2에 올리고, S3에는 STATIC 파일들을 저장하고, RDS를 연결해보는 실습을 해보고 나서 정리한 것을 올린다.

- 실제 현업에서는 **STATIC 파일을 올리는 방법들 중 S3를 사용하는 방식을 사용** 한다고 한다. 

- 지난 배포 과정에서 nginx 와 uwsgi 를 연결하여 EC2 public IPv4 만 입력해도 장고 웹 애플리케이션이 돌아가도록 했다. 하지만, **CSS** 가 적용되지 않는 문제점이 있다. 이를 해결하고자 S3에 연결하는 과정을 진행해본다. 그 다음으로 동적 데이터 처리를 위해 RDS와도 연결해본다. 

<br>

---
# Static file serving

3가지 방식으로 static file을 서빙해본다. 

이 3가지 방식을 경험해보면서 왜 S3를 현업에서 사용하는지 이해해보자. 

## 첫 번째 방법: 'location /static/' 추가

- 경로: `/etc/nginx/sites-enabled/default`
- 명령어: `sudo vi /etc/nginx/sites-enabled/default`
- 위 경로 파일에 아래 내용을 추가한다.

```yml
location /static/ { 
        alias   /home/ubuntu/www/ls-django/static/;
}

# nginx를 수정하면 다시 reload를 반드시 해야 한다.
$ sudo service nginx reload
```

- 의미: /static/ 이라는 url이 들어오면 alias로 가리키는 부분으로 이동해라.

- 문제점: 이 방법을 사용하면 admin에 적용되는 css를 확인할 수가 없다.  

<br>

## 두 번째 방법: collectstatic

모든 static 파일을 public이라는 한 폴더 안에 모으기 때문에, 첫 번째 방법의 문제점을 해결할 수 있다. 하지만, 이 경우 public 폴더를 사용하기 위해서 location 설정을 바꿔야 한다. 

### 1. config/settings.py 에 아래 내용 추가하기

- 경로: `www/ls-django`
- 명령어: `vi config/settings.py`

```yml
# STATIC
STATIC_URL = "static/"
STATICFILES_DIRS = [BASE_DIR / "static"]

# 위에 두 줄은 이미 입력되어 있을 것이다. 있다면 밑에 한 줄만 입력한다.
STATIC_ROOT = os.path.join(BASE_DIR, "public")
```

- STATIC_URL: static resources를 접근하는 URL
- STATICFILES_DIRS: django project에서 사용하는 static resources 경로 지정 
- 위에 STATIC_ROOT로 collectstatic 명령어를 통해 static resources가 모이는 위치

### 2. python manage.py collecstatic 실행

- 실행 결과
    -`139 static files copied to '/home/ubuntu/www/ls-django/public'.`
    - ` /home/ubuntu/www/ls-django/public` 이 생긴다.  


### 3. nginx.conf의 /static/ -> /public/

- 파일 경로: /etc/nginx/sites-enabled/default
- 명령어: `sudo vi /etc/nginx/sites-enabled/default`

- `python manage.py collectstatic`을 실행한 결과, public이 생기면서 정적 파일들을 이 폴더로 모았기 때문에 아래 내용에서 `/home/ubuntu/www/ls-django/static/`을 `/home/ubuntu/www/ls-django/public/`으로 바꾼다.

```yml
location /static/ { 
				alias /home/ubuntu/www/ls-django/public/;
}
```

- nginx를 수정했으므로, `sudo service nginx reload`를 실행하여 반영한다.

- 문제점: 프로젝트 내부에 정적 파일들을 모아놓기 때문에, 서버 부하를 피할 수 없다.

<br>

## 세 번째 방법: S3에 연결하기

두 번째 방법의 문제점을 해결하기 위해 내부가 아닌 외부 AWS S3에 모아놓은 정적 파일들을 올려서 서버 부하를 분산시킨다. 

### 1. AWS S3에서 Bucket 만들기



### 2. Django module 설치하기

경로: 프로젝트 내부

1. `pip install boto3` 실행

2. `pip install django-storages` 실행

storages는 settings.py 와 연관되어 있기 때문에, INSTALLED_APPS에 storages  등록한다.

### 3. AWS IAM에서 다운 받은 key를 settings.py에 반영하기

먼저 아래 과정을 거친다.
1. AWS IAM 들어가기 > 왼쪽에 액세스 관리 > 사용자 > 사용자 추가
2. 사용자 세부 정보 설정 > 사용자 이름 입력
3. AWS 액세스 유형 선택 > 액세스 키 - 프로그래밍 방식 액세스 선택
4. 다음 권한 클릭 > 권한 설정 > 그룹에 사용자 추가 > 그룹 생성 > 그룹 이름 입력 > 정책 이름 > AmazonS3FullAccess 클릭 > 그룹 생성

🔆실제 서비스에서는 AmazonS3FullAccess를 선택하지 않으므로, 나중에 조정해야 한다.

5. 다음: 태그 > 다음: 검토 > 사용자 만들기 를 거쳐서 총 5단계까지 진행한다.
    1. 5단계에서 `.csv 다운로드` 를 클릭하여 다운받는다.
    2. 다운받은 csv를 열어보면 secret access key를 가지고 있다.
6. 위에 다운받은 key를 아래 `settings.py` 에 입력한다.

AWS 설정값 구성을 settings.py에 추가하기

```yml
# 경로: settings.py 
# 제일 아래에 추가한다. 
# 입력시 대괄호는 빼고 입력한다.

AWS_ACCESS_KEY_ID = "[액세스키]"
AWS_SECRET_ACCESS_KEY = "[비밀 키]"
AWS_REGION = "[리전명]"
AWS_STORAGE_BUCKET_NAME = "[버킷명]"
AWS_S3_CUSTOM_DOMAIN = f"{AWS_STORAGE_BUCKET_NAME}.s3.{AWS_REGION}.amazonaws.com"
AWS_DEFAULT_ACL = "public-read"
DEFAULT_FILE_STORAGE = "config.storages.S3DefaultStorage" 
STATICFILES_STORAGE = "config.storages.S3StaticStorage"
```

- 액세스키, 비밀키는 csv에 존재한다.
- 버킷명과 리전명은 S3로 이동하여 확인한다. 
    - ap-northeast-2
- 맨 아래 `DEFAULT_FILE_STORAG` 와 `STATICFILES_STORAGE` 의 각 `S3DefaultStorage` , `S3StaticStorage` 는 밑에 `config/storages.py` 의 class를 의미한다.


### 4. config/storage.py 추가하기

- `config/storage.py`는 위에 settings.py에 추가한 코드대로 config 밑에 추가한다. 

- 경로: config/storages.py

```yml
from storages.backends.s3boto3 import S3Boto3Storage

class S3DefaultStorage(S3Boto3Storage): 
		location = "media"

class S3StaticStorage(S3Boto3Storage): 
		location = "static"
```

### 5. static directory 파일들을 S3로 옮기기

`python manage.py collectstatic` 실행하기

- `config/settings.py`의 `AWS_STORAGE_BUCKET_NAME` 을 AWS IAM의 사용자 이름과 착각하지 말기

### 6. nginx의 location url로 경로 바꾸기 

- 다시 브라우저 화면의 inspector를 들어가서 css 파일 url을 보면 AWS S3 bucket으로 연결되지 않은 걸 확인할 수 있다.   

- 그 이유는 바로 `/etc/nginx/sites-enabled/default` 의 location `/public/` url 안에 있는 경로 때문이다. 이를 S3 경로로 수정해야 한다.  
    - 그러면 amazon S3 bucket 에 들어가면 `static/` 이 존재한다. 그래서 각 파일을 클릭하여 들어가면 각 객체마다의 url 경로가 존재한다.  

- 버킷의 url을 복사하는 방법
    - 버킷에 들어가서 `static/` 왼쪽에 빈칸을 클릭하면 `URL 복사`가 활성화된다. 

```yaml
# 경로: /etc/nginx/sites-enabled/default
location /static/ {
				alias.  https://learningspoons-django-bucket1.s3.ap-northeast-2.amazonaws.com/static/;
}
```

변경했으니 다시 reload 한다.

- `sudo service nginx reload`: nginx 재적용
    - 하지만 그래도 css는 불려지지 않는다.

아래 명령어를 입력한 후, 다시 조회해보면 css가 불려질 것이다.

```yaml
$ uwsgi --ini .conf/uwsgi.ini

$ sudo service nginx reload
```

css 파일의 Request URL을 보면 다음과 같이 aws에서 오는 걸 확인할 수 있다.

🔆 기타 개념: CloudFront
- 인스타그램 같은 국제 서비스는 한국에서 미국으로 접속해야 한다. region과 여러 나라 간의 거리가 멀기 때문에, 스태틱 파일을 서빙하는게 오래 걸린다. 그래서 여러 중간 지점에 복사본(mirror image)을 저장하여 서빙 시간을 아낀다.   
- S3를 본 딴 이미지가 여러 각 지역에 위치한다. 그래서 보다 더 빨리 서빙한다.  
- CDN = 콘텐츠 전송 네트워크(Content Delivery Network) 로 옮겨서 응답시간을 개선. 지리적으로 분산된 서버의 네트워크  


<br>

---
# RDS 연결하기

RDS를 연결한 후, super user 계정을 생성하여 admin page에 들어가보자. 

admin page에 들어가려는 이유는 이것으로 DB 연결 유무를 확인할 수 있다. 

❗️ 아래 연결 설정들은 단지 **RDS를 연결해보자는 취지** 에 맞게만 설정한 것이므로, 실제 프로젝트 시작 시에는 다를 수 있다. 

## RDS DB 생성하기


1. AWS RDS 들어가기 -> '데이터 베이스 생성하기' 클릭

2. 데이터베이스 생성 방식 선택: '표준 생성' 클릭

3. 엔진 옵션: PostegreSQL 클릭

4. 템플릿: 개발/테스트 클릭

5. 설정
    - 'DB instance 식별자' 에 이름 입력
        - ex) learningspoons
    - '마스터 사용자 이름'에 이름 입력
        - ex) learningspoons 

6. 인스턴스 구성
    - '버스터블 클래스' 선택 -> 'db.t3.micro' 클릭

7. 스토리지: 스토리지 자동 조정 체크 해제
    - 왜냐하면 지금 바로 필요하지 않기 때문

8. 가용성 및 내구성: 기본값을 유지

9. 연결: 
    - '퍼블릭 액세스'에서 '예' 선택
    - 'VPC 보안 그룹(방화벽)' 에서 '기존 항목 선택' 클릭

10. 데이터베이스 인증:
    - '암호 인증' 선택

11. 모니터링: '성능 개선 도우미'에서 '성능 인사이트 켜기' 체크 해제


12. 추가 구성:
    - '초기 데이터베이스 이름' 작성하기
        - ex) postgres

<br>

## 인바운드 규칙 편집

1. AWS EC2에 들어가서 해당 인스턴스 선택

2. 왼쪽 '네트워크 및 보안' 에 '보안 그룹' 클릭

3. 1번 인스턴스의 보안그룹 선택 후, '인바운드 규칙 편집' 클릭

4. '규칙 추가' 클릭 -> 유형: 'postgresql' 선택 -> 소스: 'Anywhere-IPv4` 선택


## ❗️인바운드 규칙 미추가로 인한 Error

4번을 추가하지 않으면 나중에 `python manage.py runserver`를 실행할 때, 다음과 같은 안내문이 뜨면서 진행되지 않는다. 위 보안사항을 추가하면 자연스럽게 서버가 돌아간다.


```yml
# ls-django.cvhmktue5rnw.ap-northeast-2.rds.amazonaws.com 는 생성한 RDS DB의 엔드포인트를 의미
Is the server running on host "ls-django.cvhmktue5rnw.ap-northeast-2.rds.amazonaws.com" 
(172.31.28.221) and accepting TCP/IP connections on port 5432?
```

<br>

## local django settings.py에 RDS정보 입력

> **_기존에 있던 DATEBASES 내용을 다음과 같이 수정한다._**  

해당 강의를 진행할 때는 local에서만 진행했지만, 나는 EC2 우분투 서버에서도 해본 결과 동일하게 진행하면 된다. 

```python
# config/settings.py
DATABASES = { 
"default": {
       "ENGINE": "django.db.backends.postgresql_psycopg2",     

        # 아래는 예시일 뿐, 리스트말고 문자열로 입력한다.
        “HOST": [RDS 엔드포인트],
        "NAME": [DB 이름], 
        "PORT": "5432",
        "USER": [마스터 사용자 이름],
        "PASSWORD": [비밀번호], 
      }
}
```

- 생성한 DB 인스턴스를 들어가서 클릭하여 ‘연결 & 보안’ 탭에 들어가면 HOST가 존재한다.
- NAME, USER 는 “구성” 탭에 존재한다.



위 내용을 입력하고 `runserver`를 하면 `ENGINE`에서 `psycopg2`가 설치되지 않아 ModuleError가 발생된다. 

이를 해결하고자 psycopg2를 설치해보자.

<br>

## psycopg2 설치하기

postgresql을 사용하기 위해서 psycopg2 를 설치해야 한다. 

psycopg2는 컴퓨터 OS마다, 버전마다 설치방법이 달라서 다양하게 시도해보았다.

stackoverflow도 보고, github issue도 뒤져본 결과 맥북 에어 M1을 사용한다는 전제하에 다음 명령어를 입력하니 한 번에 해결되었다.

- `pip install psycopg2-binary==2.9.2` 

내가 시도해본 순서를 정리하여 아래에 기록한다.

```yml
pip install psycopg2-binary wheel
pip install psycopg2 
```

위에 두 명령어 실행 후, python manage.py runserver를 돌릴 때 안되면 아래 명령어 실행하기

```yml
# 아래 brew 명령어를 실행하기 위해서는 brew를 먼저 설치해야 한다. 
# 아래 두 명령어 실행 후, reinstall 명령어 안내가 뜨면 각각 다 실행하기
brew install libpq --build-from-source
brew install postgresql openssl

export LDFLAGS="-L/opt/homebrew/opt/openssl/lib"
export CPPFLAGS="-I/opt/homebrew/opt/openssl/include"

pip install psycopg2-binary --force-reinstall --no-cache-dir
pip install psycopg2 --force-reinstall --no-cache-dir
pip install --upgrade numpy
```

위 명령어들을 다 실행하고 `runserver`를 실행했을 때 psycopg2 module이 인식되지 않으면 `pip install psycopg2-binary==2.9.2` 을 실행해보자.


<br>

## admin 들어가기

`python manage.py createsuperuser`를 실행 후, 어드민 로그인을 한다.

로그인이 된다면 DB 연결이 잘 된 것이다. 


<br>

---

# 개발자 DB 와 운영 DB로 나누기 

### 첫 번째: config/settings 생성하기

- `config/settings` directory 생성 후, 그 안에 다음과 같이 생성하기
    - `__init__.py`
    - base.py
    - develop.py
    - production.py

### 두 번째: 기존 내용 base.py에 담기

기존 settings.py의 내용은 base.py에 담는다. 그리고, 기존 settings.py는 삭제한다.


### 세 번째: ALLOWED_HOSTS 내용 옮기기

기존에 있던 ALLOWED_HOSTS는 develop.py 와 production.py 에 옮긴다. 

- `config/settings/develop.py` 

    ```python
    from .base import * 

    DEBUG = TRUE

    ALLOWED_HOSTS = ["*"]
    ```

- `config/settings/production.py`

    ```python
    # config/settings/production.py
    from .base import *

    DEBUT = False

    ALLOWED_HOSTS = ["127,0,0,1"]
    ```

### settings option을 사용하여 runserver 실행 

그러면 settings를 나눴기 때문에, 옵션을 사용하여 적절한 설정을 택하여 서버를 실행시켜보자.

```yml
# terminal
$ python manage.py runserver --settings=config.settings.develop
```

하지만 `FileNotFoundError`가 발생된다. 그 이유는 `settings` directory가 생기면서 기존 설정 파일의 디렉토리 레벨이 한 단계 깊어져서, `BASE_DIR`의 값이 달라졌기 때문이다. 

<br>

### BASE_DIR 수정하기

처음에는 `BASE_DIR = Path(__file__).resolve().parent.parent` 이랬지만,


이처럼 `BASE_DIR = Path(__file__).resolve().parent.parent.parent` 수정하자.

그 후, 다시 아래 명령어를 실행하여 결과를 확인해보자. 

```yml
python manage.py runserver 0:8000 --settings=config.settings.production
```

그 결과, 화면에 `Disallowed Error`가 발생하는데, 정상적으로 잘 되었다는 걸 의미한다.  

<br>

### 🔆 DB를 production과 develop를 나눈 이유

> **_develop 단계에 사용한 DB는 테스트를 위한 DB이기 때문에, production 단계에서의 DB와 다를 수 밖에 없다._**

- RDS를 연결하면 그 때 DB는 production 단계에서 사용한다.  

- 코드로 확인해보자.
    - production.py 에는 RDS DATABASES를 적용한다.   
    - develop.py 에는 기존 장고 프로젝트 생성 시, DB 내용을 적용한다고 하자.  

<br>

- develop.py

```python
from .base import *

ALLOWED_HOSTS = ["*"]

DATABASES = { 
    "default": {
        "ENGINE": "django.db.backends.sqlite3",     
        "NAME": BASE_DIR / "db.sqlite3"
        }
}
```

<br>


- production.py

```python
from .base import *

ALLOWED_HOSTS = ["127.0.0.1"]

DATABASES = { 
    "default": {
        "ENGINE": "django.db.backends.postgresql_psycopg2",     
        "HOST": "ls-django.cvhmktue5rnw.ap-northeast-2.rds.amazonaws.com",
        "NAME": "postgres", 
        "PORT": "5432",
        "USER": "learningspoons",
        "PASSWORD": [비밀번호], 
    }
}
```

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)
