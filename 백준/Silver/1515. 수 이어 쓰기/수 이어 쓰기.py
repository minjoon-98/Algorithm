from collections import deque
import sys
input = sys.stdin.readline

numbers = deque(list(map(int, str(input()).strip())))

while numbers:
    for current in range(1, pow(10, 3001)):
        for digit in str(current):
            if not numbers:
                print(answer)
                exit()
            if int(digit) == numbers[0]:
                numbers.popleft()
                answer = current