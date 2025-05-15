package com.example.ferretools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import com.example.ferretools.navigation.NavGraph
import com.example.ferretools.navigation.MainAppNavigation
import com.example.ferretools.navigation.AppNavigation

import com.example.ferretools.theme.FerretoolsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    FerretoolsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            //NavGraph(navController = navController)
            //MainAppNavigation(navController = navController)
            AppNavigation(navController = navController)
        }
    }
}

//class MainActivity : ComponentActivity() {

//  override fun onCreate(savedInstanceState: Bundle?) {
//      super.onCreate(savedInstanceState)
//         val db = Firebase.firestore

//         val coleccion = "negocios"

//         // Crear una referencia a un nuevo documento de la colección "Usuarios"
//         // Se genera un ID único cada vez que se ejecuta esta línea
//         val testDocRef = db.collection(coleccion).document()

//         // Crear documento

//         val docPrueba = Usuario(
//             "Gabriel",
//             "correo@unmsm.edu.pe",
//             "celular",
//             "contra",
//             RolUsuario.Administrador,
//             "aaaaaaaa",
//         )

//         // Guardar documento creado con set()
//         // Se colocan las querys en su SuccessListener para que se ejecuten una después de otra
//         // Caso contrario, se puede ejecutar el get antes que el set, por ejemplo
//         testDocRef.set(docPrueba)
//             .addOnSuccessListener {
//                 Log.d("FIREBASE", "Documento creado correctamente")

//                 // Leer documento
//                 testDocRef.get()
//                     .addOnSuccessListener { doc ->
//                         Log.d("FIREBASE", "Documento leído: ${doc.data}")

//                         // Eliminar documento
// //                        testDocRef.delete()
// //                            .addOnSuccessListener {
// //                                Log.d("FIREBASE", "Documento eliminado correctamente")
// //                            }
//                     }
//             }
//             .addOnFailureListener {
//                 Log.e("FIREBASE", "Error: ${it.message}")
//             }

   



//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            FerreToolsTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "FerreTools",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

