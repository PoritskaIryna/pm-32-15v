import numpy as np
import matplotlib.pyplot as plt
from math import*
import random
from scipy import integrate


def func(x):
    return pow(e, pow(x,2))

def func1(x):
    return pow(x,3)+2*x+3

def Rand_x(a, b):
    for _ in range(N):
        points_x.append(random.uniform(a, b))

def Rand_y(f0, f1):
    for _ in range(N):
        points_y.append(random.uniform(f0, f1))

def Absolute_error(Integral):
    return abs(integrate.quad(func, a, b)[0]-Integral)

def Relative_error(Integral):
    return (Absolute_error(Integral)/Integral)*100


if __name__ == "__main__":

    x = []     #x, y will store points for plotting
    y = []
    a = 1      #a and b define the limits of integration
    b = 2
    N = 10000   #N is the number of random points generated
    points_x = [] #points_x and points_y store the random points generated
    points_y = []
    sum_of_points = 0
    right_x = []   #right_x, right_y store the points under the curve
    right_y = []
    wrong_x = []   #wrong_x, wrong_y store the points above the curve
    wrong_y = []
    f0 = 0.0       #f0 and f1 define the function's range.
    f1 = 55.0

    Rand_x(a, b) #встановлюю рандомні точки по у та х
    Rand_y(f0, f1)
    i = a
    while i <= b:     #точки для самої кривої
        x.append(i)
        y.append(func(i))
        i += 0.00001
    plt.plot(x, y)

    for i in range(N):                           #обираю точки над і під кривою
        if points_y[i] <= func(points_x[i]):
            right_x.append(points_x[i])
            right_y.append(points_y[i])
            sum_of_points += 1
        else:
            wrong_x.append(points_x[i])
            wrong_y.append(points_y[i])
        
    plt.scatter(right_x, right_y, s=0.7)
    plt.scatter(wrong_x, wrong_y, s=0.7)
    S = (b-a)*(f1-f0)             #рахую інтеграл за монте-карло
    Integral = S*(sum_of_points/N)
    plt.title(
        f"Точне значення iнтегралу = {integrate.quad(func, a, b)[0]}\nЗначення інтегралу методом Монте-Карло = {Integral}")
    plt.show()

    print(f"Точне значення тестового інтегралу = {integrate.quad(func1, a, b)[0]}")
    print(f"Точне значення iнтегралу = {integrate.quad(func, a, b)[0]}")
    print(f"Значення інтегралу методом Монте-Карло = {Integral}")
    print( f"Абсолютна похибка {np.around(Absolute_error(Integral),decimals=3)}")
    print(f"Вiдносна похибка {np.around(Relative_error(Integral),decimals=3)}%")