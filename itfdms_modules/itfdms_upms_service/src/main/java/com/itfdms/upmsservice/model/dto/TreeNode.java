package com.itfdms.upmsservice.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 树节点
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.upmsservice.model.dto
 * @ClassName: TreeNode
 * @Description: 树节点
 * @Author: lxr
 * @CreateDate: 2018-08-28 22:08
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 22:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Data
public class TreeNode {

    protected int id;
    protected int parentId;
    protected List<TreeNode> children = new ArrayList<>();

    public void add(TreeNode node){
        children.add(node);
    }


}
