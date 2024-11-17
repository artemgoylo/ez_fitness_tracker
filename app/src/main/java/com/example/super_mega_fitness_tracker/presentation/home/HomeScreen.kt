package com.example.super_mega_fitness_tracker.presentation.home

import android.widget.CalendarView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.super_mega_fitness_tracker.presentation.destinations.DetalizationScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator?) {
    val viewModel: HomeViewModel = koinViewModel()
    val date = viewModel.date.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), // You can adjust the height as needed
            factory = { context ->
                CalendarView(context).apply {
                    // You can customize the calendar view here
                    // For example, setting the first day of the week
                    firstDayOfWeek = Calendar.MONDAY
                    // Or setting the minimum and maximum dates
                    // minDate = System.currentTimeMillis() - 1000
                    // maxDate = System.currentTimeMillis() + 1000 * 60 * 60 * 24
                    viewModel.onDateSelect(this.date)
                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                        // Handle date change here
                        //Toast.makeText(context, "Selected date: $year-$month-$dayOfMonth", Toast.LENGTH_SHORT).show()
                        viewModel.onDateSelect(year, month, dayOfMonth)
                    }
                }
            }
        )
        Button(
            onClick = {
                date.value?.let { navigator?.navigate(DetalizationScreenDestination(date = it)) }
              },
            modifier = Modifier.fillMaxWidth().height(40.dp)
        ) {
            Text("Boo", fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(null)
}
