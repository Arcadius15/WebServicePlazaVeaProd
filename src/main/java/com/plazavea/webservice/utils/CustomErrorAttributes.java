package com.plazavea.webservice.utils;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes{
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = 
          super.getErrorAttributes(webRequest, options);
        var message = new StringBuilder();
        try {
            var i = 0;
            for (var m : errorAttributes.get("message").toString().split(":")) {
                if (i>2) {break;}
                message.append(m);
                i++;
            }
        } catch (Exception e) {
            message = new StringBuilder();
            message.append(errorAttributes.get("message").toString());
        }
        
        errorAttributes.put("locale", webRequest.getLocale()
            .toString());
        errorAttributes.put("sonso", "Deja de mandar webadas, QUESO");
        errorAttributes.remove("trace");
        errorAttributes.put("message", message.toString());

        //...

        return errorAttributes;
    }
}
