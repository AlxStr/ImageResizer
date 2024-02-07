package com.image.resizer.transformer;

import com.google.cloud.storage.Blob;
import com.image.resizer.http.response.UploadResponse;

public final class BlobToUploadResponse {

    public static UploadResponse transform(Blob blob) {
        String path = String.format("%s/%s/%s", blob.getStorage()
            .getOptions()
            .getHost(),
                blob.getBlobId()
                    .getBucket(),
                blob.getBlobId()
                    .getName());

        return UploadResponse.builder()
            .path(path)
            .build();
    }
}
