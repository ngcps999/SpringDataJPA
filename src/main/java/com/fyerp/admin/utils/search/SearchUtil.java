package com.fyerp.admin.utils.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchUtil {

    public static Map<String,Object> createSqlAndParam(List<SearchFilter> filters,
                                                       List<SearchOrder> orders, int page , int amount,Class clazz){
        List<Object> paramList = new ArrayList<>();
        int paramIndex = 0;
        String filterSql = " 1=1 and ";
        for(int i = 0; i < filters.size(); i++){
            if(filters.get(i).getLeftSplitter() == 1){
                filterSql += " ( ";
            }
//            filterSql += filters.get(i).getPropName();
            filterSql += getColumnName(filters.get(i).getPropName(),clazz);
            if(filters.get(i).getOp() < ConditionOp.BETWEEN_OP){
                filterSql += ConditionOp.getOp(filters.get(i).getOp());
                ++paramIndex;
                filterSql += ("?"+paramIndex);
                paramList.add(filters.get(i).getPropValue());
            }
            if(filters.get(i).getOp() == ConditionOp.BETWEEN_OP){
                filterSql += " between ";
                ++paramIndex;
                filterSql += ("?"+paramIndex);
                filterSql+=" and ";
                ++paramIndex;
                filterSql += ("?"+paramIndex+" ");
                paramList.add(filters.get(i).getPropValue());
                paramList.add(filters.get(i).getPropValue2());
            }
            if(filters.get(i).getOp() == ConditionOp.CONTAIN_OP){
                filterSql += " like ";
                ++paramIndex;
                filterSql += ("%"+"?"+paramIndex+"%");
                paramList.add(filters.get(i).getPropValue());
            }
            if(filters.get(i).getOp() == ConditionOp.PRECONTAIN_OP){
                filterSql += " like ";
                ++paramIndex;
                filterSql += ("?"+paramIndex+"%");
                paramList.add(filters.get(i).getPropValue());
            }
            if(filters.get(i).getOp() == ConditionOp.POSTCONTAIN_OP){
                filterSql += " like ";
                ++paramIndex;
                filterSql += ("%"+"?"+paramIndex);
                paramList.add(filters.get(i).getPropValue());
            }
            if(filters.get(i).getRightSplitter() == 1){
                filterSql += " ) ";
            }
            if(StringUtils.isNotEmpty(filters.get(i).getLogic())){
                filterSql+= ConditionLogic.getLogic(filters.get(i).getLogic());
            }
        }
        if(orders != null && orders.size() > 0){
            filterSql += " order by ";
            for(int i = 0; i < orders.size(); i++){
                if(i != 0){
                    filterSql += " , ";
                }
                filterSql += (" "+getColumnName(orders.get(i).getPropName(),clazz)+" ");
                if("0".equals(orders.get(i).getOrder())){
                    filterSql += " asc ";
                }
            }
        }
        if(page == 0){
            page = 1;
        }
        filterSql += (" limit "+(page-1)*amount+","+amount+" ");
        Map<String,Object> map = new HashMap<>();
        map.put("sql",filterSql);
        map.put("params",paramList);
        return map;
    }



    //根据参数获取字段名
    public static String getColumnName(String str,Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field field : fields){
                Annotation anno = field.getAnnotation(JsonProperty.class);
                field.setAccessible(true);
                if(anno != null){
                    if(((JsonProperty) anno).value().equals(str)){
                        return convertStr(field.getName());
                    }
                }
                if(field.getName().equals(str)){
                    return convertStr(field.getName());
                }
            }
        }


        return str;
    }


    //转为数据库字段类型
    private static String convertStr(String str){
        char[] chars = str.toCharArray();
        String result = "";
        for (int i = 0; i < chars.length;  i++) {
            char c = chars[i];
            //判断字母是不是大写，如果是大写变为小写
            if (Character.isUpperCase(c)){
                 result += ("_"+Character.toLowerCase(c)) ;
            }else{
                result+=c;
            }
        }
        return  result;
    }















}
