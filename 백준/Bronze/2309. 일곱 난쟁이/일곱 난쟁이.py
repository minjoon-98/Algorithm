dwarf_height = [int(input()) for _ in range(9)]
dwarf_height.sort()

total_height = sum(dwarf_height)

for i in range(9):
    for j in range(i + 1, 9):
        if total_height - dwarf_height[i] - dwarf_height[j] == 100:
            for k in range(9):
                if k != i and k != j:
                    print(dwarf_height[k])
            exit()