def solution(dartResult):
    # 점수를 저장할 리스트
    scores = []
    index = 0
    
    # 문자열을 파싱하여 점수, 보너스, 옵션을 추출
    while index < len(dartResult):
        # 점수 읽기
        score_start = index
        while index < len(dartResult) and dartResult[index].isdigit():
            index += 1
        score = int(dartResult[score_start:index])
        
        # 보너스 읽기
        bonus = dartResult[index]
        index += 1
        
        # 옵션 읽기 (있는 경우)
        option = ''
        if index < len(dartResult) and (dartResult[index] == '*' or dartResult[index] == '#'):
            option = dartResult[index]
            index += 1
        
        # 보너스에 따른 점수 계산
        if bonus == 'S':
            score = score ** 1
        elif bonus == 'D':
            score = score ** 2
        elif bonus == 'T':
            score = score ** 3
        
        # 옵션 적용
        if option == '*':
            if scores:
                scores[-1] *= 2  # 이전 점수 두 배
            score *= 2  # 현재 점수 두 배
        elif option == '#':
            score *= -1  # 현재 점수 음수로 변환
        
        # 점수를 리스트에 추가
        scores.append(score)
    
    # 최종 점수 합계 계산
    return sum(scores)