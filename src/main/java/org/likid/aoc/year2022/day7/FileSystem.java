package org.likid.aoc.year2022.day7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

class FileSystem {

    private final File root = new File("/", true);

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