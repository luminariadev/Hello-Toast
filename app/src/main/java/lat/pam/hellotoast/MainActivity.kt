package lat.pam.hellotoast

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var mCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mShowCount = findViewById<TextView>(R.id.show_count)
        val buttonCountUp = findViewById<Button>(R.id.button_count)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button>(R.id.button_switchpage)
        val buttonBrowser = findViewById<Button>(R.id.button_browser)
        val buttonContact = findViewById<Button>(R.id.button_contact)
        val buttonMap = findViewById<Button>(R.id.button_map)
        val buttonDial = findViewById<Button>(R.id.button_dial)

        // Tombol Count
        buttonCountUp.setOnClickListener {
            mCount++
            mShowCount.text = mCount.toString()
        }

        // Tombol Toast
        buttonToast.setOnClickListener {
            val tulisan = mShowCount.text.toString()
            Toast.makeText(this, "Angka yang dimunculkan $tulisan", Toast.LENGTH_LONG).show()
        }

        // Tombol Pindah Activity
        buttonSwitchPage.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // Tombol Browser
        buttonBrowser.setOnClickListener {
            val intentBrowse = Intent(Intent.ACTION_VIEW)
            intentBrowse.data = Uri.parse("https://www.google.com/")
            startActivity(intentBrowse)
        }

        // Tombol Contact (buka aplikasi Kontak)
        buttonContact.setOnClickListener {
            val intentContact = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            startActivity(intentContact)
        }

        // Tombol Map (buka Google Maps dengan lokasi contoh)
        buttonMap.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=Universitas Indonesia")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps") // pastikan pakai Google Maps
            startActivity(mapIntent)
        }

        // Tombol Dial (buka aplikasi Telepon dengan nomor contoh)
        buttonDial.setOnClickListener {
            val intentDial = Intent(Intent.ACTION_DIAL)
            intentDial.data = Uri.parse("tel:081234567890")
            startActivity(intentDial)
        }

        // Supaya tampilan menyesuaikan sistem bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
