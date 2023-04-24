package cn.itcast.order.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HeaderOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        //尝试获取请求头
        String origin = request.getHeader("origin");
        //非空判断
        if (StringUtils.isEmpty(origin)){
            origin = "black";
        }
        return origin;
    }
}
