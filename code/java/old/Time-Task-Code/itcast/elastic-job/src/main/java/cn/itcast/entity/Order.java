package cn.itcast.entity;

public class Order {

    //订单id
    private Integer orderId;

    //订单状态,0:未处理，1:已处理
    private Integer status;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

 
