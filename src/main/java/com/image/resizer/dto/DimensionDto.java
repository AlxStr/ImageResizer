package com.image.resizer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DimensionDto {
    private int height;
    private int width;
}
