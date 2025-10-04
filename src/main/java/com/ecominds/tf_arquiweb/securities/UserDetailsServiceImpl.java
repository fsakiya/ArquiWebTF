package com.ecominds.tf_arquiweb.securities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecominds.tf_arquiweb.entidades.Users;
import com.ecominds.tf_arquiweb.repositorios.UsersRepositorio;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepositorio usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> 
            authorities.add(new SimpleGrantedAuthority(role.getRol()))
        );

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
