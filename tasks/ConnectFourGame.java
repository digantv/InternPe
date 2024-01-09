package tasks;

import java.util.Arrays;
import java.util.Scanner;

public class ConnectFourGame {
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	private static final char EMPTY = ' ';
	private static final char PLAYER1 = 'X';
	private static final char PLAYER2 = 'O';

	private char[][] board;

	public ConnectFourGame() {
		// Initialize the game board with empty spaces
		board = new char[ROWS][COLUMNS];
		for (char[] row : board) {
			Arrays.fill(row, EMPTY);
		}
	}

	public void printBoard() {
		// Print the current state of the game board
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				System.out.print("| " + board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("  1   2   3   4   5   6   7");

	}

	public boolean isColumnFull(int col) {
		 // Check if the specified column is full
		return board[0][col] != EMPTY;
	}

	public boolean makeMove(int col, char player) {
		 // Make a move for the specified player in the given column
		if (col < 0 || col >= COLUMNS || isColumnFull(col)) {
		// Invalid move if the column is out of bounds or full
			return false;
		}

		for (int i = ROWS - 1; i >= 0; i--) {
			if (board[i][col] == EMPTY) {
		 // Place the player's token in the first available empty slot in the column
				board[i][col] = player;
				return true;
			}
		}
		return false;// Couldn't make a move (column full)
	}

	public boolean checkWin(char player) {
		// Check for a win in horizontal, vertical, and diagonal directions
		
		// Check for horizontal wins
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j <= COLUMNS - 4; j++) {
				if (board[i][j] == player && board[i][j + 1] == player && board[i][j + 2] == player
						&& board[i][j + 3] == player) {
					return true;
				}
			}
		}

		// Check for vertical wins
		for (int i = 0; i <= ROWS - 4; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (board[i][j] == player && board[i + 1][j] == player && board[i + 2][j] == player
						&& board[i + 3][j] == player) {
					return true;
				}
			}
		}

		// Check for diagonal wins (positive slope)
		for (int i = 3; i < ROWS; i++) {
			for (int j = 0; j <= COLUMNS - 4; j++) {
				if (board[i][j] == player && board[i - 1][j + 1] == player && board[i - 2][j + 2] == player
						&& board[i - 3][j + 3] == player) {
					return true;
				}
			}
		}

		// Check for diagonal wins (negative slope)
		for (int i = 3; i < ROWS; i++) {
			for (int j = 3; j < COLUMNS; j++) {
				if (board[i][j] == player && board[i - 1][j - 1] == player && board[i - 2][j - 2] == player
						&& board[i - 3][j - 3] == player) {
					return true;
				}
			}
		}

		return false;
	}

	public static void celebrateWinner(char winner) {
		// Print a congratulatory message with an ASCII art representation of celebration
		String winningMessage = " wins!";
		String winnerName = (winner == PLAYER1) ? "Player 1" : "Player 2";

		String manArt = "      \\O/\n" + "       | \n" + "      / \\\n" + "     /   \\\n";

		int artWidth = manArt.lines().mapToInt(String::length).max().orElse(0);
		int padding = (artWidth - winningMessage.length()) / 2;

		System.out.println(manArt);
		System.out.printf("%" + padding + "s%s\n", "", "Congratulations, " + winnerName + winningMessage);
	}

	public static void main(String[] args) {
		ConnectFourGame game = new ConnectFourGame();
		char currentPlayer = PLAYER1;
		boolean gameOver = false;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Connect Four!");
		game.printBoard();

		while (!gameOver) {
			System.out.print(currentPlayer + ", choose a column (1-7): ");
			int col = scanner.nextInt() - 1;

			if (game.makeMove(col, currentPlayer)) {
				game.printBoard();

				if (game.checkWin(currentPlayer)) {
					celebrateWinner(currentPlayer);
					gameOver = true;
				} else if (Arrays.stream(game.board).allMatch(row -> new String(row).indexOf(EMPTY) == -1)) {
					System.out.println("It's a draw!");
					gameOver = true;
				} else {
					currentPlayer = (currentPlayer == PLAYER1) ? PLAYER2 : PLAYER1;
				}
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}

		scanner.close();
	}
}