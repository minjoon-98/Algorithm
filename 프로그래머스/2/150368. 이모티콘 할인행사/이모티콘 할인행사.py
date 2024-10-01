from itertools import product # 카르테시안 곱

def solution(users, emoticons):
    # 할인율 리스트
    discounts = [10, 20, 30, 40]
    best_subscriber_count = 0
    best_revenue = 0

    # 모든 할인 조합 생성
    for discount_combination in product(discounts, repeat=len(emoticons)):  # repeat을 통해 같은 iterable인 discounts를 여러 번 사용하도록 함
        total_revenue = 0
        subscriber_count = 0
        
        for user_rate, user_price in users:
            total_cost = 0
            
            # 인덱스를 사용하여 이모티콘 가격과 할인율에 접근
            # for emoticon_price, discount in zip(emoticons, discount_combination): 이렇게도 가능
            for i in range(len(emoticons)):
                emoticon_price = emoticons[i]
                discount = discount_combination[i]
                
                if discount >= user_rate:
                    # 할인 적용
                    discounted_price = emoticon_price * (100 - discount) // 100
                    total_cost += discounted_price
            
            # 가입 여부 판단
            if total_cost >= user_price:
                subscriber_count += 1
            else:
                total_revenue += total_cost

        # 최적의 결과 업데이트
        if subscriber_count > best_subscriber_count or \
           (subscriber_count == best_subscriber_count and total_revenue > best_revenue):
            best_subscriber_count = subscriber_count
            best_revenue = total_revenue

    return [best_subscriber_count, best_revenue]