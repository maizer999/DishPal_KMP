package feature_post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.DefaultButton
import core.ImageBanner
import core.StarRatingBar
import core.UserAvatar
import dishpal.composeapp.generated.resources.Res
import dishpal.composeapp.generated.resources.add_to_dishpals
import dishpal.composeapp.generated.resources.ask_to_swap
import dishpal.composeapp.generated.resources.description
import dishpal.composeapp.generated.resources.done_by
import dishpal.composeapp.generated.resources.unknown_user
import feature_home.HomeViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterialApi::class, ExperimentalResourceApi::class)
@Composable
fun PostScreen(modifier: Modifier = Modifier, id: Long,
               viewModel: PostViewModel = koinInject()) {
    val state = viewModel.state.collectAsState()
    val pullRefreshState =
        rememberPullRefreshState(state.value.isLoading, { viewModel.refresh(id) })

     if (state.value.post == null && !state.value.isLoading) {
         viewModel.refresh(id)
     }

    Box(
        modifier = modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
    ) {
        if (!state.value.isLoading) {
            state.value.post?.let {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(270.dp)
                    ) {

                        ImageBanner(
                            modifier = Modifier.fillMaxSize(),
                            imageUrl = state.value.post?.dishImageUrl?.first()
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                modifier = Modifier
                                    .padding(10.dp),
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    color = Color.White
                                ),
                                text = it.dishName,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                    Text(
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        text = stringResource(Res.string.done_by),
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        UserAvatar(
                            size = 50.dp,
                            avatarUrl = it.createdBy?.userAvatarUrl,
                            initials = it.createdBy?.userInitials ?: ""
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black
                                ),
                                text = it.createdBy?.fullName ?: stringResource(Res.string.unknown_user),
                                textAlign = TextAlign.Start
                            )
                            StarRatingBar(
                                modifier = Modifier.padding(start = 5.dp),
                                starSize = 20.dp,
                                elevation = 0.dp,
                                rating = it.createdBy?.rate ?: 0f
                            )
                        }
                    }
                    Text(
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        text = stringResource(Res.string.description),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        text = it.dishDescription,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    if (it.isDishPal) {
                        DefaultButton(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onClick = {

                            },
                            text = stringResource(
                                Res.string.ask_to_swap,
                                it.createdBy?.fullName ?: ""
                            )
                        )
                    } else {
                        DefaultButton(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onClick = {

                            },
                            text = stringResource(
                                Res.string.add_to_dishpals,
                                it.createdBy?.fullName ?: ""
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
        PullRefreshIndicator(
            refreshing = viewModel.state.value.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}