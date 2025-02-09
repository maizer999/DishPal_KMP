package feature_home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.ImageBanner
import core.StarRatingBar
import core.UserAvatar
import core.asImage
import dishpal.composeapp.generated.resources.Res
import dishpal.composeapp.generated.resources.comment
import dishpal.composeapp.generated.resources.like
import dishpal.composeapp.generated.resources.like_selected
import dishpal.composeapp.generated.resources.share
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class)
@Composable
fun PostCard(modifier: Modifier = Modifier,
             userAvatarUrl: String?,
             rate: Float?,
             rateCount: Int?,
             userInitials: String,
             dishImageUrl: String?,
             dishName: String,
             dishType: String?,
             dishDescription: String,
             topHeight: Dp = 270.dp,
             isLiked: Boolean = false,
             id: Long,
             onClick: (Long) -> Unit,
             onShareClicked: (Long) -> Unit,
             onLikeClicked: (Long) -> Unit,
             onCommentClicked: (Long) -> Unit
) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(0.dp),
        modifier = modifier.height(400.dp).clickable {
            onClick(id)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Box {
                ImageBanner(
                    modifier = Modifier.height(topHeight).clickable {
                        onClick(id)
                    },
                    imageUrl = dishImageUrl
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(topHeight)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        UserAvatar(avatarUrl = userAvatarUrl, initials = userInitials)
                        StarRatingBar(
                            modifier = Modifier.padding(start = 5.dp),
                            rating = rate ?: 0f
                        )
                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            style = TextStyle(color = Color.White),
                            text = "(${rateCount})"
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)) {
                        Text(
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            ),
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            text = dishName
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp
                            ),
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            text = dishType ?: ""
                        )
                    }
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1f),
                style = TextStyle(fontSize = 16.sp),
                text = dishDescription,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Res.drawable.share.asImage(
                    modifier = Modifier
                        .height(28.dp)
                        .clickable {
                            onShareClicked(id)
                        },
                    tint = ColorFilter.tint(color = MaterialTheme.colors.primary),
                )
                (if (isLiked) Res.drawable.like_selected else Res.drawable.like).asImage(
                    modifier = Modifier
                        .height(28.dp)
                        .clickable {
                            onLikeClicked(id)
                        },
                    tint = ColorFilter.tint(color = MaterialTheme.colors.primary),
                )
                Res.drawable.comment.asImage(
                    modifier = Modifier
                        .height(28.dp)
                        .clickable {
                            onCommentClicked(id)
                        },
                    tint = ColorFilter.tint(color = MaterialTheme.colors.primary),
                )
            }
        }
    }
}