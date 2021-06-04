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
        val question1 = Question(1, "Welches war das weltweit größte Agrarhandelsunternehmen im Jahr 2018?",
        R.drawable.agrarhandel, "Archer Daniels Midland","Cofco", "Cargill", "Bunge", 3)

        questionList.add(question1)

        //2
        val question2 = Question(2, "Welches Land war der größte Produzent von Hafer im Jahr 2016?",
            R.drawable.hafer, "Indien","Brasilien", "Mexiko", "China", 1)

        questionList.add(question2)

        //3
        val question3 = Question(3, "Welches war das weltweit zweitumsatzstärkste LEH-Unternehmen im Jahr 2018?",
            R.drawable.supermarket, "Schwarz-Gruppe","Carrefour", "Costco", "Aldi Gruppe", 3)

        questionList.add(question3)

        //4
        val question4 = Question(4, "Ungefähr wieviel Kilogramm Lebensmittel werfen Verbraucher/innen in Deutschland Jahr für Jahr weg?",
            R.drawable.lebensmittelverschwendung, "350","25", "210", "75", 4)

        questionList.add(question4)

        //5
        val question5 = Question(5, "Welche waren die Top-3 der umsatzstärksten Food Delivery-Unternehmen im Jahr 2020?",
            R.drawable.lm_lieferdienst, "Amazon, Deliveroo, Doordash","Delivery Hero, Gruhub Inc, Uber Eats", "Amazon Food, Meituan Waimai, Uber Eats", "Amazon Food, Gruhub Inc., Meituan Waimai", 4)


        questionList.add(question5)

        return questionList

    }





}