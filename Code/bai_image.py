import matplotlib.pyplot as plt

plt.style.use('ggplot')
customers = ['ABC', 'DEF', 'GHI', 'JKL', 'MNO']
customers_index = range(len(customers))
sale_amounts = [127, 90, 201, 111, 232]
fig = plt.figure(dpi=200)
ax1 = fig.add_subplot(1, 1, 1)
ax1.bar(customers_index, sale_amounts, align='center', color='cyan')
ax1.xaxis.set_ticks_position('bottom')
ax1.yaxis.set_ticks_position('left')
plt.xticks(customers_index, customers, )
plt.xlabel('Customer Name')
plt.ylabel('Sale Amount')
plt.title('Sale Amount per Customer')
plt.show()