from itertools import combinations
import sys
input = sys.stdin.readline

L, C = map(int, input().split())

character = sorted(input().split())

def is_valid(password):
    vowels = {'a', 'e', 'i', 'o', 'u'}
    num_vowels = sum(1 for char in password if char in vowels)
    num_consonants = len(password) - num_vowels
    return num_vowels >= 1 and num_consonants >= 2

for answer in combinations(character, L):
    if is_valid(answer):
        print("".join(answer))