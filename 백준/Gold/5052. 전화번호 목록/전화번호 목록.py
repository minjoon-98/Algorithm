import sys
input = sys.stdin.readline

def solution(numbers):
    numbers.sort()
    for i in range(len(numbers) - 1):
        if numbers[i+1].startswith(numbers[i]):
            print("NO")
            return False
    print("YES")
    return True

t = int(input())

for _ in range(t):
    n = int(input())
    numbers = [input().strip() for _ in range(n)]
    solution(numbers)