def solution(n, words):
    used_words = set()  # 사용된 단어를 저장하는 집합
    last_word = ''      # 마지막으로 말한 단어

    for i, word in enumerate(words):
        player_number = (i % n) + 1  # 현재 플레이어 번호
        turn_count = (i // n) + 1     # 현재 플레이어의 차례 수

        # 단어가 유효한지 확인
        if (last_word and word[0] != last_word[-1]) or word in used_words:
            return [player_number, turn_count]

        # 마지막 단어 업데이트 및 사용한 단어 목록에 추가
        last_word = word
        used_words.add(word)

    return [0, 0]  # 탈락자가 없을 경우