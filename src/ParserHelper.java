public class ParserHelper {
    private int tokSeqPos;
    private boolean hasLeftChildAvailable;
    private boolean hasRightChildAvailable;
    private String previousToken;
    private int parentNodePos;
    private int directionFromParent;
    private int treeRow;

    public ParserHelper(int tokSeqPos, boolean hasLeftChildAvailable, boolean hasRightChildAvailable, String previousToken, int parentNodePos, int directionFromParent, int treeRow) {
        this.tokSeqPos = tokSeqPos;
        this.hasLeftChildAvailable = hasLeftChildAvailable;
        this.hasRightChildAvailable = hasRightChildAvailable;
        this.previousToken = previousToken;
        this.parentNodePos = parentNodePos;
        this.directionFromParent = directionFromParent;
        this.treeRow = treeRow;
    }

    //getter methods
    public int getTokSeqPos() {
        return tokSeqPos;
    }
    public boolean getHasLeftChildAvailable() {
        return hasLeftChildAvailable;
    }
    public boolean getHasRightChildAvailable() {
        return hasRightChildAvailable;
    }
    public String getPreviousToken() {
        return previousToken;
    }
    public int getParentNodePos() {
        return parentNodePos;
    }
    public int getDirectionFromParent() {
        return directionFromParent;
    }
    public int getTreeRow() {
        return treeRow;
    }

    //setter methods
    public void setTokSeqPos(int tokSeqPos) {
        this.tokSeqPos = tokSeqPos;
    }
    public void setHasLeftChildAvailable(boolean hasLeftChildAvailable) {
        this.hasLeftChildAvailable = hasLeftChildAvailable;
    }
    public void setHasRightChildAvailable(boolean hasRightChildAvailable) {
        this.hasRightChildAvailable = hasRightChildAvailable;
    }
    public void setIsVariable(String previousToken) {
        this.previousToken = previousToken;
    }
    public void setParentNodePos(int parentNodePos) {
        this.parentNodePos = parentNodePos;
    }
    public void setDirectionFromParent(int directionFromParent) {
        this.directionFromParent = directionFromParent;
    }
    public void setTreeRow(int treeRow) {
        this.treeRow = treeRow;
    }
}
