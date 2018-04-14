package com.srkapi.order.api.dto;

import com.srkapi.order.api.model.Order;

import java.io.Serializable;



public class OrderDto extends BaseDto<Order> implements Serializable {
    static final long serialVersionUID = 42L;
    private String id;
    private Long dischargeDate;
    private String coordinateInit;
    private String coordinateFinish;
    private Long idOwner;
    private Integer loadType;
    private Long idTransport;
    private Float price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Long dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getCoordinateInit() {
        return coordinateInit;
    }

    public void setCoordinateInit(String coordinateInit) {
        this.coordinateInit = coordinateInit;
    }

    public String getCoordinateFinish() {
        return coordinateFinish;
    }

    public void setCoordinateFinish(String coordinateFinish) {
        this.coordinateFinish = coordinateFinish;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public Integer getLoadType() {
        return loadType;
    }

    public void setLoadType(Integer loadType) {
        this.loadType = loadType;
    }

    public Long getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(Long idTransport) {
        this.idTransport = idTransport;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public Order toModel() {
            Order orderModel = new Order();
            orderModel.setPrice(this.getPrice());
            orderModel.setCoordinateFinish(this.getCoordinateFinish());
            orderModel.setCoordinateInit(this.getCoordinateInit());
            orderModel.setDischargeDate(this.getDischargeDate());
            orderModel.setIdOwner(this.getIdOwner());
            orderModel.setIdTransport(this.getIdTransport());
            orderModel.setLoadType(this.getLoadType());
            return orderModel;
    }
}
