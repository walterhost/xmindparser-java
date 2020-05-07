package org.liufree.xmindparser.tree;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class ToString implements Serializable {
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
