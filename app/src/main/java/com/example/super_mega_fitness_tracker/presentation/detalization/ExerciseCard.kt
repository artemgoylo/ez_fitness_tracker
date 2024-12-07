package com.example.super_mega_fitness_tracker.presentation.detalization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.super_mega_fitness_tracker.common.IncrementDirection
import com.example.super_mega_fitness_tracker.presentation.ui.theme.SupermegafitnesstrackerTheme

@Composable
fun ExerciseCard(
    exerciseName: String,
    setCount: Int,
    repCount: Int,
    cardId: Int,
    weight: String,
    modifier: Modifier = Modifier.Companion,
    onSetChange: (IncrementDirection, Int) -> Unit,
    onRepChange: (IncrementDirection, Int) -> Unit,
    onNameChange: (String, Int) -> Unit,
    onWeightChange: (String, Int) -> Unit,
    onDeleteCard: (Int) -> Unit,
) {
    Card(modifier = modifier) {
        Column {
            IconButton(onClick = { onDeleteCard(cardId) }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete card",
                    modifier = Modifier.Companion.size(24.dp),
                )
            }
            TextField(
                placeholder = { Text("Enter exercise name") },
                label = { Text("Exercise Name") },
                singleLine = true,
                value = exerciseName,
//                fontSize = 48.sp,
                modifier = Modifier.Companion.fillMaxWidth(),
//                textAlign = TextAlign.Center,
                onValueChange = { value: String -> onNameChange(value, cardId) }
            )
            HorizontalDivider()
            WeightField(
                weight = weight.toString(),
                cardId = cardId,
                onWeightChange = onWeightChange
            )
            HorizontalDivider()
            Row {
                Counter(
                    "Sets",
                    setCount,
                    cardId,
                    modifier = Modifier.Companion.weight(1f),
                    onChange = onSetChange
                )
                Counter(
                    "Reps",
                    repCount,
                    cardId,
                    modifier = Modifier.Companion.weight(1f),
                    onChange = onRepChange
                )
            }
        }
    }
}

@Composable
fun Counter(
    name: String,
    count: Int,
    cardId: Int,
    modifier: Modifier = Modifier.Companion,
    onChange: (IncrementDirection, Int) -> Unit
) {
    Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.Companion.CenterHorizontally) {
        Text(name, fontSize = 24.sp)
        Row(
            modifier = Modifier.Companion.width(120.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            IconButton(onClick = { onChange(IncrementDirection.LEFT, cardId) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back arrow",
                    modifier = Modifier.Companion.size(24.dp),
                )
            }
            Text(count.toString(), fontSize = 24.sp)
            IconButton(onClick = { onChange(IncrementDirection.RIGHT, cardId) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Forward arrow",
                    modifier = Modifier.Companion.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun WeightField(
    weight: String,
    cardId: Int,
    onWeightChange: (String, Int) -> Unit,
) {
    TextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        placeholder = { Text("0") },
        label = { Text("Weight") },
        value = weight,
        onValueChange = { value: String -> onWeightChange(value, cardId) },
        modifier = Modifier.Companion.fillMaxWidth(),
        suffix = { Text(" kg") },
    )
}

@Preview
@Composable
fun PreviewExerciseCard() {
    SupermegafitnesstrackerTheme {
        ExerciseCard(
            exerciseName = "Test",
            setCount = 1,
            repCount = 2,
            cardId = 0,
            weight = "",
            modifier = Modifier,
            onSetChange = { _, _ -> },
            onRepChange = { _, _ -> },
            onNameChange = { _, _ -> },
            onWeightChange = { _, _ -> },
            onDeleteCard = { _ -> }
        )
    }
}