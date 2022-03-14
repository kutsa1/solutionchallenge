package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.ICommentService;
import com.example.solutionchallenge.business.abstracts.IHomeworkService;
import com.example.solutionchallenge.business.abstracts.IUserService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.*;
import com.example.solutionchallenge.entities.concretes.Comment;
import com.example.solutionchallenge.repo.abstracts.ICommentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentManager implements ICommentService {
    private final ICommentDao iCommentDao;
    private final IUserService iUserService;
    private final IHomeworkService iHomeworkService;


    @Override
    public IResult addCommentToHomework(int commentId, int homeworkId) {
        var result = BusinessRule.run(isCommentExistById(commentId), isHomeworkExistById(homeworkId));

        if (result != null)
            return result;
        var comment = iCommentDao.getById(commentId);
        var homework = iHomeworkService.getById(homeworkId);

        comment.setHomework(homework.getData());

        iCommentDao.save(comment);
        return new SuccessResult();
    }

    @Override
    public IResult addCommentToUser(int commentId, int userId) {
        var result = BusinessRule.run(isCommentExistById(commentId),isExistUserById(userId));

        if (result != null)
            return result;
        var comment = iCommentDao.getById(commentId);
        var user = iUserService.getById(userId);
        comment.setUser(user.getData());
        iCommentDao.save(comment);

        return new SuccessResult(Messages.commentAddedToUser);
    }


    @Override
    public DataResult<List<Comment>> getAll() {
        return new SuccesDataResult(iCommentDao.findAll(),Messages.commentListed);

    }

    @Override
    public IResult add(Comment comment) {
        comment.setDate(LocalDateTime.now());
        iCommentDao.save(comment);
        return new SuccessResult(Messages.commentSave);
    }

    @Override
    public IResult update(Comment comment) {
        iCommentDao.save(comment);
        return new SuccessResult(Messages.commentUpdate);

    }

    @Override
    public IResult delete(Comment comment) {
        iCommentDao.delete(comment);
        return new SuccessResult(Messages.commentDelete);
    }

    @Override
    public DataResult<Comment> getById(Integer id) {
        return new SuccesDataResult(iCommentDao.getById(id),Messages.commentListed);
    }
    private IResult isCommentExistById(int commentId) {
        var result = iCommentDao.existsById(commentId);
        if (!result)
            return new ErrorResult(Messages.commentNotFound);

        return new SuccessResult();
    }

    private IResult isExistUserById(int userId) {

        var result = iUserService.existsById(userId);
        if (!result.isSuccess())
            return new ErrorResult(Messages.userNotFound);
        return new SuccessResult();
    }

    private IResult isHomeworkExistById(int homeworkId){
    var result = iHomeworkService.getById(homeworkId);
    if (result.getData()==null)
        return new ErrorResult(Messages.homeworkNotFound);
    return new SuccessResult();

    }
}
