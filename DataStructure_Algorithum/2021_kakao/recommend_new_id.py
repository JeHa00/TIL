"""
[신규 아이디 추천]

아이디 규칙
- 3자 이상 15자 이하
- 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 아침표(.) 문자만 사용
- 마침표는 처음과 끝에 사용할 수 없고, 연속으로도 사용할 수 없다. 

입력값 제한사항
- 1 이상 1000이하 문자열
- 알파벳 대문자, 알파벳 소문자, 숫자, 특수문자로 구성
- 특수문자는 -_.~!@#$%^&*()=+[]{}:?,<>/ 로 한정

입력될 id 값
- ...!@BaT#*..y.abcdefghijklm
- z-+.^.
- =.=
- 123_.def
- abcdefghijklmn.p 


7단계의 생성 과정
- 1) new_id의 모든 대문자를 대응되는 소문자로 치환
- 2) 소문자, 숫자, 빼기, 밑줄, 마침표를 제외한 모든 문자 제거
- 3) 마침표가 2번 이상 연속된 부분은 하나의 마침표로 치환
- 4) 마침표가 처음과 끝에 위치하면 제거
- 5) 빈 문자열이면 'a'를 삽입  
- 6) 길이가 16자 이상이면, 첫 15개 문자를 제외한 나머지는 모두 제거. 
    만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
- 7) 길이가 2자 이하라면 new_id의 마지막 문자를 길이가 3이 될 때까지 반복해서 끝에 붙인다.
"""

def check_end_point(id: str) -> str:
    try:
        if id[-1] == '.':
            id = id[:-1]
    except IndexError:
        id += 'a'
    return id


def main(new_id: str) -> str:
    """
    신규 유저가 아이디를 입력하면 내부 과정을 거쳐서 새로운 아이디를 추천하는 함수
    """
    recommend_id = ''
    id_MAX_LENGTH, id_MIN_LENGTH = 15, 3

    # 1) 대문자를 소문자로 치환
    new_id = new_id.lower()    

    # 2) 소문자, 숫자, 빼기, 밑줄, 마침표를 제외한 모든 문자 제거
    for character in new_id:
        if character in '~!@#$%^&*()=+[]{}:?,<>/':
            continue
        else:
            recommend_id += character

    # 3) 마침표가 2번 이상 연속된 부분은 하나의 마침표로 치환
    NOT_FOUND = -1

    # while recommend_id.find('..') != NOT_FOUND:
    while '..' not in recommend_id:
        recommend_id = recommend_id.replace('..', '.')        

    # 4) 마침표가 처음과 끝에 위치하면 제거 
    if recommend_id[0] == '.': 
        # 조건문을 작성할 때는 이에 대한 반례를 같이 생각해보고, 그 반례에서 error가 발생될 것까지 생각해보자.
        recommend_id = recommend_id[1:]

    recommend_id = check_end_point(recommend_id)

    # 5) 길이가 16자 이상이면, 첫 15개 문자를 제외한 나머지는 모두 제거
    if len(recommend_id) > id_MAX_LENGTH: 
        recommend_id = check_end_point(recommend_id[:id_MAX_LENGTH])

    # 6) 길이가 2자 이하라면 new_id의 마지막 문자를 길이가 3이 될 때까지 반복해서 끝에 붙인다.
    elif len(recommend_id) <= id_MIN_LENGTH-1:
        add_char_count = id_MIN_LENGTH - len(recommend_id)
        recommend_id = ''.join([recommend_id, recommend_id[-1] * add_char_count])

    return recommend_id


if __name__ == "__main__":
    new_id_list = ['...!@BaT#*..y.abcdefghijklm', 'z-+.^.', '=.=', '123_.def', 'abcdefghijklmn.p']
    # new_id_list = ['abcdefghijklmn.p']
    for new_id in new_id_list:
        recommend_id = main(new_id)
        print(f"new_id: {new_id} / recommend_id: {recommend_id}")