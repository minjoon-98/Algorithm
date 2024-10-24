import sys
import math

input = sys.stdin.readline

K = int(input())

# chocolate_size = math.pow(2, math.ceil(math.log2(K)))
chocolate_size = 2 ** math.ceil(math.log2(K))

count = 0
current_chocolate_size = chocolate_size

if K != current_chocolate_size:
    while K > 0:
        current_chocolate_size //= 2
        if K >= current_chocolate_size:
            K -= current_chocolate_size
        count += 1

print(chocolate_size, count)