package com.itfdms.upmsservice.model.dto;

import com.itfdms.common.vo.MenuVO;
import lombok.Data;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.upmsservice.model.dto
 * @ClassName: MenuTree
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-28 22:27
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 22:27
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Data
public class MenuTree extends TreeNode {

    private String icon;
    private String name;
    private String url;
    private boolean spread = false;
    private String path;
    private String component;
    private String authority;
    private String redirect;
    private String code;
    private String type;
    private String label;
    private Integer sort;

    public MenuTree() {
    }

    public MenuTree(int id, String name, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.label = name;
    }

    public MenuTree(int id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
        this.label = name;
    }

    public MenuTree(MenuVO menuVo) {
        this.id = menuVo.getMentId();
        this.parentId = menuVo.getParentId();
        this.icon = menuVo.getIcon();
        this.name = menuVo.getMenuName();
        this.url = menuVo.getUrl();
        this.path = menuVo.getPath();
        this.component = menuVo.getComponent();
        this.type = menuVo.getType();
        this.label = menuVo.getMenuName();
        this.sort = menuVo.getSort();
    }
}
