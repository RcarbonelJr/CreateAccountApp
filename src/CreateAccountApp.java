/* Author: Randall Carbonel
 * Date: 7/27/24
 * Class: ASD216 - Java Programming
 * Assignment: Enhance the Create Account application presented in this chapter so it gets a valid email address and a
 *             valid phone number
 * Specifications:
 *      - Create and use a method to get a valid email address. To be valid, the address has to contain an @ sign and
 *        ends with “.com”.
 *      - Create and use a method to get a valid phone number. To do that, remove all spaces, dashes, parentheses, and
 *        periods from the number. Then, check to make sure the number consists of 10 characters that are digits.
 *      - When all the entries are valid, display the message shown below, including the phone number format that uses
 *        dots to group the digits
 *          - Hi (fistName), thanks for creating an account. We'll text your confirmation code to this number:
 *            xxx.xxx.xxxx
 */

import java.util.Scanner;

public class CreateAccountApp {
    
    private static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        String fullName = getFullName();
        System.out.println();
        
        String password = getPassword();
        System.out.println();

        String emailAddress = getEmailAddress();
        System.out.println();

        String phoneNumber = getPhoneNumber();
        System.out.println();
        
        String msg = getSuccessMessage(fullName, phoneNumber);
        System.out.println(msg);
    }

    private static String getFullName() {
        while(true) {
            System.out.print("Enter full name: ");
            String name = sc.nextLine().trim();
            if(name.contains(" ")) {
                return name;
            } else {
                System.out.println("You must enter your full name.\n");
            }
        }
    }

    private static String getPassword() {
        while(true) {
            System.out.print("Enter password: ");
            String password = sc.nextLine().trim();
            
            boolean isMinLength = false;
            boolean hasDigit = false;
            boolean hasUppercase = false;           

            if (password.length() >= 8) {
                isMinLength = true;
            }
            
            for (char c: password.toCharArray()) {
                if (Character.isDigit(c)) {
                    hasDigit = true;
                } else if (Character.isUpperCase(c)) {
                    hasUppercase = true;
                }
            }
            
            if (isMinLength && hasDigit && hasUppercase) {
                return password;
            } else {
                System.out.println("Password must be 8 characters or more\n" 
                    + "with at least one digit and one uppercase letter.\n");
            }
        }
    }

    private static String getEmailAddress() {
        while(true) {
            System.out.print("Enter E-mail address: ");
            String emailAddress = sc.nextLine().trim();

            boolean isValid = false;

            if (emailAddress.contains("@") &&  emailAddress.endsWith(".com")) {
                isValid = true;
            } else {
                System.out.println("Please enter a valid e-mail address.");
            }

            if (isValid) {
                return emailAddress;
            }
        }
    }

    private static String getPhoneNumber() {
        while (true) {
            System.out.print("Enter phone number: ");
            String phoneNumber = sc.nextLine().trim();

            boolean isCorrectLength = false;
            boolean isDigits = true;

            phoneNumber = phoneNumber.replace(" ", "");
            phoneNumber = phoneNumber.replace("-", "");
            phoneNumber = phoneNumber.replace("(", "");
            phoneNumber = phoneNumber.replace(")", "");
            phoneNumber = phoneNumber.replace(".", "");

            if (phoneNumber.length() == 10) {
                isCorrectLength = true;
            }

            for (char c: phoneNumber.toCharArray()) {
                if (Character.isDigit(c)) {
                    continue;
                } else {
                    isDigits = false;
                    break;
                }
            }

            if (isCorrectLength && isDigits) {
                return phoneNumber;
            } else {
                System.out.println("Please enter a 10-digit phone number.");
            }
        }
    }
    
    private static String getSuccessMessage(String fullName, String phoneNumber) {
        int index = fullName.indexOf(" ");
        String firstName = fullName.substring(0,1).toUpperCase() +  
                           fullName.substring(1, index).toLowerCase();

        String areaCode = phoneNumber.substring(0,3);
        String centralOfficeCode = phoneNumber.substring(3,6);
        String lineNumber = phoneNumber.substring(6);

        String fullNumber = areaCode + "." + centralOfficeCode + "." + lineNumber;

        return  "Hi " + firstName + ", thanks for creating an account. We'll text your confirmation code to this " +
                "number: " + fullNumber;
    }
}