package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.ISolutionService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.results.*;
import com.example.solutionchallenge.entities.concretes.Solution;
import com.example.solutionchallenge.repo.abstracts.ISolutionDao;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolutionManager implements ISolutionService {
    private final ISolutionDao iSolutionDao;

    @Override
    public DataResult<List<Solution>> getAll() {
        return new SuccesDataResult(iSolutionDao.findAll(), Messages.solutionsListed);
    }

    @Override
    public IResult add(Solution solution) {
        iSolutionDao.save(solution);
        return new SuccessResult(Messages.solutionAdd);
    }

    @Override
    public IResult update(Solution solution) {
        iSolutionDao.save(solution);
        return new SuccessResult(Messages.solutionUpdate);
    }

    @Override
    public IResult delete(Solution solution) {
        iSolutionDao.delete(solution);
        return new SuccessResult(Messages.solutionDelete);
    }

    @Override
    public DataResult<Solution> getById(Integer id) {
        return new SuccesDataResult<>(iSolutionDao.getById(id),Messages.soluitonListed);
    }

    @Override
    public IResult existBySolutionId(int solutionId) {
        if (iSolutionDao.existsById(solutionId))
            return new SuccessResult((Messages.solutionFound));
        return new ErrorResult(Messages.solutionNotFound);

    }
}
