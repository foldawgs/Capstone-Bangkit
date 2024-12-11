package com.dicoding.cinemood

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.cinemood.api.ApiClient
import com.dicoding.cinemood.model.RequestModel
import com.dicoding.cinemood.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etMood = findViewById<EditText>(R.id.etMood)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar) // Tambahkan ProgressBar

        btnSearch.setOnClickListener {
            val moodText = etMood.text.toString().trim()
            if (moodText.isEmpty()) {
                Toast.makeText(this, "Masukkan mood Anda", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            fetchRecommendations(moodText, progressBar)
        }
    }

    private fun fetchRecommendations(mood: String, progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE // Tampilkan loading indicator

        val request = RequestModel(text = mood)

        ApiClient.instance.getRecommendations(request).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                progressBar.visibility = View.GONE // Sembunyikan loading indicator
                if (response.isSuccessful) {
                    val responseData = response.body()
                    val recommendations = responseData?.recommendations

                    val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                    intent.putParcelableArrayListExtra(
                        "recommendations",
                        ArrayList(recommendations)
                    )
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Gagal mendapatkan rekomendasi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                progressBar.visibility = View.GONE // Sembunyikan loading indicator
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
