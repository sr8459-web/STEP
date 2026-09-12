import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringAndExceptionManager {

    public static void main(String[] args) {
        StringAndExceptionManager manager = new StringAndExceptionManager();
        manager.executeOperations();
    }

    public void executeOperations() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            String fixedSampleText = "Java \"Strings\" Demonstration";
            displayMessage(fixedSampleText);

            String userProvidedText = readUserInput(reader);

            String[] textArray = splitSentenceIntoWords(userProvidedText);
            displayStringArray(textArray);

            inspectStringProperties(userProvidedText);
            displayAsciiValues(userProvidedText);

            demonstrateCheckedException();
            demonstrateUncheckedException(textArray);

        } catch (IOException ioException) {
            displayMessage("Checked Exception Encountered: " + ioException.getMessage());
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException runtimeException) {
            displayMessage("Unchecked Exception Encountered: " + runtimeException.getMessage());
        } catch (Exception generalException) {
            displayMessage("General Exception Caught: " + generalException.getMessage());
        } finally {
            closeReader(reader);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String readUserInput(BufferedReader reader) throws IOException {
        System.out.print("Enter a string with escape sequences or characters: ");
        String inputLine = reader.readLine();
        if (inputLine == null || inputLine.trim().isEmpty()) {
            throw new IllegalArgumentException("User input cannot be null or empty.");
        }
        return inputLine;
    }

    public String[] splitSentenceIntoWords(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Target sentence cannot be null.");
        }
        return sentence.split("\\s+");
    }

    public void displayStringArray(String[] stringArray) {
        if (stringArray == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        for (int index = 0; index < stringArray.length; index++) {
            System.out.println("Element at index " + index + ": \"" + stringArray[index] + "\"");
        }
    }

    public void inspectStringProperties(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Provided text is null.");
        }
        int textLength = text.length();
        String upperCaseVersion = text.toUpperCase();
        String lowerCaseVersion = text.toLowerCase();
        boolean containsEscapePattern = text.contains("\\");

        System.out.println("Length: " + textLength);
        System.out.println("Uppercase: " + upperCaseVersion);
        System.out.println("Lowercase: " + lowerCaseVersion);
        System.out.println("Contains Backslash: " + containsEscapePattern);
    }

    public void displayAsciiValues(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Provided text is null.");
        }
        for (int index = 0; index < text.length(); index++) {
            char character = text.charAt(index);
            int asciiCode = (int) character;
            System.out.println("Character: '" + character + "' -> ASCII: " + asciiCode);
        }
    }

    public void demonstrateCheckedException() throws IOException {
        boolean forceCheckedException = false;
        if (forceCheckedException) {
            throw new IOException("Checked Exception explicitly triggered.");
        }
    }

    public void demonstrateUncheckedException(String[] words) {
        try {
            int outOfBoundsIndex = words.length + 5;
            String invalidWord = words[outOfBoundsIndex];
            System.out.println(invalidWord);
        } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
            System.out.println("Handled Unchecked Exception internally: " + outOfBoundsException.getClass().getSimpleName());
        }
    }

    public void closeReader(BufferedReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ioException) {
                System.out.println("Error closing BufferedReader: " + ioException.getMessage());
            }
        }
    }
}