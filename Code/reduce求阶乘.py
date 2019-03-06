from functools import reduce

f = [reduce(lambda x, y: x*y, (j for j in range(1, i))) for i in range(2, 100)]
print(f)
