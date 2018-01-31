package com.khilman.www.mengenalbuah

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.item_buah.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Tangkap data yang dikirim oleh int   ent
        val namabuah = intent.getStringExtra("NM_BUAH")
        // default value = 0, kalau datanya ngga dapet hasilnya nanti 0
        val gambarbuah = intent.getIntExtra("GB_BUAH", 0)

        // set ke widget
        ivGambarBuah.setImageResource(gambarbuah)
        tvNamaBuah.text = namabuah
    }
}
