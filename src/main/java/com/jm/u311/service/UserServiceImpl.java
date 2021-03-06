package com.jm.u311.service;

import com.jm.u311.repository.UserRepository;
import com.jm.u311.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @Override
    public void addUser(User user){
        userRepository.save(user);
    }
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }
}
