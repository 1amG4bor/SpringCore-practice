package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.Application;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Service
public class UI_Service {
    private final String ADMIN = "admin";
    private CustomUserService userService;
    private CustomDomainObjectService domainService;
    private DaoService daoService;

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public UI_Service(CustomUserService userService, CustomDomainObjectService domainService, DaoService daoService) {
        this.userService = userService;
        this.domainService = domainService;
        this.daoService = daoService;
    }

    public void optionChooser(int option) {
        if (!checkProperOption(option)) {
            System.out.println("Invalid option!");
            return;
        }

        switch (option) {
            case 1:
                listOptions();
                break;
            case 2:
                login();
                break;
            case 3:
                register();
                break;
            case 4:
                listAuditoriums();
                break;
            case 5:
                listEvents();
                break;
            case 6:
                listUsers();
                break;
            case 0:
                exit();
        }
    }

    private boolean checkProperOption(int option) {
        IntStream validOptions = Application.getUser() == null ?
                IntStream.of(0, 1, 2, 3) : IntStream.of(0, 1, 4, 5);
        if (option == 6) return Application.getUser().getEmail().equals(ADMIN);
        return validOptions.anyMatch(i -> i==option);
    }


    private void listOptions() {
        String actualUsername = Application.getUser()!= null ? Application.getUser().getFirstName() : "'not logged in'";
        System.out.println("\n#####[user: " + actualUsername + " ]#####");
        System.out.println("************************");
        System.out.println("Options:");
        System.out.println("\t 1: List options");
        System.out.println("\t -----------");
        if (Application.getUser() == null) {
            System.out.println("\t 2: Login");
            System.out.println("\t 3: Register");
            System.out.println("\t -----------");
        } else {
            System.out.println("\t 4: List auditoriums");
            System.out.println("\t 5: List events");
            if (Application.getUser().getEmail().equals(ADMIN)) System.out.println("\t 6: List users");
            System.out.println("\t -----------");
        }
        if (Application.getUser() == null)
            System.out.println("\t 0: EXIT!");
        else
            System.out.println("\t 0: Logout!");
        System.out.println("************************");
    }

    private void login() {
        System.out.println("\nLOGIN PROCESS!");
        System.out.println("Please, add your email: ");
        String email = scanner.next();
        System.out.println("Please, add your password: ");
        String password = scanner.next();
        authentication(email, password);
        listOptions();
    }

    void authentication(String email, String password) {
        Application.setUser(userService.getUserByEmail(email));
        if (!Application.getUser().getHashedPassword().equals(cryptWithMD5(password))) {
            Application.setUser(null);
            System.out.println("Failed to login! Please, try again.");
            return;
        }
        System.out.println("Login was successful. Hi there, " + Application.getUser().getFirstName() + "!");
    }

    private void register() {
        System.out.println("\nREGISTRATION PROCESS!");
        System.out.println("Please, add your firstname: ");
        String firstName = scanner.next();
        System.out.println("Please, add your lastname: ");
        String lastName = scanner.next();
        if (firstName.toLowerCase().equals(ADMIN) || lastName.toLowerCase().equals(ADMIN)) {
            System.out.println("'admin' is reserved!");
            return;
        }
        System.out.println("Please, add your email: ");
        String email = scanner.next();
        LocalDate birthDate = askBirthDate();
        String password = askPassword();
        User newUser = new User(firstName, lastName, email, birthDate, password);
        userService.save(newUser);
        if (userService.save(newUser).equals(null)) {
            System.out.println("Some error has happened, please, restart the application!");
            System.exit(1);
        }
        Application.setUser(userService.getUserByEmail(newUser.getEmail()));
        System.out.println("Thank you, " + firstName + "!\n Your are successfully registered as: " + userService.getById(newUser.getId()).getFullName());
        listOptions();
    }

    private String askPassword() {
        String pass1 = ""; String pass2 = "";
        while (pass1 == "" || !pass1.equals(pass2)) {
            System.out.println("I will ask a password from you that can not be null!");
            System.out.println("Please, add your password: ");
            pass1 = scanner.next();
            System.out.println("Please, confirm your password: ");
            pass2 = scanner.next();
            if (!pass1.equals(pass2)) System.out.println("The passwords you entered do not match!");
        }
        return cryptWithMD5(pass1);
    }

    private LocalDate askBirthDate() {
        System.out.println("Please, add your year of birth: ");
        int year = scanner.nextInt();
        System.out.println("Please, add your month of birth (numeric): ");
        int month = scanner.nextInt();
        System.out.println("Please, add your day of birth: ");
        int day = scanner.nextInt();
        return LocalDate.of(year, month, day);
    }

    private void listAuditoriums() {
        System.out.println("All of the Auditoriums:");
        List<Auditorium> rooms = new ArrayList<>(daoService.getAuditoriumDAO().getAll());
        rooms.stream().forEach(item -> System.out.println(item.toString()));
        System.out.println();
    }

    private void listEvents() {
        System.out.println("All of the Events:");
        List<Event> events = new ArrayList<>(daoService.getEventDAO().getAll());
        events.stream().forEach(item -> {
            System.out.println(item.toString());
            daoService.getEventDAO().getEventByName(item.getName());
            item.getBasePrice();
                });
        System.out.println();
    }

    private void listUsers() {
        System.out.println("All of the Users:");
        List<User> users = new ArrayList<>(daoService.getUserDAO().getAll());
        users.stream().forEach(item -> System.out.println(item.toString()));
        System.out.println();
    }

    String cryptWithMD5(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = password.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void exit() {
        if (Application.getUser() == null) {
            System.out.println("Exit...");
            System.exit(0);
        }
        Application.setUser(null);
        System.out.println("You are logged out!");
        listOptions();
    }
}
