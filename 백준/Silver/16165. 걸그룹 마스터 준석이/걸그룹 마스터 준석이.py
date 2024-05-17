import sys
input = sys.stdin.readline

N, M = map(int, input().split())
girlgroup = []

for i in range(N):
    group_name = input().strip()
    member_num = int(input().strip())
    members = []
    for _ in range(member_num):
        member_name = input().strip()
        members.append(member_name)
    members.sort()
    girlgroup.append((group_name, members))

for _ in range(M):
    quiz = input().strip()
    quiz_num = int(input().strip())

    if quiz_num == 0:
        for group in girlgroup:
            if group[0] == quiz:
                for member in group[1]:
                    print(member)
    
    elif quiz_num == 1:
        for group in girlgroup:
            if quiz in group[1]:
                print(group[0])