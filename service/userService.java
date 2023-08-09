package com.ye13sh.service;

import com.ye13sh.controller.AdminRepository;
import com.ye13sh.controller.AuthorityRepository;
import com.ye13sh.controller.UserRepository;
import com.ye13sh.dto.userDto;
import com.ye13sh.mapper.mapuser;
import com.ye13sh.model.Admin;
import com.ye13sh.model.Authority;
import com.ye13sh.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


@Service
public class userService {
    @Autowired
    mapuser map;
    @Autowired
    UserRepository repository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;


    public userDto InsertUser(userDto dto){
        return map.uDto(repository.save(map.uEntity(dto)));
    }

    public userDto InsertUserAuthority(userDto dto){
        return map.authorityDto(authorityRepository.save(map.authorityEntityForUser(dto)));
    }

    public userDto InsertAdmin(userDto dto){
        return map.adminDto(adminRepository.save(map.adminEntity(dto)));
    }

    public userDto InsertAdminAuthority(userDto dto){
        return map.authorityDto(authorityRepository.save(map.authorityEntityForAdmin(dto)));
    }


//    @Transactional
//    public void joinUserTables() {
//        //String sql = "INSERT INTO authority (role, unique_id) SELECT u.role, u.unique_id FROM users u";
//        String sql = "INSERT INTO authority (role, unique_id) " +
//                "SELECT u.role, u.unique_id FROM users u " +
//                "JOIN authority a ON u.role = a.role AND u.unique_id = a.unique_id";
//        jdbcTemplate.update(sql);
//    }


    public userDto GetUser(String username){
        return map.uDto(repository.findByUsername(username));
    }


    public userDto GetAdmin(String username){
        return map.adminDto(adminRepository.findByUsername(username));
    }

    public userDto updateUser(String username, userDto dto){
        User u= repository.findByUsername(username);

        u.setUsername(dto.getUsername());
        u.setPassword(dto.getPassword());
        u.setName(dto.getName());
        u.setMobileno(dto.getMobileno().replaceAll("([0-9]{1})([0-9]{0})([0-9]{5})","$1*****$2"));
        u.setEmail(dto.getEmail());

        return map.uDto(repository.save(u));
    }

    public userDto updateUserAuthority(userDto dto,String username){
        Authority authority= authorityRepository.findByUsername(username).get();

        authority.setUsername(dto.getUsername());
        authority.setPassword(dto.getPassword());
        authority.setMobileno(dto.getMobileno());
        authority.setEnabled(dto.isEnabled());

        return map.authorityDto(authorityRepository.save(authority));
    }

    public userDto updateAdmin(userDto dto,String username){
        Admin admin= adminRepository.findByUsername(username);

        admin.setUsername(dto.getUsername());
        admin.setPassword(dto.getPassword());
        admin.setName(dto.getName());
        admin.setMobileno(dto.getMobileno().replaceAll("([0-9]{1})([0-9]{0})([0-9]{5})","$1*****$2"));
        admin.setEmail(dto.getEmail());

        return map.adminDto(adminRepository.save(admin));
    }

    public userDto updateAdminAuthority(userDto dto,String username){
        Authority authority= authorityRepository.findByUsername(username).get();

        authority.setUsername(dto.getUsername());
        authority.setPassword(dto.getPassword());
        authority.setMobileno(dto.getMobileno());

        return map.authorityDto(authorityRepository.save(authority));
    }

    public void DeleteUser(String username){
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(new TransactionCallbackWithoutResult(){
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                repository.deleteByUsername(username);
                authorityRepository.deleteByUsername(username);
            }
        });

    }
    public void DeleteAdmin(String username){
        TransactionTemplate transactionTemplate=new TransactionTemplate(transactionManager);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                adminRepository.deleteByUsername(username);
                repository.deleteByUsername(username);
            }
        });
    }

// If user is enabled only token will be generated (keep CreateToken method in config package!)

//    public String CreateToken(Map<Object,String> claims,String username){
//        Authority authority=new Authority();
//
//        if (authority.isEnabled()==true){
//            //return (generate jwt token function/logic)
//        }else {
//             throw new RuntimeException("User has no Authorization to login!/User has been Suspended!!!");
//        }
//        return "Successfully Logged in!!!";
//    }

}
