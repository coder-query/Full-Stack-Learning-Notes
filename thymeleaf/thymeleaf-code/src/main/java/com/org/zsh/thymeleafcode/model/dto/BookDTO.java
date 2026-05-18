package com.org.zsh.thymeleafcode.model.dto;


import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BookDTO", description = "图书DTO")
public class BookDTO extends BasePageDTO{
}
