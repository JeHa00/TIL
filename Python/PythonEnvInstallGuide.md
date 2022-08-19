# 개발 환경 설정

> 1. [Python 설치](#1-python-설치)
> 1-1. [Python project set up in VSCode](#11-python-project-set-up-in-vscode)
> 1-2. [Python project set up in Pycharm](#12-python-project-set-up-in-pycharm)
> 2. [conda 가상환경 설치하기](#2-conda-가상환경-설치하기)
> 3. [conda 명령어 정리](#3-conda-명령어-정리)


<br>


-  [amamov](https://github.com/amamov)님이 정리한 자료를 바탕으로 내 자료와 합쳐서 올린다. 

- 파이썬을 설치하고, 파이썬 가상환경을 설치하는 명령어와 순서를 정리했다.  


<br>

---

# 1. Python 설치

- _*Python과 pip을 설치하는 방법 외에는 Window OS와 설치 방법은 같습니다..*_

- [python 설치 for Window OS](https://www.python.org/)


<br>

## MAC - 공식 사이트에서 다운

1. [python 설치](https://www.python.org/)

2. MacOS 같은 경우 기본적으로 python 2.7 버전이 내장되어 있다. 따라서 설치 후에 별칭을 붙여주어야 한다.

3. `$ vi ~/.zshrc`으로 설정 파일을 열고 `i`로 편집이 가능하도록 한 후에 맨 아래에 다음 명령어 두 줄을 추가한다.
   - `alias python=python3`
   - `alias pip=pip3`

4. `:!wq`으로 저장하고 나간다.

5. 터미널을 껏다가 다시 연다.


<br>

## MAC - homebrew 사용

### 1. homebrew 설치

- [homebrew 공식 사이트에서 설치](https://brew.sh/index_ko)
- homebrew 명령어 정리
    - `$ brew -v` : brew 버전을 알 수 있다.
    - `$ brew update` : brew 설치


<br> 

### 2. python 설치

1. `$ brew install python3`


2. 파이썬 버전 확인: `python --version` in Window

3. `$ python3` : 설치된 파이썬 명령 쉘을 실행한다.
    - 설치 확인
    - `exit()`으로 나올 수 있다.

4. 파이썬이 어떤 경로에 설치되어 있는지 알려주는 명령어
    - `where python` in Window
    - `which python` in Mac / Linus

<br>

### 3. pip 설치 (윈도우는 python을 다운받아 설치할 때 이미 설치되어 있습니다.)

❗❗ `pip` 명령 대신에 `python -m pip` 명령을 사용하면, 가상 환경을 쓸 때 엉뚱한 인터프리터/가상환경에 패키지가 설치되는 이슈를 없앨 수 있다고 한다.   정보 출처: [Why you should use 'python -m pip'](https://snarky.ca/why-you-should-use-python-m-pip/)



1. `$ sudo easy_install pip` : pip을 설치한다.
2. `$ pip --version` : 설치된 pip 버전을 확인할 수 있다.

- **pip 명령어 정리**. 
    - `$ pip install pip --upgrade` : pip 업그레이드
    - `$ pip install "패키지~=3.0.0"` : 3.0.0 버전의 패키지를 설치한다.
    - `$ pip install 패키지` : 패키지를 설치한다.
    - `$ pip --version` : 설치된 pip 버전을 확인할 수 있다.
    - `$ pip freeze` : 설치된 패키지를 확인할 수 있다.
    - `$ pip freeze > requirements.txt` : requirements.txt파일에 설치된 패키지를 출력한다.
    - `$ pip install -r requirements.txt` : requirements.txt파일에 기록된 패키지를 설치한다.

<br>

### 4. 가상환경 설치 (conda, venv, pipenv 중 하나 선택)

- [conda 가상환경 사용하기](#2-conda-가상환경-설치하기)

- [venv 사용하기](https://docs.python.org/ko/3/library/venv.html)
    - 가상환경 생성: `python -m venv <가상환경 이름>`
    - 가상환경 활성화: `source venv/bin/activate`

- pipenv 가상환경 사용하기 :`$ pip install pipenv`

- pipenv 명령어 정리

    - `$ pip install pipenv` : pipenv 설치
    - `$ pip install pipenv --upgrade` : pipenv 업그레이드
    - `$ pipenv --python 3.8` : 가상환경을 만든다.
    - `$ pipenv shell` : 가상환경 안으로 들어간다.
    - `$ pipenv run python3 my_code.py` : 가상환경에서 코드 실행하기
    - `$ alias prp="pipenv run python3"` : 코드 단축어 별칭
    - `$ pipenv install` : 협업 프로젝트를 할때, 프로젝트의 모든 개발자들은 Git 저장소에 올려둔 Pipfile 파일과 Pipfile.lock 파일을 내려받은 후에  pipenv install 커맨드로 모든 패키지를 한 번에 설치할 수 있다.
    - `$ pipenv graph` : 프로젝트에 설치된 패키지들을 트리 구조로 시각화하여 보여준다.
    - `$ pipenv --where` : Output project home information.
    - `$ pipenv check` : 보안 취약점이 있는 패키지가 설치되어 있는지 간단하게 체크 가능
    - `$ pipenv --rm` : 가상환경 제거
    - [pipenv 명령어 공식 문서](https://pipenv.pypa.io/en/latest/cli/#cmdoption-pipenv-rm)

<br>

### 5. 프로젝트 폴더 만들기

1. `$ cd documents` (터미널을 열고 입력) : documents 폴더 안으로 들어간다.
2. `$ mkdir my_project` : my_project 폴더를 생성한다.
3. `$ cd my_project` : my_project 폴더 안으로 들어간다.

<br>

### 6. 가상환경 만들고 들어가기

- [venv 사용](https://docs.python.org/ko/3/library/venv.html)

- **pipenv 사용**
    1. `$ pipenv --python 3.8` : 가상환경을 만든다.
    2. `$ pipenv shell` : 가상환경 안으로 들어간다.


- **conda 사용**

    1. `$ conda create --name 가상환경이름 python=파이썬버전` : 가상환경을 만든다.
    - 위에 생성한 프로젝트 폴더와 가상환경 이름을 동일하게 사용한다. 
    2. 가상환경 활성화: `$ conda activate 가상환경이름` 을 입력하여 가상환경 안으로 들어간다.
        - `conda activate 가상환경이름` in Window
     
    3. 가상환경 비활성화: `conda deactivate` in Window

<br>

---

## 1.1 Python project set up in VSCode

[6. 가상환경 만들고 들어가기](#6-가상환경-만들고-들어가기)로부터 이어서 진행하며, VScode에서 진행하는 version이다.

### 7. vs-code에서 interpreter & linter 선택 & 설치

1. 생성한 `my_project` 폴더를 vs-code에서 연다.
2. `shift + command + p` --> `python : select interpreter` --> 생성한 가상환경 선택
3. `shift + command + p` --> `python : select linter` --> flake8 선택&설치

- linter은 작성한 코드에 에러가 생길 부분을 미리 감지한다.
- 해당 폴더에 .vscode 폴더가 생성된 것을 확인할 수 있다.

<br>

### 8. formatter-black : - 코드를 보기좋게 format해준다

[pep8](https://peps.python.org/pep-0008/)에 맞게 format 해준다.


- **pipenv 사용**
    - `$ pipenv install black --dev --pre`


- **conda 사용, venv 사용**
    - `$ pip install -U black`


- 또는 `autopep8`을 사용한다. 
    - `pip install -U autopep8`


🔅 formatter의 여러 종류에 관해 블로그 포스팅한 글: [파이썬 코드 스타일 이야기 - Style Checker, Formatter들 구경하기](https://velog.io/@city7310/%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%BD%94%EB%93%9C-%ED%8F%AC%EB%A7%A4%ED%84%B0-%EC%9D%B4%EC%95%BC%EA%B8%B0-5wjxdei9iv)

<br>

### 9. .vscode안에 settings.json파일을 다음과 같이 수정한다.

```python
{
"python.linting.pylintEnabled": false,
"python.linting.flake8Enabled": true,
"python.linting.enabled": true,
"python.formatting.provider": "black",
"python.linting.flake8Args": ["--max-line-length=88"]
}
```

<br>

---

## 1.2 Python project set up in Pycharm

- [6. 가상환경 만들고 들어가기](#6-가상환경-만들고-들어가기)로부터 이어서 진행하며, Pycharm에서 진행하는 version이다.

- 진행하기에 앞에서 [Pycharm 설치 링크](https://www.jetbrains.com/pycharm/)를 통해서 Pycharm을 설치한다.  

<br>

### 7. 인터프리터 (가상환경 선택 또는 생성) 설정하고 프로젝트 열기 (폴더 열기)

<br>

### 8. 'black' formatter 설정

- **아나콘다 가상환경에서..**

    1. **(base)** 환경에서 `pip install black`으로 **black** 패키지를 설치한다.
    2. **(base)** 환경에서 `which black`으로 **black** 패키지의 경로를 체크하고 복사한다.
    3. Pycharm에서 `command + ,` (`ctrl + ,`) 단축키로 설정창 열고 `Tools` 탭에서 `File Watchers`에 들어가서 `+`버튼을 클릭한 후 `<custom>` template를 선택한다.
    4. 아래의 캡쳐 사진처럼 설정을 한다. (Program란에 복사한 black 패키지 경로를 설정한다.)


- **pipenv 가상환경에서..**

    1. 프로젝트 가상환경에서 `pipenv install black`으로 **black** 설치한다.
    2. `which black`으로 **black** 패키지의 경로를 체크하고 복사한다.
    3. Pycharm에서 `command + ,` (`ctrl + ,`) 단축키로 설정창 열고 `Tools` 탭에서 `File Watchers`에 들어가서 `+`버튼을 클릭한 후 `<custom>` template를 선택한다.
    4. 아래의 캡쳐 사진처럼 설정을 한다. (Program란에 복사한 black 패키지 경로를 설정한다.)

    - `/Users/amamov/opt/anaconda3/bin/black`
    - `$FilePath$`

<br>

![image](https://github.com/amamov/teaching-type-python-oop/raw/main/00%20%EC%B2%AB%20%EC%8B%9C%EC%9E%91/image_for_markdown/pycharmblack.png)

<br>

### 9.'prettier' 설정

1. `node`를 설치한다.
2. `yarn`을 설치한다.
3. `yarn global add prettier`으로 prettier 패키지를 설치한다.
4. `which prettier`으로 prettier 패키지 경로를 체크하고 복사한다.
5. Pycharm에서 `command + ,` (`ctrl + ,`) 단축키로 설정창 열고 `Tools` 탭에서 `File Watchers`에 들어가서 `+`버튼을 클릭한 후 `<custom>` template를 선택한다.
6. 아래의 캡쳐 사진처럼 설정을 한다. (Program란에 복사한 prettier 패키지 경로를 설정한다.)
    - `/usr/local/bin/prettier`
    - `--write $FilePathRelativeToProjectRoot$`
    - `$FilePathRelativeToProjectRoot$`
    - `$ProjectFileDir$`


<br>


---

<br>

# 2. conda 가상환경 설치하기

### Anaconda

- python 공식 배포판보다 개발용 머신에는 Anaconda Python을 추천하는 이유는 PyPi보다 다양한 C/C++ 라이브러리 바이너리를 아나콘다 측에서 배포한다.

- 일반적인 파이썬 가상환경보다 강력하다.

- 실제 웹 서비스에서는 대개 리눅스 운영체제를 사용한다. 이 때는 운영체제 배포판에서 제공해주는 파이썬을 사용한다.

### Jupyter Notebook

- Jupyter Notebook은 오픈 소스 web API으로 라이브 코드, 시각화와 설명을 위한 텍스트 등을 포함한 문서를 만들고 공유하도록 할 수 있다. 주로 데이터 클리닝과 변형, 수치 시뮬레이션 통계 모델링, 머신 러닝 등에 사용할 수 있다.

- Jupyter Notebook은 Python, R 등 데이터 과학 분야와 관련된 여러 프로그래밍 언어를 지원하며 이메일, 드롭박스, 깃허브 등으로 공유할 수도 있다. 또한, Jupyter Notebook은 실시간으로 데이터를 조작하고 시각화할 수 있도록 해준다.

<br>

---

❗ 본 문서는 MAC OS 기반으로 작성되었습니다. 

Window OS 기반으로 설치해야할 경우 아래의 링크를 따라서 설치하면 됩니다.

[everyday-image-processing 블로그 - 아나콘다 설치하기 - 1](https://everyday-image-processing.tistory.com/61?category=870161)

[everyday-image-processing 블로그 - 아나콘다 설치하기 - 2](https://everyday-image-processing.tistory.com/66?category=870161)

<br>

### Anaconda 설치

1. Anaconda를 설치한다. [링크](https://www.anaconda.com/download/)

2. `"command + 스페이스 바"`를 눌러서 `"Spotlight"`를 실행하고 Spotlight에 `"Anaconda Navigator"`를 검색하고 실행한다.

3. 실행하고 Home에서 Jupyter Notebook을 선택한다.

<br>

### Anaconda 가상환경 만들기

가상환경이란 한 컴퓨터에서 여러 프로젝트를 작업할 때 파이썬 패키지의 의존성이 충돌하지 않도록 관리해주는 툴이다. 가상환경을 생성하면 환경변수 그룹이 만들어지고 그룹마다 지정된 경로에 패키지를 설치하고 참고하게 된다. 여러 프로젝트를 개발할 때 가상환경을 사용하면 패키지(라이브러리)의 버전 관리가 용이해진다.

기본적으로 아나콘다를 설치하게 되면 "base"라는 가상 환경이 자동적으로 생성되고 아나콘다에 접속할 때마다 해당 가상환경(base)으로 먼저 들어가게 된다. 하지만 원하는 작업 마다 각각 다른 가상환경에서 작업을 진행하기 위해 또 다른 가상환경을 생성하고 제거하는 방법을 알아야 한다.

<br>

1. `"command + 스페이스 바"`를 눌러서 `"Spotlight"`를 실행하고 Spotlight에 **`터미널`**을 검색하고 실행한다.


<br>

2. 터미널에 `"conda update conda"`라는 명령어를 입력하고 엔터를 누르면 최신 버전의 conda로 업데이트 해준다.
   (명령어를 입력하고 `[y/n]`이 보이면 y를 선택하면 된다.)

    ```yml
    $ conda update conda
    ```

<br>

3. 가상환경을 만들기 위해 `"conda create -n 가상환경이름"`라는 명령어를 입력하고 엔터를 누른다.

    ```yml
    $ conda create -n 가상환경이름
    ```

예를들어, 만들고 싶은 가상환경이름이 test라면 `"conda create test"`라고 입력하면 된다.



4. 실제로 만들어진 가상환경들을 확인하는 명령어인 `"conda env list"`를 사용하면 가상환경 test가 생성된 것을 확인할 수 있다.

    ```yml
    # 만들어진 가상환경 리스트를 확인할 수 있다.
    $ conda env list
    ```

<br>

5. 생성한 특정 가상환경으로 들어가려면 `"conda activate 들어가고 싶은 가상환경 이름"`라는 명령어를 사용한다. 예를들어, 3번 과정에서 만든 가상환경인 test에 들어가고 싶으면 `"conda activate test"`를 입력하고 엔터를 누르면 된다. 그러고 나면, (base)가 (test)로 바뀐 것을 알 수 있다. 그러면 가상환경 test에 들어간 것이다.

    ```yml
    # 특정 가상환경에 들어갈 수 있다.
    $ conda activate 들어가고 싶은 가상환경 이름
    ```

<br>

6. 해당 가상환경을 주피터와 연동하는 작업이 필요하다. 이를 위해서 ipkernel이라는 라이브러리가 필요하기 때문에 5번과정에서 들어온 가상환경 안에서 `"conda install ipkerner"`을 입력하고 엔터를 누르자.

    ```yml
    # 외부 라이브러리를 설치할 수 있다.
    $ conda install 설치할 라이브러리
    ```

<br>

7. `"conda list"`라는 명령어를 통해서 conda install로 설치된 라이브러리의 목록을 확인할 수 있다. `"conda list"`명령어를 입력하고 엔터를 누르면 6번 과정에서 설치한 ipkernel을 확인할 수 있다.

    ```yml
    # conda install로 설치된 라이브러리의 목록을 확인할 수 있다.
    $ conda list
    ```

<br>

8. `python -m ipykernel install --user--name 가상환경이름 --display-name "[가상환경이름]"`라는 명령어를 이용하자. 이 명령어의 의미는 '주피터 노트북에서 커널을 선택해서 사용할 때 표시될 커널의 가상환경 이름을 `[가상환경이름]`으로 정하자'라는 것이다. 이것은 나중에 주피터 노트북을 열어서 사용할 때 확인할 수 있다. 이렇게 하면 주피터 노트북의 커널 연동까지 성공한 것이다.


```yml
$ python -m ipykernel install --user --name 가상환경이름 --display-name "[가상환경이름]"
```

<br>

9. base로 돌아가기 위해 현재 가상환경을 종료하는 `"conda deactivate"`명령어 입력하고 엔터를 누른다.

    ```yml
    # base로 돌아가기 위해 현재 가상환경을 종료한다.
    $ conda deactivate
    ```

<br>

10. 이제 `"jupyter notebook"`을 입력해서 주피터 노트북을 실행해 준다.

```yml
$ jupyter notebook
```


우측 상단의 new를 클릭하면 앞으로 사용하고자 하는 가상환경이 있는 것을 확인할 수 있다. 해당 커널을 통해서 특정 작업을 수행할 수 있다.

<br>


<br>

---

# 3. conda 명령어 정리

### 1. 정보 확인하기

```yml
# 아나콘다 버전 확인하기.
$ conda --version
```

```yml
# 설치된 아나콘다 정보 조회
$ conda info
```

```yml
# 가상환경 리스트 조회
$ conda env list
```

```yml
# 현재 사용중인 가상환경 확인하기.
$ conda info --envs
```

```yml
# conda 업데이트 하기
$ conda update -n base conda
```

<br>

### 2. 가상환경 생성

```yml
# 새 가상환경 만들기
# ex) Window version: conda create --name askcompany pyhon=3.9
$ conda create --name 가상환경이름 python=파이썬버전
```

```yml
# 가상환경 복제
$ conda create -clone 원본_가상환경_이름 -n 새_가상환경이름
// ex) conda create --clone test -n test2
```

<br>

### 3. 가상환경 활성화

```yml
# 가상환경 활성화
$ conda activate 가상환경이름
```

<br>

### 4. 가상환경 비활성화 및 삭제하기

```yml
# 가상환경 삭제하기

#  현재 사용중인 가상환경이라면, 비활성화 하기
$ conda deactivate

#  가상환경 삭제하기
$ conda env remove --name 가상환경이름
```

<br>

### 5. 가상환경에서 패키지 설치, 업데이트, 삭제

```yml
# 가상환경에 들어간다.
$ conda activate 가상환경

#  패키지 설치
# ex) conda install tensorflow
$ conda install 패키지이름

#  패키지 업데이트
$ conda update 패키지이름

#  설치된 패키지 삭제
$ conda remove 패키지이름
```

```yml
# 특정 가상환경에서 패키지를 설치
$ conda install -n 가상환경이름 패키지이름

#  특정 가상환경에서 설치된 패키지 삭제
$ conda remove -n 가상환경이름 패키지이름
```

<br>

### 6. Jupyter Notebook 명령어

-  Jupyter Notebook 실행하기

    ```yml

    #  해당 가상환경에 들어간다.
    $ conda activate 가상환경이름

    #  jupyter 설치
    $ conda install jupyter

    // jupyter notebook 실행
    $ jupyter notebook
    ```

- 주피터에서 kernel list 확인하기

    ```yml
    # 주피터에 kernel list 확인하기

    $ jupyter kernelspec list
    ```


- Jupyter Notebook kernel 추가하기

    ```yml
    #  가상환경 들어간다.
    $ conda activate 가상환경이름

    #  ‘ipykernel’ 라이브러리 다운로드
    $ conda install ipykernel

    // 커널 추가하기
    $ python -m ipykernel install --user --name 가상환경이름 --display-name "[가상환경이름]"
    ```

- Jupyter Notebook kernel 제거하기

    ```yml
    $ jupyter kernelspec uninstall 가상환경이름
    ```


<br>


---

# Reference

- [amamov](https://github.com/amamov)  
