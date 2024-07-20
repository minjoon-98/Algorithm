import sys
input = sys.stdin.readline

N = int(input())

cereal_number = [list(map(str, input().strip())) for _ in range(N)]

def sum_of_digits(s):
    return sum(int(char) for char in s if char.isnumeric())

guitar = sorted(cereal_number, key= lambda x: (len(x), sum_of_digits(x), x)) # 다중 조건 정렬 : lambda식에 tuple로 그 순서대로 작성

for g in guitar:
    print(*g, sep="")
