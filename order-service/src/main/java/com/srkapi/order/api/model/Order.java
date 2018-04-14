package com.srkapi.order.api.model;

import com.srkapi.common.model.EntityBase;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Order")
@Data
public class Order extends EntityBase{

	private Long dischargeDate;
	private String coordinateInit;
	private String coordinateFinish;
	private Long idOwner;
	private Integer loadType;
	private Long idTransport;
	private Float price;
	private Date datePayment;

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

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}



}
