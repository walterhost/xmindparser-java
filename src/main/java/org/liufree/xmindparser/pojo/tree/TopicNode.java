/**
  * Copyright 2020 bejson.com 
  */
package org.liufree.xmindparser.pojo.tree;
import lombok.Data;
import org.liufree.xmindparser.pojo.Children;
import org.liufree.xmindparser.pojo.Comments;
import org.liufree.xmindparser.pojo.Notes;

import java.util.List;

/**
 * Auto-generated: 2020-03-24 11:24:27
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class TopicNode {

    private String id;
    private String title;
    private Notes notes;
    private List<Comments> comments;
    private List<TopicNode> subElements;
    private String parentId="0";



}