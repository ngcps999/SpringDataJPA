package com.fyerp.admin.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyerp.admin.domain.MetaDataObj;
import com.fyerp.admin.domain.Project;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:xiasc
 * @Date:2018/7/11
 * @Time:10:47
 **/
public class GetJsonUtil {

    /*public static void main(String[] args) {
        Class t = Project.class;
        System.out.println(t.getSimpleName());
    }*/


    public static Object getJsonByClass(String className){
        Class t = null;
        try {
            t = Class.forName("com.fyerp.admin.domain."+getClassName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        if(t == null){
            return null;
        }
        if(t.getName().indexOf("com.fyerp.admin.domain") == -1){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
//        Entity tableAnno = t.getClass().getAnnotation(Entity.class);
//        if(tableAnno == null){
//            return null;
//        }
        map.put("name",StringUtils.lowerCase(t.getSimpleName()));
        Field[] fields = t.getDeclaredFields();
        List<MetaDataObj> list = new ArrayList<>();
        for(Field field : fields){
            field.setAccessible(true);
            MetaDataObj property = new MetaDataObj();
            Transient transientAnno = field.getAnnotation(Transient.class);
            if(transientAnno != null){
                continue;
            }
            ManyToMany mtmAnno = field.getAnnotation(ManyToMany.class);
            if(mtmAnno != null){
                continue;
            }
            ManyToOne mtoAnno = field.getAnnotation(ManyToOne.class);
            if(mtmAnno != null){
                continue;
            }
            OneToOne otoAnno = field.getAnnotation(OneToOne.class);
            if(otoAnno != null){
                continue;
            }
            JsonProperty jsonPropertyAnno = field.getAnnotation(JsonProperty.class);
            if(jsonPropertyAnno != null && StringUtils.isNotEmpty(jsonPropertyAnno.value())){
                property.setName(jsonPropertyAnno.value());
            }else{
                property.setName(field.getName());
            }

            MyComment commentAnno = field.getAnnotation(MyComment.class);
            if(commentAnno != null){
                property.setLabel(commentAnno.value());
            }

            Map<String,Object> info = getColumnType(field.getGenericType().toString());
            if(info != null){
                Object obj = info.get("length");
                if(obj != null){
                    property.setLength((int)obj);
                }else{
                    property.setLength(null);
                }
                property.setType(info.get("type").toString());

            }

            list.add(property);
//            if(json)
        }
        map.put("properties",list);


        return map;
    }


    private static Map<String,Object> getColumnType(String str){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        String result = "";
        Map<String,Object> map = new HashMap<>();
        if("class java.lang.String".equals(str)){
            map.put("type","varchar");
            map.put("length",Integer.valueOf(255));
            return map;
        }
        if("class java.lang.Integer".equals(str)){
            map.put("type","int");
            map.put("length",Integer.valueOf(11));
            return map;
        }
        if("class java.util.Date".equals(str)){
            map.put("type","datetime");
            map.put("length",null);
            return map;
        }
        if("class java.lang.Long".equals(str)){
            map.put("type","bigint");
            map.put("length",Integer.valueOf(20));
            return map;
        }
        if("class java.lang.Double".equals(str)){
            map.put("type","double");
            map.put("length",null);
            return map;
        }
        if("class java.lang.Boolean".equals(str)){
            map.put("type","bit");
            map.put("length",Integer.valueOf(1));
            return map;
        }
        return null;
    }


    private static String getClassName(String str){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        String str1 = str.substring(0,1);
        String result = StringUtils.upperCase(str1)+str.substring(1,str.length());
        return result;
    }



}
