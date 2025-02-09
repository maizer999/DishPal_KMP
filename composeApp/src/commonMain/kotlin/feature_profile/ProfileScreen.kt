package feature_profile


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import core.DefaultButton
import core.DefaultTextField
import dishpal.composeapp.generated.resources.Res
import dishpal.composeapp.generated.resources.add_profile
import dishpal.composeapp.generated.resources.name
import dishpal.composeapp.generated.resources.next
import dishpal.composeapp.generated.resources.save
import navigator.NavigatorViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier,
                  navigatorViewModel: NavigatorViewModel,
                  profileViewModel: ProfileViewModel = koinInject()
) {
    val showDialog = remember { mutableStateOf(false) }

        Column(modifier = modifier.fillMaxSize()) {
            if (showDialog.value) {
                UserRegisterDialog(
                    { name ->
                        // save the user name
                        profileViewModel.saveUserName(name = name)
                        navigatorViewModel.loadUser()
                        showDialog.value = false
                    },
                    {

                    }
                )
            }
            DefaultButton(
                modifier = Modifier
                    .width(200.dp)
                    .padding(
                        top = 30.dp,
                        bottom = 30.dp
                    )
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    showDialog.value = true
                },
                text = stringResource(Res.string.add_profile)
            )

    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun UserRegisterDialog(
    onSaveClicked: (String) -> Unit,
    onDismissRequest: () -> Unit) {

    val inputvalue = remember { mutableStateOf(String()) }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                DefaultTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    text = inputvalue.value,
                    placeHolder = Res.string.name,
                    onValueChanged = { inputvalue.value = it }
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultButton(
                    modifier = Modifier
                        .width(150.dp)
                        .padding(
                            top = 30.dp,
                            bottom = 30.dp
                        )
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        onSaveClicked(inputvalue.value)
                    },
                    text = stringResource(Res.string.save)
                )
            }
        }
    }
}