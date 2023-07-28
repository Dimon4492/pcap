package com.lexx.presentation.ui.preview

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import java.io.File

@Composable
fun PreviewPage(
    modifier: Modifier = Modifier,
    previewPageViewModel: PreviewPageViewModel = viewModel(),
) {
    val uiState = previewPageViewModel.uiState.collectAsState().value
    LazyColumn {
        if (uiState.lastFilename.isNotEmpty()) {
            item {
                val path = Uri.parse(uiState.lastFilename).path
                val jpg = File(path)
                Image(
                    painter = rememberAsyncImagePainter(jpg),
                    contentDescription = null,
                    modifier = Modifier.height(200.dp)
                )
            }
        }

        items(
            items = uiState.logReports,
            key = null
        ) {
            Text(text = it)
        }
    }
}
