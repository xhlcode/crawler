package cn.xhlcode.controller;

import cn.xhlcode.core.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
public class TestController {
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResultDto home(String name) {
        LOGGER.info("测试");
        ResultDto<String> resultDto  = new ResultDto<>();
        resultDto.setMessage("执行成功: " + name);
        resultDto.setResult(name);
        return resultDto;
    }
}
