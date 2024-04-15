M, N, L = map(int, input().split())
stages = sorted(map(int, input().split()))  # 정렬된 stages 리스트

animals = [tuple(map(int, input().split())) for _ in range(N)]

count = 0

for animal in animals:
    min_val = animal[0] + animal[1] - L
    max_val = animal[0] - animal[1] + L
    start = 0
    end = M - 1
    while start <= end:
        mid = (start + end) // 2
        if min_val <= stages[mid] <= max_val:
            count += 1
            break  # 해당 조건을 만족할 때만 count를 증가시키고 루프를 종료
        elif stages[mid] < min_val:
            start = mid + 1
        else:
            end = mid - 1

print(count)