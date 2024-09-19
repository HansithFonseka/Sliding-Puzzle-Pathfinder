public class Places {
    private final int rowNumber;
    private final int columnNumber;
    private Places parent;
    public String direction;

    public Places(int rowNumber, int columnNumber) {
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
    }

    public Places getParent() {
        return parent;
    }

    public void setParent(Places parent) {
        this.parent = parent;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}
