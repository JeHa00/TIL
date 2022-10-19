# 0. Introduction 

> 1. [Static file Serving](#static-file-serving)  
> 2. [RDS 연결하기](#rds-연결하기)


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




<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)
