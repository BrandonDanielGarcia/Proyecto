/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Archivos {
    private final String nomArch= "Final.tesoem";
    
    List<Atributos> Datos = new ArrayList<>();
    
    public boolean VerificaArch(){
        File archivo = new File(nomArch);
        if(!archivo.exists()) return false;
        else return true;
    }
    
    public boolean Grabar(List<Atributos> Datos, int accion){
        try{
            FileWriter archivo;
            if(accion == 0){
                archivo = new FileWriter(nomArch, true);
            }else{
               archivo = new FileWriter(nomArch); 
            }
           
            try(BufferedWriter bw = new BufferedWriter(archivo)){
                for(Atributos dato: Datos){
                    bw.write(ConvierteGson(dato)+ "\n");
                }
                bw.close();
            }
            archivo.close();
        }catch(Exception ex){return false;}
        return true;
    }
    
    public void Agregar(Atributos dato){
        Datos.add(dato);
    }
    
    private String ConvierteGson(Atributos dato){
        Gson gson = new Gson();
        return gson.toJson(dato);
    }
    
    private Atributos ConvierteClase(String dato){
        Gson gson = new Gson();
        return gson.fromJson(dato, Atributos.class);
    }
    
    public boolean Leer(){
        String cadena = "";
        try{
            FileReader archivo = new FileReader(nomArch);
            BufferedReader br = new BufferedReader(archivo);
            while((cadena = br.readLine())!= null){
                Datos.add(ConvierteClase(cadena));
            }
            br.close();
            archivo.close();
        }catch(Exception ex){return false;}
        return true;
    }
    
    public List<Atributos> GetDatos(){
        return Datos;
    }
}
