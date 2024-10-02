import sys
input = sys.stdin.readline

document = input().strip()
target = input().strip()

count = 0
index = 0
target_length = len(target)

while index <= len(document) - target_length:
    if document[index:index + target_length] == target:
        count += 1
        index += target_length
    else:
        index += 1

print(count)