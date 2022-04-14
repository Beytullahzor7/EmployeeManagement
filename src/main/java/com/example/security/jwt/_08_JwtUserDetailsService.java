package com.example.security.jwt;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Log4j2
public class _08_JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private _03_UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       _02_UserEntity userEntity = (_02_UserEntity) userRepository.findByUserName(username);

       if(userEntity == null){
           log.error("Kullanıcı Bulunamadı");
           throw new UsernameNotFoundException("Boyle bir kullanıcı yoktur " + username);
       }

       return new org.springframework.security.core.userdetails.User(userEntity.getUserName(), userEntity.getPassword()
               ,new ArrayList<>());

    }

    public _02_UserEntity save(_01_UserDto userDto){

        _02_UserEntity newUser = new _02_UserEntity();
        newUser.setUserName(userDto.getUserName());
        newUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));

        return userRepository.save(newUser);
    }
}
