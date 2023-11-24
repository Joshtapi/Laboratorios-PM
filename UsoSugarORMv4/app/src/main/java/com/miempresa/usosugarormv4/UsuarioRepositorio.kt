package com.miempresa.usosugarormv4

import com.orm.SugarRecord

class UsuarioRepositorio {
    fun crear(nombre:String,correo:String,clave:String){
        var usuario = Usuario(nombre,correo,clave)
        SugarRecord.save(usuario)
    }
    fun listar():List<Usuario>{
        var usuarios = SugarRecord.listAll(Usuario::class.java)
        return usuarios
    }
    fun borrar(id:Long){
        var usuario: Usuario = SugarRecord.findById(Usuario::class.java,id)
        SugarRecord.delete(usuario)
    }
    fun leer(id:Long):Usuario{
        var usuario: Usuario = SugarRecord.findById(Usuario::class.java,id)
        return usuario
    }
    fun actualizar(id:Long, nombre: String,correo: String,clave: String){
        var usuario: Usuario = SugarRecord.findById(Usuario::class.java, id)
        usuario.nombre = nombre
        usuario.correo = correo
        usuario.clave = clave
        SugarRecord.save(usuario)
    }

}