package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;

/**
 * @program: rms-gpd
 * @description: 素材顺序工具类
 * @author: xiamu
 * @create: 2020-06-08 15:03
 */
public class MaterialUtil implements Serializable {
    private Long fileId;//文件id
    private String filetype;//文件类型
    private int fileorder;//文件顺序


    private String templateId;//模版id
    private String filesize;//文件大小
    private String content;//文件内容

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public int getFileorder() {
        return fileorder;
    }

    public void setFileorder(int fileorder) {
        this.fileorder = fileorder;
    }


}
