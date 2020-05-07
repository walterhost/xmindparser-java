/*
 * Copyright (c) 2019, XuyuanWang
 */
package org.liufree.xmindparser.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 节点模型
 *
 * @author XuyuanWang
 */
public class Node extends ToString implements Comparable<Node> {

    /**
     * ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级 ID
     */
    private String parentId;

    /**
     * 索引编号
     */
    private Integer indexNumber;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 子节点集合
     */
    private List<Node> children = new LinkedList<>();

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>parentId</tt>.
     *
     * @return property value of parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Setter method for property <tt>parentId</tt>.
     *
     * @param parentId value to be assigned to property parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * Getter method for property <tt>indexNumber</tt>.
     *
     * @return property value of indexNumber
     */
    public Integer getIndexNumber() {
        return indexNumber;
    }

    /**
     * Setter method for property <tt>indexNumber</tt>.
     *
     * @param indexNumber value to be assigned to property indexNumber
     */
    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

    /**
     * Getter method for property <tt>level</tt>.
     *
     * @return property value of level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Setter method for property <tt>level</tt>.
     *
     * @param level value to be assigned to property level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Getter method for property <tt>children</tt>.
     *
     * @return property value of children
     */
    public List<Node> getChildren() {
        return children;
    }

    /**
     * Setter method for property <tt>children</tt>.
     *
     * @param children value to be assigned to property children
     */
    public void setChildren(List<Node> children) {
        this.children = children;
    }

    /**
     * @see Comparable#compareTo(Object)
     */
    @Override
    public int compareTo(Node o) {
        return this.indexNumber - o.indexNumber;
    }

}