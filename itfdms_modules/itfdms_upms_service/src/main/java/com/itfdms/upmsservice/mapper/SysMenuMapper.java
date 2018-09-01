package com.itfdms.upmsservice.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itfdms.common.vo.MenuVO;
import com.itfdms.upmsservice.model.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.upmsservice.mapper
 * @ClassName: SysMenuMapper
 * @Description: 菜单权限表 Mapper 接口
 * @Author: lxr
 * @CreateDate: 2018-08-28 22:56
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 22:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

public interface SysMenuMapper extends BaseMapper<SysMenu> {


    /**
     * 方法实现说明
     *
     * @param role : 角色名
     * @return : List<MenuVO>
     * @throws
     * @className: SysMenuMapper
     * @methodName
     * @description: 根据角色名查询菜单列表
     * @author lxr
     * @createDate 2018-08-28 23:33
     * @updateUser: lxr
     * @updateDate: 2018-08-28 23:33
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    List<MenuVO> findMenuByRoleName(@Param("role") String role);

}
