package com.itfdms.gateway.feign.fallback;

import com.itfdms.common.vo.MenuVO;
import com.itfdms.gateway.feign.MenuService;
import com.xiaoleilu.hutool.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.feign.fallback
 * @ClassName: MenuServiceFallbackImpl
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-22 17:28
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 17:28
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


@Slf4j
@Service
public class MenuServiceFallbackImpl implements MenuService {

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
    @Override
    public Set<MenuVO> findMenuByRole(String role) {
        log.error("调用{}异常{}", "findMenuByRole", role);
        return CollUtil.newHashSet();
    }
}
