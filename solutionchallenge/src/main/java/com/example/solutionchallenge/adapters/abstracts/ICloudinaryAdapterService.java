package com.example.solutionchallenge.adapters.abstracts;

import com.example.solutionchallenge.core.utilities.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ICloudinaryAdapterService {
    DataResult<Map> uploadImage(MultipartFile image);
}