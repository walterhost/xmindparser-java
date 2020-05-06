/**
  * Copyright 2020 bejson.com 
  */
package org.liufree.xmindparser.pojo.topic;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Auto-generated: 2020-03-24 11:24:27
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */

@ToString
public class XmindCanvas {

    private String id;
    private String title;
    private List<TopicNode> topicNodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TopicNode> getTopicNodes() {
        return topicNodes;
    }

    public void setTopicNodes(List<TopicNode> topicNodes) {
        this.topicNodes = topicNodes;
    }
}