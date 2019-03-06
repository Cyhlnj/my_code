from random import choice, random
from turtle import *
a = (3 + random() * 2) * choice([1, -1])
b = (3 + random() * 2) * choice([1, -1])
ball = [0, 0]
aim = [a, b]
state = {1: 0, 2: 0}


def move(player, change):
    # 改变挡板位置
    state[player] += change


def rectangle(x, y, width, height):
    # 画一个长方形当作挡板
    up()
    goto(x, y)
    down()
    begin_fill()
    for count in range(2):
        forward(width)
        left(90)
        forward(height)
        left(90)
    end_fill()

def draw():
    # 移动小球
    clear()
    rectangle(-200, state[1], 10, 50)
    rectangle(190, state[2], 10, 50)

    ball[0] += aim[0]
    ball[1] += aim[1]
    x = ball[0]
    y = ball[1]

    up()
    goto(x, y)
    dot(8)
    update()

    if y < -190 or y > 200:
        aim[1] = -aim[1]

    if x < -185:
        low = state[1]
        high = state[1] + 50

        if low <= y <= high:
            aim[0] = -aim[0]
        else:
            goto(-100, 0)
            write('game over\nplayer 2 win!', font=("", 40))
            done()

    if x > 185:
        low = state[2]
        high = state[2] + 50

        if low <= y <= high:
            aim[0] = -aim[0]
        else:
            goto(-100, 0)
            write('game over\nplayer 1 win!', font=("", 40))
            done()

    ontimer(draw, 50)

setup(420, 420, 370, 0)
hideturtle()
tracer(False)
listen()
onkey(lambda: move(1, 20), 'w')
onkey(lambda: move(1, -20), 's')
onkey(lambda: move(2, 20), 'Up')
onkey(lambda: move(2, -20), 'Down')

draw()
done()