package com.itfdms.upmsservice.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.util.Assert;
import com.itfdms.common.vo.MenuVO;
import com.itfdms.upmsservice.mapper.SysMenuMapper;
import com.itfdms.upmsservice.model.entity.SysMenu;
import com.itfdms.upmsservice.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.upmsservice.service.impl
 * @ClassName: SysMenuServiceImpl
 * @Description: 菜单权限表 服务实现类
 * @Author: lxr
 * @CreateDate: 2018-08-28 23:39
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 23:39
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

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
    @Override
    @Cacheable(value = "menu_details", key = "#role  + '_menu'")
    public List<MenuVO> findMenuByRoleName(String role) {
        return sysMenuMapper.findMenuByRoleName(role);
    }

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
    @Override
    @CacheEvict(value = "menu_details", allEntries = true)
    public Boolean deleteMenu(Integer id) {
        Assert.isNull(id, "菜单ID不能为空");
        //删除当前节点
        SysMenu currentNode = new SysMenu();
        currentNode.setMenuId(id);
        currentNode.setDelFlag(CommonConstant.STATUS_DEL);
        this.updateById(currentNode);


        SysMenu currentParentNode = new SysMenu();
        currentParentNode.setParentId(id);
        SysMenu sysMenu = new SysMenu();
        sysMenu.setDelFlag(CommonConstant.STATUS_DEL);
        return this.update(sysMenu, new EntityWrapper<>(currentParentNode));
    }

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
    @Override
    @CacheEvict(value = "menu_details", allEntries = true)
    public Boolean updateMenuById(SysMenu sysMenu) {
        return this.updateById(sysMenu);
    }
}
