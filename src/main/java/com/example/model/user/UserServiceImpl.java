package com.example.model.user;

import com.example.model.Status;
import com.example.model.role.Role;
import com.example.model.role.RoleName;
import com.example.model.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            log.info("User not found in the database");
//            throw new UsernameNotFoundException("User not found in the database");
//        } else log.info("User found in the database {}", username);
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
//        });
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getUsername(), authorities);
//    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName(RoleName.ROLE_USER);
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);
        user.setPassword(user.getPassword());
        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);
        User registerUser = userRepository.save(user);
        log.info("Saving new user {} to the database", registerUser);
        return registerUser;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        log.info("findById {} ", result);
        return result;
    }

    @Override
    public void delete(Long id) {
    userRepository.deleteById(id);

    }

    @Override
    public User findByUsername(String username) {
        //        log.info("findByUsername user {} ", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("getAll user {}", result.size());
        return result;
    }
}