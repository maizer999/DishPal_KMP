package feature_post

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.AsyncCarousel
import core.DefaultButton
import core.DefaultMultilineTextField
import core.DefaultTextField
import core.ImagePickerButton
import core.WeekDaysPicker
import dishpal.composeapp.generated.resources.Res
import dishpal.composeapp.generated.resources.add_your_meal
import dishpal.composeapp.generated.resources.arrow_back
import dishpal.composeapp.generated.resources.camera
import dishpal.composeapp.generated.resources.delete
import dishpal.composeapp.generated.resources.desc
import dishpal.composeapp.generated.resources.name
import dishpal.composeapp.generated.resources.post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import navigator.NavigatorViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NewPostScreen(modifier: Modifier = Modifier,
                  navigatorVM: NavigatorViewModel,
                  viewModel: NewPostViewModel = koinInject()) {

    val state = viewModel.uiState.collectAsState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
            ) {
                state.value.images.let {
                    AsyncCarousel(
                        modifier = Modifier.fillMaxSize(),
                        images = it,
                        onPageLoad = { index ->
                            viewModel.userEntry(state.value.copy(currentImageIndex = index))
                        }
                    )
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(width = 32.dp, height = 32.dp)
                                .clickable {
                                    navigatorVM.back()
                                },
                            painter = painterResource(Res.drawable.arrow_back),
                            colorFilter = ColorFilter.tint(color = Color.White),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            modifier = Modifier
                                .padding(10.dp),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            text = stringResource(Res.string.add_your_meal),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.weight(1.5f))
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    ImagePickerButton(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(width = 60.dp, height = 60.dp),
                        resource = Res.drawable.camera,
                        tintColor = Color.White,
                        onImageResult = {
                            viewModel.onImageAdd(it)
                        },
                        onCancel = {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Cancelled",
                                    actionLabel = "Do something"
                                )
                            }
                        },
                        onError = { _ ->
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Cancelled",
                                    actionLabel = "Do something"
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.weight(2f))
                    if (state.value.images.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(
                                onClick = {
                                    viewModel.removeImage()
                                }
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.delete),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.value.myPalsOnly,
                    onCheckedChange = {
                        viewModel.userEntry(state.value.copy(myPalsOnly = it))
                    },
                    enabled = true,
                    colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
                )
                Text(text = stringResource(Res.string.add_your_meal))
            }

            DefaultTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                text = state.value.name ?: "",
                placeHolder = Res.string.name,
                onValueChanged = { s -> viewModel.userEntry(state.value.copy(name = s)) }
            )

            DefaultMultilineTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                text = state.value.desc ?: "",
                placeHolder = Res.string.desc,
                onValueChanged = { viewModel.userEntry(state.value.copy(desc = it)) }
            )

            WeekDaysPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 12.dp, end = 12.dp),
                start = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
                onDaySelectionChange = { start, days ->
                    val list =
                        List(days.filter { it }.size) { index ->
                            start.plus(
                                index,
                                DateTimeUnit.DAY
                            )
                        }
                    viewModel.userEntry(state.value.copy(availableDates = list))
                }
            )

            DefaultButton(
                modifier = Modifier
                    .width(200.dp)
                    .padding(
                        top = 30.dp,
                        bottom = 30.dp
                    )
                    .align(Alignment.CenterHorizontally),
                isLoading = state.value.isLoading,
                onClick = viewModel::submit,
                text = stringResource(Res.string.post)
            )
        }
    }
}

