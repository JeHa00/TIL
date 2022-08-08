def bf_match(txt: str, pat: str) -> int:
    """
    브루트 포스법으로 문자열 검색
    """

    pt = 0

    pp = 0

    while pt != len(txt) and pp != len(pat): 
        if txt[pt] == pat[pp]: # ABCCDEFG / # CDE
            pt += 1 # 4
            pp += 1 # 2
        else:
            pt = pt - pp + 1 # 5로 설정
            pp = 0 # 0으로 설정 




if __name__ == "__main__":
    s1 = input("text를 입력하라.: ")
    s2 = input("pattern을 입력하라.: ")

    idx = bf_match(s1, s2)

    if idx == -1:
        print("텍스트 안에 패턴을 존재하지 않는다.")

    else:
        print(f"text의 {(idx + 1)}번째 문자부터 일치한다.")
