import sys
input = sys.stdin.readline

maxNum = 1003001
primeCheck = [False, False] + [True]*(maxNum-1)
for i in range(2, int(maxNum**(0.5))+1):
    if primeCheck[i]:
        for j in range(2*i, maxNum+1, i):
            primeCheck[j] = False

primeNums = [k for k in range(len(primeCheck)) if primeCheck[k]]

def isPalindrome(intNum):
    strNum = str(intNum)
    if strNum == strNum[::-1]:
        return True
    else:
        return False
    

N = int(input())

for primeNum in primeNums:
    if primeNum < N:
        continue
    if isPalindrome(primeNum):
        print(primeNum)
        exit()