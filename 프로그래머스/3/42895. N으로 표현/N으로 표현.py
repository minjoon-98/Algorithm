def solution(N, number):
    if N == number:
        return 1
    
    dp = [set() for _ in range(9)]
    
    for i in range(1, 9):
        # 같은 숫자 반복하는 경우 추가 (N, NN, NNN, ... , NNNNNNNN)
        dp[i].add(int(str(N) * i))
        
        for j in range(1, i): # dp[j]와 dp[i-j]에 있는 모든 수들의 조합으로 만들수 있는 수들을 기록
            for operand_1 in dp[j]:
                for operand_2 in dp[i-j]:
                    dp[i].add(operand_1 + operand_2)
                    dp[i].add(operand_1 - operand_2)
                    dp[i].add(operand_1 * operand_2)
                    if operand_2 != 0:
                        dp[i].add(operand_1 // operand_2)
        
        # 목표 숫자가 있으면 바로 반환 -> 가장 작은 경우부터 반환하게 됨
        if number in dp[i]:
            return i
    
    return -1