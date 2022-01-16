package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.UserRequestDto;
import ua.com.alevel.dto.response.UserResponseDto;
import ua.com.alevel.entity.User;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.service.UserService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void create(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        userService.create(user);
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public UserResponseDto findById(Long id) {
        return new UserResponseDto(userService.findById(id));
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userService.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, String> findAllAccountsByUserId(Long userId) {
        return userService.findAllAccountsByUserId(userId);
    }
}
