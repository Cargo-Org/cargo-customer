package com.example.cargo_customer.presentation.screen.upload_docs.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.continue_button
import com.example.cargo_customer.presentation.base.ObserveAsEffect
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.mediapicker.rememberMediaPickerLauncher
import com.example.cargo_customer.presentation.screen.upload_docs.view.component.ConnectedFluidStepper
import com.example.cargo_customer.presentation.screen.upload_docs.viewmodel.UploadDocumentEffect
import com.example.cargo_customer.presentation.screen.upload_docs.viewmodel.UploadDocumentViewModel
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun UploadDocumentsScreen(
    viewModel: UploadDocumentViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val mediaPickerLauncher = rememberMediaPickerLauncher(
        onFilePicked = { file ->

            file ?: return@rememberMediaPickerLauncher

            viewModel.onImageSelected(
                bytes = file.bytes,
                fileName = file.name,
                contentType = file.mimeType
            )
        }
    )

    ObserveAsEffect(viewModel.effect){effect->
        when(effect){
            is UploadDocumentEffect.OpenImagePicker -> mediaPickerLauncher.launchFilePicker()
            is UploadDocumentEffect.NavigateToNextStep -> {}
            is UploadDocumentEffect.NavigateToPendingScreen -> {}
        }
    }

    UploadScreenContent(
        onContinueClick = viewModel::onNextStepClick,
        onUploadClick = viewModel::onPickDocumentClick
    )
}

@Composable
private fun UploadScreenContent(
    onContinueClick: () -> Unit,
    onUploadClick: () -> Unit

) {
    val stepCount = 3
    var stepIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = CargoTheme.colorScheme.background, floatingActionButton = {
            ColoredActionButton(
                modifier = Modifier.padding(horizontal = CargoTheme.dimens.spacing.sm),
                text = stringResource(Res.string.continue_button),
                onClick = {
                    stepIndex = ++stepIndex % stepCount
                })
        }, floatingActionButtonPosition = FabPosition.Center

    ) { paddingValues ->
        val currentTop = paddingValues.calculateTopPadding()
        val currentBottom = paddingValues.calculateBottomPadding()
        val screenPadding = PaddingValues(
            top = currentTop,
            bottom = currentBottom,
            start = CargoTheme.dimens.spacing.lg,
            end = CargoTheme.dimens.spacing.lg
        )

        Column(
            modifier = Modifier.padding(screenPadding).fillMaxWidth(),
        ) {

            Spacer(Modifier.height(CargoTheme.dimens.spacing.lg))

            ConnectedFluidStepper(
                currentIndex = stepIndex,
                stepCount = stepCount,
                modifier = Modifier.fillMaxWidth().height(CargoTheme.dimens.spacing.sm)
            )

            when (stepIndex) {
                0 -> UploadScreenFirstStep(stepIndex, {}, onUploadClick, onUploadClick)
                1 -> UploadScreenSecondStep()
                else -> UploadScreenFirstStep(stepIndex, {}, onUploadClick, onUploadClick)
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UploadDocumentsScreenPreview() {
    UploadDocumentsScreen()
}