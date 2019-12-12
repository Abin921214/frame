package com.xbin.frame.utils;

import com.xbin.frame.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TreeUtil<T extends BaseEntity> {

    private T data;

    /*public TreeUtil(T data){
        this.data = data;
    }*/


    /**
     * 两层循环实现建树
     * @param treeNodes       传入的树节点列表
     * @param parentNodesId   父类节点ID  如果为空 默认为0
     * @return
     */
    public List<T> bulid(List<T> treeNodes, Integer parentNodesId) {

        if (parentNodesId == null){
            parentNodesId= 0;
        }

        List<T> trees = new ArrayList<>();

        for (T treeNode : treeNodes) {

            if (treeNode.getParentId() == parentNodesId) {
                trees.add(treeNode);
            }

            for (T it : treeNodes) {
                if (it.getParentId() == treeNode.getId() ) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<T>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     * @param treeNodes
     * @param parentNodesId   父类节点ID  如果为空 默认为0
     * @return
     */
    public List<T> buildByRecursive(List<T> treeNodes, Integer parentNodesId) {

        if (parentNodesId == null){
            parentNodesId= 0;
        }

        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            if (parentNodesId == treeNode.getParentId()) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public T findChildren(T treeNode,List<T> treeNodes) {
        treeNode.setChildren(new ArrayList<T>());

        for (T it : treeNodes) {
            if(treeNode.getId() == it.getParentId()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<T>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}
