import numpy as np
import matplotlib.pyplot as plt
import random
from scipy import integrate

def f(x):
    return np.exp(x**2)  # Define your integrand function here

def Absolute_error(Integral):
    return abs(integrate.quad(f, a, b)[0]-Integral)

def Relative_error(Integral):
    return (Absolute_error(Integral)/Integral)*100

def monte_carlo_integral(func, a, b, num_points):
    integral_sum = 0
    under_curve_x = []
    under_curve_y = []
    outside_curve_x = []
    outside_curve_y = []

    for _ in range(num_points):
        x = random.uniform(a, b)
        y = random.uniform(0, func(b))  # Ensure y is within the function curve
        if y <= func(x):
            integral_sum += y
            under_curve_x.append(x)
            under_curve_y.append(y)
        else:
            outside_curve_x.append(x)
            outside_curve_y.append(y)
    
    integral = (b - a) * (integral_sum / num_points)
    return integral, under_curve_x, under_curve_y, outside_curve_x, outside_curve_y

# Define the interval [a, b] and number of random points
a = 1
b = 2
num_points = 10000

# Calculate the Monte Carlo integral and points under/above the curve
monte_carlo_result, under_curve_x, under_curve_y, outside_curve_x, outside_curve_y = monte_carlo_integral(f, a, b, num_points)

# Plot the function
x_values = np.linspace(a, b, 100)
y_values = f(x_values)
plt.plot(x_values, y_values, label='e^(x^2)')

# Plot points under and above the curve
plt.scatter(under_curve_x, under_curve_y, color='blue', s=5, alpha=0.5, label='Under the Curve')
plt.scatter(outside_curve_x, outside_curve_y, color='red', s=5, alpha=0.5, label='Outside the Curve')

plt.xlabel('x')
plt.ylabel('y')
plt.title('Monte Carlo Integration for e^(x^2)')
plt.legend()
plt.grid(True)
plt.show()

print("Monte Carlo Integral Result:", monte_carlo_result)
print( f"Абсолютна похибка {np.around(Absolute_error(monte_carlo_result),decimals=3)}")
print(f"Вiдносна похибка {np.around(Relative_error(monte_carlo_result),decimals=3)}%")