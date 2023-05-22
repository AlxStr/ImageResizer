package com.image.resizer.http.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponse {
    private String path;
}
