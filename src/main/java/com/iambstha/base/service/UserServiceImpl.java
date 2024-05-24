package com.iambstha.base.service;

import com.iambstha.base.dto.UserReqDto;
import com.iambstha.base.dto.UserResDto;
import com.iambstha.base.entity.User;
import com.iambstha.base.repository.UserRepository;
import com.iambstha.base.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserLoadServiceImpl userLoadService;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final JwtUtil jwtUtil;

    @Override
    public Long save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getUserId();
    }

    @Override
    public UserResDto login(UserReqDto userReqDto) {
        UserResDto userResDto = new UserResDto();
        userResDto.setToken(jwtUtil.generateToken(userLoadService.loadUserByUsername(userReqDto.getUsername())));
        userResDto.setMessage("User successfully logged in.");
        return userResDto;
    }
}
