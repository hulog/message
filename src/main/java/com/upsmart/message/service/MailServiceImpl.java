package com.upsmart.message.service;

import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.constant.GlobalConstants;
import com.upsmart.message.domain.MailMessage;
import com.upsmart.message.dto.MailDto;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 发送邮件服务具体实现
 * @date 3/8/16
 */
@Service
public class MailServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    private transient Properties props = null;
    // 发送邮件session
    private transient Session session;
    private MimeMessage message;

    // private CommonConfig commonConfig;

    /**
     * 发送邮件
     * 
     * @param mailDto
     */
    public MailMessage sendMail(MailDto mailDto) {
        logger.info("send mail start...");
        MailMessage msg = new MailMessage();
        StopWatch sw = new StopWatch();
        sw.start();
        this.init(mailDto);
        try {
            Transport transport = session.getTransport("smtp");
            InternetAddress from = new InternetAddress(mailDto.getFrom().trim());
            message.setFrom(from);
            if (null != mailDto.getTo() && mailDto.getTo().size() > 0) {
                message.addRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(this.changeListToString(mailDto.getTo())));
            }
            if (null != mailDto.getCc() && mailDto.getCc().size() > 0) {
                message.addRecipients(Message.RecipientType.CC,
                        InternetAddress.parse(this.changeListToString(mailDto.getCc())));
            }
            message.setSubject(mailDto.getSubject());
            Multipart multipart = new MimeMultipart("mixed");
            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(mailDto.getContent());
            multipart.addBodyPart(contentPart);
            // 添加附件
            // if (null != mailDto.getFiles() && mailDto.getFiles().size() > 0)
            // {
            // this.addTach(mailDto.getFiles(), multipart, msg);
            // }
            message.setContent(multipart);
            message.saveChanges();
            // 连接服务器的邮箱
            transport.connect(mailDto.getSmtpHost().trim(), mailDto.getFrom().trim(), mailDto.getPassword().trim());
            transport.sendMessage(message, message.getAllRecipients());
            logger.info("邮件发送成功");
            sw.stop();
            logger.info("send mail end, and it takes " + sw.getTime() + " ms");
            msg.setCost(sw.getTime());
            msg.setCode(MailMessage.MESSAGE_SEND_SUCCESS);
            msg.setDesc(MailMessage.MESSAGE_SEND_SUCCESS_MSG);
            return msg;
        } catch (NoSuchProviderException e) {
            sw.stop();
            logger.info("send mail end, and it takes " + sw.getTime() + " ms");
            msg.setCost(sw.getTime());
            msg.setCode(MailMessage.MESSAGE_SEND_FAILED);
            msg.setDesc(MailMessage.MESSAGE_SEND_FAILED_MSG);
            logger.error("邮件发送失败");
            e.printStackTrace();
            return msg;
        } catch (AddressException e) {
            sw.stop();
            logger.info("send mail end, and it takes " + sw.getTime() + " ms");
            msg.setCost(sw.getTime());
            msg.setCode(MailMessage.MESSAGE_SEND_FAILED);
            msg.setDesc(MailMessage.MESSAGE_SEND_FAILED_MSG);
            logger.error("邮件发送失败");
            e.printStackTrace();
            return msg;
        } catch (MessagingException e) {
            sw.stop();
            logger.info("send mail end, and it takes " + sw.getTime() + " ms");
            msg.setCost(sw.getTime());
            msg.setCode(MailMessage.MESSAGE_SEND_FAILED);
            msg.setDesc(MailMessage.MESSAGE_ADDRESS_NOT_FOUND_MSG);
            logger.error("邮件发送失败");
            e.printStackTrace();
            return msg;
        }
        // catch (IOException e) {
        // sw.stop();
        // logger.info("send mail end, and it takes " + sw.getTime() + " ms");
        // msg.setCost(sw.getTime());
        // msg.setCode(MailMessage.MESSAGE_SEND_FAILED);
        // msg.setDesc(MailMessage.MESSAGE_ATTACH_FAILED_MSG);
        // logger.error("附件添加失败");
        // e.printStackTrace();
        // return msg;
        // }
    }

    /**
     * 添加附件
     * 
     * @param files
     * @param multipart
     */
    // public void addTach(List<FileDto> files, Multipart multipart, MailMessage
    // msg)
    // throws IOException, MessagingException {
    // for (FileDto file : files) {
    // MimeBodyPart mailArchieve = new MimeBodyPart();
    // File f = new File(this.commonConfig.getMailSavePath() +
    // file.getFileName());
    // FileCopyUtils.copy(file.getFileBytes(), f);
    // FileDataSource fd = new FileDataSource(f);
    // mailArchieve.setDataHandler(new DataHandler(fd));
    // mailArchieve.setFileName(MimeUtility.encodeText(fd.getName(), "utf-8",
    // "B"));
    // multipart.addBodyPart(mailArchieve);
    // }
    // }

    /**
     * 邮箱初始化
     * 
     * @param mailDto
     */
    private void init(MailDto mailDto) {
        if (null == props) {
            props = new Properties();
        }
        // String path = this.commonConfig.getMailSavePath();
        // File file = new File(path);
        // if (!file.exists()) {
        // file.mkdirs();
        // }
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", mailDto.getPort().trim());
        props.put("mail.smtp.host", mailDto.getSmtpHost().trim());
        // 创建session
        session = Session.getInstance(props);
        message = new MimeMessage(session);
    }

    /**
     * 字符串拼接
     * 
     * @param list
     * @return
     */
    private String changeListToString(List<String> list) {
        StringBuffer toStr = new StringBuffer();
        int length = list.size();
        if (null != list && length < 2) {
            toStr.append(list.get(0));
        } else {
            for (int i = 0; i < length; i++) {
                toStr.append(list.get(i));
                if (i != (length - 1)) {
                    toStr.append(GlobalConstants.COMMA);
                }
            }
        }
        return toStr.toString();
    }
}
