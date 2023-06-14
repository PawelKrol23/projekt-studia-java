package com.example.projekt_studia_java.security;

import com.example.projekt_studia_java.domain.db.UzytkownikEntity;
import com.example.projekt_studia_java.repositories.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UzytkownikAutoryzacjaService implements UserDetailsService {
    @Autowired
    UzytkownikRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UzytkownikEntity user = users.findByLogin(username);

        if(user == null)
            throw new UsernameNotFoundException("User " +username + " not found !");
        else {
            UserDetails userDetails = new User(
                    user.getLogin(),
                    user.getHaslo(),
                    user.getRole().stream()
                            .map(role-> new SimpleGrantedAuthority(role.getRola()))
                            .collect(Collectors.toSet()));
            return userDetails;
        }
    }

}
