"""
[전화번호 목록]
전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 
어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성하기
"""
from typing import List

def solution01(phone_book:List) -> bool:
    """어떤 번호가 다른 번호의 접두어로 존재하는지 판단하는 함수

    Args:
        phone_book (List): 전화번호부에 적힌 전화번호를 담은 배열

    Returns:
        bool: false이면 다른 번호의 접두어인 번호가 존재 / true이면 존재하지 않음

    - 해시 방식으로 구현하는 방법이 떠오르지 않아 완전 탐색을 사용  
    - 테스트 20개 중 4개 통과 실패 -> startswith로 바꾼 후, 모두 통과
    - 시간 통과 x
    - deepcopy를 사용할 경우 테스트 20: 4023.52ms, 10.6MB
    - deepcopy를 사용하지 않을 경우 테스트 20: 통과 495.95ms, 10.4MB
    """

    total_length = len(phone_book)
    answer = True

    for i in range(total_length):
        pivot = phone_book[i]
        for phone_number in phone_book: 
            if pivot == phone_number:
                continue
            # elif pivot in phone_number: # 접두어가 아닌 경우도 포함되므로 틀린 케이스가 존재
            elif phone_number.startswith(pivot):
                answer = False

    return answer

def solution02(phone_book:List) -> bool:
    """
    sort를 사용하여 비슷한 문자열끼리 인접하도록 순서를 바꾼다. 
    그후, 인덱스를 하나만 어긋나게 뽑아내어 startswith를 사용하여 접두어인지 유무를 확인한다. 

    모든 테스트를 통과했고, 시간 효율성도 통과한 경우다.

    하지만, 해쉬를 사용하지 않은 방식이다.  
    """

    phone_book.sort() 

    for p1, p2 in zip(phone_book, phone_book[1:]):
        if p2.startswith(p1):
            return False
    return True


def solution03(phone_book:List) -> bool:
    """
    프로그래머스에 아래 코드로 해쉬를 이용한 정석 풀이라고 한다.
    하지만, ["119", "5521194421"]인 경우 True임에도 불구하고 False를 반환하므로 잘못된 예시다.
    """

    # 해쉬화
    hash_phone_book = dict()
    for phone_number in phone_book:
        hash_phone_book[phone_number] = 1
    
    for phone_number in phone_book:
        arr = ''
        for num in phone_number:
            arr += num
            if phone_number in hash_phone_book and arr != phone_number:
                return False


def solution04(phone_book:List) -> bool:
    """
    해쉬와 startswith를 모두 사용하는 방식을 고려하여 알고리즘을 짜는 중
    """


    hashed_phone_book = dict()
    for phone_number in phone_book:
        hashed_phone_book[phone_number] = phone_number
    
    for phone_number in phone_book:
        if phone_number in hashed_phone_book and phone_number != hashed_phone_book[phone_number]:

            pass


if __name__ == "__main__":
    phone_book_list = [["119", "5521194421"], ["119", "97674223", "1195524421"], ["123","456","789"], ["12","123","1235","567","88"]]
    for phone_book in phone_book_list:
        print('-------- solution 01 -----')
        print(solution01(phone_book))
        print('-------- solution 02 -----')
        print(solution02(phone_book))
        print('-------- solution 03 -----')
        print(solution03(phone_book))
        print('-------- solution 04 -----')
        print(solution04(phone_book))
