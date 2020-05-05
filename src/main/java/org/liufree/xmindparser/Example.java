package org.liufree.xmindparser;

import com.alibaba.fastjson.JSON;
import org.apache.commons.compress.archivers.ArchiveException;
import org.dom4j.DocumentException;
import org.liufree.xmindparser.pojo.Attached;
import org.liufree.xmindparser.pojo.Canvas;
import org.liufree.xmindparser.pojo.tree.TopicNode;
import org.liufree.xmindparser.pojo.tree.XmindCanvas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liufree liufreeo@gmail.com
 * @Classname Example
 * @Description 测试例子
 * @Date 2020/4/28 13:12
 */
public class Example {

    public static void main(String[] args) throws DocumentException, ArchiveException, IOException {
        String fileName = "doc/XmindZen解析.xmind";
        //   String fileName = "doc/Xmind8解析.xmind";
        String res = XmindParser.parseJson(fileName);
        Canvas canvas = XmindParser.parseCanvas(fileName);
        //System.out.println(canvas);
        XmindCanvas xmindCanvas = XmindParser.parseXmindCanvas(fileName);
       System.out.printf(JSON.toJSONString(xmindCanvas,true));
        System.out.printf("\n\n=========");
      //  System.out.printf(JSON.toJSONString(XmindParser.start("2rqnlssbbolniqnjuv4ufkjh11",xmindCanvas.getTopicNode().getChildren()),true));

       // System.out.printf(JSON.toJSONString(XmindParser.groupByParentId(xmindCanvas.getTopicNode().getChildren()),true));
        // Object root = XmindParser.parseObject(fileName);
        //  System.out.println(root);


    }

}
