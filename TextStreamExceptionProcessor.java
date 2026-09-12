import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextStreamExceptionProcessor {

    public static void main(String[] args) {
        TextStreamExceptionProcessor processor = new TextStreamExceptionProcessor();
        processor.runApplicationPipeline();
    }

    public void runApplicationPipeline() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String fixedEscapeSequenceText = "Header:\t\"Java Core Execution\"\nStatus:\tVerified \\ Operational";
            displayOutput(fixedEscapeSequenceText);

            String userProvidedInput = readConsoleInput(consoleReader);

            String[] extractedWords = convertStringToWordArray(userProvidedInput);
            displayStringArrayElements(extractedWords);

            String transformedSummary = evaluateStringBuiltInMethods(userProvidedInput);
            displayOutput("Transformed Summary: " + transformedSummary);

            int[] characterAsciiCodes = convertTextToAsciiCodes(userProvidedInput);
            displayAsciiAnalysis(characterAsciiCodes, userProvidedInput);

            executeCheckedFileCheck("non_existent_config.txt");
            executeUncheckedArrayBoundsCheck(extractedWords);

        } catch (IOException ioException) {
            displayOutput("Checked Exception handled in main pipeline: " + ioException.getMessage());
        } catch (IllegalArgumentException illegalArgumentException) {
            displayOutput("Unchecked Argument Exception handled: " + illegalArgumentException.getMessage());
        } catch (Exception generalException) {
            displayOutput("Generic Exception caught: " + generalException.getMessage());
        } finally {
            closeConsoleReader(consoleReader);
        }
    }

    public void displayOutput(String outputContent) {
        System.out.println(outputContent);
    }

    public String readConsoleInput(BufferedReader consoleReader) throws IOException {
        System.out.print("Enter string payload: ");
        String enteredLine = consoleReader.readLine();

        if (enteredLine == null || enteredLine.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be empty or null.");
        }
        return enteredLine;
    }

    public String[] convertStringToWordArray(String rawInput) {
        if (rawInput == null) {
            throw new IllegalArgumentException("Source string cannot be null.");
        }
        return rawInput.trim().split("\\s+");
    }

    public void displayStringArrayElements(String[] stringItems) {
        if (stringItems == null) {
            throw new IllegalArgumentException("Array reference cannot be null.");
        }
        for (int arrayIndex = 0; arrayIndex < stringItems.length; arrayIndex++) {
            System.out.println("Word [" + arrayIndex + "]: \"" + stringItems[arrayIndex] + "\"");
        }
    }

    public String evaluateStringBuiltInMethods(String inputData) {
        if (inputData == null) {
            throw new IllegalArgumentException("Input data cannot be null.");
        }
        int textLength = inputData.length();
        String upperCaseVersion = inputData.toUpperCase();
        String trimmedVersion = inputData.trim();
        boolean hasEscapeSymbol = inputData.contains("\\");

        return "Length=" + textLength + ", Uppercase=" + upperCaseVersion + ", Trimmed=" + trimmedVersion + ", HasBackslash=" + hasEscapeSymbol;
    }

    public int[] convertTextToAsciiCodes(String originalText) {
        if (originalText == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }
        int[] asciiArray = new int[originalText.length()];
        for (int charIndex = 0; charIndex < originalText.length(); charIndex++) {
            asciiArray[charIndex] = (int) originalText.charAt(charIndex);
        }
        return asciiArray;
    }

    public void displayAsciiAnalysis(int[] asciiValues, String referenceText) {
        if (asciiValues == null || referenceText == null) {
            throw new IllegalArgumentException("Ascii array and reference text must be non-null.");
        }
        for (int charIndex = 0; charIndex < referenceText.length(); charIndex++) {
            System.out.println("Character '" + referenceText.charAt(charIndex) + "' -> ASCII: " + asciiValues[charIndex]);
        }
    }

    public void executeCheckedFileCheck(String targetFilePath) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(targetFilePath))) {
            String firstLine = fileReader.readLine();
            displayOutput("File Line: " + firstLine);
        } catch (IOException checkedFileException) {
            System.out.println("Handled Checked Exception (IOException) locally: " + checkedFileException.getClass().getName());
        }
    }

    public void executeUncheckedArrayBoundsCheck(String[] stringElements) {
        try {
            int outOfBoundsIndex = stringElements.length + 10;
            String invalidElement = stringElements[outOfBoundsIndex];
            displayOutput(invalidElement);
        } catch (ArrayIndexOutOfBoundsException boundsException) {
            System.out.println("Handled Unchecked Exception (RuntimeException) locally: " + boundsException.getClass().getName());
        }
    }

    public void closeConsoleReader(BufferedReader consoleReader) {
        if (consoleReader != null) {
            try {
                consoleReader.close();
            } catch (IOException streamCloseException) {
                System.out.println("Failed to release console stream: " + streamCloseException.getMessage());
            }
        }
    }
}