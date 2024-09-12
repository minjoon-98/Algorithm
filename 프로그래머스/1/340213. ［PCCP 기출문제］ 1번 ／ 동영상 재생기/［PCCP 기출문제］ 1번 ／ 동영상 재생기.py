def mmss_to_sec(mmss):
    mm, ss = map(int, mmss.split(':'))
    return mm * 60 + ss

def sec_to_mmss(sec):
    m = sec // 60
    s = sec % 60
    if m < 10:
        mm = '0' + str(m)
    else:
        mm = str(m)
    if s < 10:
        ss = '0' + str(s)
    else:
        ss = str(s)
    return mm + ":" + ss
    
def solution(video_len, pos, op_start, op_end, commands):
    current_sec = mmss_to_sec(pos)
    op_s_sec, op_end_sec, video_len_sec = mmss_to_sec(op_start), mmss_to_sec(op_end), mmss_to_sec(video_len)
    for command in commands:
        if op_s_sec <= current_sec <= op_end_sec:
            current_sec = op_end_sec
        if command == "prev":
            current_sec -= 10
            if current_sec < 0:
                current_sec = 0
        elif command == "next":
            current_sec += 10
            if current_sec > video_len_sec:
                current_sec = video_len_sec
        if op_s_sec <= current_sec <= op_end_sec:
            current_sec = op_end_sec
    return sec_to_mmss(current_sec)