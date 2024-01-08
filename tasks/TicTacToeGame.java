package tasks;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeGame extends Application {
    private char currentPlayer = 'X';
    private Button[][] buttons = new Button[3][3];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Styled Tic Tac Toe");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

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

        Scene scene = new Scene(gridPane, 325, 325);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

    private boolean checkForWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }

        return checkDiagonals();
    }

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

    private void resetGame() {
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setStyle("-fx-background-color: white; -fx-font-size: 2em;");
            }
        }
    }

    private void showAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
