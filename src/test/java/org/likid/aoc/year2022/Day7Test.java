package org.likid.aoc.year2022;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day7Test {

    static List<String> content;
    static FileSystem fileSystem;

    @BeforeAll
    static void map() throws IOException {
        content = Util.readFileAsString("classpath:year2022/day7/input");
        fileSystem = new FileSystem(content);
    }

    @Test
    void should_day7_ex1() {
        System.out.println("day 7 ex 1");

        long sum = fileSystem.getAllSubDirectories(fileSystem.getRoot()).stream()
                .filter(f -> f.getSize() < 100_000)
                .mapToLong(File::getSize)
                .sum();

        System.out.println("result : " + sum);

        assertThat(sum).isEqualTo(1297159L);
    }

    @Test
    void should_day7_ex2() {
        System.out.println("day 7 ex 2");

        long smallestDirectorySizeToDelete = fileSystem.getAllSubDirectories(fileSystem.getRoot()).stream()
                .sorted(Comparator.comparing(File::getSize))
                .filter(f -> f.getSize() + fileSystem.getFreeSpace() >= 30000000)
                .map(File::getSize)
                .findFirst()
                .orElseThrow();

        System.out.println("result : " + smallestDirectorySizeToDelete);

        assertThat(smallestDirectorySizeToDelete).isEqualTo(3866390);
    }

    static class FileSystem {

        private File root = new File("/", true);

        public FileSystem(List<String> commands) {
            File currentDir = root;
            for (String command : commands) {
                if (command.startsWith("$")) {
                    if (command.startsWith("$ cd")) {
                        String move = command.substring(command.indexOf("$ cd ") + 5);
                        if (move.startsWith("/")) {
                            currentDir = root;
                        } else if (move.equals("..")) {
                            currentDir = currentDir.getParent();
                        } else if (!move.equals("ls")) {
                            currentDir = findDirectory(currentDir.getPath() + move + "/", currentDir);
                        }
                    }
                } else if (command.startsWith("dir ")) {
                    currentDir.addSubFile(
                            new File(
                                    currentDir.getPath() + command.substring(command.indexOf("dir ") + 4) + "/",
                                    true,
                                    currentDir
                            )
                    );
                } else {
                    String[] split = command.split(" ");
                    currentDir.addSubFile(
                            new File(
                                    currentDir.getPath() + split[1],
                                    Long.parseLong(split[0]),
                                    currentDir)
                    );
                }
            }

        }

        public File findDirectory(String move, File rootFile) {
            for (File f : rootFile.getSubFiles()) {
                if (f.isDirectory()) {
                    if (f.getPath().equals(move)) {
                        return f;
                    } else if (move.startsWith(f.getPath())) {
                        return findDirectory(move, f);
                    }
                }
            }
            throw new IllegalStateException();
        }

        public List<File> getAllSubDirectories(File file) {
            List<File> directories = new ArrayList<>();
            directories.add(file);
            file.getSubFiles().stream()
                    .filter(File::isDirectory)
                    .map(this::getAllSubDirectories)
                    .forEach(directories::addAll);
            return directories;
        }

        public File getRoot() {
            return root;
        }

        public long getFreeSpace() {
            return 70000000 - root.getSize();
        }

        @Override
        public String toString() {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(root);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class File {

        private File parent;

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
}