
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cargo_customer.presentation.screen.onboarding.view.OnBoardingPage
import com.example.cargo_customer.presentation.theme.CargoTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingPage(
    item: OnBoardingPage,
    currentPage: Int,
    pageIndex: Int,
    modifier: Modifier = Modifier
) {
    val isLastPage = pageIndex == 2
    var visible by remember { mutableStateOf(false) }
    val easing = FastOutSlowInEasing

    LaunchedEffect(currentPage) {
        visible = false

        if (currentPage == pageIndex) {
            delay(150)
            visible = true
        }
    }

    val imageAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = if (isLastPage) 1200 else 1000
        )
    )

    val imageScale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.8f,
        animationSpec = tween(
            durationMillis = if (isLastPage) 1200 else 1000,
            easing = easing
        )
    )

    val imageOffsetX by animateFloatAsState(
        targetValue = if (visible) {
            0f
        } else {
            if (isLastPage) -300f else 300f
        },
        animationSpec = tween(
            durationMillis = if (isLastPage) 1200 else 1000,
            easing = easing
        )
    )

    val titleAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = if (isLastPage) 1300 else 1000
        )
    )

    val titleOffset by animateFloatAsState(
        targetValue = if (visible) 0f else 40f,
        animationSpec = tween(
            durationMillis = if (isLastPage) 1300 else 1000,
            easing = easing
        )
    )

    val subTitleAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = if (isLastPage) 1500 else 1200
        )
    )

    val subTitleOffset by animateFloatAsState(
        targetValue = if (visible) 0f else 60f,
        animationSpec = tween(
            durationMillis = if (isLastPage) 1500 else 1200,
            easing = easing
        )
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(item.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size( 380.dp)
                .graphicsLayer {
                    alpha = imageAlpha
                    scaleX = imageScale
                    scaleY = imageScale
                    translationX = imageOffsetX
                }
        )

        Spacer(modifier = Modifier.height(CargoTheme.dimens.spacing.xl))

        Text(
            text = stringResource(item.title),
            color = CargoTheme.colorScheme.onBackground,
            style = CargoTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.graphicsLayer {
                alpha = titleAlpha
                translationY = titleOffset
            }
        )

        Spacer(modifier = Modifier.height(CargoTheme.dimens.spacing.sm))

        Text(
            text = stringResource(item.subtitle),
            color = CargoTheme.colorScheme.onSurfaceVariant,
            style = CargoTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.graphicsLayer {
                alpha = subTitleAlpha
                translationY = subTitleOffset
            }
        )
    }
}