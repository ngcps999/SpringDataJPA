package com.fyerp.admin.utils;

import com.fyerp.admin.service.SupplierService;

/**
 * @author:xiasc
 * @Date:2018/7/13
 * @Time:14:07
 **/
public class SupplierServiceAdapter{


    public static SupplierService getService(int type){
        if(type == 1){
            return (SupplierService) SpringContextUtils.getBean("AircraftLeaseSupplierService");
        }
        if(type == 2){
            return (SupplierService) SpringContextUtils.getBean("CameraSupplierService");
        }
        if(type == 3){
            return (SupplierService) SpringContextUtils.getBean("OtherSupplierService");
        }
        return null;
    }


}
