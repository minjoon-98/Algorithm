# 슬라이딩 윈도우

for test_case in range(10):
	test_case_number = int(input())
	target = input()
	target_len = len(target)
	string = input()
	string_len = len(string)
    
	count = 0
	for i in range(string_len-target_len + 1):
	    if string[i : i+target_len] == target:
	        count += 1
    
	print(f"#{test_case_number} {count}")