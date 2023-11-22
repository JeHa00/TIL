from typing import List


def main(logs: List[str]) -> List[str]:
    digits, letters = [], []

    for log in logs:
        if log.split()[1].isnumeric():
            digits.append(log)
        else:
            letters.append(log)

    # [1:] 로 하는 이유는 문제에서 문자란 식별자를 제외한 문자를 의미하기 때문이고, [1]로 하면 1이 같고 2부터 순서를
    # 판가름할 수 있지만 [1] 로 끊어졌기 때문에 판단하기가 어렵다.
    letters.sort(key=lambda x: (x.split()[1:], x.split()[0]))

    return letters + digits
