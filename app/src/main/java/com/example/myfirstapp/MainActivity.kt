package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirstapp.model.Conference
import com.example.myfirstapp.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val jsonArr = JSONArray(
            "[\n" +
                "            {\n" +
                    "                'biography' : 'Cesar Fajardo, creativo y content creator del equipo de Platzi, transforma las ideas en historias inspiradoras y contenidos innovadores que impactan al mundo para generar gracias, no solo likes, de nada sirve tu talento si no estas haciendo algo con el, de nada sirven tus habilidades si no tienes ideas para fusionarlas.',\n" +
                    "                'category' : 5,\n" +
                    "                'image' : 'https://pbs.twimg.com/profile_images/1015273976438902784/T0gZSbTP_400x400.jpg',\n" +
                    "                'jobtitle' : 'Content Creator',\n" +
                    "                'name' : 'Cesar Fajardo',\n" +
                    "                'twitter' : 'fajardocesar',\n" +
                    "                'workplace' : 'Platzi'\n" +
                    "            },\n" +
                    "            {\n" +
                    "                'biography' : 'Cesar Fajardo, creativo y content creator del equipo de Platzi, transforma las ideas en historias inspiradoras y contenidos innovadores que impactan al mundo para generar gracias, no solo likes, de nada sirve tu talento si no estas haciendo algo con el, de nada sirven tus habilidades si no tienes ideas para fusionarlas.',\n" +
                    "                'category' : 5,\n" +
                    "                'image' : 'https://pbs.twimg.com/profile_images/1015273976438902784/T0gZSbTP_400x400.jpg',\n" +
                    "                'jobtitle' : 'Content Creator',\n" +
                    "                'name' : 'Cesar Fajardo',\n" +
                    "                'twitter' : 'fajardocesar',\n" +
                    "                'workplace' : 'Platzi'\n" +
                    "            }\n" +
                "]"
                )

        val jsonArr2 = JSONArray(
            "[\n" +
                    "            {\n" +
                    "                \"datetime\" : 1564830000,\n" +
                    "                \"description\" : \"Yo les voy a hablar el día de hoy de un tema que si soy exitoso, contrario a lo que algunos de ustedes me conocen saben que juego muchos videojuegos y que trabajo con ellos, toda mi vida llevo 21 años hablando en público a lo largo de la industria de la tecnología y alrededor por supuesto de la oportunidad tremenda que los videojuegos, la realidad virtual y la realidad aumentada ofrecen, sigo convencido en ello y sigo trabajando en eso. Pero estoy aquí con otro sombrero, el día de hoy si soy exitoso van a terminar con dos sentimientos, un poco de miedo y sobretodo el miedo que desemboca en la acción.\",\n" +
                    "                \"speaker\" : \"Mario Valle\",\n" +
                    "                \"tag\" : \"Negocios\",\n" +
                    "                \"title\" : \"Ahorrar no te va a salvar del futuro\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"datetime\" : 1564862400,\n" +
                    "                \"description\" : \"En esta charla Erika Oregel del equipo de Platzi nos invita a explorar nueva forma de adquirir gustos y pasiones, nos invita a procrastinar de forma consciente. \",\n" +
                    "                \"speaker\" : \"Erika Oregel\",\n" +
                    "                \"tag\" : \"Procastinación\",\n" +
                    "                \"title\" : \"Procrastinar puede ser el alimento de tus futuras pasiones\"\n" +
                    "            }\n" +
                    "]"
        )


        // Person Object
        val firebaseFirestore = FirebaseFirestore.getInstance()
        val speaker = Speaker()
        for (i in 0 until jsonArr.length()){
            val aux = jsonArr.get(i) as JSONObject

            speaker.name      = aux.getString("name")
            speaker.jobTitle  = aux.getString("jobtitle")
            speaker.workplace = aux.getString("workplace")
            speaker.biography = aux.getString("biography")
            speaker.twitter   = aux.getString("twitter")
            speaker.image     = aux.getString("image")
            speaker.category  = 0

        }

        // Conference Object
        val conference = Conference()
        for (i in 0 until jsonArr2.length()){
            val aux = jsonArr2.get(i) as JSONObject

            conference.title        = aux.getString("title")
            conference.description  = aux.getString("description")
            conference.tag          = aux.getString("tag")

            val dateTime = Calendar.getInstance()
            dateTime.timeInMillis   = aux.getLong("datetime") * 1000
            conference.datetime     = dateTime.time
            conference.speaker      = aux.getString("speaker")

        }
        firebaseFirestore.collection("speakers").document().set(speaker)
        firebaseFirestore.collection("conference").document().set(conference)
    }
}
