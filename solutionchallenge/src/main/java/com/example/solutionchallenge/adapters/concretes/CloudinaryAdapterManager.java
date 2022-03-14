package com.example.solutionchallenge.adapters.concretes;

import com.cloudinary.Cloudinary;
import com.example.solutionchallenge.adapters.abstracts.ICloudinaryAdapterService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.cloudinaryApi.CloudinaryApi;
import com.example.solutionchallenge.core.utilities.results.DataResult;
import com.example.solutionchallenge.core.utilities.results.ErrorDataResult;
import com.example.solutionchallenge.core.utilities.results.SuccesDataResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryAdapterManager implements ICloudinaryAdapterService {

    private final Cloudinary cloudinary;
    private final CloudinaryApi cloudinaryApi;

    public CloudinaryAdapterManager(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
        this.cloudinaryApi = new CloudinaryApi();
    }

    @Override
    public DataResult<Map> uploadImage(MultipartFile image) {
        var result = cloudinaryApi.uploadImage(image, cloudinary);
        if (result != null || result.isEmpty())
            return new SuccesDataResult<>(result);
        return new ErrorDataResult<>(Messages.imageUploadError);
    }
}
