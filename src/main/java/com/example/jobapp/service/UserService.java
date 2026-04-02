package com.example.jobapp.service;
import com.example.jobapp.dto.LoginRequest;
import com.example.jobapp.dto.RegisterRequest;
import com.example.jobapp.model.User;
import com.example.jobapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public User registerUser(RegisterRequest request){
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isPresent()){
            throw new RuntimeException("Email already existed");
        }
        String encodedPssword = passwordEncoder.encode(request.getPassword());
        User user =  new User(request.getEmail(),encodedPssword);
        User savedUser = userRepository.save(user);
        return  savedUser;
    }


    public User login(LoginRequest request){
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if(optionalUser.isEmpty()){
            throw  new RuntimeException("User not found");
        }
        User user = optionalUser.get();
        boolean matches  = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!matches){
            throw  new RuntimeException("invalid password");
        }
        return user;
    }




}
