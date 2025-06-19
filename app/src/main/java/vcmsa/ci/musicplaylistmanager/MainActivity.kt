package vcmsa.ci.musicplaylistmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Comment

class MainActivity : AppCompatActivity() {
    // Declare parallel arrays to store packing list information
    private val SongTitle = ArrayList<String>()
    private val ArtistName = ArrayList<String>()
    private val Rating = ArrayList<Int>()
    private val Comment = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val songInput = findViewById<EditText>(R.id.editTheSongTitle)
        val artistInput = findViewById<EditText>(R.id.editTheArtistName)
        val ratingInput = findViewById<EditText>(R.id.editTheRating)
        val commentInput = findViewById<EditText>(R.id.editComment)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnViewList = findViewById<Button>(R.id.btnViewList)
        val btnExit = findViewById<Button>(R.id.btnExit)

        // Handle Add button click
        btnAdd.setOnClickListener {
            val song = songInput.text.toString()
            val artist = artistInput.text.toString()
            val ratingText = ratingInput.text.toString()
            val comment = commentInput.text.toString()

            // Validate inputs
            if (song.isBlank() || artist.isBlank() || ratingText.isBlank()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val rating = ratingText.toIntOrNull()
            if (rating == null || rating < 1) {
                Toast.makeText(this, "Enter a valid rating (1 to 5)", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            // Add data to arrays
            SongTitle.add(song)
            ArtistName.add(artist)
            Rating.add(rating)
            Comment.add(comment)

            // Show confirmation
            Toast.makeText(this, "Song added to packing list", Toast.LENGTH_SHORT).show()

            // Clear input fields
            songInput.text.clear()
            artistInput.text.clear()
            ratingInput.text.clear()

            // View list button navigate to second screen
            btnViewList.setOnClickListener {
                val intent = Intent(this, SongListActivity::class.java)
                // Send arrays to next screen
                intent.putStringArrayListExtra("SongTitle", SongTitle)
                intent.putStringArrayListExtra("ArtistName", ArtistName)
                intent.putIntegerArrayListExtra("Rating", ArrayList(Rating))
                intent.putStringArrayListExtra("Comment", Comment)
                startActivity(intent)
            }

            // Exit button
            btnExit.setOnClickListener {
                finish()
            }
        }
    }
}