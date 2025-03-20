package com.example.minggu_3

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.minggu_3.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private val scoreViewModel: ScoreViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Observasi skor Tim A
        scoreViewModel.scoreA.observe(this) { newScoreA ->
            binding.tvScoreA.text = newScoreA.toString()
        }

        // Observasi skor Tim B
        scoreViewModel.scoreB.observe(this) { newScoreB ->
            binding.tvScoreB.text = newScoreB.toString()
        }

        // Observasi status pemenang
        scoreViewModel.winner.observe(this) { winnerText ->
            if (winnerText != null) {
                binding.tvWinner.text = winnerText // Menampilkan teks pemenang
            } else {
                binding.tvWinner.text = "" // Menghapus teks jika permainan di-reset
            }
        }

        // Event Listener untuk tombol Team A
        binding.btnPlus1A.setOnClickListener {
            scoreViewModel.incrementScoreA()
        }

        binding.btnPlus2A.setOnClickListener {
            repeat(2) { scoreViewModel.incrementScoreA() }
        }

        // Event Listener untuk tombol Team B
        binding.btnPlus1B.setOnClickListener {
            scoreViewModel.incrementScoreB()
        }

        binding.btnPlus2B.setOnClickListener {
            repeat(2) { scoreViewModel.incrementScoreB() }
        }

        // Tombol Reset
        binding.btnReset.setOnClickListener {
            scoreViewModel.resetScore()
        }

        // Mengatur padding sesuai dengan insets sistem
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = ColorDatabase.getInstance(this)
        val executor = Executors.newSingleThreadExecutor() // Buat background thread

        executor.execute {
            val colorRed = Color(hexColor = "#ff0000", name = "Red") // Jangan isi ID
            db.ColorDao().insert(colorRed)

            // Cek apakah data masuk
            val colors = db.ColorDao().getAll()
            Log.d("Database", "Colors in DB: $colors")
        }
    }
}
