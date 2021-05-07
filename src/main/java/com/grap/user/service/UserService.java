//package com.grap.user.service;
//
//import com.grap.user.domain.User;
//import com.grap.user.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Transactional
//    public void signup(User user) {
//        userRepository.save(user);
//    }
//
//    @Transactional(readOnly = true) //start transaction when 'select'
//    public User login(User user) {
//        return userRepository.findByLoginIdAndPassword(user.getLoginId(), user.getPassword());
//    }
//}
//
