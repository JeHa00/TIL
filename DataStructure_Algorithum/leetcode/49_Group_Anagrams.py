from collections import defaultdict
from typing import List

input_data = (
    ["eat", "tea", "tan", "ate", "nat", "bat"],
    [""],
    ["a"],
)


def main(strs: List[str]) -> List[List[str]]:
    words_per_grouping_key = defaultdict(list)

    for word in strs:
        key = "".join(sorted(word))
        words_per_grouping_key[key].append(word)

    return words_per_grouping_key.values()


if __name__ == "__main__":
    for strs in input_data:
        main(strs)
