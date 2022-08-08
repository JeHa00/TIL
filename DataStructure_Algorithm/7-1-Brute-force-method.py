def bf_match(txt: str, pat: str) -> int:
    """
    브루트 포스법으로 문자열 검색
    """

    pt = 0  # text의 index

    pp = 0  # pattern의 index

    while pt != len(txt) and pp != len(pat):  # 병렬 조건 만족 시 계속 반복
        if txt[pt] == pat[pp]:  # 원소 값이 동일하면 증가한다.
            pt += 1  # 4
            pp += 1  # 2
        else:
            pt = pt - pp + 1  # 5로 설정

            """
            여기서 pp를 빼는 이유는 다음과 같다. 
            pp는 표면적으로는 pattern의 index 역할이지만, 담겨있는 또 다른 의미는
            text와 pattern을 비교할 때, text의 index 값이 커진 값을 의미한다. 
            그래서 다시 pp = 0 과 비교하기 위해서는 pp를 빼야 한다. 
            """

            pp = 0  # 0으로 설정


if __name__ == "__main__":
    s1 = input("text를 입력하라.: ")
    s2 = input("pattern을 입력하라.: ")

    idx = bf_match(s1, s2)

    if idx == -1:
        print("텍스트 안에 패턴이 존재하지 않는다.")

    else:
        print(f"text의 {(idx + 1)}번째 문자부터 일치한다.")
