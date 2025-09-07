package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                    UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
//                    UnitConverter(
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier = Modifier){
    var inputvaluse by remember { mutableStateOf("")}
    var outputvalue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00) }

    val customTextStyle= TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        color = Color.Red
    )


    fun  convertUnits(){
        // ?: -elvis operator
        val inputValueDouble = inputvaluse.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.doubleValue *100/oConversionFactor.doubleValue).roundToInt()/100.0
        outputvalue=result.toString()
    }





    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text("Unit Converter"
//            , modifier = modifier.padding(10.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        OutlinedTextField(
//            modifier = modifier,
            value = inputvaluse, onValueChange = {
                inputvaluse=it
                convertUnits()
        },
            label = {Text("Input Value")}

            )
//        BasicTextField()/
        Spacer(modifier = Modifier.height(15.dp))

        Row {
//            val context= LocalContext.current
//            Button(onClick = {Toast.makeText(context,"Thanks For Clicking",Toast.LENGTH_LONG).show()}) {
//            Text("Click")
//        }
            Box {
                //
                Button(onClick = {iExpanded =true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded =false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeter")},
                        onClick = {/*Todo*/
                            iExpanded =false
                            inputUnit = "Centimeter"
                            conversionFactor.doubleValue=0.01
                            convertUnits()

//                            conversionFactor.doubleValue = 0.01
//                            outputvalue = (inputvaluse.toDouble() * conversionFactor.value).toString()
                        }
                    )
                    
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            iExpanded =false
                            inputUnit = "Meter"
                            conversionFactor.doubleValue=1.0
                            convertUnits()


                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            iExpanded =false
                            inputUnit = "Feet"
                            conversionFactor.doubleValue=0.3048
                            convertUnits()


                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Millimeter")},
                        onClick = {
                            iExpanded =false
                            inputUnit = "Millimeter"
                            conversionFactor.doubleValue=0.001
                            convertUnits()


                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            Box { Button(onClick = {
                oExpanded =true
            }) {
                Text(outputUnit)
                 Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down Menu")
            }
                DropdownMenu(expanded = oExpanded , onDismissRequest = {oExpanded=false
                }) {
                    DropdownMenuItem(
                        text = {Text("Centimeter")},
                        onClick = {/*Todo*/
                            oExpanded =false
                            outputUnit = "Centimeter"
                            oConversionFactor.doubleValue=0.01
                            convertUnits()
                        }

                    )
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            oExpanded =false
                            outputUnit = "Meter"
                            oConversionFactor.doubleValue=1.00
                            convertUnits()

                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            oExpanded =false
                            outputUnit = "Feet"
                            oConversionFactor.doubleValue=0.3048
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Millimeter")},
                        onClick = {
                            oExpanded =false
                            outputUnit = "Millimeter"
                            oConversionFactor.doubleValue=0.001
                            convertUnits()
                        }
                    )
                }

            }

        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Results :$outputvalue  $outputUnit", style =
            customTextStyle
//            MaterialTheme.typography.headlineMedium
//            TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Red)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}