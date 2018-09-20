package top.jfunc.json.filter;

import com.alibaba.fastjson.serializer.NameFilter;
import top.jfunc.json.annotation.JsonField;

import java.lang.reflect.Field;

/**
 * 改变序列化名字过滤器
 * @author xiongshiyan at 2018/9/20 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class FieldNameChangeFilter implements NameFilter {
    @Override
    public String process(Object object, String name, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            boolean present = field.isAnnotationPresent(JsonField.class);
            //不存在直接返回
            if(!present){
                return name;
            }
            JsonField annotation = field.getAnnotation(JsonField.class);
            String fieldName = annotation.value();

            if("".equals(fieldName)){
                return name;
            }

            return fieldName;
        } catch (NoSuchFieldException e) {
            return name;
        }
    }
}
