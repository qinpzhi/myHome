package com.home.utils;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//从request中提取传过来的json值
public class RequestToJson {
    private static Log log = LogFactory.getLog(RequestToJson.class);

    public static JSONObject phrase(HttpServletRequest request) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    request.getInputStream(), "UTF-8"));
            StringBuffer sb = new StringBuffer("");
            String temp, reqMessage;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            reqMessage = sb.toString();

            JSONObject reqObject = JSONObject.fromObject(reqMessage);
            return reqObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}