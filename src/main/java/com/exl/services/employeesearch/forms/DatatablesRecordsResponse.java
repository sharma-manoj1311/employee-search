package com.exl.services.employeesearch.forms;

import java.util.List;


public class DatatablesRecordsResponse<T> {

    private final List<T> data;
    private final Long recordsTotal;
    private final Long recordsFiltered;
    private final Integer draw;

    private DatatablesRecordsResponse(DatatablesDataSet<T> dataSet, DataTableRequestDTO criterias) {
        this.data = dataSet.getRows();
        this.recordsTotal = dataSet.getTotalRecords();
        this.recordsFiltered = dataSet.getTotalDisplayRecords();
        this.draw = criterias.getDraw();
    }

    
    public static <T> DatatablesRecordsResponse<T> build(DatatablesDataSet<T> dataSet, DataTableRequestDTO criterias) {
        return new DatatablesRecordsResponse<T>(dataSet, criterias);
    }

    
    public List<T> getData() {
        return data;
    }

    
    public Long getRecordsTotal() {
        return recordsTotal;
    }

    
    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    
    public Integer getDraw() {
        return draw;
    }
}

