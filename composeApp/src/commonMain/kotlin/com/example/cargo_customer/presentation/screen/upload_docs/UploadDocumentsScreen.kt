package com.example.cargo_customer.presentation.screen.upload_docs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Glasses
import com.composables.icons.lucide.Lightbulb
import com.composables.icons.lucide.Lucide
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.screen.upload_docs.component.ConnectedFluidStepper
import com.example.cargo_customer.presentation.screen.upload_docs.component.FaceDetectionCard
import com.example.cargo_customer.presentation.screen.upload_docs.component.TipRow
import com.example.cargo_customer.presentation.theme.CargoTheme


@Composable
fun UploadDocumentsScreen() {
    val stepCount = 3
    var stepIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = CargoTheme.colorScheme.background, floatingActionButton = {
            ColoredActionButton(
                modifier = Modifier.padding(horizontal = 8.dp), text = "Continue", onClick = {
                    stepIndex = ++stepIndex % stepCount
                })
        }, floatingActionButtonPosition = FabPosition.Center

    ) { paddingValues ->
        val currentTop = paddingValues.calculateTopPadding()
        val currentBottom = paddingValues.calculateBottomPadding()
        val screenPadding =
            PaddingValues(top = currentTop, bottom = currentBottom, start = 16.dp, end = 16.dp)

        UploadScreenContent(screenPadding, stepIndex, stepCount)
    }
}

@Composable
private fun UploadScreenContent(
    screenPadding: PaddingValues, currentStepIndex: Int, stepCount: Int
) {
    Column(
        modifier = Modifier.padding(screenPadding).fillMaxWidth(),
    ) {

        Spacer(Modifier.height(16.dp))

        ConnectedFluidStepper(
            currentIndex = currentStepIndex,
            stepCount = stepCount,
            modifier = Modifier.fillMaxWidth().height(8.dp)
        )

        when(currentStepIndex) {
            0 -> UploadScreenFirstStep(currentStepIndex) { nationalIDCardIndex -> }
            1 -> UploadScreenSecondStep()
            else -> UploadScreenFirstStep(currentStepIndex) { nationalIDCardIndex -> }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UploadDocumentsScreenPreview() {
    UploadDocumentsScreen()
}