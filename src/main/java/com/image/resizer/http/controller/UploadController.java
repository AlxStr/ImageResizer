package com.image.resizer.http.controller;

import com.google.cloud.storage.Blob;
import com.image.resizer.constraint.ValidImage;
import com.image.resizer.http.response.UploadResponse;
import com.image.resizer.service.ImageResolver;
import com.image.resizer.transformer.BlobToUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequiredArgsConstructor
public class UploadController {
    private final ImageResolver imageResolver;

    @RequestMapping(path = "upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public UploadResponse upload(@RequestParam("file") @ValidImage MultipartFile file) {
        Blob blob = imageResolver.resolve(file);

        return BlobToUploadResponse.transform(blob);
    }
}
