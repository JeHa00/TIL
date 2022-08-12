"""
[problem: 1157]

- 문제
    - 소문자와 대문자가 섞인 영단어가 주어지면 대소문자 구분 없이 동일한 알파벳으로 취급하여, 가장 많은 횟수를 가진 알파벳을 출력하기
    - 하지만, 알파벳이 2개 이상이면 '?'를 출력한다. 

- 출력 제한
    - 대문자로 출력하거나 '?' 로 출력

- 대문자로 출력해야하기 때문에, .upper()를 사용했으며 검색할 단어를 대문자화한 후 그대로 사용하는 것보다 set을 통해서 중복을 없앤 후, list로 전환하여
나중에 count와 max를 사용하고 알파벳 별 횟수를 인덱스로 일치시키기 위해 sequence가 있는 data type을 사용한다. 

"""


def main():
    capitalized_text = input().upper()  # 검색될 단어를 대문자하여 할당한다.
    pattern = list(set(sorted(capitalized_text)))

    """
    대문자화한 검색될 단어를 최소 단위로 쪼갠 뒤, set을 사용하여 중복을 없앴다.  
    마지막으로 알파벳과 횟수의 index를 일치시키기 위해서 list data type으로 만든다. 
    """

    alphabet_count = []
    for p in pattern:
        alphabet_count.append(capitalized_text.count(p))

    """
    pattern과 index를 일치시키고, 나중에 max와 count를 지속적으로 사용하기 위해서 alphabet_count를 list로 만든다. 
    """

    max_count = max(alphabet_count)

    """
    가장 많이 있는 알파벳의 횟수를 max_count 변수에 할당한다. 
    """

    if alphabet_count.count(max_count) == 1:
        max_count_alphabet = alphabet_count.index(max_count)
        print(pattern[max_count_alphabet])

    else:
        print("?")


if __name__ == "__main__":
    main()
