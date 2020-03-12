package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.ChangePasswordVM;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserRepository clientConsumer;
    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping("users")
    public List<UserDTO> findAll(){
        List<User> users =  customUserDetailsService.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(u -> {
            if (u.getId() != null){
                users.stream().filter(c -> c.getId().equals(u.getId())).findFirst().ifPresent(cl ->userDTOS.add(new UserDTO(u)));
            }
        });
        return userDTOS;
    }


    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public Boolean changePassword(@RequestBody ChangePasswordVM user)
    {

        return customUserDetailsService.changePassword(user, SecurityContextHolder.getContext().getAuthentication().getName());
    }
    @PostMapping("signup")
    public UserDTO signup(@RequestBody User clientUser){
        clientUser.setId(null);
        User client = clientConsumer.save(modelMapper.map(clientUser, User.class));
        User user = modelMapper.map(clientUser, User.class);
        user.setId(client.getId());
        return new UserDTO(customUserDetailsService.save(user));
    }
    @GetMapping("/user/activate/{id}")
    public boolean activate(@PathVariable("id") Long id ){
        User user = customUserDetailsService.findById(id);
        return false;
    }

//    @GetMapping("/users")
//    public List<User> getAll() {
//
//        return customUserDetailsService.findAll();
//
//    }


    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {

        return customUserDetailsService.findById(id);
    }

    @PostMapping("/user/add")
    public User addUser(@RequestBody User user) {
        return customUserDetailsService.save(user);
    }

    @DeleteMapping("/user/delete/{id}")
    public void delete(@PathVariable Long id) {
        customUserDetailsService.delete(id);
    }


    @PutMapping("/user/update/{id}")
    public User updateUser (@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return customUserDetailsService.update(user);
    }
}
