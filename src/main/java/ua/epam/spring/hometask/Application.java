package ua.epam.spring.hometask;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Application {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("********************************************");
        System.out.println("* Application for managing a movie theater *");
        System.out.println("********************************************");
        System.out.println("...");

        Auditorium mainRoom = new Auditorium("mainRoom", 20*15); // 20 line, 15 seats/line
        mainRoom.setVipSeats(LongStream.rangeClosed(271, 300).boxed().collect(Collectors.toSet()));

        Event ironman1 = new Event("Ironman 1.", 5.0, EventRating.MID);
        Event ironman2 = new Event("Ironman 2.", 7.0, EventRating.HIGH);
        Event ironman3 = new Event("Ironman 3.", 7.5, EventRating.MID);

        System.out.println("Please, add your firstname: ");
        String firstName = scanner.nextLine();
        System.out.println("Please, add your lastname: ");
        String lastName = scanner.nextLine();
        System.out.println("Please, add your email: ");
        String email = scanner.nextLine();
        User user = new User(firstName, lastName, email);
        System.out.println("Thank you, " + firstName + "\n Your registered data are: " + user.toString());




    }
}
