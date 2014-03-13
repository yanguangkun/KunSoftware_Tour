package com.kunsoftware.bean;

import java.util.List;

import com.kunsoftware.entity.Orders;
import com.kunsoftware.entity.OrdersAttachment;
import com.kunsoftware.entity.OrdersCash;
import com.kunsoftware.entity.OrdersDetail;
import com.kunsoftware.entity.OrdersStatus;
import com.kunsoftware.entity.OrdersTravelList;

public class OrderViewBean {

	private Orders orders;
	
	private List<OrdersDetail> ordersDetail;
	
	private List<OrdersStatus> ordersStatus;
	
	private List<OrdersCash> ordersCash;
	
	private List<OrdersAttachment> ordersAttachment;
	
	private List<OrdersTravelList> ordersTravel;

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public List<OrdersDetail> getOrdersDetail() {
		return ordersDetail;
	}

	public void setOrdersDetail(List<OrdersDetail> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}

	public List<OrdersStatus> getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(List<OrdersStatus> ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

	public List<OrdersCash> getOrdersCash() {
		return ordersCash;
	}

	public void setOrdersCash(List<OrdersCash> ordersCash) {
		this.ordersCash = ordersCash;
	}

	public List<OrdersAttachment> getOrdersAttachment() {
		return ordersAttachment;
	}

	public void setOrdersAttachment(List<OrdersAttachment> ordersAttachment) {
		this.ordersAttachment = ordersAttachment;
	}

	public List<OrdersTravelList> getOrdersTravel() {
		return ordersTravel;
	}

	public void setOrdersTravel(List<OrdersTravelList> ordersTravel) {
		this.ordersTravel = ordersTravel;
	}
	
	
}
