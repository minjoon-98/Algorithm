from collections import deque
import sys
input = sys.stdin.readline

n, w, L = map(int, input().split())
trucks = deque(map(int, input().split()))
bridge = deque([0] * w)
count = 0

while trucks or bridge:
    if trucks:
        if len(bridge) == w:
            bridge.popleft()
        if len(bridge) < w:
            if trucks and sum(bridge) + trucks[0] <= L:
                bridge.append(trucks.popleft())
            elif trucks and sum(bridge) + trucks[0] > L:
                bridge.append(0)
    else:
        bridge.popleft()
    count += 1

print(count)