import java.util.Scanner;

public class CreateAccountApp {
    
    private static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        String fullName = getFullName();
        System.out.println();
        
        String password = getPassword();
        System.out.println();
        
        String msg = getSuccessMessage(fullName);
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
    
    private static String getSuccessMessage(String fullName) {
        int index = fullName.indexOf(" ");
        String firstName = fullName.substring(0,1).toUpperCase() +  
                           fullName.substring(1, index).toLowerCase();        
        return "Hi " + firstName + ", thanks for creating an account!\n";
    }
}