from ..client import openai_client

"""
Step 1: 포스팅 정보 가져오기
블로그 포스트를 날짜 순으로 정렬해서 최신 글 3개를 가져와, example 제공해 학습시키기
- 블로그 api 필요. 
- 예시 블로그 어떻게? 

또는 제목과 내용을 딕셔너리 리스트로 저장하기 
"""
import pandas as pd 

df = pd.read_csv('.test')

print(df)



"""
Step 2: 가져온 포스팅 정보와 새로운 주제를 토대로 글 생성하기

"""
messages = [
    {"role": "system", "content": "너는 K-pop 소식이나 문화 행사를 전문으로 글을 쓰는 블로거 assistant야. 반환값은 JSON 형태로 반환할거야"},
    # 여기 중간에 가져온 포스팅 정보 추가해서 context 제공하기
    {"role": "user", "content": f"주제는 땡땡이야. 위 예시 느낌으로 제목과 내용을 만들어줘"},
    {"role": "assistant", "content": f"[Title]타이틀 작성\n[Body]컨텐트 작성"},
    {"role": "user", "content": f"주제는 땡땡이야. 위 예시 느낌으로 제목과 내용을 만들어줘"},
    {"role": "assistant", "content": f"[Title]타이틀 작성\n[Body]컨텐트 작성"},
    {"role": "user", "content": f"주제는 땡땡이야. 위 예시 느낌으로 제목과 내용을 만들어줘"},
    {"role": "assistant", "content": f"[Title]타이틀 작성\n[Body]컨텐트 작성"},
    {"role": "user", "content": f"주제는 땡땡이야. 위 예시 느낌으로 제목과 내용을 만들어줘"},
    ]

response = openai_client.chat.completions.create(
        model="gpt-4o",
        response_format={"type": "json_object"},        
        messages=messages, 
        # max_tokens=1024,
    )

content_in_response = response.choices[0].message.content # json 형태

# content_in_response에서 title과 content 뽑아내어 딕셔너리 형태로 담기
article = {"title": "", "body": ""}


"""
Step 3: 미리 지정한 언어로 번역하기 

"""
source_language = "한국어"
target_languages = ["영어", "일본어", "스페인어"] # 번역 언어
translated_posts = {} # languageA: {titleA: contentA, titleB: contentB, ...}

for target_language in target_languages:
    messages = [
        {"role": "system", "content": f"너는 {source_language}에서 {target_language}로 번역하는 assistant야."},
        # 유저가 전달할 때 [Title]~~ \n[Body]~~ 이런식으로 전달해서 답변도 이렇게 할 수 있도록 하면 좋을 듯 
        {"role": "user", "content": f"제목은 {article['title']}이고, 내용은 {article['body']}야."}
    ]

    response = openai_client.chat.completions.create(
            model="gpt-4o",
            response_format={"type": "json_object"},        
            # temperature=0.1, 
            messages=messages, 
        )

    content_in_response = response.choices[0].message.content

    # 번역된 제목과 내용 뽑아내기
    title_index = content_in_response.index("title")
    body_index = content_in_response.index("body")
    title = content_in_response[title_index + len("title"):body_index]
    body = content_in_response[body_index:]

    if target_language not in translated_posts:
        translated_posts[target_language] = []
    
    translated_posts[target_language].append({title: body})

"""
Step 4: 번역글을 블로그에 올리기 
- 블로그 api 필요
- 사진 필요
"""

# [알고리즘 검증 단계]
# 블로그 medium api를 추천 
# api 종류: tutorials를 사용하기 (chat bot): 일대일 문의 글이 엑셀에 저장되어 있는 상황 (엑셀에 다시 저장하기)
# 블로그 텍스트 길이 및 블로그 글 생성하고 어디에 저장할건지, docs에 저장하는 걸로 
# langchain은 안쓰는 걸로 하고, QA 튜토리얼에서 보여준 정도로 하기. 
# Assistant API는 langchain보다는 쓰기 쉽지만, beta이므로 같이 해보기
# Chat Completions API 를 주로 사용하기. 3