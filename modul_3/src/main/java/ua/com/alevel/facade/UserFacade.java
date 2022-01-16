package ua.com.alevel.facade;

import ua.com.alevel.dto.request.UserRequestDto;
import ua.com.alevel.dto.response.UserResponseDto;

import java.util.Map;

public interface UserFacade extends BaseFacade<UserRequestDto, UserResponseDto> {

    Map<Long, String> findAllAccountsByUserId(Long userId);
}
