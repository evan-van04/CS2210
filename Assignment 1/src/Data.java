/**
 * This class represents the records that will be stored in the HashDictionary class.
 *
 * @author Evan Van
 */

public class Data {

    // Private instance variable titled config which represents the concatenated 'X' and 'O' characters placed on the board
    private String config;

    // Private instance variable titled score which stores the score of the game
    private int score;

    /**
     * Constructor that initializes a new Data object with the specified parameters configuration and score
     *
     * @param config represents the concatenated 'X' and 'O' characters placed on the board
     * @param score stores the score of the game
     */

    public Data(String config, int score) {

        // Assigns the value of parameter config to the instance variable configuration
        this.config = config;

        // Assigns the value of parameter score to the instance variable score
        this.score = score;

    }

    /**
     * Public method getConfiguration() that returns the configuration stored in this Data object
     *
     * @return returns the string configuration of the board
     */

    public String getConfiguration() {

        // Returns the string configuration of the board
        return config;

    }

    /**
     * Public method getScore() that returns the score
     *
     * @return returns the score of the game
     */

    public int getScore() {

        // Returns the score of the game
        return score;

    }

}
