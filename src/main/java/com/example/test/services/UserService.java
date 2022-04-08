package com.example.test.services;


import com.example.test.models.AppUser;
import com.example.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority ;
import org.springframework.security.core.userdetails.UserDetails ;
import org.springframework.security.core.userdetails.UserDetailsService ;
import org.springframework.security.core.userdetails.UsernameNotFoundException ;
import org.springframework.stereotype.Component;
import java.util.Collections ;
import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userRes = userRepo.findByEmail(email);
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not find User with email = " + email);
        AppUser user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }

    public int enableAppUser(String email) {
        return userRepo.enableAppUser(email);
    }

    public Optional<AppUser> findAppUser(String email){
        return userRepo.findByEmail(email);
    }

    public AppUser saveAppUser(AppUser user) {
        return userRepo.save(user);
    }

    public ResponseEntity<AppUser> updateAppUser(AppUser user) {

            AppUser existingAppUser = userRepo.findByEmail(user.getEmail()).orElseThrow(
                    () -> new UsernameNotFoundException("Could not find User with id = " + user.getEmail())
            );

            if (user.getUsername()!=null) existingAppUser.setUsername(user.getUsername());
            if (user.getEmail()!=null) existingAppUser.setEmail(user.getEmail());
            if (user.getPhone()!=null) existingAppUser.setPhone(user.getPhone());
            if (user.getRegion()!=null) existingAppUser.setRegion(user.getRegion());
            if (user.getImage()!=null) existingAppUser.setImage(user.getImage());

            userRepo.save(existingAppUser);

            return new ResponseEntity<>(HttpStatus.OK);
    }

    public Optional<AppUser> findAppUserById(int id) {
      return  userRepo.findById(id);
    }
}