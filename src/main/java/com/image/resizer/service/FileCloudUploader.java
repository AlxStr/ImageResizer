package com.image.resizer.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileCloudUploader {

    @Qualifier("googleCloudStorageBucket")
    private final Bucket bucket;

    @Qualifier("googleCloudStorageRootFolder")
    private final String rootFolder;

    public Blob upload(String path, InputStream file) {
        String destination = String.format("%s/%s", rootFolder, path);

        return bucket.create(destination, file);
    }
}
