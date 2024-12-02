package com.example.super_mega_fitness_tracker.presentation.detalization

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.super_mega_fitness_tracker.common.IncrementDirection
import com.example.super_mega_fitness_tracker.presentation.detalization.model.ExerciseReport
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel

@Destination(navArgsDelegate = DetalizationScreenNavArgs::class)
@Composable
fun DetalizationScreen(viewModel: DetalizationViewModel = koinViewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit) { Toast.makeText(context, "${viewModel.date}", Toast.LENGTH_SHORT).show() }

    val exercises = viewModel.exercises.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        CardsList(
            modifier = Modifier.weight(1f),
            exercises = exercises.value,
            onSetChange = { dir, id -> viewModel.onSetChange(dir, id) },
            onRepChange = { dir, id -> viewModel.onRepChange(dir, id) },
            onNameChange = { value, id -> viewModel.onNameChange(value, id) },
            onWeightChange = { value, id -> viewModel.onWeightChange(value, id) },
        )
        Row() {
            Button(
                modifier = Modifier.size(48.dp),
                onClick = { viewModel.onAddCard() },
                contentPadding = PaddingValues(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add card",
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Button(
                modifier = Modifier.size(48.dp),
                onClick = { viewModel.onSave() },
                contentPadding = PaddingValues(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Save cards",
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun CardsList(
    modifier: Modifier = Modifier,
    exercises: List<ExerciseReport>,
    onSetChange: (IncrementDirection, Int) -> Unit,
    onRepChange: (IncrementDirection, Int) -> Unit,
    onNameChange: (String, Int) -> Unit,
    onWeightChange: (String, Int) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        itemsIndexed(exercises) { index, report ->
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewDetalizationScreen() {
//    DetalizationScreen(DetalizationViewModel())
//}
