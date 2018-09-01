package com.itfdms.gateway.feign;

import com.itfdms.common.vo.MenuVO;
import com.itfdms.gateway.feign.fallback.MenuServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.feign
 * @ClassName: ${CLASS_NAME}
 * @Description: 菜单控制接口
 * @Author: lxr
 * @CreateDate: 2018-08-22 17:26
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 17:26
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


@FeignClient(name = "itfdms-uoms-service", fallback = MenuServiceFallbackImpl.class)
public interface MenuService {

    /**
     * 方法实现说明
     *
     * @param role 角色名称
     * @return 菜单列表
     * @throws
     * @className: MenuService
     * @methodName findMenuByRole
     * @description: java方法作用描述
     * @author lxr
     * @createDate 2018-08-22 17:30
     * @updateUser: lxr
     * @updateDate: 2018-08-22 17:30
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Set<MenuVO> findMenuByRole(@PathVariable("role") String role);

}
