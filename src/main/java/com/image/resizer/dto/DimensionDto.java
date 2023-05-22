package com.image.resizer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DimensionDto {
    public int height;
    public int width;
}
