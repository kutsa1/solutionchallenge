package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.adapters.abstracts.ICloudinaryAdapterService;
import com.example.solutionchallenge.business.abstracts.IPhotoService;
import com.example.solutionchallenge.business.abstracts.ISolutionService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.*;
import com.example.solutionchallenge.entities.concretes.Photo;
import com.example.solutionchallenge.repo.abstracts.IPhotoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoManager implements IPhotoService {

    private final IPhotoDao iPhotoDao;
    private final ICloudinaryAdapterService iCloudinaryAdapterService;
    private final ISolutionService iSolutionService;

    @Override
    public IResult addImage(MultipartFile image) {
        var result = BusinessRule.run(
                isPhotoNull(image),
                isImageSizeValid(image)
        );
        if (result != null)
            return result;
        Photo photo = new Photo();
        var resultImage = iCloudinaryAdapterService.uploadImage(image);

        if (resultImage.isSuccess()){
            photo.setImgUrl(resultImage.getData().get("url").toString());
            iPhotoDao.save(photo);
            return new SuccessResult(Messages.photoSaved);
        }
        return new ErrorResult(result.getMessage());
    }

    @Override
    public IResult addPhotoToSolution(int photoId, int solutionId) {
        var result = BusinessRule.run(isPhotoExistById(photoId), isSolutionExistById(solutionId));

        if (result != null)
            return result;
        var photo = iPhotoDao.getById(photoId);
        var solution = iSolutionService.getById(solutionId);
        photo.setSolution(solution.getData());
        iPhotoDao.save(photo);

        return new SuccessResult(Messages.photoAddedToSolution);
    }

    @Override
    public DataResult<List<Photo>> getAll() {
        return new SuccesDataResult(iPhotoDao.findAll(), Messages.photoListed);
    }

    @Override
    public IResult add(Photo photo) {
        return null;
    }

    @Override
    public IResult update(Photo photo) {
        iPhotoDao.save(photo);
        return new SuccessResult(Messages.photoUpdate);
    }

    @Override
    public IResult delete(Photo photo) {
        iPhotoDao.delete(photo);
        return new SuccessResult(Messages.photoDeleted);
    }

    @Override
    public DataResult<Photo> getById(Integer id) {
        return new SuccesDataResult<>(iPhotoDao.getById(id), Messages.photoListed);
    }

    private IResult isPhotoNull(MultipartFile image) {
        if (image == null)
            return new ErrorResult(Messages.imageCanNotBeNull);
        return new SuccessResult();
    }

    private IResult isImageSizeValid(MultipartFile image) {
        if (image.getSize() > 5000000)
            return new ErrorResult(Messages.imageSizeUnValid);
        return new SuccessResult();
    }

    private IResult isPhotoExistById(int photoId){
        var result = iPhotoDao.existsById(photoId);
        if (!result)
            return new ErrorResult(Messages.photoNotFound);
        return new SuccessResult();
    }

    private IResult isSolutionExistById(int solutionId){
        var result = iSolutionService.getById(solutionId);
        if (result.getData()==null)
            return new ErrorResult(Messages.solutionNotFound);
        return new SuccessResult();
    }
}
