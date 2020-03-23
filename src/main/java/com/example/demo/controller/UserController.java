package com.example.demo.controller;


import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.RoleService;
import com.example.demo.util.ChangePasswordVM;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserRepository clientConsumer;
    @Autowired
    private RoleRepository roleRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping("users")
    public List<User> findAll(HttpServletResponse response){
        // response.setHeader("username", "karim");
        List<User> users =  customUserDetailsService.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(u -> {
            if (u.getId() != null){
                users.stream().filter(c -> c.getId().equals(u.getId())).findFirst().ifPresent(cl ->userDTOS.add(new UserDTO(u)));
            }
        });
        return users;
    }


    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public Boolean changePassword(@RequestBody ChangePasswordVM user)
    {

        return customUserDetailsService.changePassword(user, SecurityContextHolder.getContext().getAuthentication().getName());
    }
    /*@PostMapping("signup")
    public User signup(@RequestBody UserDTO clientUser){
        // clientUser.setId(null);
        User client = clientConsumer.save(modelMapper.map(clientUser, User.class));
        User user = modelMapper.map(clientUser, User.class);
        user.setId(client.getId());
        return customUserDetailsService.save(clientUser);
         //return new UserDTO(customUserDetailsService.save(clientUser));
    }*/

    @PostMapping("/user")
    public UserDTO saveUSer(@RequestBody UserDTO clientUser){
        clientUser.setId(null);
       // User client = clientConsumer.save(modelMapper.map(clientUser, User.class));
       //  User user = modelMapper.map(clientUser, User.class);
       // user.setId(client.getId());
        return new UserDTO(customUserDetailsService.save(clientUser));
        // return new UserDTO(client, customUserDetailsService.save(user));
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

    @GetMapping("/user")
    public User getUserByToken() {
        return customUserDetailsService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /*@PostMapping("/user/add")
    public User addUser(@RequestBody User user) {
        return customUserDetailsService.save(user);
    }
*/
    @DeleteMapping("/user/delete/{id}")
    public void delete(@PathVariable Long id) {
        customUserDetailsService.delete(id);
    }


    @PutMapping("/user")
    public User updateUser (@RequestBody User user) {
        return customUserDetailsService.update(user);
    }

    @PutMapping("/user/{id}/role/{idR}")
    public User addRole (@PathVariable("id") Long id, @PathVariable("idR") Long idR) {
        return customUserDetailsService.addRole(id, idR);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public UserDetails getUserByAuth() {
        return customUserDetailsService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        return modelMapper.map(userService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()),UserDto.class);
    }
}
