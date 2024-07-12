import sys
input = sys.stdin.readline

sequence = list(map(str, input().strip()))
zeros, ones = sequence.count('0'), sequence.count('1')

half_zeros, half_ones = zeros//2, ones//2

while half_ones:
    sequence.remove('1')
    half_ones -= 1

stack = []
while half_zeros:
    temp = sequence.pop()
    if temp == '1':
        stack.append(temp)
    else:
        half_zeros -= 1

while stack:
    sequence.append(stack.pop())

print("".join(sequence))