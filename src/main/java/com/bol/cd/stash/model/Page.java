package com.bol.cd.stash.model;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    private static final long serialVersionUID = -5624783442511242212L;
    private int size;
    private int limit;
    private boolean lastPage;

    private List<T> values;

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
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        if (lastPage != page.lastPage) return false;
        if (limit != page.limit) return false;
        if (size != page.size) return false;
        if (values != null ? !values.equals(page.values) : page.values != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + limit;
        result = 31 * result + (lastPage ? 1 : 0);
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Page{");
        sb.append("size=").append(size);
        sb.append(", limit=").append(limit);
        sb.append(", lastPage=").append(lastPage);
        sb.append(", values=").append(values);
        sb.append('}');
        return sb.toString();
    }
}
