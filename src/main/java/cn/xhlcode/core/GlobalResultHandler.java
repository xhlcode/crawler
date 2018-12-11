package cn.xhlcode.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import sun.reflect.generics.tree.ReturnType;

@RestControllerAdvice
public class GlobalResultHandler implements ResponseBodyAdvice {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalResultHandler.class);


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getMethod().getReturnType() != ResultDto.class;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResultDto || body instanceof String) {
            return body;
        }
        return ResultDto.success(body);
    }

}
