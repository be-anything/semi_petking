package com.sh.petking.board.model.entity;

public class BoardAttach {
    private long id;
    private long attachId;
    private long boardId;
    private int boardAttr;

    public BoardAttach() {
    }

    public BoardAttach(long id, long attachId, long boardId, int boardAttr) {
        this.id = id;
        this.attachId = attachId;
        this.boardId = boardId;
        this.boardAttr = boardAttr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAttachId() {
        return attachId;
    }

    public void setAttachId(long attachId) {
        this.attachId = attachId;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public int getBoardAttr() {
        return boardAttr;
    }

    public void setBoardAttr(int boardAttr) {
        this.boardAttr = boardAttr;
    }

    @Override
    public String toString() {
        return "BoardAttach{" +
                "id=" + id +
                ", attachId=" + attachId +
                ", boardId=" + boardId +
                ", boardAttr=" + boardAttr +
                '}';
    }
}
