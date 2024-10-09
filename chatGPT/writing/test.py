from dotenv import load_dotenv  
from typing import List
import requests
import os 

load_dotenv() # .env에 있는 모든 환경 변수 가져오기

# 설정
MEDIUM_API_TOKEN = os.getenv("MEDIUM_API_KEY")
headers = {"Authorization": f"Bearer {MEDIUM_API_TOKEN}"}
host = "https://api.medium.com/v1"


def get_user_id(headers) -> str:
    """
    토큰 정보를 바탕으로 유저의 id를 반환한다.
    """
    url = f"{host}/me"
    response = requests.get(url=url, headers=headers)
    data = response.json()["data"]

    try: 
        assert response.status_code == 200
        assert "username" in data
        assert "name" in data
        assert "id" in data

        return data["id"]
    except:
        print(response['detail'])




def get_user_posts(user_id: str):
    """
    user_id에 해당하는 유저의 public 상태의 글들을 조회한다.
    """
    url = f"{host}/users/{user_id}/articles"
    response = requests.get(url=url, headers=headers)
    # data = response.json()['data']
    
    print(dir(response))
    print(response.reason)

    
    # if response.status_code == 200:
    #     try:
    #         assert "title" in data
    #         assert "content" in data
    #         assert "tags" in data
    #         assert "publishStatus" in data
            
    #         return data
    #     except:
    #         print("public 상태에 있는 글이 없습니다.")
    # else:
    #     print(response['detail'])


def create_a_post(
    user_id: str, 
    title: str, 
    content: str, 
    tags: List = None, 
    is_draft: bool = True,
) -> None:

    url = f"{host}/users/{user_id}/posts"

    data = {
        "title": title,
        "contentFormat": "markdown",
        "content": content,
        "canonicalUrl": "",
        "tags": tags,
        "publishStatus": "draft" if is_draft else "public",
    }

    response = requests.post(url, headers=headers, json=data)
    data = response.json()['data']

    if response.status_code == 200:
        pass 
    print(data)


if __name__ =="__main__":
    user_id = get_user_id(headers)
    posts = get_user_posts(user_id)