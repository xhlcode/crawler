package cn.xhlcode.component;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
@Aspect
public class RestControllerAspect {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerAspect.class);


    private ObjectMapper mapper;

    private User user;

    public RestControllerAspect(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public RestControllerAspect setUser(User user) {
        this.user = user;
        return this;
    }


    @Around("@within(org.springframework.web.bind.annotation.RestController) || @annotation(org.springframework.web.bind.annotation.RestController)")
    public Object  apiLog(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        if(needToLog(method)) {
           return point.proceed();
        }


        String name = "admin";
        String methodName = getMethodName(point);
        String params = getParamsJson(point);

        LOGGER.info("Started request -> requester [{}] method [{}] params [{}]",name, methodName, params);
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        LOGGER.info("End request -> requester [{}] method [{}] params [{}] and response is [{}] cost [{}] millis", name, methodName, params, result,System.currentTimeMillis() - start);
        return result;
    }


    /**
     * 获取方法名
     * @param point
     * @return
     */
    private String getMethodName(ProceedingJoinPoint point) {
        String methodName = point.getSignature().toShortString();
        String SHORT_METHOD_NAME_SUFFIX = "(..)";
        if(methodName.endsWith(SHORT_METHOD_NAME_SUFFIX)) {
            methodName = methodName.substring(0,methodName.length() - SHORT_METHOD_NAME_SUFFIX.length());
        }
        return methodName;
    }


    /**
     * 获取参数数据
     * @param point
     * @return
     */
    private String getParamsJson(ProceedingJoinPoint point) {
        Object[] args = point.getArgs();
        StringBuilder sb = new StringBuilder();
        for(Object arg : args) {
            String str;
            if(arg instanceof HttpServletResponse) {
                str = HttpServletResponse.class.getSimpleName();
            } else if (arg instanceof HttpServletRequest) {
                str = HttpServletRequest.class.getSimpleName();
            } else if (arg instanceof MultipartFile) {
                long size = ( (MultipartFile) arg).getSize();
                str = MultipartFile.class.getSimpleName() + "size : " + size;
            } else {
                try {
                    str = mapper.writeValueAsString(arg);
                } catch (Exception ex) {
                    LOGGER.error("json process error", JSONObject.toJSONString(ex));
                    str = "error Object";
                }
            }
            sb.append(str).append(",");
        }

        if (sb.length() != 0) {
            return sb.deleteCharAt(sb.length() - 1).toString();
        } else {
            return "";
        }

    }


    /**
     * 排除获取数据的方法，因为不会更改任何信息。
     * 另外还可以通过注解标记是否需要日志
     * <br/>
     * 排除全局异常处理类
     *
     * @param method 方法
     * @return true/false
     */
    private boolean needToLog(Method method) {
        return method.getName().startsWith("get");
    }


}
