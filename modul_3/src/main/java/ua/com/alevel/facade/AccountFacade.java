package ua.com.alevel.facade;

import ua.com.alevel.dto.request.AccountRequestDto;
import ua.com.alevel.dto.response.AccountResponseDto;
import ua.com.alevel.entity.User;

public interface AccountFacade extends BaseFacade <AccountRequestDto, AccountResponseDto>{
    User findUserByAccountId(Long accountId);
}
