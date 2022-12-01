package org.likid.aoc.year2021;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.likid.aoc.year2021.Day16.Packet.convertBinaryToDecimal;

public class Day16 {

    @Test
    public void should_day16() throws IOException {
        System.out.println("day 16");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day16/input");

        String binaries = Arrays.stream(inputs.get(0).split(""))
                .map(this::hexToBin)
                .reduce(String::concat)
                .orElseThrow();

        List<Packet> packets = mapToPackets(binaries, -1).getLeft();
        packets.get(0).display("");
        long ex1 = packets.stream().mapToLong(Packet::getVersionSum).sum();
        System.out.println("result ex 1 : " + ex1); // 891
        assertThat(ex1).isEqualTo(891);

        long value = packets.get(0).getValue();
        assertThat(value).isEqualTo(673042777597L);
        System.out.println("result ex 2 : " + value); // 673042777597L
    }

    private Pair<List<Packet>, String> mapToPackets(String binaries, long numberOfPackets) {
        List<Packet> packets = new ArrayList<>();

        while (!binaries.isEmpty()) {
            if (!StringUtils.containsOnly("01", binaries)) {
                break;
            }
            if (numberOfPackets > 0 && packets.size() == numberOfPackets) {
                break;
            }
            Pair<String, String> versionSplit = Packet.split(binaries, 3);
            String version = versionSplit.getLeft();
            binaries = versionSplit.getRight();

            Pair<String, String> typeIdSplit = Packet.split(binaries, 3);
            String typeId = typeIdSplit.getLeft();
            binaries = typeIdSplit.getRight();


            if (Packet.convertBinaryToDecimal(typeId) == 4) {
                List<String> values = new ArrayList<>();
                // value packet
                while (binaries.charAt(0) != '0') {
                    Pair<String, String> splitVal = Packet.split(binaries, 5);
                    binaries = splitVal.getRight();
                    values.add(splitVal.getLeft());
                }
                Pair<String, String> splitVal = Packet.split(binaries, 5);
                binaries = splitVal.getRight();
                values.add(splitVal.getLeft());

                Packet packet = new Packet(version, typeId, values);
                packets.add(packet);
            } else {
                // operator packet
                Pair<String, String> lenghtTypeIdSplit = Packet.split(binaries, 1);
                String lenghtTypeId = lenghtTypeIdSplit.getLeft();
                binaries = lenghtTypeIdSplit.getRight();
                if (lenghtTypeId.equals("0")) {
                    Pair<String, String> totalLengthInBitsSplit = Packet.split(binaries, 15);
                    String totalLengthInBits = totalLengthInBitsSplit.getLeft();
                    binaries = totalLengthInBitsSplit.getRight();

                    long totalLength = convertBinaryToDecimal(totalLengthInBits);

                    Pair<String, String> subPacketsSplit = Packet.split(binaries, (int) totalLength);
                    String subPacketsBinaries = subPacketsSplit.getLeft();
                    binaries = subPacketsSplit.getRight();

                    Pair<List<Packet>, String> subPacketsPair = mapToPackets(subPacketsBinaries, -1);
                    List<Packet> subPackets = subPacketsPair.getLeft();

                    Packet packet = new Packet(version, typeId, subPackets);
                    packets.add(packet);
                } else {
                    Pair<String, String> numberOfSubPacketsImmediatelyContainedSplit = Packet.split(binaries, 11);
                    String numberOfSubPacketsImmediatelyContained = numberOfSubPacketsImmediatelyContainedSplit.getLeft();
                    binaries = numberOfSubPacketsImmediatelyContainedSplit.getRight();

                    long numberOfSubPackets = convertBinaryToDecimal(numberOfSubPacketsImmediatelyContained);

                    Pair<List<Packet>, String> subPacketsPair = mapToPackets(binaries, numberOfSubPackets);
                    binaries = subPacketsPair.getRight();
                    List<Packet> subPackets = subPacketsPair.getLeft();

                    Packet packet = new Packet(version, typeId, subPackets);
                    packets.add(packet);
                }
            }
        }

        return Pair.of(packets, binaries);
    }

    private String hexToBin(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

    public static class Packet {
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
                    case 5 -> this.subPackets.get(0).getValue() > this.subPackets.get(1).getValue() ?  1 : 0;
                    case 6 -> this.subPackets.get(0).getValue() < this.subPackets.get(1).getValue() ? 1 : 0;
                    default -> this.subPackets.get(0).getValue() == this.subPackets.get(1).getValue() ? 1 : 0;
                };
            }
        }

        private Long parseValue() {
            return this.valuesBinary.stream()
                    .map(binaryValue -> split(binaryValue, 1).getRight())
                    .reduce(String::concat)
                    .map(Packet::convertBinaryToDecimal)
                    .orElseThrow();
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
}
