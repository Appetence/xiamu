package com.umpay.rms.gpd.internal.util.xstream;

import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

/**
 * @program: rms-gpd
 * @description: 重写 XppDriver 解析器的HierarchicalStreamWriter方法
 * @author: xiamu
 * @create: 2020-10-17 00:26
 */

public class NoneEscapeAppDriver extends XppDriver {

    @Override
    public HierarchicalStreamWriter createWriter(Writer out) {
        return new NoneEscapePrettyPintWriter(out, getNameCoder());
    }
}
