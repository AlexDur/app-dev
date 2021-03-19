package com.alusch.quizzapp

//Konstanten werden immer groß geschrieben

object Constants{

    // Konstante für den extra String im Intent
    const val USER_NAME: String="user_name"
    const val CORRECT_ANSWERS = "correct_answers"
    const val TOTAL_QUESTIONS ="total_questions"

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()



        //1
        val question1 = Question(1, "Wer ist das?",
        R.drawable.trumpaugen, "Bruce Willis","Markus Maria Profitlich", "Donald Trump", "Arjen Robben", 3)

        questionList.add(question1)

        //2
        val question2 = Question(2, "Wer trägt die Haare so?",
            R.drawable.sonjazietlow_schnitt, "Katja Riemann","Inka Bause", "Sonja Zietlow", "Bärbel Schäfer", 3)

        questionList.add(question2)

        //3
        val question3 = Question(3, "Wer machte hiermit einen Kieferorthopäden über Nacht zum Millionär?",
            R.drawable.raab, "Jürgen Klopp","Dieter Bohlen", "Robert Habeck", "Stefan Raab", 4)

        questionList.add(question3)

        //4
        val question4 = Question(4, "Wessen berühmte Glatze ist das?",
            R.drawable.brucewillis_schnitt, "Jeff Bezos","Vin Diesel", "Markus Sammer", "Bruce Willis", 4)

        questionList.add(question4)

        //5
        val question5 = Question(5, "Wer trägt seine Haar gerne so?",
            R.drawable.trumptk_schnitt2, "Der junge Oliver Kahn","Der junge Dieter Bohlen", "Donald Trump", "Tiger King", 3)


        questionList.add(question5)

        return questionList

    }





}