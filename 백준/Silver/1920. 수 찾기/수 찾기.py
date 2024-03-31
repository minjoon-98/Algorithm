def binary_search(arr, target):
    left, right = 0, len(arr) - 1
    
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return True
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    
    return False

N = int(input())
A = sorted(map(int, input().split()))  # A 배열을 정렬

M = int(input())
B = list(map(int, input().split()))

for num in B:
    if binary_search(A, num):
        print(1)
    else:
        print(0)