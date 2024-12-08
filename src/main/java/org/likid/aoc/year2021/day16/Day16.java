package org.likid.aoc.year2021.day16;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.likid.aoc.year2021.day16.Packet.convertBinaryToDecimal;

public class Day16 extends AbstractDay<Long, Long> {

    private final List<Packet> packets;

    public Day16(List<String> data) {
        super(data);
        String binaries = Arrays.stream(data.getFirst().split(""))
                .map(this::hexToBin)
                .reduce(String::concat)
                .orElseThrow();
        packets = mapToPackets(binaries, -1).getLeft();
        packets.getFirst().display("");
    }

    @Override
    public Long ex1() {
        return packets.stream().mapToLong(Packet::getVersionSum).sum();
    }

    @Override
    public Long ex2() {
        return packets.getFirst().getValue();
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


            if (convertBinaryToDecimal(typeId) == 4) {
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
}
