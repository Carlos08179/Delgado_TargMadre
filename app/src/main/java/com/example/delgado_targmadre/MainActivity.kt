package com.example.delgado_targmadre

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var heartImage: ImageView
    private lateinit var btnSurprise: Button
    private lateinit var backgroundLayout: ConstraintLayout
    private lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        heartImage = findViewById(R.id.heartImage)
        btnSurprise = findViewById(R.id.btnSurprise)
        backgroundLayout = findViewById(R.id.rootLayout)
        titleTextView = findViewById(R.id.titleTextView)

        // Animación de latido del corazón
        animateHeart()

        // Configurar el botón de sorpresa
        btnSurprise.setOnClickListener {
            showSurprise()
        }

        // Animación de fondo
        val animationDrawable = backgroundLayout.background as? AnimationDrawable
        animationDrawable?.apply {
            setEnterFadeDuration(2000)
            setExitFadeDuration(4000)
            start()
        }
    }

    private fun animateHeart() {
        val scaleX = ObjectAnimator.ofFloat(heartImage, View.SCALE_X, 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(heartImage, View.SCALE_Y, 1f, 1.2f, 1f)

        scaleX.duration = 1000
        scaleY.duration = 1000
        scaleX.repeatCount = ObjectAnimator.INFINITE
        scaleY.repeatCount = ObjectAnimator.INFINITE

        scaleX.start()
        scaleY.start()
    }

    private fun showSurprise() {
        // Animación para el título
        val titleAnimator = ObjectAnimator.ofFloat(titleTextView, View.TRANSLATION_Y, 0f, -50f, 0f)
        titleAnimator.duration = 1000
        titleAnimator.interpolator = BounceInterpolator()
        titleAnimator.start()

        // Cambiar colores del fondo
        val colorAnimator = ObjectAnimator.ofArgb(
            backgroundLayout,
            "backgroundColor",
            ContextCompat.getColor(this, R.color.colorPrimary),
            ContextCompat.getColor(this, R.color.colorAccent),
            ContextCompat.getColor(this, R.color.colorPrimaryDark)
        )
        colorAnimator.duration = 2000
        colorAnimator.start()

        // Mostrar mensaje especial
        Toast.makeText(this, "¡Te quiero mucho, mamá!", Toast.LENGTH_SHORT).show()

        // Animación de confeti
        val confettiAnimator = ObjectAnimator.ofFloat(heartImage, View.ROTATION, 0f, 360f)
        confettiAnimator.duration = 1500
        confettiAnimator.interpolator = AccelerateInterpolator()
        confettiAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                heartImage.rotation = 0f
            }
        })
        confettiAnimator.start()
    }
}