package com.zh.tcr.wechat.controller;

import com.zh.tcr.common.result.Result;
import com.zh.tcr.wechat.service.MessageService;
import com.zh.tcr.wechat.utils.SHA1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "微信公众号短信管理")
@RestController
@RequestMapping("/service_wechat/message")
public class MessageController {
    private static final String token = "nichijou";

    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation(value = "微信服务器有效性验证,用于接口配置信息")
    @GetMapping
    public String verifyToken(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (checkSignature(signature, timestamp, nonce))
            return echostr;
        return null;
    }

    @ApiOperation(value = "接收微信服务器发送来的消息")
    @PostMapping
    public String receiveMessage(HttpServletRequest request) throws Exception {
        Map<String, String> param = this.parseXml(request);
        return messageService.receiveMessage(param);
    }

    @ApiOperation(value = "发送订单成功的消息")
    @GetMapping("/pushPayMessage")
    public Result pushPayMessage() {
        messageService.pushPayMessage(1L);
        return Result.ok();
    }

    @ApiOperation(value = "解析xml")
    private Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        return map;
    }

    @ApiOperation(value = "验证签名")
    private boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] str = new String[]{token, timestamp, nonce};
        //排序
        Arrays.sort(str);
        //拼接字符串
        StringBuilder builder = new StringBuilder();
        for (String s : str)
            builder.append(s);
        //进行sha1加密
        String temp = SHA1.encode(builder.toString());
        //与微信提供的signature进行匹对
        return signature.equals(temp);
    }
}
