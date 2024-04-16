import sys

def cycle(N, count=0, original=None):
    if original is None:
        original = N
    count += 1
    left = N // 10
    right = N % 10
    plus = (left + right) % 10
    newNum = (N % 10) * 10 + plus
    if newNum == original:  # 최초의 값과 일치하면 카운트 값을 반환
        return count
    return cycle(newNum, count, original)

N = int(sys.stdin.readline())
print(cycle(N))