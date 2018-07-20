package ru.ilapin.arcompass

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ru.ilapin.common.math.Vector4f

class CompassPresenter(rootView: View) {

    @BindView(R.id.error_message)
    lateinit var errorView: TextView

    @BindView(R.id.orientation_view)
    lateinit var orientationView: OrientationView

    init {
        ButterKnife.bind(this, rootView)

        errorView.visibility = View.GONE
        orientationView.visibility = View.GONE
    }

    fun showError(errorMessage: String) {
        errorView.visibility = View.VISIBLE
        errorView.text = errorMessage

        orientationView.visibility = View.GONE
    }

    fun showOrientation() {
        errorView.visibility = View.GONE
        orientationView.visibility = View.VISIBLE
    }

    fun drawVector(vector: Vector4f) {
        System.arraycopy(vector.values, 0, orientationView.vector.values, 0, vector.values.size)
        orientationView.invalidate()
    }
}