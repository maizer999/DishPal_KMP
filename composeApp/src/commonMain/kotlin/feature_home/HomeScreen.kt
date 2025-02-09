package feature_home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import designsystem.White300
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import dishpal.composeapp.generated.resources.Res
import dishpal.composeapp.generated.resources.group
import dishpal.composeapp.generated.resources.location
import dishpal.composeapp.generated.resources.set_location
import dishpal.composeapp.generated.resources.your_group_of_friends
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import navigator.Screen
import platform.openLink
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun ColumnScope.HomeScreen(modifier: Modifier,
               viewModel: HomeViewModel = koinInject(),
               onNavigate: (Screen) -> Unit
) {
    val permissionFactory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val permissionController: PermissionsController = remember(permissionFactory) {
        permissionFactory.createPermissionsController()
    }
    BindEffect(permissionController)
    val state = viewModel.state.collectAsState()
    val pullRefreshState = rememberPullRefreshState(state.value.isLoading, { viewModel.getAllPosts() })

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState,
        modifier = modifier.weight(1f)) {
        Box(
            modifier = modifier
                .pullRefresh(pullRefreshState)
                .fillMaxSize()
        ) {
            state.value.error?.let {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Error: ${it.message}",
                        actionLabel = "Ok"
                    )
                }
            }

            if (state.value.isLoading) return@Box
            Column(modifier = modifier.background(color = White300)) {
                LazyColumn(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {

                    item {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ActionCard(
                                modifier = Modifier.weight(1f),
                                drawable = Res.drawable.location,
                                string = Res.string.set_location, onClick = {
                                    viewModel.getLocation(permissionController)
                                })
                            ActionCard(
                                modifier = Modifier.weight(1f),
                                drawable = Res.drawable.group,
                                string = Res.string.your_group_of_friends,
                                onClick = {
                                    onNavigate(Screen.Onboard1)
                                })
                        }
                    }

                    state.value.posts.forEach { post ->
                        item {
                            PostCard(
                                userAvatarUrl = null,
                                rate = post.rate,
                                rateCount = post.rateCount,
                                userInitials = post.createdBy?.userInitials ?: "",
                                dishImageUrl = post.dishImageUrl?.firstOrNull(),
                                dishName = post.dishName,
                                dishType = post.dishType,
                                dishDescription = post.dishDescription,
                                id = post.id,
                                onClick = { id -> onNavigate(Screen.Post(id)) },
                                onLikeClicked = { _ ->
                                    viewModel.openLink()
                                },
                                onCommentClicked = { _ ->

                                },
                                onShareClicked = { _ ->

                                }
                            )
                        }
                    }
                }
            }
            PullRefreshIndicator(
                state.value.isLoading,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}