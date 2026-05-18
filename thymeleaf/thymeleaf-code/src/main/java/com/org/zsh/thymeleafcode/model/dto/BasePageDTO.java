package com.org.zsh.thymeleafcode.model.dto;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 分页参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class BasePageDTO {

    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示条数", example = "10")
    private Integer pageSize;

    @ApiModelProperty(value = "偏移量", example = "0" ,hidden = true)
    private Integer offset;

    @ApiModelProperty(value = "排序字段", example = "id",hidden = true)
    private String sortField;

    @ApiModelProperty(value = "排序方式", example = "desc",hidden = true)
    private String sortOrder;

    public int getOffset() {
        ObjectUtil.defaultIfNull(pageNo,1);
        ObjectUtil.defaultIfNull(pageSize,10);
        return  (pageNo - 1) * pageSize;
    }

    public String getSortField() {
        return StringUtils.isBlank(sortField) ? "id" : sortField;
    }

    public String getSortOrder() {
        return StringUtils.isBlank(sortOrder) ? "desc" : sortOrder;
    }
}
