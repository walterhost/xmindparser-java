package org.liufree.xmindparser;

import com.alibaba.fastjson.JSON;
import org.apache.commons.compress.archivers.ArchiveException;
import org.dom4j.DocumentException;
import org.liufree.xmindparser.pojo.Attached;
import org.liufree.xmindparser.pojo.Canvas;
import org.liufree.xmindparser.pojo.topic.TopicNode;
import org.liufree.xmindparser.pojo.topic.XmindCanvas;
import org.liufree.xmindparser.tree.Builder;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liufree liufreeo@gmail.com
 * @Classname XmindParser
 * @Description 解析主体
 * @Date 2020/4/27 14:05
 */
public class XmindParser {
    public static final String xmindZenJson = "content.json";
    public static final String xmindLegacyContent = "content.xml";
    public static final String xmindLegacyComments = "comments.xml";
    public static final int MAX_LEVEL = 3;

    /**
     * 解析脑图文件，返回content整合后的内容
     *
     * @param xmindFile
     * @return
     * @throws IOException
     * @throws ArchiveException
     * @throws DocumentException
     */
    public static String parseJson(String xmindFile) throws IOException, ArchiveException, DocumentException {
        String res = ZipUtils.extract(xmindFile);

        String content = null;
        if (isXmindZen(res, xmindFile)) {
            content = getXmindZenContent(xmindFile);
        } else {
            content = getXmindLegacyContent(xmindFile);
        }

        //删除生成的文件夹
        File dir = new File(res);
        boolean flag = deleteDir(dir);
        if (flag) {
            // do something
        }
        Canvas canvas = JSON.parseObject(content, Canvas.class);
        return (JSON.toJSONString(canvas, false));
    }

    public static XmindCanvas getXmindCanvas(Canvas canvas) {
        XmindCanvas xmindCanvas = new XmindCanvas();
        xmindCanvas.setId(canvas.getId());
        xmindCanvas.setTitle(canvas.getTitle());
        List<TopicNode> result = new ArrayList<>();

        TopicNode topicNode = new TopicNode();
        topicNode.setComments(canvas.getRootTopic().getComments());
        topicNode.setId(canvas.getRootTopic().getId());
        topicNode.setNotes(canvas.getRootTopic().getNotes());
        topicNode.setName(canvas.getRootTopic().getTitle());
        topicNode.setParentId(Builder.ROOT_NODE_ID);
        List<TopicNode> topicNodeArrayList = new ArrayList<>();
        result.add(topicNode);

        List<Attached> list = canvas.getRootTopic().getChildren().getAttached();
        if (list != null && list.size() > 0) {
            topicNodeArrayList = fillParentId(list, topicNode.getId());
        }
        result.addAll(topicNodeArrayList);
        List<TopicNode> topicNodeTree = (List<TopicNode>) Builder.buildTree(result);
        xmindCanvas.setTopicNodes(topicNodeTree);
        return xmindCanvas;
    }

    private static List<TopicNode> fillParentId(List<Attached> list, String id) {
        if (list == null && list.size() == 0) {
            return new ArrayList<>();
        }
        List<TopicNode> topicNodes = new ArrayList<>();
        for (Attached attached : list) {
            TopicNode topicNode = new TopicNode();
            topicNode.setComments(attached.getComments());
            topicNode.setId(attached.getId());
            topicNode.setNotes(attached.getNotes());
            topicNode.setName(attached.getTitle());
            topicNode.setParentId(id);
            if (attached.getChildren() != null && attached.getChildren().getAttached().size() > 0) {
                topicNodes.addAll(fillParentId(attached.getChildren().getAttached(), topicNode.getId()));
            }
            topicNodes.add(topicNode);

        }
        return topicNodes;
    }


    public static Map<String, List<TopicNode>> groupByParentId(List<TopicNode> sources) {
        Map<String, List<TopicNode>> listGroupby = sources.parallelStream().collect(Collectors.groupingBy(TopicNode::getParentId));
        return listGroupby;
    }

    public static Map<String, List<TopicNode>> start(String id, List<TopicNode> sources) {
        Map<String, List<TopicNode>> parentMap = groupByParentId(sources);

        Map<String, List<TopicNode>> levelMap = new HashMap<>();
        getLevel(id, parentMap, levelMap, 1);
        return levelMap;
    }

    public static void getLevel(String parentId, Map<String, List<TopicNode>> parentMap, Map<String, List<TopicNode>> levelMap, int count) {
        //根据parentId获取节点
        List<TopicNode> nextLevel = parentMap.get(parentId);

        if (nextLevel == null || nextLevel.size() == 0)
            return;

        String countStr = String.valueOf(count);
        List<TopicNode> thisLevel = levelMap.get(countStr);
        if (thisLevel == null || thisLevel.size() == 0) {
            levelMap.put(countStr, nextLevel);
        } else {
            thisLevel.addAll(nextLevel);
            levelMap.put(countStr, thisLevel);
        }

        count++;
        if (count > MAX_LEVEL)
            return;

        for (TopicNode TopicNode : nextLevel) {
            String tempParentId = TopicNode.getId();
            getLevel(tempParentId, parentMap, levelMap, count);
        }
    }

    public static Canvas parseCanvas(String xmindFile) throws IOException, ArchiveException, DocumentException {
        String res = ZipUtils.extract(xmindFile);

        String content = null;
        if (isXmindZen(res, xmindFile)) {
            content = getXmindZenContent(xmindFile);
        } else {
            content = getXmindLegacyContent(xmindFile);
        }

        //删除生成的文件夹
        File dir = new File(res);
        boolean flag = deleteDir(dir);
        if (flag) {
            // do something
        }
        Canvas canvas = JSON.parseObject(content, Canvas.class);
        return canvas;
    }

    public static Object parseObject(String xmindFile) throws DocumentException, ArchiveException, IOException {
        String content = parseJson(xmindFile);
        Canvas canvas = JSON.parseObject(content, Canvas.class);
        return canvas;
    }


    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }


    /**
     * @return
     */
    public static String getXmindZenContent(String xmindFile) throws IOException, ArchiveException {
        List<String> keys = new ArrayList<>();
        keys.add(xmindZenJson);
        Map<String, String> map = ZipUtils.getContents(keys, xmindFile);
        String content = map.get(xmindZenJson);
        content = content.substring(1, content.lastIndexOf("]"));
        content = XmindZen.getContent(content);
        return content;
    }

    /**
     * @return
     */
    public static String getXmindLegacyContent(String xmindFile) throws IOException, ArchiveException, DocumentException {
        List<String> keys = new ArrayList<>();
        keys.add(xmindLegacyContent);
        keys.add(xmindLegacyComments);
        Map<String, String> map = ZipUtils.getContents(keys, xmindFile);

        String contentXml = map.get(xmindLegacyContent);
        String commentsXml = map.get(xmindLegacyComments);
        String xmlContent = XmindLegacy.getContent(contentXml, commentsXml);

        return xmlContent;
    }


    private static boolean isXmindZen(String res, String xmindFile) throws IOException, ArchiveException {
        //解压
        File parent = new File(res);
        if (parent.isDirectory()) {
            String[] files = parent.list(new ZipUtils.FileFilter());
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                if (files[i].equals(xmindZenJson)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static XmindCanvas parseXmindCanvas(String fileName) throws DocumentException, ArchiveException, IOException {
        Canvas canvas = XmindParser.parseCanvas(fileName);
        return getXmindCanvas(canvas);
    }

   /* public static void main(String[] args) throws IOException, ArchiveException, DocumentException {
        String fileName = "doc/XmindZen解析.xmind";
        XmindParser xmindParser = new XmindParser();
        String res = xmindParser.parser(fileName);
        JsonRootBean jsonRootBean = JSON.parseObject(res, JsonRootBean.class);
        System.out.println(jsonRootBean);
    }*/

}
