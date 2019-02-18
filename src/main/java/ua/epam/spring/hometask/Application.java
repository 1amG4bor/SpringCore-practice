package ua.epam.spring.hometask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UI_Service;

import java.util.Scanner;

@SpringBootApplication
public class Application {
    private static UI_Service uiService;
    private static Scanner scanner = new Scanner(System.in);
    private static User user = null;

    @Autowired
    public Application(UI_Service uiService) {
        Application.uiService = uiService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        System.out.println("**********************************************");
        System.out.println("** Application for managing a movie theater **");
        System.out.println("**********************************************");
        System.out.println("...");

        int option = 1;
        uiService.optionChooser(option);
        while (true) {
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            uiService.optionChooser(option);
        }
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Application.user = user;
    }

}
