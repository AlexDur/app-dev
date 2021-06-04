package com.alusch.quizzapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quizquestions.*

class quizquestionsActivity : AppCompatActivity(), View.OnClickListener {

    //m vor Variablen, um lokale von globalen Variaben zu unterscheiden
     var mUserName: String? = null
     var mCurrentPosition = 1
     var mQuestionList: ArrayList<Question>? = null
     var mSelectedOption: Int = 0
     var mCorrectAnswers: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizquestions)

        // Liest den extra String aus dem Intent aus und speichert diesen in der Variablen userName

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        // Aufruf der Funktion, um neue Frage zu laden
        setQuestion()

        //ONClLikListener für die vier Auswahloptionen
        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
        btnWeiter.setOnClickListener(this)

    }

    // Funktion um die aktuelle Frage zu laden
    private fun setQuestion() {

        // Abrufen der Frageliste --> unbedingt vor Abfrage in Zeile 51
        mQuestionList = Constants.getQuestions()

        // Zurücksetzen des Layouts der Fragenoptionen
        defaultOptionsView()

        // Wenn letzte Frage, dann bekommt Button den Text "beenden"
        if(mCurrentPosition == mQuestionList!!.size) {
            btnWeiter.text = "BEENDEN"
        //Ansonsten wird der Text auf Bestätigen zurückgesetzt
        } else {
            btnWeiter.text ="BESTÄTIGEN"
        }


        //Aktuelle Frage aus Fragenliste in Objekt der Datenklasse Question.
        // -1,weil wir einen array haben, bei dem man bei 0 anfängt zu zählen
        val question = mQuestionList!![mCurrentPosition - 1]
        //Progressbar Fortschritt zuweisen
        progressBar.progress = mCurrentPosition
        //Textfeld des Fortschrittes aktualisieren
        tvProgress.text = "$mCurrentPosition/" + progressBar.max

        //!! = es ist nicht Null
        //Daten aus aktueller Frage den UI-Elementen zuweisen
        tvQuestion.text = question!!.question
        tvFlag.setImageResource(question.image)
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
    }

        //Mit folgender Funktion werden die vier anklickbaren Optionen wieder auf Null gesetzt,
        private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)
        options.add(3, tvOptionFour)

        //ArrayList durchlaufen und jedes TextView anpassen
        for (option in options) {
            //Textfarbe auf Anfangswert
            option.setTextColor(Color.parseColor("#7A8089"))
            //Typeface auf Default (und nicht mehr Bold)
            option.typeface = Typeface.DEFAULT
            //Hintergrund auf default_option_border setzen
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border
            )
        }
    }

    // onClick_Methode um abzufragen, welche TextView ausgewählt wurde und darauf zu reagieren

    override fun onClick(v: View?) {

        // When-Abfrage mit der id der Option, als Parameter, übergebenen Views v
        // id ist die festgelegte in der Layout-Datei activity_quiz_questions.xml
        when (v?.id) {
            //Option 1
            R.id.tvOptionOne -> {
                //Aufruf der Funktion mit Option 1 (TextView tvoptionOne)
                selectedOptionView(tvOptionOne, 1)
            }
            R.id.tvOptionTwo -> {
                selectedOptionView(tvOptionTwo, 2)
            }
            R.id.tvOptionThree -> {
                selectedOptionView(tvOptionThree, 3)
            }
            R.id.tvOptionFour -> {
                selectedOptionView(tvOptionFour, 4)
            }
            R.id.btnWeiter -> {
                //Wenn mSelectedOption 0 ist, soll die nächste Frage geladen werden
                if (mSelectedOption == 0) {
                    //Zahl der nächsten Frage um 1 erhöhen
                    mCurrentPosition++

                    when {
                        //Wenn aktuelle Position innerhalb der Größe der Fragenliste ist.
                        mCurrentPosition <= mQuestionList!!.size -> {
                            //Funktion lädte neue Frage
                            setQuestion()

                        } else -> {

                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                    // Wenn ausgewählte Option nicht 0 ist, soll Antwortenauswahl farblich visualisiert werden
                    //
                }else {
                    // Speichere die aktuelle Frage in question
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    // Wenn korrekte Antwort ungleich der gegebenen ist
                    if (question!!.correctAnswer != mSelectedOption) {
                        // Setze Hintergrund der gegebenen Antwort auf falsch (wrong_option_border.xml)
                        answerView(mSelectedOption, R.drawable.wrong_option_border)
                    } else /*Richtig Antowrt: Wert um 1 erhöhen*/ {
                        mCorrectAnswers++
                    }
                    // Richtige Option muss in jedem Fall gesetzt werden, unabhängig von Nutzerauswahl
                    answerView(question!!.correctAnswer, R.drawable.correct_option_border)

                    // Wenn die aktuelle Position gleich der Größe der Fragenliste ist
                    if (mCurrentPosition == mQuestionList!!.size)/*  !! = ist nicht Null */ {
                        // Der Buttontext wird auf beenden gesetzt, weil es keine weitere Frage gibt
                        btnWeiter.text = "BEENDEN"
                    } else {
                        // Sonst soll der Text nächste Frage anzeigen, weil es weitere Fragen gibt
                    }
                    // Ausgewählte Option soll auf 0 gesetzt werden -> Dadurch wird bei erneutem Klick
                    // auf btnSubmit die Abfrage in Zeile 133 true ergeben
                    mSelectedOption = 0
                }
            }
        }
    }
        /*
    Funktion bekommt gewählte Antwort und eine id des Bildes übergeben.
    Durch eine When-Abfrage wird die übergebene Antwort geprüft und der
    Hintergrund des dazugehörigen Textfeldes wird überschrieben.
     */

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                // Hintergrund des Textfeldes zu der Option wird mit übergebener Bild-id Überschrieben
                tvOptionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tvOptionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tvOptionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                tvOptionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    /*
    Funktion um ausgewähltes Textfeld im Aussehen zu ändern
    -> Rand wird lila, Text wird dunkel, Text wird Bold
     */

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        // Zurücksetzen aller Textfelder auf Anfangsaussehen
        defaultOptionsView()
        // Globale Variable der ausgewählten Option setzen
        mSelectedOption = selectedOptionNum

        // Textfarbe des übergebenen Textviews tv ändern
        tv.setTextColor(Color.parseColor("#363A43"))
        // Typeface des übergebenen Textviews tv auf fettgedruckt ändern
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        // Hintergrund des übergebenen Textviews tv auf selected_option_border.xml ändern
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selection_option_border)
    }
}