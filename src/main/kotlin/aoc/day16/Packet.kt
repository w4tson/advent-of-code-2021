package aoc.day16

import aoc.day16.OpType.*
import aoc.takeWhileInclusive
import java.lang.Long.max
import java.lang.Long.min
import java.lang.RuntimeException

enum class PacketType {
    LITERAL, OPERATOR_BIT_LENGTH, OPERATOR_NUMBER
}

enum class OpType {
    SUM,
    MUL,
    MIN,
    MAX,
    LITERAL,
    GREATER_THAN,
    LESS_THAN,
    EQ
}

interface IPayload {
    fun size() : Int
    fun getVersion() : Int
    fun sumVersion() : Int
    fun value() : Long
}

open abstract class Payload : IPayload

class LiteralPayload(val rawPayload: String) : Payload() {
    val payload : String
    val value : Long
    init {
        payload = toBlocksOf5()
            .map { it.drop(1) }
            .joinToString("")

        value = java.lang.Long.parseLong(payload, 2)
    }
    
    fun toBlocksOf5() : List<String> {
        return rawPayload
            .windowed(5, 5)
            .takeWhileInclusive { !it.startsWith("0") }
    }
    
    override fun size(): Int = toBlocksOf5().size * 5
    override fun getVersion(): Int = 0
    override fun sumVersion(): Int = 0
    override fun value(): Long = value
    override fun toString(): String = "Literal Payload [$value] ($payload)"
}

class Packet(val raw: String) : Payload() {
    
    constructor(hex : String, isHex: Boolean) : this(hexStringToBinary(hex)) {}
    
    val typeId = getTypeId(raw)
    val payload : List<Payload>
    init {
        payload = when(typeId) {
            4 -> listOf(LiteralPayload(raw.drop(6)))
            else -> operator()
        }
    }
    
    fun lengthTypeId() : Char = raw.drop(6).take(1).first()
    
    fun operator() : List<Packet> = when (lengthTypeId()) {
        '0' -> toPacketsByBitLength()
        else -> toPacketsByNumber()
    }
    
    fun toPacketsByBitLength() : List<Packet> {
        val rawPayloads = raw.drop(6+15+1).take(getBitLengthOfPayload())
        //chomp down raw thing into packets
        val packets = mutableListOf<Packet>()
        var tempRaw = rawPayloads
        do {
            val p = Packet(tempRaw)
            packets.add(p)
            tempRaw = tempRaw.drop(p.size())
            
        } while(tempRaw.isNotEmpty())
        
        return packets
    }
    
    fun toPacketsByNumber() : List<Packet> {
        val numberOfPackets = getNumberOfPackets()
        val rawPayloads = raw.drop(6+11+1)
        
        return (0 until numberOfPackets)
            .fold(Pair(listOf<Packet>(), rawPayloads)) { (packetList,raw), _ ->
                val packet = Packet(raw)
                Pair(packetList+ packet, raw.drop(packet.size()))
            }.first
    }
    
    override fun sumVersion() : Int {
        return getVersion() + payload.sumBy { it.sumVersion() }
    }

    override fun value(): Long {
        return when(typeId) {
            SUM.ordinal -> payload.fold(0L) { acc, it -> acc + it.value() }
            MUL.ordinal -> payload.fold(1L) { acc, it -> acc * it.value() }
            MIN.ordinal -> payload.fold(Long.MAX_VALUE) { acc, it -> min(acc, it.value()) }
            MAX.ordinal -> payload.fold(Long.MIN_VALUE) { acc, it -> max(acc, it.value()) }
            LITERAL.ordinal -> payload[0].value()
            GREATER_THAN.ordinal -> if (payload[0].value() > payload[1].value()) { 1 } else { 0 }
            LESS_THAN.ordinal -> if (payload[0].value() < payload[1].value()) { 1 } else { 0 }
            EQ.ordinal -> if (payload[0].value() == payload[1].value()) { 1 } else { 0 }
            else -> throw RuntimeException("nope")
        }
    }

    //TODO add padding?
    override fun size() : Int {
        return when(typeId) {
            4 -> 6 + payload[0].size()
            else ->  sizeOfOperator()
        }
    }

    override fun getVersion(): Int = Integer.parseInt(raw.take(3), 2)

    fun sizeOfOperator() : Int {
        val lengthValueSize = if (lengthTypeId() == '0') { 15 } else { 11 }
        return 6 + 1 + lengthValueSize + payload.map { it.size() }.sum()
    }
    
    fun pad(i : Int) : Int {
        return if (i % 4 ==0) { i } else { 4 - (i%4) + i }
    }
     
    fun getBitLengthOfPayload() : Int = Integer.parseInt(raw.drop(7).take(15), 2)
    fun getNumberOfPackets() : Int = Integer.parseInt(raw.drop(7).take(11), 2)
    
    fun getTypeId(input: String): Int  {
        return Integer.parseInt(input.drop(3).take(3), 2)
    }

    override fun toString(): String {
        var res =  "Packet ${getType()}"
        if (getType() == PacketType.LITERAL) {
            res += "[${payload[0]}]"
        } else {
            val payloads = payload.map { 
                "\t\t$it"
            }.joinToString("\n")
            res += "\n$payloads"
        }
        return res
    }

    fun getType() : PacketType = if (typeId ==4) { PacketType.LITERAL }
        else if (lengthTypeId() == '0') { PacketType.OPERATOR_BIT_LENGTH }
        else PacketType.OPERATOR_NUMBER
}

fun hexStringToBinary(hex: String) : String {
    return hex.map { hexCharToBinaryString(it) }.joinToString("")
}

fun hexCharToBinaryString(hex: Char): String {
    val i = Integer.parseInt("$hex", 16);
    return Integer.toBinaryString(i).padStart(4, '0');
}