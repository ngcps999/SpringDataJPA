package com.fyerp.admin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author:xiasc
 * @Date:2018/7/10
 * @Time:15:56
 **/
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class AircraftLeaseSupplier extends Supplier{

//    @Id
//    @GeneratedValue
//    @JsonProperty("id")
//    private Integer supplierId;

    private String aircraftModel;

    
}
