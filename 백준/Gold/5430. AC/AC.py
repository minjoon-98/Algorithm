import sys
from collections import deque
input = sys.stdin.readline

T = int(input())

for _ in range(T):
    p = input().strip()
    n = int(input())
    array_str = input().strip()[1:-1]  # 첫 번째와 마지막 문자(대괄호)를 제거

    if array_str:  # 배열의 원소가 있는 경우
        array = deque(map(int, array_str.split(',')))
    else:  # 배열의 원소가 없는 경우
        array = deque()

    is_reversed = False
    error = False

    for command in p:
        if command == 'R':
            is_reversed = not is_reversed
        elif command == 'D':
            if not array:
                print('error')
                error = True
                break
            if is_reversed:
                array.pop()
            else:
                array.popleft()

    if not error:
        if is_reversed:
            array.reverse()
        print('[' + ','.join(map(str, array)) + ']')