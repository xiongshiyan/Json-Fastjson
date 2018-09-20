package top.jfunc.json.filter;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import top.jfunc.json.annotation.JsonField;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * 排序某些属性过滤器
 * @author xiongshiyan at 2018/9/20 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class ExcludePropertyPreFilter implements PropertyPreFilter {

    private final Class<?>    clazz;
    private final Set<String> excludes = new HashSet<String>();

    public ExcludePropertyPreFilter(Class<?> clazz, String... properties){
        super();
        this.clazz = clazz;
        for (String item : properties) {
            if (item != null) {
                this.excludes.add(item);
            }
        }
    }

    @Override
    public boolean apply(JSONSerializer serializer, Object source, String name) {
        if (source == null) {
            return true;
        }

        if (clazz != null && !clazz.isInstance(source)) {
            return true;
        }

        //先排除直接指定忽略的
        if(this.excludes.contains(name)){
            return false;
        }
        try {
            Field field = source.getClass().getDeclaredField(name);
            boolean present = field.isAnnotationPresent(JsonField.class);
            //不存在直接返回
            if(!present){
                return true;
            }
            JsonField annotation = field.getAnnotation(JsonField.class);
            boolean serialize = annotation.serialize();
            //存在，不需要序列化的
            if(!serialize){
                return false;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return true;
        }
        return true;
    }

}