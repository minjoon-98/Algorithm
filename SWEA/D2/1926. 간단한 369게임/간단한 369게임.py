def clap_game(N):
    result = []
    
    for i in range(1, N + 1):
        # 숫자를 문자열로 변환하여 "3", "6", "9"의 개수를 셈
        count = sum(1 for digit in str(i) if digit in '369')
        
        if count > 0:
            result.append('-' * count)  # 박수 횟수만큼 '-' 출력
        else:
            result.append(str(i))  # 숫자 그대로 출력
    
    # 출력은 공백으로 구분하여 출력
    print(" ".join(result))

# 입력 받기
N = int(input())

# 게임 실행
clap_game(N)