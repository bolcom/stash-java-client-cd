package com.bol.cd.stash.model;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    private static final long serialVersionUID = 3845096183628438741L;
    private int size;
    private int limit;
    private boolean isLastPage;
    private List<T> values;
    private int start;
    private int nextPageStart;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public boolean getIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getNextPageStart() {
        return nextPageStart;
    }

    public void setNextPageStart(int nextPageStart) {
        this.nextPageStart = nextPageStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page<?> page = (Page<?>) o;

        if (size != page.size) return false;
        if (limit != page.limit) return false;
        if (isLastPage != page.isLastPage) return false;
        if (start != page.start) return false;
        if (nextPageStart != page.nextPageStart) return false;
        return values != null ? values.equals(page.values) : page.values == null;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + limit;
        result = 31 * result + (isLastPage ? 1 : 0);
        result = 31 * result + (values != null ? values.hashCode() : 0);
        result = 31 * result + start;
        result = 31 * result + nextPageStart;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Page{");
        sb.append("size=").append(size);
        sb.append(", limit=").append(limit);
        sb.append(", lastPage=").append(isLastPage);
        sb.append(", values=").append(values);
        sb.append(", start=").append(start);
        sb.append(", nextPageStart=").append(nextPageStart);
        sb.append('}');
        return sb.toString();
    }
}
