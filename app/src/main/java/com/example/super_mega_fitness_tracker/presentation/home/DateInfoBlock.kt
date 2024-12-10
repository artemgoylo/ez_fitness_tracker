package com.example.super_mega_fitness_tracker.presentation.home

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.super_mega_fitness_tracker.R
import com.example.super_mega_fitness_tracker.presentation.detalization.model.ExerciseReport
import com.example.super_mega_fitness_tracker.presentation.ui.theme.SupermegafitnesstrackerTheme

private const val NAME_WIDTH = 132
private const val SET_WIDTH = 56
private const val REP_WIDTH = 56
private const val WEIGHT_WIDTH = 56
private const val SPACE = 16

@Composable
fun DateInfoBlock(reports: List<ExerciseReport>, modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        TableRow(
            exerciseName = stringResource(R.string.exercise_name_title),
            sets = stringResource(R.string.sets_title),
            reps = stringResource(R.string.reps_title),
            weight = stringResource(R.string.weight_title),
        )
        ExerciseReportInfoCardsList(exercises = reports)
    }
}

@Composable
private fun TableRow(
    exerciseName: String,
    sets: String,
    reps: String,
    weight: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(SPACE.dp),
    ) {
        Text(exerciseName, modifier = Modifier
            .width(NAME_WIDTH.dp)
            .basicMarquee(), maxLines = 1)
        Text(sets, modifier = Modifier.width(SET_WIDTH.dp), textAlign = TextAlign.Center)
        Text(reps, modifier = Modifier.width(REP_WIDTH.dp), textAlign = TextAlign.Center)
        Text(weight, modifier = Modifier.width(WEIGHT_WIDTH.dp), textAlign = TextAlign.Center)
    }
}

@Composable
private fun ExerciseReportInfoCardsList(modifier: Modifier = Modifier, exercises: List<ExerciseReport>) {
    Column(modifier = modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        exercises.forEach { report ->
            ExerciseReportInfoCard(
                exerciseName = report.name,
                setCount = report.setCount.toString(),
                repCount = report.repCount.toString(),
                weight = report.weight,
            )
        }
    }
}

@Composable
private fun ExerciseReportInfoCard(
    exerciseName: String,
    setCount: String,
    repCount: String,
    weight: String,
    modifier: Modifier = Modifier.Companion,
) {
    Card(modifier = modifier.fillMaxWidth()) {
        TableRow(
            exerciseName = exerciseName,
            sets = setCount,
            reps = repCount,
            weight = weight,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDateInfoBlock() {
    SupermegafitnesstrackerTheme {
        DateInfoBlock(
            reports = listOf(
                ExerciseReport(name = "Overhead Press", setCount = 3, repCount = 10, weight = "50"),
                ExerciseReport(name = "Bench Press", setCount = 3, repCount = 12, weight = "80"),
                ExerciseReport(name = "Deadlift", setCount = 4, repCount = 10, weight = "120"),
                ExerciseReport(name = "Lat Pull-Down", setCount = 4, repCount = 12, weight = "70"),
                ExerciseReport(name = "Exercise With A Very Very Very Long Name", setCount = 4, repCount = 5, weight = "100")
            )
        )
    }
}