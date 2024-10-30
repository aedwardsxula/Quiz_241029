package com.mad.quiz_241029

import android.util.Log
import kotlin.random.Random

data class Participant(val id: Int, val month: Int, val day: Int)

class Participants(numParticipants: Int = 10) {
    private val participants = buildRandomParticipants(numParticipants)
    val size = participants.size
    val ids = participants.map { it.id }.toSet().toList()

    fun buildRandomParticipantsImperative(howMany: Int) : List<Participant> {
        var participants: MutableList<Participant> = mutableListOf()
        for (i in 1..howMany) {
            participants.add(
                Participant(
                    id = (901..911).random(),
                    month = Random.nextInt(8, 12 + 1),
                    day = Random.nextInt(1, 30 + 1)
                )
            )
        }
        return participants
    }

    fun buildRandomParticipants(howMany: Int) : List<Participant> {
        return List(howMany) {
            Participant(
                id = (901..911).random(),
                month = Random.nextInt(8, 12 + 1),
                day = Random.nextInt(1, 30 + 1)
            )
        }
    }

    fun idByCountForMonthImperative(month: Int) : Map<Int, Int> {
        var idByCounts: MutableMap<Int, Int> = mutableMapOf()

        for (participant in participants) {
            if (participant.month == month) {
                idByCounts[participant.id] = idByCounts.getOrDefault(participant.id, 0) + 1
            }
        }
        return idByCounts
    }

    fun idByCountForMonth(month: Int) : Map<Int, Int> {
        return participants.filter { it.month == month}.groupingBy { it.id }.eachCount()

    }

    fun nextParticipant(month: Int): Int {
        if (wasEveryStudentSelectedInMonth(month)) {
            return -1
        }

        val idByCount = idByCountForMonth(10)
        return ids.toSet().minus(idByCount.filter { it.value >= 2 }.keys).random()
    }

    fun starParticipants(month: Int): List<Int> {
        val participatedTwiceInMonth = idByCountForMonth(month).filter { it.value >= 2 }
        val result = participants
            .groupBy { it.id }
            .mapValues { (_, participants) -> participants.map { it.day } }
            .mapValues { (k, v) -> k to v.toSet() }
            .filterValues { it.second.size >= 3 }
            .map { it.key}
            .sorted()
            .toList()

        return result
    }

    override fun toString(): String {
        return "Participants (${participants.size}):\n\t${participants.joinToString("\n\t") }})"
    }

    fun wasEveryStudentSelectedInMonth(month: Int): Boolean {
        return participants.filter { m -> m.month == month }.map{ it.id }.toSet().count() == ids.size
    }

}