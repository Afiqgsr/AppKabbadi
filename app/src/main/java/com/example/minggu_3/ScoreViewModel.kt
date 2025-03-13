package com.example.minggu_3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {
    private val _scoreA = MutableLiveData(0)
    val scoreA: LiveData<Int> get() = _scoreA

    private val _scoreB = MutableLiveData(0)
    val scoreB: LiveData<Int> get() = _scoreB

    private val _winner = MutableLiveData<String?>() // Menyimpan status pemenang
    val winner: LiveData<String?> get() = _winner

    // Fungsi untuk menambah skor Tim A
    fun incrementScoreA() {
        if (_winner.value == null) { // Jika belum ada pemenang
            _scoreA.value = (_scoreA.value ?: 0) + 1
            checkWinner() // Periksa apakah ada pemenang
        }
    }

    // Fungsi untuk menambah skor Tim B
    fun incrementScoreB() {
        if (_winner.value == null) { // Jika belum ada pemenang
            _scoreB.value = (_scoreB.value ?: 0) + 1
            checkWinner() // Periksa apakah ada pemenang
        }
    }

    // Mengecek apakah ada yang mencapai skor 25
    private fun checkWinner() {
        when {
            _scoreA.value == 25 -> _winner.value = "Tim A Menang!"
            _scoreB.value == 25 -> _winner.value = "Tim B Menang!"
        }
    }

    // Fungsi reset permainan
    fun resetScore() {
        _scoreA.value = 0
        _scoreB.value = 0
        _winner.value = null // Reset status pemenang
    }
}
