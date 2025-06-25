package com.example.qweasdqwerfd.custom_components.tasks_column

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.qweasdqwerfd.R
import com.example.qweasdqwerfd.api.models.tasks.TaskDtoResponse

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnForTasks(
    tasks: List<TaskDtoResponse>,
    selectedTasks: SnapshotStateList<Long>,
    onClickDelete: () -> Unit,
    onClickAdd: () -> Unit,
    onLongClickTask: (Long) -> Unit,
    onClickTask: (Long) -> Unit,
    isSelectionMode: Boolean
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        val (card, buttonsBox) = createRefs()

        Card(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 70.dp,
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(tasks) { task ->
                    val isSelected = selectedTasks.contains(task.id)
                    val interactionSource = remember { MutableInteractionSource() }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .combinedClickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = {
                                    if (isSelectionMode) {
                                        onClickTask(task.id) // обычное нажатие работает в режиме выделения
                                    }
                                },
                                onLongClick = {
                                    onLongClickTask(task.id) // первое зажатие
                                }
                            ),
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) Color.LightGray else Color.Gray
                        )
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                task.title ?: "Без названия",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                task.description ?: "Нет описания",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                }
            }
        }


        Box(
            modifier = Modifier
                .constrainAs(buttonsBox) {
                    top.linkTo(card.top)
                    end.linkTo(card.end)
                }
                .padding(top = 5.dp, end = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    onClick = { onClickAdd() },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = "add",
                        tint = Color.Black,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .padding(5.dp)
                    )
                }

                IconButton(
                    onClick = onClickDelete,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.trash),
                        contentDescription = "delete",
                        tint = Color.White,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Red)
                            .padding(5.dp)
                    )
                }
            }
        }


    }
}