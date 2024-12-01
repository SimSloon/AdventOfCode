package org.likid.aoc.year2022.day7;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

class File {

    private final File parent;

    private final List<File> subFiles = new ArrayList<>();

    private final String path;

    private final boolean directory;
    private long size = 0;

    public File(String path, boolean directory) {
        this(path, directory, null);
    }

    public File(String path, boolean directory, File parent) {
        this.path = path;
        this.directory = directory;
        this.parent = parent;
    }

    public File(String path, long size, File parent) {
        this.path = path;
        this.directory = false;
        this.size = size;
        this.parent = parent;
        File currentParent = parent;
        do {
            currentParent.incrementSize(size);
            currentParent = currentParent.getParent();
        } while (currentParent != null);
    }

    private void incrementSize(long size) {
        this.size += size;
    }

    public String getPath() {
        return path;
    }

    public boolean isDirectory() {
        return directory;
    }

    public long getSize() {
        return size;
    }

    public List<File> getSubFiles() {
        return subFiles;
    }

    @JsonIgnore
    public File getParent() {
        return parent;
    }

    public void addSubFile(File file) {
        this.subFiles.add(file);
    }

    @Override
    public String toString() {
        return "File{" +
                "path='" + path + '\'' +
                ", size=" + size +
                '}';
    }
}