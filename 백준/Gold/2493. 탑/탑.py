import sys

N = int(sys.stdin.readline())
towers = list(map(int, sys.stdin.readline().split()))
stack = []
answer = [0] * N  # 초기에 모든 타워의 신호를 수신할 수 없는 경우를 가정합니다.

for i, tower in enumerate(towers):
    while stack:
        if tower < stack[-1][1]:
            answer[i] = stack[-1][0] + 1  # 현재 타워가 수신 가능한 타워의 인덱스를 기록합니다.
            break
        else:
            stack.pop()
    stack.append((i, tower))  # 현재 타워를 스택에 추가합니다.

print(*answer)