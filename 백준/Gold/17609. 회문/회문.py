import sys
input = sys.stdin.readline

def isPalindrome(string):
    return string == string[::-1]

def isPseudoPalindrome(string):
    left, right = 0, len(string) - 1
    while left < right:
        if string[left] == string[right]:
            left += 1
            right -= 1
        else:
            # 두 문자가 다를 경우 두 가지 경우를 확인
            skip_left = string[left + 1 : right + 1]    # 왼쪽 문자 건너뛰기
            skip_right = string[left : right]           # 오른쪽 문자 건너뛰기
            return isPalindrome(skip_left) or isPalindrome(skip_right)
    
    # while문이 끝난 경우 반환하지 않았다는건 전체 문자열이 회문일 경우
    # 전체 문자열이 회문인 경우, 유사 회문이면 True 그렇지 않으면 False를 반환해야함
    # commoc는 회문이지만 유사 회문은 아님 commmoc는 회문이자 유사 회문임
    # 이 문제에서는 회문이자 유사회문인 경우는 신경 안써도 무방하지만
    # 함수의 정의적 관점에서 return을 True로 해도 잘못된 함수이고, False로 해도 잘못된 함수
    # 따라서 추가적인 검증 필요함

T = int(input())
for _ in range(T):
    string = input().strip()
    if isPalindrome(string):
        print(0)
    elif isPseudoPalindrome(string):
        print(1)
    else:
        print(2)