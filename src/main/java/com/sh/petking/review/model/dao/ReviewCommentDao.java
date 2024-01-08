package com.sh.petking.review.model.dao;

import com.sh.petking.review.model.entity.ReviewComment;
import org.apache.ibatis.session.SqlSession;

public class ReviewCommentDao {
    public int insertComment(SqlSession session, ReviewComment reviewComment) {
        return session.insert("review.insertComment", reviewComment);
    }

    public int deleteComment(SqlSession session, Long id) {
        return session.delete("review.deleteComment", id);
    }
}
