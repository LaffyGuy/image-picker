package com.project.imagepicker.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.project.imagepicker.R
import com.project.imagepicker.ui.theme.ImagePickerTheme

@Composable
fun DetailsScreen(
    onClickToTag: (String) -> Unit,
    viewmodel: DetailsViewmodel,
) {

    val details by viewmodel.detailsUiState.collectAsStateWithLifecycle()

    DetailsContent(
        user = details.details.user,
        tags = details.details.tags,
        likes = details.details.likes,
        imageUrl = details.details.imageURL,
        clickToTag = onClickToTag
    )


}

@Composable
fun DetailsContent(
    user: String,
    tags: String,
    likes: Int,
    imageUrl: String,
    clickToTag: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.4f),
                            Color.Black.copy(alpha = 0.8f)
                        ),
                        startY = 600f
                    )
                )
        )

        BottomInfoSection(
            user = user,
            tags = tags,
            likes = likes,
            clickToTag = clickToTag,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}


@Composable
fun BottomInfoSection(
    user: String,
    tags: String,
    likes: Int,
    clickToTag: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val tagList = remember(tags) {
        tags.split(",").map { it.trim() }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tagList) { tag ->
                TagChip(
                    tag = tag,
                    modifier = Modifier.clickable {
                        clickToTag(tag)
                    }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = user,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = likes.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(R.drawable.ic_favorite),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(26.dp)
                )

            }

        }
    }
}


//@Composable
//fun DetailsContent(
//    user: String,
//    tags: String,
//    likes: Int,
//    imageUrl: String
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background)
//    ) {
//        AsyncImage(
//            model = imageUrl,
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(4f / 5f),
//            contentScale = ContentScale.Crop
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        val tagList = remember(tags) { tags.split(",").map { it.trim() } }
//
//        LazyRow(
//            modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(horizontal = 16.dp),
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(tagList) { tag ->
//                TagChip(tag = tag)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = user,
//                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Bold,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//
//            Surface(
//                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
//                shape = RoundedCornerShape(16.dp)
//            ) {
//                Row(
//                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(6.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.ic_favorite),
//                        contentDescription = null,
//                        tint = Color.Red,
//                        modifier = Modifier.size(20.dp)
//                    )
//                    Text(
//                        text = likes.toString(),
//                        style = MaterialTheme.typography.titleMedium,
//                        fontWeight = FontWeight.ExtraBold
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun TagChip(
    tag: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "#$tag",
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
            .background(
                color = Color.LightGray.copy(alpha = 0.8f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 4.dp, vertical = 4.dp)
    )
}

@Preview(showSystemUi = true)
@Composable
private fun DetailsContentPreview() {
    ImagePickerTheme {
        DetailsContent(
            user = "Hello",
            tags = "Nature, Photo, Sunset",
            likes = 121,
            imageUrl = "",
            clickToTag = {}
        )
    }
}