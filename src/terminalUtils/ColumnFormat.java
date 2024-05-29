package terminalUtils;

public class ColumnFormat {
    private int width;
    private String columnName;
    private String colour;

    public ColumnFormat(int width, String columnName, String colour) {
        this.width = width;
        this.columnName = columnName;
        this.colour = colour;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
