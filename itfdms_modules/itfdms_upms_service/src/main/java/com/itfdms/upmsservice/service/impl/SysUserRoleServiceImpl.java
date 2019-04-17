/*
 *    Copyright (c) 2018-2025, lxr All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lxr (wangiegie@gmail.com)
 */

package com.itfdms.upmsservice.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itfdms.upmsservice.mapper.SysUserRoleMapper;
import com.itfdms.upmsservice.model.entity.SysUserRole;
import com.itfdms.upmsservice.service.SysUserRoleService;
import org.springframework.stereotype.Service;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service.impl
 * @ClassName: SysUserRoleServiceImpl
 * @Description: 用户角色表 服务实现类
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:55
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:55
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    /**
     * 方法实现说明
     *
     * @param userId 用户ID
     * @return boolean
     * @throws
     * @className: SysUserRoleServiceImpl
     * @methodName
     * @description: 根据用户Id删除该用户的角色关系
     * @author lxr
     * @createDate 2018-08-30 21:50
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:50
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public Boolean deleteByUserId(Integer userId) {
        return baseMapper.deleteByUserId(userId);
    }
}
