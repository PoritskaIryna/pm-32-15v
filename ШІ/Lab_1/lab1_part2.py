def is_safe(board, row, col, n):
    for i in range(row):
        if board[i][col] == 'X':                     #It checks if there's any queen in the same column above the current position.
            return False

    for i, j in zip(range(row, -1, -1), range(col, -1, -1)):  #It checks if there's any queen in the diagonal going from the current position upwards and to the left.
        if board[i][j] == 'X':
            return False

    for i, j in zip(range(row, -1, -1), range(col, n)):   #It checks if there's any queen in the diagonal going from the current position upwards and to the right.
        if board[i][j] == 'X':
            return False

    return True


def solve_n_queens_util(board, row, n):
    if row >= n:
        return True

    for col in range(n):
        if is_safe(board, row, col, n):
            board[row][col] = 'X'

            if solve_n_queens_util(board, row + 1, n):
                return True

            board[row][col] = 0
    return False


def solve_n_queens(n):
    board = [[0] * n for _ in range(n)]

    if not solve_n_queens_util(board, 0, n):
        print("Розв'язок не знайдено")
        return

    for row in board:
        print(' '.join(map(str, row)))


n = 8
solve_n_queens(n)

