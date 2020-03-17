package com.example.demo.service;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.ChangePasswordVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CustomUserDetailsService implements UserDetailsService, IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new CustomUserDetails(userRepository.findUserByUsername(s));
    }

    public User findByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(s);
    }
    @Override
    public User save(UserDTO user){
       User newUser = new User();
        if(user.getId() != null)
            newUser.setId(user.getId());

            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setCin(user.getCin());

        if( user.getRoles() != null){

            for (String role: user.getRoles()) {
                newUser.getRoles().add(roleRepository.findRoleByName(role));
            }
        }
        else{
            newUser.getRoles().add(roleRepository.findRoleByName("MEDECIN"));

        }

        return userRepository.save(newUser);
        // return userRepository.save(user);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
    public boolean changePassword(ChangePasswordVM vm , String username){
        User user = userRepository.findUserByUsername(username);


        if (passwordEncoder.matches(vm.getOldPassword(),user.getPassword())){
            user.setPassword(passwordEncoder.encode(vm.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        else return false;

    }

    public User addRole(Long id, Long idR) {
        Role r = roleRepository.findById(idR).get();
        User us = this.findById(id);
        us.getRoles().add(r);
        return this.update(us);
    }
    @Bean
   public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
