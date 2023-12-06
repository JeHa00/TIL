from collections import deque


from typing import ListNode


def solution01(head: ListNode) -> bool:
    if not head:
        return True

    q: deque = deque()

    node = head

    while node:
        q.append(node.val)
        node = node.next

    while len(q) > 1:
        if q.popleft() != q.pop():
            return False

    return True


def solution02(head: ListNode) -> bool:
    rev = None
    slow = fast = head

    while fast and fast.next:
        fast = fast.next.next
        rev, rev.next, slow = slow, rev, slow.next

    if fast:
        slow = slow.next

    while rev and rev.val == slow.val:
        slow, rev = slow.next, rev.next

    return not rev
