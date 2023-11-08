from typing import List

input_data = [
    "babad",
    "aasedddefff",
    "cbbd",
]


def main(s: str) -> str:
    def find_long_palindrome(left: int, right: int) -> str:
        while 0 <= left and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1

        return s[left + 1 : right]

    if len(s) == 1 or s == s[::-1]:
        return s

    result = ""

    for i in range(len(s)):
        result = max(
            result,
            find_long_palindrome(i, i + 1),
            find_long_palindrome(i, i + 2),
            key=len,
        )

    return result


if __name__ == "__main__":
    for word in input_data:
        print(main(word))
