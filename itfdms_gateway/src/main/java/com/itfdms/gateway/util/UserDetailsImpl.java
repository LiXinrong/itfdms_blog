package com.itfdms.gateway.util;

import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.constant.SecurityConstants;
import com.itfdms.common.vo.SysRole;
import com.itfdms.common.vo.UserVO;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.util
 * @ClassName: ${CLASS_NAME}
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-16 20:34
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-16 20:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class UserDetailsImpl implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String status;
    private List<SysRole> roleList = new ArrayList<>();


    public UserDetailsImpl(UserVO userVO) {
        this.username = userVO.getUserName();
        this.password = userVO.getPassWord();
        this.status = userVO.getDelFlag();
        this.roleList = userVO.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (SysRole role : roleList){
            authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }
        authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return StringUtils.equals(CommonConstant.STATUS_LOCK,status) ? true :false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return StringUtils.equals(CommonConstant.STATUS_NORMAL,status) ? true : false;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
