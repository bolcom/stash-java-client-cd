package com.bol.cd.stash.model;

/**
 * DTO for the response of requesting a file type.
 * Explicitly created for deserialization purposes only, so just a setter.
 * No getter for inner enum, so it stays hidden. Just directly convenience boolean methods for requesting state.
 */
public class FileType {

    private Type type;
    
    public FileType() {
    }

    public boolean isFile() {
        return this.type == Type.FILE;
    }

    public boolean isDirectory() {
        return this.type == Type.DIRECTORY;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    private static enum Type {
        FILE, DIRECTORY;
    }
}
