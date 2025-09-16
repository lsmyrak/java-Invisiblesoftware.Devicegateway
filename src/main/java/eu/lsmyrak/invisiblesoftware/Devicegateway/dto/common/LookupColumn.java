package eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common;

public class LookupColumn {
    private String key;
    private String label;
    private boolean display = true;

    public LookupColumn() {
    }

    public LookupColumn(String key, String label, boolean display) {
        this.key = key;
        this.label = label;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

}
