def bubble_sort(a):
    n = len(a)
    for i in range(n - 1):
        for j in range(n - 1, i, -1):
            if a[j - 1] > a[j]:
                a[j - 1], a[j] = a[j], a[j - 1]

N = int(input())
arr = [int(input()) for _ in range(N)]

bubble_sort(arr)
for i in range(len(arr)):
    print(arr[i], end='')
    print('')