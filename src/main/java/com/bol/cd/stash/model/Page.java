package com.bol.cd.stash.model;

import java.util.List;

public class Page<T> {

    private int size;
    private int limit;
    private boolean lastPage;

    private List<T> values;

    public int getSize() {
        return size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(final int limit) {
        this.limit = limit;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(final boolean lastPage) {
        this.lastPage = lastPage;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(final List<T> values) {
        this.values = values;
    }
}
