package org.likid.aoc.year2024.day9;

import java.util.ArrayList;
import java.util.List;

class DiskCompactor {
    private final DiskState state;

    public DiskCompactor(String data) {
        state = parseDiskMap(data);
    }

    static DiskState parseDiskMap(String diskMap) {
        List<Integer> files = new ArrayList<>();
        List<Integer> freeSpaces = new ArrayList<>();

        for (int i = 0; i < diskMap.length(); i++) {
            int length = Character.getNumericValue(diskMap.charAt(i));
            if (i % 2 == 0) {
                files.add(length);
            } else {
                freeSpaces.add(length);
            }
        }
        if (files.size() > freeSpaces.size()) {
            freeSpaces.add(0);
        }
        return new DiskState(files, freeSpaces);
    }

    public void compact() {
        state.compact();
    }

    public void compactWholeFiles() {
        state.compactWholeFiles();
    }

    public long checksum() {
        return state.calculateChecksum();
    }

    static class DiskState {
        private final List<Integer> files;
        private final List<Integer> freeSpaces;
        private final StringBuilder diskBlocks;

        public DiskState(List<Integer> files, List<Integer> freeSpaces) {
            this.files = files;
            this.freeSpaces = freeSpaces;
            this.diskBlocks = new StringBuilder();
            initializeDiskBlocks();
            System.out.println(this);
        }

        private void initializeDiskBlocks() {
            int fileId = 0;
            for (int i = 0; i < files.size(); i++) {
                diskBlocks.append(generateUnicodeId(fileId).repeat(files.get(i)));
                diskBlocks.append(".".repeat(freeSpaces.get(i)));
                fileId++;
            }
        }

        private String generateUnicodeId(int id) {
            return String.valueOf((char) ('Ā' + id));
        }

        public void compact() {
            while (true) {
                int emptyIndex = diskBlocks.indexOf(".");
                if (emptyIndex == -1) break;

                int lastFileBlock = findLastFileBlock();
                if (lastFileBlock == -1 || lastFileBlock <= emptyIndex) break;

                diskBlocks.setCharAt(emptyIndex, diskBlocks.charAt(lastFileBlock));
                diskBlocks.setCharAt(lastFileBlock, '.');
            }
        }

        public void compactWholeFiles() {
            for (int fileId = files.size() - 1; fileId >= 0; fileId--) {
                String fileChar = generateUnicodeId(fileId);
                int fileStart = diskBlocks.indexOf(fileChar);

                if (fileStart == -1) continue;

                int fileLength = files.get(fileId);
                int freeSpaceStart = findLargestFreeSpaceSpan(fileLength);

                if (freeSpaceStart != -1 && freeSpaceStart < fileStart) {
                    for (int i = 0; i < fileLength; i++) {
                        diskBlocks.setCharAt(fileStart + i, '.');
                    }
                    for (int i = 0; i < fileLength; i++) {
                        diskBlocks.setCharAt(freeSpaceStart + i, fileChar.charAt(0));
                    }
                }
            }
        }

        private int findLargestFreeSpaceSpan(int requiredLength) {
            int maxStart = -1;
            int currentLength = 0;

            for (int i = 0; i < diskBlocks.length(); i++) {
                if (diskBlocks.charAt(i) == '.') {
                    if (currentLength == 0) {
                        maxStart = i;
                    }
                    currentLength++;

                    if (currentLength == requiredLength) {
                        return maxStart;
                    }
                } else {
                    currentLength = 0;
                }
            }

            return -1;
        }

        private int findLastFileBlock() {
            for (int i = diskBlocks.length() - 1; i >= 0; i--) {
                if (diskBlocks.charAt(i) != '.') {
                    return i;
                }
            }
            return -1;
        }

        public long calculateChecksum() {
            long checksum = 0;
            for (int i = 0; i < diskBlocks.length(); i++) {
                char block = diskBlocks.charAt(i);
                if (block != '.') {
                    int id = block - 'Ā';
                    checksum += (long) i * id;
                }
            }
            return checksum;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            diskBlocks.chars().mapToObj(c -> c != '.' ? String.valueOf(c - 'Ā') : ".").forEach(sb::append);
            return sb.toString();
        }
    }
}