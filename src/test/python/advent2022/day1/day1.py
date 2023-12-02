root_resources = "../../../resources/"

f = open(root_resources + "year2022/day1/input", "r")
data = [i for i in f.read().strip().split("\n")]

highest = []
sums = 0

for item in data:
    if item == "":
        highest.append(sums)
        sums = 0
    else:
        sums += int(item)

ex1 = max(highest)
print("day 1 ex 1 : " + str(ex1))
assert ex1 == 70116

ex2 = 0
for i in range(3):
    current_highest = max(highest)
    ex2 += current_highest
    highest.remove(current_highest)

print("day 1 ex 2 : " + str(ex2))
assert ex2 == 206582
