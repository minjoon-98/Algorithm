def solution(A, B):
    # A와 B의 길이가 다르면 -1 반환
    if len(A) != len(B):
        return -1
    
    # A와 B가 같다면 밀지 않아도 되므로 0 반환
    if A == B:
        return 0
    
    n = len(A)

    # A를 밀면서 B와 비교
    for i in range(1, n):
        # 오른쪽으로 밀기
        A = A[-1] + A[:-1]
        
        # 밀린 A와 B 비교
        if A == B:
            return i  # 몇 번 밀었는지 반환
    
    return -1  # 밀어서 B가 될 수 없는 경우