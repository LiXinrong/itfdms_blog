package com.itfdms.upmsservice.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itfdms.common.util.QueryPage;
import com.itfdms.upmsservice.model.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.upmsservice.mapper
 * @ClassName: SysRoleMapper
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-30 17:46
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 17:46
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
      * 方法实现说明
      * @className:      SysRoleMapper
      * @methodName
      * @description:    查询角色列表含有部门信息
      * @author          lxr
      * @createDate      2018-08-30 17:47
      * @updateUser:     lxr
      * @updateDate:     2018-08-30 17:47
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
     * @param query : 查询对象
     * @param condition 条件
      * @return : null
      * @exception
    **/

    List<Object> selectRolePage(QueryPage<Object> query, Map<String, Object> condition);


    /**
      * 方法实现说明
      * @className:      SysRoleMapper
      * @methodName
      * @description:    通过部门ID查询角色列表
      * @author          lxr
      * @createDate      2018-08-30 17:50
      * @updateUser:     lxr
      * @updateDate:     2018-08-30 17:50
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param deptId :  部门ID
      * @return : 角色列表
      * @exception       
    **/
    
    List<SysRole> selectListByDeptId(Integer deptId);

}
