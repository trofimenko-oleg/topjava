package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        for (UserMealWithExceed meal: list)
        {
            System.out.println(meal);
        }

    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> filteredList = new ArrayList<>();
        Map<LocalDate,Integer> dayCalories = new HashMap<>(); // day - calories
        for (UserMeal meal: mealList)
        {
            //if dayCalories map already contains some record of the same day, add meal.calories to that day, if not - create new record with the meal.
            if (dayCalories.containsKey(meal.getDateTime().toLocalDate()))
            {
                dayCalories.put(meal.getDateTime().toLocalDate(), dayCalories.get(meal.getDateTime().toLocalDate()) + meal.getCalories());
            }
            else
            {
                dayCalories.put(meal.getDateTime().toLocalDate(), meal.getCalories());
            }
        }
        for (UserMeal meal: mealList)
        {
            boolean exceed = dayCalories.get(meal.getDateTime().toLocalDate()) > caloriesPerDay;
            if (meal.getDateTime().toLocalTime().compareTo(startTime) >= 0  && meal.getDateTime().toLocalTime().compareTo(endTime) <= 0)
            {
                filteredList.add(new UserMealWithExceed(meal.getDateTime(), "", meal.getCalories(), exceed));
            }
        }
        return filteredList;
    }
}
