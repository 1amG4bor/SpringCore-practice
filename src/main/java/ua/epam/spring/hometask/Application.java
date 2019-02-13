package ua.epam.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.CustomDomainObjectService;
import ua.epam.spring.hometask.service.CustomUserService;
import ua.epam.spring.hometask.service.DaoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static CustomUserService userService;
    private static CustomDomainObjectService domainService;
    private static DaoService daoService;

    private static Scanner scanner = new Scanner(System.in);

    public Application(CustomUserService userService, CustomDomainObjectService domainService, DaoService daoService) {
        Application.userService = userService;
        Application.domainService = domainService;
        Application.daoService = daoService;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/resources/spring.xml");
        Application app = ctx.getBean("application", Application.class);
        ((ClassPathXmlApplicationContext) ctx).refresh();

        System.out.println("**********************************************");
        System.out.println("** Application for managing a movie theater **");
        System.out.println("**********************************************");
        System.out.println("...");

        System.out.println("Please, add your firstname: ");
        String firstName = scanner.nextLine();
        System.out.println("Please, add your lastname: ");
        String lastName = scanner.nextLine();
        System.out.println("Please, add your email: ");
        String email = scanner.nextLine();
        User newUser = new User(firstName, lastName, email, 1975, 01, 01);
        userService.save(newUser);
        if (userService.save(newUser).equals(null)) {
            System.out.println("Some error has happened, please, restart the application!");
            System.exit(1);
        }
        System.out.println("Thank you, " + firstName + "!\n Your are successfully registered as: " + userService.getById(newUser.getId()).getFullName());

        System.out.println("All of the Auditoriums:");
        List<Auditorium> rooms = new ArrayList<>(daoService.getAuditoriumDAO().getAll());
        rooms.stream().forEach(item -> System.out.println(item.toString()));
        System.out.println();
        System.out.println("All of the Events:");
        List<Event> events = new ArrayList<>(daoService.getEventDAO().getAll());
        events.stream().forEach(item -> System.out.println(item.toString()));
        System.out.println();
        System.out.println("All of the Users:");
        List<User> users = new ArrayList<>(daoService.getUserDAO().getAll());
        users.stream().forEach(item -> System.out.println(item.toString()));
        System.out.println();
        System.out.println("Exit...");
    }

    public static CustomDomainObjectService getDomainService() {
        return domainService;
    }
}
