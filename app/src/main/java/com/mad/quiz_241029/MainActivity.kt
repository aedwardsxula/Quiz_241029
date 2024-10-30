package com.mad.quiz_241029

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mad.quiz_241029.ui.theme.Quiz_241029Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val participants = Participants(150)

        setContent {
            Column {
                // Log.d("XU","$participants")
                Text("There are ${participants.size} participants.")
                Spacer(Modifier.height(16.dp))

                Text("There are ${participants.ids.size} unique ids:\n${participants.ids}")
                Spacer(Modifier.height(16.dp))

                Text("Participants in October:\n${participants.idByCountForMonthImperative(10)}")
                Spacer(Modifier.height(16.dp))

                Text("Was everyone selected in October?\t${participants.wasEveryStudentSelectedInMonth(10)}")
                Spacer(Modifier.height(16.dp))

                Text("Next October participant has id ${participants.nextParticipant(10)}")
                Spacer(Modifier.height(16.dp))

                Text("October's star participant(s) are ${participants.starParticipants(10)}")
            }
        }
    }
}

