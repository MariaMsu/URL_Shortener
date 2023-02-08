package com.example.url_shortener.service

import com.example.url_shortener.dto.AppendEntryArgsDto
import com.example.url_shortener.dto.Entry
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.security.MessageDigest

@Service
class StorageService() {
    // map sort_url to long_url

    // We initialize the first Entry to simplify processing
    val entryLog: MutableList<Entry> = mutableListOf(
        Entry(0, "", "")
    )

    // index of highest log entry known to be committed
    var commitIndex: Int = 1 // TODO private set

    // index of highest log entry applied to state machine
    var lastApplied: Int = 1 // TODO private set

    var term: Int = 1 // TODO private set

    public fun increaseTerm(): Int {
        term += 1
        return term
    }

    public fun getUrl(shortUrl: String): String {
        return data.getOrDefault(shortUrl, "not saved")
    }

    public fun logEntry(appendEntryArgs: AppendEntryArgsDto): Boolean {
        if (appendEntryArgs.term < this.term) {
            // Reply false if term < currentTerm (§5.1)
            return false
        }

        val curLogIndex = entryLog.size - 1
        if (appendEntryArgs.prevLogIndex > curLogIndex) {
            // we missed some entries, Leader should send previous blocks
            return false
        }
        if (appendEntryArgs.prevLogTerm < curLogIndex) {
            if (appendEntryArgs.prevLogTerm == entryLog.last().term) {
                // If an existing entry conflicts with a new one (same index but different terms),
                // delete the existing entry and all that follow it (§5.3)

                // remove elements from 1st_arg to 2nd_arg
                entryLog.subList(appendEntryArgs.prevLogTerm, curLogIndex).clear()
                // TODO rollback this.data

                if (appendEntryArgs.prevLogIndex != curLogIndex) {
                    TODO("Error. appendEntryArgs.prevLogIndex=${appendEntryArgs.prevLogIndex}, curLogIndex=${curLogIndex}")
                }
            } else {
                // Reply false if log doesn’t contain an entry at prevLogIndex whose term matches prevLogTerm (§5.3)
                return false
            }
        }

        //  Append any new entries not already in the log
        entryLog.add(appendEntryArgs.entry)
        data[appendEntryArgs.entry.shortUrl] = appendEntryArgs.entry.longUrl

        // If leaderCommit > commitIndex, set commitIndex = min(leaderCommit, index of last new entry)
        if (appendEntryArgs.leaderCommit > commitIndex) {
            commitIndex = minOf(appendEntryArgs.leaderCommit, entryLog.size)
        }
        return true
    }

    public fun leaderLogUrlAndComputeAEDto(longUrl: String): AppendEntryArgsDto {
        val shortUrl = mapLongUrl2Short(longUrl)
        val newEntry = Entry(term = this.term, shortUrl = shortUrl, longUrl = longUrl)
        entryLog.add(newEntry)
        data[shortUrl] = longUrl // todo do it only after commit
        // todo assign real leaderId & leaderCommit
        val tmp = AppendEntryArgsDto(
            term = term,
            leaderId = 0,
            entry = newEntry,
            prevLogIndex = (entryLog.size - 2),
            prevLogTerm = entryLog.last().term,
            leaderCommit = 0
        )
        return tmp
    }

    private fun mapLongUrl2Short(longUrl: String): String {
        var shortUrl: String;
        var seed = 0
        do {
            seed += 1
            shortUrl = md5(longUrl, seed.toByte());
        } while (data.containsKey(shortUrl))
        return shortUrl
    }

    private fun md5(input: String, seed: Byte): String {
        // hash input string and map it into a short string
        // val encodedInt = BigInteger(1, hasher.digest(input.toByteArray()))
        val encodedInt = BigInteger(1, hasher.digest(input.toByteArray() + seed))
        return encodedInt.toString(36).takeLast(10);
    }

    final var data: MutableMap<String, String> = mutableMapOf()
        private set // the setter is private and has the default implementation

    private val hasher = MessageDigest.getInstance("MD5")
}
