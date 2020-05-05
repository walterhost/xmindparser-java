/**
  * Copyright 2020 bejson.com 
  */
package org.liufree.xmindparser.pojo.tree;
import lombok.Data;
import lombok.ToString;
import org.liufree.xmindparser.pojo.RootTopic;

import java.util.List;

/**
 * Auto-generated: 2020-03-24 11:24:27
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@ToString
public class XmindCanvas {

    private String id;
    private String title;
    private List<TopicNode> topicNodes;

}