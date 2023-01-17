package assn07;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> passwordManager = new PasswordManager<>();

        Boolean validMaster = false;
        while(!validMaster) {
            System.out.println("Enter Master Password");
            String input = scanner.nextLine().strip();
            Boolean check = passwordManager.checkMasterPassword(input);
            if(check) {
                validMaster=true;
            }
        }
        System.out.println("Welcome to the program!");
        Boolean active = true;
        while(active) {
            String newCommand = scanner.nextLine().strip();
            switch (newCommand) {
                case "New password" -> {
                    String newWebsite = scanner.nextLine().strip();
                    String newPassword = scanner.nextLine().strip();
                    passwordManager.put(newWebsite, newPassword);
                    System.out.println("New password added");
                }
                case "Get password" -> {
                    String website = scanner.nextLine().strip();
                    String pw = passwordManager.get(website);
                    if (pw != null) {
                        System.out.println(pw);
                    } else {
                        System.out.println("Account does not exist");
                    }
                }
                case "Delete account" -> {
                    String toDelete = scanner.nextLine().strip();
                    String deleted = passwordManager.remove(toDelete);
                    if (deleted != null) {
                        System.out.println("Account deleted");
                    } else {
                        System.out.println("Account does not exist");
                    }
                }
                case "Check duplicate password" -> {
                    String password = scanner.nextLine().strip();
                    List<String> check = passwordManager.checkDuplicate(password);
                    if (check.size() != 0) {
                        System.out.println("Websites using that password:");
                        for (int i = 0; i < check.size(); i++) {
                            System.out.println(check.get(i));
                        }
                    } else {
                        System.out.println("No account uses that password");
                    }
                }
                case "Get accounts" -> {
                    Iterator<String> accounts = passwordManager.keySet().iterator();
                    System.out.println("Your accounts:");
                    while (accounts.hasNext()) {
                        System.out.println(accounts.next());
                    }
                }
                case "Generate random password" -> {
                    Integer length = Integer.parseInt(scanner.nextLine().strip());
                    System.out.println(passwordManager.generateRandomPassword(length));
                }
                case "Exit" -> active = false;
                default -> System.out.println("Command not found");
            }
        }
    }
    }
