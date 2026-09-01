package com.org.zsh.thymeleafcode.model.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CategoryPageDTO", description = "分类DTO")
public class CategoryPageDTO extends BasePageDTO{
}
