package ua.epam.spring.hometask.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CounterAspect {
    private Map<String, Integer> eventAccessCounter = new HashMap<>();

    @AfterReturning(value = "execution(* ua.epam.spring.hometask.DAO.EventDAO.getEventByName(..))", returning = "result")
    private void countEventAccess(JoinPoint joinPoint, Event result) {
        String id = result.getName();
        eventAccessCounter.put(id, eventAccessCounter.containsKey(id) ?
                eventAccessCounter.get(id)+1 : 1);
        System.out.println("\t\t\tAspect-test -> '" + id + "' was accessed '" + eventAccessCounter.get(id) + " times'");
    }

    @AfterReturning("execution(* ua.epam.spring.hometask.domain.Event.getBasePrice())")
    private void countEventPriceAccess(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName());
        String id = joinPoint.getArgs()[0].toString() + "-price";
        eventAccessCounter.put(id, eventAccessCounter.containsKey(id) ?
                eventAccessCounter.get(id)+1 : 1);
        System.out.println("\t\t\tAspect-test -> The price of '" + id + "' was accessed '" + eventAccessCounter.get(id) + " times'");
    }

}
