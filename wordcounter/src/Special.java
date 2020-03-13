public class Special {      //为特殊行封装一个类，以便于返回特殊行时能返回多个值
    private int nulllines=0;
    private int codelines=0;
    private int commentlines=0;

    public int getNulllines() {
        return nulllines;
    }

    public int getCodelines() {
        return codelines;
    }

    public int getCommentlines() {
        return commentlines;
    }

    public void setNulllines(int nulllines) {
        this.nulllines = nulllines;
    }

    public void setCodelines(int codelines) {
        this.codelines = codelines;
    }

    public void setCommentlines(int commentlines) {
        this.commentlines = commentlines;
    }
}
