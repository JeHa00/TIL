import re


def solution_01(data: str) -> bool:
    answers = [char.lower() for char in data if char.isalnum()]

    palindrome = "".join(answers)

    return palindrome == palindrome[::-1]


def solution_02(data: str) -> bool:
    data = re.sub("[^0-9a-z]", "", data.lower())

    return data == data[::-1]


print(solution_02("A man, a plan, a canal: Panama"))
