package tasks;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeGame extends Application {
	// Represents the current player ('X' or 'O')
    private char currentPlayer = 'X';
	
	 // 2D array to store the buttons in the Tic Tac Toe grid
    private Button[][] buttons = new Button[3][3];

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }

	 // Override the start method to set up the GUI
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Styled Tic Tac Toe");

		// Create a grid pane for the Tic Tac Toe board
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

		// Create buttons for each cell in the grid and set their properties
        for (int i = 0; i < 3; i++) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setMinSize(100, 100);
                button.setStyle("-fx-font-size: 2em;");
                button.setOnAction(e -> handleButtonClick(button));
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

		// Create the scene and set it to the stage
        Scene scene = new Scene(gridPane, 325, 325);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	// Handle button click event
    private void handleButtonClick(Button button) {
        if (button.getText().equals("")) {
            button.setText(String.valueOf(currentPlayer));
            button.setStyle("-fx-background-color: " + (currentPlayer == 'X' ? "blue" : "red") + "; -fx-font-size: 2em;");
            if (checkForWinner()) {
                showAlert("Player " + currentPlayer + " wins!");
                resetGame();
            } else if (isBoardFull()) {
                showAlert("It's a draw!");
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

     // Check if there is a winner on the board
	private boolean checkForWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }

        return checkDiagonals();
    }

     // Check if a specific row has three matching symbols
	private boolean checkRow(int row) {
        return buttons[row][0].getText().equals(String.valueOf(currentPlayer))
                && buttons[row][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[row][2].getText().equals(String.valueOf(currentPlayer));
    }

    private boolean checkColumn(int col) {
        return buttons[0][col].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][col].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][col].getText().equals(String.valueOf(currentPlayer));
    }

    private boolean checkDiagonals() {
        return buttons[0][0].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][2].getText().equals(String.valueOf(currentPlayer))
                || buttons[0][2].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][0].getText().equals(String.valueOf(currentPlayer));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
	// Reset the game board
    private void resetGame() {
        currentPlayer = 'X';
		 // Clear the text and style of all buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setStyle("-fx-background-color: white; -fx-font-size: 2em;");
            }
        }
    }
	// Show an alert with the game outcome message
    private void showAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
