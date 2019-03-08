package training.busboard.web;

public class IndexInfo {
    private String errorMessage;
    private boolean isError;

    public IndexInfo() {
        isError = false;
    }

    public IndexInfo(String errorMessage) {
        this.errorMessage = errorMessage;
        isError = true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isError() {
        return isError;
    }
}
