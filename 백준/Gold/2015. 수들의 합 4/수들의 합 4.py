from collections import defaultdict
import sys
input = sys.stdin.readline

N, K = map(int, input().split())
array = list(map(int, input().split()))

prefix_sum = 0
count = 0
prefix_counts = defaultdict(int)
prefix_counts[0] = 1

for i in range(N):
    prefix_sum += array[i]
    
    if (prefix_sum - K) in prefix_counts:
        count += prefix_counts[prefix_sum - K]
    
    prefix_counts[prefix_sum] += 1

print(count)

# K(partial_sum) = prefix_sum[i] - prefix_sum[j]
# prefix_sum[j] = prefix_sum[i] - K(partial_sum)
# 기존 누적합에서 (현누적합 - K(타겟))이 몇 번 등장했는지 count하면 된다


# import sys
# input = sys.stdin.readline

# N, K = map(int, input().split())
# array = list(map(int, input().split()))

# prefix_sum = [0]
# for i in range(N):
#     prefix_sum.append(prefix_sum[i] + array[i])

# count = 0
# for i in range(1, N):
#     for j in range(i):
#         partial_sum = prefix_sum[i] - prefix_sum[j]
#         if partial_sum == K:
#             count += 1

# print(count)