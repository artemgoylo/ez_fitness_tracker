package com.example.super_mega_fitness_tracker.presentation.detalization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.super_mega_fitness_tracker.common.IncrementDirection
import com.example.super_mega_fitness_tracker.presentation.detalization.model.ExerciseReport
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun DetalizationScreen(viewModel: DetalizationViewModel = koinViewModel()) {
    val exercises = viewModel.exercises.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        CardsList(
            exercises.value,
            onSetChange = { dir, id -> viewModel.onSetChange(dir, id) },
            onRepChange = { dir, id -> viewModel.onRepChange(dir, id) },
            onNameChange = { value, id -> viewModel.onNameChange(value, id) },
            onWeightChange = { value, id -> viewModel.onWeightChange(value, id) },
        )
        Button(
            modifier = Modifier.height(120.dp).width(120.dp),
            onClick = { viewModel.onAddCard() }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add card button",
                modifier = Modifier.size(48.dp),
            )
        }
    }
}

@Composable
private fun CardsList(
    exercises: List<ExerciseReport>,
    onSetChange: (IncrementDirection, Int) -> Unit,
    onRepChange: (IncrementDirection, Int) -> Unit,
    onNameChange: (String, Int) -> Unit,
    onWeightChange: (String, Int) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        exercises.forEachIndexed { index, report ->
            ExerciseCard(
                exerciseName = report.name,
                setCount = report.setCount,
                repCount = report.repCount,
                cardId = index,
                weight = report.weight,
                modifier = Modifier.fillMaxWidth(),
                onSetChange = onSetChange,
                onRepChange = onRepChange,
                onNameChange = onNameChange,
                onWeightChange = onWeightChange,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetalizationScreen() {
    DetalizationScreen(DetalizationViewModel())
}
