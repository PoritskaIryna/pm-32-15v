import random
import math

def initial_solution(n):
    return [random.randint(0, n-1) for _ in range(n)]            #генеруємо початкове рішення(масив з індексами по стовпцям розміщення ферзів)

def conflicts(board):
    n = len(board)
    conflicts = 0
    for i in range(n):
        for j in range(i+1, n):
            if board[i] == board[j] or abs(board[i] - board[j]) == j - i:           #check in the same line and diagonal
                conflicts += 1
    return conflicts

def next_state(board):
    n = len(board)
    new_board = list(board)
    i = random.randint(0, n-1)    #generates a random integer to randomly select a column
    j = random.randint(0, n-1)
    while j == board[i]:           #generate j different from i to assign new position on the board
        j = random.randint(0, n-1)
    new_board[i] = j
    return new_board

def acceptance_probability(old_cost, new_cost, temperature):        #ймовірність прийняття рішення
    if new_cost < old_cost:              
        return 1.0                                                  #it returns a probability of 1.0
    return math.exp((old_cost - new_cost) / temperature)

def simulated_annealing(n, max_iterations=10000):
    board = initial_solution(n)
    print("initial board")
    print(board)
    current_cost = conflicts(board)
    print(f"with {current_cost} conflicts")
    temperature = 30.0
    cooling_rate = 0.95 #швидкість охолодження

    for _ in range(max_iterations):
        new_board = next_state(board)
        new_cost = conflicts(new_board)
        if new_cost == 0:
            return new_board
        ap = acceptance_probability(current_cost, new_cost, temperature)
        if ap > random.random():                                   #generates a random floating-point number in the range [0.0, 1.0).
            board = new_board
            current_cost = new_cost
        temperature *= cooling_rate
    return None


def print_solution(board):
    n = len(board)
    for row in range(n):
        line = ['.' if i != board[row] else 'Q' for i in range(n)]
        print(' '.join(line))

if __name__ == "__main__":
    n = 8  # Size of the board
    solution = simulated_annealing(n)
    if solution:
        print("Solution found:")
        print_solution(solution)
    else:
        print("Solution not found.")