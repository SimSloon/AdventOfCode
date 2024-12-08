package org.likid.aoc.year2021.day16;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

class Packet {
    private final long value;
    private final long version;
    private final long typeId;
    private List<String> valuesBinary;
    private List<Packet> subPackets;

    public Packet(String version, String typeId, List<?> values) {
        this.version = convertBinaryToDecimal(version);
        this.typeId = convertBinaryToDecimal(typeId);
        if (values.get(0) instanceof String) {
            this.valuesBinary = values.stream().map(String::valueOf).toList();
            this.value = parseValue();
        } else {
            this.subPackets = values.stream().map(v -> (Packet) v).toList();
            this.value = switch ((int) this.typeId) {
                case 0 -> this.subPackets.stream().map(Packet::getValue).reduce(Long::sum).orElseThrow();
                case 1 -> this.subPackets.stream().map(Packet::getValue).reduce((a, b) -> a * b).orElseThrow();
                case 2 -> this.subPackets.stream().map(Packet::getValue).reduce(Long::min).orElseThrow();
                case 3 -> this.subPackets.stream().map(Packet::getValue).reduce(Long::max).orElseThrow();
                case 5 -> this.subPackets.get(0).getValue() > this.subPackets.get(1).getValue() ? 1 : 0;
                case 6 -> this.subPackets.get(0).getValue() < this.subPackets.get(1).getValue() ? 1 : 0;
                default -> this.subPackets.get(0).getValue() == this.subPackets.get(1).getValue() ? 1 : 0;
            };
        }
    }

    public static Long convertBinaryToDecimal(String binaryValue) {
        return Long.valueOf(binaryValue, 2);
    }

    public static Pair<String, String> split(String binaries, int size) {
        if (binaries.isEmpty()) {
            return Pair.of("", "");
        }
        if (binaries.length() < size) {
            return Pair.of(binaries.substring(0, binaries.length() - 1), "");
        }
        String substring = binaries.substring(0, size);
        binaries = binaries.substring(size);
        return Pair.of(substring, binaries);
    }

    private Long parseValue() {
        return this.valuesBinary.stream()
                .map(binaryValue -> split(binaryValue, 1).getRight())
                .reduce(String::concat)
                .map(Packet::convertBinaryToDecimal)
                .orElseThrow();
    }

    public long getValue() {
        return value;
    }

    public long getVersionSum() {
        if (subPackets == null) {
            return version;
        }
        return version + subPackets.stream().mapToLong(Packet::getVersionSum).sum();
    }

    public void display(String level) {
        System.out.printf("%sPacket %d | type=%d | value=%d\n", level, this.version, this.typeId, this.value);
        if (subPackets != null) {
            for (Packet subPacket : this.subPackets) {
                subPacket.display(level + "\t");
            }
        }
    }
}