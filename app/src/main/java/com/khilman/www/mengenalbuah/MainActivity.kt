package com.khilman.www.mengenalbuah

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Buat adapter
        val adapter = ListViewAdapter(this)
        // Set Adapter ke ListView
        listBuah.adapter = adapter
        // Beri Event onSelected
        listBuah.setOnItemClickListener { adapterView, view, posisi, l ->
            // Perintah jika item di klik
            Toast.makeText(this@MainActivity, "Item ke $posisi dipilih",
                    Toast.LENGTH_SHORT).show()
            // dapatkan suara buah
            val suara_buah: Int = ListViewAdapter(this@MainActivity).dataSuaraBuah.get(posisi)
            val namabuah: String = ListViewAdapter(this@MainActivity).dataNamaBuah.get(posisi)
            val gambarbuah: Int = ListViewAdapter(this@MainActivity).dataGambarBuah.get(posisi)

            // Jalankan method putarSuara
            putarSuara(suara_buah)
            // Pindah ke activity baru
            // Buat objek intent
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            // Sisipkan data ke Intent
            intent.putExtra("NM_BUAH", namabuah)
            intent.putExtra("GB_BUAH", gambarbuah)

            // Mulai activity bary
            startActivity(intent)


        }


    }

    private fun putarSuara(suara_buah: Int) {
        // TODO 1 : Parsing audio ke URI
        val uriAudio = Uri.parse("android.resource://$packageName/$suara_buah")
        // TODO 2 : Panggil Class yang menghandle Media Player
        val player = MediaPlayer()
        // TODO : 3
        player.setDataSource(this@MainActivity, uriAudio)
        player.prepare()
        // TODO 4 : Putar media player
        player.start()
    }
}
// Class adapter untuk list view
class ListViewAdapter(context: Context) : BaseAdapter() {

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        // buat variable penampung Class View
        val viewItem : View
        // isi Variable view dngan Layout item_buah.xml
        viewItem = this.mInflater.inflate(R.layout.item_buah, viewGroup, false)
        // Ambil data array sesuai Posisi Item
        val nama_buah = dataNamaBuah.get(position)
        val gambar_buah = dataGambarBuah.get(position)
        // tampilkan ke Log
        Log.d("Nama buah", nama_buah)
        val tvNamaBuah = viewItem.findViewById<TextView>(R.id.tvNamaBuah)
        val ivGambarBuah = viewItem.findViewById<ImageView>(R.id.ivGambarBuah)

        // set text ke widget text view
        tvNamaBuah.text = nama_buah
        // set source gambar ke imageView
        ivGambarBuah.setImageResource(gambar_buah)

        return viewItem

    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        // Menentukan jumlah item dalam list
        return dataNamaBuah.size
    }

    // Buat data nama buah dalam array
    val dataNamaBuah = arrayOf("Alpukat", "Apel",
            "Ceri", "Durian", "Jambu Air", "Manggis", "Strawberry")
    // Buat data audio buah
    val dataSuaraBuah = arrayOf(R.raw.alpukat, R.raw.apel,
                                R.raw.ceri, R.raw.durian, R.raw.jambuair,
                                R.raw.manggis, R.raw.strawberry)
    // Buat data gambar buah
    val dataGambarBuah = arrayOf(R.drawable.alpukat, R.drawable.apel,
                                R.drawable.ceri, R.drawable.durian, R.drawable.jambuair,
                                R.drawable.manggis, R.drawable.strawberry)

    // Buat variable untuk menampung class Inflater
    val mInflater : LayoutInflater
    init {
        // inisialisasi/isi Variable mInflater dngan context
        this.mInflater = LayoutInflater.from(context)
    }

}
