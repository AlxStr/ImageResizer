package com.image.resizer.config;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.storage")
@Getter
@Setter
public class StorageConfig {

    private String bucket;

    private String rootFolder;

    @Bean
    public Storage googleCloudStorage() {
        return StorageOptions.getDefaultInstance()
            .getService();
    }

    @Bean
    public Bucket googleCloudStorageBucket() {
        return googleCloudStorage().get(bucket);
    }

    @Bean
    public String googleCloudStorageRootFolder() {
        return rootFolder;
    }
}
