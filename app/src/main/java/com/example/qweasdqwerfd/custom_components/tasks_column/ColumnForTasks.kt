package com.example.qweasdqwerfd.custom_components.tasks_column

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.qweasdqwerfd.R
import com.example.qweasdqwerfd.api.models.columns.ColumnDataResponse

@Composable
fun ColumnForTasks(
    onClickDelete: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .padding(10.dp)


    ) {
        val (card, deleteButton) = createRefs()

        Card(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

            ,

            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {

        }
        IconButton(
            onClick = { onClickDelete() },
            modifier = Modifier
                .constrainAs(deleteButton) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    end.linkTo(card.end)
                }
                .padding(
                    end = 20.dp,
                    top = 60.dp,
                    start = 15.dp
                )
                .size(40.dp),

            ) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Red)
                    .padding(5.dp),
                painter = painterResource(R.drawable.trash),
                tint = Color.White,
                contentDescription = "delete"

            )
        }
    }

}