# 0. Introduction

> 1. [SSH로 접속하기](#1-ssh로-접속하기)
> 2. [GPG로 커밋에 사인하기](#2-gpg로-커밋에-사인하기)

- 해당 내용은 [제대로 파는 Git & GitHub - by 얄코 ](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)을 통해서 공부한 내용입니다.

<br>

- Chapter 14-01 에서는 아래의 것들을 학습한다.  
    - SSH 키를 생성하고, 등록하여, remote 추가 시 HTTP가 아닌 SSH를 사용하는 방법  
    - GPG 키를 생성하고, 등록하여 local에서 작업하여 푸쉬해도 `Verified`가 뜨도록 하는 방법

<br>

---
# 1. SSH로 접속하기



## SSH 프로토콜을 통한 인증


- **_SSH protocol을 통한 인증_**
    - 공개키 암호화 방식을 사용하는 방식으로, username과 토큰 사용할 필요 없다.  
    - 컴퓨터 자체에 키 저장  

- SSH 키 등록하기
    - 계정의 `Settings` - `SSH and GPG keys`  

- 해당 페이지의 가이드 참조  

<br>

### 1) SSH키 존재 여부 확인

- GitHub 계정 > `settings` > `SSH and GPG keys` 

- 터미널(윈도우의 경우, Bash Shell)에서 `~/.ssh`로 이동  
    - `cd ~/.ssh`  

- `id_rsa.pub`, `id_ecdsa.pub`, `id_ed25519.pub` 파일 중 하나 존재 여부 확인

    - `ls`

    - `.pub`은 공개키로 다른 살마에게 알려줘도 되지만, `.pub`이 없는 `id_<numbering>` 파일은 다른 사람에게 알려주면 안된다.  
        - 알려줬다면 두 가지 키 다 제거하고 다시해야 한다. 

- 위 3개 파일 중 하나가 존재한다면 3번으로 이동


<br>

### 2) SSH 키 생성

- 터미널(윈도우의 경우 Bash Shell)에서 키 생성  
    - `ssh-keygen -t ed25519 -C "(이메일 주소)"`



- 원할 시 `passphrase` 입력


- 1번의 과정으로 키 생성 확인  

<br>

### 3) Github에 키 등록


- 공개키 열람하여 복사 
    - `cat ~/.ssh/id_ed25519.pub` 또는 `cat id_ed25519.pub`

- `New SSH Key` 클릭하여 키 이름과 함께 등록  


<br>

### 4) SSH로 사용해보기  

- 원격을 SSH 주소로 변경한 뒤 테스트  

<br>

---
# 2. GPG로 커밋에 사인하기

> **_GPG 키를 통한 검증_**


### GitHub 커밋 내역 살펴보기 

- 로컬에서 푸시한 커밋과 GitHub에서 작성한 커밋 비교  
    - GitHub repository에 들어가서 `code` tab에서 들어갈 수 있는 커밋 내역에 들어가면 `Verified`를 확인할 수 있다.  


- `Verified`: 신뢰할만한 출처에서 커밋되었다는 인증
    - local이 아닌 GitHub에서 수정한 커밋을 의미하는 것으로, 즉 GitHub이 인증해주는 것이다.  


<br>

## GPG 사용

### 1) GPG tool 설치

> GPG를 사용하기에 앞서 GPG tool을 먼저 설치해야 한다.   


- 윈도우: [다운로드 사이트](https://www.gnupg.org/download/)
- 맥: `brew install gnupg`
- `gpg --version`으로 확인


<br>

### 2) GPG 키 생성


- GPG가 존재하는지 판단하기
    - 참고문서: [Existing GPG keys](https://docs.github.com/en/authentication/managing-commit-signature-verification/checking-for-existing-gpg-keys)
    - `Git bash`에 `gpg --list-secret-keys --keyid-format=long` 입력  

- [이 링크의 가이드](https://docs.github.com/en/authentication/managing-commit-signature-verification/generating-a-new-gpg-key)에 따라 진행


<br>

### 3) New GPG key 클릭하여 등록


- [이 링크의 가이드](https://docs.github.com/en/authentication/managing-commit-signature-verification/telling-git-about-your-signing-key)에 따라 진행
    - 위 링크에 따라 진행하다가 `Enter your user ID information`에서 ID를 입력할 때는, GitHub에서 오른쪽 위 메뉴를 클릭할 때 뜨는 ID를 입력한다. 
    - 이메일도 GitHub 이메일과 동일한 것을 입력한다. 
    - `comment:` 시에는 그냥 엔터를 눌러도 된다.  

    - 끝나면 `gpg --list-secret-keys --keyid-format=long`을 입력한 후, 위 메뉴얼에 따라 GPG key ID 부분을 복사해서 따로 기록해둔다. 
        - `6D040741D60FEB1E`


    - `gpg --armor --export <GPG key ID>`를 입력한다.
        - `----BEGIN PGP PUBLIC KEY BLOCK-----` 부터 `-----END PGP PUBLIC KEY BLOCK-----` 까지 복사한다. 

    - 이제 GPG key를 GitHub 계정에 추가한다. 


❗ 맥의 경우, 추가 절차(환경 변수) 있음  

- 그 다음으로, [Telling Git about your GPG key](https://docs.github.com/en/authentication/managing-commit-signature-verification/telling-git-about-your-signing-key) 을 따라 진행한다.
    - `git config --global user.signingkey 6D040741D60FEB1E`


<br>

### 4) 사인하기  


- 커밋에 사인: 명령어에 `-S` 옵션 추가  

    - `git commit -S -m '(message)'`  
    - 위 명령어를 실행하면 GPG key 등록과정에서 입력한 `Passphrase`를 입력한다.  
    - `git push`하여 GitHub commit 내역을 확인해보면 local에서 작업했음에도 불구하고 `Verified` 를 확인할 수 있다. 

- tag에 사인: 명령어에 `-s` 옵션 추가 

    - `git tag -s (태그명) (커밋 해시) -m (메시지)`   
    - `git tag -s 3.0.0 -m 'Tag sign'` 한 후, `git push --tags` 를 입력하여 GitHub의 Tags를 확인하면 `Verified`를 확인할 수 있다.  


<br>

---

# Reference

- [제대로 파는 Git & GitHub - by 얄코](https://www.inflearn.com/course/%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%8C%8C%EB%8A%94-%EA%B9%83/dashboard)
