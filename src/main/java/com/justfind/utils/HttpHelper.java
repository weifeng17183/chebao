package com.justfind.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;

public class HttpHelper {
    public static final <T> T getObject(HttpServletRequest request,
            Class<T> clazz) throws IOException {
        return JSONObject.parseObject(
                request.getParameter("data"), clazz);
    }
}
