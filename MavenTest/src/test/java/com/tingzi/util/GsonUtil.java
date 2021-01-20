package com.tingzi.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author xiexiaoting
 * @Date 2021/1/11 14:00
 * @Version 1.0
 */



// JSON格式转换工具类

public class GsonUtil <T, Y> {
    // 防止某个值包含有=，会变为\u003d的情况
    protected static Gson gson=new GsonBuilder()
            .serializeNulls()//序列化null
            .disableHtmlEscaping()//禁止转义html标签
            .create();

        private static Type type;

    /**
     * 构造函数，设置JSON格式转换过程参数
     */

    public GsonUtil(){
        if(gson==null){
            gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")//设置日期时间格式，另有2个重载方法。在序列化和反序化时均生效
                    .setVersion(1.0)///有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
                    //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么
                    //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.
                    .serializeNulls() //序列化null,Gson在默认情况下序列化的时候是不导出值是null
                    .create();

        }
    }

    /**
     * 将bean对象转换成json格式
     *
     * @param obj bean对象
     * @return JSON格式字符串
     */
     public static  String objectToJson(Object obj){
         String jsonstr= null;
         if(gson!=null){
             jsonstr= gson.toJson(obj);
         }
         return jsonstr;
     }

  /**
     * 将json格式转换成list对象
     *
     * @param jsonStr JSON格式字符串
     * @return list对象
     */
    public static List<?> jsonToList(String jsonStr){
        List<?> objList= null;
        if (gson!=null){
            Type type= new TypeToken<List<?>>(){}.getType();
            objList=gson.fromJson(jsonStr,type);
        }
        return objList;

    }

    /**
     * 将json格式转换成list对象  并准确指定类型
     *
     * @param jsonStr JSON格式字符串
     * @param type 转换类型
     * @return list对象
     */
    public static List<?> jsonToList(String jsonStr,Type type){
        List<?> objList= null;
        if (gson!=null){
            objList=gson.fromJson(jsonStr,type);
        }
        return objList;
    }

    /**
     * 将JSON格式转换成map对象
     *
     * @param jsonStr JSON格式字符串
     * @return map对象
     */
    public  static Map<?,?> jsonToMap(String jsonStr){
        Map<?,?> objMap=null;
        if (gson!=null){
         Type type= new TypeToken<Map<?,?>>(){}.getType();
            objMap=gson.fromJson(jsonStr,type);
        }
        return objMap;
    }
    /**
     * 将Map格式转换成Bean对象
     * @param  map
     * @return Bean对象
     */

    public static Object mapToBean(Map<String, Object> map, Class<?> clazz) throws Exception {
        Object obj = clazz.newInstance();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String propertyName = entry.getKey();       //属性名
                Object value = entry.getValue();
                String setMethodName = "set"
                        + propertyName.substring(0, 1).toUpperCase()
                        + propertyName.substring(1);
                Field field = getClassField(clazz, propertyName);
                if (field == null)
                    continue;
                Class<?> fieldTypeClass = field.getType();
                value = convertValType(value, fieldTypeClass);
                try {
                    clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
    /**
     * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
     *
     * @param clazz     指定的class
     * @param fieldName 字段名称
     * @return Field对象
     */
    private static Field getClassField(Class<?> clazz, String fieldName) {
        if (Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            return getClassField(superClass, fieldName);
        }
        return null;
    }

    /**
     * 将Object类型的值，转换成bean对象属性里对应的类型值
     *
     * @param value          Object对象值
     * @param fieldTypeClass 属性的类型
     * @return 转换后的值
     */
    private static Object convertValType(Object value, Class<?> fieldTypeClass) {
        Object retVal = null;
        if (Long.class.getName().equals(fieldTypeClass.getName())
                || long.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Long.parseLong(value.toString());
        } else if (Integer.class.getName().equals(fieldTypeClass.getName())
                || int.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Integer.parseInt(value.toString());
        } else if (Float.class.getName().equals(fieldTypeClass.getName())
                || float.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Float.parseFloat(value.toString());
        } else if (Double.class.getName().equals(fieldTypeClass.getName())
                || double.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Double.parseDouble(value.toString());
        } else {
            retVal = value;
        }
        return retVal;
    }
    /**
     * @param  obj     * 将 Bean对象转换成Map格式ect
     * @return Bean对象
     */
    public static Map<String, Object> beanToMap(Object object) throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();

        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }



    /**
     * 将JSON转换成bean对象
     *
     * @param jsonStr JSON格式字符串
     * @param cl      转换类型
     * @return bean对象
     */
    public static Object jsonToBean(String jsonStr, Class<?> cl) {
        Object obj = null;
        if (gson != null) {
            obj = gson.fromJson(jsonStr, cl);
        }
        return obj;
    }

    /**
     * JAVA对象转JSON字符串
     *
     * @param data 要转换的JAVA对象
     * @return JSON格式字符串
     */
    public static String toJson(Object data) {
        type = new TypeToken<Object>() {
        }.getType();
        String jsonStr = gson.toJson(data, type);
        jsonStr = jsonStr.replaceAll(":null", ":\"\"");
        jsonStr = jsonStr.replaceAll(":NULL", ":\"\"");
        return jsonStr;
    }

    /**
     * JSON格式字符串转bean对象
     *
     * @param json  JSON格式字符串
     * @param rtype 转换类型
     * @return bean对象
     */
    public static Object fromJson(String json, Type rtype) {
        return gson.fromJson(json, rtype);
    }

}
