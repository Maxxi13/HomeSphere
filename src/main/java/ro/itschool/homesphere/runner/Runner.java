package ro.itschool.homesphere.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.itschool.homesphere.repositories.UserRepository;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

//        Customer customer = new Customer();
//        customer.setName("Alex");
//        customer.setPhone("0724987654");
//        customer.setEmail("alxe@gmail.com");
//        customer.setPassword("paine prajita");
//        customer.setUsername("Lex");
//
//        userRepository.save(customer);

    }
}
