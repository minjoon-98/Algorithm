def solution(array, commands):
    answer = []
    for i, j, k in commands:
        # i, j를 0-based 인덱스로 조정
        new_array = array[i-1:j]  # 슬라이스: i-1부터 j까지 (j는 포함되지 않음)
        new_array.sort()           # 정렬
        answer.append(new_array[k-1])  # k는 1-based 인덱스이므로 k-1
    return answer
