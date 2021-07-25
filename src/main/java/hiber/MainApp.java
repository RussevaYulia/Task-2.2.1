package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Volkswagen Golf", 2828)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Citroen", 1234)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Peugeot", 2134)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("UAZ Patriot ", 3654)));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println();

      User user = userService.userWithCar("Citroen", 1234);
      System.out.println("Owner of Citroen \nid: " + user.getId() + " \n"
              + "firstname: " + user.getFirstName() + "\n"
              + "lastname: " + user.getLastName() + "\n"
              + "email: " + user.getEmail());

      context.close();
   }
}
