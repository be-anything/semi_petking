package com.sh.petking.board.model.vo;

import com.sh.petking.board.model.entity.Attachment;
import com.sh.petking.board.model.entity.BoardAttach;
import com.sh.petking.review.model.vo.ReviewVo;

public class AttachmentVo extends Attachment {
    private BoardAttach boardAttach;
    private ReviewVo reviewVo;

    public ReviewVo getReviewVo() {
        return reviewVo;
    }

    public void setReviewVo(ReviewVo reviewVo) {
        this.reviewVo = reviewVo;
    }

    public BoardAttach getBoardAttach() {
        return boardAttach;
    }

    public void setBoardAttach(BoardAttach boardAttach) {
        this.boardAttach = boardAttach;
    }
}
