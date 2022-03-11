package com.example.solutionchallenge.core.api;

import com.example.solutionchallenge.core.entities.IEntity;
import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin
public class ControllerBase<T extends IEntity, ServiceBase extends IServiceBase<T>> {

    private final ServiceBase serviceBase;

    @GetMapping("/getall")
    ResponseEntity<?> getall() {
        var result = serviceBase.getAll();
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getbyid")
    ResponseEntity<?> getById(@RequestParam int id) {
        var result = serviceBase.getById(id);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    ResponseEntity<?> add(@RequestBody @Valid T t) {
        var result = serviceBase.add(t);

        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    ResponseEntity<?> delete(@RequestBody T t) {
        var result = serviceBase.delete(t);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@RequestBody @Valid T t) {
        var result = serviceBase.update(t);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
