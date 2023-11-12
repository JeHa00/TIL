from collections import Counter
from typing import List
import re


def main(paragraph: str, banned: List[str]) -> str:
    words = [
        word
        for word in re.sub("[^\w]", " ", paragraph).lower().split()
        if word not in banned
    ]

    """
    split은
    연속된 공백 문자는 단일한 구분자로 간주하고,
    문자열이 선행이나 후행 공백을 포함해도 결과는 시작과 끝에 빈 문자열을 포함하지 않습니다
    """

    return Counter(words).most_common(1)[0][0]
