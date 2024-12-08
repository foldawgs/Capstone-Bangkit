package com.dicoding.cinemood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        btnSearch.setOnClickListener {
            val moodText = etMood.text.toString().trim()
            if (moodText.isEmpty()) {
                Toast.makeText(this, "Masukkan mood Anda", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            fetchRecommendations(moodText)
        }
    }

    private fun fetchRecommendations(mood: String) {
        val request = RequestModel(text = mood)

        ApiClient.instance.getRecommendations(request).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
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
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
