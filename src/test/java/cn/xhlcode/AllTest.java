package cn.xhlcode;


import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.util.SerializationUtils;


public class AllTest {

    @Test
    public void 序列化委托测试() {
        Salary salary = new Salary(new Double(4000),new Double(5000));
        People people = new People("zhangsan", salary);
        System.out.println(JSONObject.toJSONString(people));
        byte[] data = SerializationUtils.serialize(people);

        People result = (People)SerializationUtils.deserialize(data);
        System.out.println(JSONObject.toJSONString(result));

    }

    @Test
    public void 边界测试() {
        Integer intMaxNum= 2147483647;
        intMaxNum += 1;
        int limit = 10;
        // 超过边界  判断失效
        if(intMaxNum < limit) {
            System.out.println("数字"+intMaxNum+"小于" + limit);
        }
    }


    public static void main (String[] args) {
        Salary salary = new Salary(new Double(4000),new Double(5000));

        Salary salary1 = org.apache.commons.lang3.SerializationUtils.clone(salary);

        salary1.setBasePay(new Double(9999));

        System.out.println(JSONObject.toJSONString(salary));



    }
}
