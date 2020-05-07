/**
  * Copyright 2020 bejson.com 
  */
package org.liufree.xmindparser.pojo.topic;

import org.liufree.xmindparser.pojo.Comments;
import org.liufree.xmindparser.pojo.Notes;
import org.liufree.xmindparser.tree.Node;

import java.util.List;

/**
 * Auto-generated: 2020-03-24 11:24:27
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TopicNode extends Node {
    private Notes notes;
    private List<Comments> comments;


    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}