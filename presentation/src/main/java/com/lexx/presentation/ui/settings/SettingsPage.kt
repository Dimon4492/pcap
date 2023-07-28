package com.lexx.presentation.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexx.presentation.R

@Composable
fun SettingsPage(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val settingsUiState by settingsViewModel.uiState.collectAsState()

    val fullUri = with(settingsUiState) {
        "smb://${username}:${password}@${serverAddress}/${shareName}/${outputDirectory}"
    }

    val rowModifier = Modifier.padding(6.dp)

    Column(modifier = modifier.padding(12.dp)) {
        Row(rowModifier) {
            Text(text = stringResource(id = R.string.output_dir_uri_label))
            SelectionContainer {
                Text(text = fullUri)
            }
        }

        Row(rowModifier) {
            Text(text = stringResource(id = R.string.server_address_label))
            TextField(
                value = settingsUiState.serverAddress,
                onValueChange = {
                    settingsViewModel.setServerAddress(it)
                }
            )
        }

        Row(rowModifier) {
            Text(text = stringResource(id = R.string.username_label))
            TextField(
                value = settingsUiState.username,
                onValueChange = {
                    settingsViewModel.setUsername(it)
                }
            )
        }

        Row(rowModifier) {
            Text(text = stringResource(id = R.string.password_label))
            TextField(
                value = settingsUiState.password,
                onValueChange = {
                    settingsViewModel.setPassword(it)
                }
            )
        }

        Row(rowModifier) {
            Text(text = stringResource(id = R.string.share_name_label))
            TextField(
                value = settingsUiState.shareName,
                onValueChange = {
                    settingsViewModel.setShareName(it)
                }
            )
        }

        Row(rowModifier) {
            Text(text = stringResource(id = R.string.output_directory_label))
            TextField(
                value = settingsUiState.outputDirectory,
                onValueChange = {
                    settingsViewModel.setOutputDirectory(it)
                }
            )
        }

        Row(rowModifier) {
            Text(text = stringResource(id = R.string.filename_suffix_label))
            TextField(
                value = settingsUiState.filenameSuffix,
                onValueChange = {
                    settingsViewModel.setFilenameSuffix(it)
                }
            )
        }
    }
}
