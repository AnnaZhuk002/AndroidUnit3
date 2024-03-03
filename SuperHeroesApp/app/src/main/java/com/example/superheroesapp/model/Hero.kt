import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @StringRes val nameResource: Int,
    @DrawableRes val imageResource: Int,
    @StringRes val descriptionResource: Int
)
