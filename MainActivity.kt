package com.alusch.quizzapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Methode um Statusbar zu verstecken (funktioniert noch nicht)
        hideStatusBar()

        //Zuerst: Angabe einer Aufforderung, wenn kein Name eingegeben wurde
        //Dann: Falls nun doch Name eingegeben, dann wird neue Activity aufgerufen (Intent=Absicht)
        // onClicklistener für Startbutton
        btn_start.setOnClickListener {
            // Prüft, ob Nutzer einen Namen eingegeben hat
            if (etName.text.toString().isEmpty()) {
                //Test-Meldung an den Nutzer
                Toast.makeText(this, "Bitte gib deinen Namen ein", Toast.LENGTH_LONG).show()
            }else {
                // Erstellung eines Intent (Absicht was aufgerufen werden soll) mit Context und aufzurufender Klasse
                val intent = Intent(this,quizquestionsActivity::class.java)
                //Speichert Nutzereingabe im intent, sodass dieser übergeben werden kann
                intent.putExtra(Constants.USER_NAME, etName.text.toString())
                // Start der Activity - Die Infos dazu befinden sich im Intent
                startActivity(intent)
                finish()
            }
        }
    }

        /*
      Methode fragt aktuelle Version von Android App. Ist diese kleiner als 30, wird
      die alte Methode für das Ausblenden der Statusbar benutzt. Ist die Version aktueller
      wird die neue Methode mit dem WindowInsetsController genutzt.
        */
    fun hideStatusBar() {
        if (Build.VERSION.SDK_INT < 30) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        } else {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}




