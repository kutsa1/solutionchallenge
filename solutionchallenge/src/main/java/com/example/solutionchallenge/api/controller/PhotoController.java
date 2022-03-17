package com.example.solutionchallenge.api.controller;

import com.example.solutionchallenge.business.abstracts.IPhotoService;
import com.example.solutionchallenge.core.api.ControllerBase;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.entities.concretes.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/photos")
public class PhotoController extends ControllerBase<Photo, IPhotoService> {
    private final IPhotoService iPhotoService;

    public PhotoController(IPhotoService iPhotoService){
        super(iPhotoService);
        this.iPhotoService = iPhotoService;
    }

    @PostMapping("/addimage")
    public ResponseEntity<?> addImage(@RequestPart MultipartFile image){
        var result = iPhotoService.addImage(image);
        if (result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addphototosolution")
    ResponseEntity<?> addPhotoToSolution(@RequestParam int photoId, @RequestParam int solutionId){
        var result = iPhotoService.addPhotoToSolution(photoId, solutionId);
        if (result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
