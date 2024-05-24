package com.iambstha.base.service;

import com.iambstha.base.dto.UserReqDto;
import com.iambstha.base.dto.UserResDto;
import com.iambstha.base.entity.User;

public interface UserService {

    Long save(User user);

    UserResDto login(UserReqDto userReqDto);

}
