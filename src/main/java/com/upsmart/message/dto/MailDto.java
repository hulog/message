package com.upsmart.message.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 提供发送邮件服务接口参数dto
 * @date 3/7/16
 */
public class MailDto implements Serializable {

    private static final long serialVersionUID = 7447491327917924454L;

    // 附件文件
    private List<FileDto> files;
    // 主题
    private String subject;
    // 内容
    private String content;
    // host
    private String smtpHost;
    // 发件人邮箱
    private String from;
    // 发件人邮箱密码
    private String password;
    // 端口号
    private String port;
    // 收件人
    private List<String> to;
    // 抄送人
    private List<String> cc;

    public MailDto() {
    }

    public MailDto(List<FileDto> files, String subject, String content, String smtpHost, String from, String password,
            String port, List<String> to, List<String> cc) {
        this.files = files;
        this.subject = subject;
        this.content = content;
        this.smtpHost = smtpHost;
        this.from = from;
        this.password = password;
        this.port = port;
        this.to = to;
        this.cc = cc;
    }

    public List<FileDto> getFiles() {
        return files;
    }

    public void setFiles(List<FileDto> files) {
        this.files = files;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }
}
