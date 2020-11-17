package com.koabs.web.permission.service.impl;

import com.koabs.web.permission.model.User;
import com.koabs.web.permission.dao.UserMapper;
import com.koabs.web.permission.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 三一用户信息 服务实现类
 * </p>
 *
 * @author bk
 * @since 2020-08-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
