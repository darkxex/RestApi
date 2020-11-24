/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PkgRest;

import PkgPost.ConsultaPost;
import PkgPost.JsonCountries;
import PkgPost.JsonGenerator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson; 
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author darkx
 */
@Path("api/v1")
public class ProductoResource {
  String KeyApiCompared = "ZXN0b3lkZW50cm9oYWNrZXJtYW4=";
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProductoResource
     */
    public ProductoResource() {
    
   
    
    }
    
    

    /**
     * Retrieves representation of an instance of PkgRest.ProductoResource
     * @return an instance of java.lang.String
     */
    

      @GET
    @Path("countries/{code}/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countriesinfo(@HeaderParam("X-API-KEY") String apikey,@PathParam("code") String code) {   
     JsonCountries jsonmode = new JsonCountries();
      
     // lista.add(p);
     Gson gson = new Gson();    
     
        
       
        //TODO return proper representation object
        String result = "";
          String consulta = "SELECT a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name FROM inc a where a.alpha2 = \""+code+"\"";
       String indicador = ""; 
       String valueindicator = "";
      String unit = "";
      String description = "";
      String temporalJson = "";
     
          try {
                  Class.forName("com.mysql.jdbc.Driver");
					Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/test","root" ,"");
					Statement comando=conexion.createStatement();
					ResultSet registro = comando.executeQuery(consulta);
					while (registro.next())
                                   {
                                      
                                   result = result + "\n"+ registro.getString("alpha3") + " - "+ registro.getString("alpha2") +
                                        " - "+ registro.getString("cod_mon_alf")+
                                        " - "+ registro.getString("cod_mon_num")+
                                        " - "+ registro.getString("nomb_mon")+
                                        " - "+ registro.getString("lang")+
                                        " - "+ registro.getString("name");
                                 
        
                                
       jsonmode.setAbbr(registro.getString("alpha3"));
       jsonmode.setCode(registro.getString("alpha2"));
       jsonmode.setCurrencyCode(registro.getString("cod_mon_alf"));
       jsonmode.setCurrencyName(registro.getString("nomb_mon"));
       jsonmode.setLang(registro.getString("lang"));
       jsonmode.setName(registro.getString("name"));
      
                                  
       
       String json = gson.toJson(jsonmode);
            json = json.replace("\"indicatorname\"", "},\n\"indicatorname\"").replace(",}", "}");
            json = json.replace("indicator","");
            
            temporalJson =  json + "," + temporalJson;
            //temporalJson = temporalJson.substring(1,temporalJson.length());
            
                                   }
						
						 result = result.substring(1, result.length());
					
					conexion.close();
					} catch(Exception ex){
                                     if (apikey != null && apikey.contentEquals(KeyApiCompared))
                                     return Response.status(Response.Status.NOT_FOUND).entity("{\"Error\": \"Contenido no encontrado.\"}" ).build();
                                         else
                                           return Response.status(Response.Status.FORBIDDEN).entity("{\"Error\": \"Acceso denegado (Bad Api Key). \"}" ).build();
				}
             
          
        //return result;
       // temp = P.getCountryCode();
      temporalJson = temporalJson.substring(0,temporalJson.length()-1);
    temporalJson = "["+ temporalJson +"]";
       String json = temporalJson;
    if (apikey != null && apikey.contentEquals(KeyApiCompared))
     {
         return Response.ok(json, MediaType.APPLICATION_JSON).build();}
    else
       return Response.status(Response.Status.FORBIDDEN).entity("{\"Error\": \"Acceso denegado (Bad Api Key). \"}" ).build();
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      @GET
    @Path("countries/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countriesall(@HeaderParam("X-API-KEY") String apikey) {
        
     JsonCountries jsonmode = new JsonCountries();
      
     // lista.add(p);
     Gson gson = new Gson();    
  
        
       
        //TODO return proper representation object
        String result = "";
           String consulta = "SELECT a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name FROM inc a";
       String indicador = ""; 
       String valueindicator = "";
      String unit = "";
      String description = "";
      String temporalJson = "";
      
          try {
                  Class.forName("com.mysql.jdbc.Driver");
					Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/test","root" ,"");
					Statement comando=conexion.createStatement();
					ResultSet registro = comando.executeQuery(consulta);
					while (registro.next())
                                   {
                                      
                                   result = result + "\n"+ registro.getString("alpha3") + " - "+ registro.getString("alpha2") +
                                        " - "+ registro.getString("cod_mon_alf")+
                                        " - "+ registro.getString("cod_mon_num")+
                                        " - "+ registro.getString("nomb_mon")+
                                        " - "+ registro.getString("lang")+
                                        " - "+ registro.getString("name");
                                 
        
                                
       jsonmode.setAbbr(registro.getString("alpha3"));
       jsonmode.setCode(registro.getString("alpha2"));
       jsonmode.setCurrencyCode(registro.getString("cod_mon_alf"));
       jsonmode.setCurrencyName(registro.getString("nomb_mon"));
       jsonmode.setLang(registro.getString("lang"));
       jsonmode.setName(registro.getString("name"));
      
                                  
       
       String json = gson.toJson(jsonmode);
            json = json.replace("\"indicatorname\"", "},\n\"indicatorname\"").replace(",}", "}");
            json = json.replace("indicator","");
            
            temporalJson =  json + "," + temporalJson;
            //temporalJson = temporalJson.substring(1,temporalJson.length());
            
                                   }
						
						 result = result.substring(1, result.length());
					
					conexion.close();
				} catch(Exception ex){
                                     if (apikey != null && apikey.contentEquals(KeyApiCompared))
                                     return Response.status(Response.Status.NOT_FOUND).entity("{\"Error\": \"Contenido no encontrado.\"}" ).build();
                                         else
                                           return Response.status(Response.Status.FORBIDDEN).entity("{\"Error\": \"Acceso denegado (Bad Api Key). \"}" ).build();
				}
             
          
        //return result;
       // temp = P.getCountryCode();
      temporalJson = temporalJson.substring(0,temporalJson.length()-1);
    temporalJson = "["+ temporalJson +"]";
      String json = temporalJson; 
     if (apikey != null && apikey.contentEquals(KeyApiCompared))
     {
         return Response.ok(json, MediaType.APPLICATION_JSON).build();}
    else
       return Response.status(Response.Status.FORBIDDEN).entity("{\"Error\": \"Acceso denegado (Bad Api Key). \"}" ).build();
       
    }
    
       @GET
    @Path("indicators/{countryCode}/{indicatorCode}/{year}/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response jsoncode(@HeaderParam("X-API-KEY") String apikey, @PathParam("countryCode") String countryCode, @PathParam("indicatorCode") String indicadorCode, @PathParam("year") String year) {
        
     JsonGenerator jsonmode = new JsonGenerator();
      
     // lista.add(p);
     Gson gson = new Gson();    
  
        
       
        //TODO return proper representation object
        String result = "";
       String consulta = "";
       String indicador = ""; 
       String valueindicator = "";
      String unit = "";
      String description = "";
      String temporalJson = "";
      switch(indicadorCode) {
        case "dbi":
            indicador = "dbi";
            valueindicator = "indicador_dbi";
            unit = "Posición en el Ranking (Menos es Mejor).";
            description = "Doing Business Index";
           consulta = "SELECT DISTINCT a.name,a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where b.year = "+year+" and a.alpha2 = '"+countryCode+"' and a.alpha3 = b.code";
    
       //result = "esto es dbi";
        break;
        case "gdp":
            indicador = "gdp";
            valueindicator = "indicador_gdp";
            unit = "US$";
            description = "Gross Domestic Product";
             consulta = "SELECT DISTINCT a.name,a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where b.year = "+year+" and a.alpha2 = '"+countryCode+"' and a.alpha3 = b.code";
        // result = "esto es gdp";
        break;
        case "ifl":
              indicador = "ifl";
            valueindicator = "indicador_ifl";
            description = "Inflation";
            unit = "% (Porcentaje)";
            consulta = "SELECT DISTINCT a.name,a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where b.year = "+year+" and a.alpha2 = '"+countryCode+"' and a.alpha3 = b.code";
    
         //result = "esto es ifl";
        break;
         case "iva":
               indicador = "iva";
            valueindicator = "indicador_iva";
             unit = "% (Porcentaje)";
             description = "Value Added Tax";
             consulta = "SELECT DISTINCT a.name,a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where b.year = "+year+" and a.alpha2 = '"+countryCode+"' and a.alpha3 = b.code";
     
         //result = "esto es iva";
        break;
         case "prf":
               indicador = "prf";
            valueindicator = "indicador_prf";
            unit = "% (Porcentaje)";
            description = "Tax Pressure";
            consulta = "SELECT DISTINCT a.name,a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where b.year = "+year+" and a.alpha2 = '"+countryCode+"' and a.alpha3 = b.code";
    
         //result = "esto es prf";
        break;
         case "smi":
                indicador = "smi";
            valueindicator = "indicador_smi";
            unit = "US$";
             description = "Minimum Salary";
             consulta = "SELECT DISTINCT a.name,a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where b.year = "+year+" and a.alpha2 = '"+countryCode+"' and a.alpha3 = b.code";
    
         //result = "esto es smi";
        break;
           case "tda":
                    indicador = "tda";
            valueindicator = "indicador_tda";
                 unit = "% (Porcentaje)";
                   description = "Annual Unemployement Rate";
              consulta = "SELECT DISTINCT a.name,a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where b.year = "+year+" and a.alpha2 = '"+countryCode+"' and a.alpha3 = b.code";
    
         //result = "esto es tda";
        break;
           case "tsc":
                    indicador = "tsc";
            valueindicator = "indicador_tsc";
           unit = "US$(Por unidad)";
             description = "Exchange Rate";
               consulta = "SELECT DISTINCT a.name,a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where b.year = "+year+" and a.alpha2 = '"+countryCode+"' and a.alpha3 = b.code";
    

        break;
           default:
         result = "Identificador no encontrado.";
        break;
      }
          try {
                  Class.forName("com.mysql.jdbc.Driver");
					Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/test","root" ,"");
					Statement comando=conexion.createStatement();
					ResultSet registro = comando.executeQuery(consulta);
					while (registro.next())
                                   {
                                      
                                result = result + "\n"+ registro.getString("alpha3") + " - "+ registro.getString("alpha2") +
                                        " - "+ registro.getString("cod_mon_alf")+
                                        " - "+ registro.getString("cod_mon_num")+
                                        " - "+ registro.getString("nomb_mon")+
                                        " - "+ registro.getString("lang")+
                                        " - "+ registro.getString("name")+
                                        " - "+ indicador +
                                           " - "+ unit +
                                        " - "+  registro.getString(valueindicator) +
                                        " - "+ registro.getString("year");
                                 
        
                                
       jsonmode.setIndicatorCode(indicador);
       jsonmode.setAbbr(registro.getString("alpha3"));
       jsonmode.setCode(registro.getString("alpha2"));
       jsonmode.setCurrencyCode(registro.getString("cod_mon_alf"));
       jsonmode.setCurrencyName(registro.getString("nomb_mon"));
       jsonmode.setLang(registro.getString("lang"));
       jsonmode.setName(registro.getString("name"));
       jsonmode.setindicatorname(description);
       jsonmode.setUnit(unit);
       jsonmode.setValue(registro.getString(valueindicator));
       jsonmode.setYear(registro.getString("year"));
                                  
       
       String json = gson.toJson(jsonmode).replace("\"abbr\":", "\"Country\": {\n\"abbr\":");
            json = json.replace("\"indicatorname\"", "},\n\"indicatorname\"").replace(",}", "}");
            json = json.replace("indicator","");
            
            temporalJson =  json + "," + temporalJson;
            //temporalJson = temporalJson.substring(1,temporalJson.length());
            
                                   }
						
						 result = result.substring(1, result.length());
					
					conexion.close();
					} catch(Exception ex){
                                     if (apikey != null && apikey.contentEquals(KeyApiCompared))
                                     return Response.status(Response.Status.NOT_FOUND).entity("{\"Error\": \"Contenido no encontrado.\"}" ).build();
                                         else
                                           return Response.status(Response.Status.FORBIDDEN).entity("{\"Error\": \"Acceso denegado (Bad Api Key). \"}" ).build();
				}
             
          
        //return result;
       // temp = P.getCountryCode();
      temporalJson = temporalJson.substring(0,temporalJson.length()-1);
    temporalJson = "["+ temporalJson +"]";
       
        String json = temporalJson;
        
    if (apikey != null && apikey.contentEquals(KeyApiCompared))
     {
         return Response.ok(json, MediaType.APPLICATION_JSON).build();}
    else
       return Response.status(Response.Status.FORBIDDEN).entity("{\"Error\": \"Acceso denegado (Bad Api Key). \"}" ).build();
       
    }
   //
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @POST
    @Path("indicators/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response enviar(@HeaderParam("X-API-KEY") String apikey, ConsultaPost p) {
        
     JsonGenerator jsonmode = new JsonGenerator();
      
     // lista.add(p);
     Gson gson = new Gson();    
  
        
       
        //TODO return proper representation object
        String result = "";
       String consulta = "";
       String indicador = ""; 
       String valueindicator = "";
      String unit = "";
      String description = "";
      String temporalJson = "";
      switch(p.getIndicatorCode()) {
        case "dbi":
            indicador = "dbi";
            valueindicator = "indicador_dbi";
            unit = "Posición en el Ranking (Menos es Mejor).";
            description = "Doing Business Index";
           consulta = "SELECT a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where (b.year >= '"+p.getStartYear()+"' and b.year <= '"+p.getEndYear()+"') and a.alpha2 = '"+p.getCountryCode()+"' and a.alpha3 = b.code order by b.year desc";
    
       //result = "esto es dbi";
        break;
        case "gdp":
            indicador = "gdp";
            valueindicator = "indicador_gdp";
            unit = "US$";
            description = "Gross Domestic Product";
             consulta = "SELECT a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where (b.year >= '"+p.getStartYear()+"' and b.year <= '"+p.getEndYear()+"') and a.alpha2 = '"+p.getCountryCode()+"' and a.alpha3 = b.code order by b.year desc";
      // result = "esto es gdp";
        break;
        case "ifl":
              indicador = "ifl";
            valueindicator = "indicador_ifl";
            description = "Inflation";
            unit = "% (Porcentaje)";
              consulta = "SELECT a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where (b.year >= '"+p.getStartYear()+"' and b.year <= '"+p.getEndYear()+"') and a.alpha2 = '"+p.getCountryCode()+"' and a.alpha3 = b.code order by b.year desc";
    
         //result = "esto es ifl";
        break;
         case "iva":
               indicador = "iva";
            valueindicator = "indicador_iva";
             unit = "% (Porcentaje)";
             description = "Value Added Tax";
              consulta = "SELECT a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where (b.year >= '"+p.getStartYear()+"' and b.year <= '"+p.getEndYear()+"') and a.alpha2 = '"+p.getCountryCode()+"' and a.alpha3 = b.code order by b.year desc";
    
         //result = "esto es iva";
        break;
         case "prf":
               indicador = "prf";
            valueindicator = "indicador_prf";
            unit = "% (Porcentaje)";
            description = "Tax Pressure";
             consulta = "SELECT a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where (b.year >= '"+p.getStartYear()+"' and b.year <= '"+p.getEndYear()+"') and a.alpha2 = '"+p.getCountryCode()+"' and a.alpha3 = b.code order by b.year desc";
    
         //result = "esto es prf";
        break;
         case "smi":
                indicador = "smi";
            valueindicator = "indicador_smi";
            unit = "US$";
             description = "Minimum Salary";
               consulta = "SELECT a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where (b.year >= '"+p.getStartYear()+"' and b.year <= '"+p.getEndYear()+"') and a.alpha2 = '"+p.getCountryCode()+"' and a.alpha3 = b.code order by b.year desc";
    
         //result = "esto es smi";
        break;
           case "tda":
                    indicador = "tda";
            valueindicator = "indicador_tda";
                 unit = "% (Porcentaje)";
                   description = "Annual Unemployement Rate";
               consulta = "SELECT a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where (b.year >= '"+p.getStartYear()+"' and b.year <= '"+p.getEndYear()+"') and a.alpha2 = '"+p.getCountryCode()+"' and a.alpha3 = b.code order by b.year desc";
    
         //result = "esto es tda";
        break;
           case "tsc":
                    indicador = "tsc";
            valueindicator = "indicador_tsc";
           unit = "US$(Por unidad)";
             description = "Exchange Rate";
               consulta = "SELECT a.alpha3,a.alpha2,a.cod_mon_alf,a.cod_mon_num,a.nomb_mon,a.lang,a.name,b."+valueindicator+",b.year FROM inc a, "+indicador+" b where (b.year >= '"+p.getStartYear()+"' and b.year <= '"+p.getEndYear()+"') and a.alpha2 = '"+p.getCountryCode()+"' and a.alpha3 = b.code order by b.year desc";
    

        break;
           default:
         result = "Identificador no encontrado.";
        break;
      }
          try {
                  Class.forName("com.mysql.jdbc.Driver");
					Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/test","root" ,"");
					Statement comando=conexion.createStatement();
					ResultSet registro = comando.executeQuery(consulta);
					while (registro.next())
                                   {
                                      
                                result = result + "\n"+ registro.getString("alpha3") + " - "+ registro.getString("alpha2") +
                                        " - "+ registro.getString("cod_mon_alf")+
                                        " - "+ registro.getString("cod_mon_num")+
                                        " - "+ registro.getString("nomb_mon")+
                                        " - "+ registro.getString("lang")+
                                        " - "+ registro.getString("name")+
                                        " - "+ indicador +
                                           " - "+ unit +
                                        " - "+  registro.getString(valueindicator) +
                                        " - "+ registro.getString("year");
                                 
        
                                
       jsonmode.setIndicatorCode(indicador);
       jsonmode.setAbbr(registro.getString("alpha3"));
       jsonmode.setCode(registro.getString("alpha2"));
       jsonmode.setCurrencyCode(registro.getString("cod_mon_alf"));
       jsonmode.setCurrencyName(registro.getString("nomb_mon"));
       jsonmode.setLang(registro.getString("lang"));
       jsonmode.setName(registro.getString("name"));
       jsonmode.setindicatorname(description);
       jsonmode.setUnit(unit);
       jsonmode.setValue(registro.getString(valueindicator));
       jsonmode.setYear(registro.getString("year"));
                                  
       
       String json = gson.toJson(jsonmode).replace("\"abbr\":", "\"Country\": {\n\"abbr\":");
            json = json.replace("\"indicatorname\"", "},\n\"indicatorname\"").replace(",}", "}");
            json = json.replace("indicator","");
            
            temporalJson =  json + "," + temporalJson;
            //temporalJson = temporalJson.substring(1,temporalJson.length());
            
                                   }
						
						 result = result.substring(1, result.length());
					
					conexion.close();
				
                                  	} catch(Exception ex){
                                     if (apikey != null && apikey.contentEquals(KeyApiCompared))
                                     return Response.status(Response.Status.NOT_FOUND).entity("{\"Error\": \"Contenido no encontrado.\"}" ).build();
                                         else
                                           return Response.status(Response.Status.FORBIDDEN).entity("{\"Error\": \"Acceso denegado (Bad Api Key). \"}" ).build();
				}
             
          
        //return result;
       // temp = P.getCountryCode();
      temporalJson = temporalJson.substring(0,temporalJson.length()-1);
    temporalJson = "["+ temporalJson +"]";
       String json = temporalJson;
    if (apikey != null && apikey.contentEquals(KeyApiCompared))
     {
         return Response.ok(json, MediaType.APPLICATION_JSON).build();}
    else
       return Response.status(Response.Status.FORBIDDEN).entity("{\"Error\": \"Acceso denegado (Bad Api Key). \"}" ).build();
       
    }
    
   
    
    
    
    
}
