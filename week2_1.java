import java.util.Scanner;

public class week2_1 {

    public static void countVowelsAndConsonants(String text) {
        try {
            if (text == null) {
                throw new IllegalArgumentException("Input text cannot be null.");
            }

            int vowelCount = 0;
            int consonantCount = 0;

            for (int index = 0; index < text.length(); index++) {
                char ch = text.charAt(index);

                if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                    char lowerCh = (ch >= 'A' && ch <= 'Z') ? (char) (ch + 32) : ch;

                    if (lowerCh == 'a' || lowerCh == 'e' || lowerCh == 'i' || lowerCh == 'o' || lowerCh == 'u') {
                        vowelCount++;
                    } else {
                        consonantCount++;
                    }
                }
            }

            System.out.println("Vowels: " + vowelCount + " | Consonants: " + consonantCount);
        } catch (Exception ex) {
            System.out.println("Error processing text: " + ex.getMessage());
        }
    }

    public static void parseStudentRecord(String csvLine) {
        try {
            if (csvLine == null) {
                System.out.println("Invalid Record");
                return;
            }

            String[] fields = csvLine.split(",");

            if (fields.length != 3) {
                System.out.println("Invalid Record");
                return;
            }

            String studentName = fields[0].trim();
            String rollNumber = fields[1].trim();
            String department = fields[2].trim();

            if (studentName.isEmpty() || rollNumber.isEmpty() || department.isEmpty()) {
                System.out.println("Invalid Record");
                return;
            }

            System.out.println("Name: " + studentName + " | Roll No: " + rollNumber + " | Dept: " + department);
        } catch (Exception ex) {
            System.out.println("Invalid Record");
        }
    }

    public static String validateFileExtension(String filename) {
        try {
            if (filename == null || filename.trim().isEmpty()) {
                return "Rejected — invalid file type";
            }

            int lastDotIndex = filename.lastIndexOf('.');
            if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
                return "Rejected — invalid file type";
            }

            String extension = filename.substring(lastDotIndex + 1);

            if (extension.equalsIgnoreCase("pdf") || 
                extension.equalsIgnoreCase("docx") || 
                extension.equalsIgnoreCase("zip")) {
                return "Accepted";
            } else {
                return "Rejected — invalid file type";
            }
        } catch (Exception ex) {
            return "Rejected — invalid file type";
        }
    }

    public static String maskPhoneNumber(String phone) {
        try {
            if (phone == null || phone.length() != 10) {
                return "Invalid phone number";
            }

            for (int index = 0; index < phone.length(); index++) {
                char ch = phone.charAt(index);
                if (ch < '0' || ch > '9') {
                    return "Invalid phone number";
                }
            }

            String lastFourDigits = phone.substring(6);
            StringBuilder maskedBuilder = new StringBuilder("XXXXXX");
            maskedBuilder.append(lastFourDigits);
            maskedBuilder.insert(6, "-");

            return maskedBuilder.toString();
        } catch (Exception ex) {
            return "Invalid phone number";
        }
    }

    public static String normalizeReference(String raw) {
        try {
            if (raw == null) {
                return "";
            }

            String trimmedRef = raw.trim();
            if (trimmedRef.length() < 3) {
                return trimmedRef;
            }

            String prefix = trimmedRef.substring(0, 3).toUpperCase();
            String remaining = trimmedRef.substring(3);

            return prefix + remaining;
        } catch (Exception ex) {
            return "";
        }
    }

    public static String validateAndFormat(String reference) {
        try {
            if (reference == null || reference.length() != 14) {
                return "Invalid: wrong length";
            }

            for (int index = 0; index < 3; index++) {
                char ch = reference.charAt(index);
                if (!Character.isLetter(ch)) {
                    return "Invalid: bank code must be 3 letters";
                }
            }

            for (int index = 3; index < 14; index++) {
                char ch = reference.charAt(index);
                if (!Character.isDigit(ch)) {
                    return "Invalid: body must contain only digits";
                }
            }

            String bankCode = reference.substring(0, 3);
            String day = reference.substring(3, 5);
            String month = reference.substring(5, 7);
            String year = reference.substring(7, 9);
            String sequenceNumber = reference.substring(9, 14);

            StringBuilder formattedBuilder = new StringBuilder();
            formattedBuilder.append("[")
                            .append(bankCode)
                            .append("] DATE: ")
                            .append(day)
                            .append("/")
                            .append(month)
                            .append("/")
                            .append(year)
                            .append(" | SEQ: ")
                            .append(sequenceNumber);

            return formattedBuilder.toString();
        } catch (Exception ex) {
            return "Invalid: processing error";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            String bookTitle = "Java Programming";
            countVowelsAndConsonants(bookTitle);

            String validCsv = "Ananya Verma, RA2211003010123,CSE";
            String invalidCsv = "Ananya Verma,CSE";
            parseStudentRecord(validCsv);
            parseStudentRecord(invalidCsv);

            String validFile = "Assignment1.PDF";
            String invalidFile = "notes.txt";
            System.out.println(validateFileExtension(validFile));
            System.out.println(validateFileExtension(invalidFile));

            String validPhone = "9876543210";
            String invalidPhone = "98765";
            System.out.println(maskPhoneNumber(validPhone));
            System.out.println(maskPhoneNumber(invalidPhone));

            String rawRef1 = "  hdf03022600042 ";
            String rawRef2 = "12F03022600042";
            String normalizedRef1 = normalizeReference(rawRef1);
            String normalizedRef2 = normalizeReference(rawRef2);
            System.out.println(validateAndFormat(normalizedRef1));
            System.out.println(validateAndFormat(normalizedRef2));
        } catch (Exception ex) {
            System.out.println("Execution error: " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }
}