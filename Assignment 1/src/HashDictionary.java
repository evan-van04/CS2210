/**
 * This class implements the DictionaryADT using a hash table and stores the game's configurations.
 *
 * @author Evan Van
 */

// Imports the built-in Java Linked List class
import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {

    // Linked list titled table that stores the game's configurations
    private LinkedList<Data>[] table;

    // Private instance variable titled size which stores the size of the dictionary
    private int size;

    /**
     * Constructor with an int-type parameter returns an empty dictionary of the specified size
     *
     * @param size represents the size of the dictionary
     */

    public HashDictionary(int size) {

        // Assigns the value of parameter size to the instance variable size
        this.size = size;

        // Creates an array of linked lists with a size of parameter
        // size and assigns this to the instance variable table
        this.table = new LinkedList[size];

        // Declares a for loop that iterates through the length of the hash table
        for (int i = 0; i < table.length; i++) {

            // Initializes the hash table at each index as an empty dictionary
            table[i] = new LinkedList<>();

        }

    }

    /**
     * Private helper method polynomialHashFunction() finds a position to place the data in the dictionary
     * <p>
     * This method makes use of the polynomial hash function method
     *
     * @param config represents the configuration of the board
     * @return returns the hash value of the given string
     */

    private int polynomialHashFunction(String config) {

        // Initializes the variable val with the value of one less than the length of config
        int val = config.length() - 1;

        // Declares a for loop that iterates over each character of the string
        for (int i = config.length() - 2; i >= 0; i--) {

            // Updates the variable val with the calculated hash function through polynomial hashing
            val = ((val * 8081) + config.charAt(i)) % size;

        }

        // Returns the final hashed value that is within the bounds of the dictionary
        return val;

    }

    /**
     * Public method put() that adds records to the dictionary
     *
     * @param record represents a record of the dictionary that stores the configuration and score
     * @return returns 1 if the specified position in the dictionary already stores at least one element, and
     * 0 if the position is empty
     * @throws DictionaryException if a configuration is already in the dictionary
     */

    public int put(Data record) throws DictionaryException {

        // Calculates the hash function of a given record's configuration and assigns this value to the variable
        // hashValue
        int hashValue = polynomialHashFunction(record.getConfiguration());

        // Retrieves the linked list, table, at position hashValue and assigns this value to the linked list, list
        LinkedList<Data> list = table[hashValue];

        // Declares an if statement that runs if the list at the index hashValue is not null
        if (list != null) {

            // Declares a for loop that iterates through the elements in the list
            for (int i = 0; i < list.size(); i++) {

                // Retrieves the Data object of the list at index i and assigns this to the data object
                Data data = list.get(i);

                // Declares an if statement that throws DictionaryException if record.getConfiguration() is already
                // in the dictionary
                if (record.getConfiguration().equals(data.getConfiguration())) {

                    // Throws the DictionaryException exception
                    throw new DictionaryException();

                }

            }

        } else {

            // Creates a new linked list titled list when list is null
            list = new LinkedList<>();

            // A reference to the newly created linked list is stored at position hashValue in the table array
            table[hashValue] = list;

        }

        // Adds the record object to the new linked list
        list.add(record);

        // Declares an if statement that returns 1 if a collision has occurred
        // (where size is greater than 1, indicating multiple objects) and 0 otherwise
        if (list.size() > 1) {

            // Returns 1 due to a collision
            return 1;

        } else {

            // Returns 0 indicating no collision
            return 0;

        }

    }

    /**
     * Public method remove() that removes the record with the given configuration from the dictionary
     *
     * @param config represents the configuration of the board
     * @throws DictionaryException if no record in the hash table stores the configuration
     */

    public void remove(String config) throws DictionaryException {

        // Declares an if statement that runs if the config exists
        // This is done by calling the get() method which returns -1 if the configuration does not exist and returns
        // the score if it does
        if (get(config) != -1) {

            // Calculates the hash function of a given record's configuration
            // and assigns this value to the variable hashValue
            int hashValue = polynomialHashFunction(config);

            // Removes the record at the position hashValue within the table array
            table[hashValue].remove();

        } else {

            // Throws the DictionaryException exception if the configuration does not exist
            throw new DictionaryException();

        }

    }

    /**
     * Public method get() which returns the score stored in the record with the specified config
     *
     * @param config represents the configuration of the board
     * @return returns the score stored in the record of the dictionary with the given config and -1
     * if the config is not in the dictionary
     */

    public int get(String config) {

        // Calculates the hash function of a given record's configuration and assigns this value to the variable
        // hashValue
        int hashValue = polynomialHashFunction(config);

        // Retrieves the linked list, table, at position hashValue and assigns this value to the linked list, list
        LinkedList<Data> list = table[hashValue];

        // Declares a for loop that iterates through the list to search for the record with the specified config
        for (int i = 0; i < list.size(); i++) {

            // Retrieves the Data object of the list at index i and assigns this to the data object
            Data data = list.get(i);

            // Declares an if statement that returns the score of the current record if the configuration
            // of the current record is equal to the specified configuration, config
            if (data.getConfiguration().equals(config)) {

                // Returns the score of the current record by calling the getScore() method from the Data class
                return data.getScore();

            }

        }

        // If the config is not in the dictionary then -1 is returned
        return -1;

    }

    /**
     * Public method numRecords() which returns the number of Data objects stored in the dictionary
     *
     * @return returns the number of Data objects stored in the dictionary
     */

    public int numRecords() {

        // Initializes an int-type variable titled count with a value of 0
        int count = 0;

        // Declares a for loop that iterates through each index of the table array
        for (int i = 0; i < table.length; i++) {

            // Retrieves the linked list stored at position i in the table array and assigns it to a new linked list,
            // list
            LinkedList<Data> list = table[i];

            // Adds the number of records in the linked list at index i to the variable count
            count += list.size();

        }

        // Returns the total number of records found in the dictionary
        return count;

    }

}
