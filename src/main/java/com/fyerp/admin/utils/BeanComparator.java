package com.fyerp.admin.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Date;

public class BeanComparator implements Comparator {

    Logger logger = LoggerFactory.getLogger(BeanComparator.class);


    private String orderBy;

    private int sortDirection = 1;



    public BeanComparator(String orderBy){
        this.orderBy = orderBy;
    }

    public BeanComparator(String orderBy,int direction){
        this.orderBy = orderBy;
        this.sortDirection = direction;
    }

    @Override
    public int compare(Object o1, Object o2) {
        //如果没有指定，按照创建日期排；
        int result = 0;
        try {
            Class clazz1 = o1.getClass();
            Class clazz2 = o2.getClass();
            Field[] fields1 = clazz1.getDeclaredFields();

            if(StringUtils.isEmpty(orderBy)){
                orderBy = "createTime";
            }
            Object value1 = null;
            Object value2 = null;
            for(Field f1 : fields1){
                f1.setAccessible(true);
                String name = f1.getName();
                if(orderBy.equals(name)){
                    try {
                        value1 = f1.get(o1);
                        value2 = f1.get(o2);
                        break;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return 0;
                    }

                }
            }

            if(value1 == null){
                result =  -1;
            }
            if(value2 == null){
                result = 1;
            }

            if(value1 instanceof Integer){
                result = ((Integer) value1).intValue()-((Integer) value2).intValue();
            }

            if (value1 instanceof Long){
                result = ((Long) value1).longValue()-((Long) value2).longValue() > 0l ? 1 : -1;
            }

            if(value1 instanceof Date){
                Date date1 = (Date)value1;
                Date date2 = (Date)value2;
                if (date1 == null){
                    result = -1;
                }
                if(date2 == null){
                    result = 1;
                }
                result = date1.getTime()-date2.getTime() > 0l ? 1: -1;
            }

            if(value1 instanceof String){
                String str1 = String.valueOf(value1);
                String str2 = String.valueOf(value2);
                result = str1.compareToIgnoreCase(str2);
            }
        }catch (Exception e){
            logger.info(e.toString());
        }

        return result*sortDirection;

    }
}
