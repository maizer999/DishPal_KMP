package core

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus


@Composable
fun WeekDaysPicker(
    modifier: Modifier,
    start: LocalDate,
    lineColor: Color = Color.Black,
    lineWidth: Dp = 1.dp,
    onDaySelectionChange: (LocalDate, List<Boolean>) -> Unit
) {
    val selectedDays = remember { MutableList(7, init = { false }) }
    Column (modifier = modifier) {
        Divider (
            color = lineColor,
            modifier = Modifier
                .height(lineWidth)
                .fillMaxWidth()
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            (0..6).forEach {
                item {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = start.plus(it, DateTimeUnit.DAY).dayOfWeek.name.substring(0,3),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                    )
                }
            }
        }

        Divider (
            color = lineColor,
            modifier = Modifier
                .height(lineWidth)
                .fillMaxWidth()
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            (0..6).forEach {
                val day = start.plus(it, DateTimeUnit.DAY)
                item {
                    CalendarCard(modifier = Modifier
                        .height(45.dp)
                        .width(45.dp),
                        textColor = Color.Black,
                        month = day.month.name.substring(0, 3),
                        dayOfMonth = day.dayOfMonth.toString(),
                        isSelected = selectedDays[it],
                        onClick = {
                            selectedDays[it] = !selectedDays[it]
                            onDaySelectionChange(start, selectedDays)
                        })
                }
            }
        }

    }

}

@Composable
fun CalendarCard(modifier: Modifier,
                 month: String,
                 dayOfMonth: String,
                 textColor: Color = Color.White,
                 backgroundColor: Color = MaterialTheme.colors.primary,
                 unSelectedColor: Color = MaterialTheme.colors.secondary,
                 isSelected: Boolean = false,
                 onClick: () -> Unit) {
    Card(
        elevation = 1.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .clickable {
                onClick()
            },
        backgroundColor = if (isSelected) backgroundColor else unSelectedColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 3.dp)
                    .align(Alignment.CenterHorizontally),
                text = month,
                style = TextStyle(
                    fontSize = 13.sp,
                    color = textColor
                )
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = dayOfMonth,
                style = TextStyle(
                    fontSize = 13.sp,
                    color = textColor
                )
            )
        }
    }
}