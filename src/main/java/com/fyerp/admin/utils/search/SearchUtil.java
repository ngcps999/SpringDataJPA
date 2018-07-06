package com.fyerp.admin.utils.search;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchUtil {

    public static Map<String,Object> createSqlAndParam(List<SearchFilter> filters,
                                                       List<SearchOrder> orders, int page , int amount){
        List<Object> paramList = new ArrayList<>();
        int paramIndex = 0;
        String filterSql = " 1=1 and ";
        for(int i = 0; i < filters.size(); i++){
            if(filters.get(i).getLeftSplitter() == 1){
                filterSql += " ( ";
            }
            filterSql += filters.get(i).getPropName();
            if(filters.get(i).getOp() < ConditionOp.BETWEEN_OP){
                filterSql += ConditionOp.getOp(filters.get(i).getOp());
                ++paramIndex;
                filterSql += ("?"+paramIndex);
                paramList.add(filters.get(i).getPropValue());
            }
            if(filters.get(i).getOp() == ConditionOp.BETWEEN_OP){
                filterSql += " between( ";
                ++paramIndex;
                filterSql += ("?"+paramIndex);
                ++paramIndex;
                filterSql += ("?"+paramIndex+" )");
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
                filterSql += (" "+orders.get(i).getPropName()+" ");
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
}
