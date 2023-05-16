//package ro.itschool.homesphere.security.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ro.itschool.homesphere.entities.User;
//import ro.itschool.homesphere.repositories.UserRepository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//       List<SimpleGrantedAuthority> authorityList = user.getRoles().stream()
//            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
//            .collect(Collectors.toList());
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorityList);
//    }
//}
