def solution(weights):
    answer = 0
    weight_dict = {}  # 무게별 개수를 저장할 딕셔너리

    # weights 리스트를 순회하며 무게별 개수를 세어 딕셔너리에 저장
    for weight in weights:
        if weight in weight_dict:
            weight_dict[weight] += 1
        else:
            weight_dict[weight] = 1

    # 동일한 무게에 대한 시소 짝꿍 계산
    for weight in weight_dict:
        count = weight_dict[weight]
        if count > 1:
            answer += count * (count - 1) // 2  # nC2 조합을 사용하여 짝꿍 수 계산

    # 서로 다른 무게에 대한 시소 짝꿍 계산
    for weight in weight_dict:
        for ratio in [2, 3/2, 4/3]:
            partner_weight = weight * ratio
            if partner_weight in weight_dict:
                answer += weight_dict[weight] * weight_dict[partner_weight]

    return answer