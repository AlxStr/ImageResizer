package com.image.resizer.service;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ImageResizer {

    public ByteArrayOutputStream resize(int newWidth, int newHeight, InputStream inputStream) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            Thumbnails.of(inputStream)
                .width(newWidth)
                .height(newHeight)
                .outputQuality(1f)
                .toOutputStream(os);

            return os;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
