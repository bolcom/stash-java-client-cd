package com.bol.cd.stash.model;

import java.io.Serializable;
import java.util.List;

public class LinesPage implements Serializable {
    private static final long serialVersionUID = -254713561123694752L;
    private int size;
    private int limit;
    private boolean lastPage;

    private List<Line> lines;

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

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinesPage linesPage = (LinesPage) o;

        if (lastPage != linesPage.lastPage) return false;
        if (limit != linesPage.limit) return false;
        if (size != linesPage.size) return false;
        if (lines != null ? !lines.equals(linesPage.lines) : linesPage.lines != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + limit;
        result = 31 * result + (lastPage ? 1 : 0);
        result = 31 * result + (lines != null ? lines.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LinesPage{");
        sb.append("size=").append(size);
        sb.append(", limit=").append(limit);
        sb.append(", lastPage=").append(lastPage);
        sb.append(", lines=").append(lines);
        sb.append('}');
        return sb.toString();
    }

    public static class Line implements Serializable {
        private static final long serialVersionUID = 376933168316479816L;
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Line line = (Line) o;

            if (text != null ? !text.equals(line.text) : line.text != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return text != null ? text.hashCode() : 0;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Line{");
            sb.append("text='").append(text).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
