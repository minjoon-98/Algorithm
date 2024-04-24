import sys

stack1 = list(sys.stdin.readline().strip())
stack2 = []

M = int(sys.stdin.readline())

for _ in range(M):
    command = list(sys.stdin.readline().split())
    if len(command) == 2:
        stack1.append(command[1])
    else:
        if command[0] == 'L':
            if not stack1:
                continue
            else:
                stack2.append(stack1.pop())
        if command[0] == 'D':
            if not stack2:
                continue
            else:
                stack1.append(stack2.pop())
        if command[0] == 'B':
            if not stack1:
                continue
            else:
                stack1.pop()

# answer= "".join(stack1+list(reversed(stack2)))
stack1.extend(list(reversed(stack2)))
print(''.join(stack1))
# print(sep='', *stack1)