package com.itfdms.upmsservice.service;

import com.baomidou.mybatisplus.service.IService;
import com.itfdms.common.vo.MenuVO;
import com.itfdms.upmsservice.model.entity.SysMenu;

import java.util.List;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service
 * @ClassName: SysMenuService
 * @Description: 菜单权限
 * @Author: lxr
 * @CreateDate: 2018-08-28 23:36
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 23:36
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-28
 **/
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 方法实现说明
     *
     * @param role : 角色名
     * @return : List<MenuVO>
     * @throws
     * @className: SysMenuService
     * @methodName findMenuByRoleName
     * @description: 通过角色名称查询URL 权限
     * @author lxr
     * @createDate 2018-08-28 23:36
     * @updateUser: lxr
     * @updateDate: 2018-08-28 23:36
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    List<MenuVO> findMenuByRoleName(String role);

    /**
     * 方法实现说明
     *
     * @param id : 菜单ID
     * @return : boolean 成功/失败
     * @throws
     * @className: SysMenuService
     * @methodName 级联删除菜单
     * @description: java方法作用描述
     * @author lxr
     * @createDate 2018-08-28 23:37
     * @updateUser: lxr
     * @updateDate: 2018-08-28 23:37
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean deleteMenu(Integer id);


    /**
     * 方法实现说明
     *
     * @param sysMenu : 菜单信息
     * @return : boolean 成功/失败
     * @throws
     * @className: SysMenuService
     * @methodName 更新菜单信息
     * @description: java方法作用描述
     * @author lxr
     * @createDate 2018-08-28 23:38
     * @updateUser: lxr
     * @updateDate: 2018-08-28 23:38
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean updateMenuById(SysMenu sysMenu);

}
