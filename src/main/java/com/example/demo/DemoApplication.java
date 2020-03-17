package com.example.demo;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CustomUserDetailsService ur;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);}

    @Override
    public void run(String... args) throws Exception {

        if(roleRepository.findAll().isEmpty()){
            roleRepository.save(new Role( "ADMIN", "ADMIN"));
            roleRepository.save(new Role( "PATIENT", "PATIENT"));
            roleRepository.save(new Role( "MEDECIN", "MEDECIN"));
            roleRepository.save(new Role( "PHARMARCIEN", "PHARMARCIEN"));
            roleRepository.save(new Role( "DIRPHARMACIE", "DIRPHARMACIE"));
            roleRepository.save(new Role( "BIOLOGISTE", "BIOLOGISTE"));
            roleRepository.save(new Role( "CHEFSERVICEHEBERG", "CHEFSERVICEHEBERG"));
            roleRepository.save(new Role( "INFIRMIER", "INFIRMIER"));
            roleRepository.save(new Role( "CHEFSERVICEEQ", "CHEFSERVICEEQ"));
        }
        User us = new User();
        us.setPassword("karim"); us.setEmail("karimea"); us.setUsername("tta");
        Role r = roleRepository.findRoleByName("MEDECIN");
        ArrayList<Role> x = new ArrayList<>(); x.add(r);
        us.setRoles(x);
        // this.ur.save(us);
    }
    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return ServerCodecConfigurer.create();
    }

}
