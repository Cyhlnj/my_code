def fibo(n):
    a = 1
    b = 1
    for i in range(n):
        if i == 1 or i == 0:
            yield 1
        else:
            b, a = a, b+a
            yield  a

for i in fibo(10):
    print(i, end=' ')