package com.image.resizer.service;

import com.google.cloud.storage.Blob;
import com.image.resizer.dto.DimensionDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageResolver {
    Logger logger = LoggerFactory.getLogger(ImageResolver.class);

    private final ImageResizer resizeService;
    private final FileCloudUploader fileCloudUploader;
    @Qualifier("imageOutputDimensions")
    private final List<DimensionDto> outputDimensions;

    public Blob resolve(MultipartFile imageFile) {
        String filePath = imageFile.getOriginalFilename();
        List<Closeable> closables = new ArrayList<>();
        Blob originalFileBlob;

        try {
            closables.add(imageFile.getInputStream());
            originalFileBlob = fileCloudUploader.upload(filePath, imageFile.getInputStream());

            for (DimensionDto d : outputDimensions) {
                String resizedFilePath = String.format("%sx%s/%s", d.getWidth(), d.getHeight(), filePath);

                ByteArrayOutputStream resizedImage = resizeService.resize(d.getWidth(), d.getHeight(),
                        imageFile.getInputStream());
                closables.add(resizedImage);

                ByteArrayInputStream imageResizedInputStream = new ByteArrayInputStream(resizedImage.toByteArray());
                closables.add(imageResizedInputStream);

                fileCloudUploader.upload(resizedFilePath, imageResizedInputStream);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            closables.forEach((s) -> {
                try {
                    s.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            });
        }

        return originalFileBlob;
    }
}
