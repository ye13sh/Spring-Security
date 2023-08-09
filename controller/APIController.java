package com.ye13sh.controller;

import com.ye13sh.dto.userDto;
import com.ye13sh.model.Admin;
import com.ye13sh.model.Authority;
import com.ye13sh.model.User;
import com.ye13sh.service.jwtService;
import com.ye13sh.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;



@RestController
public class APIController {
    @Autowired
    userService service;
    @Autowired
    UserRepository repository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    jwtService jwtSer;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/insert/user")
    public ResponseEntity<String> InsertUser(@RequestBody userDto dto) {
        try {
             service.InsertUser(dto);
             service.InsertUserAuthority(dto);
             //service.joinUserTables();
            System.out.println("Three services are executing under same api (/insert)");
        } catch (Exception e) {
            throw new RuntimeException("[Three services are not executing under same api]"+""+e);
        }
        return ResponseEntity.ok("Successfully saved User And Authority data!!!");
    }
    @PostMapping("/insert/admin")
    public ResponseEntity<String> InsertAdmin(@RequestBody userDto dto){
        try {
            service.InsertAdmin(dto);
            service.InsertAdminAuthority(dto);
            System.out.println("Both the Admin services are executing under same api (/insert)");
        }catch (Exception e){
            throw new RuntimeException("[Both the Admin services are not executing under same api]"+""+e);
        }
        return ResponseEntity.ok("Successfully saved the Admin data!!!");
    }

    @GetMapping("/get/user/{username}")
   // @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<userDto> GetUser(@PathVariable("username") String username){
        return ResponseEntity.ok(service.GetUser(username));
    }
    @GetMapping("/get/admin/{username}")
    public ResponseEntity<userDto> GetAdmin(@PathVariable("username") String username){
        return ResponseEntity.ok(service.GetAdmin(username));
    }
    @PutMapping("/update/user/{username}")
    public ResponseEntity<String> updateUser(@RequestBody userDto dto,@PathVariable("username")String username){
       try {
           service.updateUser(username,dto);
           service.updateUserAuthority(dto,username);
           System.out.println("Both the User services are executing under same api (/update/user)");
       }catch (Exception e){
           throw new RuntimeException("[Both the User services are not executing under same api]"+""+e);
       }
       return ResponseEntity.ok("Updated User Successfully!!!");
    }
    @PutMapping("update/admin/{username}")
    public ResponseEntity<String> updateAdmin(@RequestBody userDto dto,@PathVariable("username")String username){
        try {
            service.updateAdmin(dto,username);
            service.updateAdminAuthority(dto,username);
            System.out.println("Both the Admin services are executing under same api (/update/admin)");
        }catch (Exception e){
                throw new RuntimeException("[Both the Admin services are not executing under same api]"+""+e);
        }
        return ResponseEntity.ok("Admin updated Successfully!!!");
    }

    @DeleteMapping("/delete/user/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
        User user=repository.findByUsername(username);
        Authority authority= authorityRepository.findByUsername(username).get();
        if (user != null && authority != null) {
            service.DeleteUser(username);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
    @DeleteMapping("/delete/admin/{username}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("username") String username){
        Admin admin=adminRepository.findByUsername(username);
        Authority authority= authorityRepository.findByUsername(username).get();

        if (admin!=null && authority!=null){
            service.DeleteAdmin(username);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/authenticate")
    public String AuthenticateToken(@RequestBody Authority authority){
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authority.getUsername(),authority.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtSer.generateToken(authority.getUsername());
        }else {
            throw new UsernameNotFoundException("Username not found!!!");
        }
    }


}
