package com.rajeshdonepudi.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendButton = findViewById(R.id.sendButton) as Button
        val sentMessageEditText = findViewById(R.id.sentMessage) as EditText
        val displayMessageTextView = findViewById(R.id.receiveMessage) as TextView
        sendButton.setOnClickListener {

            val message: String = sentMessageEditText.getText().toString()

            val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
            val databaseReference: DatabaseReference = firebaseDatabase.getReference("message");
            databaseReference.setValue(message);


            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val value = dataSnapshot.getValue(String::class.java)
                    displayMessageTextView.setText(value)
                    Log.d("from database", "Value is $value")
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("error in database", error.toException())

                }
            })

        }


    }
}
