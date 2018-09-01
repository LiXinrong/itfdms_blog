package com.itfdms.upmsservice.common.util;


import com.itfdms.upmsservice.model.dto.MenuTree;
import com.itfdms.upmsservice.model.dto.TreeNode;
import com.itfdms.upmsservice.model.entity.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 树生成相关工具类
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.upmsservice.common.util
 * @ClassName: TreeUtil
 * @Description: 树生成相关工具类
 * @Author: lxr
 * @CreateDate: 2018-08-28 22:06
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 22:06
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class TreeUtil {
    
    
    /**
      * 方法实现说明
      * @className:      TreeUtil
      * @methodName      build
      * @description:    双层for循环实现Tree
      * @author          lxr
      * @createDate      2018-08-28 22:19
      * @updateUser:     lxr
      * @updateDate:     2018-08-28 22:19
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param           treeNodes 传入的树节点列表
      * @return          
      * @exception       
    **/
    public static <T extends TreeNode> List<T> build(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }

            for (T it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.add(it);
                }
            }

        }
        return trees;
    }


    /**
      * 方法实现说明
      * @className:      TreeUtil
      * @methodName      buildByRecursive
      * @description:    java方法作用描述
      * @author          lxr
      * @createDate      2018-08-28 22:25
      * @updateUser:     lxr
      * @updateDate:     2018-08-28 22:25
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param
      * @return          treeNodes
      * @exception
    **/

    public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes){
            if (root.equals(treeNode.getParentId())){
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
      * 方法实现说明
      * @className:      TreeUtil
      * @methodName      findChildren
      * @description:    递归查询子节点
      * @author          lxr
      * @createDate      2018-08-28 22:24
      * @updateUser:     lxr
      * @updateDate:     2018-08-28 22:24
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param           treeNodes
      * @return
      * @exception
    **/

    public static <T extends TreeNode> T findChildren(T treeNode,List<T> treeNodes){
        for (T it : treeNodes) {
            if (treeNode.getId() == it.getParentId()){
                if (treeNode.getChildren() == null){
                    treeNode.setChildren(new ArrayList<TreeNode>());
                }
                treeNode.add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

    /**
      * 方法实现说明
      * @className:      TreeUtil
      * @methodName      bulidTree
      * @description:    通过SysMenu创建树节点
      * @author          lxr
      * @createDate      2018-08-28 22:32
      * @updateUser:     lxr
      * @updateDate:     2018-08-28 22:32
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param
      * @return
      * @exception
    **/

    public List<MenuTree> bulidTree(List<SysMenu> sysMenus,int root){
        List<MenuTree> trees = new ArrayList<>();
        MenuTree menuTree;
        for (SysMenu menu : sysMenus){
            menuTree = new MenuTree();
            menuTree.setId(menu.getMenuId());
            menuTree.setParentId(menu.getParentId());
            menuTree.setName(menu.getName());
            menuTree.setUrl(menu.getUrl());
            menuTree.setPath(menu.getPath());
            menuTree.setCode(menu.getPermission());
            menuTree.setLabel(menu.getName());
            menuTree.setComponent(menu.getComponent());
            menuTree.setIcon(menu.getIcon());
            trees.add(menuTree);
        }

        return TreeUtil.build(trees,root);
    }


}
