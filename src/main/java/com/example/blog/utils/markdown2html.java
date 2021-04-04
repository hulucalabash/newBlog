package com.example.blog.utils;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-04-04 11:39
        *Description:将md转化为html的工具类
        *
        **/

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class markdown2html {
    public  String convert(String md) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(md);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html= renderer.render(document);
        return html;
    }

}
