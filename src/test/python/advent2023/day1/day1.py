import re

values = []
with open("../../../resources/year2023/day1/input") as file:
    for line in file:
        values.append(line.rstrip())

replacements_dictionary = {
    'one': 'on1e',
    'two': 'tw2o',
    'three': 'thr3e',
    'four': 'fo4ur',
    'five': 'fi5ve',
    'six': 'si6x',
    'seven': 'sev7en',
    'eight': 'ei8ght',
    'nine': 'ni9ne'
}


def keep_only_digits(full_line):
    return re.sub('\\D', '', full_line)


def replace_literal_digits_with_digits(line_with_literals):
    lines_without_literals = line_with_literals
    for key, value in replacements_dictionary.items():
        lines_without_literals = lines_without_literals.replace(key, value)
    return lines_without_literals


def extract_first_and_last_digit(digits_only_value):
    return int(digits_only_value[0] + digits_only_value[-1])


# day 1 ex 1
print("day 1 ex 1")
summed = 0
for curr_line in values:
    digits_only = keep_only_digits(curr_line)
    summed += extract_first_and_last_digit(digits_only)
print("result : " + str(summed))
assert summed == 54877

# day 1 ex 2
print("day 1 ex 2")
summed = 0
for curr_line in values:
    curr_line = replace_literal_digits_with_digits(curr_line)
    digits_only = keep_only_digits(curr_line)
    summed += extract_first_and_last_digit(digits_only)
print("result : " + str(summed))
assert summed == 54100
