def step_1(id):
    ans = ''
    for c in id:
        if c.isupper():
            c = c.lower()
        ans += c
    return ans

def step_2(id):
    ans = ''
    for c in id:
        if c.isalnum() or c == '-' or c == '_' or c == '.':
            ans += c
    return ans
            
def step_3(id):
    ans = ''
    for c in id:
        if c == '.':
            if ans and ans[-1] == '.':
                continue
        ans += c
    return ans

def step_4(id):
    ans = ''
    for i in range(len(id)):
        if i == 0 and id[i] == '.':
            continue
        if i == len(id) and id[i] == '.':
            continue
        ans += id[i]
    return ans
            
def step_5(id):
    if len(id) == 0:
        id = id + 'a'
    return id

def step_6(id):
    if len(id) >= 16:
        id = id[:15]
    if id[-1] == '.':
        id = id[:-1]
    return id

def step_7(id):
    if id:
        while len(id) <= 2:
            id = id + id[-1]
    return id

def solution(new_id):
    
    
    return step_7(step_6(step_5(step_4(step_3(step_2(step_1(new_id)))))))
    
