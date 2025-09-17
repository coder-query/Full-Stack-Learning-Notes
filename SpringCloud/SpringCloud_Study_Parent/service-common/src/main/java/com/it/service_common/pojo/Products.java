package com.it.service_common.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Table(name = "products")
public class Products {
@Id
private Integer id;
private String name;
private double price;
private String flag;
private String goodsDesc;
private String images;
private long goodsStock;
private String goodsType;
}