package com.itfdms.upmsservice.controller;

import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.util.Result;
import com.itfdms.common.vo.MenuVO;
import com.itfdms.common.web.BaseController;
import com.itfdms.upmsservice.common.util.TreeUtil;
import com.itfdms.upmsservice.model.dto.MenuTree;
import com.itfdms.upmsservice.model.entity.SysMenu;
import com.itfdms.upmsservice.service.SysMenuService;
import com.xiaoleilu.hutool.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.upmsservice.controller
 * @ClassName: ${CLASS_NAME}
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-30 17:07
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 17:07
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


@RestController
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 方法实现说明
     *
     * @param role : 角色名
     * @return : 菜单列表
     * @throws
     * @className: MenuController
     * @methodName findMenuByRole
     * @description: 通过角色名查询菜单
     * @author lxr
     * @createDate 2018-08-30 17:09
     * @updateUser: lxr
     * @updateDate: 2018-08-30 17:09
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @GetMapping(value = "findMenuByRole/{role}")
    public List<MenuVO> findMenuByRole(@PathVariable String role) {
        return sysMenuService.findMenuByRoleName(role);
    }

    /**
     * 方法实现说明
     *
     * @param
     * @return : null
     * @throws
     * @className: MenuController
     * @methodName userMenu
     * @description: 返回当前用户的菜单集合
     * @author lxr
     * @createDate 2018-08-30 17:11
     * @updateUser: lxr
     * @updateDate: 2018-08-30 17:11
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @GetMapping(value = "userMenu")
    public List<MenuTree> userMenu() {
        //获取符合条件的菜单
        Set<MenuVO> all = new HashSet<>();
        getRole().forEach(roleName -> all.addAll(sysMenuService.findMenuByRoleName(roleName)));

        List<MenuTree> menuTreeList = new ArrayList<>();
        all.forEach(menuVO -> {
            if (CommonConstant.MENU.equals(menuVO.getType())) {
                menuTreeList.add(new MenuTree(menuVO));
            }
        });
        CollUtil.sort(menuTreeList, Comparator.comparingInt(MenuTree::getSort));
        return TreeUtil.build(menuTreeList, -1);
    }


    /**
     * 方法实现说明
     *
     * @param roleName : 角色名称
     * @return : 属性集合
     * @throws
     * @className: MenuController
     * @methodName roleTree
     * @description: 返回角色的菜单集合
     * @author lxr
     * @createDate 2018-08-30 17:18
     * @updateUser: lxr
     * @updateDate: 2018-08-30 17:18
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @GetMapping(value = "roleTree/{roleName}")
    public List<Integer> roleTree(@PathVariable String roleName) {
        List<MenuVO> menuVOList = sysMenuService.findMenuByRoleName(roleName);
        List<Integer> menuList = new ArrayList<>();
        for (MenuVO menuVO : menuVOList) {
            menuList.add(menuVO.getMenuId());
        }
        return menuList;
    }

    /**
     * 方法实现说明
     *
     * @param id : 菜单ID
     * @return : 菜单详细信息
     * @throws
     * @className: MenuController
     * @methodName menu
     * @description: 通过ID查询菜单的详细信息
     * @author lxr
     * @createDate 2018-08-30 17:23
     * @updateUser: lxr
     * @updateDate: 2018-08-30 17:23
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping(value = "/{id}")
    public SysMenu menu(@PathVariable Integer id) {
        return sysMenuService.selectById(id);
    }


    /**
     * 方法实现说明
     *
     * @param sysMenu : 菜单对象
     * @return : success/false
     * @throws
     * @className: MenuController
     * @methodName menu
     * @description: 新增菜单
     * @author lxr
     * @createDate 2018-08-30 17:24
     * @updateUser: lxr
     * @updateDate: 2018-08-30 17:24
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @PostMapping
    public Result<Boolean> menu(@PathVariable SysMenu sysMenu) {
        return new Result<>(sysMenuService.insert(sysMenu));
    }


    /**
     * 方法实现说明
     *
     * @param id : 菜单ID
     * @return : success/false
     * @throws
     * @className: MenuController
     * @methodName menuDel
     * @description: 删除节点
     * 级联删除下级节点
     * @author lxr
     * @createDate 2018-08-30 17:27
     * @updateUser: lxr
     * @updateDate: 2018-08-30 17:27
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @DeleteMapping("/{id}")
    public Result<Boolean> menuDel(@PathVariable Integer id) {
        return new Result<>(sysMenuService.deleteMenu(id));
    }


    /**
     * 方法实现说明
     *
     * @param sysMenu : 菜单对象
     * @return : success/false
     * @throws
     * @className: MenuController
     * @methodName menuUpdate
     * @description: 修改菜单
     * @author lxr
     * @createDate 2018-08-30 17:29
     * @updateUser: lxr
     * @updateDate: 2018-08-30 17:29
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    public Result<Boolean> menuUpdate(@PathVariable SysMenu sysMenu) {
        return new Result<>(sysMenuService.updateMenuById(sysMenu));
    }


}
