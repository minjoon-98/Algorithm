T = int(input())
for test_case in range(1, T + 1):
    numbers = list(map(int, input().split()))
    sum_odd = 0
    for number in numbers:
        if number % 2 == 1:
            sum_odd += number
    print(f"#{test_case} {sum_odd}")