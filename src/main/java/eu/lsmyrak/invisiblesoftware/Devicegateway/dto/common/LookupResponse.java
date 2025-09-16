package eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common;

import java.util.List;

public class LookupResponse<T> {
    private List<LookupColumn> columns;
    private List<T> data;

    public LookupResponse() {

    }

    public LookupResponse(List<LookupColumn> columns, List<T> data) {
        this.columns = columns;
        this.data = data;

    }

    public List<LookupColumn> getColumns() {
        return this.columns;
    }

    public void setColumns(List<LookupColumn> columns) {
        this.columns = columns;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
