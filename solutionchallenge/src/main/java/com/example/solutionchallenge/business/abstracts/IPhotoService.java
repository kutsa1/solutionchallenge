package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.entities.concretes.Photo;
import org.springframework.web.multipart.MultipartFile;

public interface IPhotoService extends IServiceBase<Photo> {
    IResult addImage(MultipartFile image);
    IResult addPhotoToSolution(int photoId, int solutionId);
}
