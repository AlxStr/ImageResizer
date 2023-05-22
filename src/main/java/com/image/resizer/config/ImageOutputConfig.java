package com.image.resizer.config;

import com.image.resizer.dto.DimensionDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.images.output")
@Getter
@Setter
public class ImageOutputConfig {
    private List<String> dimensions = new ArrayList<>();

    @Bean(name = "imageOutputDimensions")
    public List<DimensionDto> imageOutputDimensions() {
        return dimensions.stream()
            .map((s) -> {
                String[] parts = s.split("x");

                if (parts.length < 2) {
                    throw new RuntimeException("Incorrect image output dimension format");
                }

                return DimensionDto.builder()
                    .width(Integer.parseInt(parts[0]))
                    .height(Integer.parseInt(parts[1]))
                    .build();
            })
            .toList();
    }
}
