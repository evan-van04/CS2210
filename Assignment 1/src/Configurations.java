/**
 * This class implements the methods needed by the algorithm computerPlay in the Play class.
 *
 * @author Evan Van
 */

public class Configurations {

    // Private instance variable titled board is a 2D char-type array used to store the game board
    private char[][] board;

    // Private instance variable titled boardSize represents the size of the board
    private int boardSize;

    // Private instance variable titled lengthToWin represents the sequence needed to win the game
    private int lengthToWin;

    // Private instance variable titled maxLevels represents the maximum level of the game tree that will be explored
    private int maxLevels;


    /**
     * Constructor that initializes an empty board
     *
     * @param boardSize represents the size of the board
     * @param lengthToWin represents the sequence needed to win the game
     * @param maxLevels represents the maximum level of the game tree that will be explored
     */

    public Configurations(int boardSize, int lengthToWin, int maxLevels) {

        // Assigns the value of parameter boardSize to the instance variable boardSize
        this.boardSize = boardSize;

        // Assigns the value of parameter lengthToWin to the instance variable lengthToWin
        this.lengthToWin = lengthToWin;

        // Assigns the value of parameter maxLevels to the instance variable maxLevels
        this.maxLevels = maxLevels;

        // Initializes the instance variable board with dimensions of boardSize x boardSize
        this.board = new char[boardSize][boardSize];

        // Declares a for loop that iterates through the rows of the board to form an empty board
        for (int i = 0; i < boardSize; i++) {

            // Declares an inner for loop that iterates through each column of the rows
            for (int j = 0; j < boardSize; j++) {

                // Initializes the board at position [i][j] with an empty character ' '
                board[i][j] = ' ';

            }

        }

    }

    /**
     * Public method createDictionary() that returns an empty HashDictionary of size 8081, a large prime number
     *
     * @return returns an empty HashDictionary of size 8081
     */

    public HashDictionary createDictionary() {

        // int-type variable titled size is initialized with a size of 8081 and represents the size of the
        // HashDictionary
        int size = 8081;

        // Returns a HashDictionary of size 8081
        return new HashDictionary(size);

    }

    /**
     * Public method repeatedConfiguration() returns the score of the board if the string representation of it
     * is found within the hashtable, otherwise the method returns -1
     *
     * @param hashTable represents the hash table
     * @return returns the score if the string representation of the board is found within the hashtable, otherwise
     * returns -1
     */

    public int repeatedConfiguration(HashDictionary hashTable) {

        // Calls the boardToString() method to convert the board into a string representation and stores this value
        // in the String-type variable boardString
        String boardString = boardToString();

        // Retrieves the score of the board using the get() method from the HashDictionary class and assigns this
        // value to the int-type variable score
        int score = hashTable.get(boardString);

        // Declares an if statement that returns the score of the board if the value of score is not -1
        if (score != -1) {

            // Returns the score of the current board configuration
            return score;

        } else {

            // Returns -1 if the score of the board is -1
            return -1;

        }

    }

    /**
     * Public method addConfiguration() that inserts the string representation of the board and its score
     * into the hashDictionary
     *
     * @param hashDictionary represents the hash dictionary
     * @param score represents the score of the board
     */

    public void addConfiguration(HashDictionary hashDictionary, int score) {

        // Calls the boardToString() method to convert the board into a string representation and stores this value
        // in the String-type variable boardString
        String boardString = boardToString();

        // Inserts the string representation of the board and its score using the Data constructor in the Data class
        // and the put() method from the HashDictionary class
        hashDictionary.put(new Data(boardString, score));

    }

    /**
     * Public method savePlay() that stores a symbol in the board at position [row][col]
     *
     * @param row represents the row index of the board
     * @param col represents the column index of the board
     * @param symbol represents the symbol on the board, either 'X' or 'O'
     */

    public void savePlay(int row, int col, char symbol) {

        // Assigns the symbol to the position [row][col] of the 2D array, board
        board[row][col] = symbol;

    }

    /**
     * Public method squareIsEmpty() that returns true if position [row][col] of the board is empty, ' ',
     * otherwise returns false
     *
     * @param row represents the row index of the board
     * @param col represents the column index of the board
     * @return returns true if position [row][col] of the board is empty, ' ', otherwise returns false
     */

    public boolean squareIsEmpty(int row, int col) {

        // Returns true if position [row][col] of the board is empty, ' ', otherwise returns false
        return board[row][col] == ' ';

    }

    /**
     * Public method wins() that returns true if there exists a continuous sequence of symbols of length lengthToWin
     *
     * The wins() method calls three private helper methods horizontalWin(), verticalWin(), and diagonalWin()
     * that checks for horizontal, vertical, and diagonal wins, respectively
     *
     * @param symbol represents the symbol on the board, either 'X' or 'O'
     * @return returns true if any of the three private helper methods return true, and false otherwise
     */

    public boolean wins(char symbol) {

        // Returns true if any one of the three private helper methods, horizontalWin(), verticalWin(),
        // and diagonalWin() return true, and false if none return true
        return horizontalWin(symbol) || verticalWin(symbol) || diagonalWin(symbol);

    }

    /**
     * Private helper method horizontalWin() that iterates through the board to check if there exists a continuous
     * horizontal sequence of symbols of length lengthToWin
     *
     * @param symbol represents the symbol on the board, either 'X' or 'O'
     * @return returns true if the method finds a continuous horizontal sequence of symbols of length lengthToWin
     */

    private boolean horizontalWin(char symbol) {

        // Declares a for loop that iterates through each row of the board
        for (int i = 0; i < boardSize; i++) {

            // Declares an inner for loop that iterates through each possible starting column of the rows
            for (int j = 0; j <= boardSize - lengthToWin; j++) {

                // Declares an if statement that calls the private helper method sequence() to check if there exists
                // a continuous horizontal winning sequence and returns true if there is
                if (sequence(i, j, 0, 1, symbol)) {

                    // Returns true
                    return true;

                }

            }

        }

        // If the called private helper method sequence() does not identify a horizontal winning sequence and returns
        // false, then this method returns false as well
        return false;

    }

    /**
     * Private helper method verticalWin() that iterates through the board to check if there exists a continuous
     * vertical sequence of symbols of length lengthToWin
     *
     * @param symbol represents the symbol on the board, either 'X' or 'O'
     * @return returns true if the method finds a continuous vertical sequence of symbols of length lengthToWin
     */

    private boolean verticalWin(char symbol) {

        // Declares a for loop that iterates through each column of the board
        for (int i = 0; i <= boardSize - lengthToWin; i++) {

            // Declares an inner for loop that iterates through each possible starting row of the columns
            for (int j = 0; j < boardSize; j++) {

                // Declares an if statement that calls the private helper method sequence() to check if there exists
                // a continuous vertical winning sequence and returns true if there is
                if (sequence(i, j, 1, 0, symbol)) {

                    // Returns true
                    return true;

                }

            }

        }

        // If the called private helper method sequence() does not identify a vertical winning sequence and returns
        // false, then this method returns false as well
        return false;

    }

    /**
     * Private helper method diagonalWin() that iterates through the board to check if there exists a continuous
     * diagonal sequence of symbols of length lengthToWin
     *
     * @param symbol represents the symbol on the board, either 'X' or 'O'
     * @return returns true if the method finds a continuous diagonal sequence of symbols of length lengthToWin
     */

    private boolean diagonalWin(char symbol) {

        // This set of for loops check for a winning sequence of diagonals from top-left to bottom-right
        // Declares a for loop that iterates through the rows of the board
        for (int i = 0; i <= boardSize - lengthToWin; i++) {

            // Declares an inner for loop that iterates through the columns of the board
            for (int j = 0; j <= boardSize - lengthToWin; j++) {

                // Declares an if statement that calls the private helper method sequence() to check if there exists
                // a continuous diagonal winning sequence and returns true if there is
                if (sequence(i, j, 1, 1, symbol)) {

                    // Returns true
                    return true;

                }

            }


        }

        // This set of for loops check for a winning sequence of diagonals from top-right to bottom-left
        // Declares a for loop that iterates through the rows of the board
        for (int i = 0; i <= boardSize - lengthToWin; i++) {

            // Declares an inner for loop that iterates through the columns of the board
            for (int j = boardSize - 1; j >= lengthToWin - 1; j--) {

                // Declares an if statement that calls the private helper method sequence() to check if there exists
                // a continuous diagonal winning sequence and returns true if there is
                if (sequence(i, j, 1, -1, symbol)) {

                    // Returns true
                    return true;

                }

            }

        }

        // If the called private helper method sequence() does not identify a diagonal winning sequence and returns
        // false, then this method returns false as well
        return false;

    }

    /**
     * Private helper method sequence() that checks for a continuous sequences of symbols from a specified position on
     * the board
     *
     * The method advances through the board in specified increments in both row and column directions
     *
     * @param row represents the starting row index of the sequence
     * @param col represents the starting column index of the sequence
     * @param rowInc represents the increment value used for the row index to move along the sequence
     * @param colInc represents the increment value used for the column index to move along the sequence
     * @param symbol represents the symbol on the board, either 'X' or 'O'
     * @return returns true if a continuous sequence is greater than or equal to the length needed to win, lengthToWin
     */

    private boolean sequence(int row, int col, int rowInc, int colInc, char symbol) {

        // int-type variable titled count which is initialized at 0 represents the sequence of continuous symbols
        int count = 0;

        // Declares a while loop that continuously iterates as long as the current position of the board is within the
        // bounds of the board and its symbol matches the symbol being searched for
        while (row >= 0 && row < boardSize && col >= 0 && col < boardSize && board[row][col] == symbol) {

            // Increments the sequence of symbols by 1
            count++;

            // Updates the row index by the value of rowInc
            row += rowInc;

            // Updates the column index by the value of colInc
            col += colInc;

        }

        // Returns true if the sequence of symbols is greater than or equal to the length to win, and false otherwise
        return count >= lengthToWin;

    }

    /**
     * Public method isDraw() iterates through the board to check if the game has come to a draw by identifying if
     * there are any empty spaces on the board and no player has won
     *
     * @return returns true if the board has no empty positions left and no player has won the game
     */

    public boolean isDraw() {

        // Declares a for loop that iterates through the rows of the board
        for (int i = 0; i < boardSize; i++) {

            // Declares an inner for loop that iterates through the columns of the board
            for (int j = 0; j < boardSize; j++) {

                // Declares an if statement that returns false if a position on the board is empty
                if (board[i][j] == ' ') {

                    // Returns false
                    return false;

                }

            }

        }

        // Returns true if there are no empty positions on the board, indicating the game is a draw
        return true;

    }

    /**
     * Public method evalBoard() that returns a specific value depending on the current state of the board
     *
     * @return returns 3 if the computer has won, 0 if the human player has won, 2 if the game is a draw, and
     * 1 if the game is still undecided
     */

    public int evalBoard() {

        // Declares an if statement that calls the wins() method with the symbol 'O' and if the method returns true
        // the if statement returns 3
        if (wins('O')) {

            // Returns a value of 3
            return 3;

            // Declares an else-if statement that calls the wins() method with the symbol 'X' and if the method
            // returns true the if statement returns 0
        } else if (wins('X')) {

            // Returns a value of 0
            return 0;

            // Declares an else-if statement that calls the isDraw() method and returns 2 if the isDraw() method
            // returns true
        } else if (isDraw()) {

            // Returns a value of 2
            return 2;

        } else {

            // Else, if the game is still undecided, the method returns 1
            return 1;

        }

    }

    /**
     * Private helper method boardToString() that stores the characters of the board into a string format
     *
     * @return returns the string representation of the board
     */

    private String boardToString() {

        // Declares a StringBuilder to construct the string representation of the board
        StringBuilder sb = new StringBuilder();

        // Declares a for loop that iterates through each row of the board
        for (int i = 0; i < board.length; i++) {

            // Retrieves the i-th row of the board array and assigns it to the char-type array, row
            char[] row = board[i];

            // Uses the StringBuilder to append each row of the board together
            sb.append(row);

        }

        // Returns the string representation of the board
        return sb.toString();

    }

}