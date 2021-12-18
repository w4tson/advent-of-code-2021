package aoc.day16

import aoc.Util
import org.junit.Assert.*
import org.junit.Test

class Day16Test {
    val input = Util.readInput("/day16/input.txt")

    @Test
    fun packet2() {
        val packet = Packet("38006F45291200")
        println(packet)
    }

    @Test
    fun example3() {
        val packet = Packet("EE00D40C823060")
        println(packet)
    }

    @Test
    fun example4() {
        assertEquals(16, Packet("8A004A801A8002F478").sumVersion())
        assertEquals(12, Packet("620080001611562C8802118E34").sumVersion())
        assertEquals(23, Packet("C0015000016115A2E0802F182340").sumVersion())
        assertEquals(31, Packet("A0016C880162017C3686B18A3D4780").sumVersion())
    }

    @Test
    fun examplePart2() {
        assertEquals(3, Packet("C200B40A82").value())
        assertEquals(54, Packet("04005AC33890").value())
        assertEquals(7, Packet("880086C3E88112").value())
        assertEquals(9, Packet("CE00C43D881120").value())
        assertEquals(1, Packet("D8005AC2A8F0").value())
        assertEquals(0, Packet("F600BC2D8F").value())
        assertEquals(0, Packet("9C005AC2F8F0").value())
        assertEquals(1, Packet("9C0141080250320F1802104A08").value())
    }

    @Test
    fun part1() {
        println(Packet(input).sumVersion())
    }
    
    @Test
    fun part2() {
        println(Packet(input).value())
    }
}

