package com.cybercity.application.services;


import com.cybercity.application.dtos.UserDTO;
import com.cybercity.application.entities.UserEntity;
import com.cybercity.application.exceptions.UserNotFoundException;
import com.cybercity.application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDTO createNewUser(UserDTO inputDTO){
        UserEntity userEntity = modelMapper.map(inputDTO, UserEntity.class);
        UserEntity savedEntity= userRepository.save(userEntity);
        return modelMapper.map(savedEntity, UserDTO.class);
    }

    public UserDTO getUserById(Long userId){
        UserEntity userEntity=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
        return modelMapper.map(userEntity,UserDTO.class);
    }

    public UserDTO updateUserById(UserDTO updateDTO , Long userId){
        UserEntity user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
        UserEntity userEntity= modelMapper.map(updateDTO,UserEntity.class);
        userEntity.setUserId(userId);
        userEntity.setCreateDate(user.getCreateDate());
        userEntity.setUpdateDate(LocalDateTime.now());

        UserEntity savedEntity = userRepository.save(userEntity);
        return modelMapper.map(savedEntity, UserDTO.class);
    }
}
