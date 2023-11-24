package com.miempresa.googlemapv4

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_maps.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var mMap: GoogleMap
    private var destino = ""
    private var marcadorDestino: Marker? = null
    private var coordenada = LatLng(0.0, 0.0)
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

        spnTipoMapa.onItemSelectedListener = this

        val received = this.intent.extras
        if (received != null) {
            destino = intent.extras!!.getString("destino")!!
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.setAllGesturesEnabled(true)
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isTiltGesturesEnabled = true

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 123)
        }

        when (destino.toLowerCase()) {
            "plaza de armas , arequipa" -> {
                coordenada = LatLng(-16.3988031, -71.5374435)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            "machu pichu , cuzco" -> {
                coordenada = LatLng(-13.1631, -72.5450)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            "valle del colca , arequipa" -> {
                coordenada = LatLng(-15.6419, -71.7740)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            "lineas de nazca , ica" -> {
                coordenada = LatLng(-14.8357, -74.9384)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            "punta sal , tumbes" -> {
                coordenada = LatLng(-3.8623, -81.2695)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            "amazonas" -> {
                coordenada = LatLng(-6.4631781, -79.00268)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            else -> {
                Toast.makeText(this, "No se encontró destino turístico", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 15f))
        mMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        if (marker == marcadorDestino) {
            val intent = Intent(this, destinos::class.java)
            intent.putExtra("destino", destino)
            intent.putExtra("latitud", coordenada.latitude.toString())
            intent.putExtra("longitud", coordenada.longitude.toString())

            val mensaje: String
            val foto: Int

            when (destino.toLowerCase()) {
                "plaza de armas , arequipa" -> {
                    mensaje = "La plaza Mayor o plaza de Armas de Arequipa, " +
                            "es uno de los principales espacios públicos de Arequipa " +
                            "y el lugar de fundación de la ciudad."
                    foto = R.drawable.plaza_armas
                }
                "machu pichu , cuzco" -> {
                    mensaje = "Machu Picchu es una antigua ciudad inca " +
                            "ubicada en lo alto de los Andes peruanos. Es una " +
                            "de las atracciones turísticas más famosas de Sudamérica."
                    foto = R.drawable.machu_picchu
                }
                "valle del colca , arequipa" -> {
                    mensaje = "El Valle del Colca es un impresionante valle " +
                            "andino situado en la región de Arequipa, en Perú. " +
                            "Es famoso por su paisaje natural y sus tradiciones culturales."
                    foto = R.drawable.valle_colca
                }
                "lineas de nazca , ica" -> {
                    mensaje = "Las Líneas de Nazca son geoglifos gigantes " +
                            "que se encuentran en el desierto de Nazca, en Perú. " +
                            "Estas misteriosas figuras son un enigma arqueológico."
                    foto = R.drawable.lineas_nazca
                }
                "punta sal , tumbes" -> {
                    mensaje = "Punta Sal es una hermosa playa ubicada en " +
                            "la región de Tumbes, en el norte de Perú. " +
                            "Es conocida por sus hermosas playas de arena blanca."
                    foto = R.drawable.punta_sal
                }
                "amazonas" -> {
                    mensaje = "Amazonas es una region hacia el norte del Peru"
                    foto = R.drawable.amazonas
                }
                else -> {
                    mensaje = ""
                    foto = 0
                }
            }

            intent.putExtra("info", mensaje)
            intent.putExtra("foto", foto)
            startActivity(intent)
            return true
        }
        return false
    }

    fun agregarMarcador(view: View?) {
        mMap.addMarker(
            MarkerOptions().position(
                LatLng(
                    mMap.cameraPosition.target.latitude,
                    mMap.cameraPosition.target.longitude
                )
            )
                .title("Mi Ubicación")
        )
    }

    fun dibujarCirculo(view: View?) {
        val center = LatLng(
            mMap.cameraPosition.target.latitude,
            mMap.cameraPosition.target.longitude
        )
        val circleOptions = CircleOptions()
            .center(center)
            .radius(500.0) // Radio en metros
            .strokeWidth(5f) // Grosor del borde del círculo
            .strokeColor(Color.RED) // Color del borde del círculo
            .fillColor(Color.argb(70, 255, 0, 0)) // Color de relleno del círculo

        mMap.addCircle(circleOptions)
    }

    fun dibujarPoligono(view: View?) {
        val polygonOptions = PolygonOptions()
            .add(
                LatLng(
                    mMap.cameraPosition.target.latitude + 0.001,
                    mMap.cameraPosition.target.longitude + 0.001
                ),
                LatLng(
                    mMap.cameraPosition.target.latitude - 0.001,
                    mMap.cameraPosition.target.longitude + 0.001
                ),
                LatLng(
                    mMap.cameraPosition.target.latitude - 0.001,
                    mMap.cameraPosition.target.longitude - 0.001
                ),
                LatLng(
                    mMap.cameraPosition.target.latitude + 0.001,
                    mMap.cameraPosition.target.longitude - 0.001
                )
            )
            .strokeWidth(5f) // Grosor del borde del polígono
            .strokeColor(Color.BLUE) // Color del borde del polígono
            .fillColor(Color.argb(70, 0, 0, 255)) // Color de relleno del polígono

        mMap.addPolygon(polygonOptions)
    }

    fun dibujarRectangulo(view: View?) {
        val northeast = LatLng(
            mMap.cameraPosition.target.latitude + 0.001,
            mMap.cameraPosition.target.longitude + 0.001
        )
        val southwest = LatLng(
            mMap.cameraPosition.target.latitude - 0.001,
            mMap.cameraPosition.target.longitude - 0.001
        )

        val rectangleOptions = PolygonOptions()
            .add(
                LatLng(northeast.latitude, northeast.longitude),
                LatLng(northeast.latitude, southwest.longitude),
                LatLng(southwest.latitude, southwest.longitude),
                LatLng(southwest.latitude, northeast.longitude)
            )
            .strokeWidth(5f) // Grosor del borde del rectángulo
            .strokeColor(Color.GREEN) // Color del borde del rectángulo
            .fillColor(Color.argb(70, 0, 255, 0)) // Color de relleno del rectángulo

        mMap.addPolygon(rectangleOptions)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mMap.mapType = tipos_mapas[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // No implementation needed
    }
}
