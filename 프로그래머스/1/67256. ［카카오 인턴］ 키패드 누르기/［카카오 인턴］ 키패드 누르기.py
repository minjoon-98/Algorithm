def distance(current_hand, number):
        return abs((current_hand-1)//3 - (number-1)//3) + abs((current_hand-1)%3 - (number-1)%3)

def solution(numbers, hand):
    answer = ''
    left, right = 10, 12
    for i in range(len(numbers)):
        if numbers[i] == 1 or numbers[i] == 4 or numbers[i] == 7:
            answer += 'L'
            left = numbers[i]
        elif numbers[i] == 3 or numbers[i] == 6 or numbers[i] == 9:
            answer += 'R'
            right = numbers[i]
        else:
            if numbers[i] == 0:
                numbers[i] = 11
            if distance(left, numbers[i]) == distance(right, numbers[i]):
                if hand == 'left':
                    answer += 'L'
                    left = numbers[i]
                else:
                    answer += 'R'
                    right = numbers[i]
            elif distance(left, numbers[i]) < distance(right, numbers[i]):
                answer += 'L'
                left = numbers[i]
            else:
                answer += 'R'
                right = numbers[i]
    return answer