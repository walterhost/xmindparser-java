/*
 * Copyright (c) 2019, XuyuanWang
 */
package org.liufree.xmindparser.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 无限级树形构建工具
 *
 * @author XuyuanWang
 */
public final class Builder {

    /**
     * 根节点 ID
     */
    public static final String ROOT_NODE_ID = "root";

    private Builder() {
    }

    /**
     * 构建树形
     *
     * @param nodes
     * @return
     */
    public static List<? extends Node> buildTree(List<? extends Node> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }

        final List<Node> topNodes = new LinkedList<>();
        for (Node node : nodes) {
            if (ROOT_NODE_ID.equals(node.getParentId())) {
                topNodes.add(node);
                fillChildren(node, nodes);
            }
        }
        // 对顶级节点进行排序
      //  Collections.sort(topNodes);
        return topNodes;
    }

    /**
     * 填充子节点（递归，深度优先）
     *
     * @param parentNode
     * @param nodes
     */
    private static void fillChildren(Node parentNode, List<? extends Node> nodes) {
        final String parentId = parentNode.getId();
        final List<Node> children = new LinkedList<>();
        for (Node node : nodes) {
            if (node.getParentId().equals(parentId)) {
                children.add(node);
                fillChildren(node, nodes);
            }
        }
        // 对子节点进行排序
       // Collections.sort(children);
        parentNode.setChildren(children);
    }

}