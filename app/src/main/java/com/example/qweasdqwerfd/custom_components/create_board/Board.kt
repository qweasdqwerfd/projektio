package com.example.qweasdqwerfd.custom_components.create_board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.qweasdqwerfd.R
import com.example.qweasdqwerfd.api.models.boards.response.BoardDataResponse

@Composable
fun Board(
    board: BoardDataResponse,
    onClickBoard: () -> Unit,
    onClickDelete: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier.padding(
            start = 10.dp,
            top = 20.dp,
            end = 10.dp
        )
    ) {
        val (card, deleteButton) = createRefs()
        Card(
            onClick = { onClickBoard() },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = board.boardName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                board.boardDescription?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
        IconButton(
            onClick = { onClickDelete() },
            modifier = Modifier
                .constrainAs(deleteButton) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    end.linkTo(card.end)
                }
                .padding(end = 5.dp)
                .size(32.5.dp),

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