import sys
input = sys.stdin.readline

N = int(input())

test_numbers = list(int(input()) for _ in range(N))

zeros_ones = {0:(1, 0), 1:(0, 1)}
for i in range(2, max(test_numbers)+1):
    zeros_ones[i] = tuple(sum(elem) for elem in zip(zeros_ones[i-2], zeros_ones[i-1]))

for test_num in test_numbers:
    print(*zeros_ones[test_num])