from typing import List


def main(logs: List[str]) -> List[str]:
    digits, letters = [], []

    for log in logs:
        if log.split()[1].isnumeric():
            digits.append(log)
        else:
            letters.append(log)

    letters.sort(key=lambda x: (x[1], x[0]))

    return letters + digits
