function solve(board, row, col){

  // If on the last column of a row, go to the next row
  if (col === 9){
    row++;
    col = 0;
  }
  // board is filled
  if(row === 9)
    return true;

  if (board[row][col] != '.')
    return solve(board, row, col + 1);

  for (let num = 1; num <= 9; num++){
    char = num.toString();
    if (isValid(board, row, col, char)){
      board[row][col] = char;
      if (solve(board, row, col + 1))
        return true;
      board[row][col] = '.';
    }
  }

  return false;
}

function isValid(board, row, col, char) {
  for (let i = 0; i < 9; i++)
    if (board[row][i] === char)
      return false;

  for (let i = 0; i < 9; i++)
    if (board[i][col] === char)
      return false;

  const startRow = row - (row % 3),
        startCol = col - (col % 3);

  for (let i = 0; i < 3; i++){
    for (let j = 0; j < 3; j++){
      if (board[i + startRow][j + startCol] === char)
        return false;
    }
  }
  return true;
}

function solveSudoku(board){
  solve(board, 0, 0);
}

const board = [["5","3",".",".","7",".",".",".","."],
              ["6",".",".","1","9","5",".",".","."],
              [".","9","8",".",".",".",".","6","."],
              ["8",".",".",".","6",".",".",".","3"],
              ["4",".",".","8",".","3",".",".","1"],
              ["7",".",".",".","2",".",".",".","6"],
              [".","6",".",".",".",".","2","8","."],
              [".",".",".","4","1","9",".",".","5"],
              [".",".",".",".","8",".",".","7","9"]]

solveSudoku(board);

board.forEach(row => console.log(row.join(" ")));

