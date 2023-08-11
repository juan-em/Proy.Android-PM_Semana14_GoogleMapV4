package com.miempresa.googlemapv4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.miempresa.googlemapv4.databinding.ActivityMapsBinding
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback ,GoogleMap.OnMarkerClickListener, AdapterView.OnItemSelectedListener{
    private var destino = ""
    var marcadorDestino: Marker? = null
    var coordenada = LatLng(0.0, 0.0)
    var mensaje =""
    var imagen = ""

    private val Plaza = LatLng(-16.3988031,-71.5374435)
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val tipos_mapas = intArrayOf(
        GoogleMap.MAP_TYPE_NONE,
        GoogleMap.MAP_TYPE_NORMAL,
        GoogleMap.MAP_TYPE_SATELLITE,
        GoogleMap.MAP_TYPE_HYBRID,
        GoogleMap.MAP_TYPE_TERRAIN
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        spnTipoMapa.setOnItemSelectedListener(this)

        val recibidos = this.intent.extras

        if (recibidos != null){
            destino = intent.extras!!.getString("destino")!!
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.getUiSettings().setAllGesturesEnabled(true)
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.getUiSettings().setCompassEnabled(true)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true)
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123);
        }


        when (destino) {
            "plaza de armas" -> {
                coordenada = LatLng(-16.3988031, -71.5374435)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
                frameworkPda()

            }
            "characato" -> {
                coordenada = LatLng(-16.4704097, -71.4985086)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
                frameworkCharacato()
            }
            "colca" -> {
                coordenada = LatLng(-15.6092105, -72.0897141)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
                frameworkColca()
            }
            "yura" -> {
                coordenada = LatLng(-16.3021603, -71.623934)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
                frameworkYura()
            }
            "mirador sachaca" -> {
                coordenada = LatLng(-16.426267, -71.5676064)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
                frameworkMiradorSachaca()

            }
            else -> {
                Toast.makeText(this, "No se encontro destino turistico", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
        // Cámara
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 18f))
        // Eventos
        mMap.setOnMarkerClickListener(this)

    }

    fun moverCamara(view: View?){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Plaza,18f))
    }
    fun agregarMarcador(view:View?){
        mMap.addMarker(
            MarkerOptions().position(
                LatLng(
                    mMap.cameraPosition.target.latitude,
                    mMap.cameraPosition.target.longitude
                )
            )
                .title("Mi ubicación")
        )
    }

    fun frameworkPda(){
        val polineOptions: PolylineOptions = PolylineOptions()
            .add(LatLng(-16.398569655580047,-71.53634476262454))
            .add(LatLng(-16.399387318400173,-71.53664744512312))
            .add(LatLng(-16.399064157130624,-71.53754568450843))
            .add(LatLng(-16.398237255208116,-71.53722993893311))
            .add(LatLng(-16.398571304015036,-71.53634825931003))
            .color(ContextCompat.getColor(this,R.color.teal_700))
        val polyline: Polyline = mMap.addPolyline(polineOptions)
        val pattern:List<PatternItem> = listOf(
            Dot(),Gap(20f),Dash(50f),Gap(20f)
        )
        polyline.pattern = pattern
    }
    fun frameworkCharacato(){
        val polineOptions: PolylineOptions = PolylineOptions()
        .add(LatLng(-16.469549,-71.504977))
            .add(LatLng(-16.459223,-71.503721))
            .add(LatLng(-16.456986,-71.498038))
            .add(LatLng(-16.458649,-71.493252))
            .add(LatLng(-16.465245,-71.486831))
            .add(LatLng(-16.471429,-71.457241))
            .add(LatLng(-16.476420,-71.460232))
            .add(LatLng(-16.479520,-71.459722))
            .add(LatLng(-16.482680,-71.468022))
            .add(LatLng(-16.483762,-71.480484))
            .add(LatLng(-16.479608,-71.482328))
            .add(LatLng(-16.479020,-71.483825))
            .add(LatLng(-16.483823,-71.488385))
            .add(LatLng(-16.483668,-71.500240))
            .add(LatLng(-16.469621,-71.504877))
            .color(ContextCompat.getColor(this,R.color.purple_700))
        val polyline: Polyline = mMap.addPolyline(polineOptions)
        val pattern:List<PatternItem> = listOf(
            Dot(),Gap(20f),Dash(50f),Gap(20f)
        )
        polyline.pattern = pattern
    }
    fun frameworkColca(){
        val polineOptions: PolylineOptions = PolylineOptions()

            .add(LatLng(-15.608758, -72.090244))
            .add(LatLng(-15.608765, -72.089250))
            .add(LatLng(-15.609709, -72.089263))
            .add(LatLng(-15.609656, -72.090101))
            .add(LatLng(-15.608804, -72.090148))
            .color(ContextCompat.getColor(this,R.color.anaranjado))
        val polyline: Polyline = mMap.addPolyline(polineOptions)
        val pattern:List<PatternItem> = listOf(
            Dot(),Gap(20f),Dash(50f),Gap(20f)
        )
        polyline.pattern = pattern
    }
    fun frameworkYura(){
        val polineOptions: PolylineOptions = PolylineOptions()
            .add(LatLng(-16.323420,-71.653004))
            .add(LatLng(-16.309441,-71.668602))
            .add(LatLng(-16.314054,-71.679588))
            .add(LatLng(-16.306805,-71.677528))
            .add(LatLng(-16.280443,-71.657959))

            .add(LatLng(-16.284232,-71.642853))
            .add(LatLng(-16.280937,-71.631866))
            .add(LatLng(-16.306909,-71.605613))
            .add(LatLng(-16.374037,-71.643027))
            .add(LatLng(-16.375000,-71.660081))

            .add(LatLng(-16.366337,-71.660654))
            .add(LatLng(-16.358912,-71.672692))
            .add(LatLng(-16.323706,-71.653059))
            .color(ContextCompat.getColor(this,R.color.black))
        val polyline: Polyline = mMap.addPolyline(polineOptions)
        val pattern:List<PatternItem> = listOf(
            Dot(),Gap(20f),Dash(50f),Gap(20f)
        )
        polyline.pattern = pattern
    }
    fun frameworkMiradorSachaca(){
        val polineOptions: PolylineOptions = PolylineOptions()
            .add(LatLng(-16.426337,-71.567615))
            .add(LatLng(-16.426431,-71.567454))
            .add(LatLng(-16.426240,-71.567455))
            .add(LatLng(-16.426202,-71.567553))
            .add(LatLng(-16.426349,-71.567619))
            .color(ContextCompat.getColor(this,R.color.rojo))
        val polyline: Polyline = mMap.addPolyline(polineOptions)
        val pattern:List<PatternItem> = listOf(
            Dot(),Gap(20f),Dash(50f),Gap(20f)
        )
        polyline.pattern = pattern
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        if (p0!!.equals(marcadorDestino)) {
            val intent = Intent(this, destinos::class.java)
            intent.putExtra("destino", destino)
            intent.putExtra("latitud", p0.getPosition().latitude.toString() + "")
            intent.putExtra("longitud", p0.getPosition().longitude.toString() + "")

            when (destino) {
                "plaza de armas" -> {
                    Log.d("mensaje","aqui estoy")
                    mensaje = "La plaza Mayor o plaza de Armas de Arequipa, " +
                            "es uno de los principales espacios públicos de Arequipa " +
                            "y el lugar de fundación de la ciudad"
                    imagen = "https://lh5.googleusercontent.com/p/AF1QipM6gwEi0w6tkUol06TSj2DCl6vJJlSThoWdwG98=w408-h306-k-no"

                }
                "characato" -> {
                    mensaje = "El distrito de Characato es uno de los 29 distritos que " +
                            "conforman la provincia de Arequipa en el departamento de Arequipa, " +
                            "bajo la administración del Gobierno regional de Arequipa, en el sur del Perú."
                    imagen = "https://lh5.googleusercontent.com/p/AF1QipM6kA7QhVW5ovVN3itJFFmxyShaFK7ye3NHc1U1=w408-h306-k-no"
                }
                "colca" -> {
                    mensaje = "El Cañón del Colca se encuentra en el valle de un río del sur de Perú " +
                            "y es famoso por ser uno de los más profundos del mundo. Es un destino famoso " +
                            "para el senderismo. Es un hábitat del cóndor Andino gigante, que se observa " +
                            "desde miradores como la Cruz del Cóndor."
                    imagen = "https://lh5.googleusercontent.com/p/AF1QipPqcRz88PFTXTs7KfPDtYP01eX5C21RqOWHkLws=w408-h306-k-no"
                }
                "yura" -> {
                    mensaje = "Yura es una localidad peruana ubicada en la región Arequipa, provincia " +
                            "de Arequipa, distrito de Yura. Se encuentra a una altitud de 2529 msnm. " +
                            "Tiene una población de 172 habitantes en 1993."
                    imagen  = "https://lh5.googleusercontent.com/p/AF1QipMheDp0ZvbsVw-2CjK7WsY-1VjdZWA8Q04H1T1-=w408-h544-k-no"
                }
                "mirador sachaca" -> {
                    mensaje = "Desde lo alto se ve la extensa campiña arequipeña, las antiguas y modernas " +
                            "casas de la ciudad. Esta torre de 19 metros de altura fue construida en la cima del " +
                            "cerro que albergaba el antiguo cementerio de Sachaca, remodelado en 1996. " +
                            "El mirador tiene cinco pisos. Desde su terraza se puede apreciar a los volcanes Misti, " +
                            "Chachani y Pichu Pichu."
                    imagen = "https://lh5.googleusercontent.com/p/AF1QipN2oD5gx496Qyj3WLcGHf6N051WQjM1KyfoVfX4=w408-h272-k-no"
                }
            }
            intent.putExtra("info", mensaje)
            intent.putExtra("imagen",imagen)
            startActivity(intent)
            return true
        }
        return false

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        mMap.setMapType(tipos_mapas[p2])
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}