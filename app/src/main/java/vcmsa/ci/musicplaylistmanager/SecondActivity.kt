package vcmsa.ci.musicplaylistmanager

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        // Rertieve arrays from intent
        val songTitle = intent.getStringArrayListExtra("SongTitle")?: arrayListOf()
        val rating = intent.getIntegerArrayListExtra("Rating")?: arrayListOf()
        val comment = intent.getStringArrayListExtra("Comment")?: arrayListOf()

        val displayList = findViewById<TextView>(R.id.txtSongList)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Build display text only for items with rating >= 2
        val result = StringBuilder()
        for (i in songTitle.indices) {
            if (rating[i] >= 1) {
                result.append("Song Title: ${songTitle[i]}, Rating: ${rating[i]}, Comment: ${comment[i]}\n")
            }
        }
        // Set result to the text view
        displayList.text = if (result.isNotEmpty()) result.toString() else "No items with quantity >= 1"

        // Handle return to main screen
        btnBack.setOnClickListener {
            finish()
        }
    }
}