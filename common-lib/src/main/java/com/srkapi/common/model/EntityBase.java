package com.srkapi.common.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


public abstract class EntityBase{

    public static final Integer STATUS_INACTIVE = 0;
    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_DELETED = -1;
    
    @Id
    protected String id;

    protected Integer status = 1; //default status




    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EntityBase)) {
            return false;
        }
        EntityBase other = (EntityBase) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }


}
