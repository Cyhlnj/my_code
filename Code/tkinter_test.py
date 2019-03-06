from tkinter import *
from tkinter import messagebox


def closeWindow():
    messagebox.showinfo(title="警告", message="不许关，好好回答问题")
    return


def Love():
    love = Toplevel(window, )
    love.geometry("300x160+520+260")
    love.title("好巧，我也是")
    label = Label(love, text="好巧，我也是", font=("仿宋", 20))
    label.pack()
    label1 = Label(love, text="留个微信呗", font=("仿宋", 20))
    label1.pack()
    entry = Entry(love, font=("仿宋", 20))
    entry.pack()
    btn = Button(love, text="确定", width=10, height=2, command=closeallWindow)
    btn.pack()
    love.protocol("WM_DELETE_WINDOW", closeLove)


def closeallWindow():
    window.destroy()


def noLove():
    no_love = Toplevel(window)
    no_love.geometry("300x100+520+260")
    no_love.title("再考虑考虑呗")
    label = Label(no_love, text="再考虑考虑呗", font=25)
    label.pack()
    btn = Button(no_love, text="好的", width=10, height=2, command=no_love.destroy)
    btn.pack()
    no_love.protocol("WM_DELETE_WINDOW", closeLove)


def closeLove():
    #messagebox.showinfo("再考虑一下", "再考虑一下呗")
    noLove()

window = Tk()
window.title("你喜欢我吗？")
window.geometry("400x400+500+240")
window.protocol("WM_DELETE_WINDOW", closeWindow)

label = Label(window, text="hey，小姐姐", font=("黑体", 15), fg="red")
label.grid(row=0, column=0)
label1 = Label(window, text="你喜欢我吗？", font=("微软雅黑", 30))
label1.grid(row=1, column=1, sticky=E)
photo = PhotoImage(file="./cc.png")
imageLabel = Label(window, image=photo)
imageLabel.grid(row=2, columnspan=2)

btn = Button(window, text="喜欢", width=15, height=2, command=Love)
btn.grid(row=3, column=0, sticky=W)
btn = Button(window, text="不喜欢", width=15, height=2, command=noLove)
btn.grid(row=3, column=1, sticky=E)

window.mainloop()


